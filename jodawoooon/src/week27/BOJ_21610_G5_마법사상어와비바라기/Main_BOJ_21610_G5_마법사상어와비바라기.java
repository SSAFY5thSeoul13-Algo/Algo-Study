package week27.BOJ_21610_G5_마법사상어와비바라기;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 21610 마법사 상어와 비바라기
 * @Author : Daun JO
 * @Date : 2021. 9. 15. 
 *
 */

public class Main_BOJ_21610_G5_마법사상어와비바라기 {
	static int N, M, map[][];
	static ArrayList<int[]> clouds;
	static boolean checked[][];
	static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		clouds = new ArrayList<>();

		for(int i = 1 ; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다
		clouds.add(new int[] {N, 1});
		clouds.add(new int[] {N, 2});
		clouds.add(new int[] {N-1, 1});
		clouds.add(new int[] {N-1, 2});
		
		for(int i = 0 ; i< M; i++) {
			checked = new boolean[N+1][N+1];
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); //방향
			int s = Integer.parseInt(st.nextToken()); //거리

			moveClouds(d, s%N);
			doMagic();
		}

		System.out.println(getSum());
	}
	
	private static void doMagic() {
		for(int r = 1 ; r<=N ; r++) {
			for(int c = 1; c<=N ; c++) {
				if(checked[r][c]) {
					//4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다.
					
					for(int dir = 2 ; dir <= 8 ; dir+=2) { //방향이 2,4,6,8
						int nr = r + dx[dir];
						int nc = c + dy[dir];
						
						//대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
						if(isIn(nr,nc)) if(map[nr][nc]>0) map[r][c]++;
					}
				}
			}
		}
		
		

		//5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 
		//이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
		for(int x = 1 ; x<= N; x++) {
			for(int y = 1; y<= N; y++) {
				if(map[x][y] >= 2 && !checked[x][y]) {
					clouds.add(new int[] {x,y});
					map[x][y] -= 2;
				}
			}
		}

	}


	private static int getSum() {
		int sum = 0;
		for(int i = 1 ; i<= N; i++) {
			for(int j = 1; j<= N; j++) {
				if(map[i][j]==0) continue;
				sum += map[i][j];
			}
		}
		return sum;
	}

	private static void moveClouds(int d, int s) {
		for(int[] cloud : clouds) {			
			//1. 모든 구름이 di 방향으로 si칸 이동한다.
			int r = cloud[0] + dx[d]*s;
			int c = cloud[1] + dy[d]*s; //1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결
				
			if(r<=0) r += N;
			if(c<=0) c += N;
			if(r>N) r -= N;
			if(c>N) c -= N;

			//2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
			map[r][c]++;

			checked[r][c] = true;
		}
		
		//3. 구름이 모두 사라진다.
		clouds.clear();

	}

	private static void printMap() {
		System.out.println("=========map========");
		for(int i = 1 ; i<= N; i++) {
			for(int j = 1; j<= N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	private static boolean isIn(int nr, int nc) {
		if(nr<=0||nc<=0||nr>N||nc>N) return false;
		return true;
	}
}
