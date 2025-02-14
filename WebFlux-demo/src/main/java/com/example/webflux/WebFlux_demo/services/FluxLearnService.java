package com.example.webflux.WebFlux_demo.services;

import java.time.Duration;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxLearnService {

  public void fluxTestingService(){
    System.out.println("This is from Flux testing service...");
  }

//  All operator examples

//  getting flux from passing objects as parameters to just
  public Flux<String> getFlux(){
    return Flux.just("Hello", "World", "From","Mehedi").log();
  }

  //getting flux from a list
  public Flux<String> getFluxFromList(){
    List<String> list = List.of("Hello", "World", "From","Mehedi");

    return Flux.fromIterable(list).log();
  }

  //map example
  public Flux<String> mapFluxExample(){
    return getFlux().map(String::toUpperCase);
  }

  //filter flux example
  public Flux<String> filterFluxExample(){
    return getFluxFromList().filter(name -> name.length() > 4);
  }

//  flatmap example
  public Flux<String> flatmapFluxExample(){
    return getFlux().flatMap(name -> Flux.just(name.split("")).delayElements(Duration.ofSeconds(2))).log();
  }

  public Flux<Integer> getDataFromList(){
    List<Integer> list = List.of(1, 2, 3, 4, 5);
    Flux<Integer> integerFlux = Flux.fromIterable(list);

    return integerFlux;

  }

  public Flux<String> getUppercasedData(){
    List<String> list = List.of("Alice", "Bob", "Charlie");
    return Flux.fromIterable(list).map(String::toUpperCase);
  }

  public Flux<Integer> getOddNumber(){
    List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,0);
   return Flux.fromIterable(list).filter(item -> item % 2 == 1).delayElements(Duration.ofSeconds(2));
  }

  public Mono<Object> getEmptyValue(){
    return Mono.empty().defaultIfEmpty("This is Default empty");
  }

  public Flux<String> combineTwoFlux(){
    final Flux<String> just = Flux.just("A", "B", "C");
    final Flux<String> just1 = Flux.just("1", "2", "3");

    return Flux.zip(just, just1, (j1, j2) -> j1+j2);
  }
  
  public Flux<Integer> multiplyBy2(){
    List<Integer> list = List.of(1,2,3,4);
    return Flux.fromIterable(list).flatMap(item -> Mono.just(item*2));
  }

  public Flux<Integer> mergeFluxes(){
    List<Integer> list1 = List.of(1,5,6,8);
    List<Integer> list2 = List.of(2,3,4,5);

    final Flux<Integer> integerFlux = Flux.fromIterable(list1);
    final Flux<Integer> integerFlux1 = Flux.fromIterable(list2);

    return Flux.merge(integerFlux, integerFlux1).sort();
  }
}
