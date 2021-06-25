package week17.bruteforcing2;

import java.util.ArrayList;
import java.util.List;

public class 모의고사 {
	static int[] answers = {1,3,2,4,2};
	public static void main(String[] args) {
		int[] answer = solution(answers);
		
		for(int i=0; i<answer.length; i++) {
			System.out.print(answer[i] + " ");
		}

	}
	
	public static int[] solution(int[] answers) {
		List<Integer> list = new ArrayList<>();
		int[] answer = {};
		
		int[] first = {1,2,3,4,5};
		int[] second = {2,1,2,3,2,4,2,5};
		int[] third = {3,3,1,1,2,2,4,4,5,5};
		
		// 각 수포자의 점수를 담는 배열 
		int[] score = new int[3];
		for(int i=0; i<answers.length; i++) {
			if(answers[i]==first[i%5])	score[0]++;
			if(answers[i]==second[i%8])	score[1]++;
			if(answers[i]==third[i%10])	score[2]++;

		}
		
		int max = 0;
		// 제일 많이 맞은 갯수 구하기 
		max = Math.max(score[0], Math.max(score[1], score[2]));
		
		// 많이 맞은 수포자만 list에 삽입 
		for(int i=0; i<score.length; i++) {
			if(max==score[i]) {
				list.add(i+1);
			}
		}
		
		answer = new int[list.size()];
		for(int i=0; i<list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		
		
        return answer;
    }

}
