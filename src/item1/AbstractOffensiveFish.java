package item1;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractOffensiveFish extends AbstractFish implements OffensiveBehavior {

	public AbstractOffensiveFish(String name) {
		super(name);
	}

	public AbstractOffensiveFish(String name, int cooldownTime, GenderEnum gender, int healthDegree) {
		super(name, cooldownTime, gender, healthDegree);
	}

	@Override
	public void attackFishs(List<AbstractFish> fishList) {

		attackFishs(fishList, f -> checkAttackCondition(f), f -> reduceVictimHealthDegree(f));

	}

	@Override
	public void attackFishs(List<AbstractFish> fishList, Predicate<AbstractFish> attackTester, Consumer<AbstractFish> attackEffect) {

		for (AbstractFish fish : fishList) {
			if (attackTester.test(fish)) {
				attackEffect.accept(fish);;
			}
		}
	};

}
