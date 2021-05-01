
public class Player implements Comparable<Player> {
	private String name;
	int points; 
	
	public Player(String name) {
		this.setName(name);
		points = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void increment(int points2) {
		points += points2;
	}
	
	public int getPoints() {
		return points;
	}

	@Override
	public int compareTo(Player o) {
		return name.compareTo(o.getName());
	}
	
	@Override 
	public String toString() {
		return getName();
	}
	
	
}
