package a2;

public class Edge<V> {
	protected V source;
	protected V target;
	protected double weight;
	
	public Edge(V source, V target)
	{
		this(source,target,1.0);
	}
	
	public Edge(V source, V target, double weight)
	{
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	
	public V getSource()
	{
		return source;
	}
	
	public V getTarget()
	{
		return target;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	@Override
	public String toString()
	{
		return "[" + source.toString() + "-" + target.toString() + "]";
	}
}
