package track;
import java.io.Serializable;

import circularOrbit.number;
public class Track implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//RI:
	//num >= 1
	//radius >= 0
	//AF:
	//���ϵͳ�еĹ����
	//���й����š��뾶������
	//��ֹREP��¶:
	//ʹ��private�����Ҳ�����set����
	
	//������ //name
	private int num;
	//�뾶
	private number radius;
	
	
	/**
	 * ���췽��
	 * @param ����num������
	 */
	public Track(int num) {
		this.num = num;
	}
	
	/**
	 * get����
	 * @return ���ع�����
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * get����
	 * @return ���ع���뾶
	 */
	public number getRadius() {
		return new number(this.radius.getNum());
	}
	

	/**
	 * ��������Ϊ���
	 */
	@Override
	public boolean equals(Object o) {
		if( ((Track) o).num == this.num) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * ����Ϊnum + 1
	 */
	@Override
	public int hashCode() {
		return this.num;
	}
}
