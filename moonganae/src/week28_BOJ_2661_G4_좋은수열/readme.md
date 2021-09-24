## BAEKJOON GOLD4 2661 좋은수열
- 백트레킹
- Gold 4
- https://www.acmicpc.net/problem/2661
<br>

### 문제설명

> 숫자 1, 2, 3으로만 이루어지는 수열이 있다. 임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면, 그 수열을 나쁜 수열이라고 부른다. 그렇지 않은 수열은 좋은 수열이다.


>다음은 나쁜 수열의 예이다.

- 33
- 32121323
- 123123213

> 다음은 좋은 수열의 예이다.

- 2
- 32
- 32123
- 1232123

> 길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램을 작성하라. 예를 들면, 1213121과 2123212는 모두 좋은 수열이지만 그 중에서 작은 수를 나타내는 수열은 1213121이다.

### 입력
- 입력은 숫자 N하나로 이루어진다. N은 1 이상 80 이하이다.

### 출력
- 첫 번째 줄에 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서 가장 작은 수를 나타내는 수열만 출력한다. 
- 수열을 이루는 1, 2, 3들 사이에는 빈칸을 두지 않는다.

### 입출력 예

#### 예제 1
입력
```
7
```
출력
```
1213121
```

### 풀이 및 과정

dfs를 이용한 완전탐색 + 백트래킹을 이용하였습니다.

기존의 수열뒤에 1 -> 2 -> 3 순으로 수를 추가합니다.


```java
/* 1, 2, 3순으로 적용해본다. */
for(int i=1; i<=3; i++) {
	/* 좋은 수열인가? */
	if(isGood(num+i)) {
		/* 좋은 수열이면 넘어간다. */
		dfs(length+1, num + i);
	}
}

```

이때 좋은 수열인지 판단하는 isGood 함수를 사용합니다.
방금 추가한 수열(1 or 2 or 3)을 맨끝을 기준으로 수열의 길이/2 만큼 나쁜수열을 가지는지 확인하는 함수입니다.

```java
static boolean isGood(String num) {
		
	int length = num.length();
	int limit = length / 2;
	
	/* 현재 수열의 길이의 절반까지만 좋은 수열 여부를 확인 */
	for(int i=1; i<=limit; i++) {
		int start = length - i;
		if(num.substring(start-i, length-i).equals(num.substring(start, length))) {
			return false;
		}
	}
	
	return true;
}
```
 


### 소스코드
```java
package week28_BOJ_2661_G4_좋은수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	/* 수열의 길이 */
	static int N;
	/* dfs 종료 플레그 */
	static boolean finish = false;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		
		/* 1로 시작하는 수열 */
		dfs(1, "1");
		

	}
	
	static void dfs(int length, String num) {
		
		if(finish) return;
		
		if(length == N) {
			System.out.println(num);
			finish = true;
			return;
		}
		
		/* 1, 2, 3순으로 적용해본다. */
		for(int i=1; i<=3; i++) {
			/* 좋은 수열인가? */
			if(isGood(num+i)) {
				/* 좋은 수열이면 넘어간다. */
				dfs(length+1, num + i);
			}
		}
	}
	
	static boolean isGood(String num) {
		
		int length = num.length();
		int limit = length / 2;
		
		/* 현재 수열의 길이의 절반까지만 좋은 수열 여부를 확인 */
		for(int i=1; i<=limit; i++) {
			int start = length - i;
			if(num.substring(start-i, length-i).equals(num.substring(start, length))) {
				return false;
			}
		}
		
		return true;
	}
	
}


```

### 결과
```
메모리 : 12148KB	
시간 : 76ms
```
