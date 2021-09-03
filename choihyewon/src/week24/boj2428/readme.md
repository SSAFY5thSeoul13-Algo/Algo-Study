## BOJ 2428 표절 
- BinarySearch
- 🥈 Silver3 
- 🔗[표절 문제 바로가기](https://www.acmicpc.net/problem/2428)



## 풀이

처음에 풀었던 코드는 시간초과가 나왔습니다. 슬라이딩 윈도우로 어떻게 풀어야할지 모르겠어요,,,

일단 조건에 size(Fi) ≤ size(Fj) 이였기 때문에 정렬을 해주었고, 이분탐색을 통해 size(Fi) ≥ 0.9 × size(Fj)인 경우 left를 mid+1 해주었고, 아닌 경우는 탐색 범위를 줄여주어도 되기 때문에 right값을 mid-1로 바꾸었습니다. 

탐색이 끝난 뒤 right값에서 i값을 빼준값을 result에 누적해주어 답을 구하였습니다. 

## 소스코드

#### 시간초과 나온 코드

~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2428_S3_표절 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		
		int[] files = new int[N];
		

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			files[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(files);
		
		for(int i=0; i<N-1; i++) {
			int num1 = files[i];
			for(int j=i+1; j<N; j++) {
				int num2 = files[j];
				
				if(num1>=num2*0.9) {
					result++;
				}else {
					break;
				}
			}
		}
		
		System.out.println(result);
		
		
		
	}

}
~~~


#### 정답 코드

~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2428_S3_표절 {
	static int N;
	static long result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] files = new int[N];
	
		for(int i=0; i<N; i++) {
			files[i] = Integer.parseInt(st.nextToken());
		}
		
		// 배열을 오름차순으로 정렬 
		Arrays.sort(files);
		
		for(int i=0; i<N; i++) {
			int left = i;
			int right = N-1;
			while(left<=right) {
				int mid = (left+right)/2;
				
				if(files[i]>=files[mid]*0.9) {
					left = mid + 1;
				}else {
					right = mid - 1;
				}
			}
			result += right - i;
		}
		
		
		
		System.out.println(result);
	}
	


}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 33084kb| 424ms|