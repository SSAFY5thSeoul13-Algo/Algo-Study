package week17.Programmers_LV1_모의고사;

public class Programmers_LV1_모의고사 {
	static int[] answers = {1,2,3,4,5};
	static int[] answer1 = {1,2,3,4,5};
	static int[] answer2 = {2,1,2,3,2,4,2,5};
	static int[] answer3 = {3,3,1,1,2,2,4,4,5,5};
	
	public static void main(String[] args) {
		
	}
	
	static int[] solution() {
		int length = answers.length;
		//각 수포자가 정답을 맞춘 횟수를 저장할 배열
		int[] cnt = new int[4];
		
		//수포자1의 대답배열의 인덱스
		int idx1 = 0;
		//수포자2의 대답배열의 인덱스
		int idx2 = 0;
		//수포자3의 대답배열의 인덱스
		int idx3 = 0;
		
		for (int idx = 0; idx < length; idx++) {
			//수포자 1이 정답을 맞춤
			if(answer1[idx1] == answers[idx]) {
				cnt[1]++;
			}
			//수포자 2이 정답을 맞춤
			if(answer2[idx2] == answers[idx]) {
				cnt[2]++;
			}
			//수포자 3이 정답을 맞춤
			if(answer3[idx3] == answers[idx]) {
				cnt[3]++;
			}
			
			//각 수포자의 다음 대답 인덱스 설정
			idx1 = (idx1+1)%5;
			idx2 = (idx2+1)%8;
			idx3 = (idx3+1)%10;
		}
		
		//수포자 중 가장 많이 문제를 맞춘 횟수
		int max = Math.max(Math.max(cnt[1], cnt[2]), cnt[3]);
		
		//가장 많이 문제를 맞춘 사람의 수
		int count = 0;
		
		//각 수포자가 문제를 맞춘 수가 최대랑 같으면 count 증가
		for (int i = 1; i <= 3; i++) {
			if(cnt[i] == max)
				count++;
		}
		
		//가장 많은 답을 맞춘 수포자의 명단
		int[] answer = new int[count];
		
		int idx = 0;
		
		//각 수포자가 맞춘 문제 수가 최대랑 같으면 배열에 추가
		for (int i = 1; i <= 3; i++) {
			if(cnt[i] == max)
				answer[idx++] = i;
		}
		
		return answer;
	}
}
