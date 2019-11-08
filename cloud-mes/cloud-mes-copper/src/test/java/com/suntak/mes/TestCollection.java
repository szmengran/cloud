package com.suntak.mes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.suntak.mes.entity.TMesCopperRule;

public class TestCollection {

	@Test
	public void forEachTest() {
		List<TMesCopperRule> list = new ArrayList<>();
		for (int i=0; i<3; i++) {
			TMesCopperRule tMesCopperRule = new TMesCopperRule();
			tMesCopperRule.setId(i+"");
			list.add(tMesCopperRule);
		}
		Timestamp createstamp = new Timestamp(System.currentTimeMillis());
		list.forEach(rule -> {
			rule.setCreatestamp(createstamp);
			rule.setCreate_by("***");
		});
		list.forEach(rule -> {
			System.out.println(rule.getId()+rule.getCreatestamp()+rule.getCreate_by());
		});
	}
}
