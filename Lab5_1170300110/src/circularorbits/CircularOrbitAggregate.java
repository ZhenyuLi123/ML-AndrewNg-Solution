package circularorbits;

//import java.util.Iterator;

public interface CircularOrbitAggregate<L, E> {
  /** iterator.
   * ���ص�ǰ���ϵĵ�����
   *
   * @return ��ǰ���ϵĵ�����
   */
  MyIterator<L, E> iterator();
}
