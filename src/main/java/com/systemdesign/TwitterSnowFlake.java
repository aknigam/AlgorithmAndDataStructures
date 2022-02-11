package com.systemdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TwitterSnowFlake {

    public static void test(String[] args) {


        System.out.println(String.format("%1$" + 12 + "s", "1234567890").replace(' ', '0'));

        System.out.println(System.currentTimeMillis());

        long currentTime = System.currentTimeMillis() - 1644485024328l;
//        long timeSinceTwitterEpoch = currentTime - epoch;

        String binary = "time-dc-mc-counter";

        // get the 41 bit binary representation
        System.out.println(currentTime);
        System.out.println(Long.toBinaryString(currentTime));
        System.out.println(Long.toBinaryString(System.currentTimeMillis()));

    }

    public static void main(String[] args) throws InterruptedException {

        long epoch =  System.currentTimeMillis() - 30*365*24*60*60*1000;
        Worker w = new Worker(1, epoch);
        StringBuilder s= new StringBuilder();
        List<Thread> threads = new ArrayList<>();
        for (int j = 0; j < 500; j++) {
            Thread t = new Thread(() -> {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 5; i++) {
//                    s.append("["+name+"]\t" +w.generateId()+"\n");
                    System.out.println("["+name+"]\t" +w.generateId());
//                    if(i % 2 == 0) {
//                        try {
//                            Thread.sleep(5);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            }, "t"+j);
            threads.add(t);
            t.start();
            
        }

        for (Thread t :
                threads) {
            t.join();
        }

        System.out.println(s);





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
                timestamp = new AtomicLong(System.currentTimeMillis() - epoch);
                counter = new AtomicInteger(-1);
            }

            public int giveNextCounter(long ts){

                if(this.timestamp.get() == ts) {
                    if(counter.get() >= 4096) {
                        throw new RuntimeException("Millisecond counter limit exceeded");
                    }
                }
                else  {
                    this.timestamp.compareAndSet(this.timestamp.get(), ts);
                    this.counter.set(-1);
                }
                return counter.incrementAndGet();

            }
        }

    }

}
