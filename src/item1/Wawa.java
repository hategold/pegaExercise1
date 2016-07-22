package item1;

import java.util.List;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.MEDDIUM,
	state = State.FINISH,
	lastModified = "2016/7/15")
public class Wawa extends AbstractFish {

	Wawa(String name, int speed, GenderEnum gender, int healthDegree) {
		super(name, speed, gender, healthDegree);
	}

	public void attackFishs(List<? extends AbstractFish> fishList) {

		for (AbstractFish fish : fishList) {
			if (checkAttackCondition(fish)) {
				reduceVictimHealthDegree(fish);
			}
		}
	};

	private boolean checkAttackCondition(AbstractFish fish) {
		if (Math.random() > 0.5 && !(fish instanceof Wawa)) {
			return true;
		}
		return false;
	}

	public void reduceVictimHealthDegree(AbstractFish fish) {//overloading 應該做的事情內容不太一樣  抽一個rule判斷要不要，包括了機率跟娃娃魚的判斷

		System.out.print(this.getName() + " attact " + fish.getName() + " ");
		int suf = this.getSpeed() - fish.getSpeed();
		if (suf < 1) {

			suf = 1;
		}
		fish.setHealthDegree(fish.getHealthDegree() - (int) suf * 3);
		System.out.println(fish.getName() + "'s Health Degree is " + String.valueOf(fish.getHealthDegree()) + " Now!");

	};

	@Override
	public void swim() {
		System.out.println("-----------" + getName() + " swim like a baby.");
	}
}
