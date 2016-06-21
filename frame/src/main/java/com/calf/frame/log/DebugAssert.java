package com.calf.frame.log;

import android.os.Looper;

/**
 * @author JinYi Liu
 */
public final class DebugAssert {

    private static boolean mDebug = false;

    public static void setDebug(boolean debug) {
        DebugAssert.mDebug = debug;
    }

    public static void classAssert(boolean flag, String message) {
        if (mDebug && !flag) {
            throw new AssertException(message);
        }
    }

    public static void mustMainThread() {
        classAssert(isMainThread(), "must call in main thread");
    }

    public static void mustSubThread() {
        classAssert(!isMainThread(), "must call in sub thread");
    }

    public static void mustInstance(Object obj, Class<?> t) {
        classAssert(t.isInstance(obj), String.format("%s not instance of %s", obj.getClass().getSimpleName(), t.getSimpleName()));
    }

    private static boolean isMainThread() {
        long currentId = Thread.currentThread().getId();
        long mainId = Looper.getMainLooper().getThread().getId();
        return currentId == mainId;
    }

    private static class AssertException extends RuntimeException {

        private AssertException(String message) {
            super(message);
        }

//        private AssertException(Throwable throwable) {
//            super(throwable);
//        }
//
//        private AssertException(String message, Throwable throwable) {
//            super(message, throwable);
//        }

    }

}
