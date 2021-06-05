package A2Q1;
import java.util.*;
/**
 * Represents a sorted integer array.  Provides a method, kpairSum, that
 * determines whether the array contains two elements that sum to a given
 * integer k.  Runs in O(n) time, where n is the length of the array.
 * @author jameselder
 */
public class SortedIntegerArray {

    protected int[] sortedIntegerArray;

    public SortedIntegerArray(int[] integerArray) {
        sortedIntegerArray = integerArray.clone();
        Arrays.sort(sortedIntegerArray);
    }

/**
 * Determines whether the array contains two elements that sum to a given
 * integer k. Runs in O(n) time, where n is the length of the array.
 * @author jameselder
 */
    public boolean kPairSum(Integer k) 
    {
    	int desiredIndex = 0;
    	//Searches for greatest index where the value is under the required sum
    	//O(n) because iterates through array once
    	for(int i = sortedIntegerArray.length - 1; i > 0; i--)
    	{
    		if(sortedIntegerArray[i] < k)
    		{
    			desiredIndex = i;
    			break;
    		}
    	}
    	return kIntervalPairSum(k, 0, desiredIndex);
    }
    
    /**
     * Checks if there are two elements that sum to k in the the interval i to j.
     * @param k desired sum
     * @param i starting index (inclusive)
     * @param j end index (inclusive)
     * @return true if two elements do indeed sum to k
     */
    private boolean kIntervalPairSum(Integer k, int i, int j)
    {
    	//O(n) because ends cannot exceed array index bounds. 
    	
    	//long sum to account for overflow
    	long pairSum = sortedIntegerArray[i] + sortedIntegerArray[j];
    	
    	//Since array is sorted, we can work with the ends of the array
    	//End case -- failed -- no pairs were found
    	if(i >= j)
    	{
    		return false;
    	}
    	//End case -- success -- a pair is found
    	if(pairSum == k.intValue())
    	{
    		return true;
    	}
    	//if the ends sum is too large, the summing pairs need to be smaller, so the rightmost  
    	//bound is decreased
    	else if(pairSum > k.intValue())
    	{
    		j--;
    	}
    	//if the ends sum is too small, the summing pairs to be larger, so the leftmost 
    	//bound isincreased
    	else if(pairSum < k.intValue())
    	{
    		i++;
    	}
    	
    	//Recursive step
    	return kIntervalPairSum(k, i, j);
    	
    }
    
    
    
}