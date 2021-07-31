## BAEKJOON SILVER3 2805 나무자르기
- 이분탐색
- Silver 3
- https://www.acmicpc.net/problem/2805
<br>

### 문제설명

> 상근이는 나무 M미터가 필요하다. 근처에 나무를 구입할 곳이 모두 망해버렸기 때문에, 정부에 벌목 허가를 요청했다. 정부는 상근이네 집 근처의 나무 한 줄에 대한 벌목 허가를 내주었고, 상근이는 새로 구입한 목재절단기를 이용해서 나무를 구할것이다.
목재절단기는 다음과 같이 동작한다. 먼저, 상근이는 절단기에 높이 H를 지정해야 한다. 높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다. 그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다. 따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다. 예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자. 상근이가 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고, 상근이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다. (총 7미터를 집에 들고 간다) 절단기에 설정할 수 있는 높이는 양의 정수 또는 0이다.
상근이는 환경에 매우 관심이 많기 때문에, 나무를 필요한 만큼만 집으로 가져가려고 한다. 이때, 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오.



### 입력
- 첫째 줄에 나무의 수 N과 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
- 둘째 줄에는 나무의 높이가 주어진다. 
- 나무의 높이의 합은 항상 M보다 크거나 같기 때문에, 상근이는 집에 필요한 나무를 항상 가져갈 수 있다. 
- 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.

### 출력
적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.

### 입출력 예

#### 예제 1
```
입력
4 7
20 15 10 17
```
```
출력
15
```

#### 예제 2
```
입력
5 20
4 42 40 26 46
```
```
출력
36
```

### 풀이 및 과정
이분탐색 알고리즘을 사용하였습니다.

<이분탐색 전 설정>

먼저 이분탐색의 범위를 구하기 위해 주어진 나무 최대높이를 찾습니다.
```java
// 가장 높은 나무 찾기
for(int i=0; i<N; i++) {
	h[i] = Integer.parseInt(st.nextToken());
	max = Math.max(max,  h[i]);
}
```

<이분탐색>

현재 절단기의 높이(mid)를 구한뒤, 자른 나무의 길이를 구한다.
```java
int mid = (left+right) / 2;
			
long cnt = 0L;

for(int height : h) {
	if(height - mid > 0) {
		cnt += height - mid;
	}
}
```

필요한 나무의 길이 M과 비교하여 많거나 같을 경우 left 값을 많을 경우 right 값을 갱신하며 구할 수 있는 최대 절단기의 높이를 구한다.
```
// 필요한 나무 M이 충족되면
if(cnt >= M) {
	// 높이를 늘이자
	left = mid +1;
	answer = mid;
}
// 나무가 부족하면
else {
	// 높이를 낮추자
	right = mid - 1;
}
```



#### 막힌점
- 처음에 갱신하는 min, max에 바로 Mid 값을 넣어줬는데 계속해서 무한루프가 발생하였다.
- 랜선의 길이가 INT 이하의 길이라 int로만 계산해주고 있었는데, mid를 구할대 min+max를 사용하기 때문에 long 타입을 사용하여야 한다.
- min의 초기값을 0으로 설정했는데, 1이상 정수의 범위를 가지므로, 1로 설정해야한다.<br>
0으로 설정할 경우 런타임 에러(/ by zero)가 발생한다.

### 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 나무 수, 상근이가 집으로 가져갈 나무 길이
	static int N, M;
	// 나무 높이 배열
	static int[] h;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		h = new int[N];
		
		st = new StringTokenizer(br.readLine());
		int max = -1;
		int answer=-1;
	
		// 가장 높은 나무 찾기
		for(int i=0; i<N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max,  h[i]);
		}
	
		// 절단기의 높이는 0 ~ max까지
		int left = 0;
		int right = max;
		
		while(left <= right) {
			int mid = (left+right) / 2;
			
			long cnt = 0L;
			
			for(int height : h) {
				if(height - mid > 0) {
					cnt += height - mid;
				}
			}
			// 필요한 나무 M이 충족되면
			if(cnt >= M) {
				// 높이를 늘이자
				left = mid +1;
				answer = mid;
			}
			// 나무가 부족하면
			else {
				// 높이를 낮추자
				right = mid - 1;
			}
		}

		
		System.out.println(answer);
	}

}

```

### 결과
```
메모리 : 168176KB
시간 : 536ms
```
