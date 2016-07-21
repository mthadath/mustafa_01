/**
 * 
 */
package org.mustafa.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j;

/**
 * @author mthadathil
 *
 */
@Log4j
public class DeDup {

	/**
	 * This method uses java 8 streams.
	 * Please note Set won't allow duplicates and when was able to successfully add to Set, collect them.
	 * @param randomIntegers
	 */
	private static final String ELAPSED_TIME = "The time elapsed in milliseconds ";
	
	public void findDeDupJava8 (int[] randomIntegers) {
		long begin = System.currentTimeMillis();
		Set<Integer> allItems = new HashSet<>();
		log.info("\n\nfindDupeJava8 method\n\n");
		Set<Integer> duplicates = Arrays.stream(randomIntegers).boxed().filter(n -> !allItems.add(n)).collect(Collectors.toSet());
		log.info("The uniques elements " + duplicates);
		log.info(ELAPSED_TIME + (System.currentTimeMillis() - begin));
	}
	/**
	 * This method uses java collection API to remove the duplicates and store.
	 * In memory it uses large space for each element. This maintains the original elements order.
	 * @param randomIntegers
	 */
	public void findDupe(int[] randomIntegers) {
		long begin = System.currentTimeMillis();
		List<Integer> dupes = new ArrayList<>();
        for(int i = 0; i < randomIntegers.length ; i++)
        {
        	// add to the list only if it does not exist
        	if (!dupes.contains(randomIntegers[i])) {
        		dupes.add(randomIntegers[i]);
        	}
        }
        log.info("\n\nfindDupe method\n\n");
        log.info("All the elements "  + Arrays.toString(randomIntegers) );
        int i = 0;
		for (Integer entry : dupes) {
			i++;
			log.info(  (i == 1 ? "The uniques elements " : ", ") + entry );
		}
		log.info(ELAPSED_TIME + (System.currentTimeMillis() - begin));
	}
	
	/**
	 * This method does not use any java collection API and will use less memory space for a large list
	 * However it will be slow for a large list for sorting
	 * @param randomIntegers
	 */
	public void findDupeSimple(int[] randomIntegers) {
		long begin = System.currentTimeMillis();
		// sort first
		for(int i = 0; i < randomIntegers.length; i++)  {
	        for(int j = i; j < randomIntegers.length; j++) {
	            if(randomIntegers[i] > randomIntegers[j]) {
	            	swapNumbers(i, j, randomIntegers);
	            }

	        }
	    }
		log.info("\n\nfindDupeSimple method\n\n");
		log.info("All the elements "  + Arrays.toString(randomIntegers) );
		removeDuplicatesAndPrint(randomIntegers);
		log.info(ELAPSED_TIME + (System.currentTimeMillis() - begin));
	}
	
	/**
	 * This method also does not use java collection API. Here the sorting will be much faster because of
	 * the quick sort algorithm used.
	 * @param randomIntegers
	 */
	public void findDupeWithQuickSort(int[] randomIntegers) {
		long begin = System.currentTimeMillis();
        int length = randomIntegers.length;
        quickSort(0, length - 1, randomIntegers);
        log.info("\n\nfindDupeSimpleQuickSort method\n\n");
        log.info("All the elements "  + Arrays.toString(randomIntegers) );
        removeDuplicatesAndPrint(randomIntegers);
        log.info(ELAPSED_TIME + (System.currentTimeMillis() - begin));
	}
		
	private static void quickSort(int beginIndex, int lastIndex, int[] randomIntegers) {
			         
        int i = beginIndex;
        int j = lastIndex;
        // finding pivot as the middle one on each quickSort call
        int pivot = randomIntegers[beginIndex+(lastIndex-beginIndex)/2];
        // Divide the arrays in to two.
        while (i <= j) {
            /*
             * Start with lower end and keep going up in the array, while at the same time come from the end
             *  and keep coming down the array until a number is found which is not less than the pivot while
             *  going up and also until a number is found which is not greater than pivot while coming down
             *  the array. If found swap the numbers as long as going up number position is less than going
             *   down position.
             *   
             *   We will see the smaller number going down and bigger number going up in the array
             */
            while (randomIntegers[i] < pivot) {
                i++;
            }
            while (randomIntegers[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swapNumbers(i, j, randomIntegers);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (beginIndex < j)
            quickSort(beginIndex, j, randomIntegers);
        // At this point we will see most of the numbers are sorted in ascending order starting from the
        // begining of the array
        if (i < lastIndex)
            quickSort(i, lastIndex, randomIntegers);
	}
	
	/**
	 * private method to swap numbers of the array
	 * @param i swap this with array element of j
	 * @param j swap this with array element of i
	 * @param randomIntegers
	 */
	private static void swapNumbers(int i, int j, int[] randomIntegers) {
		int temp = randomIntegers[i];
        randomIntegers[i] = randomIntegers[j];
        randomIntegers[j] = temp;
	}

	/**
	 * Once sorted it removes the duplicates from the array by moving the duplicated ones
	 * to the end of the array and by forgetting the ending unwanted numbers by moving the end towards
	 * the begning.
	 * @param randomIntegers
	 */
	private static void removeDuplicatesAndPrint(int[] randomIntegers) {
		// remove duplicates
		int lastIndex = randomIntegers.length;
		for(int i = 0; i < lastIndex ; i++)
	    {
			for (int j = i + 1 ; j < lastIndex ; j++) {
				if (randomIntegers[i] == randomIntegers[j]) {
					randomIntegers[j] = randomIntegers[lastIndex - 1];
					lastIndex--;
					j--;
				}
			}
	    }
		int[] noDupList = new int[lastIndex];
	    System.arraycopy(randomIntegers, 0, noDupList, 0, lastIndex);
		// print the result
	    log.info("The unique elements "  + Arrays.toString(noDupList)  );
	}

}
