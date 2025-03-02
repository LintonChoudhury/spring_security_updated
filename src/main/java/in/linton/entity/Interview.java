package in.linton.entity;

import java.util.HashSet;

public class Interview {

//	public static void main(String[] args) {
//		HashSet<Integer> numbers = new HashSet<>();
//		numbers.add(1);
//		numbers.add(2);
//		numbers.add(1);
//		numbers.add(2);
//		numbers.add(1);
//		numbers.add(2);
//		numbers.add(1);
//		numbers.add(2);
//		System.out.println(numbers);
//	}
//	public static void main(String[] args) {
//		 int[] nums = {3, 7, 1, 2, 8, 4, 5};
//	        System.out.println("Missing number is: " + interview(nums));
//	}
//
//	private static int interview(int[] nums) {
//		int n = nums.length;
//        int totalSum = (n + 1) * (n + 2) / 2;
//        int sumOfArray = 0;
//        for (int num : nums) {
//            sumOfArray += num;
//        }
//        return totalSum - sumOfArray;
//    }
	
	public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[] mergedArray = new int[m + n];
        
        int i = 0, j = 0, k = 0;

        // Merge the two arrays
        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        // If there are remaining elements in arr1
        while (i < m) {
            mergedArray[k++] = arr1[i++];
        }

        // If there are remaining elements in arr2
        while (j < n) {
            mergedArray[k++] = arr2[j++];
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};

        int[] mergedArray = mergeArrays(arr1, arr2);

        // Print the merged array
        for (int num : mergedArray) {
            System.out.print(num + " ");
        }
    }
	
}
