## BAEKJOON SILVER3 2805 나무자르기
- 이분탐색
- Silver 3
- https://www.acmicpc.net/problem/2805
<br>

### 문제설명

> 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.


### 입력
- 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
- 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)

### 출력
- 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

### 입출력 예

#### 예제 1
입력

```
6
10 20 10 30 20 50
```
출력

```
4
```

### 풀이 및 과정

최장길이수열 LIS(Longest Increasing Subsequence) 문제입니다.

Binary Search 알고리즘을 사용하여 문제를 해결하였습니다.

수열을 하나씩 입력받으면서, <br>
LIS 리스트의 마지막 원소보다 작다면, LIS 리스트에 추가한다.

```java
// LIS 최대보다 크면 뒤에 그냥 붙인다.
if(data > LIS.get(LIS.size()-1)) {
	LIS.add(data);
}
```

아니라면, Binary Search를 이용하여 해당 수열의 숫자가 들어갈 인덱스를 찾고, 업데이트 한다.

```java
// 아니라면 업데이트할 인덱스를 찾는다.
else {
	int pos = bSearch(0, LIS.size()-1, data);
	LIS.set(pos, data);
}
```

#### 막힌점
- LIS 문제를 전에 풀어보았지만, 까먹어서 문제 접근 개념을 알아가는데 시간이 걸렸습니다.

### 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	static List<Integer> LIS = new ArrayList<>();
 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		LIS.add(Integer.parseInt(st.nextToken()));
		for(int i=1; i<N; i++) {
			int data = Integer.parseInt(st.nextToken());
			
			// LIS 최대보다 크면 뒤에 그냥 붙인다.
			if(data > LIS.get(LIS.size()-1)) {
				LIS.add(data);
			}
			// 아니라면 업데이트할 인덱스를 찾는다.
			else {
				int pos = bSearch(0, LIS.size()-1, data);
				LIS.set(pos, data);
			}
		}
		
		System.out.println(LIS.size());
	}
 	
 	/**
 	 * 
 	 * @param left : 시작 인덱스
 	 * @param right : 끝 인덱스
 	 * @param el : 비교할 엘리먼트
 	 * @return : 넣을 위치
 	 */
 	public static int bSearch(int left, int right, int el) {
 		
 		while(left < right) {
 			int mid = (left + right) / 2;
 			
 			// 엘리먼트가 크면, 범위를 올린다.
 			if(el > LIS.get(mid)) left = mid + 1;
 			else right = mid;
 		}
 		return right;
 	}

}
```

### 결과
```
메모리 : 146244KB
시간 : 656ms
```
