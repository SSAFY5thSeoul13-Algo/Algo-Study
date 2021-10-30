package week32.boj2610;

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
