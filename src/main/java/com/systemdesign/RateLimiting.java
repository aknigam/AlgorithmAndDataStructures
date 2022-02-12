package com.systemdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class RateLimiting {

    private Map<UUID, Request> userRequestLog = new HashMap<>();

    private long rateLimitDurationInMillis = 2000;
    private int allowedRequests = 10;

    // 5 requests per second for a user
    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        RateLimiting rl = new RateLimiting();
        UUID userId = UUID.randomUUID();

        long start = System.currentTimeMillis();
        for (int i = 1; i < 100; i++) {
            int count = random.nextInt(3);
            Request nr = new Request(count> 0 ? count : 1, System.currentTimeMillis());
            rl.handleNewRequest(nr, userId);
            sleep(random.nextInt(350));
        }

        long elapsed = System.currentTimeMillis() - start;


        System.out.println("Time ---- >> "+ elapsed);

        Request tr = rl.getUserRequest(userId);
        int i=1;
        while (true) {
            long since = (tr.timestamp - start);
            int count = tr.isRejected ? -tr.requestsCount : tr.requestsCount;
            System.out.println( since  +" , "+ count);
            if (tr.previous == null) {
                break;
            }
            tr = tr.previous;
            continue;
        }

    }

    private static void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    Request getUserRequest(UUID userId) {
        return userRequestLog.get(userId);
    }

    public void handleNewRequest(Request nr, UUID userId){
        Request r = userRequestLog.get(userId);
        if(r == null) {
            nr.isRejected = false;
            userRequestLog.put(userId, nr);
            return;
        }

        nr.previous = r;
        nr.next = null;
        r.next = nr;
        r = nr;
        userRequestLog.put(userId, r);

        Request tr = r;
        long endTime = r.timestamp;
        int noOfRequestsInCurrentWindow = r.requestsCount;

        for (int i = 0; i < 45; i++) {
            Request prev = tr.previous;
            if(prev == null) {
                r.isRejected = rateLimitDurationInMillis == 0;
                break;
            }

            long timeElapsed =  endTime - prev.timestamp;
            
            if(timeElapsed <= rateLimitDurationInMillis) {
                if(!prev.isRejected) {
                    noOfRequestsInCurrentWindow = noOfRequestsInCurrentWindow + prev.requestsCount;
                }
                r.isRejected = noOfRequestsInCurrentWindow > allowedRequests;
            }
            else {
                r.isRejected = false;
            }
            // break the chain here. no need to go further
            if(r.isRejected) {
//                if(prev.previous != null) {
//                    prev.previous.next = null;
//                    prev.previous = null;
//                }
                break;

            }
            tr = prev;

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
        int requestsCount;
        long timestamp;
        boolean isRejected;

        Request next;
        Request previous;

        public Request(int requestsCount, long timestamp) {
            this.requestsCount = requestsCount;
            this.timestamp = timestamp;
        }


        @Override
        public String toString() {
            return
                    "Count=" + requestsCount +
                    ", timestamp=" + timestamp +
                    ", isRejected=" + isRejected ;
        }
    }





}
