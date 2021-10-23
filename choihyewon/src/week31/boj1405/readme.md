## BOJ 1405 미친로봇 
- BackTracking 
- 🥇 Gold5
- 🔗[미친로봇 문제 바로가기](https://www.acmicpc.net/problem/1405)



## 풀이

각 방향으로 이동할 확률 -> 처음에 두자릿수로 입력받으므로 /100 또는 * 0.01 을 해주어야한다.

~~~java
		for(int i=0; i<4; i++) {
			direction[i] = Double.parseDouble(st.nextToken()) / 100;
		}
~~~


N이 14보다 크면 안되므로 map 크기를 30으로 잡아주었고 시작점은 15,15로 고정시켜주었다.

~~~java
		visited[15][15] = true;		
		backTracking(15,15,1.0,0);
~~~

그리고 백트래킹을 통해 N번의 count가 완료되면(기저조건) 확률을 누적으로 더해주었다. 
이 때 확률을 구하는 방법은 예를 들어 현재 확률이 1, 동쪽으로 이동할 확률이 0.25라면 둘을 곱해주면 된다.

~~~java
		if(cnt == N) {
			answer += result;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr<0 || nc<0 || nr>=30 || nc>=30)	continue;

			if(!visited[nr][nc] && direction[i]>0) {
				visited[nr][nc] = true;
				backTracking(nr,nc,result*direction[i],cnt+1);
				visited[nr][nc] = false;
			}

		}
		
	}
~~~



## 소스코드
~~~java
import java.io.*;
import java.util.*;

public class BOJ_1405_G5_미친로봇 {
	// 동 서 남 북 
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static double[] direction = new double[4];
	static boolean[][] visited = new boolean[30][30];
	static int N;
	static double answer;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<4; i++) {
			direction[i] = Double.parseDouble(st.nextToken()) / 100;
		}
		
		// 시작점은 (15,15)로 고정 
		visited[15][15] = true;
		
		backTracking(15,15,1.0,0);
		
		System.out.println(answer);
		
		
	}
	private static void backTracking(int r, int c, double result, int cnt) {
		if(cnt == N) {
			answer += result;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr<0 || nc<0 || nr>=30 || nc>=30)	continue;

			if(!visited[nr][nc] && direction[i]>0) {
				visited[nr][nc] = true;
				backTracking(nr,nc,result*direction[i],cnt+1);
				visited[nr][nc] = false;
			}

		}
		
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 12504kb| 136ms|