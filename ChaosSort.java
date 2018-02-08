/*
A triumph in inefficiency, this program will sort an array
by randomly arranging elements until the array is in ascending order.
It will also print to the console a '*' for every million attempts.
 */

import java.util.Arrays;
import java.util.Random;

public class ChaosSort {

    public static void main(String[] args)
    {
    	// create an array (length, max value)
	   int[] scrambled = enlist(11, 99);
		// System.out.println("Random array:");
	   System.out.println(Arrays.toString(scrambled));
		System.out.println();
		
		// give the system timer a second to register
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
                ie.printStackTrace();}
	
 		// Arrays.sort the array
		double startA = 0.0;
		int[] jumbled = Arrays.copyOf(scrambled, scrambled.length);
		startA = System.currentTimeMillis();
		Arrays.sort(jumbled);
		
		double timeA = (System.currentTimeMillis() - startA)/1000;
		System.out.printf("Arrays.sort() took %.10f seconds to sort %d items.\n", timeA, jumbled.length);
		System.out.println(Arrays.toString(jumbled));
	
	   // sort the array, the chaos way.
	   System.out.println();
		int[] unscrambled = chaos(scrambled);
	   System.out.println(Arrays.toString(unscrambled));

    } // end of main

	/**
	 * The enlist method will create an array with random values.
	 * @param n The size of the array.
	 * @param max The maximum value of each element.
	 * @return an int[]
	 */
	public static int[] enlist(int n, int max)
	{
		Random r = new Random(); // chaos!
		int[] list = new int[n];
		int next;

		for (int i = 0; i < list.length; i++)
		{
			next = r.nextInt(max)+1;
			list[i] = next;
		}
		return list;
	}

	/**
	 * The chaos() method will take an array of ints and sort them in ascending order,
	 * using a stunningly inefficient algorithm.
	 * It will also print to the console the method's execution time and the number of iterations needed.
	 * @param unsorted The unsorted int array.
	 * @return The sorted int array.
	 */
	public static int[] chaos(int[] unsorted)
	{
		// get start time so everybody will know just how bad this is
		double start = 0.0;
		start = System.currentTimeMillis();

		// variables and temporary arrays we will need
		int[] sorted = new int[unsorted.length];
		int counter = 0;
		boolean ascending;
		boolean[] unsortedAvail = new boolean[unsorted.length];
		boolean[] sortedAvail = new boolean[sorted.length];
		Random r = new Random(); // chaos!

		// loop through the process until the array elements are in order
		do
		{
			ascending = true; // loop control

			// these arrays will track what elements have not yet been "picked up" from the unsorted list
			// and which indices in the sorted list are still need elements "dropped into" them
			for (int i = 0; i < unsorted.length; i++)
			{
				unsortedAvail[i] = true;
			}

			for (int i = 0; i < sorted.length; i++)
			{
				sortedAvail[i] = true;
			}

			// iterate through the process for each list item
			for (int i = 0; i < unsorted.length; i++)
			{
				int unsortedIndex; // to correspond to the un/sortedAvail arrays, above
				int sortedIndex;

				do // randomly select an available unsorted array element
				{
					unsortedIndex = r.nextInt(unsorted.length);
				} while (unsortedAvail[unsortedIndex] == false);

				do // randomly select an available sorted array element
				{
					sortedIndex = r.nextInt(sorted.length);
				} while (sortedAvail[sortedIndex] == false);

				// mark indices as unavailable
				unsortedAvail[unsortedIndex] = false;
				sortedAvail[sortedIndex] = false;

				// place the randomly selected element into a random place in the sorted array
				sorted[sortedIndex] = unsorted[unsortedIndex];
			}

			// check to see if sorted array is in fact, sorted
			for (int i = 0; (i < sorted.length - 1) && (ascending == true); i++)
			{
				if (sorted[i] > sorted[i + 1])
					ascending = false;
			}

			counter++; // if only we had used these cpu cycles for good instead of chaos...
			if (counter%1000000 == 0) // tally every million iterations
				System.out.print("*");
		} while (ascending==false);

		// great shot kid - that was one in a million!
		System.out.println();
		double time = (System.currentTimeMillis() - start)/1000;
		System.out.printf("ChaosSort took %.4f seconds and %,d iterations to sort %d items.\n", time, counter, unsorted.length);
		return sorted;
	} // end of chaos
} // end of class
