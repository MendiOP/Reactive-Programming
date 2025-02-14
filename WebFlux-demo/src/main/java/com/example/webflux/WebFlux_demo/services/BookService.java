package com.example.webflux.WebFlux_demo.services;

import com.example.webflux.WebFlux_demo.dto.BookDto;
import com.example.webflux.WebFlux_demo.entity.BookModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookService {
   Mono<BookDto> createBook(BookDto bookDto);
   Flux<BookDto> getAllBooks();
   Mono<BookDto> getBookById(int bookId);
   Mono<BookDto> updateBook(int bookId, BookDto bookDto);
   Mono<Void> deleteBook(int bookId);
   Flux<BookDto> searchBooks(String keyword);
}
