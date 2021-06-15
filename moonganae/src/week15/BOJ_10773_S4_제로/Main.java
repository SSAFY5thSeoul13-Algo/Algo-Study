package week15.BOJ_10773_S4_제로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) 	throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		Stack<Integer> s = new Stack<>();
		
		/* 계산도중에 int 범위를 벗어날 수 있음으로 long 타입 */
		long sum = 0;
		
		for(int i=0; i<K; i++) {
			int data = Integer.parseInt(br.readLine());
			
			// 0일경우 stack에서 빼서 sum 갱신 
			if(data == 0 ) {
				if(!s.isEmpty()) {
					sum -= s.pop();
				}
			}
			// 0이 아닌경우 더하고 stack에 push 
			else {
				sum += data;
				s.push(data);
			}
		}

		System.out.println(sum);
	}
}
