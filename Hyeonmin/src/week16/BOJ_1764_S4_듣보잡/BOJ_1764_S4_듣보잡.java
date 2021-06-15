package week16.BOJ_1764_S4_듣보잡;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1764_S4_듣보잡 {
	static int N, M;
	//듣도 못한 명단
	static Set<String> set = new HashSet<String>();
	//듣보 리스트
	static List<String> dbList = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//듣도 못한 수
		N = Integer.parseInt(st.nextToken());
		//보도 못한 수
		M = Integer.parseInt(st.nextToken());
		
		//듣도 못한 명단 저장
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			
			set.add(str);
		}
		
		
		//보도 못한 명단
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			
			//듣도 못한 명단에 있으면 듣보 리스트에 추가
			if(set.contains(str)) {
				dbList.add(str);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		//듣보 리스트의 크기
		System.out.println(dbList.size());
		
		//듣보 리스트를 사전 순으로 정렬
		dbList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2){
				return o1.compareTo(o2);
			}
		});
		
		//듣보 리스트 출력
		for (String string : dbList) {
			sb.append(string).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
