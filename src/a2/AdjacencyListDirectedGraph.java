package a2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyListDirectedGraph<V> implements directedGraph<V> {

	//B->A
	HashMap<V,HashMap<V,Double>> inAdjacencyList = new HashMap<V, HashMap<V,Double>>();
	HashMap<V,HashMap<V,Edge<V>>> inEdgeList = new HashMap<V, HashMap<V,Edge<V>>>();
	//A->B
	HashMap<V,HashMap<V,Double>> outAdjacencyList = new HashMap<V, HashMap<V,Double>>();
	HashMap<V,HashMap<V,Edge<V>>> outEdgeList = new HashMap<V, HashMap<V,Edge<V>>>();

	@Override
	public boolean addEdge(V v, V w) {
		// TODO Auto-generated method stub
		return addEdge(v,w,1.0);
	}

	@Override
	public boolean addEdge(V v, V w, double weight) {
		// TODO Auto-generated method stub
		if(!inAdjacencyList.containsKey(v))
			throw new Error("Vertex source is Missing!");
		if(!inAdjacencyList.containsKey(w))
			throw new Error("Vertex target is Missing!");
		
		if(inAdjacencyList.get(w).containsKey(v))
			throw new Error("Edge already defined!");
		
		Edge<V> newEdge = new Edge<V>(v, w);
		
		inAdjacencyList.get(w).put(v, weight);
		outAdjacencyList.get(v).put(w, weight);
		
		inEdgeList.get(w).put(v, newEdge);
		outEdgeList.get(v).put(w, newEdge);
		
		return true;
	}

	@Override
	public boolean addVertex(V v) {
		if(inAdjacencyList.containsKey(v))
			throw new Error("Vertex already defined!");
		
		inAdjacencyList.put(v,new HashMap<V, Double>());
		outAdjacencyList.put(v,new HashMap<V, Double>());
		
		inEdgeList.put(v, new HashMap<V, Edge<V>>());
		outEdgeList.put(v, new HashMap<V, Edge<V>>());
		
		return true;
	}

	@Override
	public boolean containsEdge(V v, V w) {
		if(!inAdjacencyList.containsKey(v))
			throw new Error("Vertex source not found");
		
		if(!inAdjacencyList.containsKey(w))
			throw new Error("Vertex target not found");
		
		return inAdjacencyList.get(w).containsKey(v);
	}

	@Override
	public boolean containsVertex(V v) {
		return inAdjacencyList.containsKey(v);
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		if(!inAdjacencyList.containsKey(v))
			throw new Error("Vertex not found");
		
		List<V> retVal = new LinkedList<V>();
		
		for(Entry<>)
		
		return retVal;
	}

	@Override
	public List<Edge<V>> getEdgeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfEdges() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfVertexes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<V> getVertexList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getWeight(V v, V w) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Edge<V>> getIncomingEdgeList(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInDegree(V v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOutDegree(V v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Edge<V>> getOutgoingEdgeList(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<V> getPredecessorVertexList(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<V> getSuccessorVertexList(V v) {
		// TODO Auto-generated method stub
		return null;
	}

}
