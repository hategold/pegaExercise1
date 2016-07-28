package item1;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
	public void attackFishs(List<AbstractFish> fishList) {

		for (AbstractFish fish : fishList) {
			if (checkAttackCondition(fish)) {
//			if (checkAttackCondition(fish, f -> f.getHealthDegree() < 50 ? Math.random() < 0.3 : Math.random() < 0.5)) {
				reduceVictimHealthDegree(fish);
			}
		}
	};

	@Override
	public void attackFishs(List<AbstractFish> fishList, Predicate<AbstractFish> attackTester, Consumer<AbstractFish> attackEffect) {

		for (AbstractFish fish : fishList) {
			if (attackTester.test(fish)) {
				attackEffect.accept(fish);;
			}
		}
	};

	@Override
	public boolean checkAttackCondition(AbstractFish fish, Predicate<AbstractFish> attackTester) {

		return !(this.getClass().isInstance(fish)) && attackTester.test(fish) ;
	}

	@Override
	public boolean checkAttackCondition(AbstractFish fish) {

		return (Math.random() > 0.5 && !(this.getClass().isInstance(fish)));

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
