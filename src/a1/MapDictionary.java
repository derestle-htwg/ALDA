package a1;

import java.util.Map;

public class MapDictionary<K, V> implements Dictionary<K, V> {
	
	private Map<K,V> myMap;
	
	public MapDictionary(Map<K,V> inMap){myMap = inMap;}
	
	public V insert(K key, V value){return myMap.put(key, value);}
    // Associates the specified value with the specified key in this map.
    // If the map previously contained a mapping for the key,
    // the old value is replaced by the specified value.
    // Returns the previous value associated with key,
    // or null if there was no mapping for key.

	public V search(K key){return myMap.get(key);}
    // Returns the value to which the specified key is mapped,
    // or null if this map contains no mapping for the key.

	public V remove(K key){return myMap.remove(key);}
    // Removes the key-vaue-pair associated with the key.
    // Returns the value to which the key was previously associated,
    // or null if the key is not contained in the dictionary.
}
