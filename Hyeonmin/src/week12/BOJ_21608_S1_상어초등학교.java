package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21608_S1_상어초등학교 {
	static int N;
	//학생들 배치정보
	static int[][] map;
	//각 위치 학생의 인접 좋아하는 사람 수
	static int[][] fav;
	//좋아하는 학생 번호
	static int[][] arr;
	//상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		fav = new int[N][N];
		arr = new int[N*N+1][5];
		
		//각 학생이 좋아하는 학생 정보를 저장. arr[n][0]은 해당 학생의 번호이고 1~4가 좋아하는 학생 번호 
		for (int n = 1; n <= N*N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				arr[n][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//모든 학생들에 대해서 자리를 배치
		for (int n = 1; n <= N*N; n++) {
			int y = -1;
			int x = -1;
			int num = 0;
			int empty = 0;
			
			//각 위치에 대해서
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//이미 앉은 사람이 있으면
					if(map[i][j] != 0)
						continue;
					
					//현재 위치에서 인접한 좋아하는 학생 수
					int nnum = 0;
					//현재 위치에서 인접한 빈 자리 수
					int nempty = 0;
					
					//4방향
					for (int d = 0; d < 4; d++) {
						int ny = i+dy[d];
						int nx = j+dx[d];

						//해당 위치가 범위 이내이면
						if(canGo(ny, nx)) {
							for (int k = 1; k < 5; k++) {
								//그 위치가 비어있는 경우
								if(map[ny][nx] == 0)
									nempty++;
								//그 위치에 좋아하는 학생이 있는 경우
								else if(arr[n][k] == arr[map[ny][nx]][0])
									nnum++;
							}
						}
					}
					
					//새로 구한 자리에 좋아하는 학생이 더 많은 경우
					if(nnum > num) {
						num = nnum;
						y = i;
						x = j;
						empty = nempty;
					}
					//새로 구한 자리에 좋아하는 학생은 같고 빈 공간이 더 많은 경우
					else if(nnum == num && nempty > empty) {
						num = nnum;
						y = i;
						x = j;
						empty = nempty;
					}
					//모든 빈 자리의 인접에 좋아하는 학생이나 빈 자리가 없으면 가장 먼저 나온 빈자리를 해당 학생의 위치리 해준다
					else if(y == -1){
						y = i;
						x = j;
					}
				}
			}
			
			//자리 배치에 n번째 학생인 것을 표시
			map[y][x] = n;
			//해당 자리 학생의 인접한 좋아하는 학생 수를 저장
			fav[y][x] = num;
			
			//새로 배치한 학생의 주변자리들에 대해서
			for (int d = 0; d < 4; d++) {
				int ny = y+dy[d];
				int nx = x+dx[d];
				
				//범위 이내이고 앉아있는 학생이 있으면
				if(canGo(ny, nx) && map[ny][nx] != 0) {
					int n1 = map[ny][nx];
					
					//앉아있는 학생이 좋아하는 학생과 일치하면
					for (int k = 1; k < 5; k++) {
						//앉아있는 학생의 인접한 좋아하는 학생 수를 1 증가
						if(arr[n1][k] == arr[n][0]) {
							fav[ny][nx]++;
						}
					}
				}
			}
		}
		
		int result = 0;
		
		//각 자리 학생들의 만족도를 계산
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += Math.pow(10, fav[i][j]-1);
			}
		}
		
		System.out.println(result);
	}
	
	static boolean canGo(int y, int x) {
		if(y < 0 || x < 0 || x >= N || y >= N)
			return false;
		
		return true;
	}
	
}