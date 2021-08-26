## BOJ 2428 S1 표절
- 투포인터
- silver3



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2428

세계적인 석유 재벌 "규현 조 압둘 티크리티 안드레스 후세인 리오넬 솔레르 살라 마리우 두스 산투스 펠리스 빈 자이드 술탄 친나왓 뱅거 7세"는 1등 상품으로 페라리를 걸고 프로그래밍 대회를 개최했다. 이 대회의 참석자는 총 N명이고 각각 솔루션 파일 1개를 제출했다. 이 솔루션 파일을 F1, F2, ..., Fn이라고 한다.

채점 결과를 발표하기 전에, 남의 것을 배낀 사람이 있는지 찾아내려고 한다. 이 대회의 주최측은 두 파일을 비교해서 너무 비슷한지 아닌지 판별하는 프로그램이 있다.

하지만, 제출한 파일의 개수가 너무 많아서, 모든 쌍을 검사한다면, 2012년 지구가 멸망할 때 까지도 검사를 해야할 판이다. 따라서, 파일 크기가 너무 다른 경우에는 그러한 쌍을 검사하지 않고 넘어가기로 했다.

좀더 정확하게 하기 위해서, 대회의 심판들은 두 파일이 있을 때, 작은 파일의 크기가 큰 파일 크기의 90%보다도 작을 때는, 이러한 쌍은 검사하지 않고 넘어가기로 했다. 따라서, (Fi, Fj) 쌍을 검사해야 하는데, 이때, i≠j이고, size(Fi) ≤ size(Fj)이면서, size(Fi) ≥ 0.9 × size(Fj)인 쌍만 검사하려고 한다.

몇 개의 쌍을 검사해야 하는 지 구하는 프로그램을 작성하시오.

<br>

#### ✔ 입력
첫째 줄에 제출한 솔루션의 개수 N이 주어진다. 둘째 줄에는 각 솔루션 파일의 크기 size(F1), size(F2), ..., size(FN)이 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ size(Fi) ≤ 100,000,000) 솔루션 파일의 크기는 정수이다.
<br>

#### ✔ 출력
첫째 줄에 검사해야 하는 파일의 개수를 출력한다.
<br>


<br>

###  💡 풀이

`검사해야 하는 파일의 개수`를 구하는 문제이다.<br>

해당 문제는 n의 크기가 최대 100000이므로 모든 쌍을 탐색하려면 O(n^2)으로 시간초과가 발생한다.
또한 answer의 범위를 주의해야 한다. (int형 아님)


<br>
투 포인터 알고리즘을 이용하여 `특정 조건을 만족하는 만족시키는 개수`를 구했다.

두개의 포인터 변수(`left`, `right`)의 시작점이 배열의 시작점인 경우의 문제였다.<br>

<br>

1. 먼저 files를 오름차순 정렬 하고 포인터 변수를 선언했다.
```java
Arrays.sort(files);
		
		
int left = 0;
int right = 0; //부분 배열의  인덱스를 가리키는 left, right(0으로 초기화)
```

<br>

2. 주어진 조건`(Fi >= Fj*0.9)`을 만족할 때 까지 right 포인터를 증가시킨다.

주어진 조건을 만족하는 파일의 개수를 ans에 더한뒤, left 포인터를 하나 증가시키고 위 과정을 반복한다.
```java
while(left < N) {
			
	/* 2.  size(Fi) ≥ 0.9 × size(Fj) */
	while(true) {
		if(right>=N-1) break; //범위 벗어나면 break
				
		int Fi = files[left];
		int Fj = files[right+1];
		if(Fi < Fj*0.9) break; //조건 만족하지 않으면 break
		else right++;
	}

	ans += right-left; //검사해야하는 파일의 수
	left++;
}
```
<br><br>

###  💡 소스코드


```java
package week24.BOJ_2428_S3_표절;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 2428 표절
 * @Author : Daun JO
 * @Date : 2021. 8. 26
 * @Algorithm : Sliding Window
 *
 */


public class Main_BOJ_2428_S3_표절 {
	
	
	static int N,files[];
	static long ans;
	public static void main(String[] args) throws Exception {
		
		Queue<Integer> queue = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //솔루션의 개수
	
		files = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			files[i] = Integer.parseInt(st.nextToken());
		}


		
		/* 1. 먼저 files를 오름차순 정렬한다. */ 

		Arrays.sort(files);
		
		
		int left = 0;
		int right = 0; //부분 배열의  인덱스를 가리키는 left, right(0으로 초기화)
		
		while(left < N) {
			
			/* 2.  size(Fi) ≥ 0.9 × size(Fj) */
			while(true) {
				if(right>=N-1) break; //범위 벗어나면 break
				
				int Fi = files[left];
				int Fj = files[right+1];
				if(Fi < Fj*0.9) break; //조건 만족하지 않으면 break
				else right++;
			}

			ans += right-left; //검사해야하는 파일의 수
			left++;
		}
		
		System.out.println(ans);
		
		
		
	}
}


```

<br><br>


###  💯 채점 결과
메모리 30276	시간 360
