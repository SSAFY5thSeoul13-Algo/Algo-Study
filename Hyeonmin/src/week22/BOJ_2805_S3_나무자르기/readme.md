## BOJ 1654 Silver3 나무 자르기
- 이분 탐색
- Silver3

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2805

나무 M미터가 필요하다. 근처에 나무를 구입할 곳이 모두 망해버렸기 때문에 새로 구입한 목재절단기를 이용해서 나무를 구할것이다.

목재절단기는 다음과 같이 동작한다. 먼저, 절단기에 높이 H를 지정해야 한다. 높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다. 그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다. 따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다. 예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자. 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고, 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다. 절단기에 설정할 수 있는 높이는 양의 정수 또는 0이다.

나무는 필요한 만큼만 집으로 가져가려고 한다. 이때, 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 나무의 수 N과 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)

둘째 줄에는 나무의 높이가 주어진다. 나무의 높이의 합은 항상 M보다 크거나 같기 때문에, 집에 필요한 나무를 항상 가져갈 수 있다. 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.

#### 출력
적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.

###  💡 풀이

변수
`int N` : 나무의 수
`int M` : 집에 가져가려는 나무의 길이
`int[] arr` : 각 나무의 높이
`int result` : M미터 이상의 목재를 얻을 수 있을 때의 절단기의 최대 높이


<br>

N, M을 입력받고 N길이의 `arr`배열을 만들어서 각 나무의 높이를 저장한다
이 때 가장 높은 나무의 길이를 2분 탐색을 실행하는 오른쪽 범위의 끝으로 잡는고 왼쪽 범위의 끝 부분은 0으로 잡는다

```java
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		//절단기의 최대 높이
		int right = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(right < arr[i])
				right = arr[i];
		}
		
		//절단기의 최소 높이
		int left = 0;
```

`middle`높이로 절단기의 높이를 설정하는 경우의 획득 가능한 목재를 계산하여 `M`이상이면 true, 미만이면 false를 반환하는 메소드이다

```java
	//나무 자르기
	static boolean cutTree(int middle) {
		long length = 0;
		
		for (int i = 0; i < N; i++) {
			//설정한 높이보다 높은 나무의 잘라낸 길이를 더함
			if(arr[i] > middle)
				length += arr[i] - middle;
		}
		
		if(length >= M)
			return true;
		
		return false;
	}
```

범위의 왼쪽 끝`left`가 오른쪽 끝`right` 이하일 경우 탐색을 반복한다

범위의 중간 값을 `middle`에 넣고 절단기의 높이로 설정해 `cutTree`의 실행 결과가 true인 경우 현재 절단기의 최대 높이 `result`보다 더 긴 값인 경우 `middle`을 `result`에 저장하고 `left`를 `middle+1`로 변경, false인 경우 `right`를 `middle-1`로 변경하고 다시 과정을 반복한다

반복이 끝나면 `M`미터의 목재를 얻을 수 있는 절단기의 최대 높이 `result`를 출력한다

```java
		while(left <= right) {
			//설정할 절단기의 높이
			int middle = (left+right)/2;
			
			boolean isEnough = cutTree(middle);
			
			//M이상의 목재를 얻은 경우
			if(isEnough) {
				//더 높은 높이를 result에 저장
				if(result < middle)
					result = middle;
				
				left = middle + 1; 
			}
			else {
				right = middle -1;
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

public class BOJ_2805_S3_나무자르기 {
	static int M, N, result;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		//절단기의 최대 높이
		int right = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(right < arr[i])
				right = arr[i];
		}
		
		//절단기의 최소 높이
		int left = 0;
		
		while(left <= right) {
			//설정할 절단기의 높이
			int middle = (left+right)/2;
			
			boolean isEnough = cutTree(middle);
			
			//M이상의 목재를 얻은 경우
			if(isEnough) {
				//더 높은 높이를 result에 저장
				if(result < middle)
					result = middle;
				
				left = middle + 1; 
			}
			else {
				right = middle -1;
			}
		}
		
		System.out.println(result);
	}
	
	//나무 자르기
	static boolean cutTree(int middle) {
		long length = 0;
		
		for (int i = 0; i < N; i++) {
			//설정한 높이보다 높은 나무의 잘라낸 길이를 더함
			if(arr[i] > middle)
				length += arr[i] - middle;
		}
		
		if(length >= M)
			return true;
		
		return false;
	}

}




```


<br>

메모리|시간
--|--
167900 kb|496 ms
