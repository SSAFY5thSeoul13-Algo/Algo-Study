package week16.boj1927;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 최소_힙_1927 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(!pq.isEmpty()) {
					int tmp = pq.poll();
					sb.append(tmp).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else {
				pq.offer(x);
			}
		}
		
		System.out.println(sb.toString());

	}

}
