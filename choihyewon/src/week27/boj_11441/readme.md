## BOJ 11441 합 구하기 
- Prefix Sum 
- 🥈 Silver3 
- 🔗[합구하기 문제 바로가기](https://www.acmicpc.net/problem/11441)



## 풀이

이 문제는 누적합의 기본개념을 대입해볼 수 있었습니다.
초기에 N까지 주어진 값을 배열에 그대로 넣고 M번만큼 주어지는 i부터 j만큼의 배열의 값을 일일이 더해 결과를 구하였으나 시간초과가 나왔습니다.

따라서 이를 해결하기 위해 

~~~java
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
~~~

배열에 0부터 index까지의 누적합을 넣어주었습니다.

그리고 i,j랑 주어지는 경우 배열의 j번째에서 배열의 i-1번째 값을 빼주어 결과를 구할 수 있었습니다.

## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11441_S3_합_구하기 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		
		for(int a=0; a<M; a++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			sum = arr[j] - arr[i-1];
			System.out.println(sum);
		}

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 62760kb| 1824ms|