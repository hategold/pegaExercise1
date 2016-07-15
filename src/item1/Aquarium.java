package item1;

public class Aquarium {
	
	private int NO2;
	
	Aquarium(){
		
		setNO2(0);
		
	}
	
	public void refleshWater(){
		setNO2(getNO2()/2);
	}
	
	public void checkFood(int food){
		setNO2(getNO2() + food/2);
	}
	
	public int getNO2() {
		return NO2;
	}

	public void setNO2(int NO2) {
		if (NO2 > 100){
			this.NO2 = 100;
		}else if(NO2 < 0){
			this.NO2 = 0;
		}else{
		this.NO2 = NO2;
		}
	}
}
