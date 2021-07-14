package week19.PROGRAMMERS_LV3_디스크컨트롤러;

import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Job> pq = new PriorityQueue<>();
        
        // 작업이 순서대로 들어온다는 보장이 없기때문에 정
        Arrays.sort(jobs, (o1, o2)-> o1[0]-o2[0]);
        
        // 현재 진행시간
        int curTime = 0;
        // 현재 큐에 넣은 작업 마지막인덱스
        int idx = 0;
        // 처리한 작업의 수
        int succCnt = 0;
        
        // 처리할 작업이 남았다면 수행
        while(succCnt < jobs.length){
            
            // 현재 시간까지의 들어온 작업 큐에 넣기
            while(idx < jobs.length && jobs[idx][0] <= curTime){
                pq.offer(new Job(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            // 큐가 비었다면 다음 작업들어올때까지의 시간으로 이동
            if(pq.isEmpty()){
                curTime = jobs[idx][0];
                continue;
            }
            
            // 현재 작업처리
            Job cur = pq.poll();
            curTime += cur.time;
            // 처리시간 추가하기
            answer += curTime - cur.start;
            // 성공한 작업수 증가
            succCnt++;
        }
        return (int) Math.floor(answer / jobs.length);
    }
    
    class Job implements Comparable<Job>{
        public int start, time;
        
        Job(int start, int time){
            this.start = start;
            this.time = time;
        }
        
        @Override
        public int compareTo(Job j){
            return this.time - j.time;
        }
    }
}