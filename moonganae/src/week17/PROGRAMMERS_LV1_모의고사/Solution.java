package week17.PROGRAMMERS_LV1_모의고사;

class Solution {
    
    
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int giveUp[][] = {    {1,2,3,4,5,0,0,0,0,0},
                            {2,1,2,3,2,4,2,5,0,0},
                            {3,3,1,1,2,2,4,4,5,5}};
        /* 각 수포자들의 현재 진행 idx */
        int idx[] = new int[3];
        /* 수포자들의 규칙의 길이 */
        int size[] = {5,8,10};
        /* 수포자들이 맞은 점수 */
        int score[] = new int[3];
        /* 수포자 찍는 방식에 맞추어 계산 */
        for(int i=0; i<answers.length; i++){
            
            int sol = answers[i];
            
            for(int j=0; j<3; j++){
                
                if(giveUp[j][idx[j]++] == sol)
                    score[j]++;
                
                idx[j] %= size[j];
            }
        }
        
        int max = -1;
        /* 최고 점수 가리기 */
        for(int i=0; i<3; i++){
            max = Math.max(max, score[i]);
        }
        /* 최고점 받은 사람수 구하기 */
        int cnt = 0;
        for(int i=0; i<3; i++){
            if(max == score[i]) cnt++;
        }
        /* 최고점 받은 사람 넣기 */
        answer = new int[cnt];
        int z=0;
        for(int i=0; i<3; i++){
            if(max == score[i])
                answer[z++] = i+1;
        }
        
        return answer;
    }
}