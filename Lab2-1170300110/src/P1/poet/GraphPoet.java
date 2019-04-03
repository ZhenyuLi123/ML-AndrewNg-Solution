/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;
//import P1.graph.Vertex;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

	private final Graph<String> graph = Graph.empty();

	// Abstraction function:
	// TODO ����һ��Ӣ�ľ��ӣ�����ͼ�Ķ���ͱߵ���Ϣ�������������֮�����Ϊ2�������м���Ǹ����ʼ��������С�
	// �õ�һ����ֵ�Ӣ��"ʫ" (����
	// Representation invariant:
	// TODO ͼ������Ҫ��ѭconcreteVerticesGraphTest�е��޶�(һ���Ϸ���ͼ
	// ����String������Ҫû���������ص��ʵģ�
	// Safety from rep exposure:
	// TODO �ڲ�����ʹ��final����

	/**
	 * Create a new poet with the graph from corpus (as described above).
	 * 
	 * @param corpus text file from which to derive the poet's affinity graph
	 * @throws IOException if the corpus file cannot be found or read
	 */
	

	public GraphPoet(File corpus) throws IOException {
		/// ��ȡ�����ĵ�����
		ArrayList<String> arrayList = new ArrayList<>();
		InputStreamReader input = new InputStreamReader(new FileInputStream(corpus));
		BufferedReader bf = new BufferedReader(input);
		String str;
		while ((str = bf.readLine()) != null) {
			arrayList.add(str);
		}
		bf.close();
		input.close();

		
		/// �õ������ÿո������StringList
		ArrayList<String> stringList = new ArrayList<>();
		String poem = new String();
		for (int i = 0; i < arrayList.size(); i++) {
			poem = arrayList.get(i);
			String words[] = poem.split(" ");
			for (int j = 0; j < words.length; j++) {
				stringList.add(words[j]);
			}
		}

		/// ��Ҫ�������������ŵ�����
		ArrayList<String> stringList1 = new ArrayList<>();
		String[] arrayString;
		String word = new String();
		for (int i = 0; i < stringList.size(); i++) {
			word = stringList.get(i);
			arrayString = word.split("[. ! ? \' \" , @ :]");
			for (int j = 0; j < arrayString.length; j++) {
				if (!arrayString[j].isEmpty()) {
					word = arrayString[j];
					/// ���ǺϷ�����
					break;
				}
			}
			stringList1.add(word);
			/// �õ������ı��������� stringList1
		}

		/// ��ʼ����ͼ ����
		/// �����ֹ���ֵ���
		for (int i = 0; i < stringList1.size() - 1; i++) {
			int iNext = i + 1; /// ��һλ����
			if (!stringList1.get(i).toLowerCase().equals(stringList1.get(iNext).toLowerCase())) {
				/// ��Сд������
				graph.add(stringList1.get(i).toLowerCase());
			}
		}

		/// �ӱ�
		for (int i = 0; i < stringList1.size() - 1; i++) {
			int iNext = i + 1; /// ��һλ����
			if (!stringList1.get(i).toLowerCase().equals(stringList1.get(iNext).toLowerCase())) {
				/// ��Сд������
				graph.set(stringList1.get(i).toLowerCase(), stringList1.get(iNext).toLowerCase(), 1);
			}
		}
	}

	// TODO checkRep
	private void checkRep(String str) {
		/// ������input���д���
		String[] words;
		String[] word;
		String wd = new String();
		words = str.split(" ");
		ArrayList<String> strList = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			word = words[i].split("[. ! ? \\' \\\" , @ :]");
			for (int j = 0; j < word.length; j++) {
				if (!word[j].isEmpty()) {
					wd = word[j];
					/// ���ǺϷ�����
					break;
				}
			}
			/// ��ӽ���strList��
			strList.add(wd);
		}
		
		boolean flag = true;
		for (int i = 0; i < strList.size() - 1; i++) {
			int iNext = i + 1;
			/// �����ֵ���
			if (strList.get(i).toLowerCase().equals(strList.get(iNext).toLowerCase())) {
				flag = false;
			}
		}
		
		assert (flag == true);
	}
	/**
	 * Generate a poem.
	 * 
	 * @param input string from which to create the poem
	 * @return poem (as described above)
	 */
	public String poem(String input) {
		checkRep(input);
		/// ������
		ArrayList<String> resList = new ArrayList<>();
		String res = new String();

		/// Ҫ�����ȥ�ĵ���
		String insert = new String();

		/// ������input���д���
		String[] words;
		String[] word;
		String wd = new String();
		words = input.split(" ");
		ArrayList<String> strList = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			word = words[i].split("[. ! ? \\' \\\" , @ :]");
			for (int j = 0; j < word.length; j++) {
				if (!word[j].isEmpty()) {
					wd = word[j];
					/// ���ǺϷ�����
					break;
				}
			}
			/// ��ӽ���strList��
			strList.add(wd);
		}
		
		///����ֻ��һ��������������
		if(strList.size() == 1) {
			resList.add(strList.get(0).toLowerCase());
		}
		
		for (int i = 0; i < strList.size() - 1; i++) {
			int iNext = i + 1;
			/// Ĭ�ϲ����ֵ���
			/// ���ͼ����i�����
			if (haveVertex(strList.get(i).toLowerCase())) {
				/// �������·��Ϊ2�ĵ�
				if (haveRoad2(strList.get(i).toLowerCase(), strList.get(iNext).toLowerCase())) {
					insert = getWord(strList.get(i).toLowerCase(), strList.get(iNext).toLowerCase());
					resList.add(strList.get(i).toLowerCase());
					resList.add(insert.toLowerCase());
				} else {
					/// ��ֱ������ȥ
					resList.add(strList.get(i).toLowerCase());
				}
			} else {
				/// �����ֱ������ȥ
				resList.add(strList.get(i).toLowerCase());
			}

			if (i == strList.size() - 2) {
				resList.add(strList.get(iNext).toLowerCase());
			}

		}
		
		StringBuffer r = new StringBuffer();
		int num = 0;
		for(int i = 0; i < resList.size(); i++) {
			if(num == 0) {
				r.append(Character.toUpperCase(resList.get(i).charAt(0)));
				r.append(resList.get(i).substring(1));
				if(i == resList.size() - 1) {
					r.append("!");
				}else {
					r.append(" ");
				}
				num++;
			}else {
				r.append(resList.get(i));
				if(i == resList.size() - 1) {
					r.append("!");
				}else {
					r.append(" ");
				}
			}
		}
		res = r.toString();
		return res;
	}

	///ͼ���Ƿ���ĳ������
	private boolean haveVertex(String str) {
		if (graph.vertices().contains(str)) {
			return true;
		} else {
			return false;
		}
	}

	///����������Ƿ��г���Ϊ2��·��
	private boolean haveRoad2(String str1, String str2) {
		/// �������� que
		String[] que = new String[1000];
		int front = 0;
		int rear = 0;

		Map<String, Integer> vertex = graph.targets(str1);
		// ����map�еļ�
		for (String key : vertex.keySet()) {
			que[rear] = key;
			rear++;
		}

		for (int i = front; i < rear; i++) {
			vertex = graph.targets(que[i]);
			if (vertex.containsKey(str2)) {
				return true;
			}
		}

		return false;

	}

	///�ҵ��е��Ǹ��ڵ�
	private String getWord(String str1, String str2) {
		/// �������� que
		String[] que = new String[1000];
		int front = 0;
		int rear = 0;

		Map<String, Integer> vertex = graph.targets(str1);
		// ����map�еļ�
		for (String key : vertex.keySet()) {
			que[rear] = key;
			rear++;
		}

		for (int i = front; i < rear; i++) {
			vertex = graph.targets(que[i]);
			if (vertex.containsKey(str2)) {
				return que[i];
			}
		}
		
		return "Can't get here!";
	}

	// TODO toString()
	public String toString() {
		Set<String> vertices = new HashSet<String>();
		Map<String, Integer> e = new HashMap<String, Integer>();
		vertices = graph.vertices();
		
		StringBuffer str = new StringBuffer();
		str.append("Vertices:\n");
		for(String i : vertices) {
			str.append(i);
			str.append(" ");
		}
		str.append("\n");
		str.append("Edges:\n");
		for(String i : vertices) {
			e = graph.sources(i);
			for(String j : e.keySet()) {
				str.append(j);
				str.append(" -> ");
				str.append(i);
				str.append("\n");
			}
		}
		String ret = str.toString();
		return ret;
	}
 
//	public static void main(String[] args) throws IOException {
//		String path = "src" + File.separator + "P1" + File.separator + "poet";
//		File f = new File(path, "mugar-omni-theater.txt");
//		GraphPoet g = new GraphPoet(f);
//		String result = new String();
//		result = g.poem("Seek to explore new and exciting synergies!");
//		System.out.println(result);
//		System.out.println(g.toString());
//	}
}
