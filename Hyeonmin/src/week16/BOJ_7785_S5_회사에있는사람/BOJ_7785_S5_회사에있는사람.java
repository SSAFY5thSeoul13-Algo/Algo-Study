package week16.BOJ_7785_S5_회사에있는사람;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_7785_S5_회사에있는사람 {
	static Set<String> set = new TreeSet<>();
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		//입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			//이름
			String name = st.nextToken();
			//출입여부
			String cmd = st.nextToken();

			//출근하는 경우 set에 추가
			if (cmd.equals("enter")) {
				set.add(name);
			}
			//퇴근하는 경우 set에서 제거
			else {
				set.remove(name);
			}

		}

		//set을 list로 변환
		List<String> list = new ArrayList<String>(set);

		//list를 사전의 역순으로 정렬
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		
		StringBuilder sb = new StringBuilder();
		
		//리스트에 있는 이름들을 sb에 저장
		for (String str : list) {
			sb.append(str).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
