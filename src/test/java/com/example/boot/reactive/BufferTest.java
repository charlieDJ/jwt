package com.example.boot.reactive;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class BufferTest {


    @Test
    public void buffer() {
        Flux<String> flux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<List<String>> bufferedFlux = flux.buffer(3);
        bufferedFlux.subscribe(System.out::println);
    }

    @Test
    public void flatMap() throws InterruptedException {
        Flux.just("apple", "orange", "banana", "kiwi", "strawberry")
                .buffer(3)
                .flatMap(x -> Flux.fromIterable(x)
                        .map(String::toUpperCase)
                        .subscribeOn(Schedulers.parallel())
                        .log())
                .subscribe();
        Thread.sleep(5000);
    }
}
