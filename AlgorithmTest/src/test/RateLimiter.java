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
        RateLimiter rl = new RateLimiter(50, TimeUnit.MINUTES);  // TimeUnit.MINUTES returns MINUTES
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

/**
* Modified:import java.util.concurrent.locks.*;
import java.util.concurrent.*;
import java.util.*;



public class Solution{
  
  public static void main(String[] args) throws Exception{
    
    RateLimiter r= new RateLimiter();
    System.out.println(r.checkRateLimiter("a", 0, 3, 60));
    System.out.println(r.checkRateLimiter("a", 10, 3, 60));
    System.out.println(r.checkRateLimiter("a", 20, 3, 60));
    System.out.println(r.checkRateLimiter("a", 58, 3, 60));
    System.out.println(r.checkRateLimiter("a", 61, 3, 60));
    System.out.println(r.checkRateLimiter("b", 18, 3, 60));
    System.out.println(r.checkRateLimiter("b", 18, 3, 60));
    System.out.println(r.checkRateLimiter("b", 18, 3, 60));
    System.out.println(r.checkRateLimiter("b", 58, 3, 60));
    System.out.println(r.checkRateLimiter("c", 58, 3, 60));
    
    
    TokenBucketRateLimiter t = new TokenBucketRateLimiter(240, 60);
    Thread.sleep(3000L);
    for(int i = 0; i < 14; i++){
      System.out.println(t.take());
    } 
  }
  
}

class RateLimiter{
  Map<String, Queue<Integer>> map = new HashMap<>();
  
  public boolean checkRateLimiter(String client, int timestamp, int maxSize, int timeunit) {
    
    if(!map.containsKey(client)) {
      map.put(client, new LinkedList<Integer>());
    }
    Queue<Integer> cur = map.get(client);
    if(cur.size() < maxSize){
      cur.add(timestamp);
      System.err.println("Client:"+client+" get resource!");
      return true;
    }
    //queue.size() == maxSize;
    if(cur.peek() + timeunit >= timestamp){
      System.err.println("Client:"+client+" can not get resource, request too fast!");
      return false;
    } else {
      cur.poll();
      cur.offer(timestamp);
      System.err.println("Client:"+client+" get resource!");
      return true;
    }
    
  }
  
}


class TokenBucketRateLimiter {
  int capacity;
  int tokenPerSecond;
  long prevtime;
  int tokens;
  
  public TokenBucketRateLimiter(int tokenPerUnit, int unitToSecond) {
    this.tokenPerSecond = tokenPerUnit/unitToSecond;
    prevtime = System.currentTimeMillis();
    capacity = tokenPerUnit;
    tokens = capacity;
  }
  
  public boolean take(){
    long now = System.currentTimeMillis();
    tokens += (now - prevtime) /1000 * tokenPerSecond;
    if(tokens > capacity) {
      tokens = capacity;
    }
    if(tokens < 1) {
      System.out.println("Can not get the resource!");
      return false;
    }
    prevtime = now;
    tokens--;
    System.out.println("Get the resource!");
    return true;
  }

}


class Solution_XiaoMu {

    private final double capacity;
    private final double tokensPerSecond;
    private int tokens = 0;
    private long timestamp = System.currentTimeMillis();
    private Lock lock;
    
    public Solution_XiaoMu(double tokensPerUnit, int seconds) {
        tokensPerSecond = (tokensPerUnit / seconds); // MINUTES.toSeconds(1L) returns 60. 1L represents 60 seconds
        lock = new ReentrantLock();
        capacity = tokensPerSecond;
    }
    
    public boolean take(String name) {
        try {
            lock.lock();
            System.out.println("Thread " + name + " successfully acquired the lock");
            long now = System.currentTimeMillis();
            // the tokens that should be available during the past time interval
            tokens += (int) ((now - timestamp) / 1000 * tokensPerSecond);
            // maximum available tokens cannot exceed capacity
            if (tokens > capacity) {
                tokens = (int)capacity;
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
            lock.unlock();
        }
    }
    
    public static void main(String[] args) throws InterruptedException{
        Solution_XiaoMu rl = new Solution_XiaoMu(250, 3600);  // TimeUnit.MINUTES returns MINUTES
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
    Solution_XiaoMu rl;
    ClientThread(String name, Solution_XiaoMu rl){
        this.name = name;
        this.rl = rl;
    }
    
    public void run() {
        System.out.println("Thread " + this.name + " started asking for resources");
        this.rl.take(this.name);
        System.out.println("Thread " + this.name + " finished asking for resources");
    }
}
* 
*/