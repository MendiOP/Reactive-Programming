package com.example.webflux.WebFlux_demo.controller;

import com.example.webflux.WebFlux_demo.dto.BookDto;
import com.example.webflux.WebFlux_demo.entity.BookModel;
import com.example.webflux.WebFlux_demo.services.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class ReactiveController {

    private BookServiceImpl bookService;

    public ReactiveController(BookServiceImpl bookService) {
      this.bookService = bookService;
    }

    @PostMapping
    public Mono<ResponseEntity<BookDto>> createBook(@RequestBody BookDto bookDto) {
      Mono<BookDto> book = bookService.createBook(bookDto);

      return book.map(b -> ResponseEntity.status(HttpStatus.CREATED).body(b));
    }

    @GetMapping
    public ResponseEntity<Flux<BookDto>> getAllBooks() {
      Flux<BookDto> allBooks = bookService.getAllBooks();
      return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<BookDto>> getBookById(@PathVariable int id) {
      Mono<BookDto> bookById = bookService.getBookById(id);

      return bookById
          .map(b -> ResponseEntity.status(HttpStatus.OK).body(b))
          .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteBookById(@PathVariable int id) {
      Mono<Void> voidMono = bookService.deleteBook(id);

     return voidMono.then(Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build()));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<BookDto>> updateBook(@RequestBody BookDto bookDto, @PathVariable int id) {
      Mono<BookDto> bookDtoMono = bookService.updateBook(id, bookDto);
      return bookDtoMono.map(m -> ResponseEntity.status(HttpStatus.OK).build());
    }

    @GetMapping("/search")
    public ResponseEntity<Flux<BookDto>> searchBook(@RequestParam("query") String query) {
      Flux<BookDto> bookDtoFlux = bookService.searchBooks(query);

      return ResponseEntity.status(HttpStatus.OK).body(bookDtoFlux);
    }
}
