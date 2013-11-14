package a1;

public class TreeDictionary<K, V> implements Dictionary<K, V> {
	
	private class TreeNode
	{
		public TreeNode(K inKey,V inData){Data = inData;Key = inKey;}
		public K Key;
		public V Data;
		public int Height; 
		public TreeNode Left;
		public TreeNode Right;
		public TreeNode Parent;
		public void replaceSubNode(TreeNode oldNode, TreeNode newNode)
		{
			if(Left == oldNode)
				Left = newNode;
			else
				Right = newNode;
			if(newNode != null)
			{
				newNode.Parent = this;
			}
		}
	}
	
	private TreeNode RootNode = null;
	
	private void RotateL(TreeNode inNode)
	{
		if(inNode.Right == null)
			return;
		
		if(inNode.Parent != null)//Parent anpassen
			inNode.Parent.replaceSubNode(inNode, inNode.Right);
		
		inNode.Parent = inNode.Right;
		inNode.Right = inNode.Parent.Left;
		inNode.Parent.Left = inNode;		
	}
	
	private void RotateR(TreeNode inNode)
	{
		if(inNode.Left == null)
			return;
		
		if(inNode.Parent != null)//Parent anpassen
			inNode.Parent.replaceSubNode(inNode, inNode.Left);
		
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
						actualNode.Parent.replaceSubNode(actualNode, inNode);
						parentNodeFound = true;
					}
					else if(compVal > 0)
					{//inNode Gr��er -> rechts weiter
						if(actualNode.Right == null)
						{//insert, reorganisieren
							actualNode.Right = inNode;
							inNode.Parent = actualNode;
							reorganize(inNode);
							parentNodeFound = true;
						}
						else
							actualNode = actualNode.Right;
					}
					else
					{//inNode kleiner -> links Weiter
						if(actualNode.Left == null)
						{//insert, reorganisieren
							actualNode.Left = inNode;
							inNode.Parent = actualNode;
							reorganize(inNode);
							parentNodeFound = true;
						}
						else
							actualNode = actualNode.Left;
					}
				
				}
			}
		}
		return retVal;
	}
		
	private void reorganize(TreeNode inNode)
	{//wird nur bei Inserts oder Deletes aufgerufen.
		TreeNode actNode = inNode;
		int balance;
		int height;
		boolean change = true;
		while(actNode != null && change)
		{
			change = false;
			
			balance = calcBalance(actNode);
			if(balance == -2)
			{//linkslastig
				if(calcBalance(actNode.Left) == 1)
				{//rechtslastig, Doppeldrehen
					RotateL(actNode.Left);
				}
				RotateR(actNode);
			}
			else if (balance == 2)
			{//rechtslastig
				if(calcBalance(actNode.Right) == -1)
				{//rechtslastig, Doppeldrehen
					RotateR(actNode.Right);
				}
				RotateL(actNode);
			}
			
			height = calcHeight(actNode);
			if(actNode.Height != height)
			{//höhe stimmt nicht mehr. Anpassen und weiter oben kontrollieren!
				actNode.Height = height;
				change = true;
			}
		}
	}
	
	private int calcHeight(TreeNode inNode)
	{
		int heightR;
		int heightL;
		heightR = inNode.Right == null?0:inNode.Right.Height+1;
		heightL = inNode.Left == null?0:inNode.Left.Height+1;
		return heightR>heightL?heightR:heightL;
	}
	
	private int calcBalance(TreeNode inNode)
	{
		int balance;
		balance = inNode.Right == null?0:inNode.Right.Height+1;
		balance -= inNode.Left == null?0:inNode.Left.Height+1;
		return balance;
	}
	
	private TreeNode SearchNode(K key)
	{
		TreeNode retVal = RootNode;
		while(retVal != null)
		{
			int compVal = Compare(retVal.Key,key); 
			if(compVal == 0)
				break;
			else if(compVal > 0)
			{//rechts weiter suchen
				retVal = retVal.Right;
			}
			else
			{//links weiter suchen
				retVal = retVal.Left;
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
	
	private TreeNode getLowest(TreeNode node)
	{
		TreeNode retVal = node;
		while(retVal.Left != null)
			retVal = retVal.Left;
		return retVal;
	}
	
	private TreeNode getHighest(TreeNode node)
	{
		TreeNode retVal = node;
		while(retVal.Right != null)
			retVal = retVal.Right;
		return retVal;
	}
	
	private TreeNode DeleteTreeNode(K key)
	{
		TreeNode retVal = SearchNode(key);
		
		if(retVal == null)
			return null;
		else if(retVal.Left == null && retVal.Right == null)
		{//beide Subelemnte Null
			if(retVal == RootNode)
				RootNode = null;
			else
			{
				retVal.Parent.replaceSubNode(retVal, null);
				reorganize(retVal.Parent);
			}
		}
		else if(retVal.Left != null && retVal.Right != null)
		{//vom höheren subbaum ein Element klauen
			TreeNode replacementNode = null;
			TreeNode reorganizeNode = null;
			//replacementNode suchen und ausschneiden
			if(retVal.Left.Height > retVal.Right.Height)
			{
				replacementNode = getHighest(retVal.Left);
				replacementNode.Parent.Right = replacementNode.Left;
			}
			else
			{
				replacementNode = getLowest(retVal.Right);
				replacementNode.Parent.Left = replacementNode.Right;
			}
			reorganizeNode = replacementNode.Parent;
			
			//einsetzen/austauschen
			if(retVal.Parent != null)
				retVal.Parent.replaceSubNode(retVal, replacementNode);
			replacementNode.Left = retVal.Left;
			replacementNode.Right = retVal.Right;
			//reorganisieren
			//da anfangen wo node entfernt
			reorganize(reorganizeNode);
		}
		else
		{//nur ein subknoten --einfach anhängen und reorganisieren
			if(retVal.Left != null)
			{//linken Knoten anhängen
				if(retVal == RootNode)
				{
					RootNode = RootNode.Left;
					RootNode.Parent = null;
				}
				else
				{
					retVal.Parent.replaceSubNode(retVal, retVal.Left);
				}
			}
			else
			{
				if(retVal == RootNode)
				{
					RootNode = RootNode.Right;
					RootNode.Parent = null;
				}
				else
				{
					retVal.Parent.replaceSubNode(retVal, retVal.Right);
				}
			}
		}
		
		return retVal;
	}
	
	public V insert(K key, V value){
		TreeNode retVal =InsertNode(new TreeNode(key,value)); 
		return retVal == null?null:retVal.Data;}
    // Associates the specified value with the specified key in this map.
    // If the map previously contained a mapping for the key,
    // the old value is replaced by the specified value.
    // Returns the previous value associated with key,
    // or null if there was no mapping for key.

	public V search(K key){
		TreeNode retVal = SearchNode(key);
		return retVal == null?null:retVal.Data;}
    // Returns the value to which the specified key is mapped,
    // or null if this map contains no mapping for the key.

	public V remove(K key){
		TreeNode retVal = DeleteTreeNode(key);
		
		return retVal == null?null:retVal.Data;}
    // Removes the key-value-pair associated with the key.
    // Returns the value to which the key was previously associated,
    // or null if the key is not contained in the dictionary.
}
