package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 무엇을아느냐가아니라누구를아느냐가문제다;
 * 번호 : 9694
 * 난이도 : 골4
 * 풀이시간 : 47분
 * 사용 알고리즘 : 순열 + 백트래킹 
 */
public class boj_무엇을아느냐가아니라누구를아느냐가문제다_9694 {

	/* 관계수, 정치인수 */
	static int N, M;
	/* 정치인끼리의 친밀도 배열 */
	static int[][] relation;
	/* 순열에서 사용하는 선택하는 순서배열 */
	static int[] tgt;
	/* 순열에서 사용하는 선택확인 배열 */
	static boolean[] select;
	/* 순열에서 사용될 친밀도의 합 */
	static int sum;
	
	/* 인맥간 친밀도 최소합 */
	static int min;
	/* 최소합 친밀도의 인맥순서 */
	static int[] order;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			relation = new int[M][M];
			tgt = new int[M];
			select = new boolean[M];
			order = new int[M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				
				relation[s][e] = relation[e][s] = amount;
			}
			
			min = Integer.MAX_VALUE;
			sum = 0;
			select[0] = true;
			perm(1,0);
			StringBuilder sb = new StringBuilder("Case #"+t+": ");
			
			// min == MAX_VALUE 라는 것은 최고의원에게 닿을 인맥이 전혀 없다는 뜻
			if(min == Integer.MAX_VALUE) {
				sb.append(-1);
			}else {
				// 최고의원 전까지의 인맥 순서출력
				int idx=0;
				while(order[idx] != M-1) {
					sb.append(order[idx++]+ " ");
				}
				// 마지막 최고의원
				sb.append(M-1);
			}
			
			System.out.println(sb);
		}
		
	}
	/**
	 * 
	 * @param num 	: 선택수
	 * @param e		: 직전에 선택한 의원 idx 
	 */
	static void perm(int num, int e) {
		
		/* 최고의원에게 도달했다면 */
		if(select[M-1]) {
			if(min > sum) {
				// 인맥최소합 갱신
				min = sum;
				// 순서저장
				for(int i=0; i<num;i++) {
					order[i] = tgt[i];
				}
			}
			return;
		} // 최고의원에게 도달하지 못한채 끝까지 갔다면 
		else if(num == M-1) return;
		
		for(int i=1; i<M; i++) {
			
			if(select[i]) continue;						// 이미 선택된경우
			if(relation[e][i] == 0) continue;			// 인맥이 아닌경우
			if(sum + relation[e][i] > min) continue;	// min보다 큰경우 : backtracking
			
			/* i의원 연결하기 */
			tgt[num] = i;
			select[i]= true;
			sum += relation[e][i];
			perm(num+1, i);
			
			/* i 의원 연결해제 */
			select[i]= false;
			sum -= relation[e][i];
		}
	}
}
