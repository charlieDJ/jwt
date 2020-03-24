package com.example.boot.common.util;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Throwable> {

    void accept() throws E;

    static <T, E extends Throwable> Consumer<T> wrap(ThrowingConsumer<T, E> c) {
        return t -> {
            try {
                c.accept();
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

}
