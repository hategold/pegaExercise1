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

	private List<AbstractFish> fishList;//�ŧi���q��interface

	private Aquarium aq;

	private int ffood;

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
		fishList.add(new RedNeon("RedNeon", 3, AbstractFish.GenderEnum.MALE, 100)); //fish builder �ԥX��  method chain);
		fishList.add(new Wawa("Wawa", 5, AbstractFish.GenderEnum.MALE, 80));

		//		Fish f2 = new RedNeon("RedNeon", 3, Fish.GenderEnum.MALE, 100);//fish builder �ԥX��  method chain
		//		Wawa f3 = new Wawa("Wawa", 5, Fish.GenderEnum.MALE, 80);
		//OLD version
	}

	public void setGame() {

		buildFishList();
		System.out.print("��J���� ");

		for (AbstractFish fish : fishList) {
			System.out.print(fish.getName() + " ");
		}

		System.out.println("�C���}�l");
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
		} else {
			this.ffood = ffood;
		}
	}

	private void checkGameStatus(List<? extends AbstractFish> fishList) {//��list & �o�䤣�ݭn�B�zextend
		System.out.println("�{�b�����������`������o��h:" + aq.getNo2());
		System.out.println("�{�b���������ݾl�}�Ʀ�:" + ffood);
		for (AbstractFish fish : fishList) {
			System.out.println(fish.getName() + "�����d�׬O" + String.valueOf(fish.getHealthDegree()));
		}
	}

	private boolean checkFishAlive(AbstractFish fish) {
		if (fish.getHealthDegree() < 0) {
			return false;
		} else {
			return true;
		}
	}

	private void cleanWaterFood() {
		synchronized (this) {
			aq.refleshWater();
			ffood = (int) (ffood * 0.8);
		}
	}

	private void userPutFood() {

		String msg = "�n�[�J�h�ֹ}�ƩO? ��J�D�Ʀr�h�����ʧ@";
		try {
			int userCmd = Integer.valueOf(getUserInput(msg));
			synchronized (this) {
				ffood += userCmd;
			}
			System.out.printf("��J%d���}�ơC\n", userCmd);

		} catch (NumberFormatException e) {
			System.out.println("�}�ƥu���J�Ʀr�A�^��ʧ@���");
		}
	}//�i��|�Q������J

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

	private UserGameCmd getUserGameCmd() {
		try {
			String msg = "�A�i�H�i��T�Ӱʧ@ :1.�T�{����/���Ҫ��A  2.�[�}�� 3.����";
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
						checkGameStatus(fishList);//�R�W���I�ǩ�
						break;
					case PUT_FOOD:
						userPutFood();
						break;
					case REFRESH_WATER:
						cleanWaterFood();
						break;
					default:
						System.out.println("�u���J�Ʀr1~3");
						break;
				}
			}
		}
	}

	class FishActionHandler implements Runnable {

		private int timePass = 0;

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				while (timePass >= 0) {

					if (fishList.size() == 0) { // fish ALL die
						System.out.println("�������FQQ�A�A�o����D�H");
						break;
					}
					Iterator<AbstractFish> itFishs = fishList.iterator();//iterate list and delete member inside.
					while (itFishs.hasNext()) {
						AbstractFish fish = itFishs.next();//�n�����Ʊ� �Ԥ@��method
						fish.feelWater(aq.getNo2());
						synchronized (this) {
							if (!checkFishAlive(fish)) {

								itFishs.remove();
								System.out.println(fish.getName() + "�˽շS�A�A�٦�" + fishList.size() + "����");
								if (fishList.size() == 0) {
									break;
								}
								continue;
							}
						}
						if (timePass % fish.getSpeed() == 0) {
							fish.swim();

							synchronized (this) {
								int minusFood = fish.askFood();
								if (minusFood > ffood) {
									minusFood = ffood;
									ffood = 0;
								} else {
									ffood -= minusFood;
								}
								fish.eatFood(minusFood);
							}

							if (fish instanceof Wawa) {//detail ���ӥ�interface ����x��
								synchronized (this) {
									OffensiveBehavior wawa = (OffensiveBehavior) fish;//���ΦA���ܼ�
									wawa.attackFishs(fishList);
								}
							} else if (fish instanceof RedNeon) {
								RedNeon redNeon = (RedNeon) fish;
								redNeon.display();
							}
						}
						Thread.sleep(1000);
					}

					aq.degradeWater(ffood);//update water
					timePass += 1;

					Thread.sleep(1000);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
