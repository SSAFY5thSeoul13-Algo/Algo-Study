package week31.BOJ_7682_G5_틱택토;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println((int)'O'%10 - 7); // 2
//        System.out.println((int)'X'%10 - 7); // 1
//        System.out.println((int)'O'%10 - 7 | (int)'X'%10 - 7); // 3
        int idx = 0;
        while(true){

            String input = br.readLine();

            if(input.equals("end")) break;

            if(solve(input)) System.out.println("valid");
            else System.out.println("invalid");
        }
    }

    static boolean solve(String input){


        int[] cnt = new int[3];

        int cntO=0, cntX=0;
        // OX 갯수세기
        for(char c : input.toCharArray()){
            if(c == 'O') cntO++;
            if(c == 'X') cntX++;
        }

        // O가 X보다 많으면 안됨.
        if(cntO > cntX) return false;
        // X돌과 O돌의 차이가 1개 초과이면 안된다.
        if(cntX-cntO > 1) return false;

        // 0 1 2 , 0 3 6
        // 3 4 5 , 1,4,7
        // 6,7.8 , 2,5,8
        // 빙고 갯수 세기
        for(int i=0; i<3; i++){
            // 가로
            int horizon = i + i*2;
            if(input.charAt(horizon) != '.' && input.charAt(horizon) == input.charAt(horizon +1) && input.charAt(horizon) == input.charAt(horizon+2) ) {
                cnt[(int)input.charAt(horizon)%10 - 7]++;
            }
            // 세로
            if(input.charAt(i) != '.' && input.charAt(i) == input.charAt(i+3) && input.charAt(i) == input.charAt(i+6) ) {
                cnt[(int)input.charAt(i)%10 - 7]++;
            }
        }
        int idx = (int) input.charAt(4)%10 - 7;
        // 대각선
        if(idx != -1 && input.charAt(0) == input.charAt(4) && input.charAt(4) == input.charAt(8)) cnt[idx]++;
        if(idx != -1 && input.charAt(2) == input.charAt(4) && input.charAt(4) == input.charAt(6)) cnt[idx]++;

        // OX 빙고가 동시에 존재하면 안된다.
        if(cnt[1] > 0 && cnt[2] > 0) return false;
        // X가 이겼다면 X의 수가 O의 수보다 1개
        else if(cnt[1]>0 && (cntX-cntO) == 1 ) return true;
        // O가 이겼다면 X의 수와 O의 수가 같다.
        else if(cnt[2]>0 && cntX == cntO) return true;
        // 놓을 말이 없는경우 O가 이기지 않으면 된다.
        else if(cntX+cntO == 9 && cnt[2] == 0) return true;

        return false;
    }

}