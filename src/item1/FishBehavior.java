package item1;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
		coder = "Tim",
		priority = Priority.HIGH,
		state = State.FINISH,
		lastModified = "2016/7/22")
public interface FishBehavior {

	int askFood();

	void eatFood(int ffood);

	void feelWater(int no2);

	void swim();

	void RunningOnLand();

}