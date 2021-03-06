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

package com.budjb.spring.distributed.lock.hazelcast

import com.budjb.spring.distributed.lock.DistributedLock
import com.budjb.spring.distributed.lock.DistributedLockProvider
import com.hazelcast.core.HazelcastInstance
import spock.lang.Specification

class HazelcastDistributedLockProviderSpec extends Specification {
    def 'The HazelcastDistributedLockProvider proxies requests for a lock to a Hazelcast instance'() {
        setup:
        HazelcastInstance hazelcastInstance = Mock(HazelcastInstance)
        DistributedLockProvider lockProvider = new HazelcastDistributedLockProvider(hazelcastInstance)

        when:
        DistributedLock lock = lockProvider.getDistributedLock('foo')

        then:
        lock instanceof HazelcastDistributedLock
        1 * hazelcastInstance.getLock('foo')
    }
}
