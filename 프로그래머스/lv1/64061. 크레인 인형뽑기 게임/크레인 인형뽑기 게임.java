import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();  // 바구니는 스택 이용
		
		for(int i=0; i<moves.length; i++) {  // 크레인 작동 횟수만큼 반복
			for(int j=0; j<board[0].length; j++) {
				int toy = board[j][moves[i] - 1];
				if(toy > 0) {  // 집은 인형이 있는 경우
					if(!basket.isEmpty() && basket.peek() == toy) {
                        // 같은 모양 인형이 연속해서 쌓이면 터뜨림
						answer += 2;
						basket.pop();
					} else {
                        // 아닌 경우 인형을 그냥 바구니에 넣기
						basket.push(toy);
					}
					
					board[j][moves[i] - 1] = 0;  // 인형을 집은 자리는 인형을 없애줘야함
					break;
				}
			}
		}
        
        return answer;
    }
}