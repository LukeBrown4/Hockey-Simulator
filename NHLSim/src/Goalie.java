

public class Goalie extends Player {
	
	int shotsFaced;
	int goalsAllowed;
	int gamesPlayed;

	Goalie(String fn, String ln, int num, int pass, int def, int phy, int isGoalie) {
		super(fn, ln, num, pass, def, phy, 1);
	}
	
	Goalie(String fn, String ln, int num, int pass, int def, int phy,int isGoalie, int g, int a, int p, int gp, int injL){
		super(fn, ln, num, pass, def, phy, isGoalie, g, a, p, gp, injL);
	}
	
	void pts() {
		
	}
	
	

}
