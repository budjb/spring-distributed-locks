package com.budjb.spring.distributed.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public interface DistributedLock extends Lock {
    /**
     * Attempts to acquire a lock with and a lock timeout iff the lock is available.
     *
     * @param leaseTime The amount of time to wait for the lock to become available.
     * @param timeUnit  The time unit of waitTime.
     * @throws InterruptedException if the current thread is
     *                              interrupted while acquiring the lock (and interruption
     *                              of lock acquisition is supported)
     */
    void lock(long leaseTime, TimeUnit timeUnit) throws InterruptedException;

    /**
     * Attempts to acquire a lock with a wait timeout and a lock timeout.
     * <p>
     * If the lock becomes available within the wait timeout, the lock will be acquired and the method
     * will return {@code true}. If the wait timeout is reached, the lock is not acquired.
     *
     * @param waitTime      The amount of time to wait for the lock to become available.
     * @param waitTimeUnit  The time unit of waitTime.
     * @param leaseTime     The amount of time that lock should be considered valid.
     * @param leaseTimeUnit The time unit of lockTime.
     * @return Whether the lock was acquired.
     * @throws InterruptedException if the current thread is
     *                              interrupted while acquiring the lock (and interruption
     *                              of lock acquisition is supported)
     */
    boolean tryLock(long waitTime, TimeUnit waitTimeUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException;

    /**
     * Returns whether the lock instance believes is currently owns the lock.
     *
     * @return Whether the lock instance believes is currently owns the lock.
     */
    boolean isLocked();
}
