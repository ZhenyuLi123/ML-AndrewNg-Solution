package P3;
import java.util.ArrayList;

import P3.Person;

public class FriendshipGraph {
	
	static int max = 10000;
	// �������ֵ
	static int inf = 9999; 
	// ����һ�������
	static int nums = 0;
	// ������
	static ArrayList<Person> vertex = new ArrayList<Person>();
	static ArrayList<String> people = new ArrayList<String>();
	// Person��һ���б�
	static int[][] distance = new int[max][max];
	// �����ά����
	
	/**
	 * ��������//����һ����
	 * @param name ����
	 * @return �Ƿ�ɹ�����
	 */
	public boolean addVertex(Person name) {

		
		if(people.contains(name.name)) {
			System.out.println("Name must be unique!!!");
			System.exit(0);
		}
		
		///������ô��person
		people.add(name.name);
		///������˼��뵽vertex�б���
		vertex.add(name);
		///������һ
		nums++;
		
		/// ��ʼ����������ά���� 
		for(int k = 0; k < nums; k++) {
			for(int j = 0; j < nums; j++) {
				distance[k][j] = inf;
			}
		}
		
		return true;
	}
	/**
	 * ������֮�佨����ϵ ע�ⵥ����
	 * @param name1
	 * @param name2
	 * @return �Ƿ����ӳɹ�
	 */
	public boolean addEdge(Person name1, Person name2) {
		
		
		int index1 = 0;
		int index2 = 0;
		
		///Ѱ�ҵ��������˵���vertex����±�
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name1.name)) {
				index1 = i;
			}
		}
		
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name2.name)) {
				index2 = i;
			}
		}
		
		///��ͼ�� ������֮��ľ����趨Ϊ1
		///ע��ֻ��һ��
		distance[index1][index2] = 1;
		
		return true;
	}
	
	/**
	 * �õ�����֮�����
	 * @param name1
	 * @param name2
	 * @return �о��뷵�ؾ��� ���û����ϵ ����-1 �����ͬһ���˷���0
	 */
	public int getDistance(Person name1, Person name2){
		///dijstla �㷨 ����ѭ��~
		for(int k = 0; k < nums; k++) {
			for(int i = 0; i < nums; i++) {
				for(int j = 0; j < nums; j++) {
					if(distance[i][k] + distance[k][j] < distance[i][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		
		///Ѱ���������ֵ��±�λ��
		int index1 = 0;
		int index2 = 0;
		
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name1.name)) {
				index1 = i;
			}
		}
		
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name2.name)) {
				index2 = i;
			}
		}
		
		///������������ ��ͬһ���� ��ô���ؾ���0
		if(index1 == index2) {
			return 0;
		}
		
		///���������� ������֮��û�о��� ����-1
		if(distance[index1][index2] == inf) {
			return -1;
		}
		
		return distance[index1][index2];
	}
	
	public static void main(String[] args) {
		
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		//Person ross = new Person("Rachel");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		
		graph.addEdge(ross, rachel);
		
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross)); 
		//should print 1
		System.out.println(graph.getDistance(rachel, ben)); 
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel)); 
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer)); 
		//should print -1
	}
}
