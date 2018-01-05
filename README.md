package sum_of_n;

import java.util.HashMap;

public class SumProblem {

	public static void main(String args[]) {
		
		int[] numbers = { 2, 4, 3, 5, 7, 8, 9 ,10,1,2,7,7,7,7};
		getPairs(numbers, 9);
	}

	// Prints number of pairs in arr[0..n-1] with sum equal
	// to 'sum'
	public static void getPairs(int[] arr, int sum) {

        HashMap<Integer, Integer> hm = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			int first = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				int second = arr[j];
				if ((first + second) == sum) {
					if(!hm.containsValue(first) && !hm.containsValue(second))
					{
						hm.put(i, first);
						hm.put(i, second);
						System.out.printf("(%d, %d) %n", first, second);
					}
				}
			}
		}
	}

}
