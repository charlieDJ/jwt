package com.example.boot.reactive;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateTest {

    @Test
    public void createFluxByJust() {
        Flux<String> flux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
        flux.subscribe(System.out::println);
    }

    @Test
    public void fromList() {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Orange");
        list.add("Grape");
        list.add("Banana");
        list.add("Strawberry");
        Flux.fromIterable(list)
                .subscribe(System.out::println);
    }

    @Test
    public void range() {
        Flux<Integer> flux = Flux.range(1, 5);
        flux.subscribe(System.out::println);
    }

    @Test
    public void interval() throws InterruptedException {
        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
                .take(5);
        flux.subscribe(System.out::println);
        Thread.sleep(10000);
    }

}
