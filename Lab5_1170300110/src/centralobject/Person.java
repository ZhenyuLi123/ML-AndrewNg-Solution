package centralobject;

import circularorbits.Label;

public class Person {
  private Label name;
  private int age;
  private char gender;

  // RI:
  // �Ϸ���name age gender
  // AF:
  // ���ϵͳ�е����������� for app5
  // �������֡����䡢�Ա�����
  // ��ֹREP��¶:
  // ʹ��private����
  // set��������Ϊprivate
  /**constructor.
   * 
   * @param name person's name
   * @param age person's age
   * @param gender person's gender
   */

  public Person(Label name, int age, char gender) {
    this.setName(name);
    this.setAge(age);
    this.setGender(gender);
  }

  public Label getName() {
    return name;
  }

  private void setName(Label name) {
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
