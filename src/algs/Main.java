package algs;

import java.util.Random;

public class Main {
	public static final int MERGE = 1;
	public static final int INSERT = 0;
	public static final int ITERATIONS = 1000;
	public static void main(String[] args) {
		SortObject ms = new SortObject(); 
		Random rand = new Random();

		/*
		//this routine will be repeated ad nauseum to warm her up
		for(int i = 0; i < 100000; i++) {
			int[] arr = new int[1000];
			fill(arr, rand);
	
	        ms.sort(arr, 0, arr.length-1);
	        
			arr = new int[1000];
			fill(arr, rand);
	
	        ms.insertionSort(arr);
		}
		System.out.println("done warming up");
		*/
		
		
        //now we do the actual routine -- average 10 for each n while the time for 
		//insertion is shorter than the time for merge
		//repeat 100 times and take the average of n
		int total = 0;
		for(int i = 0; i < ITERATIONS; i++) {
			long insertionSortTime = 0;
			long mergeSortTime = 1000;
			int n = 10;

			while(true) {
				mergeSortTime = sortAverage(ms, rand, n, MERGE);
				insertionSortTime = sortAverage(ms, rand, n, INSERT);
	
				if(mergeSortTime < insertionSortTime) {
					System.out.println("Size for mergesort to be faster than insertion sort: " + n);
					break;
				}
				n = n+10;
	
			}
			total = total + n;
		}
		System.out.println("Average size for mergesort to be faster than insertion sort: " + total/ITERATIONS);
        
        
	}
	public static long sortAverage(SortObject ms, Random rand, int n, int TYPE) {
		long totalTime = 0;
		for(int i = 0; i < 10; i++) {
			int[] arr = new int[n];
			fill(arr, rand);
			long startTime = System.nanoTime();
			if(TYPE == MERGE) {
				ms.sort(arr, 0, arr.length-1);
			} else {
				ms.insertionSort(arr);
			}
	        totalTime = totalTime + System.nanoTime() - startTime;
		}
		return totalTime/10;
	}

	
	public static void fill(int[] arr, Random rand) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt();
		}
	}
	
	public static String printarr(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			sb.append(',');
		}
		sb.append(']');
		return sb.toString();
	}
	
	
}
