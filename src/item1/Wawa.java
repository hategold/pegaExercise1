package item1;

import java.util.ArrayList;

public class Wawa extends Fish {

	Wawa(String name, int speed, genderEnum gender, int hDegree) {
		super(name, speed, gender, hDegree);
	}

	public void attackFish(ArrayList<? extends Fish> fishList) {

		for (int i = 0; i < fishList.size(); i++) {
			randomAttackFish(fishList.get(i));
		}
	};

	private void randomAttackFish(Fish fish) {
		if (Math.random() > 0.5) {
			attackFish(fish);
		}
	}

	public void attackFish(Fish fish) {

		if (!(fish instanceof Wawa)) {

			System.out.print(this.getName() + " attact " + fish.getName() + " ");
			float suf = this.getSpeed() - fish.getSpeed();
			if (suf < 1) {
				suf = (float) 1;
			}

			fish.sethDegree(fish.gethDegree() - (int) suf * 3);
			System.out.println(fish.getName() + "'s Health Degree is " + String.valueOf(fish.gethDegree()) + " Now!");

		}
	};

	@Override
	public void swim() {
		System.out.println("-----------" + getName() + " swim like a baby.");
	}
}
