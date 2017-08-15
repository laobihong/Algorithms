// A very good and concise example from the Java docs on the Condition class
class BoundedBuffer {
   final Lock lock = new ReentrantLock();
   final Condition notFull  = lock.newCondition(); 
   final Condition notEmpty = lock.newCondition(); 

   final Object[] items = new Object[100];
   int putptr, takeptr, count;

   public void put(Object x) throws InterruptedException {
     lock.lock();
     try {
       while (count == items.length)
         notFull.await();
       items[putptr] = x;
       if (++putptr == items.length) putptr = 0;
       ++count;
       notEmpty.signal();
     } finally {
       lock.unlock();
     }
   }

   public Object take() throws InterruptedException {
     lock.lock();
     try {
       while (count == 0)
         notEmpty.await();
       Object x = items[takeptr];
       if (++takeptr == items.length) takeptr = 0;
       --count;
       notFull.signal();
       return x;
     } finally {
       lock.unlock();
     }
   }
 }

// Interview sample question on multi-threading and thread safe
// Also can serve as producer - consumer pattern
// - verbose: contains comments from CS 4410 and Java docs
// KEY: while (NOT predicate) :
//          {
//               ......
//               condition.await();
//               ......
//          }
/**
Question:
Implement a thread-safe bounded blocking queue supporting multiple producers and consumers and atomic-multiput method ( where a list of objects will be put into the buffer in consecutive order in the presence of concurrent puts). The list being passed as multi-put argument can be bigger than the capacity of the buffer. Below is the interface to implement.
Interface to implement
*/

/*
 *   threadSafe bounded blocking queue implementation.
 *   Expected to be used in a Producer->Consumer pattern
 */
public interface MultiPutBlockingBoundedQueue
{
  /*
   * Sets the capacity of the buffer. Can be called only once.
   * If called more than once or if the passed capacity is <= 0,
   * throw an exception.
   */
  public void init(int capacity) throws Exception;
 
  /*
   * Get the next item from the queue
   * throws Exception if not initialized
   */
  public Object get() throws Exception;
 
  /*
   * Put the item to the tail of the queue.
   * throws Exception if not initialized
   */
  public void put(Object obj) throws Exception;
 
  /*
   * Put the list of items in an atomic manner.
   * The list can be more than the capacity
   * throws Exception if not initialized
   */
  public void multiput(List objs) throws Exception;
}

/**
Guidelines and Follow-ups
Start with single-put/get implementation. Check for thread-safety and correctness initially. if satisfied, ask the interviewee to implement multi-put.
Follow-up question is to get the candidate talk about different locking strategies and how to improve concurrency.
Solution:
We also have a solution in C++.
Here is a sample implementation of the Java interface:
Implementation
*/

public class MultiPutBlockingBoundedQueueImpl
  implements MultiPutBlockingBoundedQueue
{
 
  private boolean _isInitDone = false; // private properties: starts with an underscore
  private int _capacity = 0;
 
  private LinkedList _buffer = null;
  private int _currSize  = 0;
 
  private Lock _lock;
  private Lock _writerLock;
// but why two locks here? e.g. the example in java docs for Condition class is using only one
// java.util.concurrent.locks Interface Lock 

  private Condition _notEmpty;
  private Condition _notFull;
 
 
  @Override
  public void init(int capacity) throws Exception
  {
    if ( _isInitDone)
      throw new Exception("Already inited !!");
 
    if ( capacity <= 0 )
      throw new Exception("Capacity <= 0 ");
 
    _capacity = capacity;
    _buffer = new LinkedList();
    _currSize = 0;
    _writerLock = new ReentrantLock();  //java.util.concurrent.locks Class ReentrantLock
    _lock = new ReentrantLock();
    _notEmpty = _lock.newCondition(); 
/* Condition newCondition()
Returns a new Condition instance that is bound to this Lock instance.
Before waiting on the condition the lock must be held by the current thread. 
A call to Condition.await() will atomically release the lock before waiting and re-acquire 
the lock before the wait returns. */
    _notFull = _lock.newCondition(); 
  }
 
  private void assertInited()
    throws Exception
  {
    if ( ! _isInitDone)
      throw new Exception("Init not done !!");
  }
 
  @Override
  public Object get()
    throws Exception
  {
    assertInited();
    try
    {
      _lock.lock(); // If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until the lock has been acquired.
// Because access to this shared state information occurs in different threads, it must be protected, so a lock of some form is associated with the condition.
      {
        while ( _currSize <= 0 ) // predicate: _currSize > 0, also pretty straightforward
        {
          try
          {
            _notEmpty.await(); // not predicate, i.e. only when notEmpty we call await()
// A call to Condition.await() will atomically release the lock before waiting and re-acquire the lock before the wait returns.
//The key property that waiting for a condition provides is that it atomically releases the associated lock and suspends the current thread, just like Object.wait.
          } catch ( InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      }
      _currSize--;
      _notFull.signal(); // Conditions (also known as condition queues or condition variables) provide a means for one thread to suspend execution (to "wait") until notified by another thread that some state condition may now be true.
      return _buffer.remove(0); // the list library function remove 
    } catch (Exception ex) {
      throw ex;
    } finally {
      _lock.unlock();
    }
  }
 
  @Override
  public void put(Object obj)
    throws Exception
  {
    assertInited();
    try
    {
      _writerLock.lock();
      _lock.lock();
      {
        while ( _currSize >= _capacity)
        {
          try
          {
            _notFull.await();
          } catch ( InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      }
      _currSize++;
      _buffer.add(obj);
      _notEmpty.signal();
    } catch (Exception ex) {
      throw ex;
    } finally {
      _lock.unlock();
      _writerLock.unlock();
    }
  }
 
  @Override
  public void multiput(List objs)
    throws Exception
  {
    assertInited();
    try
    {
      _writerLock.lock();
      _lock.lock();
 
      for ( Object obj : objs)
      {
        while ( _currSize >= _capacity) // condition: _currSize < _capacity, pretty straightforward huh?
        {
          try
          {
            _notFull.await();
          } catch ( InterruptedException ex) {
            ex.printStackTrace();
          }
        }
        _buffer.add(obj);
        _currSize++;
        _notEmpty.signal();
      }
    } catch (Exception ex) {
      throw ex;
    } finally {
      _lock.unlock();
      _writerLock.unlock();
    }
  }
 
}
