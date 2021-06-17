package week16.BOJ_1927_S1_최소힙;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
/**
 * 
 * @author moonseounguk
 * 풀이시간 : 7분
 */
public class Main {

	public static void main(String[] args) throws Exception{
		
		/*
		 * 최소 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램 작성하라
		 * 1. 배열에 자연수 x를 넣는다.
		 * 2. (x가 0일때) 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
		 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
		 * 
		 * [풀이]
		 * 우선순위 큐를 이용하여 0이 나올때마다 poll을 해준다.
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e1-e2);
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0){
				if(pq.isEmpty()) {
					sb.append(0 + "\n");
				}else {
					sb.append(pq.poll() + "\n");
				}
				
			}else {
				pq.offer(x);
			}
		}
		System.out.println(sb.toString());
	}

}
