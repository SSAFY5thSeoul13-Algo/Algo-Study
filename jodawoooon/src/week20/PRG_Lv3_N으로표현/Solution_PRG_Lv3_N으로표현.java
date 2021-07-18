package week20.PRG_Lv3_N으로표현;
import java.util.*;

class Solution {
    static int ans = Integer.MAX_VALUE;
    public int solution(int N, int number) {
        
        dfs(0,0, N, number);
        
        if(ans>8) return -1;
        else return ans;
    }
    
    static void dfs(int idx, int sum, int N, int number){
        if(idx>8) {
            return;
            //최솟값이 8보다 크면 -1을 return 
        }
        
        if(sum==number){ //target number
            ans = Math.min(ans, idx);
            //N 사용횟수의 최솟값
        }
        
        //5, 55, 555, 5555
        int operNum = N;
        for(int i=1 ; i < 8 ; i++){
            
            dfs(idx+i, sum+operNum, N, number);
            dfs(idx+i, sum-operNum, N, number);
            dfs(idx+i, sum*operNum, N, number);
            dfs(idx+i, sum/operNum, N, number);
            //사칙연산만
            
            
            operNum *= 10;
            operNum += N;
            
            
        }
    }
}