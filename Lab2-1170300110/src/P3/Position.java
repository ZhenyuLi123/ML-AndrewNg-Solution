package P3;

public class Position {
	public int x;
	public int y;
	// �������� ����ı� ����Ϊpublic
	
	// Abstraction function:
	// TODO xy ��ʾ�ڿռ��ϵ�����λ��
	// Representation invariant:
	// TODO xy Ҫ���ڵ���0
	// Safety from rep exposure:
	// TODO ��muttable���� �ɱ�
	
	/**
	 * ������
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
//	public Position() {
//		this.x = -1;
//		this.y = -1;
//	}
    
	//��дequals xy ���
	@Override 
	public boolean equals(Object p) {
		if(((Position) p).x == x && ((Position) p).y == y){
			return true;
		}else {
			return false;
		}
	}
}
