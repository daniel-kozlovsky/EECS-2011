package A1Q3;
import java.util.*;

/**
 * Specializes the stack data structure for comparable elements, and provides
 * a method for determining the maximum element on the stack in O(1) time.
 * @author jameselder
 */
public class MaxStack<E extends Comparable<E>> extends Stack<E> {

    private Stack<E> maxStack;
    private Stack<E> trackingStack;
    
    private E maxValue;

    public MaxStack() {
        maxStack = new Stack<>();
        trackingStack = new Stack<>();
        
    }

    /* must run in O(1) time */
    public E push(E element) {
    	
    	
    	if(maxStack.empty())
    	{
    		trackingStack.push(element);
    		
    		
    	}
    	else if(element.compareTo(trackingStack.peek()) >= 0)
    	{
    		//only push to tracking stack if element is 
    		//larger than or equal to the max value
    		//This way only the maximum values are retained
    		trackingStack.push(element);
    		
    	}
    	
    	//Always push to stack
    	return maxStack.push(element);
         
    }

    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
   public synchronized E pop() {
	   if(maxStack.empty())
	   {
		   throw new EmptyStackException();
	   }
	   if(maxStack.peek().equals(trackingStack.peek()))
	   {
		   //get rid of max value only if this value is 
		   //popped from the maxStack
		   trackingStack.pop();
		   
	   }
	   
	   //Always pop maxStack
	   return maxStack.pop();
        
    }

    /* Returns the maximum value currently on the stack. */
    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
    public synchronized E max() {
       if(maxStack.empty())
       {
    	   throw new EmptyStackException();
       }
       else
       {
    	   //Top of trackingStack is always max number at that time
    	   return trackingStack.peek();
       }
    	
    }
}