package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16197_G4_두동전 {
	static int N,M;
	//상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	//이전에 진행했던 위치인지 확인하는 배열
	static boolean[][][][] visited;
	static char[][] map;
	static Queue<int[]> q = new LinkedList<int[]>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N =Integer.parseInt(st.nextToken());
		M =Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		//2개의 동전위치 방문여부 저장할 배열
		visited = new boolean[N][M][N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'o') {
					q.offer(new int[] {i,j});
				}
			}
		}
		
		//첫 시작 카운트
		int count = 1;
		
		while(!q.isEmpty()) {
			//큐에 동전 1,2가 순서대로 저장되었어서 크기의 절반만큼만 반복문을 실행
			int size = q.size()/2;
			
			//카운트가 10이 넘어가면 중단
			if(count > 10) {
				break;
			}
			
			//현재 카운트에 해당하는 동전들의 이동 실행
			for (int i = 0; i < size; i++) {
				int[] btn1 = q.poll();
				int[] btn2 = q.poll();
				
				//이미 두 동전이 온적이 있는 위치이면 다음 것을 진행
				if(visited[btn1[0]][btn1[1]][btn2[0]][btn2[1]])
					continue;
				
				//방문 체크
				visited[btn1[0]][btn1[1]][btn2[0]][btn2[1]] = true;
				
				//두 동전이 겹쳐지면 동시에 떨어지기 때문에 고려 대상에서 제외
				if(btn1[0] == btn2[0] && btn1[1] == btn2[1]) {
					continue;
				}
				
				//4방향 진행
				for (int d = 0; d < 4; d++) {
					//두 동전의 이동후의 좌표
					int[] np1 = {btn1[0]+dy[d], btn1[1]+dx[d]};
					int[] np2 = {btn2[0]+dy[d], btn2[1]+dx[d]};
					
					//두 동전이 밖으로 나갔는지 여부 체크할 변수
					boolean out1 = false;
					boolean out2 = false;
					
					//1번 동전이 보드 위에 있는 경우
					if(canGo(np1[0], np1[1])) {
						//이동할 위치가 벽이면 이전 좌표를 큐에 저장
						if(map[np1[0]][np1[1]] == '#'){
							q.offer(btn1);
						}
						//이동이 가능하면 이동한 좌표를 큐에 저장
						else {
							q.offer(np1);
						}
					}
					//1번 동전이 밖으로 떨어진 경우
					else {
						out1 = true;
					}
					
					//2번 동전이 보드 위에 있는 경우
					if(canGo(np2[0], np2[1])) {
						//이동할 위치가 벽이면 이전 좌표를 큐에 저장
						if(map[np2[0]][np2[1]] == '#'){
							q.offer(btn2);
						}
						//이동이 가능하면 이동한 좌표를 큐에 저장
						else {
							q.offer(np2);
						}
					}
					//2번 동전이 밖으로 떨어진 경우
					else {
						out2 = true;
					}
					
					//한 개의 동전은 떨어지고 다른 한 개는 떨어지지 않은 경우 현재 카운트 출력
					if(out1 != out2) {
						System.out.println(count);
						return;
					}
					
				}
			}
			
			//카운트 증가
			count++;
		}
		
		//카운트가 10이 넘어가면 -1 출력
		System.out.println(-1);
	}
	
	//동전이 보드 위에 있는 확인하는 메소드
	static boolean canGo(int ny, int nx) {
		if(ny < 0 || nx < 0 || ny >= N || nx >= M) {
			return false;
		}
		
		return true;
	}
}