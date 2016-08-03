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

	private int healthDegree; //ぃ┤

	private static final int HEALTHDEGREE_UPPERBOUND = 100;

	private static final int COOLDOWNTIME_LOWERBOUND = 1;

	private static final int COOLDOWNTIME_UPPERBOUND = 10;

	public AbstractFish() {
	}

	public AbstractFish(String name) {
		this.name = name;
	}

	public AbstractFish(String name, int cooldownTime, GenderEnum gender, int healthDegree) {
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
	public void feelWater(int no2) {	//NO2 э糶 琌把计
		setHealthDegree(healthDegree = healthDegree - no2 / 10 - 1);
	}

	@Override
	public abstract void swim();

	public String getName() {
		return name;
	}

	public AbstractFish setName(String name) {
		this.name = name;
		return this;
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	public AbstractFish setCooldownTime(int cooldownTime) {
		if (cooldownTime < COOLDOWNTIME_LOWERBOUND) { //场だ穦﹚竡盽计ノゅボゑ耕参 magic number 盽计癬ン
			this.cooldownTime = COOLDOWNTIME_LOWERBOUND;
			return this;
		}
		if (cooldownTime > COOLDOWNTIME_UPPERBOUND) {
			this.cooldownTime = COOLDOWNTIME_UPPERBOUND;
			return this;
		}
		this.cooldownTime = cooldownTime;
		return this;
	}

	public int getHealthDegree() {
		return healthDegree;
	}

	public AbstractFish setHealthDegree(int healthDegree) {
		if (healthDegree > HEALTHDEGREE_UPPERBOUND) {
			this.healthDegree = HEALTHDEGREE_UPPERBOUND;
			return this;
		}

		this.healthDegree = healthDegree;
		return this;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public AbstractFish setGender(GenderEnum gender) {
		this.gender = gender;
		return this;
	}

	@Override
	@Deprecated
	public void runningOnLand() {
		System.out.println("琌兵辰玱穦禲ㄢ聪摸癴?");
	}
}
