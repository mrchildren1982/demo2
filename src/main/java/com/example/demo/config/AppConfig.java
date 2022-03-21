package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

//@Configuration
//@ComponentScan("com.example.demo")
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
public class AppConfig{
//implements Config {

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

//	@Override
//	public Dialect getDialect() {
//		return new MysqlDialect();
//	}
//
//	@Override
//	public DataSource getDataSource() {
//
//		AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
//			@Override
//			protected Object determineCurrentLookupKey() {
//
//				if (MultipleDataSourceContextHolder.getDataSourceType() == null) {
////					return DataSourceType.MYSQL.getValue();
//					return DataSourceType.POSTGRES.getValue();
//				}
//				return MultipleDataSourceContextHolder.getDataSourceType().getValue();
//			}
//
//		};
//		Map<Object, Object> dataSources = new HashMap<>();
//		dataSources.put(DataSourceType.POSTGRES.getValue(), dataSourceForProduct());
//		dataSources.put(DataSourceType.MYSQL.getValue(), dataSourceForDefault());
//
//		//トランザクションのテストのためにデフォルトのデータソースを逆にする
//		dataSources.put(DataSourceType.MYSQL.getValue(), dataSourceForProduct());
//		dataSources.put(DataSourceType.POSTGRES.getValue(), dataSourceForDefault());
//
//		abstractRoutingDataSource.setTargetDataSources(dataSources);
//		abstractRoutingDataSource.setDefaultTargetDataSource(dataSourceForDefault());
//
//		abstractRoutingDataSource.afterPropertiesSet();
//		// return new TransactionAwareDataSourceProxy(abstractRoutingDataSource);
//
//		// データソースをトランザクション管理対象に追加
//		return new TransactionAwareDataSourceProxy(abstractRoutingDataSource);
//
//	}
//
//	@ConfigurationProperties(prefix = "spring.datasource.postgres")
////	@Bean("dataSourceForProduct")
//	@Bean("dataSourceForDefault")
//	public DataSource dataSourceForProduct() {
//		return DataSourceBuilder.create().driverClassName("org.postgresql.Driver")
//				.url("jdbc:postgresql://localhost:5432/postgres").build();
//	}
//
//	@ConfigurationProperties(prefix = "spring.datasource.mysql")
////	@Bean("dataSourceForDefault")
//	@Bean("dataSourceForProduct")
//	@Primary // これがないと起動できません
//	public DataSource dataSourceForDefault() {
//		return DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver").username("root")
//				.password("tbK112001!")
//				.url("jdbc:mysql://localhost:3306/sampledba").build();
//	}
//
//	// @Override
//	// public TransactionManager getTransactionManager() {
//	// return transactionManager;
//	// }
//	//
//	// public static AppConfig singleton() {
//	// return CONFIG;
//	// }
//
//	public void test() {
//
//		//トランザクション管理
//		//トランザクションマネージャー
//		//PlatformTransactionManagerインタフェース
//		//ｊDBCやMyBatisのようなJDBCベースのライブラリによるデータベースアクセスを行う場合に利用する
//		PlatformTransactionManager jdbcManager = new DataSourceTransactionManager();
//
//		PlatformTransactionManager hibernateManager = new HibernateTransactionManager();
//
//		PlatformTransactionManager manager3 = new JpaTransactionManager();
//
//		PlatformTransactionManager manager4 = new JtaTransactionManager();
//	}
}