package item1;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface OffensiveBehavior {

	void attackFishs(List<AbstractFish> fishList); //考慮未來避免 dosomething()的狀況 每隻魚的行為可能更複雜

	void attackFishs(List<AbstractFish> fishList, Predicate<AbstractFish> attackTester, Consumer<AbstractFish> attackEffect);

	boolean checkAttackCondition(AbstractFish fish);

	boolean checkAttackCondition(AbstractFish fish, Predicate<AbstractFish> attackTester);

	void reduceVictimHealthDegree(AbstractFish fish);

}