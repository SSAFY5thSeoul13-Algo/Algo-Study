package week34.devmatching1;

import java.util.*;

public class �ζ���_�ְ�_������_����_���� {

	class Solution {
	    public int[] solution(int[] lottos, int[] win_nums) {
	        int[] answer = new int[2];
	        int zero = 0;
	        int match = 0;
	        for(int i : lottos){
	            // 0�� �ƴ� ��쿡�� win_nums Ž�� 
	            if(i==0){
	                zero++;
	            }else{
	                for(int j : win_nums){
	                    if(i==j){
	                        match++;
	                        break;
	                    }
	                }
	            }
	        }
	        
	        
	        answer[0] = match !=0 ? 7-match-zero : (zero !=0 ? 7-zero : 6);
	        answer[1] = match !=0 ? 7-match : 6;
	        
	        return answer;
	    }
	}
}
