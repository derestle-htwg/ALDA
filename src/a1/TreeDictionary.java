package a1;

import sun.security.mscapi.KeyStore.ROOT;

public class TreeDictionary<K, V> implements Dictionary<K, V> {
	
	private class TreeNode
	{
		public K Key;
		public V Data;
		public int Height; 
		public TreeNode Left;
		public TreeNode Right;
		public TreeNode Parent;
	}
	
	TreeNode RootNode = null;
	
	private void RotateL(TreeNode inNode)
	{
		if(inNode.Right == null)
			return;
		
		if(inNode.Parent != null)
		{//Parent anpassen
			if(inNode.Parent.Left == inNode)
			{
				inNode.Parent.Left = inNode.Right;
			}
			else
			{
				inNode.Parent.Right = inNode.Right;
			}
		}
		inNode.Right.Parent = inNode.Parent;
		inNode.Parent = inNode.Right;
		inNode.Right = inNode.Parent.Left;
		inNode.Parent.Left = inNode;		
	}
	
	private void RotateR(TreeNode inNode)
	{
		if(inNode.Left == null)
			return;
		
		if(inNode.Parent != null)
		{//Parent anpassen
			if(inNode.Parent.Left == inNode)
			{
				inNode.Parent.Left = inNode.Left;
			}
			else
			{
				inNode.Parent.Right = inNode.Left;
			}
		}
		inNode.Left.Parent = inNode.Parent;
		inNode.Parent = inNode.Left;
		inNode.Left = inNode.Parent.Right;
		inNode.Parent.Right = inNode;		
	}

	private TreeNode InsertNode(TreeNode inNode)
	{
		TreeNode retVal = null;
		if(RootNode == null)
			RootNode = inNode;
		else
		{//search insert node
			if(Compare(RootNode.Key,inNode.Key) == 0)
			{//Rootnode ersetzen
				inNode.Height = RootNode.Height;
				inNode.Left = RootNode.Left;
				inNode.Right = RootNode.Right;
			}
			else
			{
			TreeNode actualNode = RootNode;
			boolean parentNodeFound = false;;
			while(!parentNodeFound)
			{
				int compVal = Compare(actualNode.Key, inNode.Key);
			
				if(compVal == 0)
				{//ersetzen
					inNode.Height = actualNode.Height;
					inNode.Left = actualNode.Left;
					inNode.Right = actualNode.Right;
					inNode.Parent = actualNode.Parent;
					if(inNode.Parent.Left == actualNode)
					{
						inNode.Parent.Left = inNode;
					}
					else
					{
						inNode.Parent.Right = inNode;
					}
				}
				else if(compVal == -1)
				{//inNode Größer -> rechts weiter
					if(actualNode.Right == null)
					{//insert, reorganisieren
						
					}
					else
						actualNode = actualNode.Right;
				}
				else
				{//inNode kleiner -> links Weiter
					if(actualNode.Left == null)
					{//insert, reorganisieren
						
					}
					else
						actualNode = actualNode.Left;
				}
			
			}
		}
		return retVal;
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
    // Removes the key-value-pair associated with the key.
    // Returns the value to which the key was previously associated,
    // or null if the key is not contained in the dictionary.
}
