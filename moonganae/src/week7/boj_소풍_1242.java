package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 소풍
 * 번호 : 1242
 * 난이도 : 골2
 * 풀이시간 : 40분
 * 사용 알고리즘 : 요세푸스 
 */
public class boj_소풍_1242 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		/* 총 인원수 */
		int N = Integer.parseInt(st.nextToken());
		/* 퇴장시킬 번호 */
		int K = Integer.parseInt(st.nextToken());
		/* 동호번호 : 0번부터 시작하기위해서 -1 */
		int M = Integer.parseInt(st.nextToken()) -1;

		/* 횟수 */
		int cnt = 1;
		/* 타겟번호 */
		int target = -1;
		while(true) {
			
			/* 아웃당할 사람 번호 */
			target += K;
			
			// 만약 범위를 벗어나면 보정
			if(target >= N)
				target %= N;
			
			// 타겟이 동호라면 멈추기
			if(target == M)
				break;
			// 타겟의 번호가 동호보다 뒤에 사람이면 전체인원수만 줄이기
			else if(target > M) {
				N--;
			}
			// 타겟의 번호가 동호보다 앞의 사람이면 동호번호와 전체인원수 줄이기
			else {
				N--;
				M--;
			}
			
			// 삭제하고 학생번호 줄이기
			target--;
			
			// 횟수증가
			cnt++;
		}
		System.out.println(cnt);
	}
}
