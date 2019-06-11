package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@ComponentScan("com.example.demo")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig implements Config {

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Override
	public Dialect getDialect() {
		return new MysqlDialect();
	}

	@Override
	public DataSource getDataSource() {

		AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {

				if (MultipleDataSourceContextHolder.getDataSourceType() == null) {
					return DataSourceType.MYSQL.getValue();
				}
				return MultipleDataSourceContextHolder.getDataSourceType().getValue();
			}

		};
		Map<Object, Object> dataSources = new HashMap<>();
		dataSources.put(DataSourceType.POSTGRES.getValue(), dataSourceForProduct());
		dataSources.put(DataSourceType.MYSQL.getValue(), dataSourceForDefault());
		abstractRoutingDataSource.setTargetDataSources(dataSources);
		abstractRoutingDataSource.setDefaultTargetDataSource(dataSourceForDefault());

		abstractRoutingDataSource.afterPropertiesSet();
		// return new TransactionAwareDataSourceProxy(abstractRoutingDataSource);

		// データソースをトランザクション管理対象に追加
		return new TransactionAwareDataSourceProxy(abstractRoutingDataSource);

	}

	@ConfigurationProperties(prefix = "spring.datasource.postgres")
	@Bean("dataSourceForProduct")
	public DataSource dataSourceForProduct() {
		return DataSourceBuilder.create().driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/postgres").build();
	}

	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	@Bean("dataSourceForDefault")
	@Primary // これがないと起動できません
	public DataSource dataSourceForDefault() {
		return DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/sampledba").build();
	}

	// @Override
	// public TransactionManager getTransactionManager() {
	// return transactionManager;
	// }
	//
	// public static AppConfig singleton() {
	// return CONFIG;
	// }

	public void test() {

		//トランザクション管理
		//トランザクションマネージャー
		//PlatformTransactionManagerインタフェース
		//ｊDBCやMyBatisのようなJDBCベースのライブラリによるデータベースアクセスを行う場合に利用する
		PlatformTransactionManager jdbcManager = new DataSourceTransactionManager();

		PlatformTransactionManager hibernateManager = new HibernateTransactionManager();

		PlatformTransactionManager manager3 = new JpaTransactionManager();

		PlatformTransactionManager manager4 = new JtaTransactionManager();
	}
}