package com.budjb.distributed.lock.example;

import com.budjb.spring.distributed.lock.DistributedLockProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;

@RestController
public class Controller {
    private final DistributedLockProvider distributedLockProvider;

    public Controller(DistributedLockProvider distributedLockProvider) {
        this.distributedLockProvider = distributedLockProvider;
    }

    @RequestMapping("/")
    public String get() {
        Lock lock = distributedLockProvider.getDistributedLock("foo");

        if (lock.tryLock()) {
            try {
                return "Lock acquired.";
            }
            finally {
                lock.unlock();
            }
        }
        else {
            return "Could not acquire lock.";
        }
    }
}
