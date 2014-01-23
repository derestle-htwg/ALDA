package a3;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class TelNet implements Comparator<TelVerbindung>{
	
	LinkedList<TelKnoten> nodes = new LinkedList<TelKnoten>();
	LinkedList<TelVerbindung> connections = new LinkedList<TelVerbindung>();
	
	int sizeX;
	int sizeY;
	int lbg = 100;
	
	void addTelKnoten(int x, int y)
	{
		nodes.add(new TelKnoten(x,y));
	}
	void addTelKnoten(int x, int y, int ID)
	{
		nodes.add(new TelKnoten(x,y,ID));
	}
	
	void computeOptTelNet(){
		UnionFind u = new UnionFind(nodes.size());
		PriorityQueue<TelVerbindung> pq = new PriorityQueue<TelVerbindung>(1,this);
		
		for(int i = 0; i<nodes.size();i++)
			for(int y = i+1;y<nodes.size();y++)
			{
				TelVerbindung nv = new TelVerbindung(nodes.get(i), nodes.get(y));
				if(nv.cost() <= lbg)
					pq.add(nv);
			}
		
		while(u.size() > 1)
		{
			TelVerbindung v = pq.remove();
			if(u.getParent(v.a.id) != u.getParent(v.b.id))
			{//verbindung erstellen
				connections.add(v);
				u.union(u.getParent(v.a.id), u.getParent(v.b.id));
			}
		}
		
	}

	void drawOptTelNet(int xMax, int yMax)
	{
		
		generateRandomTelNet(1000,xMax,yMax);
		
		StdDraw.setXscale(0, sizeX);
		StdDraw.setYscale(0, sizeY);
		
		for(TelKnoten t : nodes)
		{
			StdDraw.filledRectangle(t.x+0.5, t.y+0.5, 0.5,0.5);
		}
		
		computeOptTelNet();
		
		StdDraw.setPenColor(StdDraw.RED);
		for(TelVerbindung c : connections)
		{
			StdDraw.line(c.a.x+0.5, c.a.y+0.5, c.a.x+0.5, c.b.y+0.5);
			StdDraw.line(c.b.x+0.5, c.b.y+0.5, c.a.x+0.5, c.b.y+0.5);
		}
		
	}
	
	void generateRandomTelNet(int n, int xMax, int yMax)
	{
		sizeX = xMax;
		sizeY = yMax;
		java.util.Date date = new Date();
		Random r = new Random(date.getTime());
		for(int i =0;i<n;i++)
			addTelKnoten(r.nextInt(xMax),r.nextInt(yMax),i);
	}
	
	java.util.List<TelVerbindung> getOptTelNet()
	{
		return connections;
	}
	
	int	getOptTelNetKosten(){return 0;}
	
	public static void	main(java.lang.String[] args){
		
		StdDraw.setCanvasSize(1000, 1000);
		TelNet tn = new TelNet();
		tn.drawOptTelNet(1000, 1000);
		
	}

	@Override
	public int compare(TelVerbindung o1, TelVerbindung o2) {
		if(o1.cost() > o2.cost())
			return 1;
		if(o1.cost() == o2.cost())
			return 0;
		return -1;
	}
}
