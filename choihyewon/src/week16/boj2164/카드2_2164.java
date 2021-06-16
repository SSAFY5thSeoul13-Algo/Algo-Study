package week16.boj2164;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 카드2_2164 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		// 카드가 1장이 남을때까지 반복 
		while(queue.size()>1) {
			queue.poll();
			int tmp = queue.poll();
			queue.offer(tmp);
		}
		
		System.out.println(queue.poll());

	}

}
