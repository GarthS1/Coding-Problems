/**
Yraglac loves number sequences. One of his favourites is the classic Fibonacci numbers. 
He likes to set the base case as F0=F1=1 and Fn=Fn−1+Fn−2.

Now, one can only list out the same numbers so many times before getting bored, so Yraglac came up with a new game: 
if he took the sequence of Fibonacci numbers modulo some k, what is the index of the first number in the new sequence that gets repeated? Yraglac only considers numbers in the sequence starting from n=2 since F0=F1 is boring.

Can you write a program to help Yraglac find the answer?

Input
The first line contains a single integer 1≤Q≤500, the number of queries to follow.

The next Q lines each contain a single integer 2≤k≤1000, the modulo to be used for the query. It is guaranteed that some number in the sequence will be repeated.

Output
For each query, output the n such that Fn is the first number in the sequence of Fibonacci numbers modulo k that has a repeat at some point in the sequence.

Sample Input 1	Sample Output 1
3
4
13
22
4
5
8
**/
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemG {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int queries  = scan.nextInt();
		for( int i = 0; i < queries; i++ ) {
			int mod = scan.nextInt();
			System.out.println(findNextRepeat(mod));
		}
	}

	private static int findNextRepeat(int mod) {
		
		int f0 = 1;
		int f1 = 1;
		boolean noRepeatFound = true;
		ArrayList<Integer> newFib = new ArrayList<Integer>();
		int index = 2;

		while( noRepeatFound ) {
			int temp = f1;
			f1 = (f1 + f0) % mod;
			f0 = temp;
			for( int i = 0 ; i < newFib.size(); i++ ) {
				if( (f1 ) == newFib.get(i)) {
					noRepeatFound = false;
					index += i;
					return index;
				}
			}
			newFib.add( f1);
		}
		return -1;
	}
}
