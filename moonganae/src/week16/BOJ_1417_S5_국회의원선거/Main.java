package week16.BOJ_1417_S5_국회의원선거;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	/* 후보자 수, 1번후보 지지자수, 매수자수 */
	static int N, one, dark;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
	public static void main(String[] args) throws Exception{
		
		/* 국회의원은 최다 득표자가 당선이 된다.
		 * 후보수는 N명이며, 다솜이가 매수해야하는 사람의 수를 구하라.
		 *
		 *
		 * 풀이방식 :
		 * pq.peek() : 1번후보를 제외한 현재 최고 지지자수
		 * 
		 * pq.peek()에서 한명을 매수한다. == one++;
		 * 
		 */
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/* 후보수 N명 입력 */
		N = Integer.parseInt(br.readLine());
		/* 1번 후보 지지자 수 입력 */
		one = Integer.parseInt(br.readLine());
		/* 2~N 후보 지지자 수 입력 : Priority Queue */
		for(int i=1; i<N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		while(!pq.isEmpty() && one <= pq.peek()) {
			/* 1번후보를 제외한 최고 지지자를 가진사람캠프에서 매수하기 */
			pq.offer(pq.poll()-1);
			
			/* 매수완료 */
			one += 1;
			dark++;
		}
		
		System.out.println(dark);
		
	}

}
