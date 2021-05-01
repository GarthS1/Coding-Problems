/**
War is a classic and very simple two-player card game played with a standard 52-card deck. 
The deck is shuffled and split into two equal-sized piles, one for each player, with cards face down. 
To play, both players turn over the top card of their draw pile. 
The player whose card rank is higher (suit doesn’t matter) wins both cards. 
In our variant of War for this problem, when a tie in rank occurs, both players take their one card back.
1 The ranks of the cards, from highest to lowest, are [A]ce, [K]ing, [Q]ueen, [J]ack, [T]en, 9, 8, 7, 6, 5, 4, 3, and 
2. After the card battle is settled, both players would turn over the next card in their draw piles for the following battle, 
and so forth. Also in our variant, after the initial 26-card piles have been exhausted, 
both players count the cards they have won, and the player with the most cards wins the game.

After a few games, Yraglac was bored out of his mind because the game, clearly, has absolutely no element of strategy whatsoever! At the beginning of the next game, when his friend wasn’t looking, he decided to take a peek through both piles of cards and wondered, “How many cards could I win if I could rearrange my own pile of cards?” Now there’s a challenge worthy of his mighty brain!

It turned out for Yraglac that cheating at War was a little harder than he initially thought. Can you help by writing a program that, given lists of the cards in Yraglac’s opponent’s pile and in his own pile, determines the most cards Yraglac can win if he could rearrange the cards in his draw pile in any order he likes?

Input
The first line of the input contains a single integer, 1≤N≤50, indicating the number of games of war to be played. Following, there are two lines of input for each game. The first of the two lines contains a 26-character string indicating the cards in the opponent’s pile, in the order that they will be played. Non-numeric cards are encoded with a single capital letter as described in the problem statement. The second of the two lines indicates the 26 cards in Yraglac’s pile. Both piles together will always form the set of cards in a standard 52-card deck.

Output
For each game of War described in the input, output on a single line the highest number of cards Yraglac can win if he were able to rearrange his pile of cards to be played in any order.

Sample Input 1	Sample Output 1
3
3J665T72457Q2J3AA9K3TK7T5A
296K979725JQA3K686679KT338
AKQJT98765432AKQJT98765432
AKQJT98765432AKQJT98765432
AAAAKKKKQQQQJJJJTTTT999988
22223333444455556666777788
42
48
2
**/
import java.util.*;

public class ProblemC {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numGames = scan.nextInt();
		int[] yraglacHand = new int [26];
		int[] friendsHand = new int [26];
		
		for( int round = 0; round < numGames; round++ ) {
			char[] friendsHandC = scan.next().toCharArray();
			char[] yraglacHandC = scan.next().toCharArray();
			int maxCards = 0;
			
			for(int i = 0; i < 26; i++) {
				if( friendsHandC[i] == 'T') {
					friendsHand[i] = 10;
				}
				else if( friendsHandC[i] == 'J') {
					friendsHand[i] = 11;
				}
				else if( friendsHandC[i] == 'Q') {
					friendsHand[i] = 12;
				}
				else if( friendsHandC[i] == 'K') {
					friendsHand[i] = 13;
				}
				else if( friendsHandC[i] == 'A') {
					friendsHand[i] = 14;
				}
				else {
					friendsHand[i] = friendsHandC[i] - 48; 
				}
			}
			
			for(int i = 0; i < 26; i++) {
				if( yraglacHandC[i] == 'T') {
					yraglacHand[i] = 10;
				}
				else if( yraglacHandC[i] == 'J') {
					yraglacHand[i] = 11;
				}
				else if( yraglacHandC[i] == 'Q') {
					yraglacHand[i] = 12;
				}
				else if( yraglacHandC[i] == 'K') {
					yraglacHand[i] = 13;
				}
				else if( yraglacHandC[i] == 'A') {
					yraglacHand[i] = 14;
				}
				else {
					yraglacHand[i] = yraglacHandC[i] - 48; 
				}
			}
			
			sort(friendsHand);
			sort(yraglacHand);
			
			for(int i = 0; i < 26; i++) {
				int cards = 0;
				for(int j = 0; j < 26; j++) {
					if(yraglacHand[(i+j) % 26 ] > friendsHand[j] ) {
						cards += 2;
					}
					else if( yraglacHand[(i+j) % 26 ] == friendsHand[j]) {
						cards += 1;
					}
				}
				if( cards > maxCards)
					maxCards = cards;
			}
			/*ArrayList<Integer> possibleTies = new ArrayList<Integer>();
			for( int i = 0; i < 26 ; i++ ) {
				boolean cardFound = false;
				for( int j = 0; j < 26 ; j++) {
					if(yraglacHand[j] > friendsHand[i] ) {
						yraglacHand[j] = 0;
						maxCards += 2;
						cardFound = true;
						break;
					}
				}
				if(!cardFound) {
					possibleTies.add(i);
				}
			}
			
			for( int i = 0; i < possibleTies.size(); i++) {
				for( int j = 0; j < 26; j++) {
					if(yraglacHand[j] == friendsHand[possibleTies.get(i)] ) {
						yraglacHand[j] = 0;
						maxCards += 1;
						break;
					}
			}
		}*/
			System.out.println(maxCards);
		}
	}

	private static void sort(int[] hand) {
		
		for( int i = 0; i < 26; i++) {
			int big = i;
			for( int j = i + 1; j < 26; j++) {
				if( hand[j] > hand[big] ) {
					big = j;
				}
			}
			int temp = hand[i];
			hand[i] = hand[big];
			hand[big] = temp;
		}
	}
}
