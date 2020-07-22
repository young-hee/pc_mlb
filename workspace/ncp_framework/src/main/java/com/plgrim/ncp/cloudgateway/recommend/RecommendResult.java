package com.plgrim.ncp.cloudgateway.recommend;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendResult {
	String recoStatus;
	Object result;
}
