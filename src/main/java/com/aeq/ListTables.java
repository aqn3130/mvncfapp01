package com.aeq;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A sample app that connects to a Cloud SQL instance and lists all available tables in a database.
 */
public class ListTables {
  public static void main(String[] args) throws IOException, SQLException {
    // TODO: fill this in
    // The instance connection name can be obtained from the instance overview page in Cloud Console
    // or by running "gcloud sql instances describe <instance> | grep connectionName".
    String instanceConnectionName = "business-systems-technology:europe-west1:canisignit";

    // TODO: fill this in
    // The database from which to list tables.
    String databaseName = "canisignit";

    String username = "aqn3130";

    // TODO: fill this in
    // This is the password that was set via the Cloud Console or empty if never set
    // (not recommended).
    String password = "solars7M";

//    if (instanceConnectionName.equals("business-systems-technology:europe-west1:canisignit")) {
//      System.err.println("Please update the sample to specify the instance connection name.");
//      System.exit(1);
//    }
//
//    if (password.equals("solars7Morange")) {
//      System.err.println("Please update the sample to specify the mysql password.");
//      System.exit(1);
//    }

    //[START doc-example]
//    String jdbcUrl = String.format(
//        "jdbc:mysql://google/%s?cloudSqlInstance=%s"
//            + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
//            "mini-db-test",
//        "business-systems-technology:europe-west2:mini-db-test");
    String jdbcUrl = "jdbc:mysql://google/canisignit?cloudSqlInstance=business-systems-technology:europe-west1:canisignit&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false";

    Connection connection = DriverManager.getConnection(jdbcUrl,"root","root");
    System.out.println(connection.getSchema());
//   [END doc-example]

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SHOW TABLES");
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
    }
  }
}