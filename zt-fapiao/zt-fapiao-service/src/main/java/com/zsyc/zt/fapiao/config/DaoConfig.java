package com.zsyc.zt.fapiao.config;

import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by lcs on 2018/9/26.
 */

@Configuration
@MapperScan(basePackages = {"com.zsyc.**.dao", "com.zsyc.**.mapper",})
public class DaoConfig {

    //	@Bean
//	public SqlSessionFactoryBean sqlSessionFactory(PaginationInterceptor paginationInterceptor,DataSource dataSource) throws IOException {
//		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//		factory.setMapperLocations(
//				new PathMatchingResourcePatternResolver().getResources("classpath*:**/mapper/*.xml")
//		);
//		factory.setDataSource(dataSource);
//		factory.setPlugins(new Interceptor[]{paginationInterceptor});
//		return factory;
//	}
/*    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(
            DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }*/

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public OracleKeyGenerator oracleKeyGenerator() {
        return new OracleKeyGenerator();
    }
}
