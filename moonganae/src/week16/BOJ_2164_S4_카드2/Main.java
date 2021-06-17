package week16.BOJ_2164_S4_카드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author moonseounguk
 * 풀이시간 : 5분
 */
public class Main {

	public static void main(String[] args) throws Exception{
		/**
		 * N장의 카드 (위)1~N(아래) 의 순서로 되어있다.
		 * 
		 * 다음과 같은 동작을 한장 남을 때까지 반복한다.
		 * 1. 제일 위의 카드를 바닥에 버림.
		 * 2. 그다음 제일 위에 있는 카드를 제일 아래에 있는 카드 밑을 옮긴다.
		 * 
		 * [풀이]
		 * Queue자료구조를 이용하자.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N 입력받기
		int N = Integer.parseInt(br.readLine());
		// Queue에 1~N까지 숫자 넣기
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		// 한장이 남을때까지
		while(q.size() > 1) {
			// 1. 버리기
			q.poll();
			// 2. 뒤로넣기
			q.offer(q.poll());
		}
		
		// 마지막 남은 카드 출력
		System.out.println(q.peek());
	}

}
