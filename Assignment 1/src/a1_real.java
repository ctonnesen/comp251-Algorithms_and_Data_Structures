import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class a1_real {
	
	public static int silence(int[] positions) {
		int lowest = positions.length;
		Hashtable<Integer, Integer> dupes = new Hashtable<>();
		for (int i = 0; i < positions.length; i++) {
			if (dupes.get(positions[i]) != null && i-dupes.get(positions[i]) < lowest) {
				lowest = i - dupes.get(positions[i]);
			}
			dupes.put(positions[i], i);
		}
		return lowest;
	}

//		for (int i = 0; i < positions.length; i++) {
//		if (dupes.get(positions[i]) == null) {
//			dupes.put(positions[i], i);
//		} else {
//			if (i-dupes.get(positions[i]) < lowest) {
//				lowest = i-dupes.get(positions[i]);
//			}
//			dupes.put(positions[i], i);
//		}
//	}
//		return lowest;
//}


	public static void main(String[] args) {
		try {
			String path = args[0];
      		File myFile = new File(path);
      		Scanner sc = new Scanner(myFile);
      		int num_students = sc.nextInt();
      		int[] positions = new int[num_students];
      		for (int student = 0; student<num_students; student++){
				positions[student] =sc.nextInt();
      		}
      		sc.close();
      		int answer = silence(positions);
      		System.out.println(answer);
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	}
//		int[] check = {1,2,3,4,5,6,1,9};
//		System.out.println(silence(check));
  	}
}