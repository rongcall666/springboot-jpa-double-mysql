package com.example.demo.conf;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySecond",//配置连接工程
        transactionManagerRef="transactionManagerSecond",//配置事务管理器
        basePackages= { "com.example.demo.dao.test2" }) //配置dao所在的位置
public class SecondConfig {
	
	@Autowired
	@Qualifier("secondaryDatasource")
	private DataSource secondaryDatasource;
	
	 // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
    // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
	@Bean(name="entityManagerFactorySecond")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary 
	(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(secondaryDatasource)//设置数据源
				.packages("com.example.demo.entity.test2")//设置实体类所在位置
				//.扫描所有带有 @Entity 注解的类
				.persistenceUnit("secondPersistenceUnit")
				.build();
	}
	
	//配置事务管理器
    @Bean(name = "transactionManagerSecond")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}

