package week21.Programmers_LV2_프린터;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_LV2_프린터 {
	public static void main(String[] args) {
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		
		System.out.println(solution(priorities, location));
	}
	
	static int solution(int[] priorities, int location) {
		//각 문서의 번호와 중요도를 저장할 큐
		Queue<int[]> queue = new LinkedList<>();
		//남아 있는 문서의 중요도들을 내림차순으로 저장할 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		//문서의 중요도와 번호를 저장
		for (int i = 0; i < priorities.length; i++) {
			queue.offer(new int[] {i, priorities[i]});
			pq.offer(priorities[i]);
		}
		
		//인쇄된 문서의 수
		int count = 0;
		
		//남은 문서 작업이 있는 경우
		while(!pq.isEmpty()) {
			//현재 남아있는 작업 중 가장 높은 우선순위
			int priority = pq.poll();
			
			while(!queue.isEmpty()) {
				//다음 순서에 있던 문서 작업
				int[] document = queue.poll();
				
				//해당 문서 작업의 우선순위가 지금 남아있는 문서들의 중요도중 가장 높은 경우
				if(priority == document[1]) {
					//문서를 인쇄
					count++;
					
					//인쇄한 문서가 목표 문서인 경우 그 문서가 몇번째로 인쇄된 문서인지 리턴
					if(document[0] == location)
						return count;
					
					break;
				}
				//해당 문서보다 높은 중요도의 작업이 있는 경우 해당 작업을 다시 큐에 저장
				else {
					queue.offer(document);
				}
			}
			
		}
		
		return count;
		
	}
}
