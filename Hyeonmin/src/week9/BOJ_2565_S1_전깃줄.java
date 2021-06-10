package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2565_S1_전깃줄 {
	static int N, count;
	//전깃줄을 저장할 리스트
	static List<Node> list = new ArrayList<Node>();
	//인덱스 i번째 줄을 끝에 붙였을 경우의 최대 전선 개수
	static int[] l;
	
	//전선 정보
	static class Node{
		int start;
		int end;
		
		public Node(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		l = new int[N];
		
		StringTokenizer st;
		
		//전선정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken());
			
			list.add(new Node(a, b));
		}
		
		//전선의 시작 번호를 기준으로 정렬
		list.sort((o1,o2) -> Integer.compare(o1.start, o2.start));
		
		LIS();
		
		//전선이 최대로 많을 때 최대 전선과의 차이 = 제거한 전선의 최소 숫자
		System.out.println(N-count);
	}
	
	//최장 증가 수열
	static void LIS() {
		for (int i = 0; i < N; i++) {
			l[i] = 1;
			for (int j = 0; j < i; j++) {
				if(list.get(i).end > list.get(j).end && l[i] < l[j] + 1) {
					l[i] = l[j] + 1;
				}
			}
			count = Math.max(count, l[i]);
		}
	}
}
