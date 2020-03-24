package com.example.boot.common.util;

@FunctionalInterface
public interface ThrowingSupplier<T, E extends Throwable> {

    T get() throws E;

    static <T, E extends Throwable> T wrap(ThrowingSupplier<T, E> s) {
        try {
            return s.get();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

}
