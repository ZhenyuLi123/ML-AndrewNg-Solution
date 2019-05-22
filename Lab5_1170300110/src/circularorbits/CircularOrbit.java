package circularorbits;

import java.io.File;
import java.io.IOException;

import exception.MyException;
import track.Track;

public interface CircularOrbit<L, E> extends CircularOrbitAggregate<L, E> {
  //L���ĵ���������
  //E������������

  /**getCertainObject.
   * �ҵ�ָ��num������
   * @param num object num
   * @return ĳ�����Ϊnum������
   */
  public E getCertainObject(int num);

  /**getCertainTrack.
   * �ҵ�ĳ�����Ϊnum�Ĺ��
   * @param num track num
   * @return ĳ�����Ϊnum�Ĺ��
   */
  public Track getCertainTrack(int num);

  /**addTrack.
   * ���ӹ��
   * @param t Ҫ���ӵĹ�� t
   */
  public void addTrack(Track t);

  /**deleteTrack.
   * ɾ�����
   * @param t Ҫɾ���Ĺ�� t
   */
  public void deleteTrack(Track t);

  /**setCentral.
   * �趨���ĵ�����
   * @param object ���ĵ����� object
   */
  public void setCentralObject(L object);

  /**addObjecttoTrack.
   * ��ĳ���������������
   * @param t ��� t
   * @param object ĳ������ object
   */
  public void addObjectToTrack(Track t, E object);


  /**readfile.
   * ��ȡ�ļ�
   * @param filename �ļ�·�� filename
   * @throws IOException IOE exception
   * @throws MyException  My exception
   */
  public void readFile(File filename) throws IOException, MyException;

  /**transit.
   * һ������ı���
   * @param object ���� object
   * @param t Ŀ���� t
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


