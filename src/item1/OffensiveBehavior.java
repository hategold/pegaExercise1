package item1;

import java.util.List;

public interface OffensiveBehavior {

	void attackFishs(List<AbstractFish> fishList); //考慮未來避免 dosomething()的狀況 每隻魚的行為可能更複雜

	boolean checkAttackCondition(AbstractFish fish);

	void reduceVictimHealthDegree(AbstractFish fish);

}