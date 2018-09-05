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

import com.budjb.spring.distributed.lock.DistributedLock
import com.budjb.spring.distributed.lock.DistributedLockProvider
import org.redisson.api.RedissonClient
import spock.lang.Specification

class RedisDistributedLockProviderSpec extends Specification {
    def 'The RedisDistributedLockProvider proxies requests for a lock to a Redis instance'() {
        setup:
        RedissonClient redissonClient = Mock(RedissonClient)
        DistributedLockProvider lockProvider = new RedisDistributedLockProvider(redissonClient)

        when:
        DistributedLock lock = lockProvider.getDistributedLock('foo')

        then:
        lock instanceof RedisDistributedLock
        1 * redissonClient.getLock('foo')
    }
}
