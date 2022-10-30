package com.systemdesign;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class LoadBalancer {

    private static final String LEAST_CONNECTIONS = "LEAST_CONNECTIONS";
    private static final String LEAST_MEAN_RESPONSE_TIME = "MEAN_RESPONSE_TIME";
    private static final String LEAST_REQUESTS_PROCESSED = "LEAST_REQUESTS_PROCESSED";
    Map<String, NodeMetrics> metrics = new HashMap<>();

    Map<String, NodeMetrics> nodeStats = new HashMap<>();

    Random responseTime = new Random();

    int nodeCount = 5;

    public static void main(String[] args) throws InterruptedException {
        LoadBalancer lb = new LoadBalancer();

//        NodeMetrics nm = new NodeMetrics("node-0");
//        lb.metrics.put(LEAST_CONNECTIONS, nm);
//        lb.metrics.put(LEAST_MEAN_RESPONSE_TIME, nm);
//        lb.metrics.put(LEAST_REQUESTS_PROCESSED, nm);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int nodeNum = i % 5;
            String nodeName= "node-"+nodeNum;

            // check if any node is idle. If yes then send the request to it.
            // this means serially send request to all in the first iteration
            // else take the decision based on metrics

            if(i < 5) {
                threads.add(lb.sendRequest(nodeName));
                Thread.sleep(100);
            }
            else {
                join(threads);
                NodeMetrics node = lb.metrics.get(LEAST_REQUESTS_PROCESSED);
                if (node != null) {
                    nodeName = node.nodeName;
                    System.out.println("Sending request to - " + nodeName);
                    threads.add(lb.sendRequest(nodeName));
                    Thread.sleep(100);
                } else {
                    System.out.println("ERROORR !!!!");
                    threads.add(lb.sendRequest(nodeName));
                    Thread.sleep(100);

                }
            }
            if(i%10 == 0) {
                System.out.println("\nMETRICS\n"+lb.metrics);
//                System.out.println("\n");
            }
        }

        join(threads);

        System.out.println("Server shutdown");

        System.out.println(lb.metrics);

        for (Map.Entry e: lb.nodeStats.entrySet()) {
            System.out.println(e.getValue());
        }
    }

    private static void join(List<Thread> threads) throws InterruptedException {
        for (Thread t :
                threads) {
            t.join();
        }
    }


    // method called to update the counter if the new value is higher
    synchronized void updateGlobalMetrics( String metric, NodeMetrics nm){

        NodeMetrics currentMetric = metrics.get(metric);
        if(currentMetric == null) {
            metrics.put(metric, nm);
            return;
        }
        if(metric == LEAST_CONNECTIONS){
            if (currentMetric.activeConnections.intValue() > nm.activeConnections.intValue()) {
                metrics.put(metric, nm);
            }
        }

        else if(metric == LEAST_MEAN_RESPONSE_TIME){
            if (currentMetric.meanResponseTime.intValue() > nm.meanResponseTime.intValue()) {
                metrics.put(metric, nm);
            }
        }

        else if(metric == LEAST_REQUESTS_PROCESSED){
            int curVal = currentMetric.noOfRequests.intValue();
            // any noe which has not processed any request ? put that
            // this requires iteration through all node metrics
            if(curVal == 0) {
                metrics.put(metric, nm);
            }
            else if (currentMetric.noOfRequests.intValue() > nm.noOfRequests.intValue()) {
                metrics.put(metric, nm);
            }
        }
    }

    Thread sendRequest(String node) throws InterruptedException {

        NodeMetrics nodeMetrics = getNodeMetrics(node);
        long start = System.currentTimeMillis();

        long rt = responseTime.nextInt(30);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    nodeMetrics.activeConnections.incrementAndGet();
                    nodeMetrics.noOfRequests.incrementAndGet();
                    System.out.println(rt);
                    sleep(rt);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    long duration = System.currentTimeMillis() - start;
                    nodeMetrics.activeConnections.decrementAndGet();
                    nodeMetrics.updateMeanResponseTime(duration);

                    updateGlobalMetrics(LEAST_CONNECTIONS, nodeMetrics);
                    updateGlobalMetrics(LEAST_REQUESTS_PROCESSED, nodeMetrics);
                    updateGlobalMetrics(LEAST_MEAN_RESPONSE_TIME, nodeMetrics);
                }
            }
        };

        t.start();

        return t;

    }
    
    // mean3 = (a1 + a2 + a3 ) / 3
    // mean4 = (a1 + a2 + a3 + a4) / 4
    // mean4 = (mean3*3 + a4) /4

    private NodeMetrics getNodeMetrics(String node) {
        NodeMetrics nm = nodeStats.get(node);
        if(nm != null) {
            return nm;
        }

        nm = new NodeMetrics(node);
        nodeStats.put(node,nm);
        return nm;
    }

    static class NodeMetrics {
        String nodeName;
        AtomicLong noOfRequests = new AtomicLong(0);
        AtomicLong activeConnections = new AtomicLong(0);
        AtomicLong meanResponseTime = new AtomicLong(0);

        @Override
        public String toString() {
            return "NodeMetrics{" +
                    "nodeName='" + nodeName + '\'' +
                    ", noOfRequests=" + noOfRequests +
                    ", activeConnections=" + activeConnections +
                    ", meanResponseTime=" + meanResponseTime +
                    '}';
        }

        public NodeMetrics(String node) {
            this.nodeName =node;
        }

        public AtomicLong getActiveConnections() {
            return activeConnections;
        }

        public void setActiveConnections(AtomicLong activeConnections) {
            this.activeConnections = activeConnections;
        }

        public AtomicLong getMeanResponseTime() {
            return meanResponseTime;
        }

        public void setMeanResponseTime(AtomicLong meanResponseTime) {
            this.meanResponseTime = meanResponseTime;
        }

        public AtomicLong getNoOfRequests() {
            return noOfRequests;
        }

        public void setNoOfRequests(AtomicLong noOfRequests) {
            this.noOfRequests = noOfRequests;
        }

        public synchronized void updateMeanResponseTime(long duration) {

            long currentMean = meanResponseTime.get();

            long noOfRequestsTillNow = noOfRequests.get();
            if(noOfRequestsTillNow == 0) {
                meanResponseTime.set(duration);
            }
            else {
                long mrt = (currentMean * noOfRequestsTillNow + duration) / (noOfRequestsTillNow + 1);
                meanResponseTime.set(mrt);
            }
        }
    }


}
