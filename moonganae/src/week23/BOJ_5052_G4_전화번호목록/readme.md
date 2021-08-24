## BAEKJOON Gold4 5052 전화번호 목록
- 트라이, 그래프
- Gold 4
- https://www.acmicpc.net/problem/5052
<br>

### 문제설명

> 전화번호 목록이 주어진다. 이때, 이 목록이 일관성이 있는지 없는지를 구하는 프로그램을 작성하시오.
전화번호 목록이 일관성을 유지하려면, 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.
예를 들어, 전화번호 목록이 아래와 같은 경우를 생각해보자

- 긴급전화: 911
- 상근: 97 625 999
- 선영: 91 12 54 26

> 이 경우에 선영이에게 전화를 걸 수 있는 방법이 없다. 전화기를 들고 선영이 번호의 처음 세 자리를 누르는 순간 바로 긴급전화가 걸리기 때문이다. 따라서, 이 목록은 일관성이 없는 목록이다. 



### 입력
- 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50) 
- 각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000) 
- 다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다. 
- 전화번호의 길이는 길어야 10자리이며, 목록에 있는 두 전화번호가 같은 경우는 없다.

### 출력
각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다.

### 입출력 예

#### 예제 1
입력

```
2
3
911
97625999
91125426
5
113
12340
123440
12345
98346
```
출력

```
NO
YES
```


### 풀이 및 과정
트라이 자료구조를 사용하였습니다.

전체적인 알고리즘은 다음과 같습니다.

1. 전화번호 목록 입력
2. 정렬 -> 길이가 짧은 전화번호부터 트라이에 넣어야 접두어 여부를 확인할 수 있다.
3. 각 전화번호를 하나씩 눌러보며 Trie.contains인지 확인해보기.




#### 막힌점
- YES, NO를 Yes, No로 출력하였습니다. ㅠㅠ

### 소스코드
```java
package week23.BOJ_5052_G4_전화번호목록;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	/* 테스트케이스 수, 전화번호 수 */
	static int T, N;
	/* 전화번호 목록 */
	static List<String> numbers;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		
		for(int t=0; t<T; t++) {
		
			N = Integer.parseInt(br.readLine());
			
			numbers = new ArrayList<>();
		
			// 전화번호 목록 추가
			for(int i=0; i<N; i++) {
				numbers.add(br.readLine());
			}
			// 전화번호 정렬
			Collections.sort(numbers);
			
			// 일관성여부 확인
			if(isConsistence()) System.out.println("YES");
			else System.out.println("NO");
			
		}
	}
	
	static boolean isConsistence() {
		// 트라이 자료구조
		Trie trie = new Trie();
		
		// 모든 전화번호 순회
		for(String word : numbers) {
			
			// 전화번호 내에서 하나씩 입력해보기
			for(int i=0; i<word.length(); i++) {
				// 다른 전화번호가 prefix라면 일관성 실패
				if(trie.contains(word.substring(0, i+1))) {
					return false;
				}
			}
			
			// 트라이 자료구조에 현재 전화번호 넣가
			trie.insert(word);;
		}
		return true;
	}


	static class Trie{
		TrieNode root = new TrieNode();
		
		void insert(String word) {
			
			TrieNode thisNode = this.root;
			
			for(int i=0; i<word.length(); i++) {
				thisNode = ( thisNode.childs[Character.getNumericValue(word.charAt(i))] = new TrieNode() );
			}
			
			thisNode.setLast(true);
		}
		
		boolean contains(String word) {
			TrieNode thisNode = this.root;
			
			for(int i=0; i<word.length(); i++) {
				
				int idx = Character.getNumericValue(word.charAt(i));
				TrieNode node = thisNode.childs[idx];
				if(node == null) return false;
				
				thisNode = node;
			}
			
			return thisNode.isLast();
		}
	}
	static class TrieNode {
		
		TrieNode[] childs = new TrieNode[10];
		
		boolean isLastChar;
		
		boolean isLast() {
			return this.isLastChar;
		}
		
		void setLast(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
	}

}
```

### 결과
```
메모리 : 221180 KB	
시간 : 912 ms
```
