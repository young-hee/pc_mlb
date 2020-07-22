package com.plgrim.ncp.framework.commons;

import java.util.Collection;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;

import com.plgrim.ncp.framework.utils.JsonUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

@Slf4j
public class CollectionServiceTest {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	CollectionService collectionService;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	@Before
	public void begin() {
		collectionService = new CollectionService();
	}

//	 @Test
	public void multiSet() throws Exception {

		/*
		 * HashMultiSet 순서를 보장하지 않는다. 중복은 허용 한다. (주로 데이터 갯수를 카운팅 할때 사용 한다.)
		 */
		Multiset<String> multiSet = HashMultiset.create();
		multiSet.add("Realtree APG");
		multiSet.add("Realtree Hardwoods HD");
		multiSet.add("Realtree APG");
		multiSet.add("Realtree Hardwoods Green HD");
		multiSet.add("Mossy Oak New Break-Up");
		multiSet.add("Realtree APG");

		// 동일한 엘리먼트 전체 갯수
		int numberOfRealTrees = multiSet.count("Realtree APG");
		log.info("" + numberOfRealTrees);

		// 전체 사이즈
		numberOfRealTrees = multiSet.size();
		log.info("" + numberOfRealTrees);

		// 전체 리스트 조회
		log.info(JsonUtil.marshallingJsonWithPretty(collectionService
		        .iteratortoList(multiSet.iterator())));
		log.info("------------------");
	}

//	 @Test
	public void multiMap() throws Exception {

		/*
		 * ArrayListMultimap 중복을 허용하며, 순서도 보장 한다.
		 */
		Multimap<String, String> myMultimap = ArrayListMultimap.create();
		myMultimap.put("Fruits", "Bannana");
		myMultimap.put("Fruits", "Apple");
		myMultimap.put("Fruits", "Pear");
		myMultimap.put("Vegetables", "Carrot");

		// 전체 엘리먼트 갯수
		int size = myMultimap.size();
		log.info("" + size);

		// Fruits 요소 값들을 리스트 형태로 리턴 한다.
		Collection<String> fruits = myMultimap.get("Fruits");
		log.info(JsonUtil.marshallingJsonWithPretty(collectionService
		        .collectionToList(fruits)));

		// 전체 값을 리턴 한다.
		Collection<String> results = myMultimap.values();
		log.info(JsonUtil.marshallingJsonWithPretty(collectionService
		        .collectionToList(results)));

		// 엘리먼트 제거 하기
		myMultimap.removeAll("Fruits");
		log.info(JsonUtil.marshallingJsonWithPretty(collectionService
		        .collectionToList(fruits)));
		
		
	}

//	@Test
	public void biMap() throws Exception {

		/*
		 * 중복을 허용하지 않고 , 순서도 보장하지 않는다. key도 유일해야 하며, value 값도 유일 해야 한다.
		 */
		BiMap<String, Integer> bimap = HashBiMap.create();

		bimap.put("bratwurst", 1);
		bimap.put("drinking fountain", 2);
		bimap.put("that", 3);
		bimap.put("alright", 4);
		bimap.put("soda", 5);

		// 리스트 출력
		log.info(JsonUtil.marshallingJsonWithPretty(collectionService
		        .setToList(bimap.entrySet())));

		// bimap 리버스
		BiMap<Integer, String> reverse = bimap.inverse();
		log.info(JsonUtil.marshallingJsonWithPretty(collectionService
		        .setToList(reverse.entrySet())));
	}

	@Test
	public void table() throws Exception {

		Table<Integer, String, Integer> table = HashBasedTable.create();
		table.put(1, "Filthy 50", 100);
		table.put(1, "Fran", 101);
		table.put(1, "The Seven", 102);
		table.put(1, "Murph", 103);
		table.put(1, "The Ryan", 104);
		table.put(1, "King Kong", 105);

		table.put(2, "Filthy 50", 106);
		table.put(2, "Fran", 107);
		table.put(2, "The Seven", 108);
		table.put(2, "Murph", 109);
		table.put(2, "The Ryan", 110);
		table.put(2, "King Kong", 111);

		// row 출력
		Map<String, Integer> map = table.row(1);
		log.info(JsonUtil.marshallingJsonWithPretty(map));

		//column 출력
		Map<Integer, Integer>  mapc = table.column("Filthy 50");
		log.info(JsonUtil.marshallingJsonWithPretty(mapc));
		
		log.info(JsonUtil.marshallingJsonWithPretty(table.columnKeySet()));
		
	}
	
	

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
