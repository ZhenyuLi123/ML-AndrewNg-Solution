package circularOrbit;
import java.io.File;
import java.io.IOException;

import track.Track;

public interface CircularOrbit<L, E> extends CircularOrbitAggregate<L, E>{
	//L���ĵ���������
	//E������������
	
	
	/**
	 * �ҵ�ָ��num������
	 * @param num
	 * @return ĳ�����Ϊnum������
	 */
	public E getCertainObject(int num);
	
	/**
	 * �ҵ�ĳ�����Ϊnum�Ĺ��
	 * @param num
	 * @return ĳ�����Ϊnum�Ĺ��
	 */
	public Track getCertainTrack(int num);
	
	/**
	 * ���ӹ��
	 * @param Ҫ���ӵĹ�� t
	 */
	public void addTrack(Track t);
	
	/**
	 * ɾ�����
	 * @param Ҫɾ���Ĺ�� t
	 */
	public void deleteTrack(Track t);
	
	/**
	 * �趨���ĵ�����
	 * @param ���ĵ����� object
	 */
	public void setCentralObject(L object);
	
	/**
	 * ��ĳ���������������
	 * @param ��� t
	 * @param ĳ������ object
	 */
	public void addObjectToTrack(Track t, E object);
	
		
	/**
	 * ��ȡ�ļ�
	 * @param �ļ�·�� filename
	 * @throws IOException
	 */
	public void readFile(File filename) throws IOException;
	
	/**
	 * һ������ı���
	 * @param ���� object
	 * @param Ŀ���� t
	 */
	public void transit(E object, Track t);
	
	
	//�õ������Ŀ
	public int getTrackNum();
	
	//�õ�������Ŀ
	public int getObjectNum();
	
	
	//iterator
	@Override 
	public MyIterator<L, E> iterator();
}


