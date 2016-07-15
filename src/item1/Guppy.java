package item1;

public class Guppy extends Fish {

	public static enum PatternName {
		TUXEDO, MOSAIC, COBRA, OTHERS
	};

	private PatternName pattern;

	Guppy(String name, int speed, genderEnum gender, int hDegree, PatternName pattern) {
		super(name, speed, gender, hDegree);
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
