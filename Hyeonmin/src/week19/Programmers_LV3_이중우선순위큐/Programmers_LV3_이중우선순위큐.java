package week19.Programmers_LV3_이중우선순위큐;

import java.util.Collections;
import java.util.PriorityQueue;

public class Programmers_LV3_이중우선순위큐 {
	public static void main(String[] args) {
		String[] operations = {
				"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"
		};
		
		int[] result = solution(operations);
		
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+" ");
		}
	}
	
	static int[] solution(String[] operations) {
		//최소
		PriorityQueue<Integer> minPq = new PriorityQueue<>();
		//최대
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
		
		//명령
		for (int i = 0; i < operations.length; i++) {
			String str = operations[i];
			
			//최댓값 삭제
			if(str.equals("D 1")) {
				if(minPq.isEmpty()) continue;
				
				int num = maxPq.poll();
				minPq.remove(num);
			}
			//최솟값 삭제
			else if(str.equals("D -1")) {
				if(minPq.isEmpty()) continue;
				
				int num = minPq.poll();
				maxPq.remove(num);
			}
			//값 입력
			else{
				String[] split = str.split(" ");
				int num = Integer.parseInt(split[1]);
				
				minPq.offer(num);
				maxPq.offer(num);
			}
			
		}
		
		int min = 0;
		int max = 0;
		
		//남은 숫자가 있는 경우
		if(!minPq.isEmpty()){
			min = minPq.poll();
			max = maxPq.poll();
		}
		
		int[] answer = new int[] {max, min};
		
		return answer;
	}
}
