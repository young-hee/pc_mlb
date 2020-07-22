package com.plgrim.ncp.framework.partitiondata;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import lombok.Data;

@Data
public abstract class PartitionDataAwareProcessor<T> implements ItemProcessor<T, PartitionData> {
	protected PartitionData data;
	protected StepExecution stepExecution;
	protected String key = "PARTITION_NO";
	protected String[] headers;
	private String partitionNo;

	@Override
	public PartitionData process(T item) throws Exception {
		if (data == null) {
			partitionNo = readFromJobExecutionContext();
			if (ArrayUtils.isNotEmpty(headers)) {
				data = PartitionData.header(partitionNo, headers);
			}
		}
		beforeConvert(data, item);
		data = data.nextData(convert(item));
		afterConvert(data, item);
		return data;
	}

	public void beforeConvert(PartitionData beforeData, T item) {

	}

	protected abstract String[] convert(T item);

	protected void afterConvert(PartitionData afterData, T item) {

	}

	protected String readFromJobExecutionContext() {
		return partitionNo == null ? stepExecution.getJobExecution().getExecutionContext().getString(key) : partitionNo;
	}

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
}
