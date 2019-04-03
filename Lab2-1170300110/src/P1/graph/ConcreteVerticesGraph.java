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
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	// Abstraction function:
	// TODO ����һ��ͼ��ͼ������Ԫ��Ϊ���㼯��ÿ�����㺬�иõ�����(target ָ��õ��source��weight����������map
	// Representation invariant:
	// TODO ����ÿ�����㼯��target������ͬ������һ�����㼯�еĶ����map�е�source������õ�target��ͬ��weightҪ�����㡣
	// Safety from rep exposure:
	// TODO �������ĸ���Ȼ�󷵻�

	// TODO constructor
	public ConcreteVerticesGraph(List<Vertex<L>> vertices) {
		for(Vertex<L> v : vertices) {
			this.vertices.add(v);
		}
		checkRep();
	}
	public ConcreteVerticesGraph() {
		this.vertices.clear();
		checkRep();
	}
	
	// TODO checkRep
	private void checkRep() {
		for(Vertex<L> i : vertices) {
			Iterator<Map.Entry<L, Integer>> it = i.vertices.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<L, Integer> entry = it.next();
				assert (entry.getValue() > 0);
				assert (entry.getKey().equals(i.target) != true);
			}
		}
	}
		
	/// ��string.equel
	@Override
	public boolean add(L vertex) {
		///�����
		Vertex<L> v = new Vertex<L>(vertex);
		if(vertices.isEmpty()) {
			vertices.add(v);
			checkRep();
			return true;
		}else {
			for(Vertex<L> i : vertices) {
				//�������ظ��ľͲ����˷���false
				if(i.target.equals(vertex)) {
					checkRep();
					return false;
				}
			}
			vertices.add(v);
			checkRep();
			return true;
		}
	}

	@Override
	public int set(L source, L target, int weight) {
		if(weight != 0) {
			Vertex<L> v = new Vertex<L>(source, target, weight);
			for(Vertex<L> i : vertices) {
				///ͬһ�������
				if(i.equals(v)) {
					if(i.vertices.containsKey(source)) {
						i.vertices.replace(source, weight);
					}
					else {
						i.vertices.put(source, weight);
					}
				}
			}
			checkRep();
			return 0;
		}else {
			int ret = 0;
			Vertex<L> v = new Vertex<L>(source, target, 1);
			for(Vertex<L> i : vertices) {
				///������Ϊ�˺ò���
				if(i.equals(v)) {
					///ͬһ������
					if(i.vertices.containsKey(source)) {
						ret = i.vertices.get(source).intValue();
						///ɾ����������
						i.vertices.remove(source);
					}
				}
			}
			checkRep();
			return ret;
		}
	}

	@Override
	public boolean remove(L vertex) {
		boolean flag = false;
		
		Iterator<Vertex<L>> it = vertices.iterator();
		while(it.hasNext()){
			///�������㼯��
			///����������Ҫremove���Ǹ�vertex
		    Vertex<L> x = it.next();
		    if(x.target.equals(vertex)) {
				it.remove();
				flag = true;
			}
		  ///���ĳ�������source���ĸ�Ҫremove��vertex
			if(x.vertices.containsKey(vertex)) {
				x.vertices.remove(vertex);
				flag = true;
			}
		}
		checkRep();
		return flag;
	}

	@Override
	public Set<L> vertices() {
		Set<L> v = new HashSet<>();
		for(Vertex<L> i : vertices) {
			v.add(i.target);
		}
		checkRep();
		return v;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Vertex<L> i : vertices) {
			if(i.target.equals(target)) {
				Iterator<Map.Entry<L, Integer>> it = i.vertices.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<L, Integer> entry = it.next();
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		checkRep();
		return map;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Vertex<L> i : vertices) {
			if(i.vertices.containsKey(source)) {
				map.put(i.target, i.vertices.get(source));
			}
		}
		checkRep();
		return map;
	}

	// TODO toString()
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Vertices:\n");
		for(Vertex<L> i : vertices) {
			str.append(i.target);
			str.append(" ");
		}
		str.append("\n");
		str.append("Edges:\n");
		for(Vertex<L> v : vertices) {
			str.append(v.toString());
			str.append("\n");
		}

		String ret = str.toString();
		checkRep();
		return ret;
	}
	
//	public static void main(String[] args) {
//		ConcreteVerticesGraph<String> g = new ConcreteVerticesGraph<String>();
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
//	
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// TODO fields
	// source - weight
	public Map<L, Integer> vertices = new HashMap<L, Integer>();
	// target
	public L target;
	
	// Abstraction function:
	// TODO ��source��target��weight ����vertex �ö��㼯�е�targe��Ϊ����target��map����source��target��weight��ӳ�䡣
	// Representation invariant:
	// TODO target��vertices�е�key������ͬ��vertices�е�weight���������
	// Safety from rep exposure:
	// TODO Ϊ�ɱ����ͣ����Զ���Ϊpublic��ʽ

	// TODO constructor
	public Vertex() {
		vertices.clear();
		checkRep();
	}
	
	public Vertex(L name) {
		vertices.clear();
		//��û��sources
		this.target = name;
		checkRep();
	}
	
	public Vertex(L name, L target, int weight) {
		vertices.clear();
		vertices.put(name, new Integer(weight));
		this.target = target;
		checkRep();
	}

	// TODO checkRep
	private void checkRep() {
		Iterator<Map.Entry<L, Integer>> it = vertices.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<L, Integer> entry = it.next();
			assert (entry.getValue() > 0);
			assert (entry.getKey().equals(this.target) != true);
		}
	}

	// TODO methods
	
	@Override
	public boolean equals(Object e) {
		if( ((Vertex<?>) e).target.equals(this.target)) {
			checkRep();
			return true;
		}else {
			checkRep();
			return false;
		}
		
	}
	
	// TODO toString()
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		int num = 0;
		Iterator<Map.Entry<L, Integer>> it = vertices.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<L, Integer> entry = it.next();
			if(num == 0) {
				str.append(entry.getKey());
				str.append(" -> ");
				str.append(this.target);
				str.append("\t");
				str.append("weight: ");
				str.append(entry.getValue().toString());
				num++;
			}else {
				str.append("\n");
				str.append(entry.getKey());
				str.append(" -> ");
				str.append(this.target);
				str.append("\t");
				str.append("weight: ");
				str.append(entry.getValue().toString());
				num++;
			}
			
		}
		checkRep();
		return str.toString();
	}

}
