package week19.PROGRAMMERS_LV3_이중우선순위큐;

import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        // 최대힙, 최소힙 선언
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1,o2)->o2-o1);
        PriorityQueue<Integer> minPq = new PriorityQueue<>((o1,o2)->o1-o2);
        
        for(String oper : operations){
            StringTokenizer st = new StringTokenizer(oper);
            
            char cmd = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            // 숫자삽입
            if(cmd == 'I'){
                maxPq.offer(num);
                minPq.offer(num);
            }
            // 숫자 삭제
            else if(!maxPq.isEmpty()){
                // 최솟값 삭제
                if(num == -1){
                    int min = minPq.poll();
                    maxPq.remove(min);
                }
                // 최댓값 삭제
                else{
                    int max = maxPq.poll();
                    // System.out.println(max + "삭제");
                    minPq.remove(max);
                }
            }
        }
        // 큐가 비어있다면 0,0 반환
        if(maxPq.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            answer[0] = maxPq.poll();
            answer[1] = minPq.poll();
            
        }
        
        return answer;
    }
}