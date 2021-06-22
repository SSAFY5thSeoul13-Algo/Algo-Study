package week16.BOJ_1927_S1_최소힙;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1927_S1_최소힙 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		//연산의 개수
		for (int i = 0; i < N; i++) {
			//입력된 연산
			int num = Integer.parseInt(br.readLine());
			
			//입력이 0인경우
			if(num == 0) {
				//입력되어 있는 수가 없으면 0
				if(pq.isEmpty()) {
					sb.append("0").append("\n");
				}
				//수가 있으면 가장 작은 값
				else {
					sb.append(pq.poll()).append("\n");
				}
			}
			//입력이 0이 아닌 경우 값을 저장
			else {
				pq.offer(num);
			}
		}
		
		//결과 출력
		System.out.println(sb.toString());
	}
}
