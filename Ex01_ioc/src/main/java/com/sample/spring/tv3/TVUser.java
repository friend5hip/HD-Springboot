package com.sample.spring.tv3;

public class TVUser {

	public static void main(String[] args) {
		BeanContainer c = new BeanContainer();
		
		TV tv = (TV) c.getBean(args[0]);
		tv.turnOn();
	}

}
