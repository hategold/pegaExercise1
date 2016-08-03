package item1;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.MEDDIUM,
	state = State.FINISH,
	lastModified = "2016/7/15")
public class RedNeon extends AbstractFish {

	public RedNeon(String name) {
		super(name);
	}

	public RedNeon(String name, int cooldownTime, AbstractFish.GenderEnum gender, int healthDegree) {
		super(name, cooldownTime, gender, healthDegree);
	}

	public void display() {
		System.out.println(getName() + "®i¶}¤F³½Å_");
	}

	@Override
	public void swim() {
		System.out.println("-----------" + getName() + " swim like a boss.");
		display();
	}

}
