package com.budjb.spring.lock.distributed.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastDistributedLockAutoConfiguration {
    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public HazelcastDistributedLockProvider distributedLockProvider(HazelcastInstance hazelcastInstance) {
        return new HazelcastDistributedLockProvider(hazelcastInstance);
    }
}
