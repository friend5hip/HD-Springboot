package com.sample.spring.tv2;

public class SamsungTV implements TV {
	public SamsungTV() {
		System.out.println("= SamsungTV =");
	}

	@Override
	public void turnOn() {
		System.out.println("SamsungTV ON");
	}

	@Override
	public void turnOff() {
		System.out.println("SamsungTV OFF");
	}

	@Override
	public void volumeUp() {
		System.out.println("SamsungTV VOLUME UP");
	}

	@Override
	public void volumeDown() {
		System.out.println("SamsungTV VOLMUME DOWN");
	}

}
