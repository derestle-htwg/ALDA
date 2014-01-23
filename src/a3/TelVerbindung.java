package a3;

public class TelVerbindung {
	public TelKnoten a;
	public TelKnoten b;
	
	public TelVerbindung(TelKnoten inA, TelKnoten inB)
	{
		a = inA;
		b = inB;
	}
	
	public int cost()
	{
		return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
	}
	
}
