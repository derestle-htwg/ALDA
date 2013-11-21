package a2;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		graphTraversion<Integer> gt = new graphTraversion<Integer>();
		directedGraph<Integer> dg = new AdjacencyListDirectedGraph<Integer>();
		dg.addVertex(1);
		dg.addVertex(2);
		dg.addVertex(3);
		dg.addVertex(4);
		dg.addVertex(5);
		dg.addVertex(6);
		dg.addVertex(7);
		dg.addEdge(1, 2);
		dg.addEdge(2, 6);
		dg.addEdge(2, 7);
		dg.addEdge(1, 3);
		dg.addEdge(1, 5);
		dg.addEdge(5, 4);
		
		System.out.println(gt.breadthFirstSearch(dg, 1));
		System.out.println(gt.depthFirstSearch(dg, 1));
		
	}

}
