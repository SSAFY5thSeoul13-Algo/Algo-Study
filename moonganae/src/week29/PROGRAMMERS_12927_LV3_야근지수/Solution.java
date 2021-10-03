package week29.PROGRAMMERS_12927_LV3_야근지수;

import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = bf(n, works);
        return answer;
    }
    // 무지성으로 최댓값 하나씩 줄이기!
    public long  bf(int n, int[] works){
        
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works){
            q.offer(work);
        }
        
        /* 남은 정규시간 N시간 적용하기 */
        while(n-- != 0){
            int val = q.poll();
            // 남은 최대 작업량이 0일경우 야근 안해도 된다!
            if(val == 0) {
                q.clear();
                break;
            }
            
            // 아닐경우 최대 야근작업 1줄이기
            q.offer(val-1);
        }
        
        /* 야근지수 계산 */
        long cnt = 0;
        while(!q.isEmpty()){
            int val = q.poll();
            cnt += val*val;
        }
        return cnt;
    }
    
    public long cal(int[] works){
        
        long cnt = 0;
        
        for(int i=0; i<works.length; i++){
            int val = works[i];
            cnt += val*val;
        }
        
        return cnt;
    }
}