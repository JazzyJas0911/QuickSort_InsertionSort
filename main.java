/*
 * Create two functions named quick_sort(a) and insertion_sort(a)
 * 1. Request the user to enter a positive integer, and call it n
 * 2. Generate n random integers between -7000 to 7000 and save them in array a
 * 3. Call quick_sort(a) function to sort the array
 * 4. Call insertion_sort(a) function to sort the array
 * 5. Determine the average-running time of each function for n = 10000, and 100 repetitions
 * 6. Calculate the growth of each function. (On a scratch paper!)
 * 7. Calculate how many instructions your machine can run in a second using step 5 and 6 (using the running time of insertion sort)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main{

	public static void main(String[] args){

		Scanner keyboard = new Scanner(System.in);
		List<Integer> a = new ArrayList<>();
		Random randomGenerator = new Random();
		
		
		System.out.println("Enter a positive integer");
		int n = keyboard.nextInt();
		int repetitions = 1;
		for(int i = 0; i < n; i++){
			int num = -7000 + randomGenerator.nextInt(14000);
			a.add(num);
		}
		List<Integer> temp = new ArrayList<Integer>(a);
		
		
//		System.out.println("Original: ");
//		for(int i = 0; i < a.size(); i++){
//			System.out.print(a.get(i) + ", ");
//		}
		
		
//		System.out.println("\n\nQuick Sort:");
		long quickSortTotalTime = 0;
		for(int i = 0; i < repetitions; i++){
			temp = new ArrayList<Integer>(a);
			long quickSortStartTime = System.nanoTime();
			quick_sort(temp, 0, temp.size() - 1);
			long quickSortEndTime = System.nanoTime();
			quickSortTotalTime += (quickSortEndTime - quickSortStartTime);
		}
		long quickSortAverageTime = quickSortTotalTime / repetitions;
//		for(int i = 0; i < temp.size(); i++){
//			System.out.print(temp.get(i) + ", ");
//		}
		System.out.println("\nQuick Sort Average-Running Time: " + quickSortAverageTime + " ns");
		
		
//		System.out.println("\nInsertion Sort:");
		long insertionSortTotalTime = 0;
		for(int i = 0; i < repetitions; i++){
			temp = new ArrayList<Integer>(a);
			long insertionSortStartTime = System.nanoTime();
			insertion_sort(temp);
			long insertionSortEndTime = System.nanoTime();
			insertionSortTotalTime += (insertionSortEndTime - insertionSortStartTime);
		}
		long insertionSortAverageTime = insertionSortTotalTime / repetitions;
//		for(int i = 0; i < temp.size(); i++){
//			System.out.print(temp.get(i) + ", ");
//		}
		System.out.println("\nInsertion Sort Average-Running Time: " + insertionSortAverageTime + " ns");
		
		double singleLineQuickSort = quickSortAverageTime / (n * Math.log(n) / Math.log(2));
		double nQuickSort = 1000000000 / singleLineQuickSort;
		System.out.println("\nNumber of Instructions Ran in a Second, Quick Sort: " + nQuickSort + " instructions");
		float singleLineInsertionSort = (float) insertionSortAverageTime / (n * n);
		float nInsertionSort = (float) 1000000000 / singleLineInsertionSort;
		System.out.println("\nNumber of Instructions Ran in a Second, Insertion Sort: " + nInsertionSort + " instructions");
	}
	
	public static void quick_sort(List<Integer> a, int start, int end){
		if(start < end){
			medianOfThree(a, start, end);
			int pivot = (start + end) / 2;
			int lower = start;
			int upper = end;
			while(lower < upper){
				while(a.get(lower) < a.get(pivot))
					lower++;
				while(a.get(upper) > a.get(pivot))
					upper--;
				if(lower <= upper){
					int temp = a.get(lower);
					a.set(lower, a.get(upper));
					a.set(upper, temp);
					lower++;
					upper--;
				}
			}
			if(start < upper)
				quick_sort(a, start, upper);// sort left of pivot
			if(lower < end)
				quick_sort(a, lower, end);// sort right of pivot
		}
	}
	
	public static void medianOfThree(List<Integer> a, int s, int e){
		List<Integer> findMedian = new ArrayList<Integer>();
		int m = (s + e) / 2;
		findMedian.add(a.get(s));
		findMedian.add(a.get(m));
		findMedian.add(a.get(e));
		Collections.sort(findMedian);
		if(a.get(s) == findMedian.get(1)){
			int temp = a.get(s);
			a.set(s, a.get(m));
			a.set(m, temp);
			
		}
		else if(a.get(e) == findMedian.get(1)){
			int temp = a.get(e);
			a.set(e, a.get(m));
			a.set(m, temp);
		}
	}
	
	public static void insertion_sort(List<Integer> a){
		for(int i = 1; i < a.size(); i++){
			int current = a.get(i);
			int previous = i - 1;
			while(previous >= 0 && a.get(previous) > current){
				a.set(previous + 1, a.get(previous));
				previous--;
			}
			a.set(previous + 1, current);
		}
	}

}
