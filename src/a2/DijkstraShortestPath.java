package a2;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import a1.HashDictionary;
import a1.TreeDictionary;

public class DijkstraShortestPath<V> {

	private enum vertexStatus{alreadyVisited,PossibileVisit,notVisitable}
	private Graph<V> myGraph;
	private HashMap<V, V> prev;
	private HashMap<V,Double> visited;//bereits besucht
	private HashMap<V,Double> candidates;
	private List<V> shortestPath;
	private double shortestPathDistance;

	public DijkstraShortestPath(Graph<V> g)
	{
		myGraph = g;
		prev = new HashMap<V, V>();
		visited= new HashMap<V,Double>();
		candidates = new HashMap<V,Double>();

		for(V v : g.getVertexList())
		{
			prev.put(v, null);
			//visited.put(v, Double.MAX_VALUE);
		}
	}

	boolean searchShortestPath(V s, V t)
	{
		boolean movePossibile = true;
		boolean pathFound = false;

		V shortestNext = null;


		visited.put(s, 0.0);//start einfügen

		for(V v : myGraph.getAdjacentVertexList(s)){
			candidates.put(v, myGraph.getWeight(v, s));
			prev.put(v, s);
		}

		while((!pathFound) && movePossibile)
		{
		/*	String outval = "";
			for(V i : candidates.keySet())
				outval += i+" ";
			System.out.println(outval);*/
			double distance = Double.MAX_VALUE;
			//nächsten knoten finden
			if(candidates.size() == 0)
				movePossibile = false;

			for(Entry<V,Double> e : candidates.entrySet())
			{
				if(e.getValue() < distance)
				{
					distance = e.getValue();
					shortestNext = e.getKey();
				}
			}
			if(shortestNext.equals(t))
				pathFound = true;
			//nächsten Knoten besuchen
			//eintragen in besuchten Liste und entfernen aus KandidatenListe
			visited.put(shortestNext, distance);
			candidates.remove(shortestNext);
			
			//Listen auffrischen
			if(!pathFound)
			{
				for(V v : myGraph.getAdjacentVertexList(shortestNext))
				{
					if(visited.containsKey(v))
					{//evtl kürzeren Weg eintragen
						if((distance+myGraph.getWeight(v, shortestNext)) < visited.get(v))
						{
							prev.put(v,shortestNext);
							visited.put(v, distance+myGraph.getWeight(v, shortestNext));
						}
					}
					else if(candidates.containsKey(v))
					{//kandidaten aktualisieren
						if((distance+myGraph.getWeight(v, shortestNext)) < candidates.get(v))
						{
							prev.put(v,shortestNext);
							candidates.put(v, distance+myGraph.getWeight(v, shortestNext));
						}
					}
					else
					{//nicht vorhanden, als Kandidat eintragen
						candidates.put(v, distance+myGraph.getWeight(v, shortestNext));
						prev.put(v,shortestNext);
					}
				}
			}
		}
		if(pathFound)
		{
			shortestPathDistance = visited.get(t);
			shortestPath = new LinkedList<V>();

			V myNode = t;
			while(myNode != null)
			{
				shortestPath.add(myNode);

				myNode = prev.get(myNode);
			}
		}
		else{
			shortestPath = null;
			shortestPathDistance = Double.MAX_VALUE;
		}

		return pathFound;
	}

	List<V> getShortestPath()
	{	
		return shortestPath;
	}

	double getDistance()
	{
		return shortestPathDistance;
	}

	V startNode;
	HashMap<V,List<V>> ways;
	HashMap<V,Double> distances;

	boolean searchAllShortestPaths(V s)
	{
		boolean retVal = true; 
		startNode = s;
		ways = new HashMap<V, List<V>>();
		distances = new HashMap<V, Double>();

		for(V v : myGraph.getVertexList())
		{
			retVal &= searchShortestPath(s, v);
			ways.put(v, getShortestPath());
			distances.put(v, getDistance());
		}
		return retVal;
	}

	List<V> getShortestPathTo(V g)
	{
		return ways.get(g);
	}

	double getDistanceTo(V g)
	{
		return distances.get(g);
	}

}
