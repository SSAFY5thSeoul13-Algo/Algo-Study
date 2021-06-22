package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_15685_드래곤커브 {
	/*
	 * 0세대 드래곤커브 =>길이가 1인 선분
	 * 
	 * 즉, K(K > 1)세대 드래곤 커브는
	 *  K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 
	 *  그것을 끝 점에 붙인 것이다.
	 *  
	 *  0 : 우, 1 : 상, 2 : 좌, 3 : 하
	 *  
	 *  0 
	 *  0 1
	 *  0 1 2 1 (우 상 <-> 좌 상 (왼쪽으로 90도회전) )
	 *  0 1 2 1 2 3 2 1 (우 상 좌 상  <-> 좌 하 좌 상 (왼쪽으로 90도회전) )
	 *  0 1 2 1 2 3 2 1 2 3 0 3 2 3 2 1
	 *  
	 *  크기가 100×100인 격자 위에 드래곤 커브가 N개 있다
	 * 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수
	 * 
	 * 세대는 0세대 부터 10세대 까지 있다.
	 */
	
	
/*	델타 방향배열을 순서대로 90도씩 왼쪽으로 회전하도록 설정했습니다.
	드래곤커브의 방향이 g를 기준으로 0세대 1세대 2세대.. 증가할수록
	거꾸로 뒤집어서 왼쪽으로 90도씩 회전합니다.

	이를 이용해 드래곤커브의 방향을 미리 list에 저장했습니다.
	그리고 list에 저장된 방향대로 boolean형 2차원 배열에 드래곤 커브를 그렸습니다.
	그리고 크기 1인 정사각형의 꼭짓점을 검사해서 답을 구했습니다.

	처음에 y가 세로이고 x가 가로라는것을 제대로 안읽어서 막혔었습니다.


	메모리 : 11944	시간 : 100*/
	
	static int dy[] = {0,-1,0,1}; //우 상 좌 하 => 인덱스가 증가할수록 왼쪽으로 90도씩 회전하는 방향
	static int dx[] = {1,0,-1,0};
	
	static int N, ans;
	static boolean arr[][];
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>(); //dir저장리스트
		arr = new boolean[101][101];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			list.clear();
			list.add(d); //0세대 방향이 d

			for (int a = 0; a < g; a++) { //0세대부터 g세대까지
				int size = list.size(); 
				for (int j = size-1; j>=0; j--) { //뒤집어서
					int dir = (list.get(j)+1) % 4; // 왼쪽으로 90도씩 회전한 dir저장해주기
					list.add(dir);
				}
			}
			
			//System.out.println(list.toString());
			
			makeCurve(y,x,list.size());
		}
		
		getSquareNum();
		
		System.out.println(ans);
		
	
	}
	
	private static void getSquareNum() {
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				if(arr[i][j]) { //드래곤커브의 점이 나오면
					int cnt = 0;
					for (int a = i; a < i+2; a++) { //크기가 1인 정사각형의 네꼭짓점 검사
						for (int b = j; b < j+2; b++) {
							if(b<101&&a<101) {
								if(arr[a][b]) {
									cnt++;
								}
							}
						}
					}
					if(cnt==4) { //꼭짓점이 4개다있으면 => 정사각형
						ans++; //ans++
					}
					
				}
			}
		}
	}

	private static void makeCurve(int y, int x, int size) {

		arr[y][x] = true; 
		
		int ny = y;
		int nx = x;
		for (int d : list) {
			 //list에 넣어놓은 dir방향대로 한칸씩 이동하면서 드래곤커브를 boolean배열에 그린다.
			ny += dy[d];
			nx += dx[d];
			
			if(ny>=0&&nx>=0&&ny<101&&nx<101) {
				if(!arr[ny][nx]) arr[ny][nx]=true;
			}
		}
		
		list.clear();
	}

	
}
