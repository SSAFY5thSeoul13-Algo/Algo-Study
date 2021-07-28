package week21.PROGRAMMERS_LV2_주식가격;

import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 떨어지지 않은 주식을 담을 큐
        Queue<Price> q = new LinkedList<>();
        
        // 주식가격 순회
        for(int i=0; i<prices.length; i++){
            
            // 현재 떨어지지 않은 주식가격들 순회
            int size = q.size();
            for(int j=0; j<size; j++){
                Price cur = q.poll();
                
                // 가격이 떨어짐.  
                if(prices[i] < cur.price){
                    // 떨어진 기간 기록
                    answer[cur.idx] = i - cur.idx;
                }
                // 안떨어짐.
                else{
                    // 다시 큐에 담기
                    q.offer(cur);
                }
            }
            // 방금 들어온 주식가격 큐에 넣기
            q.offer(new Price(i, prices[i]));
        }
        
        // 아직까지 떨어지지 않은 주식들을 순회하며, 마지막 시간(prices.length-1)과의 차이를 구함.
        while(!q.isEmpty()){
                Price cur = q.poll();
                answer[cur.idx] = prices.length-1 - cur.idx;
            }
        return answer;
    }
    class Price {
        int idx, price;
        public Price(int idx, int price){
            this.idx = idx;
            this.price = price;
        }
    }
}
