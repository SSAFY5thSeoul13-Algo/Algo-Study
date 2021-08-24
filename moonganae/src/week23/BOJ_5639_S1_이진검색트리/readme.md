## BAEKJOON SILVER1 5639 이진 검색 트리
- 그래프, 그래프순회
- Silver 1
- https://www.acmicpc.net/problem/5639
<br>

### 문제설명

> 이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.

- 노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
- 노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
- 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.

```
					50
					/\
			30				98
			/\				/
		24		45		52
		/\				 \
	5		28				60
```

> 전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다. 후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다. 예를 들어, 위의 이진 검색 트리의 전위 순회 결과는 50 30 24 5 28 45 98 52 60 이고, 후위 순회 결과는 5 28 24 45 30 60 52 98 50 이다.
이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.

### 입력
- 트리를 전위 순회한 결과가 주어진다. 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 
- 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 
- 같은 키를 가지는 노드는 없다.

### 출력
입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다.

### 입출력 예

#### 예제 1
입력
```
50
30
24
5
28
45
98
52
60
```
출력
```
5
28
24
45
30
60
52
98
50
```

### 풀이 및 과정
Node 클래스를 만들어 add, postorder 함수를 구현하여 만들었습니다.


#### 자료구조
Node 클래스
##### 멤버변수
```java
static class Node{
	int num;
	
	Node left;
	Node right;
}
```
- 노드 키값, 2개의 자식노드 left, right를 가지고 있습니다.

##### 메서드
1. add : 숫자를 입력받아, 알맞은 위치에 넣습니다.
```
 void add(int input) {
			
	// 크면 오른쪽
	if(num < input) {
		// 비어있으면 넣기
		if(right == null) right = new Node(input);
		else right.add(input);
	}
	// 작으면 왼쪽
	else {
		// 비어있으면 넣기
		if(left == null) left = new Node(input);
		else left.add(input);
	}
}
```

2. postOrder : left -> right -> root 순으로 순회하는 함수입니다.
```
void postorder() {
			
	// 왼쪽
	if(left != null)
		left.postorder();
	
	// 오른쪽
	if(right != null)
		right.postorder();
	
	// 루트
	System.out.println(this.num);
}
```

### 소스코드
```java
package week23.BOJ_5639_S1_이진검색트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 루트노드 설정 전위순회에서는 무조건 첫번째가 루트임
		Node root = new Node(Integer.parseInt(br.readLine()));
		while(true) {
			String input = br.readLine();
			
			// 입력이 끝나면 종료
			if(input == null) break;
			
			// 내 예시 입력시 -1d을 종료조건으로 둠
//			if(input.equals("-1")) break;
			
			// 트리 추가
			root.add(Integer.parseInt(input));
		}
		
		// 후위순회(왼 -> 오 -> 루트)
		root.postorder();
		
	}

	
	static class Node{
		int num;
		
		Node left;
		Node right;
		
		Node(int num){
			this.num = num;
		}
		
		void add(int input) {
			
			// 크면 오른쪽
			if(num < input) {
				// 비어있으면 넣기
				if(right == null) right = new Node(input);
				else right.add(input);
			}
			// 작으면 왼쪽
			else {
				// 비어있으면 넣기
				if(left == null) left = new Node(input);
				else left.add(input);
			}
		}
		
		void postorder() {
			
			// 왼쪽
			if(left != null)
				left.postorder();
			
			// 오른쪽
			if(right != null)
				right.postorder();
			
			// 루트
			System.out.println(this.num);
		}
	}
}


```

### 결과
```
메모리 : 17700KB	
시간 : 692ms
```
