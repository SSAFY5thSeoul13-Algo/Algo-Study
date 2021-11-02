## BOJ 2610 회의준비 
- Floyd-Warshall
- 🥇 Gold2
- 🔗[회의준비 문제 바로가기](https://www.acmicpc.net/problem/2610)



## 풀이

이 문제는 플로이드와샬 알고리즘을 이용하여 먼저 각 번호의 의견전달시간을 구해주었습니다.

~~~java
		arr = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i!=j)
					arr[i][j] = INF;
			}
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			arr[tmp1][tmp2] = 1;
			arr[tmp2][tmp1] = 1;
		}
		
		for(int j=1; j<=N; j++) {
			for(int i=1; i<=N; i++) {
				if(i==j)	continue;
				for(int k=1; k<=N; k++) {
					if(j==k || i==k)	continue;
					if(arr[i][k]>arr[i][j] + arr[j][k]) {
						arr[i][k] = arr[i][j] + arr[j][k];
					}
				}
			}
		}
~~~

이렇게 구해진 2차원 배열에서 10001인 값은 다른 위원회 번호이므로 전달할 수 없습니다.
먼저 같은 위원회를 구하기 위해 boolean 타입 배열을 통해 1번 부터 N까지 검사를 해줍니다.
같은 위원회를 구하기 위해 자기자신과 같은 숫자이면 continue, 
방문하지 않은 번호이고 그 값이 INF가 아니라면 아직 위원회에 배정되지 않은 전달가능한 번호이므로 같은 위원회 리스트에 넣어줍니다.

~~~java
		List<Integer> commission = new ArrayList<>();
		visited[idx] = true;
		commission.add(idx);
		
		for(int i=1; i<=N; i++) {
			if(i==idx)	continue;
			if(arr[idx][i] != INF && !visited[i]) {
				visited[i] = true;
				commission.add(i);
			}
		}
~~~

그리고 그 위원회의 대표를 구하기 위해 각 번호들의 의견전달시간의 최댓값 중 최소값을 구해주었습니다.

~~~java
		int min = INF;
		int leaderNum = 0;
		for(int i=0; i<commission.size(); i++) {
			int tmp = 0;
			int a = commission.get(i);
			for(int j=0; j<commission.size(); j++) {
				if(i==j)	continue;
				int b = commission.get(j);
				if(tmp<arr[a][b]) {
					tmp = arr[a][b];
				}
			}
			if(min>tmp) {
				min = tmp;
				leaderNum = a;
			}
		}
~~~

이 로직에서 return된 대표자 번호를 list에 담아서 오름차순 정렬한뒤, 차례로 list의 길이와 순서대로 값을 출력해주었습니다.


## 막힌점 
위원회에서 모든 참석자들의 의사전달시간 중 최댓값이 최소가 되도록 대표를 정하는 프로그램 -> 이 부분을 잘못 이해하여 다른 로직으로 풀었었습니다..

## 소스코드
~~~java
import java.io.*;
import java.util.*;

public class BOJ_2610_G2_회의준비 {
	static final int INF = 10001;
	static boolean[] visited;
	static int N,M;
	static int[][] arr;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i!=j)
					arr[i][j] = INF;
			}
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			arr[tmp1][tmp2] = 1;
			arr[tmp2][tmp1] = 1;
		}
		
		for(int j=1; j<=N; j++) {
			for(int i=1; i<=N; i++) {
				if(i==j)	continue;
				for(int k=1; k<=N; k++) {
					if(j==k || i==k)	continue;
					if(arr[i][k]>arr[i][j] + arr[j][k]) {
						arr[i][k] = arr[i][j] + arr[j][k];
					}
				}
			}
		}
		
		visited = new boolean[N+1];
		// 각 대표자 번호를 담는 리스트 
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				int leader = check(i);
				list.add(leader);
			}
		}
		
		Collections.sort(list);
		
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}

	}
	static int check(int idx) {
		List<Integer> commission = new ArrayList<>();
		visited[idx] = true;
		commission.add(idx);
		
		for(int i=1; i<=N; i++) {
			if(i==idx)	continue;
			if(arr[idx][i] != INF && !visited[i]) {
				visited[i] = true;
				commission.add(i);
			}
		}
		
		
		int min = INF;
		int leaderNum = 0;
		for(int i=0; i<commission.size(); i++) {
			int tmp = 0;
			int a = commission.get(i);
			for(int j=0; j<commission.size(); j++) {
				if(i==j)	continue;
				int b = commission.get(j);
				if(tmp<arr[a][b]) {
					tmp = arr[a][b];
				}
			}
			if(min>tmp) {
				min = tmp;
				leaderNum = a;
			}
		}
		
		return leaderNum;
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 12880kb| 136ms|