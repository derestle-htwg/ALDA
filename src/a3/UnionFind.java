package a3;

import java.util.HashMap;
import java.util.Map.Entry;

public class UnionFind {
	
	private HashMap<Integer,Integer> parent = new HashMap<Integer, Integer>();
	
	public UnionFind(int n)
	{
		for(int i = 0;i<n;i++)
			parent.put(i, null);
	}
	
	public int size()
	{
		int i = 0;
		for(Entry<Integer,Integer> e : parent.entrySet())
			if(e.getValue() == null)
				i++;
		return i;
	}
	
	public void union(int e1, int e2)
	{
		if(parent.get(e1) == null)
	}
}
