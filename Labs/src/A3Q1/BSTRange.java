package A3Q1;


/**
 * Extends the TreeMap class to allow convenient access to entries
 * within a specified range of key values (findAllInRange).
 * @author jameselder
 */
public class BSTRange<K,V> extends TreeMap<K,V>{

    /* Returns the lowest (deepest) position in the subtree rooted at pos
     * that is a common ancestor to positions with
     * keys k1 and k2, or to the positions they would occupy were they present.
     */
    protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2,
            Position<Entry<K, V>> pos) 
    {
    	if(this.compare(k1, pos.getElement().getKey()) < 0 &&
    			this.compare(k2, pos.getElement().getKey()) < 0)//k1 and k2 smaller than root key
    	{
    		if(this.isInternal(this.left(pos))) //out of range
    		{
    			pos = this.left(pos);
        		return findLowestCommonAncestor(k1, k2, pos);
    		}
    		else
    		{
    			return null;
    		}
    	}
    	else if(this.compare(k1, pos.getElement().getKey()) > 0 &&
    			this.compare(k2, pos.getElement().getKey())> 0) //k1 and k2 larger than root key
    	{
    		if(this.isInternal(this.right(pos))) //out of range
    		{
    			pos = this.right(pos);
        		return findLowestCommonAncestor(k1, k2, pos);
    		}
    		else
    		{
    			return null;
    		}
    	}
    	else if(this.compare(k1, pos.getElement().getKey()) <= 0 &&
    			this.compare(k2, pos.getElement().getKey()) >= 0)//k1 <= root key and k2 >= root key
    	{
    		return pos;
    	}
    	return null;
    }

    /* Finds all entries in the subtree rooted at pos  with keys of k or greater
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllAbove(K k, Position<Entry<K, V>> pos,
            PositionalList<Entry<K, V>> L)
    {
		//inorder traversal 
    	if(this.isInternal(this.left(pos)))
    	{
    		findAllAbove(k, this.left(pos), L);
    	}
    	
    	if(this.compare(pos.getElement().getKey(), k) >= 0)//key must be >= k
    	{
    		L.addLast(pos.getElement());
    	}
    	
    	if(this.isInternal(this.right(pos)))
    	{
    		findAllAbove(k, this.right(pos), L);
    	}
    }

    /* Finds all entries in the subtree rooted at pos with keys of k or less
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllBelow(K k, Position<Entry<K, V>> pos,
            PositionalList<Entry<K, V>> L) 
    {
    	//inorder traversal 
    	if(this.isInternal(this.left(pos)))
    	{
    		findAllBelow(k, this.left(pos), L);
    	}
    	
    	if(this.compare(pos.getElement().getKey(), k) <= 0)//key must be <= k
    	{
    		L.addLast(pos.getElement());
    	}
    	
    	if(this.isInternal(this.right(pos)))
    	{
    		findAllBelow(k, this.right(pos), L);
    	}
    }

    /* Returns all entries with keys no less than k1 and no greater than k2,
     * in non-decreasing order.
     */
    public PositionalList<Entry<K, V>> findAllInRange(K k1, K k2) 
    {
    	
    	LinkedPositionalList<Entry<K, V>> listKeysInRange = new LinkedPositionalList<Entry<K, V>>();
    	
    	if(k1.equals(null) || k2.equals(null) || this.compare(k1, k2) > 0 || this.isEmpty() )
    	{
    		return listKeysInRange;
    	}
    	
    	
    	//find lowest common ancestor
    	
    	Position<Entry<K,V>> p = this.root();
		Position<Entry<K, V>> posCommonAncestor = this.findLowestCommonAncestor(k1, k2, p);
		if(posCommonAncestor == null) //No common ancestor or keys out of range
		{
			return listKeysInRange;
		}
		Position<Entry<K, V>> leftSubTree = this.left(posCommonAncestor);
		Position<Entry<K, V>> rightSubTree = this.right(posCommonAncestor);
		
		//add all keys in left subtree
		if(this.isInternal(leftSubTree))
		{
			this.findAllAbove(k1, leftSubTree, listKeysInRange);
		}
		
		//add common ancestor key
		listKeysInRange.addLast(posCommonAncestor.getElement());
		
		//add all keys in right subtree
		if(this.isInternal(rightSubTree))
		{	
			this.findAllBelow(k2, rightSubTree, listKeysInRange);
		}
		
		return listKeysInRange;
    	
    }
}