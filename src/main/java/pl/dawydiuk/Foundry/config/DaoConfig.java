package pl.dawydiuk.Foundry.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Judith on 25.02.2019.
 */

@Configuration
@EnableTransactionManagement
@Slf4j
public class DaoConfig {

    @Value("${datasource.driver-class-name}")
    private String DRIVER_CLASS;

    @Value("${datasource.url}")
    private String DATEBASE_URL;

    @Value("${datasource.username}")
    private String USER_NAME;

    @Value("${datasource.password}")
    private String USER_PASSWORD;

    @Value("${hibernate.dialect}")
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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource appDataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(appDataSource);

        HibernateJpaVendorAdapter  hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        //hibernateJpaVendorAdapter.setDatabase(Database.H2);
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        em.setPackagesToScan(new String[]{"models"});
        em.setJpaProperties(additionalProperties());
        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.dialect", DIALECT);
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }

    @Bean
    @Primary
    public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean//bean ktory tlumaczy wyjatki na klasy Springa
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
