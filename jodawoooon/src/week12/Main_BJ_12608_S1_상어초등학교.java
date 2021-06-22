package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_12608_S1_상어초등학교 {
/*
	주어진 조건에 맞게 차례대로 구현하여 풀었습니다.

	각 학생이 좋아하는 학생들을 리스트에 셋팅한 뒤에
	2중 for문을 돌아 map안에서 1. 인접한 칸의 좋아하는 학생수와 2.인접한 빈칸 갯수를 세어가며 1번조건, 2번조건, 3번조건을 만족하는 tgtX, tgtY를 찾았습니다.

	그리고 모든 학생의 자리구하기가 끝나면 미리 값을 넣어둔 satArr에서 해당하는 만족도 점수를 찾아 답을 구했습니다


	- 결과
	18972	180
	
	1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
	2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
	3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
	*/
	
	static int N, map[][], ans;
	static ArrayList<Integer>[] list;
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1};
	//static int satArr[] = {0,1,10,100,1000};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //맵 크기
		
		map = new int[N][N];
		list = new ArrayList[N*N+1];
		
		for (int i = 1; i <= N*N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N*N; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken()); //학생 번호
			
			for (int j = 0; j < 4; j++) {
				list[num].add(Integer.parseInt(st.nextToken())); //학생이 좋아하는 사람 번호 추가
			}
			//list 셋팅
			
			//학생 자리 구하기
			int tgtX = 0;
			int tgtY = 0;
			int favCnt = -1;
			int empCnt = -1;
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if(map[x][y]!=0) continue;
					int tmpEmpCnt = 0;
					int tmpFavCnt = 0;
					
					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						if(nx<0||ny<0||nx>=N||ny>=N) continue; 
						
						
						if(list[num].contains(map[nx][ny])) tmpFavCnt++; //1. 인접한 좋아하는 학생 수 체크
						if(map[nx][ny]==0) tmpEmpCnt++; //2. 인접한 빈칸 갯수 체크
					}
					
					if(tmpFavCnt>favCnt) {
						//1번 조건
						favCnt = tmpFavCnt;
						empCnt = tmpEmpCnt;
						tgtX = x;
						tgtY = y;
						
					}else if(tmpFavCnt==favCnt && tmpEmpCnt > empCnt) {
						//2번조건
						
						favCnt = tmpFavCnt;
						empCnt = tmpEmpCnt;
						tgtX = x;
						tgtY = y;
						
					}
					// 3. >=가 아니므로 1,2를 만족하는게 여러개라면 행,열이 작은것을 우선으로 택한다
					
				}
			}
			
			//tgtX, tgtY를 찾았으면 idx를 집어넣는다
			map[tgtX][tgtY] = num;

			//print();
	
		}
		//학생 자리구하기 끝
		
		
		
		//학생 만족도 구하기
		//학생의 만족도를 구하려면 그 학생과 인접한 칸에 앉은 좋아하는 학생의 수를 구해야 한다. 
		//그 값이 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000이다.
		ans = 0; //만족도
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(nx<0||ny<0||nx>=N||ny>=N) continue; 
					
					if(list[map[x][y]].contains(map[nx][ny])) cnt++;
					//인접한 칸에 좋아하는 학생 있으면 cnt++
				}
				ans += Math.pow(10, cnt-1);
			}
		}
		
		System.out.println(ans);
		
	}
	private static void print() {
		for (int[] ia : map) {
			System.out.println(Arrays.toString(ia));
		}
		System.out.println("===========================");
	}
}
