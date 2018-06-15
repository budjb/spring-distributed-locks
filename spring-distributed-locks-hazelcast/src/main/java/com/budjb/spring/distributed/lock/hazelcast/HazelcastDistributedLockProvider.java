package com.budjb.spring.distributed.lock.hazelcast;

import com.budjb.spring.distributed.lock.DistributedLock;
import com.budjb.spring.distributed.lock.DistributedLockProvider;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastDistributedLockProvider implements DistributedLockProvider {
    /**
     * Hazelcast instance.
     */
    private final HazelcastInstance hazelcastInstance;

    /**
     * Constructor.
     *
     * @param hazelcastInstance Hazelcast instance that backs the distributed locks.
     */
    public HazelcastDistributedLockProvider(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DistributedLock getDistributedLock(String key) {
        return new HazelcastDistributedLock(hazelcastInstance.getLock(key));
    }
}
