## BOJ 10986 나머지 합 
- Prefix Sum, Math 
- 🥇 Gold3
- 🔗[나머지 합 문제 바로가기](https://www.acmicpc.net/problem/10986)



## 풀이

처음에는 각 index까지의 누적합을 배열에 넣어준 뒤 이중for문으로 문제를 풀었으나 시간초과가 나왔습니다.

여기서 M으로 나누어 떨어지는 구간을 구하기 위해, (arr[j]-arr[i-1])%M = 0 인 구간을 찾아야 합니다.

(arr[j]-arr[i-1])%M = 0 는 분배법칙을 통해 arr[j]%M-arr[i-1]%M = 0으로 나타낼 수 있고 이는 곧 arr[j]%M = arr[i-1]%M 을 나타냅니다. 따라서 누적합 배열중 M으로 나누었을 때 나머지가 같은 구간을 찾으면 됩니다.

~~~Java
		for(int i=0; i<N; i++) {
			sum += Integer.parseInt(st.nextToken())%M;
			arr[sum%M]++;
		}
~~~

배열에는 누적합의 나머지를 계산하여 count 해줍니다.

~~~java
		for(int i=0; i<M; i++) {
			int n = arr[i];
			result += (long) n*(n-1)/2;
		}
~~~

모든 누적합에 대한 나머지를 저장하였으면 나머지가 같은 구간끼리의 경우의 수를 구해줍니다.

이전코드보다 현재코드를 통해 시간 복잡도를 O(N)으로 줄일 수 있었습니다. 

## 소스코드

#### 시간초과 난 코드 
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10986_G3_나머지_합 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=i; j<=N; j++) {
				int sum = arr[j] - arr[i-1];
				if(sum%3==0) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);

	}

}
~~~

#### 정답코드 

~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10986_G3_나머지_합 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 나머지를 저장하는 배열 
		int[] arr = new int[M];
		
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sum += Integer.parseInt(st.nextToken())%M;
			arr[sum%M]++;
		}
		
		long result = arr[0];
		for(int i=0; i<M; i++) {
			int n = arr[i];
			result += (long) n*(n-1)/2;
		}
		
		System.out.println(result);
		

	}

}
~~~

## 결과 

| 메모리  | 시간 |
|----|----|
|160468kb| 440ms|