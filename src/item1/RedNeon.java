package item1;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.MEDDIUM,
	state = State.FINISH,
	lastModified = "2016/7/15")
public class RedNeon extends Fish {

	RedNeon(String name, int speed, Fish.GenderEnum gender, int healthDegree) {
		super(name, speed, gender, healthDegree);
	}

	public void display() {
		System.out.println(getName() + "®i¶}¤F³½Å_");
	}

	@Override
	public void swim() {
		System.out.println("-----------" + getName() + " swim like a boss.");
	}

}
