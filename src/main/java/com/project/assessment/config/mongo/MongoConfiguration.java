package com.project.assessment.config.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String uri;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Override
	public String getDatabaseName() {
		return this.database;
	}

	@Override
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient(new MongoClientURI(this.uri));
	}

	/*
	 * Spring adds "_class" entry to MongoDB objects to support polymorphism. If you don't need polymorphism support, _class wastes spaces and introduces issues if you refactor your model classes to
	 * different packages.
	 */
	@Override
	@Bean
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
		converter.setCustomConversions(customConversions());
		// For remove "_class" from documents
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return converter;
	}
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
	    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

}
