## BAEKJOON SILVER3 2428 표절
- 슬라이딩 윈도우, 정렬
- Silver 3
- https://www.acmicpc.net/problem/2428
<br>

### 문제설명

> 세계적인 석유 재벌 "규현 조 압둘 티크리티 안드레스 후세인 리오넬 솔레르 살라 마리우 두스 산투스 펠리스 빈 자이드 술탄 친나왓 뱅거 7세"는 1등 상품으로 페라리를 걸고 프로그래밍 대회를 개최했다. 이 대회의 참석자는 총 N명이고 각각 솔루션 파일 1개를 제출했다. 이 솔루션 파일을 F1, F2, ..., Fn이라고 한다.
채점 결과를 발표하기 전에, 남의 것을 배낀 사람이 있는지 찾아내려고 한다. 이 대회의 주최측은 두 파일을 비교해서 너무 비슷한지 아닌지 판별하는 프로그램이 있다.
하지만, 제출한 파일의 개수가 너무 많아서, 모든 쌍을 검사한다면, 2012년 지구가 멸망할 때 까지도 검사를 해야할 판이다. 따라서, 파일 크기가 너무 다른 경우에는 그러한 쌍을 검사하지 않고 넘어가기로 했다.
좀더 정확하게 하기 위해서, 대회의 심판들은 두 파일이 있을 때, 작은 파일의 크기가 큰 파일 크기의 90%보다도 작을 때는, 이러한 쌍은 검사하지 않고 넘어가기로 했다. 따라서, (Fi, Fj) 쌍을 검사해야 하는데, 이때, i≠j이고, size(Fi) ≤ size(Fj)이면서, size(Fi) ≥ 0.9 × size(Fj)인 쌍만 검사하려고 한다.
몇 개의 쌍을 검사해야 하는 지 구하는 프로그램을 작성하시오.

### 입력
- 첫째 줄에 제출한 솔루션의 개수 N이 주어진다.
- 둘째 줄에는 각 솔루션 파일의 크기 size(F1), size(F2), ..., size(FN)이 주어진다.
- (1 ≤ N ≤ 100,000, 1 ≤ size(Fi) ≤ 100,000,000) 솔루션 파일의 크기는 정수이다.

### 출력
첫째 줄에 검사해야 하는 파일의 개수를 출력한다.

### 입출력 예

#### 예제 1
입력
```
2
2 1
```
출력
```
0
```

#### 예제 2
입력
```
5
1 1 1 1 1
```
출력
```
10
```

### 풀이 및 과정
슬라이딩 윈도우 알고리즘을 사용하였습니다.

먼저 솔루션 파일의 크기를 입력받고 오름차순으로 정렬합니다.

```java
A = new int[N];
/* 1. 솔루션 파일 크기 입력 */
for(int i=0; i<N; i++) {
	A[i] = Integer.parseInt(st.nextToken());
}

/* 2. 파일크기로 정렬 */
Arrays.sort(A);

```


슬라이딩 윈도우에서 시작인덱스와 현재인덱스(끝인덱스)를 사용하여 검사가 필요한 범위를 찾고 해당 범위의 수만큼 ans에 더해줍니다.

```java
for(int i=1; i<N; i++) {
			
	/* 검사대상인지 체킹 */
	while(isCheck(si, i) == false) {
		// 현재 인덱스와 같다면 모든 범위를 탐색함
		if(si==i) break;
		// 아니라면 검색범위 좁히기 -> 슬라이딩 윈도우 시작인덱스 증가
		si++;
	}
	/* 검색해야할 파일 쌍수 더하기 */
	ans += (i - si);
}

```

### 막힌점
1. 파일의 크기는 int의 max보다 작은건 확인하였지만, 파일의 쌍의 수의 최대가 int의 범위를 초과할 수 있다는것을 간과했습니다.<br>
ex) N=10,000이고, 모든 파일의 크기가 1일경우 ans = (100000)*(100000-2) = 9999800000 > Integer.MAX

### 소스코드
```java
package week24.BOJ_2428_S3_표절;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/* 솔루션의 수, 슬라이딩 윈도우 시작인덱스 */
	static int N, si;
	/* 정답수 */
	static long ans;
	/* 파일크기 배열 */
	static int[] A;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = new int[N];
		/* 1. 솔루션 파일 크기 입력 */
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 2. 파일크기로 정렬 */
		Arrays.sort(A);
		
		for(int i=1; i<N; i++) {
			
			/* 검사대상인지 체킹 */
			while(isCheck(si, i) == false) {
				// 현재 인덱스와 같다면 모든 범위를 탐색함
				if(si==i) break;
				// 아니라면 검색범위 좁히기 -> 슬라이딩 윈도우 시작인덱스 증가
				si++;
			}
			/* 검색해야할 파일 쌍수 더하기 */
			ans += (i - si);
		}
		
		System.out.println(ans);
	}
	
	/**
	 * 
	 * @param start 슬라이딩 윈도우 시작인덱스
	 * @param cur 현재 검사할 파일 인덱스
	 * @return 검사여부
	 */
	static boolean isCheck(int start, int cur) {
		if(A[cur] * 0.9 <= A[start]) return true;
		
		return false;
	}

}


```

### 결과
```
메모리 : 33728KB	
시간 : 384ms
```
