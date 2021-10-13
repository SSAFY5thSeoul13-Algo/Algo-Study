package week29.PRG_야근지수;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_PRG_야근지수 {
    public long solution(int n, int[] works) {
        long answer = 0;
 
        //n번동안 제일 큰 값들을 꺼내서 하나 줄이고 다시 넣고 반복하기
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) pq.add(work);
        
        for(int i =0 ; i< n; i++){
            if(pq.peek()==0) break;
            pq.add(pq.poll()-1);
        }
        while(!pq.isEmpty()) answer += Math.pow(pq.poll(),2);
        return answer;
    }
}
