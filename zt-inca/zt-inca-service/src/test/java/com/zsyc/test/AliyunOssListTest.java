package com.zsyc.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class AliyunOssListTest {


    /**
     * accessKeyId
     */
    private String accessKeyId = "LTAI4GJ12GtvfzZyLjKpx2yN";

    /**
     * accessKeySecret
     */
    private String accessKeySecret = "S5B2hcXni2qjRzahvdwEVcZjdPrMem";

    /**
     * endpoint
     * oss-cn-shenzhen.aliyuncs.com
     */
    private String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";

    /**
     * bucketName
     * gdztyy-b2b.oss-cn-shenzhen.aliyuncs.com
     */
    private String bucketName = "gdztyy-b2b";

    @Resource
    DataSource dataSource;


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    OSS client = new OSSClientBuilder().build(
            endpoint,
            accessKeyId,
            accessKeySecret);

    @Test
    public void test1() throws SQLException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // ??????ListObjectsRequest?????????
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);

        // ??????prefix???????????????fun???????????????????????????

        // ???????????????
        String nextMarker ="";
        Pattern pattern = Pattern.compile("goods/(\\d*)/(\\d\\.(jpg|png))");

        PreparedStatement preparedStatement =dataSource.getConnection().prepareStatement("insert into b2b.TMP_GOODS_PIC(goods_id,pic) values(?,?)");

        while(true){
            listObjectsRequest.setPrefix("goods/");
            listObjectsRequest.setMarker(nextMarker);
            listObjectsRequest.setMaxKeys(1000);
            listObjectsRequest.setDelimiter("/");
            ObjectListing listing = ossClient.listObjects(listObjectsRequest);

            // ?????????????????????
            /*System.out.println("Objects:");
            for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {

                System.out.println(objectSummary.getKey());
                if(pattern.matcher(objectSummary.getKey()).matches()){
                    System.out.println("-------????????????------------");
                    System.out.println(objectSummary.getKey());
                }

            }*/


            System.out.println("CommonPrefixes:");
            for (String commonPrefix : listing.getCommonPrefixes()) {
                System.out.println(commonPrefix);
                ListObjectsRequest listObjectsFileRequest = new ListObjectsRequest(bucketName);
                listObjectsFileRequest.setPrefix(commonPrefix);
                listObjectsRequest.setMaxKeys(1000);
                ObjectListing listFile = ossClient.listObjects(listObjectsFileRequest);

                for (OSSObjectSummary fileSummary : listFile.getObjectSummaries()) {
                   Matcher matcher =  pattern.matcher(fileSummary.getKey());

                        while (matcher.find()){

                            System.out.println("-------????????????------------");
                            System.out.println("??????ID:"+matcher.group(1));
                            System.out.println("?????????:"+matcher.group(2));
                            preparedStatement.setString(1,matcher.group(1));
                            preparedStatement.setString(2,matcher.group(2));
                            preparedStatement.executeUpdate();
                        }

                }

            }
            nextMarker = listing.getNextMarker();
            //System.out.println(nextMarker);
            if (!listing.isTruncated()) {

                break;

            }

        }


        // ????????????commonPrefix???

        // ??????OSSClient???



        ossClient.shutdown();
    }
}
