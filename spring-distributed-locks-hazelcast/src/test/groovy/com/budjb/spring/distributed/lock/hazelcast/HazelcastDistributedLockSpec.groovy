package com.budjb.spring.distributed.lock.hazelcast

import com.hazelcast.core.ILock
import spock.lang.Specification

import java.util.concurrent.TimeUnit

class HazelcastDistributedLockSpec extends Specification {
    def 'Hazelcast locks support leases'() {
        setup:
        ILock lock = Mock(ILock)
        expect:
        new HazelcastDistributedLock(lock).supportsLeases()
    }

    def 'Method calls are proxied to the underlying lock implementation'() {
        setup:
        ILock hazelcastLock = Mock(ILock)
        HazelcastDistributedLock lock = new HazelcastDistributedLock(hazelcastLock)

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
        1 * hazelcastLock.isLocked()
        1 * hazelcastLock.lock()
        1 * hazelcastLock.lockInterruptibly()
        1 * hazelcastLock.tryLock()
        1 * hazelcastLock.tryLock(1, TimeUnit.MICROSECONDS)
        1 * hazelcastLock.unlock()
        1 * hazelcastLock.newCondition()
        1 * hazelcastLock.tryLock(1, TimeUnit.MICROSECONDS, 1, TimeUnit.MICROSECONDS)
        1 * hazelcastLock.lock(1, TimeUnit.MICROSECONDS)

    }
}
