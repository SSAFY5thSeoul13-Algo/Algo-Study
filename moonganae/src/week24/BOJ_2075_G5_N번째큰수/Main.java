package week24.BOJ_2075_G5_N번째큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
//	static int N;
//	public static void main(String[] args) throws Exception{
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		N = Integer.parseInt(br.readLine());
//		
//		List<Integer> list = new ArrayList<>();
//		
//		for(int i=0; i<N; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			for(int j=0; j<N; j++) {
//				list.add(Integer.parseInt(st.nextToken()));
//			}
//		}
//		
//		
//		Collections.sort(list, Collections.reverseOrder());
//		
//		System.out.println(list.get(N-1));
//	}
	
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 가장 큰수 N개를 가질 우선순위큐
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				
				int data = Integer.parseInt(st.nextToken());
				
				// 우선순위큐에 n개 이하라면 그냥 넣기
				if(q.size() < N) q.offer(data);
				// 현재 큐에서 가장 작은 수가 data보다 작다면
				else if(data > q.peek()) {
					// 데이터 교체
					q.poll();
					q.offer(data);
					
				}
			}
		}
		
		// q.peek() == N번째 큰수
		System.out.println(q.peek());
	}

}		
