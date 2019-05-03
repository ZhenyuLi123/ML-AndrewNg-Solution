package physicalObject;
import java.io.Serializable;

import circularOrbit.number;
public abstract class PhysicalObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int num;
	private number r;
	private double sitha;
	
	//RI:
	//num >= 1
	//sitha [0, 360)
	//AF:
	//���ϵͳ�е�������
	//������š��뾶���Ƕȵ�����
	//��ֹREP��¶:
	//ʹ��private����
	//set����ֻ�����������ʼ��ʱʹ��
	
	
	public number getR() {
		return this.r;
	}
	
	public void setSitha(double sitha) {
		this.sitha = sitha;
	}
	
	public double getSitha() {
		return this.sitha;
	}
	
	@Override
	abstract public boolean equals(Object o);

	public int getNum() {
		return num;
	}

	public void setNum(int i) {
		this.num = i;
	}

}
