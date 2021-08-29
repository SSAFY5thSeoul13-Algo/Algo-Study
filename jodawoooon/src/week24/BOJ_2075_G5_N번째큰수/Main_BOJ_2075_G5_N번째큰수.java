package week24.BOJ_2075_G5_N번째큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 2075 N번째 큰 수
 * @Author : Daun JO
 * @Date : 2021. 8. 27. 
 * @Algorithm : Sliding Window
 *
 */
public class Main_BOJ_2075_G5_N번째큰수 {
	
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args)  throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				
				if(pq.size()<N){
					pq.add(input);
				}else {
					if(pq.peek()<input) {
						pq.poll();
						pq.add(input);
					}
				}
			}
		}
		
		
		System.out.println(pq.peek());
	}
	
}
