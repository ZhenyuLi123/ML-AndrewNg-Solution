package circularorbits;

public interface MyIterator<L, E> {
  /** hasNext.
   * ����Ƿ�����һ��Ԫ��
   *
   * @return ����У�����true�����򷵻�false
   */
  boolean hasNext();

  /** next.
   * ���ص�ǰԪ�أ�����ָ��ָ����һ��Ԫ��
   *
   * @return ��ǰԪ��
   */
  E next();

}
