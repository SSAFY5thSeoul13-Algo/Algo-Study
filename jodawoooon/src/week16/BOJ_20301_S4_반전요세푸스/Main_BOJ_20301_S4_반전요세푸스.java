package week16.BOJ_20301_S4_반전요세푸스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BOJ_20301_S4_반전요세푸스 {
	
	static int N, K, M, cnt, flag;
	static Deque<Integer> deque;
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//M명의 사람이 제거될 때 마다 방향이 바뀌는 요세푸스 순열
		StringBuilder sb = new StringBuilder();
		
		deque = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //사람의 수
		K = Integer.parseInt(st.nextToken()); //제거 대상
		M = Integer.parseInt(st.nextToken()); //방향 바뀔 조건 수
		
		for (int i = 1; i <= N; i++) {
			deque.add(i);
		}

		while(!deque.isEmpty()) {
			
			cnt++;
			
			if(flag%2==0) {
				for (int i = 1; i <= K; i++) {
					if(i==K) {
						sb.append(deque.pollFirst()).append("\n");
					}else {
						
						deque.addLast(deque.pollFirst());
					}
				}
			}else {
				for (int i = 1; i <= K; i++) {
					if(i==K) {
						sb.append(deque.pollLast()).append("\n");
					}else {
						deque.addFirst(deque.pollLast());
					}
				}
			}
			
			
			if(cnt==M) {
				flag++;		
				cnt=0;
			}
			
		
		}
		
		System.out.println(sb);
		
	}
	
}
