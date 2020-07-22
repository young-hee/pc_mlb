package com.plgrim.ncp.framework.systems;

import java.util.Date;

/**
 * System.currentTimeMillis 은 mock 객체화가 되지 않기 때문에 추상화 계층 추가
 * 
 * @author narusas
 *
 */
public interface SystemClock {
	long currentTimeMillis();

	Date nowInMinutes();

	Date nowInHours();

	Date nowInDays();

	Date now();

	boolean isNotOver(long timestamp, long interval);

	boolean isOver(long timestamp, long interval);

	boolean isNotOver(long start, long end, long interval);

	boolean isOver(long start, long end, long interval);

}
