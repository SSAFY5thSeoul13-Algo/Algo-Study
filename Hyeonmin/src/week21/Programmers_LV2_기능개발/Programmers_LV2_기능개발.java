package week21.Programmers_LV2_기능개발;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_LV2_기능개발 {
	
	//작업 진도와 개발 속도를 멤버변수로 갖음 
	static class Node{
		int progress;
		int speed;
		
		public Node(int progress, int speed) {
			super();
			this.progress = progress;
			this.speed = speed;
		}
	}
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		
		int[] result = solution(progresses, speeds);
		
		for (int i : result) {
			System.out.print(i+" ");
		}
	}
	
	static int[] solution(int[] progresses, int[] speeds) {
		//각 작업을 순서대로 저장할 큐
		Queue<Node> queue = new LinkedList<>();
		//작업이 완료되었을 때 배포되는 기능의 수를 저장할 큐
		Queue<Integer> resultQueue = new LinkedList<>();
		
		//큐에 각 작업 진도와 개발속도를 저장
		for (int i = 0; i < speeds.length; i++) {
			queue.offer(new Node(progresses[i], speeds[i]));
		}
		
		//경과 시간을 저장할 변수
		int time = 1;
		
		//작업이 남아있는 동안 반복
		while(!queue.isEmpty()) {
			//완료된 작업의 수
			int count = 0;
			
			while(!queue.isEmpty()) {
				//가장 앞에 남아있는 기능
				Node node = queue.peek();
				//현재 시간에 기능의 작업 정도
				int num = node.progress + node.speed*time;
				
				//100퍼센트가 넘지 않은 경우
				if(num < 100) {
					//현재 작업이 완료되는 시간
					time = (100-node.progress) % node.speed == 0 ? (100 - node.progress)/node.speed : (100 - node.progress)/node.speed +1;
					
					break;
				}
				
				//100퍼센트가 넘은 경우 작업 목록에서 제거
				queue.poll();
				//완료된 기능수 증가
				count++;
				
			}
			
			//완료된 기능이 있는 경우 결과에 배포된 기능의 수 저장  
			if(count != 0)
				resultQueue.add(count);
		}
		
		//배포된 결과를 저장한 큐의 정보를 배열로 저장
		int[] answer = new int[resultQueue.size()];
		
		for (int i = 0; i < answer.length; i++) {
			answer[i] = resultQueue.poll();
		}
		
		return answer;
	}
}
