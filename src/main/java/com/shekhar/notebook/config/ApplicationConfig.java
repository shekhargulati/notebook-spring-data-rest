package com.shekhar.notebook.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.shekhar.notebook.domain.Notebook;
import com.shekhar.notebook.repository.NotebookRepository;

@Configuration
@ComponentScan(basePackages = "com.shekhar.notebook")
@EnableJpaRepositories(basePackageClasses = NotebookRepository.class)
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(Notebook.class.getPackage().getName());
		factory.setDataSource(dataSource());
		return factory;
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://" + System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST") + ":"
				+ System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT") + "/"+System.getenv("OPENSHIFT_APP_NAME"));
		dataSource.setUsername(System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME"));
		dataSource.setPassword(System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD"));
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(1800000L);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1800000L);
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}
	
	@Bean
	public JpaDialect jpaDialect() {
		return new HibernateJpaDialect();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}

}