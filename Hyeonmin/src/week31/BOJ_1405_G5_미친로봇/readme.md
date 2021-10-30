## BOJ 1405 G5 미친 로봇
- 백트래킹
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1405

통제 할 수 없는 미친 로봇이 평면위에 있다. 그리고 이 로봇은 N번의 행동을 취할 것이다.

각 행동에서 로봇은 4개의 방향 중에 하나를 임의로 선택한다. 그리고 그 방향으로 한 칸 이동한다.

로봇이 같은 곳을 한 번보다 많이 이동하지 않을 때, 로봇의 이동 경로가 단순하다고 한다. (로봇이 시작하는 위치가 처음 방문한 곳이다.) 로봇의 이동 경로가 단순할 확률을 구하는 프로그램을 작성하시오. 예를 들어, EENE와 ENW는 단순하지만, ENWS와 WWWWSNE는 단순하지 않다. (E는 동, W는 서, N은 북, S는 남)


#### 입력
첫째 줄에 N, 동쪽으로 이동할 확률, 서쪽으로 이동할 확률, 남쪽으로 이동할 확률, 북쪽으로 이동할 확률이 주어진다. N은 14보다 작거나 같은 자연수이고,  모든 확률은 100보다 작거나 같은 자연수 또는 0이다. 그리고, 동서남북으로 이동할 확률을 모두 더하면 100이다.

확률의 단위는 %이다.

#### 출력
첫째 줄에 로봇의 이동 경로가 단순할 확률을 출력한다. 절대/상대 오차는 10-9 까지 허용한다.

###  💡 풀이

재귀 호출 방식으로 풀이 했다

4방향 별로 이동할 확률을 다음 호출을 할 때 마다 `per`에 곱해주어서 `step == N`이 될 경우 단순한 경로이므로 `result`에 `per`을 더해주었다 


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405_G5_미친로봇 {
	static int[][]delta = {
			{0,1},
			{0,-1},
			{1,0},
			{-1,0}
	};
	static boolean[][] map;
	static int N;
	static double result;
	static int[] percent = new int[4];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new boolean[N*2+1][N*2+1];
		
		for (int i = 0; i < 4; i++) {
			percent[i] = Integer.parseInt(st.nextToken());
		}
		
		map[N][N] = true;
		recursive(0, N, N, 1);
		
		System.out.println(result);
	}
	
	static void recursive(int step, int y, int x, double per) {
		if(step == N) {
			result += per;
			
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			if(percent[d] == 0)	continue;
			
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];
			
			//단순한 경로인 경우
			if(!map[ny][nx]) {
				map[ny][nx] = true;
				recursive(step + 1, ny, nx, per * (double)percent[d]/100);
				map[ny][nx] = false;
			}
		}
	}

}






```


<br>



메모리|시간
--|--
12292 KB|148 ms