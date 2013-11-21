package a2;

public interface directedGraph<V> extends Graph<V> {
	java.util.List<Edge<V>> getIncomingEdgeList(V v);
	int getInDegree(V v);
	int getOutDegree(V v);
	java.util.List<Edge<V>> getOutgoingEdgeList(V v);
	java.util.List<V> getPredecessorVertexList(V v);
	java.util.List<V> getSuccessorVertexList(V v);
}
