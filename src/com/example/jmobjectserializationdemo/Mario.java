package com.example.jmobjectserializationdemo;

import java.io.Serializable;

public class Mario implements Serializable {

	private int totatlLife;
	private String name = "Default";
	private Boolean isPowerUpActivated;
	private transient int state;

	public int getToatlLife() {
		return totatlLife;
	}

	public void setToatlLife(int toatlLife) {
		this.totatlLife = toatlLife;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPowerUpActivated() {
		return isPowerUpActivated;
	}

	public void setIsPowerUpActivated(Boolean isPowerUpActivated) {
		this.isPowerUpActivated = isPowerUpActivated;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
