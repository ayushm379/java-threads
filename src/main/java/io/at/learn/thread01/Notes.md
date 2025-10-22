# Creating Thread

In Java, there are various ways of creating a Thread.

## Thread Class
By Extending java.lang.Thread class and overriding run() method, we can create a Thread instance.

### Use Case
1. Simple scenario with minimal design.

### Limitation
1. Java doesn't support multiple inheritance, so the class won't be able to extend any other class.
2. Not recommended for complex scenarios.

***

## Runnable Interface
By implementing Runnable interface and passing the object to Thread Object.

### Use Case
1. Allows class to extend some other class.
2. Useful when a task does not return any value.
3. Encourages better design and reusability.

### Runnable Lambda
Same as Runnable Interface, and is shorthand for that.

***

## Callable Interface
Represents a task that returns a result or throws an exception.

### Use Case
1. When the task returns a value

### vs Runnable
- Runnable run() method returns void
- Callable call() method returns a generic value and can throw exceptions

### Callable Lambda
Same as callable Interface, and is shorthand for that.

***

## Executor Service
ExecutorService takes (Runnable or Callable) to a thread pool and returns a Future object.
Types of Thread Pools:

#### Fixed Thread Pool
- Creates a thread pool with fixed number of threads.
- If a new threads are busy, tasks will wait in queue.
- Useful for CPU-bound tasks where limited concurrency is required.

#### Cached Thread Pool
- Creates a flexible pool that creates threads when needed.
- Reuses idle threads
- Best for short-lived, light weight async tasks and frequency of tasks is unknown.
- No limit on the number of threads

#### Single Thread Executor
- It uses only one thread so the tasks will run sequentially.
- Tasks are executed as FIFO.
- Replaces thread if it dies unexpectedly.

#### Scheduled Thread Pool
- Executed a task after a delay or periodically.
- It is like a timer
- Can repeat a task like heart beat signal

#### Single Thread Scheduler Executor
- Same as Scheduled Thread Pool, but uses single thread
- Ensures tasks executed in order and sequentially
- It is used for Scheduled tasks that must not run concurrently.

### Use Case
1. When a user wants to manage thread pool as well
2. Fine control over life cycle of thread

### Advantages
1. Supports Callable, Runnable and timeout
2. Centralize Thread management
3. Scales better than creating individual threads

***

## Fork Join Pool
Designed for a Divide and conquer task, which can be recursively broken down.
It has two types of tasks: 
- RecursiveTask (returns a value) 
- RecursiveAction (returns void)

### Use Case
1. If the task is recursive
2. CPU bound task that can benefit from Task Stealing.

### Advantages
1. Efficient for parallelism
2. Automatically manages task scheduling and stealing

***
