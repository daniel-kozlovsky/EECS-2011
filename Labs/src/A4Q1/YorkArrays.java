package A4Q1;
import java.util.*;

/**
 *
 * Provides two static methods for sorting Integer arrays (heapSort and mergeSort)
 * @author jameselder
 */
public class YorkArrays {

    /* Sorts the input array of Integers a using HeapSort.  Result is returned in a.
     * Makes use of java.util.PriorityQueue.  
       Sorting is NOT in place - PriorityQueue allocates a separate heap-based priority queue. 
       Not a stable sort, in general.  
       Throws a null pointer exception if the input array is null.  */
    public static void heapSort(Integer[] a) throws NullPointerException {
       
    	if(a.equals(null))
    	{
    		throw new NullPointerException();
    	}
    	
    	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    	for(Integer i : a)
    	{
    		minHeap.add(i);
    	}
    	for(int i = 0; i < a.length; i++)
    	{
    		a[i] = minHeap.remove();
    	}
    	
    }
    
    /* Sorts the input array of Integers a using mergeSort and returns result.
     * Sorting is stable.
       Throws a null pointer exception if the input array is null. */
    public static Integer[] mergeSort(Integer[] a)  throws NullPointerException {
        return(mergeSort(a, 0, a.length-1));
    }
    
    /* Sorts the input subarray of Integers a[p...q] using MergeSort and returns result.
     * Sorting is stable.
     */
    private static Integer[] mergeSort(Integer[] a, int p, int q) {
        
    	if(a.equals(null))
    	{
    		throw new NullPointerException();
    	}
    	
    	
    	//split
    	int rightBoundLeftHalf = Math.floorDiv(q - p, 2) + 1; //exclusive
    	Integer[] leftHalf = Arrays.copyOfRange(a, p, rightBoundLeftHalf);
    	Integer[] rightHalf = Arrays.copyOfRange(a, rightBoundLeftHalf, q + 1);
    	
    	//recurse each subtree
    	//left
    	if(leftHalf.length > 1) 
    	{
    		leftHalf = mergeSort(leftHalf, 0, leftHalf.length-1);
    	}
    	//right
    	if(rightHalf.length > 1)
    	{
    		rightHalf = mergeSort(rightHalf, 0, rightHalf.length-1);
    	}
    	//sort
    	if(a[0] > a[a.length-1])
    	{
    		int left = a[0];
    		a[0] = a[1];
    		a[1] = left;
    	}
    	//merge
    	return merge(leftHalf, rightHalf);
    }
    
    /* Merges two sorted arrays of Integers into a single sorted array.  Given two
     * equal elements, one in a and one in b, the element in a precedes the element in b.
     */
    private static Integer[] merge(Integer[] a, Integer[] b) {
    	
    	if(a.equals(null))
    	{
    		throw new NullPointerException();
    	}
    	
    	
    	ArrayList<Integer> merged = new ArrayList<>();
    	int i = 0;
    	int j = 0;
    	
    	while(i < a.length && j < b.length)
    	{
    		if(a[i] < b[j])
    		{
    			merged.add(a[i]);
    			i++;
    			
    		}
    		else
    		{
    			merged.add(b[j]);
    			j++;
    		}
    	}
    	while(i < a.length)
    	{
    		merged.add(a[i]);
    		i++;
    	}
    	while(j < b.length)
    	{
    		merged.add(b[j]);
    		j++;
    	}
    	
    	return merged.toArray(a);
    	
    }
}




