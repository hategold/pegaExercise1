package item1;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface OffensiveBehavior {

	public void attackFishs(List<AbstractFish> fishList);

	public void attackFishs(List<AbstractFish> fishList, Predicate<AbstractFish> attackTester, Consumer<AbstractFish> attackEffect);

	public boolean checkAttackCondition(AbstractFish fish);

	public boolean checkAttackCondition(AbstractFish fish, Predicate<AbstractFish> attackTester);

	public void reduceVictimHealthDegree(AbstractFish fish);

}