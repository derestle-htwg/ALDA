package a2;

public interface Graph<V> {
	boolean addEdge(V v, V w);
	boolean addEdge(V v, V w, double weight);
	boolean addVertex(V v);
	boolean containsEdge(V v, V w);
	boolean containsVertex(V v);
	java.util.List<V> getAdjacentVertexList(V v);
	java.util.List<Edge<V>> getEdgeList();
	java.util.List<Edge<V>> getIncidentEdgeList(V v);
	int getNumberOfEdges();
	int getNumberOfVertexes();
	java.util.List<V> getVertexList();
	double getWeight(V v, V w);
	
}
