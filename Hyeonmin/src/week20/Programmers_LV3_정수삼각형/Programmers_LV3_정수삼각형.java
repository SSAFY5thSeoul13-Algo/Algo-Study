package week20.Programmers_LV3_정수삼각형;

import java.util.ArrayList;
import java.util.List;

public class Programmers_LV3_정수삼각형 {
	public static void main(String[] args) {
		int[][] triangle = {
				{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}
		};
		
		System.out.println(solution(triangle));
	}
	
	static int solution(int[][] triangle) {
		//삼각형의 높이
		int size = triangle.length;
		//각 높이별 숫자를 저장할 자료형
		List<List<Integer>> list = new ArrayList<>();
		
		//각 높이마다 리스트 추가
		for (int i = 0; i < size; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		//맨 아래 리스트에 삼각형 맨 아래에 있는 숫자 저장
		for (int i = 0; i < triangle[size-1].length; i++) {
			list.get(size-1).add(triangle[size-1][i]);
		}
		
		//각 높이별로 그 바로 위 높이의 각 위치에 있는 숫자의 합을 구해 더 큰 수를 해당 위치에 저장
		for (int i = size-1; i > 0; i--) {
			//i높이의 리스트에 있는 수자의 수만큼 반복
			for (int j = 0; j < list.get(i).size(); j++) {
				//i높이 j번쨰 숫자
				int num = list.get(i).get(j);
				
				//왼쪽 대각선 위로 올라가는 경우
				int idx = j-1;
				
				if(idx > -1) {
					
					//왼쪽 대각선 위에 아직 숫자가 저장되지 않은 경우 그 위치에 있는 triangle배열의 숫자와의 합을 저장
					if(list.get(i-1).size() <= idx) {
						list.get(i-1).add(num + triangle[i-1][idx]);
					}
					//왼쪽 대각선 위에 숫자가 저장되어 있는 경우 그 위치에 있는 숫자와 비교해 더 큰것을 저장
					else {
						int temp = list.get(i-1).get(idx);
						
						if(temp < num + triangle[i-1][idx]) {
							list.get(i-1).set(idx, num + triangle[i-1][idx]);
						}
					}
				}
				
				//오른쪽 대각선 위로 올라가는 경우
				idx = j;
				
				if(idx < triangle[i-1].length) {
					
					//오른쪽 대각선 위에 아직 숫자가 저장되지 않은 경우 그 위치에 있는 triangle배열의 숫자와의 합을 저장
					if(list.get(i-1).size() <= idx) {
						list.get(i-1).add(num + triangle[i-1][idx]);
					}
					//오른쪽 대각선 위에 숫자가 저장되어 있는 경우 그 위치에 있는 숫자와 비교해 더 큰것을 저장
					else {
						int temp = list.get(i-1).get(idx);
						
						if(temp < num + triangle[i-1][idx]) {
							list.get(i-1).set(idx, num + triangle[i-1][idx]);
						}
					}
				}
			}
		}
		
		//삼각형 맨위에 저장된 숫자 리턴
		return list.get(0).get(0);
	}
}
