package week17.PRG_L1_K번째수;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution_PRG_Lv1_K번째수 {
	public static void main(String[] args) {
		
		int[] arr = new int[] {1, 5, 2, 6, 3, 7, 4};
		int[][] cmd = new int[][] {{2,5,3},{4,4,1},{1,7,3}};
		
		System.out.println(Arrays.toString(solution(arr, cmd)));
	}
	
	public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int c = 0; c < commands.length; c++) {
        	int i = commands[c][0];
			int j = commands[c][1];
			int k = commands[c][2];
			
			ArrayList<Integer> list = new ArrayList<>();
	        
			//i번째 숫자까지 자른다.
			for (int x = i-1; x < j; x++) {
				list.add(array[x]);
			}
			
			//정렬한다.
			Collections.sort(list);
			
			//k번째에 있는 수를 구한다.
			answer[c]=list.get(k-1);
		}
        return answer;
    }
}
