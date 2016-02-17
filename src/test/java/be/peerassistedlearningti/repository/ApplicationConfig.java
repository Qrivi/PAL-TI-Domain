package be.peerassistedlearningti.repository;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories( "be.peerassistedlearningti" )
public class ApplicationConfig
{

    @Bean
    public DataSource dataSource()
    {
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setURL( "jdbc:mysql://185.3.210.249:3306/PAL-TEST?autoReconnectForPools=true" );
        dataSource.setUser( "PAL-TI" );
        dataSource.setPassword( "DjKdwfrmquJ9SL55" );

        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager()
    {
        return new JpaTransactionManager( entityManagerFactory().getObject() );
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase( Database.MYSQL );
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource( dataSource() );
        factoryBean.setJpaVendorAdapter( jpaVendorAdapter() );
        factoryBean.setPackagesToScan( "be.peerassistedlearningti" );
        return factoryBean;
    }

}
