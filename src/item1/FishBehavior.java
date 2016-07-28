package item1;

import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.HIGH,
	state = State.FINISH,
	lastModified = "2016/7/22")
public interface FishBehavior {

	public int askFood();

	public void eatFood(int ffood);

	public void feelWater(int no2);

	public void swim();

	public void runningOnLand();

}