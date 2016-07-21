/**
 * 
 */
package org.mustafa.utility;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mthadathil
 *
 */
public class DepDupTest {
	
	DeDup dedup; 
	int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,
			16,19,1,18,4,9,3,20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,
			16,3,6,19,13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};
	
	@Before
	public void before() {
		dedup = new DeDup();
	}
	
	@Test
	public void testDeDup() {
    	dedup.findDupe(randomIntegers);
	}

	@Test
	public void testDeDupSimple() {
    	dedup.findDupeSimple(randomIntegers);
	}
	
	@Test
	public void testDeDupWithQuickSort() {
    	dedup.findDupeWithQuickSort(randomIntegers);
	}
	
	@Test
	public void testDeDupJava8() {
    	dedup.findDeDupJava8(randomIntegers);
	}
}
