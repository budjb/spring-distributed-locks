package com.budjb.spring.distributed.lock;

import com.budjb.spring.distributed.lock.reentrant.ReentrantDistributedLockProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DistributedLocksAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public DistributedLockProvider distributedLockProvider() {
        return new ReentrantDistributedLockProvider();
    }
}
