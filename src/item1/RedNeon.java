package item1;

public class RedNeon extends Fish {
	
	RedNeon(String name, int speed, Fish.genderEnum gender, int hDegree){
		super(name, speed, gender, hDegree);
	}
	
	public void display(){
		System.out.println(getName()+ "�i�}�F���_");
	}
	
	@Override
	public void swim(){
		System.out.println("-----------"+getName()+" swim like a boss.");
	}
	

}
