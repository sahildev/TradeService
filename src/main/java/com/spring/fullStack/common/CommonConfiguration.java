package com.spring.fullStack.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class CommonConfiguration {

	private final static String DYNAMODB_ENDPOINT_DEFAULT_VALUE = "http://localhost:8000";

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${dynamoDbEndpoint:" + DYNAMODB_ENDPOINT_DEFAULT_VALUE + "}")
	private String dynamoDbEndpoint;

	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	@Bean
	public AWSCredentials awsCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDb(AWSCredentials awsCredentials) {

		log.trace("Entering amazonDynamoDb()");
		AmazonDynamoDB client = new AmazonDynamoDBClient(awsCredentials);
		log.info("Using DynamoDb endpoint {}", dynamoDbEndpoint);
		client.setEndpoint(dynamoDbEndpoint);
		return client;
	}

	@Bean
	public DynamoDBMapper dynamoDbMapper(AmazonDynamoDB amazonDynamoDB) {

		log.trace("Entering dynamoDbMapper()");
		return new DynamoDBMapper(amazonDynamoDB);
	}
}
