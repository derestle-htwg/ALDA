package a2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {
	
	//könnte auch alles in EdgeListe
	HashMap<V,HashMap<V,Double>> adjacencyList = new HashMap<V, HashMap<V,Double>>();
	HashMap<V,HashMap<V,Edge<V>>> edgeList = new HashMap<V, HashMap<V,Edge<V>>>();
	
	
	@Override
	public int getDegree(V v) {
		if(adjacencyList.containsKey(v))
		{
			return adjacencyList.get(v).size();
		}
		else			
			throw new Error("Vertex not found!");
	}

	@Override
	public boolean addEdge(V v, V w) {
		return addEdge(v,w,1.0);
	}
	
	@Override
	public boolean addEdge(V v, V w, double weight) {
		if(!adjacencyList.containsKey(v))
		{
			throw new Error("Vertex source not found!");
		}
		if(!adjacencyList.containsKey(w))
		{
			throw new Error("Vertex target not found!");
		}
		
		if(adjacencyList.get(v).containsKey(w))
			throw new Error("Edge already defined!");
		
		adjacencyList.get(v).put(w, weight);
		adjacencyList.get(w).put(v, weight);
		
		Edge<V> newEdge = new Edge<V>(v,w);
		edgeList.get(v).put(w,newEdge);
		edgeList.get(w).put(v,newEdge);
		
		return true;
	}

	@Override
	public boolean addVertex(V v) {
		if(!adjacencyList.containsKey(v))
		{
			adjacencyList.put(v, new HashMap<V, Double>());
			edgeList.put(v,new HashMap<V, Edge<V>>());
			return true;
		}
		throw new Error("Vertex already there!");
	}

	@Override
	public boolean containsEdge(V v, V w) {
		// TODO Auto-generated method stub
		if(!adjacencyList.containsKey(v))
		{
			throw new Error("Vertex source not found!");
		}
		if(!adjacencyList.containsKey(w))
		{
			throw new Error("Vertex arget not found!");
		}
		return adjacencyList.get(v).containsKey(w);
	}

	@Override
	public boolean containsVertex(V v) {
		// TODO Auto-generated method stub
		return adjacencyList.containsKey(v);
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		if(!adjacencyList.containsKey(v))
			throw new Error("Vertex not found!");
		List<V> retVal = new LinkedList<V>();
			
		for(V entry : adjacencyList.get(v).keySet())
		{
			retVal.add(entry);
		}
		return retVal;
	}

	@Override
	public List<Edge<V>> getEdgeList() {
		
		List<Edge<V>> retVal = new LinkedList<Edge<V>>();
			
		for(Entry<V, HashMap<V, Edge<V>>> a : edgeList.entrySet())
		{
			for(Entry<V,Edge<V>> b : a.getValue().entrySet())
			{
				if(!retVal.contains(b))
					retVal.add(b.getValue());
			}
		}

		return retVal;
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		if(!edgeList.containsKey(v))
			throw new Error("Vertex not found!");
		
		List<Edge<V>> retVal = new LinkedList<Edge<V>>();
		
		for(Entry<V,Edge<V>> b : edgeList.get(v).entrySet())
		{
			if(!retVal.contains(b))
				retVal.add(b.getValue());
		}
		
		return retVal;
	}

	@Override
	public int getNumberOfEdges() {
			
		return getEdgeList().size();
	}

	@Override
	public int getNumberOfVertexes() {
		return edgeList.size();
	}

	@Override
	public List<V> getVertexList() {
		List<V> retVal = new LinkedList<V>();
		retVal.addAll(edgeList.keySet());
		return retVal;
	}

	@Override
	public double getWeight(V v, V w) {
		if(!adjacencyList.containsKey(v))
			throw new Error("Vertex source not Found");
		if(!adjacencyList.get(v).containsKey(w))
			throw new Error("Vertex target not Found");
		
		return adjacencyList.get(v).get(w);
	}

}
