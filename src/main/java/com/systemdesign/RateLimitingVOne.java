package com.systemdesign;

import java.util.*;

public class RateLimitingVOne {

    private Map<UUID, Request> userRequestLog = new HashMap<>();

    private long rateLimitDurationInMillis = 2000;
    private int allowedRequests = 20;

    // 5 requests per second for a user
    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        RateLimitingVOne rateLimiter = new RateLimitingVOne();
        UUID userId = UUID.randomUUID();

        List<Request> allRequests =  new ArrayList<>();

        long start = System.currentTimeMillis();
        for (int i = 1; i < 100; i++) {
            int count = random.nextInt(10);
            Request nr = new Request(count> 0 ? count : 1, System.currentTimeMillis(), i);
            allRequests.add(nr);
            rateLimiter.handleNewRequest(nr, userId);
//            sleep(random.nextInt(350));
            sleep(250);
        }

        long elapsed = System.currentTimeMillis() - start;

        System.out.println("Time ---- >> "+ elapsed);

        for (Request tr :  allRequests) {
            long since = (tr.timestamp - start);
            int count = tr.isRejected ? -tr.requestsCount : tr.requestsCount;
            System.out.println(tr.sequence+", "+ since + " , " + count);
        }


        Request r = rateLimiter.userRequestLog.get(userId);
        int count =0;
        while (r.previous != null) {
            count++;
            r = r.previous;
        }

        System.out.println(count);


    }

    private static void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    Request getUserRequest(UUID userId) {
        return userRequestLog.get(userId);
    }

    public void handleNewRequest(Request incomingRequest, UUID userId){
        Request r = userRequestLog.get(userId);

        long endTime = incomingRequest.timestamp;
        int noOfRequestsInCurrentWindow = incomingRequest.requestsCount;
        Request prev = r;
        // ITERATE OVER ALL THE PAST REQUESTS IN THE CURRENT TIME WINDOW
        while (true) {
            // this also handles first request as well
            if(prev == null) {
                incomingRequest.isRejected = noOfRequestsInCurrentWindow > allowedRequests;
                break;
            }

            long timeElapsed =  endTime - prev.timestamp;
            // IF TIME ELAPSED SINCE PREVIOUS REQUEST IS LESS THAN THE WINDOW SIZE then INCREMENT THE COUNT AND CHECK
            // AGAINST ALLOWED LIMIT
            if(timeElapsed <= rateLimitDurationInMillis) {
                // IF PREVIOUS WAS NOT REJECTED THEN INCREASE THE COUNT
                noOfRequestsInCurrentWindow = noOfRequestsInCurrentWindow + prev.requestsCount;
                incomingRequest.isRejected = noOfRequestsInCurrentWindow > allowedRequests;
                if(incomingRequest.isRejected){
                    break;
                }
                prev = prev.previous;

            }
            else {
                // if current window is more than the max window size then set status and break;
                incomingRequest.isRejected = noOfRequestsInCurrentWindow > allowedRequests;
                break;
            }

        }

//        if(prev != null) {
//            // then break the link;
//            Request nxt = prev.next;
//            prev.next = null;
//            if(nxt != null) {
//                nxt.previous = null;
//            }
//        }


        // ADD TO THE ALLOWED REQUEST LIST IF THE REQUEST WAS NOT REJECTED
        if(!incomingRequest.isRejected) {
            if (r != null) {
                incomingRequest.previous = r;
                incomingRequest.next = null;
                r.next = incomingRequest;
            }
            userRequestLog.put(userId, incomingRequest);
        }
        else {

        }
//        else {
//            System.out.println("\t request rejected: "+ nr);
//        }

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
        int requestsCount;
        long timestamp;
        boolean isRejected;

        int allowedRequests;
        int rejectedRequestsCount;

        Request next;
        Request previous;

        public Request(int requestsCount, long timestamp, int sequence) {
            this.requestsCount = requestsCount;
            this.timestamp = timestamp;
            this.sequence = sequence;
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
