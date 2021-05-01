import java.util.*;

// Krushal's Algorithm 
public class BasketBall {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int participants = scanner.nextInt();
		int pointToWin = scanner.nextInt();
		int lines = scanner.nextInt();
		boolean winner = false;
		ArrayList<Player> players = new ArrayList<Player>(); 
		
		for(int i = 0; i < participants; i++) {
			players.add(new Player(scanner.next()));
		}
		
		Collections.sort(players);
		
		for(int i = 0; i < lines; i++) {
			String name = scanner.next();
			int points = scanner.nextInt();
			for(int j = 0; j < participants; j++) {
				if(players.get(j).getName().compareTo(name)== 0) {
					players.get(j).increment(points);
				}
			}
		}
		
		for(int i = 0; i < participants; i++) {
			if(players.get(i).getPoints() >= pointToWin) {
				System.out.println(players.get(i).getName() + " wins!");
				winner = true;
			}
		}
		if(!winner) {
			System.out.println("No winner!");
		}
	}

}
