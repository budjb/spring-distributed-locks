package com.budjb.spring.distributed.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * A base implementation of a {@link DistributedLock} useful for implementation which merely
 * wrap an existing {@link Lock}.
 * <p>
 * The methods {@link #tryLock(long, TimeUnit)}, {@link #lock(long, TimeUnit)},
 * {@link #isLocked()}, and {@link #supportsLeases()} are not part of the base {@link Lock} interface, and will
 * throw {@link UnsupportedOperationException} when called (exception for {@code supportsLeases()}, which
 * returns false). Implementations that wish to use these methods may simply override them.
 */
public class AbstractDistributedLock implements DistributedLock {
    /**
     * Underlying lock this class is proxying.
     */
    private final Lock lock;

    /**
     * Constructor.
     *
     * @param lock Lock this class will proxy.
     */
    public AbstractDistributedLock(Lock lock) {
        this.lock = lock;
    }

    /**
     * Returns the underlying lock instance.
     */
    protected Lock getLock() {
        return lock;
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
    public boolean tryLock(long waitTime, TimeUnit waitTimeUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLocked() {
        throw new UnsupportedOperationException();
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
