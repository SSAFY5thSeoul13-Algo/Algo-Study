## BOJ 5639 이진 검색 트리 
- Tree
- 🥈 Silver1
- 🔗[이진 검색 트리 문제 바로가기](https://www.acmicpc.net/problem/5639)



## 풀이

~~~java
	static class Node{
		int num;
		Node left;
		Node right;
		Node(int num){
			this.num = num;
		}
	}
~~~
노드 클래스를 생성해줍니다. 

~~~java
		void add(Node node,int num) {
			if(node==null) {
				root = new Node(num);
			}else {
				if (num > node.num) {
                    if(node.right != null) add(node.right, num);
                    else node.right = new Node(num);
                } else if(num < node.num) {
                    if(node.left != null) add(node.left, num);
                    else node.left = new Node(num);
                }
			}
		}
~~~

add 메소드를 통해 노드가 비어있으면 root, 현재 노드보다 작으면 왼쪽 자식 노드, 현재 노드보다 크면 오른쪽 자식 노드에 삽입해주었습니다.

~~~java
		void postorder(Node node) {
			if(node.left!=null) {
				postorder(node.left);
			}
			if(node.right!=null) {
				postorder(node.right);
			}
			System.out.println(node.num);
		}
~~~

그리고 postorder 메소드를 통해 후위순회를 해주어 왼쪽 자식노드, 오른쪽 자식 노드, 현재 노드 순서대로 출력을 해주었습니다.

## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5639_S1_이진_검색_트리 {
	static class Node{
		int num;
		Node left;
		Node right;
		Node(int num){
			this.num = num;
		}
	}
	static class Tree{
		Node root;
		void add(Node node,int num) {
			if(node==null) {
				root = new Node(num);
			}else {
				if (num > node.num) {
                    if(node.right != null) add(node.right, num);
                    else node.right = new Node(num);
                } else if(num < node.num) {
                    if(node.left != null) add(node.left, num);
                    else node.left = new Node(num);
                }
			}
		}
		
		void postorder(Node node) {
			if(node.left!=null) {
				postorder(node.left);
			}
			if(node.right!=null) {
				postorder(node.right);
			}
			System.out.println(node.num);
		}
	}
	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Tree tree = new Tree();
		
		while(true) {
			String str = br.readLine();
			
			if(str==null || str.equals(""))
				break;
			
			int num = Integer.parseInt(str);
			tree.add(tree.root, num);
		}
		
		tree.postorder(tree.root);

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 17712kb| 760ms|