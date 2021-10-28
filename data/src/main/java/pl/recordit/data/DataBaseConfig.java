package pl.recordit.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setName("books")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/sql/schema.sql")
                .addScript("/sql/insert.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource source){
        return new JdbcTemplate(source);
    }
}
