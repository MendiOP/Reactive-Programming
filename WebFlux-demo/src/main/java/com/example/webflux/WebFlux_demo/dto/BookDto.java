package com.example.webflux.WebFlux_demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

  private int bookId;
  private String name;
  private String description;
  private String publisher;
  private String author;

}
