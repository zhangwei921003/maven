package com.study.panda.persistence.po;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Book {

  protected Long id;
  protected String title;
  protected String author;

  public Book(Long id, String title, String author, BigDecimal price) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.price = price;
  }

  public Book(String title, String author, BigDecimal price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  protected BigDecimal price;
  private ZonedDateTime createDate;
  private ZonedDateTime updateDate;
}
