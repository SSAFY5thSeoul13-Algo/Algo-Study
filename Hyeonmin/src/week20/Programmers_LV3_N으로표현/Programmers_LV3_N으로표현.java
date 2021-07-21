package week20.Programmers_LV3_N으로표현;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Programmers_LV3_N으로표현 {
	public static void main(String[] args) {
		int N = 5;
		int number = 12;
		
		System.out.println(solution(N, number));
	}

	static int solution(int N, int number) {
		List<HashSet<Integer>> list = new ArrayList<>();
		int temp = 0;
		
		//시작 숫자가 목표 숫자인 경우
		if(N == number)
			return 1;
		
		//i개의 N을 사용한 경우를 저장할 Set의 리스트를 만들기
		for (int i = 0; i < 9; i++) {
			//N을 i개 이어붙인 숫자
			temp += N*Math.pow(10, i-1);
			
			//목표랑 일치하면 해당 숫자의 수를 반환
			if(temp == number)
				return i;
			
			//리스트의 각 인덱스에 HashSet 할당
			list.add(new HashSet<Integer>());
			
			//각 HashSet에 N을 i개 이어붙인 숫자 저장
			list.get(i).add(temp);
		}
		
		//N을 i개 써서 나타낼 수 있는 수식으로 구해지는 숫자를 구할 반복문
		for (int i = 2; i < 9; i++) {
			//N을 i개보다 적게 쓴 경우의 숫자들의 조합으로 i개를 쓴 경우를 구함
			for (int j = 1; j < i; j++) {
				//j개의 N을 사용해 구할 수 있는 수의 리스트
				Iterator<Integer> dpList1 = list.get(j).iterator();
				
				//리스트에 숫자가 있는 만큼 반복
				while(dpList1.hasNext()) {
					//N을 j개 써서 만든 숫자
					int num = dpList1.next();
					
					//i-j개를 써서 만든 숫자의 리스트
					Iterator<Integer> dpList2 = list.get(i-j).iterator();
					
					//숫자가 남아있는 만큼 반복
					while(dpList2.hasNext()) {
						int num2 = dpList2.next();
						
						//0이 만들어진 경우는 제외
						if(num2 == 0)
							continue;
						
						//두 숫자의 사칙연산으로 i개의 N로 만들 수 있는 숫자들을 구함
						list.get(i).add(num + num2);
						list.get(i).add(num - num2);
						list.get(i).add(num * num2);
						list.get(i).add(num / num2);
					}
					
				}
				
				//i개의 N으로 만들 수 있는 모든 수를 구한 다음 number가 그 목록에 있는 경우 i반환
				if(list.get(i).contains(number))
					return i;
			}
			
		}

		//N의 개수가 8개를 넘어가면  -1 리턴
		return -1;
	}

}
