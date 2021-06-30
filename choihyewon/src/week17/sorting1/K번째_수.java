package week17.sorting1;

import java.util.Arrays;

public class K번째_수 {
	static int[] array = {1,5,2,6,3,7,4};
	static int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
	static int[] answer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		answer = solution(array, commands);
		
		for (int i : answer) {
			System.out.println(i);
		}

	}
	
	public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i=0; i<commands.length; i++) {
        	// i번째 숫자부터 j번째 숫자까지 자르고 정렬할 배열 생성 
        	int[] tmp = new int[commands[i][1]-commands[i][0]+1];
        	for(int j=0; j<tmp.length; j++) {
        		tmp[j] = array[commands[i][0]-1+j];
        	}
        	// 자른 후 정렬 
        	Arrays.sort(tmp);
        	answer[i] = tmp[commands[i][2]-1];
        }
       
        return answer;
    }
	

}
