package com.zsyc.test

import org.springframework.test.context.jdbc.Sql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class BmsSaRec {
    static void main(String[] args) {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@172.168.220.10:1521:orcl", "gdztzs2", "gdztzs2");

        /*def sql =  Sql.newInstance(oracle.jdbc.driver.OracleDriver.class,["jdbc:oracle:thin:@172.168.220.8:1521:orcl", "incaerp",
                 "incaerp"])*/
        //"UPDATE Author SET lastname='Pragt' where lastname='Thorvaldsson'"
        List<String> recIdList = ['241319',
                                  '241320',
                                  '241321',
                                  '241322',
                                  '241323',
                                  '241324',
                                  '241330',
                                  '241331',
                                  '241332',
                                  '241333',
                                  '241334',
                                  '241335',
                                  '241336',
                                  '241337',
                                  '241338',
                                  '241339',
                                  '241340',
                                  '241346',
                                  '241347',
                                  '241348',
                                  '241349',
                                  '241358',
                                  '241359',
                                  '241406',
                                  '241408',
                                  '241409',
                                  '241410',
                                  '241411',
                                  '241412',
                                  '241414',
                                  '241415',
                                  '241416',
                                  '241417',
                                  '241418',
                                  '241419',
                                  '241420',
                                  '241421',
                                  '241422',
                                  '241423',
                                  '241424',
        ];

        for (String recId : recIdList) {

            PreparedStatement preparedStatement = con.prepareStatement(" update bms_sa_rec_doc a set a.Recexpmoney =  -a.Recexpmoney where a.sarecid = ?")

            preparedStatement.setString(1, recId)
            preparedStatement.executeUpdate();

        }

        con.close();


        println "111";
    }
}
