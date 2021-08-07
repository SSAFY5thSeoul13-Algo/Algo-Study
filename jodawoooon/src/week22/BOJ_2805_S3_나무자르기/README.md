## BOJ 1654 S3 랜선자르기
- 이분탐색
- silver3



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2805


상근이는 나무 M미터가 필요하다. 근처에 나무를 구입할 곳이 모두 망해버렸기 때문에, 정부에 벌목 허가를 요청했다. 정부는 상근이네 집 근처의 나무 한 줄에 대한 벌목 허가를 내주었고, 상근이는 새로 구입한 목재절단기를 이용해서 나무를 구할것이다.

목재절단기는 다음과 같이 동작한다. 먼저, 상근이는 절단기에 높이 H를 지정해야 한다. 높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다. 그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다. 따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다. 예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자. 상근이가 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고, 상근이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다. (총 7미터를 집에 들고 간다) 절단기에 설정할 수 있는 높이는 양의 정수 또는 0이다.

상근이는 환경에 매우 관심이 많기 때문에, 나무를 필요한 만큼만 집으로 가져가려고 한다. 이때, 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오.

<br>

#### ✔ 입력
첫째 줄에 나무의 수 N과 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)

둘째 줄에는 나무의 높이가 주어진다. 나무의 높이의 합은 항상 M보다 크거나 같기 때문에, 상근이는 집에 필요한 나무를 항상 가져갈 수 있다. 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.
<br>

#### ✔ 출력
적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.
<br>


<br><br>

###  💡 풀이

이분탐색으로 mid값을 찾아 풀었고, 
잘린 나무의 길이가 M이상 일 경우 ans를 구했다.     

타입이 (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)이고 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이므로 변수 타입에 주의해야 한다.    

<br><br>

###  💡 소스코드


```java
package week22.BOJ_2805_S3_나무자르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2805_S3_나무자르기 {
	static int N;
	static long[] trees;
	static long left, right, ans, M; 
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //나무의 수
		M = Long.parseLong(st.nextToken()); //집으로 가져가야 되는 나무의 길이 M
		
		trees = new long[N];
		
		left = 1;
		
		st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n < N ; n++) {
			trees[n] = Integer.parseInt(st.nextToken());
			right = Math.max(right, trees[n]);
		}
		   
		//적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
		
		while(left<=right) {
			long mid = (left+right)/2;
			long sum = 0;

			for(int n = 0 ; n < N ; n++) {
				if(trees[n]>mid) {
					sum += (trees[n]-mid);
				}
			}

			if(sum>=M) {
				ans = Math.max(mid, ans);
				left = mid + 1;
			}
			else right = mid - 1;
		}
		
		System.out.println(ans);
		
	}
}


```

<br><br>

### 🚩 결과
메모리 172024	
시간 560