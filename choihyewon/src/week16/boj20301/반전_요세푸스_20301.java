package week16.boj20301;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 반전_요세푸스_20301 {
	static int N,K,M;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deque = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			deque.add(i);
		}
		
		
		int cnt = 0;
		boolean reverse = false;
		while(!deque.isEmpty()) {
			// 제거한 사람이 M명이 되거나 cnt가 0외 되는 경우 원을 돌리는 방향을 바꾼다. 
			if(cnt==M) {
				reverse = true;
			}else if(cnt==0){
				reverse = false;
			}
			
			// 역순으로 방향이 돌 때 
			if(reverse) {
				for(int i=0; i<K; i++) {
					// K번째가 되면 deque의 마지막에 위치한 사람을 제거한다.
					if(i==K-1) {
						cnt--;
						System.out.println(deque.pollLast());
					}
					// deque의 마지막에 있는 사람을 젤 앞으로 넣는다. 여기서 push는 deque의 앞부분에 element를 삽입한다.
					else {
						deque.push(deque.pollLast());
					}
				}
			}
			// 원래 돌리는 방향 
			else if(!reverse){
				for(int i=0; i<K; i++) {
					if(i==K-1) {
						cnt++;
						System.out.println(deque.poll());
					}else {
						deque.add(deque.poll());
					}
				}
			}
			
			
		}
	}

}

