package com.example.webflux.WebFlux_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("books_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookModel {
  @Id
  @Column("book_id")
  private int bookId;
  private String name;

  @Column("book_desc")
  private String description;
  private String publisher;
  private String author;
}
