package a1;

public class TreeDictionary<K, V> implements Dictionary<K, V> {
	public V insert(K key, V value){return null;}
    // Associates the specified value with the specified key in this map.
    // If the map previously contained a mapping for the key,
    // the old value is replaced by the specified value.
    // Returns the previous value associated with key,
    // or null if there was no mapping for key.

	public V search(K key){return null;}
    // Returns the value to which the specified key is mapped,
    // or null if this map contains no mapping for the key.

	public V remove(K key){return null;}
    // Removes the key-vaue-pair associated with the key.
    // Returns the value to which the key was previously associated,
    // or null if the key is not contained in the dictionary.
}
