## BOJ - 2110 공유기 설치 

- BinarySearch
- 🥈 Silver1

🔗[도둑질 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42897)


## 풀이

이분탐색을 이용하여 문제를 풀었습니다. 각 집의 좌표는 arr배열에 순서대로 넣어 정렬을 해주었고 left에는 거리는 적어도 1이상 차이나므로 1, right는 가장 멀리 떨어진 거리는 가장 멀리있는 집의 좌표와 가장 가까운 집의 좌표를 뺀 값이므로 arr[N-1] - arr[0]으로 설정해두었습니다.

그리고 mid를 기준으로 공유기가 C개 이상 설치가 가능하다면 left = mid + 1, 아니라면 right = mid - 1 으로 공유기를 설치할 수 있는 거리의 최대값을 줄여 답을 구하였습니다.



## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_S1_공유기_설치 {
	static int N,C;
	static int[] arr;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int left = 1;
		int right = arr[N-1] - arr[0];
		int result = 0;
		
		while(left<=right) {
			int mid = (left+right) / 2;
			int prev = arr[0];
			int cnt = 1;
			for(int i=1; i<N; i++) {
				if(arr[i]-prev>=mid) {
					cnt++;
					prev = arr[i];
				}
			}
			if(cnt>=C) {
				result = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
			
		}
		
		System.out.println(result);

	}

}

~~~

## 결과 

| 메모리 | 시간 |
|----|----|
|21492kb | 	252ms|