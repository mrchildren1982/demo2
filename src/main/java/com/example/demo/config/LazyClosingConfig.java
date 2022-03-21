package com.example.demo.config;

import java.util.LinkedList;
import java.util.Queue;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.CommandImplementors;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class LazyClosingConfig implements Config, AutoCloseable {

	@Autowired
	private Config config;
	private final Queue<AutoCloseable> autoCloseables = new LinkedList<>();
	private final CommandImplementors commandImplementors = new LazyClosingCommandImplementors(
			this);

	public LazyClosingConfig() {

	}

	public LazyClosingConfig(Config config) {
		this.config = config;
	}

	/*
	 * SELECT文を実行した場合、Connection・PreparedStatement・ResultSetを閉じずに
	 * 結果を返すSelectCommandを使用するCommandImplementorsの実装を返します。
	 */
	@Override
	public CommandImplementors getCommandImplementors() {
		return commandImplementors;
	}

	/*
	 * AutoCloseableを集めます。
	 * Connection・PreparedStatement・ResultSetはAutoCloseableをimplementsしています。
	 * このメソッドはLazyClosingCommandImplementorsが生成するLazyClosingSelectCommandから
	 * 呼ばれることを想定しています。
	 */
	public void addAutoCloseable(AutoCloseable autoCloseable) {
		autoCloseables.add(autoCloseable); 
	}

	/*
	 * 収集したAutoCloseableを全てcloseします。
	 */
	@Override
	public void close() throws Exception {
		AutoCloseable autoCloseable;
		while ((autoCloseable = autoCloseables.poll()) != null) {
			autoCloseable.close();
		}
	}

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	@Override
	public DataSource getDataSource() {

		DataSource dataSource = DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver")
				.username("root")
				.password("tbK112001!")
				.url("jdbc:mysql://localhost:3306/sampledba").build();
		return new TransactionAwareDataSourceProxy(dataSource);

	}
	/*
	 * これより下に定義したメソッドはすべて保持しているconfigの同メソッドを呼び出します。
	 */
//
//	@Override
//	public DataSource getDataSource() {
//
//		DataSource dataSource = dataSource();
//
//		return new TransactionAwareDataSourceProxy(dataSource);
//	}

	@Override
	public Dialect getDialect() {
		//        return config.getDialect();

		return new MysqlDialect();
	}

	@Override
	public String getDataSourceName() {
		return config.getDataSourceName();
	}

//	@Override
//	public SqlFileRepository getSqlFileRepository() {
//		return config.getSqlFileRepository();
//	}
//
//	@Override
//	public JdbcLogger getJdbcLogger() {
//		return config.getJdbcLogger();
//	}
//
//	@Override
//	public RequiresNewController getRequiresNewController() {
//		return config.getRequiresNewController();
//	}
//
//	@Override
//	public ClassHelper getClassHelper() {
//		return config.getClassHelper();
//	}
//
//	@Override
//	public QueryImplementors getQueryImplementors() {
//		return config.getQueryImplementors();
//	}
//
//	@Override
//	public SqlLogType getExceptionSqlLogType() {
//		return config.getExceptionSqlLogType();
//	}
//
//	@Override
//	public UnknownColumnHandler getUnknownColumnHandler() {
//		return config.getUnknownColumnHandler();
//	}
//
//	@Override
//	public Naming getNaming() {
//		return config.getNaming();
//	}
//
//	@Override
//	public MapKeyNaming getMapKeyNaming() {
//		return config.getMapKeyNaming();
//	}

	@Override
	public TransactionManager getTransactionManager() {
		return config.getTransactionManager();
	}
//
//	@Override
//	public Commenter getCommenter() {
//		return config.getCommenter();
//	}
//
//	@Override
//	public int getMaxRows() {
//		return config.getMaxRows();
//	}
//
//	@Override
//	public int getFetchSize() {
//		return config.getFetchSize();
//	}
//
//	@Override
//	public int getQueryTimeout() {
//		return config.getQueryTimeout();
//	}
//
//	@Override
//	public int getBatchSize() {
//		return config.getBatchSize();
//	}
//
//	@Override
//	public EntityListenerProvider getEntityListenerProvider() {
//		return config.getEntityListenerProvider();
//	}
}
