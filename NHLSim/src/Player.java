
public class Player {
	String firstName;
	String lastName;
	int offense;
	int passing;
	int defense;
	int physical;
	int isGoalie;
	int seasonGoals;
	int seasonAssists;
	int seasonPoints;
	int allTimeGoals;
	int allTimeAssits;
	int allTimePoints;
	int gamesPlayed;
	int injuryLength;
	
	Player(String fn, String ln, int off, int pass, int def, int phy, int isGoalie){
		this.firstName = fn;
		this.lastName = ln;
		this.offense = off;
		this.passing = pass;
		this.defense = def;
		this.physical = phy;
		this.isGoalie = isGoalie;
		this.gamesPlayed = 0;
		this.injuryLength = 0;
	}
	
	Player(String fn, String ln, int off, int pass, int def, int phy, int isGoalie, int g, int a, int p, int gp, int injL){
		this.firstName = fn;
		this.lastName = ln;
		this.offense = off;
		this.passing = pass;
		this.defense = def;
		this.physical = phy;
		this.isGoalie = isGoalie;
		this.seasonGoals = g;
		this.seasonAssists = a;
		this.seasonPoints = p;
		this.gamesPlayed = gp;
		this.injuryLength = injL;
	}

	void scoresGoal(){
		seasonGoals = seasonGoals + 1;
		seasonPoints = seasonGoals + seasonAssists;
	}
	
	void assistsGoal(){
		seasonAssists = seasonAssists + 1;
		seasonPoints = seasonGoals + seasonAssists;
	}
	
	String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	void pts() {
		seasonPoints = seasonGoals + seasonAssists;
	}
	
	void play() {
		gamesPlayed++;	
	}
	void healing() {
		if (injuryLength > 0) {
			injuryLength--;
		} 
	}
	
	double ovrRating() {
		return roundItTwo((double)((this.defense * 1.25)  + (this.offense * 1.5) + (this.passing * 1.5) + (this.physical * .5))/3);
	}
	
	public static double roundItTwo(double value) {
	    double scale = Math.pow(10, 1);
	    return Math.round(value * scale) / scale;
	}
	

}