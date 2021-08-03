## BOJ - 1654 랜선자르기 
- BinarySearch
- 🥈 Silver3

🔗[랜선자르기 문제 바로가기](https://www.acmicpc.net/problem/1654)


## 풀이

이 문제는 나무자르기 문제와 거의 유사하게 풀었습니다. 
랜선의 최소길이를 left, 최대 길이를 right로 설정해둔 다음, 이분탐색을 진행하였습니다.
임의로 구한 mid를 이용하여 각 배열에 저장된 랜선의 길이에서 mid를 나눈 값을 누적으로 합한 값 cnt가 N보다 크거나 같으면 랜선의 길이는 더 큰 값이 될 수 있으므로 left를 mid+1의 값으로 저장해주고, cnt가 N보다 작으면 랜선의 길이는 mid보다 작은 값이 되어야 하므로 right을 mid-1값으로 저장해주었습니다. 그렇게 이분탐색을 마치면 구할 수 있는 랜선의 최대 길이를 구해야 하므로 right을 출력해주었습니다.


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1654_S3_랜선자르기 {
	static int K,N;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		
		long left = 1;
		long right = arr[K-1];
		
		while(left<=right) {
			long mid = (left + right)/2;
			// 자른 랜선의 개수 
			int cnt = 0;
			
			for(int i=0; i<K; i++) {
				cnt += arr[i]/mid;
			}
			
			if(cnt>=N) {
				left = mid + 1;
			}else if(cnt<N){
				right = mid - 1;
			}
			
		}
		
		System.out.println(right);

	}

}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|15236kb|140ms|