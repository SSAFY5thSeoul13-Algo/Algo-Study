package week17.PRG_Lv1_모의고사;

import java.util.*;

public class Solution_PRG_Lv1_모의고사 {
	static int[] std1 = {1,2,3,4,5};
	static int[] std2 = {2,1,2,3,2,4,2,5};
	static int[] std3 = {3,3,1,1,2,2,4,4,5,5};
	public static void main(String[] args) {
		int[] arr = new int[] {1,3,2,4,2};
		System.out.println(Arrays.toString(solution(arr)));
	}
	
	public static int[] solution(int[] answers) {
		ArrayList<Integer> maxList = new ArrayList<>(); //가장 높은 점수를 받은 사람

		int[] cnt = new int[3];

		
		int max = 0; //가장 높은 점수
		
		for (int i = 0; i < answers.length; i++) {
			if(answers[i]==std1[i%5]) cnt[0]++; //1번 맞은 개수
			if(answers[i]==std2[i%8]) cnt[1]++; //2번 맞은 개수
			if(answers[i]==std3[i%10]) cnt[2]++; //3번 맞은 개수
		}
		

		for (int i = 0; i < 3; i++) {
			//오름차순으로 집어넣기
			if(max<cnt[i]) {
				max=cnt[i];
				maxList.clear();
				maxList.add(i+1);
			}else if(max==cnt[i]) {
				maxList.add(i+1);
			}
		}

		//list를 array로 옮기기
		int[] res = new int[maxList.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = maxList.get(i);
		}
		
		return res;
	}
}
