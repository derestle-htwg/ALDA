package a2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import sim.SYSimulation;
import a1.TreeDictionary;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test nt = new Test();
		nt.Start();
		//nt.demo();
		nt.AnimateWay(192,22);
	}

	java.util.Map<Integer, List<Integer>> Taxi = new HashMap<Integer, List<Integer>>();
	java.util.Map<Integer, List<Integer>> Bus = new HashMap<Integer, List<Integer>>();
	java.util.Map<Integer, List<Integer>> Subway = new HashMap<Integer, List<Integer>>();
	SYSimulation sim;

	public void AnimateWay(int s, int t)
	{
		try {
			if(sim == null)
				sim = new SYSimulation();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		sim.startSequence("Test1");
		
		DijkstraShortestPath<Integer> d = new DijkstraShortestPath<Integer>(g);

		d.searchShortestPath(s, t);

		int prev = 0;

		for(Integer lst : d.getShortestPath())
		{
			if(prev != 0)
			{
				switch((int)g.getWeight(prev, lst))
				{
				case 3://taxi
					sim.drive(prev,lst, Color.YELLOW);
					break;
				case 2://Bus
					sim.drive(prev,lst, Color.RED);
					break;
				case 5:
					sim.drive(prev,lst, Color.green);
					break;
				}
			}
			prev = lst;
		}
		sim.stopSequence();
	}

	public void demo()
	{

		try {
			if(sim == null)
				sim = new SYSimulation();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		sim.startSequence("Test1");
		for(Entry<Integer, List<Integer>> a : Taxi.entrySet())
		{
			for(Integer b : a.getValue())
				sim.drive(a.getKey(), b, Color.YELLOW);
		}

		for(Entry<Integer, List<Integer>> a : Bus.entrySet())
		{
			for(Integer b : a.getValue())
				sim.drive(a.getKey(), b, Color.RED);
		}

		for(Entry<Integer, List<Integer>> a : Subway.entrySet())
		{
			for(Integer b : a.getValue())
				sim.drive(a.getKey(), b, Color.green);
		}

		/*
        sim.visitStation(9);
        sim.visitStation(20);
        sim.visitStation(33);
        sim.visitStation(46);
        sim.visitStation(79);
        sim.visitStation(63);
		sim.drive(9, 20, Color.YELLOW);
        sim.drive(20, 33, Color.YELLOW);
        sim.drive(33, 46, Color.YELLOW);
        sim.drive(46, 79, Color.RED.darker());
        sim.drive(79, 63, Color.GREEN.darker());*/
		sim.stopSequence();
	}

	Graph<Integer> g = new AdjacencyListUndirectedGraph<Integer>();

	public void Start()
	{
		int MaxStation = 0;
		File F;



		F = new File(this.getClass().getResource("ScotlandYard.txt").getPath());
		Scanner s = null;

		try
		{
			s = new Scanner( new BufferedReader(new FileReader(F)));
			while (s.hasNext()) {
				String line = s.nextLine();
				if(line != null)
				{
					if(line.split("\\s").length == 3)
					{
						String S1 = line.split("\\s")[0];
						String S2 = line.split("\\s")[1];
						String Type = line.split("\\s")[2];

						int P1 = Integer.parseInt(S1);
						int P2 = Integer.parseInt(S2);

						if(!g.containsVertex(P1))
							g.addVertex(P1);

						if(!g.containsVertex(P2))
							g.addVertex(P2);

						if(MaxStation < P1)
							MaxStation = P1;
						if(MaxStation < P2)
							MaxStation = P2;

						if(Type.equals("UBahn"))
						{
							if(!Subway.containsKey(P1))
								Subway.put(P1, new LinkedList<Integer>());
							Subway.get(P1).add(P2);
						}
						if(Type.equals("Taxi"))
						{
							if(!Taxi.containsKey(P1))
								Taxi.put(P1, new LinkedList<Integer>());
							Taxi.get(P1).add(P2);
						}
						if(Type.equals("Bus"))
						{
							if(!Bus.containsKey(P1))
								Bus.put(P1, new LinkedList<Integer>());
							Bus.get(P1).add(P2);
						}
					}
					else
						break;
				}
			}
		}
		catch(Exception e){}//Dateiende oder Dateifehler -> aufhören

		for(Entry<Integer, List<Integer>> a : Bus.entrySet())
		{
			for(Integer b : a.getValue())
				g.addEdge(a.getKey(), b,2.0);
		}

		for(Entry<Integer, List<Integer>> a : Taxi.entrySet())
		{
			for(Integer b : a.getValue())
				if(!g.containsEdge(a.getKey(), b))
					g.addEdge(a.getKey(), b,3.0);
		}

		for(Entry<Integer, List<Integer>> a : Subway.entrySet())
		{
			for(Integer b : a.getValue())
				if(!g.containsEdge(a.getKey(), b))
					g.addEdge(a.getKey(), b,5.0);
		}


	}


}
