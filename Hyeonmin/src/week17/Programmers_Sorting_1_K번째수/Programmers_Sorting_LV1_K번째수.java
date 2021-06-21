package week17.Programmers_Sorting_1_K번째수;

import java.util.Arrays;

public class Programmers_Sorting_LV1_K번째수 {
	static int[] array = { 1, 5, 2, 6, 3, 7, 4 };
	static int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
	static int[] answer;

	public static void main(String[] args) {
		answer = solution();
		
		for (int num : answer) {
			System.out.print(num+" ");
		}
	}

	static int[] solution() {
		//입력된 명령의 수
		int length = commands.length;

		//명령의 수만큼 숫자를 저장할 배열 생성
		int[] answer = new int[length];

		//명령의 수 만큼 반복
		for (int i = 0; i < length; i++) {
			//잘라내기 범위 시작점
			int start = commands[i][0];
			//잘라내기 범위 끝점
			int end = commands[i][1];
			//원하는 숫자의 위치
			int target = commands[i][2];
			
			//배열에서 원하는 범위만큼을 잘라내서 저장
			int[] temp = Arrays.copyOfRange(array, start-1, end);
			
			//배열을 정렬
			Arrays.sort(temp);
			
			//target번째에 있는 숫자를 배열에 저장
			answer[i] = temp[target-1];
			
		}
		
		return answer;
	}
}
