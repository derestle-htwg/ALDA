package a2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class graphTraversion<V> {
	
	public List<V> depthFirstSearch(Graph<V> g, V s){return depthSearch(g,s,new LinkedList<V>());}
	
	private List<V> depthSearch(Graph<V> g, V v, List<V> l)
	{
		if(!l.contains(v))
		{
			l.add(v);
			for(V s : g.getAdjacentVertexList(v))
				depthSearch(g, s, l);
		}
		return l;
	}
	
	public List<V> breadthFirstSearch(Graph<V> g, V s){return breadthSearch(g, s);}

	
	private List<V> breadthSearch(Graph<V> g, V v)
	{
		List<V> l = new LinkedList<V>();
		Queue<V> q = new LinkedList<V>();
		V actElem = v;
		while(actElem != null)
		{
			if(!l.contains(actElem))
				l.add(actElem);
			
			for(V s : g.getAdjacentVertexList(actElem))
			{
				if(!q.contains(s) && !l.contains(s))
					q.add(s);
			}
			actElem = q.poll();
		}
		
		return l;
	}
	
	public List<V> topologicalSort(directedGraph<V> g){return null;}



}
