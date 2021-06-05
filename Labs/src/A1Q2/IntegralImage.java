package A1Q2;

/**
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time.  Uses O(n) memory,
 * where n is the number of pixels in the image.
 *
 * @author jameselder
 */
public class IntegralImage {

    private final int[][] integralImage;
    private final int imageHeight; // height of image (first index)
    private final int imageWidth; // width of image (second index)
    
    

    /**
     * Constructs an integral image from the given input image.  
     *
     * @author jameselder
     * @param image The image represented
     * @throws InvalidImageException Thrown if input array is not rectangular
     */
    public IntegralImage(int[][] image) throws InvalidImageException {
    	
    	//Check rectangular
    	for(int i = 1; i < image.length; i++)
    	{
    		if(image[i].length != image[i-1].length)
    		{
    			throw new InvalidImageException("Must be rectangular array!");
    		}
    	}
    	
    	imageHeight = image.length;
    	imageWidth = image[0].length;
    	
    	//Make Integral image
    	
    	integralImage = new int[imageHeight][imageWidth]; 
    	
    	//O(n/height * n/width) = O(n^2/n) = O(n)
    	for(int y = 0; y < imageHeight; y++)//O(n/width)
    	{
    		System.out.println(" ");
    		//O(n/height)
    		for(int x = 0; x < imageWidth; x++)
    		{
    			//Everything in for loop is O(1)
    			int imageValue = image[y][x];
    			int leftIntegralImageValue;
    			int topIntegralImageValue;
    			int leftDiagIntegralImageValue;
    			
    			if(y-1 < 0 || x-1 < 0)
    			{
    				leftDiagIntegralImageValue = 0;
    				
    				if(y-1 < 0)
    				{
    					topIntegralImageValue = 0;
    				}
    				else
    				{
    					topIntegralImageValue = integralImage[y-1][x];
    				}
    				
    				if(x-1 < 0)
    				{
    					leftIntegralImageValue = 0;
    				}
    				else
    				{
    					leftIntegralImageValue = integralImage[y][x-1];
    				}
    			}
    			else
    			{
    				leftDiagIntegralImageValue = integralImage[y-1][x-1];
    				topIntegralImageValue = integralImage[y-1][x];
    				leftIntegralImageValue = integralImage[y][x-1];
    			}
    			
    			
    			//Calculates actual integral image value
    			
    			integralImage[y][x] = imageValue + leftIntegralImageValue +
    					topIntegralImageValue - leftDiagIntegralImageValue;
    			
    			//Testing Purposes
    			System.out.print(integralImage[y][x] + " ");
    			
    		}
    	}
    	
    }

    /**
     * Returns the mean value of the rectangular sub-image specified by the
     * top, bottom, left and right parameters. The sub-image should include
     * pixels in rows top and bottom and columns left and right.  For example,
     * top = 1, bottom = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting
     * at (top, left) coordinate (1, 1).  
     *
     * @author jameselder
     * @param top top row of sub-image
     * @param bottom bottom row of sub-image
     * @param left left column of sub-image
     * @param right right column of sub-image
     * @return 
     * @throws BoundaryViolationException if image indices are out of range
     * @throws NullSubImageException if top > bottom or left > right
     */
    public double meanSubImage(int top, int bottom, int left, int right) throws 
    BoundaryViolationException, NullSubImageException {
       
    	if(top > bottom | left > right)
    	{
    		throw new NullSubImageException("Improper dimensions!");
    	}
    	if(top < 0 || bottom > imageHeight-1 || left < 0 || right > imageWidth-1)
    	{
    		throw new BoundaryViolationException("Out of bounds!");
    	}
    	
    	//total sum (large rectangle)
    	int rightBottomCorner = integralImage[bottom][right];
    	
    	//sum of rectangle left of desired sub-rectangle (as long as there is a value)
    	int leftofBottomLeftCorner = (left <= 0) ? 0 : integralImage[bottom][left-1];
    	
    	//sum of rectangle top of desired sub-rectangle (as long as there is a value)
    	int topofTopRightCorner= (top <=0 ) ? 0 : integralImage[top-1][right];
    	
    	//sum of rectangle intersecting with previous two (as long as there is a value)
    	int diagofTopLeftCorner = (top <=  0 && left <= 0) ? 0 : integralImage[top-1][left-1];
    	
    	//number of cells
    	int numCells = (right - left + 1) * (bottom - top + 1);
    	
    	//formula for average (subimage sum - subimage above - subimage to left +
    	//subimage intersecting last two , all over the number of values
        double average = (double) (rightBottomCorner + diagofTopLeftCorner -
        		topofTopRightCorner - leftofBottomLeftCorner) / numCells;
        
    	return average;
    }
    
    
}