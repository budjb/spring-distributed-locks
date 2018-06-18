package com.budjb.spring.distributed.lock.reentrant;

import com.budjb.spring.distributed.lock.DistributedLock;
import com.budjb.spring.distributed.lock.DistributedLockProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A non-distributed implementation of a lock provider utilizing {@link ReentrantLock}.
 */
public class ReentrantDistributedLockProvider implements DistributedLockProvider {
    /**
     * Registry of lock names to reentrant lock instances.
     */
    final private Map<String, ReentrantLock> registry = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public DistributedLock getDistributedLock(String key) {
        if (!registry.containsKey(key)) {
            registry.put(key, new ReentrantLock());
        }
        return new ReentrantDistributedLock(registry.get(key));
    }
}
