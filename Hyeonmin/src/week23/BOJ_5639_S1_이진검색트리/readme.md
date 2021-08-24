## BOJ 5639 S1 이진검색트리
- 트리
- silver1

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/5639

이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.

- 노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
- 노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
- 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.

전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다. 후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다. 예를 들어, 위의 이진 검색 트리의 전위 순회 결과는 50 30 24 5 28 45 98 52 60 이고, 후위 순회 결과는 5 28 24 45 30 60 52 98 50 이다.

이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.


#### 입력
트리를 전위 순회한 결과가 주어진다. 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 같은 키를 가지는 노드는 없다.
<br><br>

#### 출력
입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다.
<br><br>

###  💡 풀이

변수
`static Node root` : 루트 노드

<br>

입력이 전위 순회이므로 입력한 순서대로 새로운 이진 트리를 만들면 원래의 트리 구조를 갖는다
따라서 첫 입력을 `root`에 저장을 하고 그 노드를 기준으로 새로 트리를 만들어 낸다

```
		while(true) {
			String str = br.readLine();
			
			if(str == null || str.equals(""))
				break;
					
			int num = Integer.parseInt(str);
			
			if(root == null) {
				root = new Node(null, null, num);
				continue;
			}
			
			insertNode(num, root);
		}
```

이진 트리를 만들 때 현재 노드보다 작은 노드는 왼쪽 큰 노드는 오른쪽에 저장을 한다


```
	static void insertNode(int num, Node node) {
		if(node.num < num) {
			if(node.right == null) {
				node.right = new Node(null, null, num);
				return;
			}
			insertNode(num, node.right);
		}
		else if(node.num > num) {
			if(node.left == null) {
				node.left = new Node(null,null, num);
				return;
			}
			insertNode(num, node.left);
		}
		
		
	}
```

`printTree`는 `root`노드를 기준으로 후위 순회를 하여 해당 노드의 번호를 출력하는 메소드이다

```
	static void printTree(Node node) {
		if(node.left != null) {
			printTree(node.left);
		}
		
		if(node.right != null) {
			printTree(node.right);
		}
		
		System.out.println(node.num);
	}
```


<br><br>

###  💡 소스코드
```
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5639_S1_이진검색트리 {
	static Node root;
	
	static class Node{
		Node left;
		Node right;
		int num;
		
		public Node(Node left, Node right, int num) {
			super();
			this.left = left;
			this.right = right;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = br.readLine();
			
			if(str == null || str.equals(""))
				break;
					
			int num = Integer.parseInt(str);
			
			if(root == null) {
				root = new Node(null, null, num);
				continue;
			}
			
			insertNode(num, root);
		}
		
		printTree(root);
		
		
	}
	
	static void insertNode(int num, Node node) {
		if(node.num < num) {
			if(node.right == null) {
				node.right = new Node(null, null, num);
				return;
			}
			insertNode(num, node.right);
		}
		else if(node.num > num) {
			if(node.left == null) {
				node.left = new Node(null,null, num);
				return;
			}
			insertNode(num, node.left);
		}
		
		
	}
	
	static void printTree(Node node) {
		if(node.left != null) {
			printTree(node.left);
		}
		
		if(node.right != null) {
			printTree(node.right);
		}
		
		System.out.println(node.num);
	}
}



```


<br>


####결과

| 메모리  | 시간 |
|----|----|
|17520 KB |	692 ms|