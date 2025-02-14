package com.example.webflux.WebFlux_demo;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@SpringBootTest
class WebfluxApplicationTests {

	@Test
	void AgainTest() {
		final Mono<String> userMono = Mono.just("User123");

		final Flux<String> stringFlux = userMono.flatMapMany(
				id -> Flux.fromIterable(List.of("o1", "o2", "o3")));

		stringFlux.subscribe(System.out::println);

	}

	@Test
	void Test(){
		UserService userService = new UserService();

		Mono<Integer> userNameMono = Mono.just(200);

		Mono<String> mappedMono = userNameMono.map(id -> "Student id -> " + id);
		mappedMono.subscribe(data -> System.out.println("Modified User: " + data));

		Mono<Student> studentMono = userNameMono.flatMap(id -> userService.getService(id));

		studentMono.subscribe(data -> System.out.println("User name: " + data.getName().toUpperCase()));

		final Mono<String> flatManyExample = Mono.just("This is Mehedi");

		final Flux<String> stringFlux = flatManyExample.flatMapMany(
				value -> Flux.just(value.split(" "))).log();

		System.out.println("---------------------");
		stringFlux.subscribe(data -> System.out.println("Modified String: " + data));

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}


