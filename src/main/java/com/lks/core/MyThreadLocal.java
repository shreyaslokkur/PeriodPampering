package com.lks.core;

/**
 * Created with IntelliJ IDEA.
 * User: shreyaslokkur
 */
public class MyThreadLocal {

    public static final ThreadLocal userThreadLocal = new ThreadLocal();

    public static void set(Context context) {
        userThreadLocal.set(context);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static Context get() {
        return (Context) userThreadLocal.get();
    }
}
