package a2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import a1.TreeDictionary;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test nt = new Test();
		nt.Start();
	}
	
	public void Start()
	{
		int MaxStation = 0;
		File F;
		
		java.util.Map<Integer, Integer> Taxi = new TreeMap<>();
		java.util.Map<Integer, Integer> Bus = new TreeMap<>();
		java.util.Map<Integer, Integer> Subway = new TreeMap<>();
		
		
		Graph<Integer> g = new AdjacencyListUndirectedGraph<Integer>();
		
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
	                		Subway.put(P1, P2);
	                	if(Type.equals("Taxi"))
	                		Taxi.put(P1, P2);
	                	if(Type.equals("Bus"))
	                		Bus.put(P1, P2);
                	}
            		else
            			break;
            	}
            }
    	}
        catch(Exception e){}//Dateiende oder Dateifehler -> aufhören
		
		for(Entry<Integer, Integer> a : Taxi.entrySet())
		{
			g.addEdge(a.getValue(), a.getKey(),3.0);
		}
		
		for(Entry<Integer, Integer> a : Bus.entrySet())
		{
			g.addEdge(a.getValue(), a.getKey(),2.0);
		}
		
		for(Entry<Integer, Integer> a : Subway.entrySet())
		{
			g.addEdge(a.getValue(), a.getKey(),5.0);
		}

		
	}
        

}
