package a2;

import java.util.Dictionary;
import java.util.List;

import a1.TreeDictionary;

public class DijkstraShortestPath<V> {
	
	private enum vertexStatus{alreadyVisited,PossibileVisit,notVisitable}
	private Graph<V> myGraph;
	private a1.TreeDictionary<V, V> prev;
	private a1.TreeDictionary<V,Float> dist;
	private a1.TreeDictionary<V,vertexStatus> status;
	
	public DijkstraShortestPath(Graph<V> g)
	{
		myGraph = g;
		prev = new a1.TreeDictionary<V, V>();
		dist= new TreeDictionary<V,Float>();
		status = new TreeDictionary<V,vertexStatus>();
		
		for(V v : g.getVertexList())
		{
			prev.insert(v, null);
			dist.insert(v, Float.MAX_VALUE);
			status.insert(v, vertexStatus.notVisitable);
		}
	}
	
	boolean searchShortestPath(V s, V t)
	{
		boolean movePossibile = true;
		boolean pathFound = false;
		
		V shortestNext = null;
		float distance = Float.MAX_VALUE;
		
		dist.insert(shortestNext, 0.0f);
		
		while(!pathFound && movePossibile)
		{
			for(V v : myGraph.get)
		}
		
		return pathFound;
	}
	
	double getDistance()
	{
		return 0;
	}
	
	boolean searchAllShortestPaths(V s)
	{
		return false;
	}
	
	List<V> getShortestPathTo(V g)
	{
		return null;
	}
	
	double getDistanceTo(V g)
	{
		return 0;
	}

}
