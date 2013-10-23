package a1;

public class KeyValuePair<K,V> {
	private K Key;
	private V Value;
	
	public KeyValuePair(K inKey, V inValue)
	{
		Key = inKey;
		Value = inValue;
	}
	
	public K getKey()
	{
		return Key;
	}
	
	public V getValue()
	{
		return Value;
	}

	@Override
	public String toString() {
		return "<" + Key.toString() + ";" + Value.toString() + ">";
	}
}
