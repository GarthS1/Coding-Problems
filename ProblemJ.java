/**
As the coach of your university’s ICPC teams, you encourage your students to solve as many Kattis problems as possible. This has numerous benefits — for example, it helps improve students’ competitive programming skills, and it also creates a strong sense of school spirit. The main reason for the latter is that each time a student solves a problem, not only does that student’s individual Kattis score increase, but so does the group score for your university. It is fun and motivating for your students (and you) to watch your university move up in the Kattis rankings.

The collective score for a group of n Kattis users is calculated as follows: Let s0,s1,…,sn−1 be the the n individual scores in non-increasing order (i.e., s0≥s1≥s2≥…≥sn−1). Then the group score is

15∑i=0n−1si⋅(45)i
So, for example, if n=3 and the individual scores are s0=500 , s1=120, s2=75, then the group score is

15(500⋅1+120⋅(45)+75⋅(1625))=128.8
Thanks to Kattis, being an ICPC coach seems pretty easy, but sometimes you worry that the school spirit fostered by the Kattis ranking system is not enough to retain all your students. What if one of your students transfers to another university and, as a result, changes university affiliation on Kattis? How will this affect the group score for your university?

Actually, what you really want to know is the average new group score if exactly one of your students leaves. More precisely, if your students’ scores are s0≥s1≥s2≥…≥sn−1, let gi be the new group score for your university if the student with score si leaves (and the other (n−1) students remain). What is the average value of gi over all all n students?

Input
The first line of input contains an integer, n (2≤n≤50), the number of student competitive programmers at your university (all of whom use Kattis, of course). This is followed by n lines, each containing one of your students’ Kattis scores. These scores are listed in non-increasing order, and each score is an integer between 1 and 10000, inclusive.

Output
Output two lines. On the first line, print your university’s current Kattis group score. On the second line, print the average of the gi values as described above. Your output will be considered correct if each value is within 10−6 of the corresponding official value. (Note that although Kattis reports every score with exactly one digit after the decimal point, you should not round your answers.)

Sample Input 1	Sample Output 1
3
500
120
75
128.8
89.06666666666666
Sample Input 2	Sample Output 2
2
100
100
36.0
20.0
**/
import java.util.Scanner;

public class ProblemJ {
	private Scanner scan;
	private int numOfStudent;

	ProblemJ(){
		scan = new Scanner(System.in);
	}
	
	public static void main(String args[]) {
		ProblemJ test = new ProblemJ();
		test.getStudent();
		test.calculate();
	}
	
	private void getStudent() {
		numOfStudent = scan.nextInt();
	}
	
	private void calculate() {
		int[] studentValues = new int[numOfStudent];
		for(int i = 0; i < numOfStudent; i++) {
			studentValues[i] = scan.nextInt();
		}
		System.out.println(findSum(studentValues));
		System.out.println(findNewSum(studentValues));
	}
	
	private double findSum(int[] values) {
		double sum = 0;
		boolean part2 = false;
		
		for(int i= 0; i < numOfStudent; i++) {
			if(values[i] < 0)
				part2 = true;
			if(!part2)
				sum+= Math.pow(.8, i) * values[i];
			else {
				if(values[i] >= 0) {
					sum+= Math.pow(.8, i-1) * values[i];
				}
			}
		}
		sum /= 5;
		return sum;
	}
	
	private double findNewSum(int [] values) {
		double sum = 0;
		int [] arrayCopy = values.clone();
		for(int i = 0; i < numOfStudent; i++) {
			arrayCopy[i] = -1;
			sum += findSum(arrayCopy);
			arrayCopy[i] = values[i];
		}
		sum/= numOfStudent;
		return sum;
		
	}
}
