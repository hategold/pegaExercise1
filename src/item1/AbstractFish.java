package item1;

import item1.AssignmentsInfo.*;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.HIGH,
	state = State.FINISH,
	lastModified = "2016/7/15")
public abstract class AbstractFish implements FishBehavior {

	private String name;

	private int cooldownTime;	//lower cooldownTime = faster

	public static enum GenderEnum {
		MALE, FEMALE, UNKNOWN
	};

	private GenderEnum gender;

	private int healthDegree; //不怕他長

	AbstractFish(String name, int cooldownTime, GenderEnum gender, int healthDegree) {
		this.name = name;
		this.cooldownTime = cooldownTime;
		this.setGender(gender);
		this.healthDegree = healthDegree;
	}

	@Override
	public int askFood() {
		return cooldownTime;
	}

	@Override
	public void eatFood(int ffood) {
		if (ffood > 0) {

			setHealthDegree(healthDegree + ffood * 2);
			System.out.println(name + " eat " + String.valueOf(ffood));
		}
	}

	@Override
	public void feelWater(int no2) {	//NO2 改小寫 只是參數
		setHealthDegree(healthDegree = healthDegree - no2 / 10 - 1);
	}

	@Override
	public abstract void swim();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	public void setCooldownTime(int cooldownTime) {
		if (cooldownTime < 0) {
			this.cooldownTime = 0;
			return;
		}
		if (cooldownTime > 10) {
			this.cooldownTime = 10;
			return;
		}
		this.cooldownTime = cooldownTime;
	}

	public int getHealthDegree() {
		return healthDegree;
	}

	public void setHealthDegree(int healthDegree) {
		if (healthDegree > 100) {
			this.healthDegree = 100;
			return;
		}

		this.healthDegree = healthDegree;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	@Override
	@Deprecated
	public void RunningOnLand() {
		System.out.println("是條魚卻會跑，兩棲類逆?");
	}
}
