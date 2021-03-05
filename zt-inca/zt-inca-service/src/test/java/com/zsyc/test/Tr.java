package com.zsyc.test;

import org.junit.Test;

import java.sql.*;

public class Tr {



    @Test
    public void test1() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection1 = DriverManager.getConnection("jdbc:oracle:thin:@172.168.200.230:1521:orcl", "b2b", "b2b");
        Connection connection_target= DriverManager.getConnection("jdbc:oracle:thin:@172.168.220.10:1521:orcl", "b2b", "b2b");

        PreparedStatement preparedStatement = connection1.prepareStatement("select id,name,content from UREPORT_FILE_TBL");


        PreparedStatement preparedStatement_target = connection_target.prepareStatement("insert into UREPORT_FILE_TBL(id,name,content) values(?,?,?)");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){

            preparedStatement_target.setInt(1,resultSet.getInt(1));
            preparedStatement_target.setString(2,resultSet.getString(2));
            preparedStatement_target.setBinaryStream(3,resultSet.getBlob(3).getBinaryStream());
            preparedStatement_target.executeUpdate();

        }

        preparedStatement_target.close();

        connection1.close();
        connection_target.close();



    }
}
