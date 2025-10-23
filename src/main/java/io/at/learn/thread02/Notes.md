# Thread Lifecycle

## New
When a new thread is created, but not yet executed.

## Runnable/Running
When thread is ready to run and is either running or waiting for CPU to allocate time.

## Blocked
When thread is trying to acquire a monitor lock, but can't proceed since another lock is holding the lock.

## Waiting
When thread calls join() method, it moves to waiting state, waiting for the other thread to finish.

## Time Waiting
When thread waits for IO or the wait() method is called.

## Terminated
When thread has finished execution, it can't be started again.


