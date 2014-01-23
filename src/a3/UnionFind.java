package a3;


public class UnionFind {
	
	int[] parent;
	int size;
	
	public UnionFind(int n)
	{
		size = n;
		parent = new int[n];
		for(int i = 0;i<n;i++)
			parent[i] = -1;
	}
	
	public int size()
	{
		int i = 0;
		for(int y = 0;y<size;y++)
			if(parent[y] == -1)
				i++;
		return i;
	}
	
	public int getParent(int e1)
	{
		int retVal = e1;
		while(parent[retVal] != -1)
			retVal = parent[retVal];
		return retVal;
	}
	
	public void union(int e1, int e2)
	{
		int newSub = getParent(e2);
		parent[newSub] = e1;
	}
}
