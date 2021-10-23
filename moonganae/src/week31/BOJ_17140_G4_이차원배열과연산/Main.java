package week31.BOJ_17140_G4_이차원배열과연산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 확인할 좌표(R,C), 찾을 값(K)
    static int R,C,K;
    // 배열 A
    static int[][] A = new int[100][100];
    // 행의 수, 열의 수
    static int rLen = 3, cLen = 3;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) -1;
        C = Integer.parseInt(st.nextToken()) -1;
        K = Integer.parseInt(st.nextToken());

        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while(time <= 100){
            if(A[R][C] == K) {
                System.out.println(time);
                return;
            }

            if(rLen >= cLen){
                sortR();

            }else {
                sortC();
            }
            time++;
        }
        System.out.println(-1);

    }
    static void printA(){
        System.out.println("----------A------------");
        for(int i=0; i<rLen; i++){
            for(int j=0; j<cLen; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
    // R 연산
    static void sortR(){

        // 정렬을 하고난 후의 길이 보관 -> 남은 공간 0으로 채우기 위함
        List<Integer> lenList = new ArrayList<>();
        int max = -1;
        for(int i=0; i<rLen; i++){

            // 같은 행에서 0을 제외한 수를 가져오기
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<cLen; j++){
                if(A[i][j] == 0) continue;
                list.add(A[i][j]);
            }

            // Number 리스트 가져오기
            List<Number> numberList = getNumberList(list);

            // 원래 배열 A에 넣기
            int idx = 0;
            for(Number num : numberList){
                A[i][idx++] = num.val;
                A[i][idx++] = num.cnt;
            }

            max = Math.max(max, idx);
            lenList.add(idx);
        }

        // 숫자를 제외한 나머지 영역은 0으로 채우기
        for(int i=0; i<rLen; i++){
            for(int j=lenList.get(i); j<max; j++){
                A[i][j] = 0;
            }
        }

        // 열의길이 갱신
        cLen = max;
    }

    // C연산
    static void sortC(){

        // 정렬을 하고난 후의 길이 보관 -> 남은 공간 0으로 채우기 위함
        List<Integer> lenList = new ArrayList<>();
        int max = -1;
        for(int i=0; i<cLen; i++){

            // 같은 열에서 0을 제외한 수를 가져오기
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<rLen; j++){
                if(A[j][i] == 0) continue;
                list.add(A[j][i]);
            }

            // Number 리스트 가져오기
            List<Number> numberList = getNumberList(list);

            // 원래 배열 A에 넣기
            int idx = 0;
            for(Number num : numberList){
                A[idx++][i] = num.val;
                A[idx++][i] = num.cnt;
            }

            max = Math.max(max, idx);
            lenList.add(idx);
        }

        // 숫자를 제외한 나머지 영역은 0으로 채우기
        for(int i=0; i<cLen; i++){
            for(int j=lenList.get(i); j<max; j++){
                A[j][i] = 0;
            }
        }
        // 행의 길이 갱신
        rLen = max;
    }

    static List<Number> getNumberList(List<Integer> list){
        Collections.sort(list);
        int prev = list.get(0), cnt=1;

        List<Number> numberList = new ArrayList<>();
        for(int j=1; j<list.size(); j++){

            if(prev == list.get(j)){
                cnt++;
            }else{
                numberList.add(new Number(prev, cnt));
                prev = list.get(j);
                cnt=1;
            }
        }
        numberList.add(new Number(prev, cnt));
        Collections.sort(numberList);

        return numberList;
    }

    static class Number implements Comparable<Number>{
        int val, cnt;
        public Number(int val, int cnt){
            this.val = val;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number b){
            if(this.cnt == b.cnt){
                return this.val - b.val;
            }
            return this.cnt - b.cnt;
        }

        @Override
        public String toString(){
            return "val="+this.val + " cnt="+this.cnt;
        }
    }
}
