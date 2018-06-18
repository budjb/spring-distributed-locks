package com.budjb.spring.distributed.lock.reentrant;

import com.budjb.spring.distributed.lock.DistributedLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A non-distributed implementation of a distributed lock utilizing {@link ReentrantLock}.
 */
public class ReentrantDistributedLock implements DistributedLock {
    /**
     * Backing lock.
     */
    private final ReentrantLock lock;

    /**
     * Constructor.
     *
     * @param lock Reentrant lock instance.
     */
    public ReentrantDistributedLock(ReentrantLock lock) {
        this.lock = lock;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lock(long leaseTime, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryLock(long waitTime, TimeUnit waitTimeUnit, long leaseTime, TimeUnit leaseTimeUnit) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLocked() {
        return lock.isLocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsLeases() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lock() {
        lock.lock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        lock.lockInterruptibly();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryLock() {
        return lock.tryLock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return lock.tryLock(time, unit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlock() {
        lock.unlock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Condition newCondition() {
        return lock.newCondition();
    }
}
