package com.example.demo;

import java.util.concurrent.atomic.AtomicReference;

public class AutomicThread {
    AtomicReference<Thread> ar = new AtomicReference<>();

    public void myLock() {
        Thread t = Thread.currentThread();

        while (!ar.compareAndSet(null, t)) {

        }
    }

    public void myUnLock() {
        Thread t = Thread.currentThread();
        ar.compareAndSet(t, null);

    }

    public static void main(String[] args) {
        AutomicThread at = new AutomicThread();
        new Thread(() -> {
            try {
                at.myLock();

            } finally {
                at.myUnLock();
            }

        });


    }

}
