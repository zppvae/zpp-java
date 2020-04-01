package org.java.concurrency.immutable;

/**
 * final的方法
 */
public class FinalMethodDemo {

    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }
}

class SubClass extends FinalMethodDemo {

    @Override
    public void drink() {
        super.drink();
        eat();
    }

//    public final void eat() {
//
//    }
    public static void sleep() {

    }
}