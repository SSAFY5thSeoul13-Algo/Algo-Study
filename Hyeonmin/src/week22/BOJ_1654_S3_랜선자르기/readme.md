## BOJ 1654 Silver3 랜선 자르기
- 이분 탐색
- Silver3

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1654

길이가 제걱각인 K개의 랜선을 있을 때, 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다. 

편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정한다. 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자. N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다. 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.


#### 입력
첫째 줄에는 이미 가지고 있는 랜선의 개수 K, 그리고 필요한 랜선의 개수 N이 입력된다. K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다. 그리고 항상 K ≦ N 이다. 그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다. 랜선의 길이는 23^1-1보다 작거나 같은 자연수이다.

#### 출력
첫째 줄에 N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력한다.

###  💡 풀이

변수
`int K` : 가지고 있는 랜선의 개수
`int N` : 만들려고 하는 랜선의 개수
`int[] arr` : 가지고 있는 각 랜선의 길이
`long result` : N개 이상을 만들 수 있는 최대의 랜선 길이


<br>

K, N을 입력받고 K길이의 arr배열을 만들어서 각 랜선의 길이를 저장한다
이 때 가장 긴 랜선의 길이를 2분 탐색을 실행하는 오른쪽 범위의 끝으로 잡는고 왼쪽 범위의 끝 부분은 1로 잡는다

```java
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[K];
		
		long right = 0;
		
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			right = Math.max(arr[i], right);
		}
				
		long left = 1;
```

`middle`길이로 랜선을 자를 경우 몇개의 랜선을 만들 수 있는지 구하고 N개 이상인 경우 true, 미만인 경우 false를 반환하는 메소드이다

```java
	static boolean calcLines(long middle) {
		int count = 0;
		
		for (int i = 0; i < K; i++) {
			long num = arr[i]/middle;
			
			count += num;
		}
		
		//N개 이상의 랜선을 만드는 경우
		if(count >= N)
			return true;
		
		return false;
	}
```

범위의 왼쪽 끝`left`가 오른쪽 끝`right` 이하일 경우 탐색을 반복한다

범위의 중간 값을 `middle`에 넣고 `calcLines`의 실행 결과가 true인 경우 현재 길이 `result`보다 더 긴 값인 경우 `middle`을 `result`에 저장하고 `left`를 `middle+1`로 변경, flase인 경우 `right`를 `middle-1`로 변경하고 다시 과정을 반복한다

반복이 끝나면 `N`개를 만들 수 있는 가장 긴 랜선의 길이 `result`를 출력한다

```java
		while(left <= right) {
			long middle = (left+right)/2;
			
			boolean isEnough = calcLines(middle);
			
			if(isEnough) {
				result = Math.max(result, middle);
				left = middle+1;
			}
			else {
				right = middle-1;
			}
		}
		
		System.out.println(result);
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654_S3_랜선자르기 {
	static int K, N;
	static int[] arr;
	static long result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[K];
		
		//이분 탐색범위의 오른쪽 끝 값
		long right = 0;
		
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			right = Math.max(arr[i], right);
		}
		
		//이분 탐색범위의 왼쪽 끝 값
		long left = 1;
		
		//탐색
		while(left <= right) {
			//범위의 중간 값. 자르게 될 랜선의 길이
			long middle = (left+right)/2;
			
			boolean isEnough = calcLines(middle);
			
			//N개 이상의 랜선을 만들 수 있는 경우
			if(isEnough) {
				result = Math.max(result, middle);
				left = middle+1;
			}
			else {
				right = middle-1;
			}
		}
		
		System.out.println(result);
	}
	
	static boolean calcLines(long middle) {
		int count = 0;
		
		for (int i = 0; i < K; i++) {
			long num = arr[i]/middle;
			
			count += num;
		}
		
		//N개 이상의 랜선을 만드는 경우
		if(count >= N)
			return true;
		
		return false;
	}
}




```


<br>

메모리|시간
--|--
15204|140
