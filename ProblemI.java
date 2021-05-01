/**
Everyday Yraglac relies on his city’s local transit system to get to and from campus. Since this is routine for him, he’s memorized exactly what time will let him leave his house and make it in time to his first morning class. Of course transit being transit, they decided to change around the schedules for some of the routes Yraglac takes.

Yraglac leaves his house everyday at time s to make it to his first class which starts at time t. To get there, he takes n transit routes, one after the other. When transferring from one transit route to another, going from his house to the first transit stop, and going from the last transit stop to his class, he must walk for di time. Yraglac rides the i−th bus for bi time before getting off and walking to the i+1-th bus stop. Last but not least, each bus only comes at an interval of every ci. The first one always leaving at time 0.

Given the new schedules for the routes Yraglac takes, can you find out if he can make it to class on time?

Input
The first line contains 3 space separated integers, 0≤s≤t≤1000, and 1≤n≤20.

The second line contains n+1 space separated integers di (0≤di≤1000) denoting the time it takes to walk from the i-th bus’ drop-off point to the i+1-th bus stop. Note that d0 is the time it takes to walk from Yraglac’s house to the first bus stop, and dn is the time it takes to walk from the last bus’ drop-off point to his class.

The third line contains n space separated integers bi (1≤bi≤1000) denoting the amount of time Yraglac rides the i-th bus.

The fourth line contains n space separated integers ci (1≤ci≤1000) denoting the intervals the i-th bus arrives.

Output
Output “yes” if Yraglac will be able to get to class in time, and “no” otherwise.

Sample Input 1	Sample Output 1
0 20 2
2 2 2
5 5
3 5
yes
Sample Input 2	Sample Output 2
0 10 1
3 3
1
8
no
**/
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemI {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int currentTime = scan.nextInt();
		int classTime = scan.nextInt();
		int numTranistRoutes = scan.nextInt();
		ArrayList<Integer> walkTimes = new ArrayList<Integer>();
		ArrayList<Integer> busTimes = new ArrayList<Integer>();
		ArrayList<Integer> arrivalTimes = new ArrayList<Integer>();
		
		for( int i = 0; i <= numTranistRoutes ; i++ ) {
			 walkTimes.add(scan.nextInt());
		}
		
		for( int i = 0; i < numTranistRoutes ; i++ ) {
			busTimes.add(scan.nextInt());
		}
		
		for( int i = 0; i < numTranistRoutes ; i++ ) {
			arrivalTimes.add(scan.nextInt());
		}
		
		int index = 0;
		while(index < numTranistRoutes ) {
			currentTime += walkTimes.get(index);
			for(; ( currentTime % arrivalTimes.get(index) ) != 0; currentTime++) {}
			currentTime += busTimes.get(index);
			index++;
		}
		
		currentTime += walkTimes.get(index);
		
		if( classTime >= currentTime ) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}
	}
}
