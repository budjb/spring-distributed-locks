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
