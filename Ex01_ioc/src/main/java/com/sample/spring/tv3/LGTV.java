package com.sample.spring.tv3;

public class LGTV implements TV {
	public LGTV() {
		System.out.println("= LGTV =");
	}

	@Override
	public void turnOn() {
		System.out.println("LGTV ON");
	}

	@Override
	public void turnOff() {
		System.out.println("LGTV OFF");
	}

	@Override
	public void volumeUp() {
		System.out.println("LGTV VOLUME UP");
	}

	@Override
	public void volumeDown() {
		System.out.println("LGTV VOLUME DOWN");
	}
}
