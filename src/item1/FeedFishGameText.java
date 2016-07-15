package item1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class FeedFishGameText {

	private ArrayList<Fish> fishList;

	private Aquarium aq;

	private int ffood;

	FeedFishGameText() {
		aq = new Aquarium();
		fishList = new ArrayList<Fish>();
	}

	public void startGame() {
		Thread ut = new Thread(new UserHandler());
		Thread ft = new Thread(new FishActionHandler());
		ut.start();
		ft.start();
	}

	public void setGame() {
		Guppy f1 = new Guppy("Guppy", 8, Fish.genderEnum.FEMALE, 100, Guppy.PatternName.COBRA);
		RedNeon f2 = new RedNeon("RedNeon", 3, Fish.genderEnum.MALE, 100);
		Wawa f3 = new Wawa("Wawa", 5, Fish.genderEnum.MALE, 80);
		fishList.add(f1);
		fishList.add(f2);
		fishList.add(f3);

		System.out.print("��J���� ");

		for (Fish fish : fishList) {
			System.out.print(fish.getName() + " ");
		}

		System.out.println("�C���}�l");
	}

	public void checkNO2() {
		System.out.println(String.valueOf(aq.getNO2()));
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

	private void checkAll(ArrayList<? extends Fish> fishList) {
		System.out.println("�{�b�����������`������o��h:" + aq.getNO2());
		System.out.println("�{�b���������ݾl�}�Ʀ�:" + ffood);
		for (Fish fish : fishList) {
			System.out.println(fish.getName() + "�����d�׬O" + String.valueOf(fish.gethDegree()));
		}
	}

	private boolean checkFishAlive(Fish fish) {
		if (fish.gethDegree() < 0) {
			return false;
		} else {
			return true;
		}
	}

	class UserHandler implements Runnable {

		@Override
		public void run() {
			String msg = "";
			int userCmd = 0;

			while (true) {

				if (fishList.size() == 0) {
					break;
				}

				msg = "�A�i�H�i��T�Ӱʧ@ :1.�T�{����/���Ҫ��A  2.�[�}�� 3.����";
				userCmd = Integer.valueOf(getUserInput(msg));

				switch (userCmd) {
					case 1:
						checkAll(fishList);
						break;
					case 2:
						msg = "�n�[�J�h�ֹ}�ƩO?";
						userCmd = Integer.valueOf(getUserInput(msg));
						synchronized (this) {
							ffood += userCmd;
						}
						break;
					case 3:
						synchronized (this) {
							aq.refleshWater();
							ffood = (int) (ffood * 0.8);
						}
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

					if (fishList.size() == 0) {
						System.out.println("�������FQQ�A�A�o����D�H");
						break;
					}
					Iterator<Fish> itFishs = fishList.iterator();
					while (itFishs.hasNext()) {
						Fish fish = itFishs.next();
						fish.feelWater(aq.getNO2());
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

							if (fish instanceof Wawa) {
								synchronized (this) {
									Wawa wawa = (Wawa) fish;
									wawa.attackFish(fishList);
								}
							} else if (fish instanceof RedNeon) {
								RedNeon redNeon = (RedNeon) fish;
								redNeon.display();
							}
						}
						Thread.sleep(1000);
					}

					aq.checkFood(ffood);//update water
					timePass += 1;

					Thread.sleep(1000);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
