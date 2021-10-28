## BOJ 9663 N-Queen
- BackTracking
- 🥇 Gold5
- 🔗[N-Queen 문제 바로가기](https://www.acmicpc.net/problem/9663)



## 풀이

N- Queen 이란 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하는 것이다.
공격을 할수 없도록 배치하기 위해서는 동일한 행에 위치하거나 대각선상에 위치하면 안된다.

~~~java

	private static boolean isPossible(int row) {
		for(int i=0; i<row; i++) {
			if(arr[row] == arr[i] || Math.abs(i-row) == Math.abs(arr[i]-arr[row])) {
				return false;
			}
		}
		return true;
	}
~~~

모든 열을 다 검사했으면 cnt++을 해주고 결과를 출력한다.

~~~java
    public void backTracking(int n,int row){
        if(row == n){
            answer++;
            return;
        }
        else{
            for(int i=0; i<n; i++){
                cols[row] = i;
                if(isPossible(row)){
                    backTracking(n,row+1);
                }
            }
        }
    }
~~~



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
	private static void backTracking(int row) {
		if(row==N) {
			cnt++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			arr[row] = i;
			if(isPossible(row)) {
				backTracking(row+1);
			}
		}
		
		
	}
	private static boolean isPossible(int row) {
		for(int i=0; i<row; i++) {
			if(arr[row] == arr[i] || Math.abs(i-row) == Math.abs(arr[i]-arr[row])) {
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