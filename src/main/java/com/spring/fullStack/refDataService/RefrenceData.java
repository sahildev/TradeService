package com.spring.fullStack.refDataService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RefrenceData {
	
	private String refCode;
	private String refValue;
	private String refDescription;
	

}
