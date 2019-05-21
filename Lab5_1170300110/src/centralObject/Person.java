package centralObject;
import circularOrbit.label;

public class Person {
	private label name;
	private int age;
	private char gender;
	
	//RI:
	//�Ϸ���name age gender
	//AF:
	//���ϵͳ�е����������� for app5
	//�������֡����䡢�Ա�����
	//��ֹREP��¶:
	//ʹ��private����
	//set��������Ϊprivate
	
	public Person(label name, int age, char gender) {
		this.setName(name);
		this.setAge(age);
		this.setGender(gender);
	}

	public label getName() {
		return name;
	}

	private void setName(label name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	private void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	private void setGender(char gender) {
		this.gender = gender;
	}
	
}
