package week21.PROGRAMMERS_LV2_기능개발;

import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        int limit = progresses.length;
        int sIdx = 0;
        
        while(sIdx < limit){
            
            // 작업 증가!
            
            for(int i=sIdx; i<limit; i++){
                progresses[i] += speeds[i];
            }
            
            // 끝난 작업 배포
            int cnt = 0;
            for(int i=sIdx; i<limit; i++){
                if(progresses[i] < 100) break;
                cnt++;
                sIdx++;
            }
            
            if(cnt != 0){
                answer.add(cnt);
            }
        }
        
        int[] ans = new int[answer.size()];
        
        for(int i=0; i<answer.size(); i++){
            ans[i] = answer.get(i);
        }
        
        
        return ans;
    }
}

