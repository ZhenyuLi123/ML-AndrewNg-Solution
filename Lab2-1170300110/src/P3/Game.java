package P3;

public class Game {
	private String gameType;
	//��Ϸ����
	private Board gameBoard;
	//��Ϸ����
	private Action gameAction;
	//��Ϸ����
	private Player PlayerA;
	private Player PlayerB;
	
	// Abstraction function:
	// TODO Game�д洢�������Ϸ�еģ���Ϸ���͡����̡���������
	// ͨ��Action�������̺����ֵĲ���ͬ��������
	// Representation invariant:
	// TODO ����Ԫ����Ҫ�Ϸ�
	// Safety from rep exposure:
	// TODO private ʹ�ø���
	
	
	public Game() {
		//do nothing 
	}
	
	/**
	 * ///����������Ӽ��
	 * @param piece
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @return �Ƿ�����ô�ƶ����� ����ܷ���true
	 */
	public boolean checkEat(Piece piece, int x, int y, int x1, int y1) {
		Game g = new Game();
		String name = piece.getName();
		if(name.equals("Pawn1")|| name.equals("Pawn2")|| name.equals("Pawn3")||
				name.equals("Pawn4")|| name.equals("Pawn5")|| name.equals("Pawn6")|| name.equals("Pawn7")
				|| name.equals("Pawn8")|| name.equals("Pawn1_")|| name.equals("Pawn2_")|| name.equals("Pawn3_")
				|| name.equals("Pawn4_")|| name.equals("Pawn5_")|| name.equals("Pawn6_")|| name.equals("Pawn7_")
				|| name.equals("Pawn8_")) {
			if(Math.abs(x - x1) == 1 && Math.abs(y - y1) == 1) {
				return true;
			}else {
				return false;
			}
		}
		
		if(name.equals("Rook1")|| name.equals("Rook1_")|| name.equals("Rook2")|| name.equals("Rook2_")) {
			if(g.checkMove(piece, x, y, x1, y1)) {
				return true;
			}else {
				return false;
			}
		}
		
		if(name.equals("Knight1")|| name.equals("Knigh1_")|| name.equals("Knight2")|| name.equals("Knigh2_")){
			if(g.checkMove(piece, x, y, x1, y1)) {
				return true;
			}else {
				return false;
			}
		}
		
		if(name.equals("Bisho1_")|| name.equals("Bisho2_")|| name.equals("Bishop1")|| name.equals("Bishop2")) {
			if(g.checkMove(piece, x, y, x1, y1)) {
				return true;
			}else {
				return false;
			}
		}
		
		if(name.equals("King")|| name.equals("King_")) {
			if(g.checkMove(piece, x, y, x1, y1)) {
				return true;
			}else {
				return false;
			}
		}
		
		if(name.equals("Queen")|| name.equals("Queen_")) {
			if(g.checkMove(piece, x, y, x1, y1)) {
				return true;
			}else {
				return false;
			}
		}
		return false; // can't get here!
	}
	
	
	/**
	 * ///�������� �ƶ����
	 * @param piece
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @return �������ô�߷���true
	 */
	public boolean checkMove(Piece piece, int x, int y, int x1, int y1) {
		String name = piece.getName();
		if(name.equals("Pawn1")|| name.equals("Pawn2")|| name.equals("Pawn3")||
				name.equals("Pawn4")|| name.equals("Pawn5")|| name.equals("Pawn6")|| name.equals("Pawn7")
				|| name.equals("Pawn8")|| name.equals("Pawn1_")|| name.equals("Pawn2_")|| name.equals("Pawn3_")
				|| name.equals("Pawn4_")|| name.equals("Pawn5_")|| name.equals("Pawn6_")|| name.equals("Pawn7_")
				|| name.equals("Pawn8_")) {
			if(y == 1) {
				if( y1 != 2 && y1 != 3) {
					return false;
				}else {
					return true;
				}
			}else if(y == 6) {
				if( y1 != 5 && y1 != 4) {
					return false;
				}else {
					return true;
				}
			}else {
				if(Math.abs(y1 - y) == 1) {
					return true;
				}else {
					return false;
				}
			}
		}
		
		
		if(name.equals("Rook1")|| name.equals("Rook1_")|| name.equals("Rook2")|| name.equals("Rook2_")) {
			if(y == y1 || x == x1) {
				if(y == y1 && x < x1) {
					for(int i = x + 1; i <= x1 - 1; i++) {
						if(gameBoard.isContain(i, y)) {
							return false;
						}
					}
					return true;
				}
				if(y == y1 && x > x1) {
					for(int i = x1 + 1; i <= x - 1; i++) {
						if(gameBoard.isContain(i, y)) {
							return false;
						}
					}
					return true;
				}
				if(x == x1 && y < y1) {
					for(int i = y + 1; i <= y1 - 1; i++) {
						if(gameBoard.isContain(x, i)) {
							return false;
						}
					}
					return true;
				}
				if(x == x1 && y > y1) {
					for(int i = y1 + 1; i <= y - 1; i++) {
						if(gameBoard.isContain(x, i)) {
							return false;
						}
					}
					return true;
				}
				return false;
			}else {
				return false;
			}
		}
		
		if(name.equals("Knight1")|| name.equals("Knigh1_")|| name.equals("Knight2")|| name.equals("Knigh2_")){
			if(Math.abs(x - x1) == 1 && Math.abs(y - y1) == 2) {
				return true;
			}
			if(Math.abs(x - x1) == 2 && Math.abs(y - y1) == 1) {
				return true;
			}
			return false;
		}
		
		if(name.equals("Bisho1_")|| name.equals("Bisho2_")|| name.equals("Bishop1")|| name.equals("Bishop2")) {
			if(Math.abs(x - x1) == Math.abs(y - y1)) {
				if(x<x1 && y<y1) {
					int j = y + 1;
					for(int i = x + 1; i <= x1-1; i++) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j++;
					}
				}
				if(x<x1 && y>y1) {
					int j = y - 1;
					for(int i = x + 1; i <= x1-1; i++) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j--;
					}
				}
				if(x>x1 && y<y1) {
					int j = y + 1;
					for(int i = x - 1; i >= x1+1; i--) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j++;
					}
				}
				if(x>x1 && y>y1) {
					int j = y - 1;
					for(int i = x - 1; i >= x1+1; i--) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j--;
					}
				}
			}
		}
		
		if(name.equals("King")|| name.equals("King_")) {
			if(Math.abs(x1 - x) == 1 && Math.abs(y1 - y) == 1) {
				return true;
			}else {
				return false;
			}
		}
		
		if(name.equals("Queen")|| name.equals("Queen_")) {
			///rook
			if(y == y1 || x == x1) {
				if(y == y1 && x < x1) {
					for(int i = x + 1; i <= x1 - 1; i++) {
						if(gameBoard.isContain(i, y)) {
							return false;
						}
					}
					return true;
				}
				if(y == y1 && x > x1) {
					for(int i = x1 + 1; i <= x - 1; i++) {
						if(gameBoard.isContain(i, y)) {
							return false;
						}
					}
					return true;
				}
				if(x == x1 && y < y1) {
					for(int i = y + 1; i <= y1 - 1; i++) {
						if(gameBoard.isContain(x, i)) {
							return false;
						}
					}
					return true;
				}
				if(x == x1 && y > y1) {
					for(int i = y1 + 1; i <= y - 1; i++) {
						if(gameBoard.isContain(x, i)) {
							return false;
						}
					}
					return true;
				}
			}
			
			///bishop
			if(Math.abs(x - x1) == Math.abs(y - y1)) {
				if(x<x1 && y<y1) {
					int j = y + 1;
					for(int i = x + 1; i <= x1-1; i++) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j++;
					}
					return true;
				}
				if(x<x1 && y>y1) {
					int j = y - 1;
					for(int i = x + 1; i <= x1-1; i++) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j--;
					}
					return true;
				}
				if(x>x1 && y<y1) {
					int j = y + 1;
					for(int i = x - 1; i >= x1+1; i--) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j++;
					}
					return true;
				}
				if(x>x1 && y>y1) {
					int j = y - 1;
					for(int i = x - 1; i >= x1+1; i--) {
						if(gameBoard.isContain(i, j)) {
							return false;
						}
						j--;
					}
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	/**
	 * �������غ� history�����Ӽ�¼
	 * @param player
	 */
	public void skip(Player player) {
		StringBuffer s = new StringBuffer();
		s.append("Skip over");
		player.putStep(s.toString());
	}

	/**
	 * �������� history�����Ӽ�¼
	 * @param player
	 * @return ���ظ�player��������
	 */
	public int getNum(Player player) {
		StringBuffer s = new StringBuffer();
		s.append("Count numbers");
		player.putStep(s.toString());
		return player.getNums();
	}
	
	/**
	 * �������� history�����Ӽ�¼
	 * @param player
	 * @return ���ظ�player��������
	 */
	public int getOtherNum(Player player) {
		return player.getNums();
	}
	
	/**
	 * ��ѯĳһ�㴦��������� history�����Ӽ�¼
	 * @param player
	 * @param x
	 * @param y
	 * @return ����Ǹ������� �����Ǹ��ӣ� ���û���� ����null
	 */
	
	public Piece query(Player player, int x, int y) {
		StringBuffer s = new StringBuffer();
		s.append("Query information of the piece at ");
		player.putStep(s.toString() +"(" + Integer.toString(x) + ", " + Integer.toString(y)+ ")");
		
		if(gameBoard.getPiece(x, y) == null) {
			return null;
		}else {
			return gameBoard.getPiece(x, y);
		}
	}
	
	
	/**
	 * //���� for chess history�����Ӽ�¼
	 * @param player
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @param opponent
	 */
	
	public void eatPiece(Player player, int x, int y, int x1, int y1, Player opponent) {
		StringBuffer s = new StringBuffer();
		s.append("Eat piece: from ");
		player.putStep(s.toString() + " (" + Integer.toString(x) + ", " + Integer.toString(y)+ ") to (" +
				Integer.toString(x1) + ", " + Integer.toString(y1) + ")");
		gameAction.eatPiece(player, x, y, x1, y1, opponent);
	}
	
	/**
	 * //���� for go history�����Ӽ�¼
	 * @param player
	 * @param x
	 * @param y
	 * @param opponent
	 */
	public void removePiece(Player player, int x, int y, Player opponent) {
		StringBuffer s = new StringBuffer();
		s.append(gameBoard.getPiece(x, y).getName());
		gameAction.removePiece(opponent, x, y);
		player.putStep("Remove opponent's piece " + s.toString() + 
				" at (" +Integer.toString(x)+ ", "+ Integer.toString(y)+ ")");
	}
	
	
	
	/**
	 * //�ƶ����� for chess
	 * @param player
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 */
	public void movePiece(Player player, int x, int y, int x1, int y1) {
		
		StringBuffer s = new StringBuffer();
		s.append(gameBoard.getPiece(x, y).getName());
		gameAction.movePiece(player, x, y, x1, y1);
		player.putStep("Move " + s.toString()+ " from ("+ Integer.toString(x)+ ", "+ Integer.toString(y)+ ")"+" to ("+ 
						Integer.toString(x1) + ", " + Integer.toString(y1) + ")");
		
	}
	
	/**
	 * //�ж�������xyλ���Ƿ������� for go 
	 * @param x
	 * @param y
	 * @return ����� ����true 
	 */
	
	public boolean ifHavePiece(int x, int y) {
		return gameBoard.isContain(x, y);
	}
	
	/**
	 * //���� for go
	 * @param p
	 * @param x
	 * @param y
	 */
	
	public void putPiece(Player p, int x, int y) {
		StringBuffer s = new StringBuffer();
		s.append(p.getName());
		s.append(p.getNums()+1);
		
		Piece pieceOne = new Piece(s.toString(), x, y);
		p.addPieces(pieceOne);
		
		gameAction.putPiece(p, pieceOne, x, y);
		p.putStep("Put "+ s.toString() + " at (" + Integer.toString(x) +", " + Integer.toString(y)+")");
	}
	
	/**
	 * get����
	 * @return ����
	 */
	public Board getBoard() {
		return gameBoard;
	}
	/**
	 * get����
	 * @return ��Ϸ����
	 */
	
	public String getGameType() {
		return gameType;
	}
	
	/**
	 * get����
	 * @param name
	 * @return �����������
	 */
	public Player getPlayer(String name) {
		if(PlayerA.getName().equals(name)) {
			return PlayerA;
		}else {
			return PlayerB;
		}
	}
	
	/**
	 * ������
	 * @param gameType
	 */
	public Game(String gameType) {
		/// ��������
		if(gameType.equals("Chess")) {
			this.gameType = "Chess";
		}else if (gameType.equals("Go")) {
			this.gameType = "Go";
		}else {
			//do nothing
		}
	}
	
	//��ʼ�� ��������
	public void init(String player1, String player2) {
		this.PlayerA = new Player(player1);
		this.PlayerB = new Player(player2);
		this.gameBoard = new Board(gameType);
		this.gameAction = new Action(this.PlayerA, this.PlayerB, gameBoard);
		
		if(gameType.equals("Chess")) {
			PlayerA.addPieces(new Piece("Pawn1",0,1));
			PlayerA.addPieces(new Piece("Pawn2",1,1));
			PlayerA.addPieces(new Piece("Pawn3",2,1));
			PlayerA.addPieces(new Piece("Pawn4",3,1));
			PlayerA.addPieces(new Piece("Pawn5",4,1));
			PlayerA.addPieces(new Piece("Pawn6",5,1));
			PlayerA.addPieces(new Piece("Pawn7",6,1));
			PlayerA.addPieces(new Piece("Pawn8",7,1));

			PlayerB.addPieces(new Piece("Pawn1_",0,6));
			PlayerB.addPieces(new Piece("Pawn2_",1,6));
			PlayerB.addPieces(new Piece("Pawn3_",2,6));
			PlayerB.addPieces(new Piece("Pawn4_",3,6));
			PlayerB.addPieces(new Piece("Pawn5_",4,6));
			PlayerB.addPieces(new Piece("Pawn6_",5,6));
			PlayerB.addPieces(new Piece("Pawn7_",6,6));
			PlayerB.addPieces(new Piece("Pawn8_",7,6));
			
			PlayerA.addPieces(new Piece("Rook1",0,0));
			PlayerA.addPieces(new Piece("Knight1",1,0));
			PlayerA.addPieces(new Piece("Bishop1",2,0));
			PlayerA.addPieces(new Piece("Queen",3,0));
			PlayerA.addPieces(new Piece("King",4,0));
			PlayerA.addPieces(new Piece("Bishop2",5,0));
			PlayerA.addPieces(new Piece("Knight2",6,0));
			PlayerA.addPieces(new Piece("Rook2",7,0));
			
			PlayerB.addPieces(new Piece("Rook1_",0,7));
			PlayerB.addPieces(new Piece("Knigh1_",1,7));
			PlayerB.addPieces(new Piece("Bisho1_",2,7));
			PlayerB.addPieces(new Piece("Queen_",3,7));
			PlayerB.addPieces(new Piece("King_",4,7));
			PlayerB.addPieces(new Piece("Bisho2_",5,7));
			PlayerB.addPieces(new Piece("Knigh2_",6,7));
			PlayerB.addPieces(new Piece("Rook2_",7,7));
			
		}
		
		
		
	}
}
