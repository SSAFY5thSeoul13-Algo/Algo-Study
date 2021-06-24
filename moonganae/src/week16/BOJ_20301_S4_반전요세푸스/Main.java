package week16.BOJ_20301_S4_반전요세푸스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N, K, M;
	static boolean dir = true;
	static Deque<Integer> dq = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		/*
		 * 요세푸스 문제
		 * M명의 사람이 제거될때 마다 원을 돌리는 방향을 계속해서 바꾸려고 한다.
		 * 
		 * (N,K,M)- 반전 요세푸스 순열
		 * 
		 * [풀이]
		 * Dequeue를 이용하여, 문제를 해결할 수 있음.
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N, K, M 입력
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 1~N deque에 삽입
		for(int i=1; i<=N; i++) {
			dq.offer(i);
		}
		int cnt = 0;
		int remove = -1;
		StringBuilder sb = new StringBuilder();
		while(!dq.isEmpty()) {
			
			// 방향에 따라 K-1번 만큼 빼서 넣기
			// 정방향
			if(dir) {
				turn();
				remove = dq.removeFirst();
			}
			// 역방향
			else {
				reverse();
				remove = dq.removeLast();
			}
			// K번째 제거
			sb.append(remove + "\n");

			// M명제거인지 확인하고 방향조절
			if(++cnt == M) {
				dir = !dir;
				cnt = 0;
			}
		}
		System.out.println(sb.toString());
		
	}
	// 정방향 돌리기
	static void turn() {
		for(int i=1; i<K; i++) {
			dq.offer(dq.removeFirst());
		}
	}
	// 역방향 돌리기
	static void reverse() {
		for(int i=1; i<K; i++) {
			dq.push(dq.removeLast());
		}
	}

}
