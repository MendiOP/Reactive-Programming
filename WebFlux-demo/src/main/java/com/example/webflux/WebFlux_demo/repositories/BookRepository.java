package com.example.webflux.WebFlux_demo.repositories;

import com.example.webflux.WebFlux_demo.entity.BookModel;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository  extends ReactiveCrudRepository<BookModel, Integer> {
  @Query("select * from books_table where name like :query" )
  Flux<BookModel> searchBook(String query);
}
