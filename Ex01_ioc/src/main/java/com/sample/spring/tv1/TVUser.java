package com.sample.spring.tv1;

public class TVUser {
	public static void main(String[] args) {
		LGTV tv = new LGTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}
}
