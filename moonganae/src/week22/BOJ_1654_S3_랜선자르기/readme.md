## BAEKJOON SILVER 랜선 자르기
- 이분탐색
- Silver 3
- https://www.acmicpc.net/problem/1654
<br>

### 문제설명

> 집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다. 박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.
이미 오영식은 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다. 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다. 예를 들어 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)
편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자. 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자. N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다. 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.




### 제한사항
- 첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K, 그리고 필요한 랜선의 개수 N이 입력된다. 
- K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다. 
- 그리고 항상 K ≦ N 이다. 
- 그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다. 
- 랜선의 길이는 231-1보다 작거나 같은 자연수이다.

### 입출력 예

#### 예제 1
```
입력
4 11
802
743
457
539
```
```
출력
200
```


#### 입출력 예 설명
802cm 랜선에서 4개, 743cm 랜선에서 3개, 457cm 랜선에서 2개, 539cm 랜선에서 2개를 잘라내 모두 11개를 만들 수 있다.

### 풀이 및 과정
이분탐색 알고리즘을 사용하였습니다.

<이분탐색 전 설정>

먼저 최댓값을 찾기위해 랜선의 길이 배열을 정렬시킵니다.

```java
for(int i=0; i<K; i++) {
	line[i] = Integer.parseInt(br.readLine());
}

Arrays.sort(line);
```

<이분탐색>

자를 길이(mid)를 구한뒤, 만들 수 있는 갯수를 구한다.

```java
long mid = (min+max) / 2;
			
// 만들 수 있는 랜선의 갯수
int cnt = 0;
for(int len : line) {
	cnt += len/mid;
}
```

필요한 랜선의 수 N과 비교하여 많거나 같을 경우 max 값을 많을 경 min값을 갱신하며 구할 수 있느 최대 길이를 구한다.

```java
// 내가 원하는 갯수라면
if(N <= cnt) {
	// 최솟값을 올린다
	ans = Math.max(ans, mid);
	min = mid+1;
	
}else {
	// 최댓값을 낮춘다.
	max = mid-1;
}
```



#### 막힌점
- 처음에 갱신하는 min, max에 바로 Mid 값을 넣어줬는데 계속해서 무한루프가 발생하였다.
- 랜선의 길이가 INT 이하의 길이라 int로만 계산해주고 있었는데, mid를 구할대 min+max를 사용하기 때문에 long 타입을 사용하여야 한다.
- min의 초기값을 0으로 설정했는데, 1이상 정수의 범위를 가지므로, 1로 설정해야한다.<br>
0으로 설정할 경우 런타임 에러(/ by zero)가 발생한다.

### 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int K, N;
	static int[] line;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		line = new int[K];
		
		for(int i=0; i<K; i++) {
			line[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(line);
		
		// 자연수이기 때문에 1부터!
		long min = 1;
		long max = line[line.length-1];
		long ans = -1;
		
		while(min <= max) {
			long mid = (min+max) / 2;
			
			// 만들 수 있는 랜선의 갯수
			int cnt = 0;
			for(int len : line) {
				cnt += len/mid;
			}
			
			// 내가 원하는 갯수라면
			if(N <= cnt) {
				// 최솟값을 올린다
				ans = Math.max(ans, mid);
				min = mid+1;
				
			}else {
				// 최댓값을 낮춘다.
				max = mid-1;
			}
			
		}
		
		System.out.println(ans);
	}

}

```

### 결과
```
메모리 : 15320KB
시간 : 144ms
```

