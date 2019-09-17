package com.suntak.cloud.haiwd;

import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTimeInMillis() / 1000);
		System.out.println(calendar.getTimeInMillis() / 1000 - 86400);
		System.out.println(calendar.get(Calendar.DATE));
	}
}
