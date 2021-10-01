## BOJ 1590 S1 캠프가는영식
- 이분탐색
- silver1



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1590

영식이는 민식이와 함게 고속버스를 타고 캠프를 가야 하지만, 민식이는 영식이를 깨우지 않고 혼자 버스를 타고 캠프에 가버렸다.

영식이는 혼자 고속버스터미널까지 가서 캠프에 오려고 한다. 터미널에는 캠프 장소까지 운행하는 N가지의 버스가 있다. 각각의 버스는 시작 시각, 간격, 대수의 정보를 가지고 있다. 예를 들어, 어떤 버스의 시작 시각이 특점 시점을 기준으로 10분 후이고, 간격은 10분이고, 대수가 5대이면, 이 버스는 10분, 20분, 30분, 40분, 50분에 한 대씩 출발한다.

영식이는 버스터미널에 T분에 도착했다. 영식이가 버스를 타려면 최소 몇 분을 더 기다려야 하는지 구하는 프로그램을 작성하시오.

<br>

#### ✔ 입력
첫째 줄에 버스의 개수 N과 영식이가 버스터미널에 도착하는 시간 T가 주어진다. 둘째 줄부터 총 N개의 줄에 각 버스의 시작 시각, 간격, 대수가 공백을 사이에 두고 주어진다. 버스의 개수와 각 버스의 대수는 100,000보다 작거나 같은 자연수이며, T와 각 버스의 시작 시각과 간격은 1,000,000,000보다 작거나 같은 자연수이다.
<br>

#### ✔ 출력
첫째 줄에 영식이가 기다려야 하는 시간을 출력한다. 영식이가 도착하는 동시에 버스가 출발하면 정답은 0이다. 만약 버스가 없어서 캠프에 갈 수 없으면 -1을 출력한다. 정답은 231보다 작다.
<br>


<br>

###  💡 풀이

예외처리와 이분탐색을 이용해서 풀었다.
로직 자체를 생각하는 것은 오래걸리지 않았으나


```java
			while(left<right) {
				mid = (left+right)/2;
				if(busTimes.get((int)mid)==T) {
					System.out.println(0);
                    return;
				}
				else if(busTimes.get((int)mid)>T) right = mid;
				else   left = mid+1;
			}
```

`while`문 안에서 `busTimes.get((int)mid)`와 `T`가 일치할 때, `ans = 0`셋팅 후 `break`를 하는데 이 때 break 후 `return` 처리나 `flag`를 처리를 안해줘서 break 밖에서 0으로 바뀐 0에 연산을 한 번 더 해주는 오류가 생겼다.......

그래서 계속 틀렸습니다가 나왔다
그치만 포기하지 않았다..

앞으로는 메소드를 따로 빼서 풀어야겠다

<br><br>

###  💬 소스코드

```java
import java.io.*;
import java.util.*;

public class Main_BOJ_1590_S1_캠프가는영식 {
	static int N, T;
	static long ans;
	static ArrayList<Long> busTimes;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //버스의 개수
		T = Integer.parseInt(st.nextToken()); //영식 도착 시간
		
		busTimes = new ArrayList<>();
		
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			long start = Long.parseLong(st.nextToken());
			long gap = Long.parseLong(st.nextToken());
			long n = Long.parseLong(st.nextToken());
			
			for(int j = 0 ; j < n ; j++) {
				busTimes.add(start + gap*j);
			}
			//모든 버스 시간 입력
		}
		
		Collections.sort(busTimes); //시간 순 정렬
		
		if(busTimes.get(0) >= T ) ans = busTimes.get(0) - T;
		else if(busTimes.get(busTimes.size() - 1) < T) ans = -1;
		else {
			//이분탐색
			long left = 0;
			long right = busTimes.size() -1;
			long mid = (left+right)/2;

			while(left<right) {
				mid = (left+right)/2;
				if(busTimes.get((int)mid)==T) {
					System.out.println(0);
                    return;
				}
				else if(busTimes.get((int)mid)>T) right = mid;
				else   left = mid+1;
			}
            
            ans = busTimes.get((int)right)-T;
		}
		System.out.println(ans);
		
	}

}

```
<br><br>


###  💯 채점 결과
	11784	92