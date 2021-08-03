## BOJ 12015 G2 가장 긴 증가하는 부분 수열 2
- 이분탐색
- gold2



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/12015

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

<br>

#### ✔ 입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)
<br>

#### ✔ 출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
<br>


<br><br>

###  💡 풀이
주어진 숫자 `arr`를 탐색해서 각 원소의 위치를 `이분 탐색`으로 찾았다.  
arr[i] (`num`)을 삽입하는 방법은 경우에 따라 2가지로 나뉜다.  

1. `lis`의 마지막 원소보다 `num`이 크다 
```java
			if(num > lis.get(lis.size()-1)) {
             			//리스트 마지막에 넣어야 되는 경우 => 뒤에 넣기
            			lis.add(num);
          		}
```
=> `lis`의 마지막에 `add`한다.

<br>

2. `lis`의 마지막 원소보다 `num`이 같거나 작다.
```java
			int left = 0;
			int right = lis.size()-1;
			int mid = 0;
				
			while(left<right) {
				mid = (left+right)/2;
				if(lis.get(mid) < num) left = mid+1;
				else right = mid; 
			}
				
			lis.set(right, num);
```
=> `lis`에서 `num`보다 같거나 큰 수의 idx를 찾아서 거기에 `num`을 넣는다.
=> `이분탐색`사용
    

<br><br>

###  💡 소스코드


```java
package week22.BOJ_12015_G2_가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_12015_G2_가장긴증가하는부분수열2 {
	static int N, ans, arr[];
	static ArrayList<Integer> lis;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //수열의 크기
		arr = new int[N]; //수열
		lis = new ArrayList<>();
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//LIS의 길이 구하기

		//각 원소가 LIS에 들어갈 위치를 찾는다.

		
		lis.add(0);
		
		for(int num : arr) {
			if(num > lis.get(lis.size()-1)) {
             			//리스트 마지막에 넣어야 되는 경우 => 뒤에 넣기
            			lis.add(num);
          		}
		
			else { //넣을 위치 찾는다
				int left = 0;
				int right = lis.size()-1;
				int mid = 0;
				
				while(left<right) {
					mid = (left+right)/2;
					if(lis.get(mid) < num) left = mid+1;
					else right = mid; 
				}
				
				lis.set(right, num);
			}
		}
		
		System.out.println(lis.size()-1);

	}
	
	
}

```

<br><br>

### 🚩 결과
11828	84