## BOJ 9663 N-Queen
- BackTracking
- 🥇 Gold5
- 🔗[N-Queen 문제 바로가기](https://www.acmicpc.net/problem/9663)



## 풀이

N- Queen 이란 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하는 것이다.
공격을 할수 없도록 배치하기 위해서는 동일한 행에 위치하거나 대각선상에 위치하면 안된다.
따라서 각 열에 반복문을 통해 숫자를 놓을 수 있는 모든 경우를 구해본다.
isPossible 메소드를 통해 공격이 불가능하다면 다음 행에 들어갈 수 있는 수를 재귀를 통해 탐색한다.

~~~java

	private static void backTracking(int col) {
		if(col==N) {
			cnt++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			arr[col] = i;
			if(isPossible(col)) {
				backTracking(col+1);
			}
		}
		
		
	}
~~~

isPossible에서는 공격이 가능한 위치가 아니라면 true를 공격이 가능하다면 false를 return한다.
만약 i의 행과 선택한 col의 행이 같다면 같은 행에 위치하는 것이므로 false,
또는 두 퀸의 열의 차이와 행의 차이가 같다면 대각선상에 위치하는 것이므로 false를 return한다.

~~~java
	private static boolean isPossible(int col) {
		for(int i=0; i<col; i++) {
			if(arr[col] == arr[i] || Math.abs(i-col) == Math.abs(arr[i]-arr[col])) {
				return false;
			}
		}
		return true;
	}
~~~

모든 열을 다 검사했으면 cnt++을 해주고 결과를 출력한다.




## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9663_G5_N_Queen {
	static int N,cnt;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for(int i=0; i<N; i++) {
			arr = new int[N];
			arr[0] = i;
			backTracking(1);
		}
		
		System.out.println(cnt);
		

	}
	private static void backTracking(int col) {
		if(col==N) {
			cnt++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			arr[col] = i;
			if(isPossible(col)) {
				backTracking(col+1);
			}
		}
		
		
	}
	private static boolean isPossible(int col) {
		for(int i=0; i<col; i++) {
			if(arr[col] == arr[i] || Math.abs(i-col) == Math.abs(arr[i]-arr[col])) {
				return false;
			}
		}
		return true;
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 12080kb| 5876ms|