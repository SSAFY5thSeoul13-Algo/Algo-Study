package week19.PRG_Lv3_디스크컨트롤러;

import java.util.*;

public class Solution_PRG_Lv3_디스크컨트롤러 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,3},{1,9},{2,6}}));
	}
	
	public static int solution(int[][] jobs) {
		
		//SJF
		
        int answer = 0; //작업의 요청부터 종료까지 걸린 시간의 합
        int currTime = 0;
        int len = jobs.length;
        
        Arrays.sort(jobs, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
        	
        });
		//jobs : 요청 시간 기준 오름차순 정렬
        
        PriorityQueue<int[]> jobPQ = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
        });
        //job PQ : 처리시간 기준 오름차순 정렬

        jobPQ.add(jobs[0]);
        currTime = jobs[0][0];
        int idx = 1;
        
        while(!jobPQ.isEmpty()) {
        	
        	int[] job = jobPQ.poll(); //작업할 일 poll
        	currTime += job[1]; //현재 시간 갱신
        	answer += currTime - job[0]; //작업의 요청부터 종료까지 걸린 시간

        	
        	//현재까지 도착한 job pq에 넣기
        	while(idx < len && jobs[idx][0] <= currTime) {
        		jobPQ.add(jobs[idx++]);
        	}
        	
        	//아직 처리할 작업이 남았는데 jobPQ 비었다. 도착한pq없다
        	//currTime을 변화시키고 PQ에 제일 빨리 도착하는 job 넣기
        	if(idx < len && jobPQ.isEmpty()) {
        		currTime = jobs[idx][0];
        		jobPQ.add(jobs[idx++]);
        		
        	}
        }
        
        return answer / len;
    }
}
