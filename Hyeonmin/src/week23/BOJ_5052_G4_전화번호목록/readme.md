## BOJ 5052 G4 전화번호 목록
- 트리
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/5052

전화번호 목록이 주어진다. 이때, 이 목록이 일관성이 있는지 없는지를 구하는 프로그램을 작성하시오.

전화번호 목록이 일관성을 유지하려면, 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.

예를 들어, 전화번호 목록이 아래와 같은 경우를 생각해보자

- 긴급전화: 911
- 상근: 97 625 999
- 선영: 91 12 54 26

이 경우에 선영이에게 전화를 걸 수 있는 방법이 없다. 전화기를 들고 선영이 번호의 처음 세 자리를 누르는 순간 바로 긴급전화가 걸리기 때문이다. 따라서, 이 목록은 일관성이 없는 목록이다. 


#### 입력
첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50) 각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000) 다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다. 전화번호의 길이는 길어야 10자리이며, 목록에 있는 두 전화번호가 같은 경우는 없다
<br><br>

#### 출력
각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다
<br><br>

###  💡 풀이 1

변수
`static Set<String> telList` : 력한 전화번호에 대한 모든 prefix를 저장하는 set

<br>

`TreeSet`을 사용해서 가능한 모든 prefix를 저장하여서 풀어낸 방법이다

입력할 모든 전하번호를 `telArr`에 저장을 한 후에 정렬을 하여 내림차순 방향으로 처리 한다

각 번호에 대한 모든 prefix를 subString으로 `telList`에 저장을 하고 새로 입력하는 번호가 이미 `telList`에 존재하는 경우를 일관성이 없는 경우로 여긴다

```
			N = Integer.parseInt(br.readLine());
			
			telList = new TreeSet<>();
			
			String[] telArr = new String[N];
			
			for (int i = 0; i < N; i++) {
				String tel = br.readLine();
				telArr[i] = tel;
			}
			
			//번호를 정렬
			Arrays.sort(telArr);
			
			boolean isPrefix = false;
			
			for (int i = telArr.length-1; i >= 0; i--) {
				//prefix가 존재하는 경우
				if(telList.contains(telArr[i])){
					isPrefix = true;
					break;
				}
				
				//현재 번호에서 해당하는 모든 prefix를 저장
				for (int j = 0; j < telArr[i].length(); j++) {
					telList.add(telArr[i].substring(0,j));
				}
			}
```


```
			if(isPrefix) {
				sb.append("NO").append("\n");
			}
			else {
				sb.append("YES").append("\n");
			}
```

###  💡 풀이 2


변수

```java
	static class Trie{
		boolean isEnd;
		boolean isContinue;
		Trie[] children;
		
		public Trie() {
			this.isEnd = false;
			this.isContinue = false;
			this.children = new Trie[10];
		}
		
		public boolean add(int idx, String tel) {
			if(isEnd) {
				return false;
			}
			
			if(idx == tel.length()) {
				isEnd = true;
				
				if(isContinue) {
					return false;
				}
				else {
					return true;
				}
			}
			
			int num = Character.getNumericValue(tel.charAt(idx));
			
			if(children[num] == null) {
				children[num] = new Trie();
				isContinue = true;
			}
			
			return children[num].add(idx+1, tel);
		}
	}
```

<br>

Trie를 활용한 풀어낸 방법이다

`Trie`클래스를 만들어서 각 순서의 숫자마다 1개의 숫자를 가지고 그 하위의 Trie를 저장할 `Trie[] children`을 갖고있다.
911의 경우에 `childnen[9] -> children[1] -> children[1]` 순서로 저장이 된다

`isEnd`는 해당 노드의 숫자가 어떠한 전화번호의 마지막인 경우를 의미하고 `isContinue`는 중간인 경우를 의미한다

