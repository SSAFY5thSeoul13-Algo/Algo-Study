package week24.boj2075;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075_G5_N번째_큰_수 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 큐의 길이가 N과 같다면 
				if(pq.size()==N) {
					if(pq.peek()<num) {
						pq.poll();
						pq.add(num);
					}
				}else {
					pq.add(num);
				}
				
			}
		}
		
		System.out.println(pq.poll());

	}

}
