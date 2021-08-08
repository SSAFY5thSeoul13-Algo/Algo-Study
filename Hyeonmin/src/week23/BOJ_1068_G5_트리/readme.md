## BOJ 1068 G5 트리
- 트리
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1068

첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.

첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.

###  💡 풀이

변수
`int N` : 노드 개수 
`int root ` : 루트 노드의 번호
`int remove` : 삭제할 노드의 번호
`List<Integer>[] tree` : 각 트리의 노드의 하위 노드 번호를 저장할 리스트


<br>

각 번호에 해당하는 리스트에 하위 노드의 번호들을 저장한 후에 dfs를 통해서 해당 리스트의 길이가 0이 되는 경우를 count하여 풀었다

리스트의 배열에 각각 ArrayList를 할당해준다

```java
		for (int i = 0; i < 50; i++) {
			tree[i] = new ArrayList<>();
		}
```

입력을 받을 때 루트에 해당하는 경우 `root`에 해당 번호를 저장하고 아닌 경우는 해당하는 `tree[]` 리스트에 해당 노드의 번호를 추가해준다 

```java
		//노드 번호
		int idx = 0;
		
		//입력
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			//루트 노드 번호 저장
			if(num == -1) {
				root = idx;
				idx++;
				
				continue;
			}
			
			//해당 번호의 노드에 하위 노드 저장
			tree[num].add(idx);
			
			idx++;
		}
```

삭제할 노드의 번호를 입력 받고 `dfs`를 실행한다

```java
		//삭제할 노드
		remove = Integer.parseInt(br.readLine());
		
		//탐색
		dfs(root);
```

`dfs` 메소드는 `tree[num]`의 사이즈가 0인경우에 `count`를 1 증가시키고 그렇지 않은 경우에는 다음 `dfs`를 실행하여 존재하는 모든 노드의 하위 노드 개수를 확인하여 리프 노드의 수를 계산한다

```java
	static void dfs(int num) {
		if(num == remove) {
			return;
		}
		
		int size = tree[num].size();
		
		//삭제한 노드가 하위에 있는 경우
		if(tree[num].contains(remove)) {
			size--;
		}
		
		//하위 노드가 없으면 리프노드
		if(size == 0) {
			count++;
			return;
		}
		
		//계속해서 하위 탐색
		for (Integer idx : tree[num]) {
			if(idx != remove)
				dfs(idx);
		}
	}
```


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1068_G5_트리 {
	static List<Integer>[] tree = new ArrayList[50];	
	static int N, root, remove, count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 50; i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//노드 번호
		int idx = 0;
		
		//입력
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			//루트 노드 번호 저장
			if(num == -1) {
				root = idx;
				idx++;
				
				continue;
			}
			
			//해당 번호의 노드에 하위 노드 저장
			tree[num].add(idx);
			
			idx++;
		}
		
		//삭제할 노드
		remove = Integer.parseInt(br.readLine());
		
		//탐색
		dfs(root);
		
		System.out.println(count);
	}
	
	static void dfs(int num) {
		if(num == remove) {
			return;
		}
		
		int size = tree[num].size();
		
		//삭제한 노드가 하위에 있는 경우
		if(tree[num].contains(remove)) {
			size--;
		}
		
		//하위 노드가 없으면 리프노드
		if(size == 0) {
			count++;
			return;
		}
		
		//계속해서 하위 탐색
		for (Integer idx : tree[num]) {
			if(idx != remove)
				dfs(idx);
		}
	}
}



```


<br>



메모리|시간
--|--
11604 KB |88 ms