package com.spring.fullStack.tradeService;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@DynamoDBTable(tableName = "Trade")
@Data
@Getter
@Setter
public class Trade {

	@DynamoDBHashKey(attributeName = "tradeId")
	@DynamoDBAutoGeneratedKey
	private String tradeId;
	private String tradeName;
	private int quantity;
	private String commodity;
	private String location;
	private String counterparty;

	public Trade withName(String tradeName) {

		setTradeName(tradeName);
		return this;
	}
	
	public Trade withId(String tradeId) {

		setTradeId(tradeId);
		return this;
	}

}
