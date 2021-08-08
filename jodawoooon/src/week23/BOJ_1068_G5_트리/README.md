## BOJ 1068 G5 트리
- 트리
- gold5



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1068

트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.

트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.

예를 들어, 다음과 같은 트리가 있다고 하자.

![](https://images.velog.io/images/jodawooooon/post/556fb903-84e5-4ef1-801e-a7ea0d9f6ed0/image.png)

현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 지우면, 다음과 같이 변한다. 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.

![](https://images.velog.io/images/jodawooooon/post/88067632-22c2-4cbf-94f3-80f049e49ea1/image.png)

이제 리프 노드의 개수는 1개이다.

<br>

#### ✔ 입력
첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.
<br>

#### ✔ 출력
첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.
<br>


<br><br>

###  💡 풀이


- `ArrayList<Integer>[] tree` : tree 정보를 저장할 자료구조
- `root` : root 노드 번호를 저장할 변수
- `tgt` : 삭제할 노드 번호
- `dfs` : 리프 노드 탐색

입력받은 정보를 바탕으로 tree를 세팅하고, root 노드를 설정한다.
```java
		for(int i=0; i<N ; i++) {
			int parent = Integer.parseInt(st.nextToken()); //각 노드의 부모
			
			if(parent==-1) root = i; //부모가 없다면 -1이 주어진다. 즉 루트이다.
			else tree[parent].add(i);
		}
```
그리고 root부터 `dfs`를 이용하여 리프노드를 탐색했다.  

<br>

dfs 메소드 내의 구조는 다음과 같다.

1. 해당 노드가 `tgt`이면 (삭제할 노드이면) 탐색을 멈춘다.
```java
		if(node==tgt) return; //삭제할 노드이면 탐색 중지
```

2. 해당 노드가 자식이 없다면 리프노드이다.
	따라서 `ans++`하고 탐색을 멈춘다.
```java
		if(tree[node].size()==0) {
			//자식이 없다 => 리프노드이다.
			ans++;
			return;
		}
```
3. 해당 노드의 자식노드를 계속 탐색한다.
	이 때, 현재 노드가 오직 `tgt`노드만을 자식노드로 가지고 있다면 리프노드가 된다.
    따라서 `ans++`하고 탐색을 멈춘다.
```java
		for(int v : tree[node]) {

			if(v==tgt&&tree[node].size()==1) {
				//현재 node가 삭제할 노드만을 자식으로 가지고 있다. => 리프노드가 된다	
				ans++;
				return;	
			}
			dfs(v);
		}
```



<br><br>

###  💡 소스코드


```java
package week23.BOJ_1068_G5_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1068 트리
 * @Author : Daun JO
 * @Date : 2021. 8. 7. 
 *
 */
public class Main_BOJ_1068_G5_트리 {
	
	static int N, root, tgt, ans;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/**
		 * 노드 지우고, 리프노드 찾기
		 */
		
		N = Integer.parseInt(br.readLine()); //트리의 노드의 개수
		
		tree = new ArrayList[N];
		for(int i=0; i<N ; i++) {
			tree[i] = new ArrayList<>(); //이진트리아님
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N ; i++) {
			int parent = Integer.parseInt(st.nextToken()); //각 노드의 부모
			
			if(parent==-1) root = i; //부모가 없다면 -1이 주어진다. 즉 루트이다.
			else tree[parent].add(i);
		}
		
		tgt = Integer.parseInt(br.readLine()); //지울 노드의 번호
		
		dfs(root); //root부터 탐색
		
		System.out.println(ans);
		
	}
	
	private static void dfs(int node) {
		if(node==tgt) return; //삭제할 노드이면 탐색 중지
		
		if(tree[node].size()==0) {
			//자식이 없다 => 리프노드이다.
			ans++;
			return;
		}
		
		for(int v : tree[node]) {

			if(v==tgt&&tree[node].size()==1) {
				//현재 node가 삭제할 노드만을 자식으로 가지고 있다. => 리프노드가 된다	
				ans++;
				return;	
			}
			dfs(v);
		}
		
		
	}
	
	
}

```

<br><br>


###  💯 채점 결과
11604	88