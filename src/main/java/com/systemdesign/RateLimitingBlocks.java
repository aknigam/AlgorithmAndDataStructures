package com.systemdesign;

/**
 * Refer: https://medium.com/@saisandeepmopuri/system-design-rate-limiter-and-data-modelling-9304b0d18250
 *
 * Sliding window counters
 * - This is a hybrid of Fixed Window Counters and Sliding Window logs
 * - The entire window time is broken down into smaller buckets. The size of each bucket depends on how much elasticity is allowed for the rate limiter
 * - Each bucket stores the request count corresponding to the bucket range.
 *
 * If a request is received then find the block number to which it belongs,
 * check the total count across all the blocks in the current window
 * if the total count is <= allowed count in the window then allow otherwise reject
 *
 *
 *
 */
import java.util.*;

public class RateLimitingBlocks {



    private Map<UUID, Block> userRequestLog = new HashMap<>();
    private int debugGlobalCount =0;

    private long rateLimitDurationInMillis = 60000;
    private int blockSizeMillis = 5000;
    private int noOfBlocksInWindow = 12;
    private int allowedRequests = 100;
    private long beginTime;

    static class Block {
        long blockNumber;
        long requestCount = 1l;

        public Block(long blockNumber) {
            this.blockNumber = blockNumber;
        }

        Block previous;
    }

    public RateLimitingBlocks(long rateLimitDurationInMillis, int blockSizeMillis, int allowedRequests) {
        this.rateLimitDurationInMillis = rateLimitDurationInMillis;
        this.blockSizeMillis = blockSizeMillis;
        this.allowedRequests = allowedRequests;

        noOfBlocksInWindow = (int) (rateLimitDurationInMillis/blockSizeMillis);

        beginTime =  System.currentTimeMillis();
    }

    public RateLimitingBlocks() {
        beginTime =  System.currentTimeMillis();

    }

    public synchronized void handleNewRequest(Request nr, UUID userId){
        nr.timestamp = System.currentTimeMillis();
        long currentSecond = System.currentTimeMillis();
        long block =  (currentSecond - beginTime)/blockSizeMillis;

        Block r = userRequestLog.get(userId);
        if(r == null) {
            userRequestLog.put(userId, new Block(block));
            return;
        }

        int blocksToIterate = r.blockNumber == block ? noOfBlocksInWindow : noOfBlocksInWindow-1;

        long totalCount = 1; // new request
        Block cb = r;

        for (int i = 1; i < blocksToIterate; i++) {
            if(cb == null) {
                break;
            }
            long depth = block - cb.blockNumber;
            if(depth >= blocksToIterate) {
                break;
            }

            totalCount = totalCount + cb.requestCount;
            nr.isRejected = totalCount >  allowedRequests;
            if(nr.isRejected){
                break;
            }
            cb = cb.previous;
        }

        if(!nr.isRejected ) {
            if(r.blockNumber == block) {
                r.requestCount++;
            }
            else {
                Block nb = new Block(block);
                nb.previous = userRequestLog.get(userId);
                userRequestLog.put(userId, nb);
            }
        }


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
        RateLimitingBlocks rl = new RateLimitingBlocks(10000l, 1000, 100);

        UUID userId = UUID.randomUUID();
        final List<Thread> threads = new ArrayList<>();
        final List<Request> allRequests =  new ArrayList<>();

        long start = System.currentTimeMillis();

        int noOfWorkers = 1;
        for (int j = 0; j < noOfWorkers; j++) {
            Thread t = new Thread(() -> {
                String name = Thread.currentThread().getName();
                for (int i = 0; ; i++) {
                    Request nr = new Request(1,  i, name);
                    allRequests.add(nr);
                    rl.handleNewRequest(nr, userId);
//                    System.out.println(name+": "+i);
                    sleep(75);
                    if((System.currentTimeMillis() - start ) > 11000) {
                        break;
                    }
                }
            }, "t"+j);
//            sleep(random.nextInt(10));
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
        RateLimitingBlocks rl = new RateLimitingBlocks();
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

    private static void postLoad(RateLimitingBlocks rl, UUID userId, List<Request> allRequests, long start) {
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


        Block r = rl.userRequestLog.get(userId);
        int count =0;
        while (r.previous != null) {
            count++;
            r = r.previous;
            System.out.println(r.blockNumber + " "+ r.requestCount);
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






}
