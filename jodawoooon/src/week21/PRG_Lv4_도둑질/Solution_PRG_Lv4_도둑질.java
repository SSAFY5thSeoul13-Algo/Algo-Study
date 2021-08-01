package week21.PRG_Lv4_도둑질;

import java.util.*;

public class Solution_PRG_Lv4_도둑질 {
	public int solution(int[] money) {
        int answer = 0;
        //money[] : 각 집에 있는 돈
        
        int LEN = money.length; //집의 개수
        
        //인접한 두집 털면 경보울림
        //한칸씩 건너서 훔쳐보자
        
        int dp1[] = new int[LEN];
        int dp2[] = new int[LEN];

        dp1[0] = money[0];
        dp1[1] = dp1[0];
        //첫번째 집 털었다 => 두번째 집 못털음
        
        dp2[0] = 0;
        dp2[1] = money[1];
        //첫번째 집 안털었다 => 두번째 집 털었다
        
        
        for(int i = 2 ; i < LEN ; i++){ 
            
            dp2[i] = Math.max(dp2[i-1], dp2[i-2]+money[i]); 
            //i-1까지 털었을 때 돈, i-2까지 털고 지금 i번째 터는 돈 중 더 큰 값
 
            if(i==LEN-1) continue; //dp1 : 첫번째 집 털었다 => 마지막 집 털면 안되니까 LEN-2까지
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+money[i]);
            //i-1까지 털었을 때 돈, i-2까지 털고 지금 i번째 터는 돈 중 더 큰 값
            
            
        }
        
        answer = Math.max(dp1[LEN-2], dp2[LEN-1]);
        return answer;
    }
}
