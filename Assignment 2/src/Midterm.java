import java.util.*;
import java.lang.*;
import java.io.*;

public class Midterm {
	private static int[][] dp_table;
	private static int[] penalization;


	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int chairs;
		try {
			chairs = Integer.valueOf(reader.readLine());
			penalization = new int[chairs];
			for (int i = 0; i < chairs; i++) {
				penalization[i] = Integer.valueOf(reader.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int answer = lost_marks(penalization);
		System.out.println(answer);
	}

	public static int lost_marks(int[] penalization) {
		if (penalization.length == 0) {
			return 0;
		}
		dp_table = new int[penalization.length][penalization.length];
		for (int[] i : dp_table) {
			Arrays.fill(i, -1);
		}
		return penaltyCalc(penalization, 0, 0);
	}

	public static int penaltyCalc(int[] penal, int pos, int minJump) {
		if(pos==penal.length-1) return 0;
		// If we at final position, no jumps to be made
		if(dp_table[pos][minJump] != -1) return dp_table[pos][minJump];
		// Check table for possible previous value
		int forwardScore = 2147483646;
		int backwardScore = 2147483646;
		int posFor = pos+minJump+1;
		int posBack = pos-minJump;
		if(0<pos && posBack>=0)
			backwardScore = penal[posBack] + penaltyCalc(penal, posBack, minJump);
		//We add the value for the previous position, and then begin another instance to check the min from that spot
		if(posFor < penal.length)
			forwardScore = penal[posFor] + penaltyCalc(penal, posFor, minJump+1);
		return dp_table[pos][minJump] = Math.min(forwardScore, backwardScore);
	}


//	public static int penaltyCalc1(int[] penal, int pos, int minJump) {
//		if(pos==penal.length-1) return 0;
//		// If we at final position, no jumps to be made
//		if(dp_table[pos][minJump] != -1) return dp_table[pos][minJump];
//		// Check table for possible previous value
//		int forwardScore = 2147483646;
//		int backwardScore = 2147483646;
//		int forPos = pos+minJump;
//		int backPos = pos-(minJump-1);
//		if(0<pos && backPos>=0)
//			forwardScore = penal[backPos] + penaltyCalc(penal, backPos, minJump);
//		//We add the value for the previous position, and then begin another instance to check the min from that spot
//		if(forPos < penal.length)
//			backwardScore = penal[forPos] + penaltyCalc(penal, forPos, minJump+1);
//		return dp_table[pos][minJump] = Math.min(forwardScore, backwardScore);
//	}

}

//if(pos==penal.length-1) {
//		return 0;
//		}
//		// If we at final position, no jumps to be made
//		if(dp_table[pos][minJump] != -1){
//		return dp_table[pos][minJump];
//		}
//		// Check table for possible previous value
//		int forwardScore = 500;
//		int backwardScore = 500;
//		int prevPosFor = pos-minJump+1;
//		int prevPosBac = pos+minJump;
//		if(pos>0 && prevPosFor>=0)
//		forwardScore = penal[prevPosFor] + penaltyCalc(penal, prevPosFor, minJump);
//		//We add the value for the previous position, and then begin another instance to check the min from that spot
//		if(prevPosBac < penal.length)
//		backwardScore = penal[prevPosBac] + penaltyCalc(penal, prevPosBac, minJump+1);
//		return dp_table[pos][minJump] = Math.min(forwardScore, backwardScore);
//		}
//
//		}





//	public static int penaltyCalc(int[] penal, int prevJump, int pos, int penTot) {
//		for (int i = 1; i <= prevJump; i++) {
//			int forwards = 600;
//			int backwards = 600;
//			try {
//				backwards = penal[pos + i];
//			} catch (Exception ignore) {
//			}
//			try {
//				forwards = penal[pos - (i + 1)];
//			} catch (Exception ignore) {
//			}
//			int min = Math.min(forwards, backwards);
//			dp_table[pos][prevJump] = min;
//			if (min == backwards) {
//				penaltyCalc()
//				pos += prevJump;
//			} else {
//				pos -= (prevJump + 1);
//				prevJump++;
//			}
//		}
//		return 0;
//	}

//		while (pos != penal.length) {
//			int forwards = 600;
//			int backwards = 600;
//			try {
//				backwards = penal[pos - prevJump];
//			} catch (Exception ignore) {}
//			try {
//				forwards = penal[pos + prevJump+1];
//			} catch (Exception ignore) {}
//			int min = Math.min(forwards,backwards);
//			if (min == backwards) {
//				pos -= prevJump;
//			} else {
//				pos += prevJump + 1;
//				prevJump ++;
//			}
//			penTot+=min;
//			penaltyCalc(penal, prevJump, pos,penTot);
//		}
//		return penTot;


