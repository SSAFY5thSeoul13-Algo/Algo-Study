## BOJ 2110 S1 공유기 설치
- 이분탐색
- silver1

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2110

집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.

집에 공유기 C개를 설치하려고 한다. 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.

C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다. 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.

#### 출력
첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.
<br><br>

###  💡 풀이

변수
`int N` : 집의 개수
`int C` : 설치할 공유기의 수
`int[] houses` : 각 집의 좌표


<br>

`houses`에 각 집의 좌표를 저장하고 오름차순으로 정렬한다

```java
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		houses = new int[N];
		
		//각 집의 좌표 저장
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}

		//집의 좌표를 오름차순으로 정렬
		Arrays.sort(houses);
```

집간의 최단 거리를 `left` 최장 거리를 `right`로 시작해서 그 중간간`mid`를 공유기를 설치하는 집간 거리의 기준으로 한다
`mid`보다 거리가 먼 집들에 공유기를 설치하고 그 때 마다 `count`를 증가시킨다
`count`가 `C`이상인 경우 그 때의 집들의 거리중 최소 값을 `result`에 저장하고 `left`를 `mid-1`로, `C`이하인 경우 `right`를 `mid+1`로 변경하여 과정을 반복한다

```java
		//공유기 설치 거리를 이분탐색하기 위한 범위
		int left = 0;
		int right = houses[N - 1] - houses[0];

		while (left <= right) {
			//공유기를 설치할 거리의 기준
			int mid = (left + right) / 2;
			
			//설치한 공유기의 수
			int count = calc(mid);

			if (count >= C) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
```

`calc`메소드는 기준 거리를 받아서 그 거리를 기준으로 공유기를 설치 했을 때 각 집간의 최단 거리를 구하고 설치한 공유기의 수 `count`를 리턴하는 메소드이다

```java
	static int calc(int distance) {
		int now = houses[0];
		int count = 1;
		//공유기 사이의 최단 거리
		int minDistance = Integer.MAX_VALUE;

		for (int i = 1; i < houses.length; i++) {
			int num = houses[i] - now;

			// 공유기 설치 한 경우
			if (num >= distance) {
				count++;
				now = houses[i];
				minDistance = Math.min(minDistance, num);
			}
		}

		if (count >= C) {
			result = Math.max(result, minDistance);
		}

		return count;
	}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_S1_공유기설치 {
	static int N, C, result;
	static int[] houses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		houses = new int[N];
		
		//각 집의 좌표 저장
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}

		//집의 좌표를 오름차순으로 정렬
		Arrays.sort(houses);

		//공유기 설치 거리를 이분탐색하기 위한 범위
		int left = 0;
		int right = houses[N - 1] - houses[0];

		while (left <= right) {
			//공유기를 설치할 거리의 기준
			int mid = (left + right) / 2;
			
			//설치한 공유기의 수
			int count = calc(mid);

			if (count >= C) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(result);
	}

	static int calc(int distance) {
		int now = houses[0];
		int count = 1;
		//공유기 사이의 최단 거리
		int minDistance = Integer.MAX_VALUE;

		for (int i = 1; i < houses.length; i++) {
			int num = houses[i] - now;

			// 공유기 설치 한 경우
			if (num >= distance) {
				count++;
				now = houses[i];
				minDistance = Math.min(minDistance, num);
			}
		}

		if (count >= C) {
			result = Math.max(result, minDistance);
		}

		return count;
	}
}



```


<br>



메모리|시간
--|--
21048 KB|244 ms