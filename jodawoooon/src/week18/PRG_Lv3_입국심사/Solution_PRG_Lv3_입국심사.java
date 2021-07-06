package week18.PRG_Lv3_입국심사;

import java.util.Arrays;

public class Solution_PRG_Lv3_입국심사 {
	public long solution(int n, int[] times) {
        
        //이분탐색

        
        long answer = 0;
        
        //1. times 배열 오름차순 정렬
        Arrays.sort(times); //시간 순 정렬
        
        //2. left = 1 (속도가 1이며 사람이 1명) 
        //   right = 제일 오래 걸리는 심사관에게 모두 심사받는 경우
        long left = 1*1;
        long right = (long) times[times.length-1] * n;

        while(left <= right){
            long cnt = 0;
            long mid = (left+right)/2;
            
            for(int t : times){
                cnt += mid/t; 
                //mid 기준 심사할 수 있는 사람 수
            }
            
            if(cnt >= n){
                //n명을 심사할 수 있다
                //answer에 mid값 넣고
                answer = mid;
                //right를 줄여본다
                right = mid - 1;
                
            }else{
                //n명을 심사할 수 없다
                //left를 늘려본다
                left = mid + 1;
            }
        }
        return answer;
    }	
}
