package week16.BOJ_2164_S4_카드2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164_S4_카드2 {
	static int N;
	static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		//큐에 N까지의 숫자 저장
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		
		int num = 0;
		
		//큐에 1개의 숫자만 남을 때 까지
		while(q.size() > 1) {
			//num이 홀수인 경우 가장 위에 있는 숫자를 버림
			if(num % 2 == 0) {
				q.poll();
			}
			//num이 짝수인 경우 가장 위에 있는 숫자를 제일 아래로 넣음
			else {
				q.offer(q.poll());
			}
			
			num++;
		}
		
		//마지막 남은 숫자 출력
		System.out.println(q.poll());
	}
}
