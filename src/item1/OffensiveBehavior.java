package item1;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface OffensiveBehavior {

	void attackFishs(List<AbstractFish> fishList); //�Ҽ{�����קK dosomething()�����p �C�������欰�i������

	void attackFishs(List<AbstractFish> fishList, Predicate<AbstractFish> attackTester, Consumer<AbstractFish> attackEffect);

	boolean checkAttackCondition(AbstractFish fish);

	boolean checkAttackCondition(AbstractFish fish, Predicate<AbstractFish> attackTester);

	void reduceVictimHealthDegree(AbstractFish fish);

}