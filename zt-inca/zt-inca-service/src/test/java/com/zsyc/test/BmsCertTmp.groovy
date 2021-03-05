package com.zsyc.test

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class BmsCertTmp {
    static void main(String[] args) {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@172.168.220.10:1521:orcl","gdztzs2","gdztzs2");

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
        for(String recId:recIdList){
            PreparedStatement idPs = con.prepareStatement('select BMS_CERT_DTL_TMP_SEQ.nextval xx from dual')
            ResultSet idRs = idPs.executeQuery();
            idRs.next();
            Long id = idRs.getLong(1)
            println id

            PreparedStatement trPs = con.prepareStatement('select bms_cert_dtl_tmp_trans_seq.nextval  from dual')
            ResultSet trRs = trPs.executeQuery();
            trRs.next();
            Long tr =  trRs.getLong(1)
            println tr
            PreparedStatement preparedStatement = con.prepareStatement("insert into BMS_CERT_DTL_TMP(TMPID,SOURCEID,SOURCETABLE,CERTTYPE,ACCTYPE,TRANSACTIONID) values(?,?,'BMS_SA_REC_DOC',?,?,?)")

            preparedStatement.setLong(1,id)
            preparedStatement.setString(2,recId)
            preparedStatement.setInt(3,3)
            preparedStatement.setInt(4,3)
            preparedStatement.setLong(5,tr)
            preparedStatement.execute();

        }

        con.close();


        println "111";
    }
}
