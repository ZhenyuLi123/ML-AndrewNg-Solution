package circularOrbit;

public interface MyIterator<L, E> {
	 /**
     * ����Ƿ�����һ��Ԫ��
     *
     * @return ����У�����true�����򷵻�false
     */
    boolean hasNext();

    /**
     * ���ص�ǰԪ�أ�����ָ��ָ����һ��Ԫ��
     *
     * @return ��ǰԪ��
     */
    E next();

}
