/**
 * 
 */
package com.poc.springk8s;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


/**
 * @author GANESH
 *
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration{
	
	Logger logger = LoggerFactory.getLogger(MongoConfig.class);

	@Value("${MONGO_HOST}")
	private String mongohost;

	@Value("${MONGO_DATABASE}")
	private String mongoDatabase;

	@Value("${MONGO_USERNAME}")
	private String mongoUserName;

	@Value("${MONGO_USER_PASSWORD}")
	private String mongoPassword;

	/**
	 * 
	 */
	public MongoConfig() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MongoClient mongoClient() {
		String stringURI="mongodb://"+mongoUserName+":"+mongoPassword+"@"+mongohost+":27017/"+mongoDatabase;
		logger.trace("uri "+stringURI);
		MongoClientURI clientURI=new MongoClientURI(stringURI);
		return new MongoClient(clientURI);
	}

	@Override
	protected String getDatabaseName() {
		return mongoDatabase;
	}

}
