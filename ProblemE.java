/**
n the past century, a new style of music composition has emerged. Unlike more traditional methods based on keys and chords, 
the technique known as dodecaphony focuses on using all twelve notes equally. As a quick reminder, the twelve notes, 
in ascending order are,

C,C♯,D,D♯,E,F,F♯,G,G♯,A,A♯,B
The sequence then wraps around so that the next note after B is C and so on. For this problem, 
we’ll ignore equivalent notations that use flats, double sharps, or double flats.

Each successive note above is considered one semitone away from the next. Now in our simplified version of dodecaphony, 
a melody is a permutation of the previous melody by one of three relations.

First, we have transposition, where each note has been shifted up by n semitones.
A retrograde is when the notes have their order reversed. Finally we have inversion about the first note of the melody. 
With inversions, the first note doesn’t change, but the rest of the notes are inverted such that the the interval (number of semitones) 
between that note and the first note is negated.

For example, if F is our first note, and we want to invert an A♯, which is 5 semitones higher, 
the inverted note would be a C, which is 5 semitones lower. Note that the first note in an inverted melody is always 
just the first note of the original melody.

Given two melodies, can you tell what relation the second has to the first?

Input
The first line contains a single integer 2≤l≤50, the number of notes in each melody.

The next two lines each contain l space separated notes. It is guaranteed that each note will be one of the twelve listed above.

Output
Output on a single line “Transposition” if the second melody is a transposition of the first, “Retrograde” if the second melody is the first melody reversed, “Inversion” if the second melody is an inversion of the first melody, else “Nonsense” if it is none of the other cases.

If the second melody satisfies more than one relation, output the first valid relation in the order of “Transposition”, 
then “Retrograde”, then “Inversion”.

Sample Input 1	Sample Output 1
3
C E G
D F# A
Transposition
Sample Input 2	Sample Output 2
7
C C G G A A G
C C F F D# D# F
Inversion
Sample Input 3	Sample Output 3
7
A B C D E F G
G F E D C B A
Retrograde
Sample Input 4	Sample Output 4
5
C D E F G
C C C C C
Nonsense
**/
import java.util.Scanner;

public class ProblemE {

	static public int[] convertNotes(int length, String[] src) {
		int[] convert = new int[length];
		
		for(int i = 0; i < length; i++) {
			if(src[i].compareTo("C") == 0)
				convert[i] = 1;
			else if(src[i].compareTo("C#") == 0)
				convert[i] = 2;
			else if(src[i].compareTo("D") == 0)
				convert[i] = 3;
			else if(src[i].compareTo("D#") == 0)
				convert[i] = 4;
			else if(src[i].compareTo("E") == 0)
				convert[i] = 5;
			else if(src[i].compareTo("F") == 0)
				convert[i] = 6;
			else if(src[i].compareTo("F#") == 0)
				convert[i] = 7;
			else if(src[i].compareTo("G") == 0)
				convert[i] = 8;
			else if(src[i].compareTo("G#") == 0)
				convert[i] = 9;
			else if(src[i].compareTo("A") == 0)
				convert[i] = 10;
			else if(src[i].compareTo("A#") == 0)
				convert[i] = 11;
			else if(src[i].compareTo("B") == 0)
				convert[i] = 12;
		}
		return convert;
	}
	
	static public boolean checkTransposition(int length, int[] m1, int[] m2) {
		int range = m2[0] - m1[0];
		
		for(int i = 1; i < length; i++) {
			int noteRange = m2[i] - m1[i];
			if(noteRange != range)
				return false;
		}
		return true;
	}
	
	static public boolean checkInversion(int length, int[] m1, int[] m2) {
		
		for(int i = 1; i < length; i++) {
			int difference = m1[i] - m1[0];
			if(difference <= 0 )
				difference += 12;
			int difference2 = m2[0] - m2[i];
			if(difference2 <= 0 )
				difference2 += 12;
			
			if(difference2 != difference)
				return false;
		}
		return true;
	}
	
	static public boolean checkRetrograde(int length, int[]m1, int[]m2) {
		for(int i = 0; i < length ; i++) {
			if(m1[i] != m2[length - 1 - i])
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int melody = in.nextInt();
		
		String[] firstMelody = new String[melody];
		String[] secondMelody = new String[melody];
		
		for(int i = 0; i < melody; i++)
		{
		    firstMelody[i] = in.next();
		}

		for(int i = 0; i < melody; i++)
		{
		    secondMelody[i] = in.next();
		}

		int[] melodyOne = convertNotes(melody,firstMelody);
		int[] melodyTwo = convertNotes(melody,secondMelody);
		
		
		if(checkTransposition(melody,melodyOne, melodyTwo))
			System.out.println("Transposition");
		else if(checkRetrograde(melody,melodyOne,melodyTwo))
			System.out.println("Retrograde");
		else if(checkInversion(melody,melodyOne, melodyTwo))
			System.out.println("Inversion");
		else
			System.out.println("Nonsense");
		
	}
}
