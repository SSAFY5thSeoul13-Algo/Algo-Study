package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_트럭_13335 {

	/* 트럭수(N), 다리길이(W), 다리 최대하중(L) */
	static int N, W, L;
	static int[] weight;
	static int[] time;	// time[i] : i번째 트럭이 다리를 지날때의 시간
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		weight = new int[N];
		time = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		
		Queue<Integer> q = new LinkedList<>();
		int curW = 0;		// 현재 다리위 무게
		int inTime = 1;		// 진행시간(트럭 입고 시간
		int i=0;			// target 트럭 인덱스
		while(i < N) {
			int target = weight[i];
			
			/* 다리위에 올릴수 있다면 */
			if( curW + target <= L) {
				/* 지금 들어가는 놈이 끝나는 시간 = 들어가는 시간 + 다리길이 */
				time[i] = inTime + W;
				
				q.offer(target);
				q.offer(time[i]);
				
				curW += target;		// 무게 증가
				i++;				// 다음 트럭 준비
				inTime++;			// 시간증가
			}
			/* 무게가 많이 나간다. */
			else {
				curW -= q.poll();			// 트럭제거
				if(inTime < q.peek()) {		// 퇴출되는 트럭시간이 더 오래된 시간이면
					inTime = q.peek();		// 진행시간 갱신
				}
				
				q.poll();
			}
			
		}
		System.out.println(time[N-1]);
	}
}

/*
5 4 100
2 3 2 3 4
=> 9
5 3 100
2 3 2 3 4

5 4 5
2 3 2 3 4
=> 14

5 4 7
2 3 2 3 4
=> 11

10 100 10
10 10 10 10 10 10 10 10 10 10

2 2 20
10 10

10 50 100
10 10 10 10 10 10 10 10 10 10

9 5 5
2 2 2 2 1 1 1 1 1=
=> 19

9 6 5
2 2 2 2 1 1 1 1 1
*/
