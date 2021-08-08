## BAEKJOON SILVER1 2110 공유기 설치
- 이분탐색
- Silver 1
- https://www.acmicpc.net/problem/2110
<br>

### 문제설명

> 도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다. 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.


### 입력
- 첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
- 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.

### 출력
첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.

### 입출력 예

#### 예제 1
입력

```
5 3
1
2
8
4
9
```

출력

```
3
```
#### 예제 풀이
- 공유기를 1, 4, 8 또는 1, 4, 9에 설치하면 가장 인접한 두 공유기 사이의 거리는 3이고, 이 거리보다 크게 공유기를 3개 설치할 수 없다.

### 풀이 및 과정
매개변수 탐색알고리즘을 사용하였습니다.

#### 매개변수 탐색 알고리즘
- 최적화 문제를 결정 문제로 풀 수 있는 알고리즘
- 최적 문제 : 어떤 알고리즘의 최적의 솔루션을 찾아내는 것
- 결정 문제 : 답이 이미 결정되어 있다고 보고 문제를 푸는 것

##### 매개 변수 탐색 알고리즘을 사용할 수 있는 경우
    - 결정 문제로 풀 수 있는 문제
    - 어떤 시점까지는 조건을 만족하지만, 그 시점 이후로는 조건을 만족하지 않는 경우에서 최댓값 찾기
    - 어떤 시점ㄲ자ㅣ는 조건을 만족하지 않지만, 그 시점 이후로는 조건을 만족하는 경우에서 최소값

##### 매개 변수 탐색의 동작 과정
    - 문제에서 최종적으로 찾고자하는 최솟값/최댓값을 매개변수로 본다.
    - 결과 배열이 연속인지 확인
    - 최솟밗이면 결정 함수의 결과가 f->t로 바뀌는 부분 찾기
    - 최댓값이면 결정 함수의 결과가 t->f로 바뀌는 부분 찾기

##### 출처
- [이분탐색 & 매개변수탐색](https://velog.io/@guswns3371/%EC%9D%B4%EC%A7%84-%ED%83%90%EC%83%89-%EB%A7%A4%EA%B0%9C-%EB%B3%80%EC%88%98-%ED%83%90%EC%83%89)


#### 풀이과정
- 먼저 집의 수, 설치 공유기 수, 집의 위치좌표를 입력받습니다.
- 집의 위치를 오름차순으로 정렬합니다.

<이분탐색과정>
- 중간간격(mid)를 구하고, 해당 간격으로 공유기를 설치할 수 있는지 결정함수를 통해 확인합니다.
- 설치할 수 있다면 탐색 최소간격(left)를 늘립니다.
- 설치할 수 없다면 탐색 최대간격(right)를 줄입니다.

<결정함수>
- 특정간격(mid)를 기준으로 공유기를 설치할 수 있는지 확인합니다.
- C개 이상 설치할 수 있다면 true
- C개 이상 설치할 수 없다면 false


### 소스코드
```java
package week22.BOJ_2110_S1_공유기설치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 집의 수, 설치할 공유기 수
	static int N, C;
	// 집의 좌표
	static int[] house;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		// 집위치 정렬
		Arrays.sort(house);
		
		// 아무리 작아도 1부터, 최대 집사이의 간격을 탐색 범위로 지정한다.
		System.out.println(binarySearch(1, house[N-1] - house[0]));
	}
	/**
	 * 이분탐색
	 * @param left : 최소 집 사이 간격
	 * @param right : 최대 집 사이 간격
	 * @return 두 공유기 사이의 최대간격
	 */
	public static int binarySearch(int left, int right) {
		
		int ans = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			// 공유기가 설치가능하다면 더 넓은 간격으로 탐색
			if(isPossible(mid)) {
				ans = mid;
				left = mid + 1;
			}
			// 아니라면 더 좁은 간격으로 탐색
			else {
				right = mid - 1;
			}
			
		}
		
		return ans;
	}
	/**
	 * 결정함수
	 * @param mid : 확인하려는 특정 간격
	 * @return 공유기 설치가능 유무
	 */
	public static boolean isPossible(int mid) {
		int start = house[0];
		int cnt = 1;
		for(int i=1; i<N; i++) {
			// 공유기를 설치할 두 집간의 간격
			int diff = house[i] - start;
			
			// 특정간격보다 크다면
			if(diff >= mid) {
				cnt++;				// 설치횟수 증가
				start = house[i];	// 다음 간격을 찾기 위해 시작위치 업데이트
			}
		}
		// C개 이상 설치할 수 있는지 확인
		return cnt >= C;
	}

}

```

### 결과
```
메모리 : 21360KB
시간 : 236ms
```
