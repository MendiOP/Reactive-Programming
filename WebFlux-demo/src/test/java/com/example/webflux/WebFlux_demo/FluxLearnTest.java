package com.example.webflux.WebFlux_demo;

import com.example.webflux.WebFlux_demo.services.FluxLearnService;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifier.Step;
import reactor.util.function.Tuple3;

@SpringBootTest
public class FluxLearnTest {

  @Autowired
  private FluxLearnService fluxLearnService;

  @Test
  public void testing(){
    fluxLearnService.fluxTestingService();
  }

  @Test
  public void simpleFluxTest(){
//    fluxLearnService.getFlux().subscribe(data->{
//      System.out.println(data);
//      System.out.println("Done with data...");
//    });

    fluxLearnService.getFluxFromList().subscribe(data->{
      System.out.println(data);
      System.out.println("Done with Flux datas...");
    });
  }

  @Test
  public void mapFluxTest(){
//    fluxLearnService.mapFluxExample().subscribe(System.out::println);

     Flux<String> stringFlux = fluxLearnService.mapFluxExample();

    StepVerifier.create(stringFlux)
        .expectNext("Hello".toUpperCase(), "World".toUpperCase(), "From".toUpperCase(),"Mehedi".toUpperCase())
        .verifyComplete();
  }

  @Test
  public void filterFluxTest(){
    final Flux<String> stringFlux = fluxLearnService.filterFluxExample();

//    stringFlux.subscribe(System.out::println);

    StepVerifier.create(stringFlux)
        .expectNext("Hello", "World", "Mehedi")
        .verifyComplete();
  }

  @Test
  public void flatMapTest(){
     Flux<String> stringFlux = fluxLearnService.flatmapFluxExample();
    StepVerifier.create(stringFlux)
        .expectNextCount(20)
        .verifyComplete();
  }

  @Test
  public void mergeWIthTest(){
    Flux<String> flux1 = Flux.just("A", "B").delayElements(Duration.ofMillis(100));
    Flux<String> flux2 = Flux.just("C", "D").delayElements(Duration.ofMillis(500));

    flux1.mergeWith(flux2).subscribe(System.out::println);

    try{
      Thread.sleep(2000);
    }catch (Exception e){
      e.printStackTrace();
    }

  }

  @Test
  public void zipTest(){
    Flux<String> first = Flux.just("First", "Fourth");
    Flux<String> second = Flux.just("Second", "Fifth");
    Flux<String> third = Flux.just("Third", "Sixth");

    final Flux<Tuple3<String, String, String>> zip = Flux.zip(first, second, third).log();
//    zip.subscribe(System.out::println);

    StepVerifier.create(zip)
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  public void firstTask(){
     Mono<String> just = Mono.just("Hello, Reactive World!");
     just.subscribe(System.out::println);
  }

  @Test
  public void secondTask(){
    Flux<Integer> dataFromList = fluxLearnService.getDataFromList();
    dataFromList.subscribe(data -> System.out.println(data));
  }

  @Test
  public void thirdTask(){
    Flux<String> uppercasedData = fluxLearnService.getUppercasedData();
    uppercasedData.subscribe(System.out::println);
  }

  @Test
  public void fourthData(){
    Flux<Integer> oddNumber = fluxLearnService.getOddNumber();
    oddNumber.subscribe(System.out::println);

    try{
      Thread.sleep(14000);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Test
  public void fifthTask(){
    final Mono<Object> emptyValue = fluxLearnService.getEmptyValue();

    emptyValue.subscribe(System.out::println);

  }

  @Test
  public void combineTasks(){
    final Flux<String> stringFlux = fluxLearnService.combineTwoFlux();

    stringFlux.subscribe(System.out::println);
  }

  @Test
  public void multiply(){
    final Flux<Integer> integerFlux = fluxLearnService.multiplyBy2();

    integerFlux.subscribe(System.out::println);
  }

  @Test
  public void merge(){
    final Flux<Integer> integerFlux = fluxLearnService.mergeFluxes();

    integerFlux.subscribe(System.out::println);
  }
}
