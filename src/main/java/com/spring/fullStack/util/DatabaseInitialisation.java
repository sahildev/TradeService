package com.spring.fullStack.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;


@Component
public class DatabaseInitialisation implements ApplicationListener<ContextRefreshedEvent> {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private DynamoDBMapper dbMapper;

  @Autowired
  private AmazonDynamoDB dynamoDB;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    log.trace("Entering createDatabaseTablesIfNotExist()");
    CreateTableRequest request = dbMapper
        .generateCreateTableRequest(com.spring.fullStack.tradeService.Trade.class)
        .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
    try {
      DescribeTableResult result = dynamoDB.describeTable(request.getTableName());
      log.info("Table status {}, {}", request.getTableName(), result.getTable().getTableStatus());
    } catch (ResourceNotFoundException expectedException) {
      CreateTableResult result = dynamoDB.createTable(request);
      log.info("Table creation triggered {}, {}", request.getTableName(), result.getTableDescription().getTableStatus());
    }
    
    
    log.trace("Entering createDatabaseTablesIfNotExist()");
    CreateTableRequest request2 = dbMapper
        .generateCreateTableRequest(com.spring.fullStack.marketDataService.Metal.class)
        .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
    try {
      DescribeTableResult result = dynamoDB.describeTable(request2.getTableName());
      log.info("Table status {}, {}", request2.getTableName(), result.getTable().getTableStatus());
    } catch (ResourceNotFoundException expectedException) {
      CreateTableResult result = dynamoDB.createTable(request2);
      log.info("Table creation triggered {}, {}", request2.getTableName(), result.getTableDescription().getTableStatus());
    }
  }

}