package week16.BOJ_1764_S4_듣보잡;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		/**
		 * 듣도 못한 사람의 명단 + 보도 못한 사람의 명단이 주어질때
		 * 듣도 보도 못한 살마의 명단을 구하라
		 * 
		 * [풀이]
		 * 1. 듣도 못한 사람을 Set에 넣기 
		 * 2. 보도 못한 사람의 이름이 Set에 있다면 List에 넣고, cnt증가 
		 * 3. List 사전순으로 정렬
		 * 4. 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		
		// 듣도 못한 사람 Set에 삽입
		for(int i=0; i<N; i++) {
			String name = br.readLine();
			set.add(name);
		}
		
		
		List<String> list = new ArrayList<>();
		int cnt=0;
		
		// 보도 못한 사람 확인
		for(int i=0; i<M; i++) {
			String name = br.readLine();
			
			// 듣도 보도 못한 사람이면
			if(set.contains(name)) {
				cnt++;				// 인원수 증가
				list.add(name);		// 리스트 삽입
			}
		}
		
		// 사전순 정렬
		Collections.sort(list);
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt + "\n");			// 인원수 출력
		for(String name : list) {
			sb.append(name + "\n");		// 이름 출력
		}
		System.out.println(sb.toString());
	}

}
