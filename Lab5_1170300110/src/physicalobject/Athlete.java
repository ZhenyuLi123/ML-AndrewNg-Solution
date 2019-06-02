package physicalobject;

import circularorbits.Word;

public class Athlete extends PhysicalObject {
  /**Serializable.
   * 
   */
  private static final long serialVersionUID = 1L;
  private Word name;
  private int num;
  private int age;
  private String nation = new String();//国籍 三个大写字母
  private double grade; //最多两位正数，小数点两位

  /**constructor.
   * 
   * @param name ath name
   * @param num ath num
   * @param nation ath notion
   * @param age ath age
   * @param grade ath last grade
   */
  public Athlete(Word name, int num, String nation, int age, double grade) {
    this.name = name;
    this.num = num;
    this.nation = nation;
    this.age = age;
    //grade需要舍入
    this.grade = grade;
    setSitha(0);
  }

  @Override
  public int getNum() {
    return num;
  }

  public Word getName() {
    return name;

  }


  public int getAge() {
    return age;
  }

  public String getNation() {
    return nation;
  }

  public double getGrade() {
    return grade;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) { 
      return false;
    }
    if (((Athlete) o).age == this.age && ((Athlete) o).grade == this.grade 
        && ((Athlete) o).name.equals(this.name) && ((Athlete) o).nation.equals(this.nation)
        && ((Athlete) o).num == this.num) { 
      return true;
    } else { 
      return false;
    }
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return super.hashCode();
  }

  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append(name.toString() + " ");
    s.append(String.valueOf(num) + " ");
    s.append(String.valueOf(age) + " ");
    s.append(nation + " ");
    s.append(String.valueOf(grade));
    return s.toString();
  }

}
