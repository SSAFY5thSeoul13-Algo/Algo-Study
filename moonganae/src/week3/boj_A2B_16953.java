package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_A2B_16953 {

	/* 해맨점
	 * Num 클래스 부분의 num 부분을 long이 아닌 int로 선언하고 풀어서 overflow가 발생하였다.
	 *  */
	static int A;
	static int B;
	static int min = -1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		// 값이 같다면 연산할필요가 없음
		if(A == B) {
			min = 1;
		} else {
			BFS();
		}
		
		System.out.println(min);
	}
	static void BFS() {
		
		Queue<Num> q = new LinkedList<>();
		// 처음 A를 큐에 넣는다.
		q.add(new Num(A, 1));
		
		while(!q.isEmpty()) {
			Num cur = q.poll();
			
			
			long doubleCur = cur.num * 2;			// 2배
			long addOne = cur.num*10 + 1;			// 오른쪽에 1추가하기
			if(doubleCur == B || addOne == B) {		// 정답을 찾았다면
				min = cur.cnt+1;					// 마지막 cnt+1
				break;
			} 
			
			// 범위를 벗어나지 않았을 경우 큐에 넣기
			if(doubleCur < B) {
				q.add(new Num(doubleCur, cur.cnt+1));
			}
			if(addOne < B) {
				q.add(new Num(addOne, cur.cnt+1));
			}
			
		}	
	}
	
	static class Num{
		long num;
		int cnt;

		public Num(long num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
	}

}
