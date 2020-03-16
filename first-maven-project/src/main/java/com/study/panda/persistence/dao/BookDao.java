package com.study.panda.persistence.dao;

import com.study.panda.persistence.po.Book;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

  private String jdbcURL;
  private String jdbcUsername;
  private String jdbcPassword;
  private Connection jdbcConnection;

  public BookDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
    this.jdbcURL = jdbcURL;
    this.jdbcUsername = jdbcUsername;
    this.jdbcPassword = jdbcPassword;
  }

  protected void connect() throws SQLException {
    if (jdbcConnection == null || jdbcConnection.isClosed()) {
      try {
        Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
        throw new SQLException(e);
      }
      jdbcConnection = DriverManager.getConnection(
          jdbcURL, jdbcUsername, jdbcPassword);
    }
  }

  protected void disconnect() throws SQLException {
    if (jdbcConnection != null && !jdbcConnection.isClosed()) {
      jdbcConnection.close();
    }
  }

  public boolean insertBook(Book book) throws SQLException {
    String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
    connect();

    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    statement.setString(1, book.getTitle());
    statement.setString(2, book.getAuthor());
    statement.setBigDecimal(3, book.getPrice());

    boolean rowInserted = statement.executeUpdate() > 0;
    statement.close();
    disconnect();
    return rowInserted;
  }

  public List<Book> listAllBooks() throws SQLException {
    List<Book> listBook = new ArrayList<>();

    String sql = "SELECT * FROM book";

    connect();

    Statement statement = jdbcConnection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {
      Long id = resultSet.getLong("book_id");
      String title = resultSet.getString("title");
      String author = resultSet.getString("author");
      BigDecimal price = resultSet.getBigDecimal("price");

      Book book = new Book(id, title, author, price, ZonedDateTime.now(), ZonedDateTime.now());
      listBook.add(book);
    }

    resultSet.close();
    statement.close();

    disconnect();

    return listBook;
  }

  public boolean deleteBook(Long id) throws SQLException {
    String sql = "DELETE FROM book where book_id = ?";

    connect();

    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    statement.setLong(1, id);

    boolean rowDeleted = statement.executeUpdate() > 0;
    statement.close();
    disconnect();
    return rowDeleted;
  }

  public boolean updateBook(Book book) throws SQLException {
    String sql = "UPDATE book SET title = ?, author = ?, price = ?";
    sql += " WHERE book_id = ?";
    connect();

    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    statement.setString(1, book.getTitle());
    statement.setString(2, book.getAuthor());
    statement.setBigDecimal(3, book.getPrice());
    statement.setLong(4, book.getId());

    boolean rowUpdated = statement.executeUpdate() > 0;
    statement.close();
    disconnect();
    return rowUpdated;
  }

  public Book getBook(Long id) throws SQLException {
    Book book = null;
    String sql = "SELECT * FROM book WHERE book_id = ?";
    connect();
    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    statement.setLong(1, id);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      String title = resultSet.getString("title");
      String author = resultSet.getString("author");
      BigDecimal price = resultSet.getBigDecimal("price");
      book = Book.builder().id(id)
          .title(title)
          .author(author)
          .price(price)
          .build();
    }
    resultSet.close();
    statement.close();
    return book;
  }
}
