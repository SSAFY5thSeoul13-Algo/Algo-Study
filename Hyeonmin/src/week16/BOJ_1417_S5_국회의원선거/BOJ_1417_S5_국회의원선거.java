package week16.BOJ_1417_S5_국회의원선거;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 *우선순위 큐를 사용 1번을 제외한 후보의 득표를 구함
 *내림차순으로 정렬한 후에 하나씩 꺼내면서 1씩 감소하고 다솜이의 득표수와 매수한 사람수는 1씩 증가
 *꺼낸 수가 다솜이의 득표수 보다 적으면 종료 
 * */
public class BOJ_1417_S5_국회의원선거 {
	static int N, count;
	static int myVotes;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		
		//다솜이의 득표 수
		myVotes = Integer.parseInt(br.readLine());
		
		//나머지 후보들
		for (int i = 1; i < N; i++) {
			//득표 수
			int num = Integer.parseInt(br.readLine());
			
			//큐에 득표수 저장
			pq.offer(num);
		}
		
		//다솜이보다 득표수가 많은 후보가 존재하는 경우
		while(true) {
			//다솜이를 제외하고 가장 많이 득표한 후보의 득표 수
			int vote = pq.poll();
			
			//다솜이의 득표 수 보다 작은 경우 매수한 사람 수 출력 후 종료
			if(vote < myVotes) {
				System.out.println(count);
				return;
			}
			
			//다솜이보다 득표수가 크면 매수를 해서 다솜이의 득표 수와 매수한 사람 수가 증가
			myVotes++;
			count++;
			
			//매수당한 후보의 득표 수를 1 줄이고 다시 큐에 저장
			pq.offer(vote-1);
		}
	}
}
