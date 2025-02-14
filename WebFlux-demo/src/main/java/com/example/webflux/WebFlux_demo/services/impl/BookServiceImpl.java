package com.example.webflux.WebFlux_demo.services.impl;

import com.example.webflux.WebFlux_demo.dto.BookDto;
import com.example.webflux.WebFlux_demo.entity.BookModel;
import com.example.webflux.WebFlux_demo.exception.BookNotFoundException;
import com.example.webflux.WebFlux_demo.repositories.BookRepository;
import com.example.webflux.WebFlux_demo.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final ModelMapper modelMapper;

  public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
    this.bookRepository = bookRepository;
    this.modelMapper = modelMapper;
  }


  @Override
  public Mono<BookDto> createBook(BookDto bookDto) {

    BookModel currentBookModel = modelMapper.map(bookDto, BookModel.class);
    Mono<BookModel> savedBook = bookRepository.save(currentBookModel);

    return savedBook.map(book -> modelMapper.map(book, BookDto.class));
  }

  @Override
  public Flux<BookDto> getAllBooks() {

    Flux<BookModel> all = bookRepository.findAll();
    return all.map(bookModel -> modelMapper.map(bookModel, BookDto.class));
  }

  @Override
  public Mono<BookDto> getBookById(int bookId) {

    Mono<BookModel> bookModel = bookRepository.findById(bookId);
    return bookModel
        .switchIfEmpty(Mono.error(new BookNotFoundException("Book with " + bookId + " id not found")))
        .map(book -> modelMapper.map(book, BookDto.class));
  }

  @Override
  public Mono<BookDto> updateBook(int bookId, BookDto bookDto) {

    BookModel currentBookModel = modelMapper.map(bookDto, BookModel.class);

    return bookRepository.findById(bookId)
        .switchIfEmpty(Mono.error(new BookNotFoundException("Book with " + bookId + " id not found")))
        .flatMap(oldBook -> {
          oldBook.setName(currentBookModel.getName());
          oldBook.setAuthor(currentBookModel.getAuthor());
          oldBook.setPublisher(currentBookModel.getPublisher());
          oldBook.setDescription(currentBookModel.getDescription());
          return bookRepository.save(oldBook);
        })
        .map(oldBook -> modelMapper.map(oldBook, BookDto.class));
  }

  @Override
  public Mono<Void> deleteBook(int bookId) {
    Mono<BookModel> willBeDeletedBook = bookRepository.findById(bookId);
    return willBeDeletedBook
        .switchIfEmpty(Mono.error(new BookNotFoundException("Book with " + bookId + " id not found")))
        .flatMap(oldBook -> bookRepository.delete(oldBook));
  }

  @Override
  public Flux<BookDto> searchBooks(String keyword) {

    String currentQuery = "%" + keyword + "%";

    Flux<BookModel> bookModelFlux = bookRepository.searchBook(currentQuery);
    return bookModelFlux.map(oldBook -> modelMapper.map(oldBook, BookDto.class));
  }
}
