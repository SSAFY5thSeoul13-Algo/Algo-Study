package week16.BOJ_1927_S1_최소힙;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BOJ_1927_S1_최소힙 {
	
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			//x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산
			if(x>0) {
				pq.add(x);
			}
			//x가 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우
			else if(x==0) {
				if(pq.isEmpty()) {//배열이 비어 있는 경우인데 가장 작은 값을 출력하라고 한 경우에는 0을 출력하면 된다.
					sb.append(0).append("\n");
				}else {
					sb.append(pq.poll()).append("\n");
				}
			}
		}
		
		System.out.println(sb);
		
	}
}
