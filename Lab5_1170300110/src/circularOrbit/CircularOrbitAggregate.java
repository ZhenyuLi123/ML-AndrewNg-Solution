package circularOrbit;

//import java.util.Iterator;

public interface CircularOrbitAggregate<L, E> {
	 /**
     * ���ص�ǰ���ϵĵ�����
     *
     * @return ��ǰ���ϵĵ�����
     */
    MyIterator<L, E> iterator();
}
