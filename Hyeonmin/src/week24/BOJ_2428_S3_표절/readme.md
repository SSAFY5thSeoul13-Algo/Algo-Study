## BOJ 2428 S3 표절
- 슬라이딩 윈도우
- 이분 탐색
- silver3

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2428

작은 파일의 크기가 큰 파일 크기의 90%보다도 작을 때는, 이러한 쌍은 검사하지 않고 넘어가기로 했다. 따라서, (Fi, Fj) 쌍을 검사해야 하는데, 이때, i≠j이고, size(Fi) ≤ size(Fj)이면서, size(Fi) ≥ 0.9 × size(Fj)인 쌍만 검사하려고 한다.

몇 개의 쌍을 검사해야 하는 지 구하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 제출한 솔루션의 개수 N이 주어진다. 둘째 줄에는 각 솔루션 파일의 크기 size(F1), size(F2), ..., size(FN)이 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ size(Fi) ≤ 100,000,000) 솔루션 파일의 크기는 정수이다.

#### 출력
첫째 줄에 검사해야 하는 파일의 개수를 출력한다.

###  💡 풀이

변수
`int[] sizes` : 각 파일의 사이즈


<br>

각 파일의 크기를 저장한 배열을 정렬한다

```java
		N = Integer.parseInt(br.readLine());
		
		sizes = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//각 파일 사이즈
		for (int i = 0; i < N; i++) {
			sizes[i] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(sizes);
```

이분 탐색을 통해 각 파일을 기준으로 그 파일과 비교 검사를 해야 하는 파일중 가장 마지막 파일의 인덱스를 구한다
구한 인덱스에서 현재 파일 까지의 파일 개수를 제외한 값을 `result`에 더한다

```java
		for (int i = 0; i <N-1; i++) {
			result += search(i) - i;
		}
```

이분 탐색을 통해서 `idx`가 인덱스인 파일의 크기를 기준으로 비교해야 하는 파일의 범위를 구하고 구한 인덱스를 리턴한다

`sizes[middle] *0.9 > sizes[idx]` 인 경우는 범위에 해당하지 않으므로 그 외의 경우인 `else` 부분에서의 인덱스인 `middle`을 `num`에 저장한다

```java
	static int search(int idx) {
		int left = idx+1;
		int right = N-1;
		int middle = 0;
		int num = 0;
		
		//검사할 쌍이 없는 경우
		if(sizes[idx] < 0.9*sizes[idx+1]) {
			return idx;
		}
		
		while(left <= right) {
			middle = (left+right)/2;
			
			//middle이 인덱스인 파일이 검사 대상이 아닌 경우
			if(sizes[middle] *0.9 > sizes[idx]) {
				right = middle -1;
			}
			//middle이 인덱스인 파일이 검사 대상인 경우 그 인덱스 값을 저장
			else {
				num = middle;
				left = middle +1;
			}
		}
		
		return num;
	}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2428_S3_표절 {
	static int N;
	static int[] sizes;
	static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sizes = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//각 파일 사이즈
		for (int i = 0; i < N; i++) {
			sizes[i] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(sizes);
		
		for (int i = 0; i <N-1; i++) {
			result += search(i) - i;
		}
		
		System.out.println(result);
		
	}
	
	//이분 탐색
	static int search(int idx) {
		int left = idx+1;
		int right = N-1;
		int middle = 0;
		int num = 0;
		
		//검사할 쌍이 없는 경우
		if(sizes[idx] < 0.9*sizes[idx+1]) {
			return idx;
		}
		
		while(left <= right) {
			middle = (left+right)/2;
			
			//middle이 인덱스인 파일이 검사 대상이 아닌 경우
			if(sizes[middle] *0.9 > sizes[idx]) {
				right = middle -1;
			}
			//middle이 인덱스인 파일이 검사 대상인 경우 그 인덱스 값을 저장
			else {
				num = middle;
				left = middle +1;
			}
		}
		
		return num;
	}

}




```


<br>



메모리|시간
--|--
34376 KB|420 ms