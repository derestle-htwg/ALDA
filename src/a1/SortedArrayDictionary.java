package a1;

public class SortedArrayDictionary <K,V> implements Dictionary<K,V>{
	
	private KeyValuePair<K,V> Data[];
	private int size;
	private int elements;

	@SuppressWarnings("unchecked")
	public SortedArrayDictionary()
	{
		elements = 0;
		size = 1;
		Data = new KeyValuePair[size];
	}
	
	public int SearchKey(K key)
	{
		int re = elements-1;
		int li = 0;
		
		while (re >= li) {
			int m = (li + re)/2;
			if (Compare(key,Data[m].getKey()) < 0)
			re = m - 1;
			else if (Compare(key,Data[m].getKey()) > 0)
			li= m + 1;
			else
			return m; // key gefunden
			}
			return -1; // key nicht gefunden

	}
	
	public V search(K Key){
		V Value = null;
		
		int pos = SearchKey(Key);
		
		if(pos != -1)
			Value = Data[pos].getValue();
		
		return Value;
	}
	
	@SuppressWarnings("unchecked")
	public V insert(K Key, V Value)	{
		int Position = SearchKey(Key);
		
		if(Position != -1)
		{//ersetzen
			V OldValue = Data[Position].getValue();
			
			Data[Position] = new KeyValuePair<K, V>(Key, Value);
			
			return OldValue;
		}
		else
		{//einfügen
			int insertPosition = getInsertPosition(Key);
			
			if(elements == size)
			{//neues Array
				//Array Kopieren Daten VOR meinen Key
				KeyValuePair<K,V> OldData[] = Data;
				size *= 2;
				
				Data = new KeyValuePair[size];
				for(int i = 0;i<insertPosition;i++)
					Data[i] = OldData[i];
				Data[insertPosition] = new KeyValuePair<K, V>(Key, Value);
				for(int i = insertPosition;i<OldData.length;i++)
					Data[i+1] = OldData[i];
			}
			else
			{//nur einfügen
				for(int i = elements;i>insertPosition;i--)
					Data[i] = Data[i-1];
				Data[insertPosition] = new KeyValuePair<K, V>(Key, Value);
			}
			elements++;
		}
				
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int Compare(K k1, K k2)
	{
		Object o1 = k1;
		Object o2 = k2;
		
		if(o1.getClass().isAssignableFrom(Comparable.class))
		{
			Comparable c1 = (Comparable) o1;
			Comparable c2 = (Comparable) o2;
			return c1.compareTo(c2);
		}
		else
		{
			return o1.toString().compareTo(o2.toString());
		}
	}
	
	public V remove(K key)
	{
		int position = SearchKey(key);
		V oldVal = null;
		
		if(position != -1)
		{
			oldVal = Data[position].getValue();
			Data[position] = null;
			elements--;
			for(int i = position;i<elements;i++)
			{	
				Data[i] = Data[i+1];
				Data[i+1] = null;
			}
			
		}
		return oldVal;
	}
	
	private int getInsertPosition(K key)
	{
		if(elements == 0)
			return 0;
		
		int re = elements-1;
		int li = 0;
		int m = 0;
		
		while (re >= li) {
			m = (li + re)/2;
			if (Compare(key,Data[m].getKey()) < 0)
			re = m - 1;
			else if (Compare(key,Data[m].getKey()) > 0)
			li= m + 1;
			else
			return m; // key gefunden
			}
		
		if (Compare(key,Data[m].getKey()) > 0)
			m++;
		
		return m;
	}
	
	@Override
	public String toString()
	{
		String retVal = "[" + size + ":" + elements + " - ";
		for(int i = 0;i<Data.length;i++)
		{
			if(i != 0)
				retVal += "; ";
			if(Data[i] == null)
				retVal += " - ";
			else
				retVal += Data[i].toString();
		}
		return retVal + "]";
	}
}
