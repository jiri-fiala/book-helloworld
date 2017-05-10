package org.openshift;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TextGenerator {
  public String generateUserList() {
    String userDetails = "";

    try {
      String databaseURL = "jdbc:mysql://";
      databaseURL += System.getenv("MYSQL_SERVICE_HOST");
      databaseURL += "/" + System.getenv("MYSQL_DATABASE");

      String username = System.getenv("MYSQL_USER");
      String password = System.getenv("MYSQL_PASSWORD");

      Connection connection = DriverManager.getConnection(databaseURL, username, password);

      if (connection != null) {
        String SQL = "select name, nickname, species from users";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
          while (rs.next()) {
            userDetails = String.format("Name: %s, Nickname: %s, Species: %s",
            rs.getString("name"), rs.getString("nickname"), rs.getString("species"));
          }
          rs.close();
          connection.close();
        }
      } catch (Exception e) {
        return "Database connection problem!";
      }
    return userDetails;
  }
}
