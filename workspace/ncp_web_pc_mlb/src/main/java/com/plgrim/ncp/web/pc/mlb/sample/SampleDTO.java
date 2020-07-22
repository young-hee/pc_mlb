package com.plgrim.ncp.web.pc.mlb.sample;

import java.util.Collection;

import lombok.Data;

@Data
public class SampleDTO {
	String title;
	
	Collection<SampleProductDTO> sampleList;
}
