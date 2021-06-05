package A3Q1;
import java.util.*;

/**
 * Tests BSTRange
 * 
 * @author jameselder
 */
public class testFindAllInRange {

    public static void main(String[] args) {
        BSTRange<Integer, String> medals = new BSTRange<Integer, String>();
        PositionalList<Entry<Integer, String>> medalList;
        Iterator<Entry<Integer, String>> entryIter;
        int k1 = 3;
        int k2 = 7;

        System.out.println("2018 Winter Olympics Medal Standings:");

        try { //should output nothing
        	System.out.println("Test Case 1:");
            medalList = medals.findAllInRange(k1, k2);
            entryIter = medalList.iterator();

            System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
            while (entryIter.hasNext()) {
                System.out.println(entryIter.next().getValue());
            }
        } catch (Exception x) {
        	
            System.out.println("Test Case 1 Failed");
            x.printStackTrace();
        }

        medals.put(1, "Norway");
        medals.put(2, "Germany");
        medals.put(3, "Canada");
        medals.put(4, "USA");
        medals.put(5, "Netherlands");
        medals.put(6, "Sweden");
        medals.put(7, "South Korea");
        medals.put(8, "Switzerland");
        medals.put(9, "France");
        medals.put(10, "Austria");

        try { //should output Canada, USA, Netherlands, Sweden, South Korea 
        	System.out.println("Test Case 2:");
            medalList = medals.findAllInRange(k1, k2);
            entryIter = medalList.iterator();

            System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
            while (entryIter.hasNext()) {
                System.out.println(entryIter.next().getValue());
            }
        } catch (Exception x) {
            System.out.println("Test Case 2 Failed");
            x.printStackTrace();
        }

       k1 = -10;
       k2 = -5;
        try { //should output nothing
        	System.out.println("Test Case 3:");
            medalList = medals.findAllInRange(k1, k2);
            entryIter = medalList.iterator();

            System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
            while (entryIter.hasNext()) {
                System.out.println(entryIter.next().getValue());
            }
        } catch (Exception x) {
            System.out.println("Test Case 3 Failed");
            x.printStackTrace();
        }

       k1 = 5;
       k2 = 4;
       try { //should output nothing
    	   System.out.println("Test Case 4:");
            medalList = medals.findAllInRange(k1, k2);
            entryIter = medalList.iterator();

            System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
            while (entryIter.hasNext()) {
                System.out.println(entryIter.next().getValue());
            }
        } catch (Exception x) {
            System.out.println("Test Case 4 Failed");
            x.printStackTrace();
        }

       k1 = 3;
       k2 = 3;
       try {//should output Canada
    	   System.out.println("Test Case 5:");
            medalList = medals.findAllInRange(k1, k2);
            entryIter = medalList.iterator();

            System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
            while (entryIter.hasNext()) {
                System.out.println(entryIter.next().getValue());
            }
        } catch (Exception x) {
            System.out.println("Test Case 5 Failed");
            x.printStackTrace();
        }

       k1 = -10;
       k2 = 10;
       try {//should output Norway, Germany, Canada, USA, Netherlands, Sweden, South Korea, Switzerland, France, Austria
    	   	System.out.println("Test Case 6:");
    	   	medalList = medals.findAllInRange(k1, k2);
            entryIter = medalList.iterator();

            System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
            while (entryIter.hasNext()) {
                System.out.println(entryIter.next().getValue());
            }
        } catch (Exception x) {
            System.out.println("Test Case 6 Failed");
            x.printStackTrace();
        }

    }
}