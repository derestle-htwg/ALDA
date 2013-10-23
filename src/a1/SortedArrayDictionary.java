package a1;

public class SortedArrayDictionary <K extends Comparable<? super K>,V>{
	
	private KeyValuePair<K,V> Data[];
	private int size;

	@SuppressWarnings("unchecked")
	public SortedArrayDictionary()
	{
		size = 1;
		Data = new KeyValuePair[size];
	}
	
	public int SearchKey(K key)
	{
		int re = size-1;
		int li = 0;
		
		while (re >= li) {
			int m = (li + re)/2;
			if (key.compareTo(Data[m].getKey()) < 0)
			re = m - 1;
			else if (key.compareTo(Data[m].getKey()) > 0)
			li= m + 1;
			else
			return m; // key gefunden
			}
			return -1; // key nicht gefunden

	}
	
	public V Search(K Key){
		V Value = null;
		
		int pos = SearchKey(Key);
		
		if(pos != -1)
			Value = Data[pos].getValue();
		
		return Value;
	}
	
	public V Insert(K Key, V Value)	{
		int Position = SearchKey(Key);
		
		return null;
	}
}
