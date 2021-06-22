package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 지각하면 안돼;
 * 번호 : 12763
 * 난이도 : 골2
 * 풀이시간 : 2시간
 * 사용 알고리즘 : dfs + 백트래킹
 * 
 * 막힌점
 * [1] 가지치기를 할때 M(보유돈)을 넘었을때를 가지치기를 하지 않았다.
 */
public class boj_지각하면안돼_12763 {
	/* 건물수, 제한시간, 현재돈, 길의 개수 */
	static int N, T, M, L;
	/* 건물별 이동비용 */
	static int[][] money;
	/* 건물별 이동시간 */
	static int[][] time;
	/* 선택한 건물확인 배열 */
	static boolean[] select;
	
	/* 이동시간합, 사용한 돈합 */
	static int tSum, mSum;
	/* 정답: 최소비용 */
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(br.readLine());
		
		/* 건물이 1~N까지 존재한다 */
		money = new int[N+1][N+1];
		time = new int[N+1][N+1];
		select = new boolean[N+1];
		
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			time[s][e] = time[e][s] = t;
			money[s][e] = money[e][s] = m;
		}
		
		
		min = Integer.MAX_VALUE;
		
		select[1] = true;
		tSum = 0; mSum = 0;
		dfs(1, 1);

		// 조건에 맞는 min을 찾지못한 경우
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		// 조건에 맞는 min을 찾은 경우
		else System.out.println(min);
	}
	
	static void dfs(int num, int cur) {
		
		// 원하는 건물에 도착했을경우
		if(select[N]) {
			// 비용최솟값 갱신
			min = Math.min(min,  mSum);
			return;
		}
		// 건물에 결국 도달못했을경우
		else if(num == N)  return;
		
		for(int i=2; i<=N; i++) {
			if(select[i]) continue;				// 이미 선택한경우
			
			int t=time[cur][i];
			int m=money[cur][i];
			
			if(time[cur][i] == 0) continue;		// 길이없는 경우
			if(tSum + t > T) continue;			// 시간이 초과한경우
			if(mSum + m > M) continue;			// 돈이 초과한경우
			if(mSum + m > min) continue;		// 첫번째 구한길보다 비용이 더드는 경우
			
			// cur -> i로 가는길 선택
			select[i] = true;
			tSum += t;
			mSum += m;
			dfs(num+1, i);
			
			// cur -> i로 가는길 선택해제
			select[i] = false;
			tSum -= t;
			mSum -= m;
		}
	}
}