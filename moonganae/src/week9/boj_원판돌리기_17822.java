package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 문제명 : 원판돌리기
 * 번호 : 17822
 * 난이도 : 골3
 * 풀이시간 : 1시간 30분
 * 사용 알고리즘 : 시뮬레이션
 */
public class boj_원판돌리기_17822 {

	/* 원판의 수, 각 원판의 수, 원판 회전동작 수 */
	static int N, M, T;
	/* 원판 */
	static int[][] pan;
	/* 없는 숫자 상수 */
	static final int NONE = -1;
	
	/* delta array */
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		pan = new int[N+1][M+1];
		
		/* 초기원판 입력받기 */
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}

		/* 회전 */
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());	// 돌릴 원판 배수번호
			int d = Integer.parseInt(st.nextToken());	// 방향
			int k = Integer.parseInt(st.nextToken());	// 회전수
			
			/* x의 배수 회전 */
			for(int i=x; i<=N; i++) {
				if(i%x == 0) {			// x의 배수만
					rotate(i,d,k);		// 회전
				}
			}

			/* 인접 같은수 삭제 */
			delete(x);

		}
		
		/* 원판에 남아있는 수 구하기 */
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(pan[i][j] != NONE) {
					cnt += pan[i][j];
				}
			}
		}
		System.out.println(cnt);
	}

	private static void delete(int x) {
		/* 삭제할 원판의 좌표 저장리스트 */
		List<Integer> list = new ArrayList<>();
		
		/* 삭제할게 없을시 평균을 구하기 위한 변수 */
		int cnt = 0;	// 남아있는 수의 갯수
		int sum = 0;	// 남아있는 수의 합
		
		/* i : 원판 번호 */
		for(int i=1; i<=N; i++) {
			/* j :  원판에 있는 수의 인덱스 */
			for(int j=1; j<=M; j++) {
				int curNum = pan[i][j];
				/* 숫자가 없다면 넘겨라 */
				if(curNum == NONE) continue;
				
				/* 숫자니까 갯수와 합증가 */
				cnt++;
				sum += curNum;
				
				/* 현재 삭제리스트의 길이 */
				int listSize = list.size();
				for(int z=0; z<2; z++) {
					int zy = i + dy[z];
					int zx = j + dx[z];
					
					if(zy<1 || zy>N) continue;
					else if(zx < 1){
						zx = M;
					}else if(zx > M) {
						zx = 1;
					}
					
					if(curNum != pan[zy][zx]) continue;
					
					list.add(zy);
					list.add(zx);
				}
				
				/* 리스트의 길이가 변동이 생김 == 나와 같은 수 있음 => 나도 삭제!! */
				if(listSize != list.size()) {
					list.add(i);
					list.add(j);
				}
			}
		}
		int size = list.size();
		
		/* 삭제할게 없음!! */
		if(size == 0) {
			/* 평균을 구해서 */
			float avg = (float)sum / cnt;
			
			
			for(int i=1;i<=N;i++) {
				for(int j=1; j<=M; j++) {
					if(pan[i][j] == NONE) continue;
					/* 평균보다 크면 -1 */
					if(pan[i][j] > avg) {
						pan[i][j]--;
					}
					/* 평균보다 작으면 +1 */
					else if(pan[i][j] < avg) {
						pan[i][j]++;
					}
				}
			}
		}
		/* 삭제할게 있다면 */
		else {
			for(int i=0; i<size; i+=2) {
				int zy = list.get(i);
				int zx = list.get(i+1);
				/* 삭제! */
				pan[zy][zx] = NONE;
			}
		}
	}

	/* x원판을 d방향으로 k만큼 회전 */
	private static void rotate(int x, int d, int k) {
		/* 시계방향 */
		if(d==0) {
			for(int i=0; i<k; i++) {
				int tmp = pan[x][M];
				for(int j=M; j>1; j--) {
					pan[x][j] = pan[x][j-1];
				}
				pan[x][1] = tmp;
			}
		} 
		/* 반시계방향 */
		else {
			for(int i=0; i<k; i++) {
				int tmp = pan[x][1];
				for(int j=1; j<M; j++) {
					pan[x][j] = pan[x][j+1];
				}
				pan[x][M] = tmp;
			}
		}
	}
}
