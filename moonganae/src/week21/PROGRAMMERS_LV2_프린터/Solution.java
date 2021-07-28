package week21.PROGRAMMERS_LV2_프린터;

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        // 인쇄의 작업 큐
        Queue<Doc> q = new LinkedList<>();
        
        // 중요도를 정렬할 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>( (o1, o2) -> o2 - o1);
        
        // 작업 큐와 우선순위 큐에 넣음
        int curPrior = priorities[location];
        for(int i=0; i<priorities.length; i++){
            q.offer(new Doc(i, priorities[i]));
            pq.offer(priorities[i]);
        }
        
        
        while(!q.isEmpty()){
            
            Doc cur = q.poll();
            // 우선순위가 높은 작업이라면
            if(pq.peek() == cur.priority){
                pq.poll();
                // 작업완료
                answer++;
                
                // 내가 인쇄요청한 문서라면
                if(cur.idx == location){
                    // 중단
                    break;
                }
            }else{
                // 처리못하면 다시 넣음.
                q.offer(cur);
            }
        }
                    
        return answer;
    }
    class Doc {
        int idx, priority;
        public Doc(int idx, int priority){
            this.idx = idx;
            this.priority = priority;
        }
    }
}

