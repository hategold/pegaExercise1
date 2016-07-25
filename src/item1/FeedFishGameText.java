package item1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import item1.AssignmentsInfo.Difficulty;
import item1.AssignmentsInfo.Priority;
import item1.AssignmentsInfo.State;

@AssignmentsInfo(
	coder = "Tim",
	priority = Priority.HIGH,
	state = State.CHECKING,
	lastModified = "2016/7/18",
	difficulty = Difficulty.HARD)
public class FeedFishGameText {

	private List<AbstractFish> fishList;//宣告儘量用interface

	private Aquarium aq;

	private int ffood;

	@SuppressWarnings("unused")
	private static enum UserGameCmd {
		CHECK_STATUS(1), PUT_FOOD(2), REFRESH_WATER(3), NONE(-1);

		private final int value;

		private UserGameCmd(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static UserGameCmd valueOf(int value) {
			switch (value) {
				case 1:
					return CHECK_STATUS;
				case 2:
					return PUT_FOOD;
				case 3:
					return REFRESH_WATER;
				default:
					return NONE;
			}
		}
	}

	FeedFishGameText() {
		aq = new Aquarium();
		fishList = new ArrayList<AbstractFish>();
	}

	public void startGame() {
		Thread ut = new Thread(new UserHandler());
		Thread ft = new Thread(new FishActionHandler());
		ut.start();
		ft.start();
	}

	public void buildFishList() {
		fishList.add(new Guppy("Guppy", 8, AbstractFish.GenderEnum.FEMALE, 100, Guppy.PatternName.COBRA));
		fishList.add(new RedNeon("RedNeon", 3, AbstractFish.GenderEnum.MALE, 100)); //fish builder 拉出來  method chain);
		fishList.add(new Wawa("Wawa", 5, AbstractFish.GenderEnum.MALE, 80));

		//		Fish f2 = new RedNeon("RedNeon", 3, Fish.GenderEnum.MALE, 100);//fish builder 拉出來  method chain
		//		Wawa f3 = new Wawa("Wawa", 5, Fish.GenderEnum.MALE, 80);
		//OLD version
	}

	public void setGame() {

		buildFishList();
		System.out.print("放入魚隻 ");

		for (AbstractFish fish : fishList) {
			System.out.print(fish.getName() + " ");
		}

		System.out.println("遊戲開始");
	}

	public void checkWater() {
		System.out.println(String.valueOf(aq.getNo2()));
	}

	public String getUserInput(String msg2User) {

		String userCmd = "";
		System.out.println(msg2User);

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			userCmd = br.readLine();

			if (userCmd.length() == 0) {
				return "";
			}

		} catch (IOException | NumberFormatException e) {
			System.out.println("Exception: " + e);
		}

		return userCmd;
	}

	public int getFfood() {
		return ffood;
	}

	public void setFfood(int ffood) {
		if (ffood < 0) {
			this.ffood = 0;
			return;
		}
		this.ffood = ffood;

	}

	private void checkGameStatus(List<AbstractFish> fishList) {//改list & 這邊不需要處理extend
		System.out.println("現在魚缸中的有害物質佔這麼多:" + aq.getNo2());
		System.out.println("現在魚缸中的殘餘飼料有:" + ffood);
		for (AbstractFish fish : fishList) {
			System.out.println(fish.getName() + "的健康度是" + String.valueOf(fish.getHealthDegree()));
		}
	}

	private boolean checkFishAlive(AbstractFish fish) {
		if (fish.getHealthDegree() < 0) {
			return false;
		}
		return true;
	}

	private void cleanWaterFood() {
		synchronized (this) {
			aq.refleshWater();
			ffood = (int) (ffood * 0.8);
		}
	}

	private void userPutFood() {

		String msg = "要加入多少飼料呢? 輸入非數字則取消動作";
		try {
			int userCmd = Integer.valueOf(getUserInput(msg));
			synchronized (this) {
				ffood += userCmd;
			}
			System.out.printf("投入%d顆飼料。\n", userCmd);

		} catch (NumberFormatException e) {
			System.out.println("飼料只能輸入數字，回到動作選單");
		}
	}//可能會想取消輸入

	private UserGameCmd getUserGameCmd() {
		try {
			String msg = "你可以進行三個動作 :1.確認魚隻/環境狀態  2.加飼料 3.換水";
			int inputCmd = Integer.valueOf(getUserInput(msg));

			return UserGameCmd.valueOf(inputCmd);
		} catch (NumberFormatException e) {
			return UserGameCmd.NONE;
		}
	}

	class UserHandler implements Runnable {

		@Override
		public void run() {
			UserGameCmd userCmd;

			while (fishList.size() > 0) {

				userCmd = getUserGameCmd();

				switch (userCmd) {
					case CHECK_STATUS://enum
						checkGameStatus(fishList);//命名有點怪怪
						break;
					case PUT_FOOD:
						userPutFood();
						break;
					case REFRESH_WATER:
						cleanWaterFood();
						break;
					default:
						System.out.println("只能輸入數字1~3");
						break;
				}
			}
		}
	}

	class FishActionHandler implements Runnable {

		private int gameRound = 0;

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				while (fishList.size() > 0) {// every round

					Iterator<AbstractFish> itFishs = fishList.iterator();//iterate list and delete member inside.

					while (itFishs.hasNext()) { //every fish
						AbstractFish fish = itFishs.next();//要做的事情 拉一個method
						fish.feelWater(aq.getNo2());
						synchronized (this) {
							if (removeDeadFish(fish, itFishs)) {
								continue;
							}
						}
						if (canFishMove(fish)) {
							oneRoundMove(fish);
						}
						Thread.sleep(1000);
					}
					aq.degradeWater(ffood);//degrade water every round
					gameRound += 1;
					Thread.sleep(1000);
				}
				endThreadMsg();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private boolean removeDeadFish(AbstractFish fish, Iterator<AbstractFish> itFishs) {
			if (!checkFishAlive(fish)) {
				itFishs.remove();
				System.out.println(fish.getName() + "屎調惹，你還有" + fishList.size() + "隻魚");
				return true;
			}
			return false;
		}

		private void endThreadMsg() {
			System.out.println("魚都死了QQ，你這個爛主人");
		}

		private void oneRoundMove(AbstractFish fish) {

			fish.swim();
			synchronized (this) {
				fish.eatFood(checkFood2Eat(fish));
			}

			if (fish instanceof OffensiveBehavior) {//detail 應該用interface 比較泛用
				synchronized (this) {
					((OffensiveBehavior) fish).attackFishs(fishList);
				}
			}
		}

		private int checkFood2Eat(AbstractFish fish) {
			int eatenFood = fish.askFood();
			if (eatenFood > ffood) {
				eatenFood = ffood;
				ffood = 0;
			} else {
				ffood -= eatenFood;
			}
			return eatenFood;
		}

		private boolean canFishMove(AbstractFish fish) {
			if (gameRound % fish.getCooldownTime() == 0) {
				return true;
			}
			return false;
		}

	}
}
