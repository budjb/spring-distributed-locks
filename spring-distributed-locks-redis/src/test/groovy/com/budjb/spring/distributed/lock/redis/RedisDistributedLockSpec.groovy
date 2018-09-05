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

package com.budjb.spring.distributed.lock.redis


import org.redisson.api.RLock
import spock.lang.Specification

import java.util.concurrent.TimeUnit

class RedisDistributedLockSpec extends Specification {
    def 'Redis locks support leases'() {
        setup:
        RLock lock = Mock(RLock)

        expect:
        new RedisDistributedLock(lock).supportsLeases()
    }

    def 'Method calls are proxied to the underlying lock implementation'() {
        setup:
        RLock rLock = Mock(RLock)
        RedisDistributedLock lock = new RedisDistributedLock(rLock)

        when:
        lock.isLocked()
        lock.lock()
        lock.lockInterruptibly()
        lock.tryLock()
        lock.tryLock(1, TimeUnit.MICROSECONDS)
        lock.unlock()
        lock.newCondition()
        lock.tryLock(1, TimeUnit.MICROSECONDS, 1, TimeUnit.MICROSECONDS)
        lock.lock(1, TimeUnit.MICROSECONDS)

        then:
        1 * rLock.isLocked()
        1 * rLock.lock()
        1 * rLock.lockInterruptibly()
        1 * rLock.tryLock()
        1 * rLock.tryLock(1, TimeUnit.MICROSECONDS)
        1 * rLock.unlock()
        1 * rLock.newCondition()
        1 * rLock.tryLock(1, 1, TimeUnit.MICROSECONDS)
        1 * rLock.lock(1, TimeUnit.MICROSECONDS)

    }
}
