import java.util.*;

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//Sort assignments
		//Order will depend on how compare function is implemented
		int[] homeworkPlan = new int[lastDeadline];
		Collections.sort(Assignments, new Assignment());
		// If homeworkPlan[i] has a value -1, it indicates that the 
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}
		ArrayList<Assignment> AssignmentsCopy = (ArrayList<Assignment>) Assignments.clone();
		for(int i = 0; i < lastDeadline; i++) {
			for (Assignment cur : AssignmentsCopy) {
				if (i<cur.deadline) {
					homeworkPlan[i]=cur.number;
					AssignmentsCopy.remove(cur);
					Collections.sort(Assignments, new Assignment());
					break;
				}
			}
		}
		return homeworkPlan;
	}
}
	



