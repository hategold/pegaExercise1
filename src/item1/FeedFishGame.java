package item1;

import javax.swing.JFrame;

public class FeedFishGame {
	
	FeedFishGame(){}
	public void startGame(){
		FishGameFrame fishGameFrame = new FishGameFrame();
		fishGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fishGameFrame.setSize(800,600);
		fishGameFrame.pack();
		fishGameFrame.setVisible(true);
	}
	
}


