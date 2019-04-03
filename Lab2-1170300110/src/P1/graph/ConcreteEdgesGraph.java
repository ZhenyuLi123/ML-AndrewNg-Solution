/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	// TODO ���� xָ��y x��y��Ȩ��Ϊweight ��ô����һ����e��e.source - x  e.target - y e.weight - weight
	// vertices ����ͼ�����ж���
	// edges ����ͼ�е����б�
	// Representation invariant:
	// TODO edges�߼���û���ظ�Ԫ�أ���һ���˵���һ���˲����ж���·��
	// û��һ���߻��Լ�ָ���Լ� ��������� e.source = e.target
	// Safety from rep exposure:
	// TODO vertices sources targets���Ƿ���һ���µ�(new)����
	// ÿ�����Զ��� private final����

	// TODO constructor

	public ConcreteEdgesGraph(Set<L> vertices, List<Edge<L>> edges) {
		for(L s : vertices) {
			this.vertices.add(s);
		}
		for(Edge<L> e : edges) {
			this.edges.add(e);
		}
		checkRep();
	}
	
	//����
	public ConcreteEdgesGraph() {
		this.vertices.clear();
		this.edges.clear();
		checkRep();
	}
	
	// TODO checkRep
	// ���ظ���
	private void checkRep() {
		boolean flag = false;
		Edge<L> e;
		for(int i = 0; i < edges.size() - 1; i++) {
			e = edges.get(i);
			for(int j = i + 1; j < edges.size(); j++) {
				if(e.equals(edges.get(j))) {
					flag = true;
				}
			}
		}
		assert (flag == false);
		
	}


	@Override
	public boolean add(L vertex) {
		if (vertices.contains(vertex) == true) {
			checkRep();
			return false;
		} else {
			checkRep();
			return vertices.add(vertex);

		}
	}

	@Override
	public int set(L source, L target, int weight) {
//		if(source.equals(target)) {
//			return 99;
//			///emmm ���Ϸ��ļӱ�
//		}
		if (weight != 0) {
			Edge<L> e = new Edge<L>(source, target, weight);
			boolean flag = false;
			for (int index = 0; index < edges.size(); index++) {
				if (edges.get(index).equals(e)) {
					// ɾ��֮��������
					edges.remove(index);
					edges.add(e);
					flag = true;
				}
			}
			if (flag == false) {
				edges.add(e);
			}
			checkRep();
			return 0;
		}else {
			int ret = 0;
			///Ϊ�˷����ѯ ����Ϊ1����edge repCheck����ȥ��
			Edge<L> e = new Edge<L>(source, target, 1);
			for (int index = 0; index < edges.size(); index++) {
				if (edges.get(index).equals(e)) {
					//��¼ֵ
					ret = edges.get(index).getWeight();
					//ɾ��
					edges.remove(index);
					//ֱ��over
					break;
				}
			}
			checkRep();
			return ret;
		}
	}

	@Override
	public boolean remove(L vertex) {
		boolean flag = false;
		
		Iterator<Edge<L>> it = edges.iterator();
		while(it.hasNext()){
			Edge<L> x = it.next();
			if(x.getSource().equals(vertex)||x.getTarget().equals(vertex)) {
				it.remove();
				flag = true;
			}
		}
		
		if(vertices.contains(vertex)) {
			vertices.remove(vertex);
			flag = true;
		}
		checkRep();
		return flag;
	}

	@Override
	public Set<L> vertices() {
		/// ���ɸı�
		Set<L> v = new HashSet<>();
		for(L s : vertices) {
			v.add(s);
		}
		/// ����һ������
		checkRep();
		return v;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Edge<L> e : edges) {
			if(e.getTarget().equals(target)) {
				map.put(e.getSource(), e.getWeight());
			}
		}
		checkRep();
		return map;
		
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Edge<L> e : edges) {
			if(e.getSource().equals(source)) {
				map.put(e.getTarget(), e.getWeight());
			}
		}
		checkRep();
		return map;
	}

	// TODO toString()
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		int index = 0;
		
		str.append("Vertices:\n");
		for(L i : vertices) {
			str.append(i);
			str.append(" ");
			index++;
		}
		str.append("\n");
		str.append("Edges:\n");
		for(int  i = 0; i < edges.size(); index = index + 2, i++) {
			str.append(edges.get(i).toString());
			str.append("\n");
		}
		
		
		String ret = str.toString();
		checkRep();
		return ret;
	}
	
//	public static void main(String[] args) {
//		Set<String> vertices = new HashSet<>();
//		List<Edge<String>> edges = new ArrayList<>();
//		ConcreteEdgesGraph<String> g = new ConcreteEdgesGraph<String>(vertices, edges);
//		g.add("Lc");
//    	g.add("Lzy");
//    	g.add("ShiLao");
//    	g.add("Ywq");
//    	
//    	g.set("Lc", "Lzy", 8);
//    	g.set("Lc", "ShiLao", 5);
//    	g.set("Lzy", "ShiLao", 3);
//    	g.set("Ywq", "Lc", 2);
//    	System.out.println(g.toString());
//	}

}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	// TODO fields ����
	private final L source;
	private final L target;
	private final int weight;
	// ��ʼ�� �յ� Ȩֵ

	// Abstraction function: ������
	// TODO ͼ����xָ��y ȨֵΪweight ��ô��Edge e e.source = x e.target = y e.weight = weight
	// Representation invariant: ������
	// TODO weight > 0; ÿ���ߵ�Ȩֵ����Ϊ��
	// source �� target����ͬ û��һ���߿���ָ���Լ�
	// this.source.equal(this.target) != true;
	// Safety from rep exposure: ��η�ֹ��¶
	// TODO ����source target weight ʹ�� private final 

	// TODO constructor ���캯�� //��Ҫɶдɶ
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}
	// TODO checkRep �����Щ������� ��鲻����
	// �����Լ�ָ���Լ� ȨֵΪ��
	private void checkRep() {
		assert (this.weight > 0);
		assert (this.source.equals(this.target) != true);
	}

	// TODO methods ����ʵ��һЩ���� ����

	public int getWeight(){
		checkRep();
		return this.weight;
	}
	public L getSource(){
		checkRep();
		return this.source;
	}
	public L getTarget(){
		checkRep();
		return this.target;
	}
	
	
	@Override
	public boolean equals(Object e) {
		if( ((Edge<?>) e).getSource().equals(this.source) && 
				((Edge<?>) e).getTarget().equals(this.target)) {
			checkRep();
			return true;
		}else {
			checkRep();
			return false;
		}
		
	}
	
	// TODO toString() ����һЩ
	@Override
	public String toString() {
		String res;
		res = this.source + " -> " + this.target + " \tweight: " + this.weight;
		checkRep();
		return res;
	}

}
