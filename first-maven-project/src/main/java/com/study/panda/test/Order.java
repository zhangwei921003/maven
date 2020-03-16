package com.study.panda.test;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

  private String name;
  private List<Product> products;
}
