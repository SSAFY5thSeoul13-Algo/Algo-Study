## BAEKJOON Gold4 2096 내려가기
- DP
- Gold 4
- https://www.acmicpc.net/problem/2096
<br>

### 문제설명

> N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.
먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다. 이 제약 조건을 그림으로 나타내어 보면 다음과 같다.

```
	|*| | |		| |*| |		| | |*|
	|O|O|X|		|O|O|O|		|X|O|O|
```
> 별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며, 빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다. 숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.




### 입력
- 첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다.
- 다음 N개의 줄에는 숫자가 세 개씩 주어진다.
- 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.

### 출력
첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.

### 입출력 예

#### 예제 1
입력

```
3
1 2 3
4 5 6
4 9 0
```

출력

```
18 6
```

### 풀이 및 과정
DP 알고리즘을 사용하였습니다.

문제 제한조건을 보면 메모리제한이 4MB(C++기준)으로 되어있기 때문에 모든 입력을 저장한뒤 진행하는 것이 아니라, 한줄씩 입력받으면서 진행해야한다는 점을 알 수 있습니다.

먼저 2개의 DP 배열을 생성합니다. (maxDP, minDP)

그리고 각 DP의 인덱스에 따라 고려하는 범위를 다르게 고려합니다.(maxDP는 max를 minDP는 min 값을 구하기)
- 인덱스 0 : 0~1
- 인덱스 1 : 0~2
- 인덱스 2 : 1~2

구한 값에 현재 input을 더해서 최대 또는 최소값을 구해나갑니다.

### 소스코드
```java
package week24.BOJ_2096_G4_내려가기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] maxDP, minDP;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		maxDP = new int[3];
		minDP = new int[3];
		

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<3; i++) {
			int data = Integer.parseInt(st.nextToken());
			maxDP[i] = data;
			minDP[i] = data;
		}
		
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] input = new int[3];
			
			for(int j=0; j<3; j++) {
				input[j] = Integer.parseInt(st.nextToken());
			}

			
			// 최대
			int tmp0 = maxDP[0], tmp2 = maxDP[2];
			maxDP[0] = Math.max(maxDP[0], maxDP[1]) + input[0];
			maxDP[2] = Math.max(maxDP[1], maxDP[2]) + input[2];
			maxDP[1] = Math.max(Math.max(tmp0, maxDP[1]), tmp2) + input[1];

			// 최소
			tmp0 = minDP[0]; tmp2 = minDP[2];
			minDP[0] = Math.min(minDP[0], minDP[1]) + input[0];
			minDP[2] = Math.min(minDP[1], minDP[2]) + input[2];
			minDP[1] = Math.min(Math.min(tmp0, minDP[1]), tmp2) + input[1];
		}
		
		int max = Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]);
		int min = Math.min(Math.min(minDP[0], minDP[1]), minDP[2]);
		System.out.println(max + " " + min);
	}

}

```

### 결과
```
메모리 : 44304KB
시간 : 336ms
```
