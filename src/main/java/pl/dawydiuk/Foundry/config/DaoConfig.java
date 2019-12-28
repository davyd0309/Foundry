package pl.dawydiuk.Foundry.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Konrad on 25.02.2019.
 */

@Configuration
@EnableTransactionManagement
@Slf4j
public class DaoConfig {

    @Value("${datasource.driver-class-name:class}")
    private String DRIVER_CLASS;

    @Value("${datasource.url:url}")
    private String DATEBASE_URL;

    @Value("${datasource.username:username}")
    private String USER_NAME;

    @Value("${datasource.password:password}")
    private String USER_PASSWORD;

    @Value("${hibernate.dialect:dialect}")
    private  String DIALECT;


    @Bean
    public DataSource appDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUrl(DATEBASE_URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(USER_PASSWORD);
        return dataSource;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource appDataSource) {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(appDataSource);
//
//        HibernateJpaVendorAdapter  hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        //hibernateJpaVendorAdapter.setDatabase(Database.H2);
//        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
//        em.setPackagesToScan(new String[]{"models"});
//        em.setJpaProperties(additionalProperties());
//        return em;
//    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(appDataSource());
        sessionFactory.setPackagesToScan("models");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.put("hibernate.format_sql", "true");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

//    Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.format_sql", "true");
//        properties.setProperty("hibernate.dialect", DIALECT);
//        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
//        return properties;
//    }

//    @Bean
//    @Primary
//    public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
//        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//            throw new NullPointerException("factory is not a hibernate factory");
//        }
//        return entityManagerFactory.unwrap(SessionFactory.class);
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(
//            EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//
//        return transactionManager;
//    }

    @Bean//bean ktory tlumaczy wyjatki na klasy Springa
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
