package week17.Programmers_LV2_카펫;

import java.util.Arrays;

//세로 최소 3
public class Programmers_LV2_카펫 {
	static int brown = 8, yellow = 1;

	public static void main(String[] args) {
		int[] answer = solution();
		
		System.out.println(Arrays.toString(answer));
	}

	static int[] solution() {
		int cntBrown = 0;
		int cntYellow = 0;

		//가로 세로가 동일할 떄의 가로 길이를 x의 시작으로 for문 실행
		for (int x = (brown - 4) / 4 + 2; x <= brown / 2; x++) {
			//y 값 계산
			int y = (brown - 2 * x) / 2 + 2;

			//x가 y보다 작은것은 고려하지 않음
			if (x < y)
				continue;

			//겉 둘레의 길이를 갈색 카펫의 수로 저장
			cntBrown = y * 2 + x * 2 - 4;
			//전체 넓이에서 갈색 카펫 수를 뺀것이 노란색 카펫의 수
			cntYellow = y * x - cntBrown;

			//카펫 수가 일치하면 그 때의 x,y를 값으로 갖는 배열 리턴
			if (cntBrown == brown && cntYellow == yellow) {
				return new int[] { x, y };
			}
		}
		
		return null;
	}
}
