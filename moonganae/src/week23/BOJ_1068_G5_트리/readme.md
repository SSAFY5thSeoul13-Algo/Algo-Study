## BAEKJOON Gold5 1068 트리
- 그래프, DFS
- Gold 3
- https://www.acmicpc.net/problem/1068
<br>

### 문제설명

> 트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.
트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
예를 들어, 다음과 같은 트리가 있다고 하자.

```
		0
	1		2
3		4
```
> 현재 리프 노드의 개수는 3개이다. (2, 3, 4) 이때, 1번을 지우면, 다음과 같이 변한다. x 노드가 트리에서 제거된 노드이다.

```
		0
	x		2
x		x
```
>  이제 리프 노드의 개수는 1개이다.




### 입력
- 첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 
- 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 
- 셋째 줄에는 지울 노드의 번호가 주어진다.

### 출력
첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.

### 입출력 예

#### 예제 1
입력

```
5
-1 0 0 1 1
2
```

출력

```
2
```

#### 예제 2
입력

```
5
-1 0 0 1 1
1
```

출력

```
1
```

#### 예제 3
입력

```
5
-1 0 0 1 1
0
```

출력

```
0
```

#### 예제 4
입력

```
9
-1 0 0 2 2 4 4 6 6
4
```

출력

```
2
```

### 풀이 및 과정

알고리즘 진행순서는 다음과 같습니다.
1. 먼저 트리 자료구조를 만들기
2. 트리 순회

#### 트리 만들기
child 노드의 인덱스를 가진 Node 클래스를 선언하고, 생성합니다.

```java
public static class Node {
	List<Integer> childs = new ArrayList<>();
}


N = Integer.parseInt(br.readLine());
/* N개의 노드 생성 */
nodes = new Node[N];
for(int i=0; i<N; i++) {
	nodes[i] = new Node();
}
```

노드를 연결합니다. 이때, 루트가 아니면서, 삭제 인덱스가 아닐때만 부모노드에 연결합니다.

```java
// 노드 연결
for(int i=0; i<N; i++) {
	int parent = Integer.parseInt(st.nextToken());
	// 루트노드일 경우
	if(parent == -1) root = i;
	// 삭제할 인덱스는 연결하지 않는다.
	else if(i==deleteIdx) continue;
	// 아니라면 노드를 부모노드의 child로 연결
	else {
		nodes[parent].childs.add(i);
	}
}
```

#### 탐색하기
DFS를 사용하여 Leaf 노드를 탐색합니다.

```java
public static void searchLeaf(int idx) {
	// Leaf 노드라면 갯수증가
	if(nodes[idx].childs.size() == 0) {
		answer++;
		return;
	}
	// 자식순회
	for(int child : nodes[idx].childs) {
		searchLeaf(child);
	}
}
```


#### 막힌점
- 삭제노드가 root인 경우를 간과했습니다.
- 노드 연결시 루트확인보다, 삭제인덱스를 먼저 확인해서 root의 default 값이 0으로 설정되었습니다.

### 소스코드
```java
package week23.BOJ_1068_G5_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/* 노드수, 삭제노드 번호, Leaf노드 수, 트리의 루트번호 */
	static int N, deleteIdx, answer, root;
	/* 노드 */
	static Node[] nodes;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		/* N개의 노드 생성 */
		nodes = new Node[N];
		for(int i=0; i<N; i++) {
			nodes[i] = new Node();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		deleteIdx = Integer.parseInt(br.readLine());
		
		// 노드 연결
		for(int i=0; i<N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			// 루트노드일 경우
			if(parent == -1) root = i;
			// 삭제할 인덱스는 연결하지 않는다.
			else if(i==deleteIdx) continue;
			// 아니라면 노드를 부모노드의 child로 연결
			else {
				nodes[parent].childs.add(i);
			}
		}

		// 루트 == 삭제할 인덱스라면 Leaf노드는 0개
		if(deleteIdx != root) searchLeaf(root);
		
		
		System.out.println(answer);
	}
	
	public static class Node {
		List<Integer> childs = new ArrayList<>();
	}

	public static void searchLeaf(int idx) {
		// Leaf 노드라면 갯수증가
		if(nodes[idx].childs.size() == 0) {
			answer++;
			return;
		}
		// 자식순회
		for(int child : nodes[idx].childs) {
			searchLeaf(child);
		}
	}
}


```

### 결과
```
메모리 : 11844KB
시간 : 88ms
```
