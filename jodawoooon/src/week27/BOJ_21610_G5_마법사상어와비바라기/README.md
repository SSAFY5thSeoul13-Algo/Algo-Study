## BOJ 21610 G5 마법사 상어와 비바라기 
- 구현
- gold5



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/21610

마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그 마법을 할 수 있다. 오늘 새로 배운 마법은 비바라기이다. 비바라기를 시전하면 하늘에 비구름을 만들 수 있다. 오늘은 비바라기를 크기가 N×N인 격자에서 연습하려고 한다. 격자의 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지한다. 바구니에 저장할 수 있는 물의 양에는 제한이 없다. (r, c)는 격자의 r행 c열에 있는 바구니를 의미하고, A[r][c]는 (r, c)에 있는 바구니에 저장되어 있는 물의 양을 의미한다.

격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다. 마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다. 즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.

비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 구름은 칸 전체를 차지한다. 이제 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다. 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. 이동을 명령하면 다음이 순서대로 진행된다.

모든 구름이 di 방향으로 si칸 이동한다.
각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
구름이 모두 사라진다.
2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.


<br>

#### ✔ 입력
첫째 줄에 N, M이 주어진다.

둘째 줄부터 N개의 줄에는 N개의 정수가 주어진다. r번째 행의 c번째 정수는 A[r][c]를 의미한다.

다음 M개의 줄에는 이동의 정보 di, si가 순서대로 한 줄에 하나씩 주어진다.
<br>

#### ✔ 출력
첫째 줄에 M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 출력한다.
<br>


<br>

###  💡 풀이

2021년 상반기 삼성 역량테스트
<br>

주어진대로 구현하면 되는 문제
예제 설명을 보고 차근차근 이해하면 된다.

1. d방향으로 s만큼 모든 구름 이동 후 물 증가
2. 물복사버그 마법 : 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
3. 구름 생성 후 물 줄어들기 : 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 
4. 남은 물의 합

<br>

1번 행과 N번 행을 연결했고, 1번 열과 N번 열이 연결되어 있어서 각 구름을 이동할 때의 `%`연산만 신경써주면 되는 문제였다.
 
<br><br>

###  💬 소스코드

```java

package boj.구현;

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

```
<br><br>


###  💯 채점 결과
16592	204