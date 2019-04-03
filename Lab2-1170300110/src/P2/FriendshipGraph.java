package P2;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;

public class FriendshipGraph {

	private final Graph<Person> graph = Graph.empty();

	/**
	 * ��������//����һ����
	 * 
	 * @param name ����
	 * @return �Ƿ�ɹ�����
	 */
	public boolean addVertex(Person name) {

		boolean flag = true;

		Set<Person> vertices = new HashSet<Person>();
		vertices = graph.vertices();

		for (Person i : vertices) {
			if (i.getName().toLowerCase().equals(name.getName().toLowerCase())) {
				flag = false;
			}
		}

		if (flag == false) {
			System.out.println("Name same Error!");
			System.exit(0);
		} else {
			// ͼ�����Ӷ���
			graph.add(name);
		}

		return true;
	}

	/**
	 * ������֮�佨����ϵ ע�ⵥ����
	 * 
	 * @param name1
	 * @param name2
	 * @return �Ƿ����ӳɹ�
	 */
	public boolean addEdge(Person name1, Person name2) {

		/// ����ͼ�� �����������ͬ
		if (name1.getName().toLowerCase().equals(name2.getName().toLowerCase())) {
			return false;
		}

		/// ���ͼ�в���������֮�е�����һ������
		Set<Person> vertices = new HashSet<Person>();
		vertices = graph.vertices();
		boolean flag1 = false;
		boolean flag2 = false;

		for (Person i : vertices) {
			if (i.getName().toLowerCase().equals(name1.getName().toLowerCase())) {
				flag1 = true;
			}
			if (i.getName().toLowerCase().equals(name2.getName().toLowerCase())) {
				flag2 = true;
			}

		}

		if (flag1 && flag2) {
			graph.set(name1, name2, 1);
			return true;
		} else {
			return false;
		}

	}

	public int getDistance(Person name1, Person name2) {

		if (name1.getName().toLowerCase().equals(name2.getName().toLowerCase())) {
			return 0;
		}

		Set<Person> vertices = new HashSet<Person>();
		/// ���㼯��
		vertices = graph.vertices();
		int nums = vertices.size();

		/// �������
		Person que[] = new Person[999];
		int distance[] = new int[999];
		int front = 0;
		int rear = 0;

		que[0] = name1;
		distance[0] = 0;
		rear++;

		Map<Person, Integer> targets = new HashMap<Person, Integer>();
		while (front < nums) {
			targets = graph.targets(que[front]);
			for (Person i : targets.keySet()) {
				if (i.getName().toLowerCase().equals(name2.getName().toLowerCase())) {
					return distance[front] + 1;
				} else {
					que[rear] = i;
					distance[rear] = distance[front] + 1;
					rear++;
				}
			}
			front++;
		}

		return -1;
	}

	public static void main(String[] args) {

//		FriendshipGraph graph = new FriendshipGraph();
//		Person rachel = new Person("Rachel");
//		Person ross = new Person("Ross");
//		//Person ross = new Person("Rachel");
//		Person ben = new Person("Ben");
//		Person kramer = new Person("Kramer");
//		graph.addVertex(rachel);
//		graph.addVertex(ross);
//		graph.addVertex(ben);
//		graph.addVertex(kramer);
//		graph.addEdge(rachel, ross);
//		
//		graph.addEdge(ross, rachel);
//		
//		graph.addEdge(ross, ben);
//		graph.addEdge(ben, ross);
//		System.out.println(graph.getDistance(rachel, ross)); 
//		//should print 1
//		System.out.println(graph.getDistance(rachel, ben)); 
//		//should print 2
//		System.out.println(graph.getDistance(rachel, rachel)); 
//		//should print 0
//		System.out.println(graph.getDistance(rachel, kramer)); 
//		//should print -1

		FriendshipGraph FriendshipGraph = new FriendshipGraph();

		Person A = new Person("A");
		Person B = new Person("B");
		Person C = new Person("C");
		Person D = new Person("D");
		Person E = new Person("E");

		FriendshipGraph.addVertex(A);
		FriendshipGraph.addVertex(B);
		FriendshipGraph.addVertex(C);
		FriendshipGraph.addVertex(D);
		FriendshipGraph.addVertex(E);

		FriendshipGraph.addEdge(B, A);
		FriendshipGraph.addEdge(A, C);
		FriendshipGraph.addEdge(C, A);
		FriendshipGraph.addEdge(E, D);

		System.out.println(FriendshipGraph.getDistance(A, C));
		// should print 1
		System.out.println(FriendshipGraph.getDistance(B, E));
		// should print -1
		System.out.println(FriendshipGraph.getDistance(B, C));
		// should print 2
		System.out.println(FriendshipGraph.getDistance(A, A));
		// should print 0
	}

}
