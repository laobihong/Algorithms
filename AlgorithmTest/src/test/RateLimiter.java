package test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * references:
 * http://blog.gssxgss.me/not-a-simple-problem-rate-limiting/
 * https://www.mkyong.com/java/java-thread-mutex-and-semaphore-example/
 * https://github.com/google/guava/blob/master/guava/src/com/google/common/util/concurrent/RateLimiter.java
 * @author x
 *
 */
public class RateLimiter {

    private final int capacity;
    private final int tokensPerSecond;
    private int tokens = 0;
    private long timestamp = System.currentTimeMillis();
    private Semaphore mutex;
    
    public RateLimiter(int tokensPerUnit, TimeUnit unit) {
        capacity = tokensPerSecond = (int) (tokensPerUnit / unit.toSeconds(1L)); // MINUTES.toSeconds(1L) returns 60. 1L represents 60 seconds
        mutex = new Semaphore(1); // only 1 permit, so works as a mutex
    }
    
    public boolean take(String name) {
        try {
            mutex.acquire();
            System.out.println("Thread " + name + " successfully acquired the lock");
            long now = System.currentTimeMillis();
            // the tokens that should be available during the past time interval
            tokens += (int) ((now - timestamp) / 1000 * tokensPerSecond);
            // maximum available tokens cannot exceed capacity
            if (tokens > capacity) {
                tokens = capacity;
            }
            
            // IMPORTANT: update current time!
            timestamp = now;
            
            // time not long enough, no token available, so cannot take
            if (tokens < 1) {
                System.out.println("===Error: However thread " + name + " failed to obtain resources due to too frequent requests!===");
                return false;
            }
            // take the token
            tokens--;
            System.out.println("===Thread " + name + " successfully passed the rate limiter and got the resource!===");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("Thread " + name + " released the lock");
            mutex.release();
        }
    }
    
    public static void main(String[] args) throws InterruptedException{
        RateLimiter rl = new RateLimiter(250, TimeUnit.MINUTES);  // TimeUnit.MINUTES returns MINUTES
        // elapse 1 second so there are 4 resources available
        // then the expected result is that among the 10 threads, 4 will succeed and the other 6 will fail
        Thread.sleep(1000L);
        for (int i = 0; i < 10; i++) {
            ClientThread ct = new ClientThread(Integer.toString(i), rl);
            ct.start();
        }
    }

}

class ClientThread extends Thread {
    String name;
    RateLimiter rl;
    ClientThread(String name, RateLimiter rl){
        this.name = name;
        this.rl = rl;
    }
    
    public void run() {
        System.out.println("Thread " + this.name + " started asking for resources");
        this.rl.take(this.name);
        System.out.println("Thread " + this.name + " finished asking for resources");
    }
}