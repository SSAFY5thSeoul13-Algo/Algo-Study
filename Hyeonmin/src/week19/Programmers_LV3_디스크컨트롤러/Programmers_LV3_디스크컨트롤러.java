package week19.Programmers_LV3_디스크컨트롤러;

import java.util.PriorityQueue;

public class Programmers_LV3_디스크컨트롤러 {
	
	//소요 시간으로 정렬되는 클래스
	static class Node implements Comparable<Node>{
		//소요 시간
		int time;
		//요청 시점
		int start;
		
		public Node(int time, int start) {
			super();
			this.time = time;
			this.start = start;
		}

		@Override
		public int compareTo(Node o) {
			return this.time-o.time;
		}
	}
	
	public static void main(String[] args) {
		int[][] jobs = {
				{0,4},
				{0,1},
				{0,2},
				{0,3}
		};
		
		int result = solution(jobs);
		
		System.out.println(result);
	}
	
	static int solution(int[][] jobs) {
		//현재 시점에 요청이 된 작업을 소요 시간으로 정렬할 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		//큐에 넣었는지 여부를 저장
		boolean[] check = new boolean[jobs.length];
		
		//큐에 넣은 작업의 수
		int count = 0;
		
		//요청 시간이 0인 작업을 큐에 저장
		for (int i = 0; i < jobs.length; i++) {
			if(jobs[i][0] == 0) {
				pq.offer(new Node(jobs[i][1], jobs[i][0]));
				check[i] = true;
				count++;
			}
		}
		
		//각 작업의 요청 시간에서부터 완료까지의 걸린 시간들의 합
		int sum = 0;
		//현재 시간
		int time = 0;
		
		while(true) {
			//우선순위 큐에 값이 더 없는 경우
			if(pq.isEmpty()) {
				//모든 작업 처리가 끝났으면 중단
				if(count == jobs.length)
					break;
				
				//작업이 남았으면 다음 시간으로 이동
				time++;
			}
			//우선수위 큐에 작업이 있는 경우
			else {
				Node node = pq.poll();
				
				//현재 작업을 처리하고 난 후의 시간
				time += node.time;
				
				//현재 작업이 처리될 떄 까지 걸린 시간을 더함
				sum += time - node.start;
			}
			
			//현재 시간 기준 아직 처리하지 않은 작업중 요청이 된 작업을 우선순위 큐에 저장
			for (int i = 0; i < jobs.length; i++) {
				if(!check[i] && jobs[i][0] <= time) {
					pq.offer(new Node(jobs[i][1], jobs[i][0]));
					check[i] = true;
					count++;
				}
			}
			
		}
		
		//요청부터 처리까지 걸린 시간의 평균
		int average = sum/jobs.length;
		
		return average;
	}
}
