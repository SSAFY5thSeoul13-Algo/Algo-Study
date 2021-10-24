## BOJ 17140 G4 이차원 배열과 연산
- 정렬
- 시뮬레이션
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/17140

크기가 3×3인 배열 A가 있다. 배열의 인덱스는 1부터 시작한다. 1초가 지날때마다 배열에 연산이 적용된다.

R 연산: 배열 A의 모든 행에 대해서 정렬을 수행한다. 행의 개수 ≥ 열의 개수인 경우에 적용된다.
C 연산: 배열 A의 모든 열에 대해서 정렬을 수행한다. 행의 개수 < 열의 개수인 경우에 적용된다.
한 행 또는 열에 있는 수를 정렬하려면, 각각의 수가 몇 번 나왔는지 알아야 한다. 그 다음, 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬한다. 그 다음에는 배열 A에 정렬된 결과를 다시 넣어야 한다. 정렬된 결과를 배열에 넣을 때는, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저이다.

예를 들어, [3, 1, 1]에는 3이 1번, 1가 2번 등장한다. 따라서, 정렬된 결과는 [3, 1, 1, 2]가 된다. 다시 이 배열에는 3이 1번, 1이 2번, 2가 1번 등장한다. 다시 정렬하면 [2, 1, 3, 1, 1, 2]가 된다.

정렬된 결과를 배열에 다시 넣으면 행 또는 열의 크기가 달라질 수 있다. R 연산이 적용된 경우에는 가장 큰 행을 기준으로 모든 행의 크기가 변하고, C 연산이 적용된 경우에는 가장 큰 열을 기준으로 모든 열의 크기가 변한다. 행 또는 열의 크기가 커진 곳에는 0이 채워진다. 수를 정렬할 때 0은 무시해야 한다. 예를 들어, [3, 2, 0, 0]을 정렬한 결과는 [3, 2]를 정렬한 결과와 같다.

행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.

배열 A에 들어있는 수와 r, c, k가 주어졌을 때, A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간을 구해보자.


#### 입력
첫째 줄에 r, c, k가 주어진다. (1 ≤ r, c, k ≤ 100)

둘째 줄부터 3개의 줄에 배열 A에 들어있는 수가 주어진다. 배열 A에 들어있는 수는 100보다 작거나 같은 자연수이다.

#### 출력
A[r][c]에 들어있는 값이 k가 되기 위한 연산의 최소 시간을 출력한다. 100초가 지나도 A[r][c] = k가 되지 않으면 -1을 출력한다.

###  💡 풀이

`Node`클래스를 각 숫자와 그 숫자의 카운트를 저장하고 카운트로 정렬이 가능하게 만든다

R, C 연산에 맞춰서 각 행 또는 열을 순회하면서 `arr`에 각 숫자별 카운틀를 구한 후 카운트가 0이 아닌 수를 Node로 만들어서 `list`에 저장한다

list를 정렬한 후 각 행 또는 열에 순서대로 값을 갱신한다

매 연산 시작전에 `map[N][M]` 값을 확인한고 `time`이 100을 넘는 경우 종료한다

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17140_G4_이차원배열과연산 {
	static int N, M, K, time, maxYIdx = 2, maxXIdx = 2;
	static int[][] map = new int[200][200];
	
	static class Node implements Comparable<Node>{
		int idx;
		int num;
		
		public Node(int idx, int num) {
			super();
			this.idx = idx;
			this.num = num;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.num, o.num);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken())-1;
		M = Integer.parseInt(st.nextToken())-1;
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			if(map[N][M] == K)	break;
			
			if(time > 100) break;
			
			if(maxXIdx <= maxYIdx) {
				rowCalc();
			}
			else {
				colCalc();
			}
			
			time++;
		}
		
		System.out.println(time > 100 ? -1 : time);
	}
	
	static void rowCalc() {
		int maxLength = 0;
		
		for (int y = 0; y <= maxYIdx; y++) {
			int[] arr = new int[200];
			List<Node> list = new ArrayList<>();
			
			for (int x = 0; x <= maxXIdx; x++) {
				int num = map[y][x];
				if(num == 0)	continue;
				
				map[y][x] = 0;
				
				arr[num]++;
			}
			
			for (int idx = 1; idx < 200; idx++) {
				if(arr[idx] == 0)	continue;
				
				list.add(new Node(idx, arr[idx]));
			}
			
			Collections.sort(list);
			int size = (list.size()-1)*2 + 1 ;
			
			for (int idx = 0; idx < list.size(); idx++) {
				map[y][idx*2] = list.get(idx).idx;
				map[y][idx*2+1] = list.get(idx).num;
			}
			
			maxLength = Math.max(maxLength, size);
		}
		
		maxXIdx = maxLength;
	}
	
	static void colCalc() {
		int maxLength = 0;
		
		for (int x = 0; x <= maxXIdx; x++) {
			int[] arr = new int[200];
			List<Node> list = new ArrayList<>();
			for (int y = 0; y <= maxYIdx; y++) {
				int num = map[y][x];
				if(num == 0)	continue;
				
				map[y][x] = 0;
				
				arr[num]++;
			}
			
			for (int idx = 1; idx < 200; idx++) {
				if(arr[idx] == 0)	continue;
				
				list.add(new Node(idx, arr[idx]));
			}
			
			Collections.sort(list);
			int size = (list.size()-1)*2+1;
			
			for (int idx = 0; idx < list.size(); idx++) {
				map[idx*2][x] = list.get(idx).idx;
				map[idx*2+1][x] = list.get(idx).num;
			}
			
			maxLength = Math.max(maxLength, size);
		}
		maxYIdx = maxLength;
	}
}





```


<br>



메모리|시간
--|--
17864 KB|124 ms