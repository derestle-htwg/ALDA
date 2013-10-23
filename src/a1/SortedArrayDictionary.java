package a1;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class SortedArrayDictionary <K extends Comparable<? super K>,V>{
	
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
	
	@SuppressWarnings("unchecked")
	public V Insert(K Key, V Value)	{
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
	
	private int getInsertPosition(K key)
	{
		if(elements == 0)
			return 0;
		
		int re = elements-1;
		int li = 0;
		int m = 0;
		
		while (re >= li) {
			m = (li + re)/2;
			if (key.compareTo(Data[m].getKey()) < 0)
			re = m - 1;
			else if (key.compareTo(Data[m].getKey()) > 0)
			li= m + 1;
			else
			return m; // key gefunden
			}
		
		if (key.compareTo(Data[m].getKey()) > 0)
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
