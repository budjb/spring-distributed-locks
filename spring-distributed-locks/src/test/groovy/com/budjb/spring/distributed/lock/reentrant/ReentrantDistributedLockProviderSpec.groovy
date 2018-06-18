package com.budjb.spring.distributed.lock.reentrant

import spock.lang.Specification

class ReentrantDistributedLockProviderSpec extends Specification {
    def 'The ReentrantDistributedLockProvider returns instances of ReentrantDistributedLock'() {
        setup:
        ReentrantDistributedLockProvider provider = new ReentrantDistributedLockProvider()

        expect:
        provider.getDistributedLock('foo') instanceof ReentrantDistributedLock
    }
}
