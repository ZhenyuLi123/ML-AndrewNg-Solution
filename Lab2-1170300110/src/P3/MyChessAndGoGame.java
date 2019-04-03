package P3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyChessAndGoGame {
	private Game game;

	public void runGame() {
		///easy for test
		String gameType1 = "Go";
		String gameType2 = "Chess";
		
		String playerName1 = "A";
		String playerName2 = "B";
		///
		int x;
		int y;
		int x1;
		int y1;
		String[] str;
		List<Player> players = new ArrayList<Player>();

		System.out.println("A small game!");
		System.out.println("Input the type you want to play:(Chess or Go)");
		Scanner in = new Scanner(System.in);
		while (true) {

			String type = in.nextLine();
			//String type = gameType2;
			if (type.equals("Chess")) {
				game = new Game("Chess");
				break;
			} else if (type.equals("Go")) {
				game = new Game("Go");
				break;
			} else {
				System.out.println("Please input according to the prompt. Try again!");
			}
		}

		System.out.println("Please input the first player's name:");
		String name1 = in.nextLine();
		//String name1 = playerName1;
		System.out.println("Please input the second player's name:");
		String name2 = in.nextLine();
		//String name2 = playerName2;

		game.init(name1, name2);
		players.add(game.getPlayer(name1));
		players.add(game.getPlayer(name2));

		boolean exitFlag = false;
		int count = 0;
		while (true) {
			game.getBoard().showBoard();
			Player p = players.get(count % 2);
			System.out.printf("%s's round. Please input the num to play!\n", p.getName());
			printMenu();
			String choose = in.nextLine();

			switch (choose) {
			case "1":
				//�������� �Ҿ���ֻ��Χ������������
				System.out.println("Input the position you want to put your piece to:(x y) input (-1 -1) to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//����������곬Խ������
					if(x > 18 || y > 18) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//������λ���Ѿ���������
					if(game.ifHavePiece(x, y)) {
						System.out.println("The position has piece! Please input again!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}else {
					count++;
					game.putPiece(p, x, y);
					break;
					
				}
				
			case "2":
				//�ƶ�����
				System.out.println("Input the position of the piece you want to move:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//�������곬������
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//���������Ҫ�ƶ�������
					if(!game.getBoard().isContain(x, y)) {
						System.out.println("There is no piece at that position! Please input agian!");
						continue;
					}
					//���Ҫ�ƶ������Ӳ������
					if(!p.ifContain(game.getBoard().getPiece(x, y))) {
						System.out.println("The piece doesn't belong to you! Please input agian!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}
				System.out.println("Input the position you want to put at:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x1 = Integer.valueOf(str[0]);
					y1 = Integer.valueOf(str[1]);
					if(x1 == -1) {
						break;
					}
					//Խ��
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//�Ǹ�λ���Ѿ���������
					if(game.getBoard().getPiece(x1, y1) != null) {
						System.out.println("There is already a piece at that position! Please input again!");
						continue;
					}
					//MOVE�����Ϲ����������
					if(!game.checkMove(game.getBoard().getPiece(x, y), x, y, x1, y1)) {
						System.out.println("Break the rules! Please input again!");
						continue;
					}
					break;
				}
				if(x1 == -1) {
					break;
				}else {
					count++;
					game.movePiece(p, x, y, x1, y1);
					break;
				}
				
				
				
			case "3":
				//����
				System.out.println("Input the position of the opponent's piece you want to remove:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//Խ��
					if(x > 18 || y > 18) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//�Ǹ�λ�ø���û������
					if(game.getBoard().getPiece(x, y) == null) {
						System.out.println("There is no piece at that position! Please input again!");
						continue;
					}
					//����Ǹ����Ӳ��ǶԷ�������
					if(!players.get((count+1)%2).ifContain(game.getBoard().getPiece(x, y))) {
						System.out.println("The piece is yours! Please input agian!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}else {
					game.removePiece(p, x, y, players.get((count+1)%2));
					count++;
					break;
				}
				
			case "4":	
				//����
				System.out.println("Input the position of your piece:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//Խ��
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//������Ҫ����������
					if(!game.getBoard().isContain(x, y)) {
						System.out.println("There is no piece at that position! Please input agian!");
						continue;
					}
					//���Ӳ������
					if(!p.ifContain(game.getBoard().getPiece(x, y))) {
						System.out.println("The piece does't belong to you! Please input agian!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}
				System.out.println("Input the position of the piece you want to eat:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x1 = Integer.valueOf(str[0]);
					y1 = Integer.valueOf(str[1]);
					//Խ��
					if(x1 == -1) {
						break;
					}
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//�Ǹ���û������
					if(game.getBoard().getPiece(x1, y1) == null) {
						System.out.println("There is no piece at that position! Please input again!");
						continue;
					}
					//�Ǹ����Ӳ��ǶԷ���
					if(p.ifContain(game.getBoard().getPiece(x1, y1))) {
						System.out.println("The piece is yours! Please input agian!");
						continue;
					}
					//�����Ϲ�������Ĺ���
					if(!game.checkEat(game.getBoard().getPiece(x, y), x, y, x1, y1)) {
						System.out.println("Break rules! Please input agian!");
						continue;
					}
					break;
				}
				
				if(x1 == -1) {
					break;
				}else {
					game.eatPiece(p, x, y, x1, y1, players.get((count+1)%2));
					count++;
					break;
				}
				
				
			case "5":	
				//��ѯĳ������
				System.out.println("Input the position of the piece you want to query:(x y)");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					//Խ��
					if(game.getGameType().equals("Chess")) {
						if(x > 8|| y > 8) {
							System.out.println("Get out of the board! Please input again!");
							continue;
						}
					}
					
					if(game.getGameType().equals("Go")) {
						if(x > 18 || y > 18) {
							System.out.println("Get out of the board! Please input again!");
							continue;
						}
					}
					break;
				}
				Piece piece = game.query(p, x, y);
				if(piece != null) {
					System.out.println("The name of the piece is: " + piece.getName());
					if(p.ifContain(piece)) {
						System.out.println("The piece belongs to " + p.getName());
					}else {
						System.out.println("The piece belongs to " + players.get((count+1)%2).getName());
					}
				}else {
					System.out.println("There is no piece!");
				}
			//	count++;
				break;
				
			case "6":
				//��ѯ��������
				int numPiece = game.getNum(players.get(0));
				if(numPiece == 0) {
					System.out.println(players.get(0).getName() + " has no piece!");
				}else if(numPiece == 1) {
					System.out.println(players.get(0).getName() + " has 1 piece!");
				}else {
					System.out.println(players.get(0).getName() + " has " + Integer.toString(numPiece) + " pieces!");
				}
				
				numPiece = game.getOtherNum(players.get(1));
				if(numPiece == 0) {
					System.out.println(players.get(1).getName() + " has no piece!");
				}else if(numPiece == 1) {
					System.out.println(players.get(1).getName() + " has 1 piece!");
				}else {
					System.out.println(players.get(1).getName() + " has " + Integer.toString(numPiece) + " pieces!");
				}
			//	count++;
				break;
				
			case "7":
				//�������غ�
				game.skip(p);
				count++;
				break;
				
			case "end":	
				//������Ϸ ��ӡhistory
				System.out.println("Here is the play histroy:");
				System.out.println(players.get(0).getName()+":");
				System.out.printf(players.get(0).getHistory());
				System.out.println(players.get(1).getName()+":");
				System.out.printf(players.get(1).getHistory());
				exitFlag = true;
				break;
			}

			if(exitFlag) break;

		}

	}

	//��ӡѡ��˵�
	private void printMenu() {
		System.out.println("1.\tput the piece at the position(Just for Go)");
		// ����δ�������ϵ�һ�����ӷ��������ϵ�ָ��λ�� go
		System.out.println("2.\tmove the piece to the position(Just for Chess)");
		// �ƶ������ϵ�ĳ��λ�õ��������µ�λ�� chess
		System.out.println("3.\tremove the piece(Just for Go)");
		// ���ӣ��Ƴ��������ӣ�go
		System.out.println("4.\teat the piece(Just for Chess)");
		// ���ӣ�ʹ�ü������ӳԵ��������ӣ�chess
		System.out.println("5.\tquery state of the position");
		// ��ѯĳ��λ�õ�ռ�����
		System.out.println("6.\tcount the nums of pieces");
		// ����������ҷֱ��������ϵ���������
		System.out.println("7.\tskip over");
		// ����
		System.out.println("end.\tgame over");
		// ��������
	}
	

	public static void main(String[] args) {
		new MyChessAndGoGame().runGame();
	}
}
