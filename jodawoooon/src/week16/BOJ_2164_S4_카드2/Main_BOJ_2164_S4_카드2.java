package week16.BOJ_2164_S4_카드2;

import java.io.*;
import java.util.*;

public class Main_BOJ_2164_S4_카드2 {
	
	//카드놀이
	//1. 제일 위에 있는 카드를 바닥에 버린다.
	//2. 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
	// -> 제일 마지막에 남는 카드 구하기
	
	static int N;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<>();
		
		N = Integer.parseInt(br.readLine());
		
		//1번 카드가 제일 위에, N번 카드가 제일 아래인 상태로 순서대로 카드가 놓여 있다.
		for (int i = 1; i <= N ; i++) {
			queue.add(i);
		}
		
		while(queue.size()>1) {
			//1. 제일 위에 있는 카드를 바닥에 버린다.
			queue.poll();
			
			//2.그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
			queue.add(queue.poll());
		}
		
		System.out.println(queue.poll());
	}
}
