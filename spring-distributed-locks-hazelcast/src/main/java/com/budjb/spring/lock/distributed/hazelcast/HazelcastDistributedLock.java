package com.budjb.spring.lock.distributed.hazelcast;

import com.budjb.spring.distributed.lock.DistributedLock;
import com.hazelcast.core.ILock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class HazelcastDistributedLock implements DistributedLock {
    /**
     * Underlying Hazelcast lock.
     */
    private final ILock lock;

    /**
     * Constructor.
     *
     * @param lock Underlying Hazelcast {@link ILock} implementation.
     */
    public HazelcastDistributedLock(ILock lock) {
        this.lock = lock;
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
    public void lock(long leaseTime, TimeUnit timeUnit) {
        lock.lock(leaseTime, timeUnit);
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
    public boolean tryLock(long waitTime, TimeUnit waitTimeUnit) throws InterruptedException {
        return lock.tryLock(waitTime, waitTimeUnit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryLock(long waitTime, TimeUnit waitTimeUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException {
        return lock.tryLock(waitTime, waitTimeUnit, leaseTime, leaseTimeUnit);
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
    public boolean isLocked() {
        return lock.isLocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Condition newCondition() {
        return lock.newCondition();
    }
}
