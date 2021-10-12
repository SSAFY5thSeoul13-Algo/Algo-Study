## BOJ 2630 색종이 만들기 
- Divide and Conquer
- 🥈 Silver3
- 🔗[색종이 만들기 문제 바로가기](https://www.acmicpc.net/problem/2630)



## 풀이

이 문제는 분할정복을 이용하여 풀었습니다. 먼저 0행 0열 부터 시작하여 나누어진 공간에서 isSame 메소드를 통해 모두 같은 색상인지 체크합니다.

먼저 시작점의 색상을 color 변수에 저장하여 다른 색상인 경우 false를 return, 다 같은 색상일 경우 true를 return 한다.

~~~java
	private static boolean isSame(int r, int c, int len) {
		int color = arr[r][c];
		
		for(int i=r; i<r+len; i++) {
			for(int j=c; j<c+len; j++) {
				if(arr[i][j]!=color) {
					return false;
				}
			}
		}
		
		return true;
	}
~~~

만약 true인 경우 하나의 정사각형이 되므로 시작점이 0이라면 하얀색 색종이의 수를 1이라면 파랑색 색종이의 수를 +1 해준다.

~~~java
		if(isSame(r,c,len)) {
			if(arr[r][c]==0) {
				white++;
			}else if(arr[r][c]==1){
				blue++;
			}
			return;
		}
~~~

색상이 같지 않다면 4개의 사분면으로 나누어 divide 메소드를 재귀호출해주었습니다.


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_S3_색종이_만들기 {
	static int N,white,blue;
	static int[][] arr;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0,0,N);
		
		System.out.println(white);
		System.out.println(blue);

	}
	private static void divide(int r, int c, int len) {
		if(isSame(r,c,len)) {
			if(arr[r][c]==0) {
				white++;
			}else if(arr[r][c]==1){
				blue++;
			}
			return;
		}
		
		int division = len/2;
		
		divide(r,c,division);
		divide(r,c+division,division);
		divide(r+division,c,division);
		divide(r+division,c+division,division);
		
		
	}
	
	private static boolean isSame(int r, int c, int len) {
		int color = arr[r][c];
		
		for(int i=r; i<r+len; i++) {
			for(int j=c; j<c+len; j++) {
				if(arr[i][j]!=color) {
					return false;
				}
			}
		}
		
		return true;
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 13792kb| 120ms|