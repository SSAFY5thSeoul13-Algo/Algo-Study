## BOJ 2805 나무자르기 
> BinarySearch
> 🥈 Silver 3
> 🔗[나무자르기 문제 바로가기](https://www.acmicpc.net/problem/2805)

## 설명 

저는 이분탐색을 통해 풀었습니다. left는 0, right는 나무의 높이 최대값인 1000000000 으로 설정해두었습니다.

그리고 각각 나무의 길이를 배열에 저장한 뒤 이분탐색을 위해 정렬을 해주었습니다.

~~~java
		Arrays.sort(tree);
~~~

그리고 left가 rigth보다 작거나 같을 동안 mid를 구하여 베어갈 수 있는 나무의 최대길이를 구하였습니다.
여기서 mid는 절단기의 높이로 각 배열의 나무의 길이에서 mid를 뺀 값을 slice에 누적합 시켜줍니다. 

만일 slice가 M보다 작거나 같다면 절단기의 높이를 더 늘려도 되기 때문에 left를 mid + 1값으로 바꾸어 줍니다.
만약 slice가 M보다 크다면 절단기의 높이를 더 줄여야 하기 때문에 right를 mid - 1으로 바꾸어주었습니다.

~~~java
			if(M<=slice) {
				left = mid + 1;
			}else if(M>slice){
				right = mid - 1;
			}
~~~

그리고 절단기를 설정할 수 있는 최대의 높이를 구해야 하므로 right에 저장된 값을 출력해줍니다.

## 막힌점 

나무 길이의 총합이 int 범위를 벗어나 처음에 틀렸다고 정답이 나와 long 타입으로 바꾸어주었습니다.

## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M,result;
	static int[] tree;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		
		int left = 0;
		int right = 1000000000;
		
		while(left<=right) {
			int mid = (left+right)/2;
			long slice = 0;
			for(int i=0; i<N; i++) {
				if(tree[i] - mid > 0 ) {
					slice += tree[i] - mid;
				}
			}
			
			if(M<=slice) {
				left = mid + 1;
			}else if(M>slice){
				right = mid - 1;
			}
		}
		
		System.out.println(right);
		

	}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
| 168544KB | 900ms|