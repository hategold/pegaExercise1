package item1;


public abstract class Fish {

	private String name;

	private int speed;

	public static enum genderEnum {
		MALE, FEMALE, UNKNOWN
	};

	private genderEnum gender;

	private int hDegree;

	Fish(String name, int speed, genderEnum gender, int hDegree) {
		this.name = name;
		this.speed = speed;
		this.setGender(gender);
		this.hDegree = hDegree;
	}

	public int askFood() {
		return (int) (speed);
	}

	public void eatFood(int ffood) {
		if (ffood > 0) {

			sethDegree(hDegree + ffood * 2);
			System.out.println(name + " eat " + String.valueOf(ffood));

		}
	}

	public void feelWater(int NO2) {
		sethDegree(hDegree = hDegree - NO2 / 10 - 1);
	}

	public abstract void swim();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSpeed() {
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

	public int gethDegree() {
		return hDegree;
	}

	public void sethDegree(int hDegree) {
		if (hDegree > 100) {
			this.hDegree = 100;
		} else {
			this.hDegree = hDegree;
		}
	}

	public genderEnum getGender() {
		return gender;
	}

	public void setGender(genderEnum gender) {
		this.gender = gender;
	}

	@Deprecated
	public void RunningOnLand() {
		System.out.println("是條魚卻會跑，兩棲類逆?");

	}
}
