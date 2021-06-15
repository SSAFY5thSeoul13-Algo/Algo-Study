package week16.BOJ_12018_S3_YonseiTOTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 풀이시간 : 19분
 */
public class Main {
	/* 과목수, 성준이 마일리지 */
	static int N, M;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		/*
		 * 마일리지는 과목당 1~36으로 분배한다.
		 * 모든 분배가 끝이 나면 과목당 마일리지를 많이 투자한 순으로 수강인원만큼 신청되는 방식
		 * 
		 * 성준이가 주어진 마일리지로 최대로 들을 수 있는 과목 갯수를 출력하라.
		 * (단, 마일리지가 같다면 성준이에게 우선순위가 주어진다.)
		 * 
		 * [풀이]
		 * 1. 각 과목별 성준이가 투자해서 들을 수 있는 최소 마일리지 리스트를 구한다.
		 * 2. 최소 마일리지 리스트를 오름차순으로 정렬한다.
		 * 3. 마일리지가 모두 소진될 때까지 count 한다. 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 과목당 정보 입력 */
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			/* 신청한 과목사람 수 */
			int p = Integer.parseInt(st.nextToken());
			/* 과목 수강인원 */
			int l = Integer.parseInt(st.nextToken());
			/* 신청한 사람들이 i번째 과목에 투자한 마일리지 */
			int[] classList = new int[p];
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<p; j++) {
				classList[j] = Integer.parseInt(st.nextToken());
			}
			
			// 만약 수강인원이 널널하다면
			if(p < l) {
				// 마일리지 1만 사용
				list.add(1);
			}else {
				// 아니라면 턱걸이로 투자한 사람과 동일한 마일리지 제출하기 (마일리지 동일시 성준이가 우선)
				Arrays.sort(classList);
			
				list.add(classList[p-l]);
			}
		}
		
		// 과목당 최소투자마일리지들을 오름차순 정렬
		Collections.sort(list);
		
		/* 마일리지를 적게 사용하는 과목부터 투자 */
		int cnt = 0;
		for(int i=0; i<N; i++) {
			// 남은 마일리지로 과목수강이 안되면 끝
			if(M < list.get(i)) break;
			
			// 아니라면 해당과목에 마일리지 사용
			cnt++;
			M -= list.get(i);
		}
		System.out.println(cnt);
	}

}
