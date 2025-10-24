# Future

### Future
- Represents a result of async computation
- On calling get method, the main thread gets blocked till result is available.
- Cannot chain or compose tasks.

### Completable Future
- Extension to Future, introduced in Java 8
- Non-blocking callbacks
- Chaining multiple async task is possible
- Combining multiple futures is possible
- Has exception handling

### Atomic Variables
These variables provide safety of data with multiple threads without the synchronized keyword
They ensure lock-free thread safety.
This is faster than using the synchronized block for simple atomic updates.

- AtomicInteger
- AtomicLong
- AtomicBoolean
- AtomicReference<V>

It stores the data in volatile memory and uses Compare And Swap to auto update the value
It is implemented with the Unsafe class for native hardware instructions.


### Concurrent Collections
- Collections.synchronizedList(new ArrayList<>());
- ConcurrentHashMap
- CopyOnWriteArrayList
- CopyOnWriteArraySet
- ConcurrentLinkedQueue
- BlockingQueue
  - ArrayBlockingQueue
  - LinkedBlockingQueue
  - PriorityBlockingQueue
  - DelayQueue
- ConcurrentSkipListMap