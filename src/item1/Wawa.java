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

		for (int i = 0; i < fishList.size(); i++) {
			randomAttackFish(fishList.get(i));
		}
	};

	private void randomAttackFish(AbstractFish fish) {
		if (Math.random() > 0.5) {
			attackFish(fish);
		}
	}

	public void attackFish(AbstractFish fish) {//overloading ���Ӱ����Ʊ����e���Ӥ@��  ��@��rule�P�_�n���n�A�]�A�F���v�򫽫������P�_

		if (!(fish instanceof Wawa)) {

			System.out.print(this.getName() + " attact " + fish.getName() + " ");
			int suf = this.getSpeed() - fish.getSpeed();
			if (suf < 1) {
				suf = 1;
			}

			fish.setHealthDegree(fish.getHealthDegree() - (int) suf * 3);
			System.out.println(fish.getName() + "'s Health Degree is " + String.valueOf(fish.getHealthDegree()) + " Now!");

		}
	};

	@Override
	public void swim() {
		System.out.println("-----------" + getName() + " swim like a baby.");
	}
}
