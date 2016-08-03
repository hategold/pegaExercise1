package item1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		CHECK_STATUS(1), PUT_FOOD(2), REFRESH_WATER(3), NONE(0);

		private final int intValue;

		public static final Map<Integer, UserGameCmd> cmdMap = Collections.unmodifiableMap(initializeMapping());

		private UserGameCmd(int v) {
			this.intValue = v;
		}

		public int getIntValue() {
			return intValue;
		}

		private static Map<Integer, UserGameCmd> initializeMapping() {
			Map<Integer, UserGameCmd> cmdMap = new HashMap<Integer, UserGameCmd>();
			for (UserGameCmd gameCmd : UserGameCmd.values()) {
				cmdMap.put(gameCmd.getIntValue(), gameCmd);
			}
			return cmdMap;
		}

		public static UserGameCmd valueOfInt(int inputCmd) { //原本提供的方法不是用於value的比對
			if (inputCmd == CHECK_STATUS.getIntValue())
				return CHECK_STATUS;
			if (inputCmd == PUT_FOOD.getIntValue())
				return PUT_FOOD;
			if (inputCmd == REFRESH_WATER.getIntValue())
				return REFRESH_WATER;

			return NONE;
		}
	}

	public FeedFishGameText() {
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
		fishList.add(
				(new Guppy("Guppy")).setPattern(Guppy.PatternName.COBRA).setCooldownTime(8).setGender(AbstractFish.GenderEnum.FEMALE).setHealthDegree(100));
		fishList.add((new RedNeon("RedNeon")).setCooldownTime(3).setGender(AbstractFish.GenderEnum.MALE).setHealthDegree(100));
		fishList.add((new Wawa("Wawa")).setCooldownTime(5).setGender(AbstractFish.GenderEnum.MALE).setHealthDegree(80));

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

	private boolean checkFishAlive(AbstractFish fish) {
		if (fish.getHealthDegree() < 0) {
			return false;
		}
		return true;
	}

	private class UserHandler implements Runnable {

		@Override
		public void run() {
			UserGameCmd userCmd;

			while (fishList.size() > 0) {

				userCmd = getUserGameCmd();

				switch (userCmd) {
					case CHECK_STATUS://enum
						checkGameStatus(fishList);
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

		private void checkGameStatus(List<AbstractFish> fishList) {//改list & 這邊不需要處理extend
			System.out.println("現在魚缸中的有害物質佔這麼多:" + aq.getNo2());
			System.out.println("現在魚缸中的殘餘飼料有:" + ffood);
			for (AbstractFish fish : fishList) {
				System.out.println(fish.getName() + "的健康度是" + String.valueOf(fish.getHealthDegree()));
			}
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
		}

		private UserGameCmd getUserGameCmd() {
			try {
				String msg = "你可以進行三個動作 :1.確認魚隻/環境狀態  2.加飼料 3.換水";
				UserGameCmd gameCmd = UserGameCmd.cmdMap.get(Integer.valueOf(getUserInput(msg)));
				if (gameCmd != null)
					return gameCmd;

				return UserGameCmd.NONE;
			} catch (NumberFormatException e) {
				return UserGameCmd.NONE;
			}
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

	}

	private class FishActionHandler implements Runnable {

		private int gameRound = 0;

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				while (fishList.size() > 0) {// every round

					Iterator<AbstractFish> itFishs = fishList.iterator();//iterate list and delete member inside.

					while (itFishs.hasNext()) { //every fish
						AbstractFish fish = itFishs.next();
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
//					((OffensiveBehavior) fish).attackFishs(fishList,
//							f -> (f.getHealthDegree() < 50 ? Math.random() < 0.3 : Math.random() < 0.5) && !(fish.getClass().isInstance(f)), f -> {
//								System.out.print(fish.getName() + " attact " + f.getName() + " ");
//								int suf = fish.getCooldownTime() - f.getCooldownTime();
//								if (suf < 1) {
//									suf = 1;
//								}
//								f.setHealthDegree(f.getHealthDegree() - (int) suf * 3);
//								System.out.println(f.getName() + "'s Health Degree is " + String.valueOf(f.getHealthDegree()) + " Now!");
//							});
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
