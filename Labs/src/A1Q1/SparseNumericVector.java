package A1Q1;
import java.util.*;

/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value.  The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like
 * inner products (projections).  Note that location indices can be any integer
 * from 1 to Long.MAX_VALUE.  The representation is based upon a
 * singly-linked list.
 * The following methods are supported:  iterator, getSize, getFirst,
 * add, remove, and dot, which takes the dot product of the with a second vector
 * passed as a parameter.
 * @author jameselder
 */
public class SparseNumericVector implements Iterable {

    protected SparseNumericNode head = null;
    protected SparseNumericNode tail = null;
    protected long size;
    

  /**
     * Iterator
     */
    @Override
    public Iterator<SparseNumericElement> iterator() { //iterator
        return new SparseNumericIterator(this);
    }

    /**
     * @return number of non-zero elements in vector
     */
   public long getSize() {
        return size;
    }

     /**
     * @return the first node in the list.
     */
    public SparseNumericNode getFirst() {
        return head;
    }
    
    /**
     * Add the element to the vector.  It is inserted to maintain the
     * vector in increasing order of index.  If the element has zero value, or if 
     * an element with the same index already exists, an UnsupportedOperationException is thrown. 
     * @param e element to add
     */
  public void add(SparseNumericElement e) throws UnsupportedOperationException {
      
	  //check for zero value
	  if(e.getValue() == 0)
      {
		  throw new UnsupportedOperationException("Cannot have zero value");
      }
	  
	  //node to insert
	  SparseNumericNode newNode;
	  
	  //if first entry
	  if(head == null)
	  {
		  newNode = new SparseNumericNode(e, null);
		  head = newNode;
		  tail = newNode;
	  }
	  
	  else
	  {
		  SparseNumericNode positionNode = head;
		  //Since vector is ordered, place before head if index is smaller
		  if(e.getIndex() < head.getElement().getIndex())
		  {
			  newNode = new SparseNumericNode(e, head);
			  head = newNode;
		  }
		  
		  while(positionNode.getNext() != null)
		  {
			  //current and next index are not same as insertion index
			  if(positionNode.getElement().getIndex() != e.getIndex() &&
					  positionNode.getNext().getElement().getIndex() != e.getIndex())
			  {
				  //the first position higher than the insertion index (next node)
				  if(positionNode.getNext().getElement().getIndex() > e.getIndex())
				  {
					  newNode = new SparseNumericNode(e, positionNode.getNext());
					  positionNode.setNext(newNode);
					  break;//Inserted
				  }
			  }
			  else
			  {
				  throw new UnsupportedOperationException("Index already exists!");
			  }
			  
			  
			  positionNode = positionNode.getNext();
		  }
	  }
	  
	  size++;

    }

    /**
     * If an element with the specified index exists, it is removed and the
     * method returns true.  If not, it returns false.
     *
     * @param index of element to remove
     * @return true if removed, false if does not exist
     */
    public boolean remove(Long index) {
        
    	SparseNumericNode positionNode = head;
    	
    	//first node
    	if(positionNode.getElement().getIndex() == index)
    	{
    		//remove
			positionNode.setNext(positionNode.getNext().getNext());
			return true;
    	}
    	
    	//loop through rest of nodes
    	while(positionNode.getNext() != null)
    	{
    		if(positionNode.getNext().getElement().getIndex() == index)
    		{
    			//remove
    			if(positionNode.getNext().getNext() == null)
    			{
    				//last node is index
    				tail = positionNode;
    			}
    			else
    			{
    			
    			positionNode.setNext(positionNode.getNext().getNext());
    			
    			}
    			size--;
    			return true;
    		}
    		
    		positionNode = positionNode.getNext();
    	}
        return false; 
    }

    /**
     * Returns the inner product of the vector with a second vector passed as a
     * parameter.  The vectors are assumed to reside in the same space.
     * Runs in O(m+n) time, where m and n are the number of non-zero elements in
     * each vector.
     * @param Y Second vector with which to take inner product
     * @return result of inner product
     */

    public double dot (SparseNumericVector Y) {
        
    	double runningDotProduct = 0;
    	Map<Long, Double> valueMap = new TreeMap<Long, Double>();
    	
    	Iterator<SparseNumericElement> mainIt = iterator();
    	Iterator<SparseNumericElement> secondIt = Y.iterator();
    	SparseNumericElement E;
    	
    	//O(n)
    	while(mainIt.hasNext())
    	{
    		E = mainIt.next();
    		valueMap.put(E.getIndex(), E.getValue());
    		
    	}
    	//+ O(m)
    	while(secondIt.hasNext())
    	{
    		E = secondIt.next();
    		if(valueMap.containsKey(E.getIndex()))
    		{
    			runningDotProduct += (E.getValue() * valueMap.get(E.getIndex()));
    		}
    		
    	}
    	
    	return runningDotProduct;
   }

       /**
     * returns string representation of sparse vector
     */

    @Override
    public String toString() {
        String sparseVectorString = "";
        Iterator<SparseNumericElement> it = iterator();
        SparseNumericElement x;
        while (it.hasNext()) {
            x = it.next();
            sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
        }
        return sparseVectorString;
    }
}
