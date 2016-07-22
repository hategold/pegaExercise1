package item1;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.MEDDIUM,
	state = State.FINISH,
	lastModified = "2016/7/15")
public class Aquarium {

	private int no2;

	Aquarium() {
		setNo2(0);
	}

	public void refleshWater() {
		setNo2(getNo2() / 2);
	}

	public void degradeWater(int food) {//naming variables, method meaningful
		setNo2(getNo2() + food / 2);
	}

	public int getNo2() {
		return no2;
	}

	public void setNo2(int no2) {
		if (no2 > 100) {
			this.no2 = 100;
			return;
		}
		if (no2 < 0) {
			this.no2 = 0;
			return;
		}
		this.no2 = no2;

	}
}
