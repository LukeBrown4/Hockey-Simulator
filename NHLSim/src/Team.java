import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Team {
	int playerID = 10000;
	String Name;
	int teamStrength;
	int offenseStrength;
	int defenseStrength;
	int physicalStrength;
	int wins;
	int losses;
	int ot;
	int points;
	String goalsong;
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Player> healthyPlayers = new ArrayList<Player>();
	ArrayList<Player> starters = new ArrayList<Player>();
	ArrayList<Player> reserves = new ArrayList<Player>();
	
	Goalie g;

	Team(String n){
		this.Name = n;
	}
	
	Team(String n, int w, int l, int o, String g){
		this.Name = n;
		this.wins = w;
		this.losses = l;
		this.ot = o;
		this.goalsong = g;
	}
	
	void addPlayer(Player p){
		players.add(p);
	}
	
	void addGoalie(Goalie g){
		this.g = g;
	}
	
	void win() {
		wins = wins + 1;
	}
	
	void lose() {
		losses = losses + 1;
	}
	
	void otLoss() {
		ot = ot + 1;
	}
	
	String getName() {
		return Name;
	}
	
	void pts() {
		points =  (wins * 2) + ot;
	}
	
	void str() {
		teamStrength = 0;
		for(int i = 0; i < players.size(); i++) {
			teamStrength = teamStrength + players.get(i).offense + players.get(i).defense + players.get(i).passing;
		}
		teamStrength = teamStrength + g.offense;
	}
	
	void offStr() {
		offenseStrength = 0;
		for(int i = 0; i < players.size(); i++) {
			offenseStrength = offenseStrength + players.get(i).offense + players.get(i).passing;
		}
		offenseStrength = offenseStrength/2;
	}
	
	void defStr() {
		defenseStrength = 0;
		for(int i = 0; i < players.size(); i++) {
			defenseStrength = defenseStrength + players.get(i).defense;
		}
	}
	
	void phyStr() {
		physicalStrength = 0;
		for(int i = 0; i < players.size(); i++) {
			physicalStrength = physicalStrength + players.get(i).physical;
		}
	}
	
	int gamesPlayed() {
		return wins + losses +ot;
	}
	
	void health() {
		healthyPlayers.clear();
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).injuryLength == 0) {
				healthyPlayers.add(players.get(i));
			} else {
				
			}
		}
	}
	
	void getStarters() {
		starters.clear();
		int s=0;
		for(int i = 0; i < healthyPlayers.size(); i++) {
			if(s < 18) {
				starters.add(healthyPlayers.get(i));
				s++;
			} 
		}
	}
	
	void saveTeam() {
		ArrayList<Player> injuredPlayers = new ArrayList<Player>();
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).injuryLength > 0) {
				injuredPlayers.add(players.get(i));
			}
		}
		players.clear();
		for(int i = 0; i < injuredPlayers.size(); i++) {
			players.add(injuredPlayers.get(i));
			//System.out.println(injuredPlayers.get(i).getName());
		}
		
		for(int i = 0; i < starters.size(); i++) {
			players.add(starters.get(i));
			//System.out.println(starters.get(i).getName());
		}
		
		for(int i = 0; i < reserves.size(); i++) {
			players.add(reserves.get(i));
			//System.out.println(reserves.get(i).getName());
		}
	}

	void readFile(String fileName) {
		File file = new File(fileName);
		Scanner inFile;
		try {
			inFile = new Scanner(file);
			
			while(inFile.hasNextLine()){
				String fn = inFile.next();
				String ln = inFile.next();
				int n = inFile.nextInt();
				int p = inFile.nextInt();
				int d = inFile.nextInt();
				int h = inFile.nextInt();
				int isGoalie = inFile.nextInt();
				if (isGoalie == 1) {
					addGoalie(new Goalie(fn, ln, n, p, d, h, 1));
				} else {
				players.add(new Player(fn, ln, n, p, d, h, 0));
				}
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}