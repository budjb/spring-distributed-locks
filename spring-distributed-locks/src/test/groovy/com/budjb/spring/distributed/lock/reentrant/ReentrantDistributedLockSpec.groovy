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

package com.budjb.spring.distributed.lock.reentrant

import spock.lang.Specification

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

class ReentrantDistributedLockSpec extends Specification {
    def 'Reentrant locks do not support leases'() {
        expect:
        !new ReentrantDistributedLock(new ReentrantLock()).supportsLeases()
    }

    def 'Lease-type lock methods throw UnsupportedOperationExceptions'() {
        setup:
        ReentrantDistributedLock lock = new ReentrantDistributedLock(new ReentrantLock())

        when:
        lock.tryLock(1, TimeUnit.MICROSECONDS, 1, TimeUnit.MICROSECONDS)

        then:
        thrown UnsupportedOperationException

        when:
        lock.lock(1, TimeUnit.MICROSECONDS)

        then:
        thrown UnsupportedOperationException
    }

    def 'Method calls are proxied to the underlying lock implementation'() {
        setup:
        ReentrantLock reentrantLock = Mock(ReentrantLock)
        ReentrantDistributedLock lock = new ReentrantDistributedLock(reentrantLock)

        when:
        lock.isLocked()
        lock.lock()
        lock.lockInterruptibly()
        lock.tryLock()
        lock.tryLock(1, TimeUnit.MICROSECONDS)
        lock.unlock()
        lock.newCondition()

        then:
        1 * reentrantLock.isLocked()
        1 * reentrantLock.lock()
        1 * reentrantLock.lockInterruptibly()
        1 * reentrantLock.tryLock()
        1 * reentrantLock.tryLock(1, TimeUnit.MICROSECONDS)
        1 * reentrantLock.unlock()
        1 * reentrantLock.newCondition()

    }
}