`N`개의 입력에 대해서 `Trie`에 추가를 한다. 유효하지 않은 경우는 `isPrefix`를 `true`로 하여 표시하고 `N`개의 입력을 마저 받는다

```
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			boolean isPrefix = false;
			Trie trie = new Trie();
			
			for (int i = 0; i < N; i++) {
				String tel = br.readLine();
				
				//n개만큼 읽어야 하니까 break를 하면 안된다
				if(!isPrefix && !trie.add(0, tel)) {
					isPrefix = true;
				}
			}
			
			if(isPrefix) {
				sb.append("NO").append("\n");
			}
			else {
				sb.append("YES").append("\n");
			}
			
		}
```

전홥번호의 유효 여부는 Trie의 `add`를 실행하는 과정에서 확인한다.
아직 새로운 전화번호의 입력이 끝나지 않았는데 `isEnd`가 `true`인 경우, 입력이 끝나는 순간 노드의 `isContinue`가 `true`인 경우가 유효하지 않은 경우이다

```
		public boolean add(int idx, String tel) {
			if(isEnd) {
				return false;
			}
			
			if(idx == tel.length()) {
				isEnd = true;
				
				if(isContinue) {
					return false;
				}
				else {
					return true;
				}
			}
			
			int num = Character.getNumericValue(tel.charAt(idx));
			
			if(children[num] == null) {
				children[num] = new Trie();
				isContinue = true;
			}
			
			return children[num].add(idx+1, tel);
		}
	}
```



<br><br>

###  💡 소스코드1
```
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class BOJ_5052_G4_전화번호목록 {
	static int T, N;
	//입력한 전화번호에 대한 모든 prefix를 저장하는 set
	static Set<String> telList;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			telList = new TreeSet<>();
			
			String[] telArr = new String[N];
			
			for (int i = 0; i < N; i++) {
				String tel = br.readLine();
				telArr[i] = tel;
			}
			
			//번호를 정렬
			Arrays.sort(telArr);
			
			boolean isPrefix = false;
			
			for (int i = telArr.length-1; i >= 0; i--) {
				//prefix가 존재하는 경우
				if(telList.contains(telArr[i])){
					isPrefix = true;
					break;
				}
				
				//현재 번호에서 해당하는 모든 prefix를 저장
				for (int j = 0; j < telArr[i].length(); j++) {
					telList.add(telArr[i].substring(0,j));
				}
			}
			
			if(isPrefix) {
				sb.append("NO").append("\n");
			}
			else {
				sb.append("YES").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}





```


<br><br>

###  💡 소스코드2
```
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5052_G4_전화번호목록_Trie {
	static int T, N;
	
	static class Trie{
		boolean isEnd;
		boolean isContinue;
		Trie[] children;
		
		public Trie() {
			this.isEnd = false;
			this.isContinue = false;
			this.children = new Trie[10];
		}
		
		public boolean add(int idx, String tel) {
			if(isEnd) {
				return false;
			}
			
			if(idx == tel.length()) {
				isEnd = true;
				
				if(isContinue) {
					return false;
				}
				else {
					return true;
				}
			}
			
			int num = Character.getNumericValue(tel.charAt(idx));
			
			if(children[num] == null) {
				children[num] = new Trie();
				isContinue = true;
			}
			
			return children[num].add(idx+1, tel);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			boolean isPrefix = false;
			Trie trie = new Trie();
			
			for (int i = 0; i < N; i++) {
				String tel = br.readLine();
				
				//n개만큼 읽어야 하니까 break를 하면 안된다
				if(!isPrefix && !trie.add(0, tel)) {
					isPrefix = true;
				}
			}
			
			if(isPrefix) {
				sb.append("NO").append("\n");
			}
			else {
				sb.append("YES").append("\n");
			}
			
		}
		
		System.out.println(sb.toString());
	}
}




```


<br>


####결과

| 메모리  | 시간 |
|----|----|
|129664 KB |	1692 ms|
|106576 KB | 340 ms|
