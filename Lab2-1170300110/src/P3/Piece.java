package P3;

public class Piece {
	//private int state; // 0 δ���� 1 ���� 2 ����
	private Position p; // ������Ϣ
	private final String name; // ��������
	
	// Abstraction function:
	// TODO ���� ����������Ϣ����������
	// Representation invariant:
	// TODO ������Ϣ��Ҫ�Ϸ�
	// Safety from rep exposure:
	// TODO private ʹ�ø���
	/**
	 * ����������
	 * @param name
	 */
//	public Piece(String name) {
//		this.p = new Position();
//		this.name = name;
//		//this.state = 0;
//	}
//	
	
	public Piece(String name, int x, int y) {
		this.p = new Position(x, y);
		//this.state = state;
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
	 * @return ����λ��
	 */
	public Position getPosition() {
		return new Position(p.x, p.y);
	}
	
//
//	/**
//	 * ����������
//	 * @param name
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//	
	/**
	 * �ı�����λ��
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.p.x = x;
		this.p.y = y;
	}
	

}
