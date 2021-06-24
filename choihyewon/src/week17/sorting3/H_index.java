package week17.sorting3;

import java.util.Arrays;

public class H_index {
	static int[] citations = {3,0,6,1,5};
	public static void main(String[] args) {
		int answer = solution(citations);
		
		System.out.println(answer);
	}
	
	public static int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int h;
        for(int i=0; i<citations.length; i++) {
        	h = citations.length - i;
        	if(citations[i]>=h) {
        		answer = h;
        		break;
        	}
        }
        
        return answer;
    }

}
