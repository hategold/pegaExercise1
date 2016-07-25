package item1;

import java.util.List;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.MEDDIUM,
	state = State.FINISH,
	lastModified = "2016/7/15")
public class Wawa extends AbstractFish implements OffensiveBehavior {

	Wawa(String name, int cooldownTime, GenderEnum gender, int healthDegree) {
		super(name, cooldownTime, gender, healthDegree);
	}

	@Override
	public void attackFishs(List<? extends AbstractFish> fishList) {

		for (AbstractFish fish : fishList) {
			if (checkAttackCondition(fish)) {
				reduceVictimHealthDegree(fish);
			}
		}
	};

	@Override
	public boolean checkAttackCondition(AbstractFish fish) {
		if (Math.random() > 0.5 && !(this.getClass().isInstance(fish))) {
			return true;
		}
		return false;
	}

	@Override
	public void reduceVictimHealthDegree(AbstractFish fish) {

		System.out.print(this.getName() + " attact " + fish.getName() + " ");
		int suf = this.getCooldownTime() - fish.getCooldownTime();
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
