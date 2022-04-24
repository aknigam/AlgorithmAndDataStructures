package com.systemdesign;

import java.util.*;

public class RateLimiting {

    private Map<UUID, Request> userRequestLog = new HashMap<>();
    private int debugGlobalCount =0;

    private long rateLimitDurationInMillis = 50;
    private int allowedRequests = 15;

    public synchronized void handleNewRequest(Request nr, UUID userId){
        nr.timestamp = System.currentTimeMillis();
        Request r = userRequestLog.get(userId);
        debugGlobalCount++;
        long endTime = nr.timestamp;
        int noOfRequestsInCurrentWindow = nr.requestsCount;
        Request prev = r;
        // ITERATE OVER ALL THE PAST REQUESTS IN THE CURRENT TIME WINDOW
        while (true) {
            // this also handles first request as well
            if(prev == null) {
                nr.isRejected = noOfRequestsInCurrentWindow > allowedRequests;
                break;
            }

            long timeElapsed =  endTime - prev.timestamp;
            // IF TIME ELAPSED SINCE PREVIOUS REQUEST IS LESS THAN THE WINDOW SIZE then INCREMENT THE COUNT AND CHECK
            // AGAINST ALLOWED LIMIT
            if(timeElapsed <= rateLimitDurationInMillis) {
                // IF PREVIOUS WAS NOT REJECTED THEN INCREASE THE COUNT
                noOfRequestsInCurrentWindow = noOfRequestsInCurrentWindow + prev.requestsCount;
                nr.isRejected = noOfRequestsInCurrentWindow > allowedRequests;
                if(nr.isRejected){
                    break;
                }
                prev = prev.previous;

            }
            else {
                // if current window is more than the max window size then set status and break;
                nr.isRejected = noOfRequestsInCurrentWindow > allowedRequests;
                // DROP THE PREVIOUS AS THE CURRENT WINDOW EXCEEDED THE MAX WINDOW SIZE
                if(prev != null) {
                    // then break the link;
                    Request nxt = prev.next;
                    prev.next = null;
                    if(nxt != null) {
                        nxt.previous = null;
                    }
                }
                break;
            }

        }

        // ADD TO THE ALLOWED REQUEST LIST IF THE REQUEST WAS NOT REJECTED
        if(!nr.isRejected) {
            if (r != null) {
                nr.previous = r;
                nr.next = null;
                r.next = nr;
            }
            userRequestLog.put(userId, nr);
        }

    }

    private Request addRequestToHead(Request nr, Request r) {
        nr.previous = r;
        nr.next = null;
        r.next = nr;
        r = nr;
        return r;
    }


    static class Request{
        private final int sequence;
        private final String requester;
        int requestsCount;
        long timestamp;
        boolean isRejected;


        Request next;
        Request previous;

        public Request(int requestsCount, int sequence, String name) {
            this.requestsCount = requestsCount;
            this.sequence = sequence;
            this.requester = name;
        }


        @Override
        public String toString() {
            return
                    "Thread=" + requester +
                    ", Count=" + requestsCount +
                    ", timestamp=" + timestamp +
                    ", isRejected=" + isRejected ;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        RateLimiting rl = new RateLimiting();
        UUID userId = UUID.randomUUID();
        final List<Thread> threads = new ArrayList<>();
        final List<Request> allRequests =  new ArrayList<>();

        long start = System.currentTimeMillis();


        int noOfWorkers = 50;
        for (int j = 0; j < noOfWorkers; j++) {
            Thread t = new Thread(() -> {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {
                    Request nr = new Request(1,  i, name);
                    allRequests.add(nr);
                    rl.handleNewRequest(nr, userId);
//                    System.out.println(name+": "+i);
                    sleep(20);
                }
            }, "t"+j);
            sleep(random.nextInt(10));
            threads.add(t);
            t.start();

        }

        for (Thread t :
                threads) {
            t.join();
        }

        postLoad(rl, userId, allRequests, start);
    }

    // 5 requests per second for a user
    public static void mainOld(String[] args) throws InterruptedException {

        Random random = new Random();
        RateLimiting rl = new RateLimiting();
        UUID userId = UUID.randomUUID();

        List<Request> allRequests =  new ArrayList<>();

        long start = System.currentTimeMillis();
        for (int i = 1; i < 100; i++) {
            int count = random.nextInt(10);
            Request nr = new Request(count> 0 ? count : 1, i, "main");
            allRequests.add(nr);
            rl.handleNewRequest(nr, userId);
//            sleep(random.nextInt(350));
            sleep(250);
        }

        postLoad(rl, userId, allRequests, start);


    }

    private static void postLoad(RateLimiting rl, UUID userId, List<Request> allRequests, long start) {
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("Time ---- >> "+ elapsed);
        int i =0;
        for (int j = 0; j < allRequests.size(); j++) {
            Request tr = allRequests.get(j);
            if(tr == null){
                continue;
            }

            long since = (tr.timestamp - start);
            int count = tr.isRejected ? -tr.requestsCount : tr.requestsCount;
            System.out.println(j+", "+ since + " , " + count);
        }


        Request r = rl.userRequestLog.get(userId);
        int count =0;
        while (r.previous != null) {
            count++;
            r = r.previous;
        }

        System.out.println(count);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Request getUserRequest(UUID userId) {
        return userRequestLog.get(userId);
    }




}
