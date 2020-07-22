package com.plgrim.ncp.framework.partitiondata;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PartitionNoDecideTasklet implements Tasklet {
	String PARTITION_NO_KEY = "PARTITION_NO";
	String prefix;

	// 시간 기반으로 id 를 만들어낼때 동일시간으로 인해 동일 ID가 만들어지는 것을 방지하기 위한 susfix
	static Integer susfix = 0;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		String partitionNo = decicePartitionNo(chunkContext);
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put(PARTITION_NO_KEY, partitionNo);
		return null;
	}

	private String decicePartitionNo(ChunkContext chunkContext) {
		if (chunkContext.getStepContext().getJobParameters().containsKey(PARTITION_NO_KEY)) {
			String partitionNo = (String) chunkContext.getStepContext().getJobParameters().get(PARTITION_NO_KEY);
			log.info("Given Partion No: {}", partitionNo);
			return partitionNo;
		} else {
			String partitionNo = generatePartitionNo();
			log.info("Generated Partion No: {}", partitionNo);
			return partitionNo;
		}
	}

	protected String generatePartitionNo() {
		adjustSusfix();
		return prefix + "_" + System.currentTimeMillis() + "" + susfix;
	}

	void adjustSusfix() {
		synchronized (susfix) {
			if (susfix > 10) {
				susfix = 0;
			}
		}
	}
}
