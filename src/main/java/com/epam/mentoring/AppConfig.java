package com.epam.mentoring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.epam.mentoring.db.DBContentGenerator;
import com.epam.mentoring.db.server.DBServerStarter;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { AppConfig.class })
public class AppConfig {

	@Bean
	@Autowired
	public DataSourceInitializer dataSourceIniter(final DataSource ds) {
		
		final DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDatabasePopulator(dbInit());
		dataSourceInitializer.setDataSource(ds);
		return dataSourceInitializer;
	}

	@Bean
	public DatabasePopulator dbInit() {
		final ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(
				new ClassPathResource("sql/schema.sql"));

		return resourceDatabasePopulator;
	}

	@Bean
	public DBContentGenerator generator() {
		return new DBContentGenerator();
	}

	@Bean
	public DBServerStarter starter() {
		return new DBServerStarter();
	}

	@Bean
	@Autowired
	@Scope("prototype")
	public JdbcTemplate jdbcTemplate(final DataSource ds) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(ds);
		return jdbcTemplate;
	}

}
