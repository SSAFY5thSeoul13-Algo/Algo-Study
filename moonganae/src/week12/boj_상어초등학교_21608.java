package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 상어초등학교;
 * 번호 : 21608
 * 난이도 : 실버1
 * 풀이시간 : 1시간 38분
 * 사용 알고리즘 : 시뮬레이션 
 */
public class boj_상어초등학교_21608 {

	/* 교실의 크기 */
	static int N;
	/* 교실, 좋아하는 학생배열 */
	static int[][] map, favor;
	
	// y, x, fCnt, eCnt
	static int[] target = new int[4];
	
	/* Delta Array */
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int limit = N*N;
		map = new int[N][N];
		favor = new int[limit+1][4];
		
		
		for(int i=0; i<limit; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int sNum = Integer.parseInt(st.nextToken());
			/* 좋아하는 학생 저장 */
			favor[sNum][0] = Integer.parseInt(st.nextToken());
			favor[sNum][1] = Integer.parseInt(st.nextToken());
			favor[sNum][2] = Integer.parseInt(st.nextToken());
			favor[sNum][3] = Integer.parseInt(st.nextToken());
			
			/* 자리앉기 */
			select(sNum);
		}
		
		System.out.println(cal());
	}

	/* 자리선택하기 */
	static void select(int curIdx) {
		// 타겟자리 초기화
		target[0] = N+1;
		target[1] = N+1;
		target[2] = 0;
		target[3] = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				/* 이미 선택된 자석은 넘겨라 */
				if(map[i][j] != 0) continue;

				int fCnt = 0;	// 선호하는 학생수
				int eCnt = 0;	// 비어있는 칸수
				for(int z=0; z<4; z++) {
					
					int zy = i + dy[z];
					int zx = j + dx[z];
					
					if(zy<0 || zx<0 || zx>=N || zy>=N) continue;
					
					int seat = map[zy][zx];  

					// 빈자리가 아니라면
					if( seat != 0) {
						// 좋아하는 학생인지 확인
						for(int idx : favor[curIdx]) {
							// 좋아하는 학생이라면
							if(seat == idx) {
								fCnt++;	// 증가
								break;
							}
						}
					}else {
						eCnt++;	// 빈방수 증가
					}
				}
				
				boolean suc = false;
				/* 우선순위 영역 */
				if(target[2] < fCnt) suc = true;
				else if(target[2] != fCnt) continue;
				else if(target[3] < eCnt) suc = true;
				else if(target[3] != eCnt) continue;
				else if(target[0] > i) suc = true;
				else if(target[0] != i) continue;
				else if(target[1] > j) suc = true;
				
				if(suc) {
					target[0] = i;
					target[1] = j;
					target[2] = fCnt;
					target[3] = eCnt;
				}
			}
		}

		// 선택된 자리앉기
		map[target[0]][target[1]] = curIdx;
	}
	
	/* 만족도 조사 */
	static int cal() {
		
		int satis = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				int curIdx = map[i][j];
				int fCnt = 0;
				for(int z=0; z<4; z++) {
					
					int zy = i + dy[z];
					int zx = j + dx[z];
					
					if(zy<0 || zx<0 || zx>=N || zy>=N) continue;
					
					int seat = map[zy][zx];  

					for(int idx : favor[curIdx]) {
						if(seat == idx) {
							fCnt++;
							break;
						}
					}
				}
				
				/*  만족도 계산 */
				if(fCnt == 2) {
					fCnt = 10;
				} else if(fCnt == 3) {
					fCnt = 100;
				} else if(fCnt == 4) {
					fCnt = 1000;
				}
				satis += fCnt;
			}
		}
		
		return satis;
	}
}
