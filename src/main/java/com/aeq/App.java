package com.aeq;

import com.aeq.AppData.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.sql.*;

import com.sun.jersey.core.util.Base64;

@Configuration
@Import(Data.class)

public class App {

    @Autowired
    private String greeting;


    public static void main( String[] args ) {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        String text = (String) context.getBean("greeting");
        Data.Worker worker = context.getBean(Data.Worker.class);
        worker.execute();

        String instanceConnectionName_canisignit = "business-systems-technology:europe-west1:canisignit";
        String dbName_canisignit = "canisignit";

        String user = "root";
        String password = "root";


        String instanceConnectionName_eCard18 = "business-systems-technology:europe-west1:ecard18";
        String dbName_eCard18 = "ecard18";

        Connection connection = null;
        try {

			String jdbcUrl = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s"
                        + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
                    dbName_canisignit,
                    instanceConnectionName_canisignit);
			connection = DriverManager.getConnection(jdbcUrl, user, password);
//        System.out.println(connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

        }

        if (connection != null) {
            System.out.println("Connection made to DB!");
        }

        String allFromCompanies = "SELECT * FROM Companies";
        String showTables = "SHOW TABLES";

        try (Statement statement = connection.createStatement()) {
          ResultSet resultSet = statement.executeQuery(allFromCompanies);
          while (resultSet.next()) {
              System.out.println(resultSet.getString(1));
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
