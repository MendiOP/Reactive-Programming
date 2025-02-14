package com.example.webflux.WebFlux_demo;

import java.time.Duration;
import reactor.core.publisher.Mono;

public class UserService {

  public Mono<Student> getService(int id){
    return Mono.just(new Student("mehedi", 25, "Dhanomondi, Dhaka", id))
        .delayElement(Duration.ofMillis(2000));
  }
}
