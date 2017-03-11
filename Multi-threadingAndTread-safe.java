/**Question:
Implement a thread-safe bounded blocking queue supporting multiple producers and consumers and atomic-multiput method ( where a list of objects will be put into the buffer in consecutive order in the presence of concurrent puts). The list being passed as multi-put argument can be bigger than the capacity of the buffer. Below is the interface to implement.*/
//Interface to implement
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
Follow-up question is to get the candidate talk about different locking strategies and how to improve concurrency.*/

//Solution:
//Implementation
public class MultiPutBlockingBoundedQueueImpl
  implements MultiPutBlockingBoundedQueue
{
 
  private boolean _isInitDone = false; // private + underscore
  private int _capacity = 0;
 
  private LinkedList _buffer = null;
  private int _currSize  = 0;
 
  private Lock _lock;
  private Lock _writerLock;
//java.util.concurrent.locks Interface Lock 

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
    _writerLock = new ReentrantLock(); 
//java.util.concurrent.locks Class ReentrantLock
    _lock = new ReentrantLock();
    _notEmpty = _lock.newCondition();
// Condition newCondition()
/**
Returns a new Condition instance that is bound to this Lock instance.
Before waiting on the condition the lock must be held by the current thread. A call to Condition.await() will atomically release the lock before waiting and re-acquire the lock before the wait returns.*/
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
      _lock.lock();
      {
        while ( _currSize <= 0 )
        {
          try
          {
            _notEmpty.await(); // predicate not satisfied, i.e. notEmpty then await()
          } catch ( InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      }
      _currSize--;
      _notFull.signal();
      return _buffer.remove(0);// list remove
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
        while ( _currSize >= _capacity)
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
