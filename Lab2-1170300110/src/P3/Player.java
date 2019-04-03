package P3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
	private final String name;
	private StringBuffer history = new StringBuffer();
	private final List<Piece> pieces = new ArrayList<Piece>();
	
	// Abstraction function:
	// TODO ���� �������֡�ӵ�е����ӡ���ʷ��¼
	// Representation invariant:
	// TODO �����б��е�������Ҫ�Ϸ�
	// Safety from rep exposure:
	// TODO private ʹ�ø���
	
	/**
	 * ������
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}
	/**
	 * get����
	 * @return ��������
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * get����
	 * @return ������ʷ
	 */
	public String getHistory() {
		return this.history.toString();
	}
	
	/**
	 * get����
	 * @return ��������ӵ�е������б�
	 */
	public List<Piece> getPieces(){
		///����һ��
		List<Piece> res = new ArrayList<Piece>();
		for(Piece i : this.pieces) {
			res.add(i);
		}
		return res;
	}
	
	/**
	 * ��������ֵ�λ��x y���������ƶ���x1 y1
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 */
	public void moveTo(int x, int y, int x1, int y1) {
		Iterator<Piece> it = pieces.iterator();
		while(it.hasNext()) {
		  Piece p = it.next();
		  if(p.getPosition().equals(new Position(x, y))){
			  p.setPosition(x1, y1);
		  }
		}
	}
	
	/**
	 * ������λ��x y��������ɾ��
	 * @param x
	 * @param y
	 */
	public void removePiece(int x, int y) {
		Iterator<Piece> it = pieces.iterator();
		while(it.hasNext()) {
		  Piece p = it.next();
		  if(p.getPosition().equals(new Position(x, y))){
			  it.remove();
		  }
		}
	}
	
	/**
	 * �ж������Ƿ���piece����
	 * @param piece
	 * @return ����з���ture
	 */
	public boolean ifContain(Piece piece) {
		boolean flag = false;
		for(Piece p : pieces) {
			if(p.getName().equals(piece.getName())&& p.getPosition().equals(piece.getPosition()) ) {
				flag = true;
			}
		}
		
		if(flag) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * get����
	 * @return ��������ӵ�е���������
	 */
	public int getNums() {
		return pieces.size();
	}
	/**
	 * 	///�����������������
	 * @param p
	 * @return �����������û��p���� ��ӳɹ�����true ����з���false
	 */

	public boolean addPieces(Piece p) {
		if(pieces.contains(p)) {
			return false;
		}else {
			pieces.add(p);
			return true;
		}
	}
	/**
	 * ���������� �����ʷ
	 * @param step
	 */
	public void putStep(String step) {
		history.append(step);
		history.append("\n");
	}
}
