package a1;

public class HashDictionary<K,V> implements Dictionary<K, V> {
	
	private class LLEntry<K,V>
	{
		public LLEntry(KeyValuePair<K, V> Data)
		{
			Element = Data;
		}
		KeyValuePair<K, V> Element;
		LLEntry<K,V> next;
	}

	private int Elements = 0;
	private LLEntry<K,V>[] HashList;
	
	@SuppressWarnings("unchecked")
	public HashDictionary()
	{
		HashList = new LLEntry[7];
	}
	
	@SuppressWarnings({ "unchecked" })
	private void resize(int newSize)
	{
		if(newSize <= 0)
			return;
		Elements = 0;
		System.out.println("Resize");
		LLEntry<K,V>[] newList = new LLEntry[newSize];
		for(int i = 0;i<HashList.length;i++)
		{
			int counter = 0;
			LLEntry<K,V> transferEntry = HashList[i];
			while(transferEntry != null)
			{
				insert(transferEntry.Element.getKey(),transferEntry.Element.getValue(),newList);
				transferEntry = transferEntry.next;
				counter++;
			}
			System.out.println(i + " \t" + counter);
		}
		
		HashList = newList;
	}
	
	private V insert(K key, V value, LLEntry<K, V>[] HashList){
		
		if(Elements > HashList.length*4)
			resize(HashList.length*2);
		
		V oldValue = null;
		int hash = key.hashCode();
		KeyValuePair<K, V> newKVP = new KeyValuePair<K, V>(key, value);
		if(hash < 0)
			hash = -hash;
		
		hash = hash % HashList.length;
		
		if(HashList[hash] == null)
		{//erster Eintrag, einfach hinzufügen.
			HashList[hash] = new LLEntry<K, V>(newKVP);
		}
		else
		{//in Liste suchen und einsetzen
			LLEntry<K,V> LLElem = HashList[hash];
			while(LLElem.next != null && !LLElem.Element.getKey().equals(key))
			{//solange nicht letztes oder gleiches Element, weitersuchen
				LLElem = LLElem.next;
			}
			if(LLElem.Element.getKey().equals(key))
			{//gleich, austauschen
				oldValue = LLElem.Element.getValue();
				LLElem.Element = newKVP;
			}
			else//hinzufügen
			{
				LLElem.next = new LLEntry<K, V>(newKVP);
			}
		}
		if(oldValue == null)
			Elements++;
		return oldValue;
	}

	public V insert(K key, V value){return insert(key,value,HashList);}
    // Associates the specified value with the specified key in this map.
    // If the map previously contained a mapping for the key,
    // the old value is replaced by the specified value.
    // Returns the previous value associated with key,
    // or null if there was no mapping for key.

    public V search(K key){
    	int hash = key.hashCode();
    	if(hash < 0)
			hash = -hash;
    	hash = hash % HashList.length;
    	LLEntry<K,V> ActEntry = HashList[hash];
    	while(ActEntry != null)
    	{
    		if(ActEntry.Element.getKey().equals(key))
    			return ActEntry.Element.getValue();
    		ActEntry = ActEntry.next;
    	}
    	return null;
    }
    // Returns the value to which the specified key is mapped,
    // or null if this map contains no mapping for the key.
    
    public V remove(K key){
    	V retVal = null;
    	int hash = key.hashCode();
    	if(hash < 0)
			hash = -hash;
    	hash = hash % HashList.length;
    	
    	if(HashList[hash] == null)
    		return null;
    	
    	if(HashList[hash].Element.getKey().equals(key))
    	{
    		retVal = HashList[hash].Element.getValue(); 
    		HashList[hash] = HashList[hash].next;
    	}
    	else
    	{	
	    	LLEntry<K,V> LLElem = HashList[hash];
			while(LLElem.next != null && !LLElem.next.Element.getKey().equals(key))
			{//solange nicht letztes oder gleiches Element, weitersuchen
				LLElem = LLElem.next;
			}
			if(LLElem.next == null)
				return null;
			else
			{
				retVal = LLElem.next.Element.getValue();
				
				LLElem.next = LLElem.next.next;
			}
    	}
    	if(retVal != null)
    		Elements--;
    	return retVal;
    }
    // Removes the key-vaue-pair associated with the key.
    // Returns the value to which the key was previously associated,
    // or null if the key is not contained in the dictionary.
}
