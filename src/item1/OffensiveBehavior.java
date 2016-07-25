package item1;

import java.util.List;

public interface OffensiveBehavior {

	void attackFishs(List<AbstractFish> fishList); //�Ҽ{�����קK dosomething()�����p �C�������欰�i������

	boolean checkAttackCondition(AbstractFish fish);

	void reduceVictimHealthDegree(AbstractFish fish);

}