package circularOrbit;

import track.Track;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exception.MyException;
import physicalObject.PhysicalObject;


public class ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E> {
	
	// RI:
	// relation\ rel�е�E��Ӧ������objects
	// rel �е�Track Ӧ����tracks
	// E L�ֱ�Ҫ�������Ӧ���޶�����
	// AF:
	// ����һ�����ϵͳ
	// �ù��ϵͳ��L �������塢Track �����E ������������
	// tracks�д��й����Ϣ
	// relation�д��й��������֮��Ĺ�ϵ
	// rel�д洢ĳ������ϵ�������Ϣ
	// objects�д������й���ϵ�����
	// rel2�������嵽�����ڹ����ӳ���ϵ
	// Safety from rep exposure: mutable none
	
	// ��������
	public L central;
	// ���
	public List<Track> tracks = new ArrayList<Track>();
	// ��ϵӳ��
	public Map<E, List<E>> relation = new HashMap<E, List<E>>();
	// track �� ����Ĺ�ϵӳ��
	public Map<Track, ArrayList<E>> rel = new HashMap<Track, ArrayList<E>>();
	// ����
	public List<E> objects = new ArrayList<E>();
	
	// ���嵽�����ӳ��
	public Map<E, Track> rel2 = new HashMap<E, Track>();
	
	
	@Override
	public E getCertainObject(int num) {
		E object;
		for(int i = 0; i < objects.size(); i++) {
			object = objects.get(i);
			if(object instanceof PhysicalObject) {
				if(((PhysicalObject) object).getNum() == num) {
					return object;
				}
			}
		}
		return null;
	}
	
	
	@Override
	public Track getCertainTrack(int num) {
		for(int i = 0; i < tracks.size(); i++) {
			if(tracks.get(i).getNum() == num) {
				return tracks.get(i);
			}
		}
		return null;
	}
	
	
	@Override
	public void addTrack(Track t) {
		boolean flag = false;
		Iterator<Track> it = tracks.iterator();
		while (it.hasNext()) {
			if (it.next().getNum() == t.getNum()) {
				flag = true;
			}
		}

		if (flag) {
			// do nothing
		} else {
			rel.put(t, new ArrayList<E>());
			tracks.add(t);
		}
	}

	
	@Override
	public void deleteTrack(Track t) {
		Iterator<Track> it = tracks.iterator();
		while (it.hasNext()) {
			if (it.next().getNum() == t.getNum()) {
				it.remove();
				rel.remove(t);
			}
		}
	}

	
	@Override
	public void setCentralObject(L object) {
		this.central = object;
	}

	
	@Override
	public void addObjectToTrack(Track t, E object) {
		ArrayList<E> array = new ArrayList<E>();
		
		Iterator<Map.Entry<Track, ArrayList<E>>> it = rel.entrySet().iterator();
		while (it.hasNext()) {
			///�ҵ�ԭ���Ĺ��
			Map.Entry<Track, ArrayList<E>> entry = it.next();
			if(entry.getKey().equals(t)) {
				array = entry.getValue();
				if(array == null) {
					array = new ArrayList<E>();
				}
				array.add(object);
			}
		}
		
		rel.put(t, array);
		rel2.put(object, t);
	}

	/**
	 * ��ȡ�ļ�
	 * @throws MyException 
	 */
	@Override
	public void readFile(File filename) throws IOException, MyException {
		// need to be override
	}

	
	@Override
	public void transit(E object, Track t) {
		ArrayList<E> array = new ArrayList<E>();
		Track track = null;
		
		Iterator<Map.Entry<Track, ArrayList<E>>> it = rel.entrySet().iterator();
		while (it.hasNext()) {
			///�ҵ�ԭ���Ĺ��
			Map.Entry<Track, ArrayList<E>> entry = it.next();
			if(entry.getValue().contains(object)) {
				track = entry.getKey();
				array = entry.getValue();
				array.remove(object);
				it.remove();
			}
		}
		rel.put(track, array);
		
		Iterator<Map.Entry<Track, ArrayList<E>>> its = rel.entrySet().iterator();
		while (its.hasNext()) {
			///�ҵ��µĹ��
			Map.Entry<Track, ArrayList<E>> entry = its.next();
			if(entry.getKey().equals(t)) {
				track = entry.getKey();
				array = entry.getValue();
				array.add(object);
				its.remove();
			}
		}
		rel.put(t, array);
		
		if(object instanceof PhysicalObject) {
			rel2.put(object, t);
		}
	}
	
	/**
	 * ����get����
	 */
	@Override
	public int getTrackNum() {
		return tracks.size();
	}

	@Override
	public int getObjectNum() {
		return objects.size();
	}

	
	/**
	 * iterator���
	 */
	@Override
	public MyIterator<L, E> iterator() {
		return new CircularOrbitIterator<>(this);
	}

	
}
