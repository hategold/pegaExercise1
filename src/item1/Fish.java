package item1;

import item1.AssignmentsInfo.*;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.HIGH,
	state = State.FINISH,
	lastModified = "2016/7/15")
public abstract class Fish {

	private String name;

	private int speed;// lower speed => higher cool down time. so, lower speed = faster

	public static enum GenderEnum {
		MALE, FEMALE, UNKNOWN
	};

	private GenderEnum gender;

	private int healthDegree;//不怕他長

	Fish(String name, int speed, GenderEnum gender, int healthDegree) {
		this.name = name;
		this.speed = speed;
		this.setGender(gender);
		this.healthDegree = healthDegree;
	}

	public int askFood() {
		return speed;
	}

	public void eatFood(int ffood) {
		if (ffood > 0) {

			setHealthDegree(healthDegree + ffood * 2);
			System.out.println(name + " eat " + String.valueOf(ffood));

		}
	}

	public void feelWater(int no2) {//NO2 泉小寫 只是參數
		setHealthDegree(healthDegree = healthDegree - no2 / 10 - 1);
	}

	public abstract void swim();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		if (speed < 0) {
			this.speed = 0;
		} else if (speed > 10) {
			this.speed = 10;
		} else {
			this.speed = speed;
		}
	}

	public int getHealthDegree() {
		return healthDegree;
	}

	public void setHealthDegree(int healthDegree) {
		if (healthDegree > 100) {
			this.healthDegree = 100;
		} else {
			this.healthDegree = healthDegree;
		}
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	@Deprecated
	public void RunningOnLand() {
		System.out.println("是條魚卻會跑，兩棲類逆?");

	}
}
