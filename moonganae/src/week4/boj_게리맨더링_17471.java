package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_게리맨더링_17471 {

	/* 구역의 수 */
	static int N;
	/* 구역당 인구수 */
	static int[] population;
	/* 인구수 총합, 정답저장변수 */
	static int totalPopulation = 0, min = Integer.MAX_VALUE;
	/* 인접구역정보 */
	static List<ArrayList<Integer>> link; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		link = new ArrayList<ArrayList<Integer>>();
		link.add(new ArrayList<>());				// link.get(1) : 1번째 구역을 나타내기 위해 0번째 temp List 삽입
		
		/* 구역당 인구수 저장 */
		StringTokenizer st= new StringTokenizer(br.readLine());
		population = new int[N+1];
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalPopulation += population[i]; 
		}
		
		/* 인접구역정보 저장 */
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			ArrayList<Integer> tmp = new ArrayList<>();
			
			int len = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<len; j++) {
				tmp.add(Integer.parseInt(st.nextToken()));
			}
			link.add(tmp);
		}
		
		// 구역조합시작
		comb(0,0,0,0);
		
		
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else 
			System.out.println(min);
	}
	/*	A선거구에 속할 구역을 뽑는 조합
	 * */
	static void comb(int num, int start, int aSelect, int aSize) {
		
		/* 구역을 1~N개까지 순회함 */
		if(1<= num && num <=N) {
			
			// A 선거구에 구역이 하나도 없거나, 모든 구역이 소속되어 있다면 잘못된 뽑기
			if(aSelect == 0 || aSelect == 1<<(N-1)) return;
			
			// 인구수 확인
			check(aSelect, aSize);
		}
		
		for(int i=start; i<N; i++) {
			if( (aSelect &1<<i) !=0) continue;
			
			comb(num+1, i+1, aSelect|1<<i, aSize+1);
		}
		
	}
	
	/* A, B 선거구에 속한 인구수 차이를 확인하는 함수
	 * */
	private static void check(int aSelect, int aSize) {
		
		/* 2번의 BFS과정을 통해서 각 선거구가 모두 연결되어 있는지 확인한다.
		 * 현재 bitCount를 사용하고 있기 때문에 bSelect = ~aSelect 이고,
		 * A 또는 B선거구에 속해야하기 때문에 bSize = N-aSize 이다. 
		 * */
		
		// 선거구 A 연결확인 
		boolean isLink = BFS(aSelect, aSize);
		if(isLink == false) return;
		// 선거구 B 연결확인
		isLink = BFS(~aSelect, N-aSize);
		if(isLink == false) return;
		
		// 인구수 차이 확인
		int aPopulation = 0;
		for(int i=0; i<N; i++) {
			if( (aSelect & 1<<i) != 0) {
				aPopulation += population[i+1];
			}
		}
		int bPopulation = totalPopulation-aPopulation;
		min = Math.min(min, Math.abs(aPopulation - bPopulation));
	}
	
	/* select : 선거구에 소속됨을 나타내는 bitCount
	 * size : 소속된 구역의 수
	 * 
	 * @return
	 * 			true : 선거구 내 모든 구역이 연결됨
	 * 			false : 선거구 내 모든 구역이 연결되지 않음.
	 *  */
	static boolean BFS(int select, int size) {
		Queue<Integer> q = new LinkedList<>();
		int cnt = 1;
		
		boolean[] vis = new boolean[N+1];
		// 선거구 확인
		for(int i=1; i<=N; i++) {
			// 선거구에 포함된 첫번째 구역찾기
			if( (select & 1<<(i-1)) != 0) {
				vis[i] = true;
				q.offer(i);
				break;
			}
		}
		// BFS 시작
		while(!q.isEmpty()) {
			
			int cur = q.poll();
			for(int conn : link.get(cur)) {
				
				// 이미 방문한 곳이라면 제외
				if(vis[conn]) continue;
				
				// 확인표시
				vis[conn] = true;
				// 선거구에 소속되지 않은 구역일 경우 제외
				if( (select & 1<<(conn-1)) == 0 ) continue;
				
				// 소속 구역 횟수증가
				cnt++;
				q.offer(conn);
			}
		}
		// 선거구 구역수와 일치하지 않는다
		if(cnt != size) {
			return false;
		} 
		// 선거구 구역수와 일치한다면
		return true;
	}
}
