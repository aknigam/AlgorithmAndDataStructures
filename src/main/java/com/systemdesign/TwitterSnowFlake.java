package com.systemdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TwitterSnowFlake {

    // this can be cleaned regularly to keep the number of entries limited
    private static ConcurrentHashMap<Long, AtomicInteger> counterTimeMap=  new ConcurrentHashMap<>();
//    private static HashMap<Long, AtomicInteger> counterTimeMap=  new HashMap<>();
    private static ConcurrentHashMap<String, Object> uniques=  new ConcurrentHashMap<>();


    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        long epoch =  System.currentTimeMillis() - 30*365*24*60*60*1000;
        Worker w = new Worker(1, epoch);
        StringBuilder s= new StringBuilder();
        List<Thread> threads = new ArrayList<>();
        for (int j = 0; j < 1000000; j++) {
            Thread t = new Thread(() -> {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {

//                    s.append("["+name+"]\t" +w.generateId()+"\n");
                    String id = w.generateId();
                    if(uniques.get(id) != null) {
                        System.out.println("Duplicate -> "+id);
                    }
                    else {
                        uniques.put(id, new Object());
                    }

                    System.out.println("["+name+"]\t" +id);


                }
            }, "t"+j);
            threads.add(t);
            t.start();
            
        }

        for (Thread t :
                threads) {
            t.join();
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(time +" -> " + counterTimeMap.size());

        int max = -1;
        for (Map.Entry<Long, AtomicInteger> e:
             counterTimeMap.entrySet()) {
            int val = e.getValue().get();
            if(val > max) {
                max = val;
            }
        }

        System.out.println("Max -> "+ max);

//        System.out.println(s);





    }


    static class Worker {

        private final String dataCenterBinary;
        private final String machineIdBinary;
        private final String dataCenter;
        private final String machineId;
        private final long epoch;
        private final TimestampCounter counter;


        Worker(int dataCenter, long twitterEpoch){
            this.dataCenter = pad(2, Integer.toString(dataCenter));
            this.dataCenterBinary = pad(5, Integer.toBinaryString(dataCenter));

            int mId = acquireMachineNumber();
            this.machineId = pad(2, Integer.toString(mId)) ;
            this.machineIdBinary = pad(5, Integer.toBinaryString(mId)) ;
            this.epoch = twitterEpoch;

            counter = new TimestampCounter();

        }

        private int acquireMachineNumber() {
            return 0;
        }

        private void sendHeartBeat(){

        }

        public  String generateId() {
            long currentTime = System.currentTimeMillis();
            long timeSinceTwitterEpoch = currentTime - epoch;

            // String binary = "time-dc-mc-counter";

            String timeBinary = pad(41,Long.toBinaryString(timeSinceTwitterEpoch));
            String timePart = pad(13, Long.toString(timeSinceTwitterEpoch));
            // get the 41 bit binary representation

            int nextVal = counter.giveNextCounter(timeSinceTwitterEpoch); // 4, 12

            String nextValString = pad(4, Integer.toString(nextVal));
            String nextValStringBinary = pad(12, Integer.toBinaryString(nextVal));

            String binary = timeBinary+ "-"+dataCenterBinary+ "-"+machineIdBinary+"-"+ nextValStringBinary;
            return timePart +"-"+ dataCenter +"-"+ machineId +"-"+ nextValString +"\t"+ binary;
        }
        // refer: https://www.baeldung.com/java-pad-string
        private String pad(int totalLength, String inputString){
            return String.format("%1$" + totalLength + "s", inputString).replace(' ', '0');
        }

        class TimestampCounter {
            private AtomicLong timestamp;
            private AtomicInteger counter;



            TimestampCounter(){
                long ts = System.currentTimeMillis() - epoch;
                timestamp = new AtomicLong(ts);
                counter = new AtomicInteger(-1);
                counterTimeMap.putIfAbsent(ts, counter);
            }

            public int giveNextCounter(long ts){

                counterTimeMap.putIfAbsent(ts, new AtomicInteger(-1));
                return counterTimeMap.get(ts).incrementAndGet();

            /*
                if (this.timestamp.get() != ts) {
                    this.timestamp.set(ts);
                    this.counter.set(-1);
                    int currVal = counter.get();
                    return counter.incrementAndGet();
                }
                else  {
                    if(counter.get() >= 4096) {
                        throw new RuntimeException("Millisecond counter limit exceeded");
                    }
                    return counter.incrementAndGet();
                }

             */


            }
        }

    }

}
