/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.budjb.spring.distributed.lock.redis;

import com.budjb.spring.distributed.lock.DistributedLock;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class RedisDistributedLock implements DistributedLock {
    private final RLock rLock;

    RedisDistributedLock(RLock rLock) {
        this.rLock = rLock;
    }

    @Override
    public void lock(long leaseTime, TimeUnit timeUnit) {
        rLock.lock(leaseTime, timeUnit);
    }

    @Override
    public boolean tryLock(long waitTime, TimeUnit waitTimeUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException {
        return rLock.tryLock(waitTime, leaseTimeUnit.convert(leaseTime, waitTimeUnit), waitTimeUnit);
    }

    @Override
    public boolean isLocked() {
        return rLock.isLocked();
    }

    @Override
    public boolean supportsLeases() {
        return true;
    }

    @Override
    public void lock() {
        rLock.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        rLock.lockInterruptibly();
    }

    @Override
    public boolean tryLock() {
        return rLock.tryLock();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return rLock.tryLock(time, unit);
    }

    @Override
    public void unlock() {
        rLock.unlock();
    }

    @Override
    public Condition newCondition() {
        return rLock.newCondition();
    }
}
