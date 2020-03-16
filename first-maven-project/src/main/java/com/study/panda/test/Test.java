package com.study.panda.test;

import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.StringUtils.split;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.core.io.ClassPathResource;

public class Test {

  public static void main(String[] args) throws IOException {
    Test test = new Test();
    test.writeFile(test.tryToMergeData(test.getOrders(test.file("test.txt"))));
  }


  private String tryToMergeData(List<Order> orders) {
    StringBuffer result = new StringBuffer();
    result.append("======================订单明细统计========================\r\n");
    orders.forEach(value -> {
      StringBuffer sb = new StringBuffer();
      sb.append(value.getProducts().stream().mapToInt(Product::getNum).sum() + " ");
      value.getProducts().forEach(dto -> sb.append(dto.getProductName() + " " + dto.getNum() + " "));
      result.append(value.getName() + " " + sb.toString() + "\r\n");
    });

    Map<String, List<Product>> productMap = orders.stream().map(Order::getProducts).flatMap(Collection::stream)
        .collect(groupingBy(Product::getProductName));

    result.append("======================商品数量统计========================\r\n");
    productMap.entrySet().stream().sorted(Comparator.comparing(Entry::getKey))
        .forEach(entry -> result.append(entry.getKey() + ":" + entry.getValue().stream().mapToInt(Product::getNum).sum() + "\r\n"));
    result.append("总计："+productMap.values().stream().flatMap(Collection::stream).mapToInt(Product::getNum).sum());
    return result.toString();
  }

  private List<Order> getOrders(String file) {
    String[] parts = split(file, "\r\n");
    List<Order> orders = Lists.newArrayList();
    for (String part : parts) {
      String[] strings = split(part.trim(), ":");
      String suffix = strings[1];
      String[] productString = split(suffix.trim(), ",");
      List<Product> products = Lists.newArrayList();
      for (String s : productString) {
        products.add(new Product(s.replaceAll("[0-9+]", "").trim(), Integer.parseInt(s.replaceAll("[^0-9+]", "").trim())));
      }
      orders.add(new Order(strings[0].trim(), products));
    }
    return orders;
  }

  private void writeFile(String content) {
    String file = System.getProperty("user.dir") + "/src/main/resources/order.txt";
    try {
      Files.write(Paths.get(file), content.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected String file(String path) throws IOException {
    return new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8))
        .lines()
        .reduce((a, b) -> a + "\n" + b)
        .orElse("");
  }
}
