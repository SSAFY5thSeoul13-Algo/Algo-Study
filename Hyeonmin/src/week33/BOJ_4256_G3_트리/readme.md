## BOJ 4256 G3 트리
- 트리
- 재귀
- 분할 정복

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/4256

이진 트리는 매우 중요한 기본 자료 구조이다. 아래 그림은 루트 노드가 유일한 이진 트리이다. 모든 노드는 최대 2개의 자식 노드를 가질 수 있으며, 왼쪽 자식이 순서가 먼저이다. 노드 n개로 이루어진 이진 트리를 BT라고 하자. BT의 노드는 1부터 n까지 유일한 번호가 매겨져 있다.

BT를 전위 순회, 중위 순회한 결과가 주어진다. 즉, 위의 함수 중 preorder(root node of BT)와 inorder(root node of BT)를 호출해서 만든 리스트가 주어진다. 두 순회한 결과를 가지고 다시 BT를 만들 수 있다. BT의 전위, 중위 순회한 결과가 주어졌을 때, 후위 순회했을 때의 결과를 구하는 프로그램을 작성하시오.

#### 입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 노드의 개수 n이 주어진다. (1 ≤ n ≤ 1,000) BT의 모든 노드에는 1부터 n까지 서로 다른 번호가 매겨져 있다. 다음 줄에는 BT를 전위 순회한 결과, 그 다음 줄에는 중위 순회한 결과가 주어진다. 항상 두 순회 결과로 유일한 이진 트리가 만들어지는 경우만 입력으로 주어진다.

#### 출력
각 테스트 케이스마다 후위 순회한 결과를 출력 한다.

###  💡 풀이

`inOrder`을 기준으로 분할 정복을 한다

분할을 하는 기준은 `inOrder`를 호출할 때 마다의 횟수를 `preOrder` 의 인덱스로 하여 `preOrder[idx]`의 값이 있는 `inOrder`의 위치를 기준으로 한다.

<br><br>

###  💡 소스코드
```java
package week33.BOJ_4256_G3_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4256_G3_트리 {
	static int T, N, idx;
	static int[] preOrder, inOrder;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			idx = 1;
			
			preOrder = new int[N];
			inOrder = new int[N];
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				preOrder[i] = Integer.parseInt(st1.nextToken());
				inOrder[i] = Integer.parseInt(st2.nextToken());
			}
			
			int startIdx = 0;
			
			for (int i = 0; i < N; i++) {
				if(preOrder[0] == inOrder[i]) {
					startIdx = i;
					break;
				}
			}
			
			divide(startIdx, 0, N-1);
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void divide(int nowIdx, int start, int end) {
		if(start == end) {
			sb.append(inOrder[start]).append(" ");
			return;
		}
		
		if(idx >= N) {	
			sb.append(inOrder[nowIdx]).append(" ");
			return;
		}
		
		int num = preOrder[idx];
		
		int nextIdx = start;
		
		while(nextIdx < nowIdx) {
			if(num == inOrder[nextIdx]) {
				idx++;
				divide(nextIdx, start, nowIdx-1);
				break;
			}
			
			nextIdx++;
		}
		
		if(idx >= N) {	
			sb.append(inOrder[nowIdx]).append(" ");
			return;
		}
		
		nextIdx = end;
		num = preOrder[idx];
		
		while(nextIdx > nowIdx) {
			if(num == inOrder[nextIdx]) {
				idx++;
				divide(nextIdx, nowIdx+1, end);
				break;
			}
			
			nextIdx--;
		}
		
		
		sb.append(inOrder[nowIdx]).append(" ");
		
	}

}






```


<br>



메모리|시간
--|--
42112 KB|508 ms