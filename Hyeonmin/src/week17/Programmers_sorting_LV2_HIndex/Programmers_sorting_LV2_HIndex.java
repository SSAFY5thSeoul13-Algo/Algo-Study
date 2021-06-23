package week17.Programmers_sorting_LV2_HIndex;

import java.util.Arrays;

public class Programmers_sorting_LV2_HIndex {
	static int[] citations = {3,0,6,1,5};
	
	public static void main(String[] args) {
		System.out.println(solution());
	}
	
	static int solution() {
		int h = 0;
		
		Arrays.sort(citations);
		
		int[] arr = new int[citations[citations.length-1]+1];
				
		for (int num : citations) {
			arr[num]++;
		}
		
		int size = arr.length-1;
		
		for (int idx = size; idx > 0; idx--) {
			if(arr[idx] >= idx) {
				h = idx;
				break;
			}
			arr[idx-1] += arr[idx];
		}
		
		return h;
	}
}
