package org.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;
import org.java.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicReferenceTest {

    public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("zpp", 15);
        atomicUserRef.set(user);
        User updateUser = new User("lll", 17);
        atomicUserRef.compareAndSet(user, updateUser);
        log.info("新的名字:{}",atomicUserRef.get().getName());
        log.info("新的old:{}",atomicUserRef.get().getOld());
    }

    static class User {
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}