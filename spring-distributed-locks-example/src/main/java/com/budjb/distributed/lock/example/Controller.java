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
