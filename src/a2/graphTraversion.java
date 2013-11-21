package a2;

import java.util.LinkedList;
import java.util.List;

public class graphTraversion<V> {
	
	public List<V> depthFirstSearch(Graph<V> g, V s){return depthSearch(g,s,new LinkedList<V>());}
	
	private List<V> depthSearch(Graph<V> g, V v, List<V> l)
	{
		if(!l.contains(v))
		{
			l.add(v);
			for(V s : g.getAdjacentVertexList(v))
				depthSearch(g, v, l);
		}
		return l;
	}
	
	public List<V> breadthFirstSearch(Graph<V> g, V s){return breadthSearch(g, s, new LinkedList<V>());}
	
	private List<V> breadthSearch(Graph<V> g, V v, List<V> l)
	{
		if(!l.contains(v))
		{
			l.add(v);
		}
		return l;
	}
	
	public List<V> topologicalSort(directedGraph<V> g){return null;}



}
