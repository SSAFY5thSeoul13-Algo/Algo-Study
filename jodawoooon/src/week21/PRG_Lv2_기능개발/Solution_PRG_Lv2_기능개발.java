import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answerList = new ArrayList<>(); //나중에 Array형으로 변환할 정답 리스트
        
        //앞에 기능이 배포 되어야 내가 배포될 수 있따.
        int P_LEN = progresses.length;
        
        int idx = 0; //작업 대상
        int cnt = 0; //배포되는 기능의 수
        
        while(idx<P_LEN){

            for(int i = idx ; i < P_LEN ; i++){
                progresses[i] += speeds[i];
            }
            //하루가 지난 뒤 작업량
            
            
            
            if(progresses[idx]>=100){ //현재 가장 앞에있는 작업이 배포 가능하다면
                cnt++; //배포 가능한 기능의 갯수 1개 증가

                //이제 뒤에꺼 배포 가능한거 있는지 검사해야한다
                
                
                int i = idx+1; //탐색 대상 인덱스
                while(i<P_LEN){
                    if(progresses[i]>=100){
                        cnt++; //배포 가능한 기능의 갯수++
                        i++;
                    }else break;
                }
                
                idx = i; //while문 탈출 시 인덱스를 저장
                answerList.add(cnt); //배포된 작업의 갯수 answerList에 추가
                cnt = 0; //cnt 초기화
            }

            
        }
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i<answerList.size() ; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}