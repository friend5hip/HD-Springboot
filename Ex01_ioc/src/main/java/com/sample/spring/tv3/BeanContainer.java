package com.sample.spring.tv3;

public class BeanContainer {

	public Object getBean(String id) {
		if (id.equals("LG")) {
			return new LGTV();
		} else if (id.equals("Samsung")) {
			return new SamsungTV();
		}
		return null;
	}

}
