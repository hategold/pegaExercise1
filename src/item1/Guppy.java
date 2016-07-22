package item1;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.LOW,
	state = State.FINISH,
	lastModified = "2016/7/15")
public class Guppy extends AbstractFish {

	public static enum PatternName {
		TUXEDO, MOSAIC, COBRA, OTHERS
	};

	private PatternName pattern;

	Guppy(String name, int speed, GenderEnum gender, int healthDegree, PatternName pattern) {
		super(name, speed, gender, healthDegree);
		this.pattern = pattern;
	}

	@Override
	public void swim() {
		System.out.println("-----------" + getName() + " swim like a angel.");
	}

	public PatternName getPattern() {
		return pattern;
	}

	public void setPattern(PatternName pattern) {
		this.pattern = pattern;
	}
}
