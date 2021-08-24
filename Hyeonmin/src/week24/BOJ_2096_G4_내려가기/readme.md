## BOJ 2096 G4 내려가기
- 슬라이딩 윈도우
- DP
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2096

N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.

먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다.

숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.


#### 입력
첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다. 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.

#### 출력
첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.

###  💡 풀이

변수
`int[][] map` : 입력된 숫자를 저장할 배열
`int[][] maxDp` : 지나오면서 더 큰 값을 가진 경우를 저장하는 배열
`int[][] minDp` : 지나오면서 더 작은 값을 가진 경우를 저장하는 배열


<br>

최상단에서 내려오면서 더 큰 경우를 저장하는 배열과 더 작은 경우를 저장하는 배열 2개의 배열을 사용해 값을 구하였다

각 위치에 숫자를 저장하고 `minDp`의 값은 최대값으로 초기화한다

```java
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minDp[i][j] = Integer.MAX_VALUE;
			}
		}
```

최상단의 dp배열에는 `map`과 같은 숫자를 저장한다

```java
		for (int i = 0; i < 3; i++) {
			maxDp[0][i] = minDp[0][i] = map[0][i];
		}
```

최상단부터 밑으로 내려가면서 각 위치에 올 수 있는 최대값, 최소값을 저장한다

```java
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < 3; j++) {
				//아래에 있는 3방향에 대해서
				for (int d = 0; d < 3; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					
					//범위 밖이면 스킵
					if(!canGo(ny, nx))	continue;
					
					//더 큰 수를 ny, nx위치에 저장
					maxDp[ny][nx] = Math.max(maxDp[ny][nx], maxDp[i][j] + map[ny][nx]);
					//더 작은 수를 ny, nx위치에 저장
					minDp[ny][nx] = Math.min(minDp[ny][nx], minDp[i][j] + map[ny][nx]);
				}
			}
		}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096_G4_내려가기 {
	static int[][] map, maxDp, minDp;
	static int[] dx = {-1,0,1};
	static int[] dy = {1,1,1};
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][3];
		maxDp = new int[N][3];
		minDp = new int[N][3];
		
		//숫자 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minDp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		//첫 번 째 숫자 dp배열에 입력
		for (int i = 0; i < 3; i++) {
			maxDp[0][i] = minDp[0][i] = map[0][i];
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < 3; j++) {
				//아래에 있는 3방향에 대해서
				for (int d = 0; d < 3; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					
					//범위 밖이면 스킵
					if(!canGo(ny, nx))	continue;
					
					//더 큰 수를 ny, nx위치에 저장
					maxDp[ny][nx] = Math.max(maxDp[ny][nx], maxDp[i][j] + map[ny][nx]);
					//더 작은 수를 ny, nx위치에 저장
					minDp[ny][nx] = Math.min(minDp[ny][nx], minDp[i][j] + map[ny][nx]);
				}
			}
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < 3; i++) {
			max = Math.max(max, maxDp[N-1][i]);
			min = Math.min(min, minDp[N-1][i]);
		}
		
		System.out.println(max+" "+min);
	}
	
	static boolean canGo(int y, int x) {
		if(y<0 || x<0 || x>=3 || y>=N)
			return false;
		
		return true;
	}
}




```


<br>



메모리|시간
--|--
56540 KB|420 ms