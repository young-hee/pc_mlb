package com.plgrim.ncp.cloudgateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudGatewayController {
	@RequestMapping("/")
	SampleResult home() {
		return new SampleResult("Hello World!", 15);
	}
}
