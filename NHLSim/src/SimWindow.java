import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GraphicsDevice;

import javax.swing.JTextPane;
import java.awt.List;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.awt.Toolkit;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Label;
import javax.swing.JLayeredPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;
import javax.swing.JButton;

public class SimWindow extends JFrame {
	public static File savedTeams = new File("savedTeams.txt");
	static ArrayList<Team> teams = new ArrayList<Team>();
	private static JPanel contentPane;
	public static JTextPane textPane;
	public static List standings;
	public static List pointsList;
	public static List goalsList;
	public static List assistList;
	public static List injuryList;
	public static List goalsAgainstList;
	public static List shotsFacedList;
	public static List gamesPlayedList;
	public static List record;
	public static List strengthIndex;
	public static List offStrengthIndex;
	public static List defStrengthIndex;
	public static ArrayList<String>  events = new ArrayList<String> ();
	public static boolean areWeSimming = false;
	public static int eventCounter = 0;
	public static JLabel hockeyPuck;
	public static int homeScoreEvent = 0;
	public static int awayScoreEvent = 0;
	public static JLabel homeForEvents;
	public static JLabel awayForEvents;
	public static JLabel goalLight;
	public static Choice homeChoice;
	public static Choice awayChoice;
	GraphicsDevice gDevice;

	
	/**
	 * TO-DO
	 * 
	 * Reserve List
	 * 
	 */
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loadTeams();
					SimWindow frame = new SimWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimWindow() {
		
		
		setTitle("Hockey Sim");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 980);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40,46,50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		homeChoice = new Choice();
		homeChoice.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		homeChoice.setBounds(20, 124, 317, 26);
		contentPane.add(homeChoice);
		
		awayChoice = new Choice();
		awayChoice.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		awayChoice.setBounds(460, 124, 317, 26);
		contentPane.add(awayChoice);
		
		for(int i = 0; i < teams.size(); i++) {
			homeChoice.add(teams.get(i).getName());
			awayChoice.add(teams.get(i).getName());
		}

		
		Color col = new Color(51,54,57);
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(col);
		textPane.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		textPane.setBounds(20, 156, 757, 733);
		contentPane.add(textPane);
		
		standings = new List();
		standings.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		standings.setForeground(Color.WHITE);
		standings.setBounds(1363, 89, 315, 252);
		standings.setBackground(col);
		contentPane.add(standings);
		
		record = new List();
		record.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		record.setForeground(Color.WHITE);
		record.setBackground(col);
		record.setBounds(1684, 89, 67, 252);
		contentPane.add(record);
		
		strengthIndex = new List();
		strengthIndex.setForeground(Color.WHITE);
		strengthIndex.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		strengthIndex.setBackground(new Color(51, 54, 57));
		strengthIndex.setBounds(1831, 89, 37, 252);
		contentPane.add(strengthIndex);
		
		offStrengthIndex = new List();
		offStrengthIndex.setForeground(Color.WHITE);
		offStrengthIndex.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		offStrengthIndex.setBackground(col);
		offStrengthIndex.setBounds(1757, 89, 31, 252);
		contentPane.add(offStrengthIndex);
		
		defStrengthIndex = new List();
		defStrengthIndex.setForeground(Color.WHITE);
		defStrengthIndex.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		defStrengthIndex.setBackground(new Color(51, 54, 57));
		defStrengthIndex.setBounds(1794, 89, 31, 252);
		contentPane.add(defStrengthIndex);
		
		JLabel defRank = new JLabel("DEF");
		defRank.setHorizontalAlignment(SwingConstants.CENTER);
		defRank.setForeground(Color.WHITE);
		defRank.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		defRank.setBounds(1788, 48, 47, 35);
		contentPane.add(defRank);
		
		
		Button playSeason = new Button("Simulate Season");
		playSeason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int p = 0; p < 1; p++) {
					for(int i = 0; i < teams.size() ; i++) {
						for(int j = i + 1; j < teams.size(); j++) {
							simGame(teams.get(i), teams.get(j));
						}
					}
				}
			}
		});
		playSeason.setFont(new Font("Arial", Font.BOLD, 15));
		playSeason.setBackground(Color.WHITE);
		playSeason.setBounds(10, 895, 179, 35);
		contentPane.add(playSeason);
		
		JLabel homeLabel = new JLabel("Home");
		homeLabel.setForeground(Color.WHITE);
		homeLabel.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setBounds(20, 70, 317, 48);
		contentPane.add(homeLabel);
		
		JLabel awayLabel = new JLabel("Away");
		awayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		awayLabel.setForeground(Color.WHITE);
		awayLabel.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		awayLabel.setBounds(460, 70, 315, 48);
		contentPane.add(awayLabel);
		
		JLabel winLoss = new JLabel("W/L/OT");
		winLoss.setHorizontalAlignment(SwingConstants.CENTER);
		winLoss.setForeground(Color.WHITE);
		winLoss.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		winLoss.setBounds(1685, 50, 59, 29);
		contentPane.add(winLoss);
		
		JLabel offRank = new JLabel("OFF");
		offRank.setHorizontalAlignment(SwingConstants.CENTER);
		offRank.setForeground(Color.WHITE);
		offRank.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		offRank.setBounds(1757, 48, 31, 35);
		contentPane.add(offRank);
		
		Button saveSeason = new Button("Save Season");
		saveSeason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveTheSeason();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		saveSeason.setFont(new Font("Arial", Font.BOLD, 15));
		saveSeason.setBackground(Color.WHITE);
		saveSeason.setBounds(195, 895, 179, 35);
		contentPane.add(saveSeason);
		
		Button loadSeason = new Button("Load Season");
		loadSeason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadTheSeason();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loadSeason.setFont(new Font("Arial", Font.BOLD, 15));
		loadSeason.setBackground(Color.WHITE);
		loadSeason.setBounds(418, 895, 179, 35);
		contentPane.add(loadSeason);
		
		Choice scoringLeaderBar = new Choice();
		scoringLeaderBar.add("All Teams");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		tabbedPane.setBounds(787, 347, 1107, 583);
		JPanel leagueScoringPanel = new JPanel();
		tabbedPane.addTab("Skater Stats", null, leagueScoringPanel, null);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		leagueScoringPanel.setLayout(null);
		leagueScoringPanel.setBackground(new Color(51,54,57));
		
		contentPane.add(tabbedPane);
		for(int i = 0; i < teams.size(); i++) {
			scoringLeaderBar.add(teams.get(i).getName());
		}
		
		scoringLeaderBar.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		scoringLeaderBar.setBounds(10, 6, 787, 26);
		leagueScoringPanel.add(scoringLeaderBar);
		
		

		pointsList = new List();
		pointsList.setBounds(10, 45, 337, 497);
		leagueScoringPanel.add(pointsList);
		pointsList.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		pointsList.setForeground(Color.WHITE);
		pointsList.setBackground(col);
		
		goalsList = new List();
		goalsList.setBounds(377, 45, 337, 497);
		leagueScoringPanel.add(goalsList);
		goalsList.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		goalsList.setForeground(Color.WHITE);
		goalsList.setBackground(col);
		
		assistList = new List();
		assistList.setBounds(746, 45, 337, 497);
		leagueScoringPanel.add(assistList);
		assistList.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		assistList.setForeground(Color.WHITE);
		assistList.setBackground(col);
		

		
		Button loadScorers = new Button("Go");
		loadScorers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scoringLeaderBar.getSelectedIndex() == 0) {
					updateGoals();
					updateAssists();
					updatePoints();
				} else {
					updatePointsForTeam(scoringLeaderBar.getSelectedIndex() - 1);
				}
			}
		});
		loadScorers.setActionCommand("");
		loadScorers.setFont(new Font("Arial", Font.BOLD, 15));
		loadScorers.setBackground(Color.WHITE);
		loadScorers.setBounds(803, 6, 280, 26);
		leagueScoringPanel.add(loadScorers);
		
		JPanel leagueGoaliePanel = new JPanel();
		leagueGoaliePanel.setLayout(null);
		leagueGoaliePanel.setBackground(new Color(51, 54, 57));
		tabbedPane.addTab("Goalie Stats", null, leagueGoaliePanel, null);
		
		goalsAgainstList = new List();
		goalsAgainstList.setForeground(Color.WHITE);
		goalsAgainstList.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		goalsAgainstList.setBackground(new Color(51, 54, 57));
		goalsAgainstList.setBounds(10, 43, 337, 499);
		leagueGoaliePanel.add(goalsAgainstList);
		
		shotsFacedList = new List();
		shotsFacedList.setForeground(Color.WHITE);
		shotsFacedList.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		shotsFacedList.setBackground(new Color(51, 54, 57));
		shotsFacedList.setBounds(383, 43, 337, 499);
		leagueGoaliePanel.add(shotsFacedList);
		
	    gamesPlayedList = new List();
	    gamesPlayedList.setForeground(Color.WHITE);
	    gamesPlayedList.setFont(new Font("Bahnschrift", Font.BOLD, 15));
	    gamesPlayedList.setBackground(new Color(51, 54, 57));
	    gamesPlayedList.setBounds(755, 43, 337, 499);
		leagueGoaliePanel.add(gamesPlayedList);
		
		Choice goalieBar = new Choice();
		goalieBar.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		goalieBar.setBounds(10, 6, 796, 26);
		goalieBar.addItem("All Teams");
		for(int i = 0; i < teams.size(); i++) {
			goalieBar.add(teams.get(i).Name);
		}
		leagueGoaliePanel.add(goalieBar);
		
		Button loadGoalie = new Button("Go");
		loadGoalie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGoalieStats();
			}
		});
		loadGoalie.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		loadGoalie.setBackground(Color.WHITE);
		loadGoalie.setActionCommand("");
		loadGoalie.setBounds(812, 6, 280, 26);
		leagueGoaliePanel.add(loadGoalie);
		
		JPanel teamExplorePanel = new JPanel();
		teamExplorePanel.setLayout(null);
		teamExplorePanel.setBackground(new Color(51,54,57));
		tabbedPane.addTab("Team Explorer", null, teamExplorePanel, null);
	
		
		JLabel teamName = new JLabel();
		teamName.setHorizontalAlignment(SwingConstants.CENTER);
		teamName.setForeground(Color.WHITE);
		teamName.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		teamName.setBounds(10, 38, 326, 69);
		teamExplorePanel.add(teamName);
		
		JLabel teamLogo = new JLabel("");
		teamLogo.setHorizontalAlignment(SwingConstants.CENTER);
		teamLogo.setForeground(Color.WHITE);
		teamLogo.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		teamLogo.setBounds(69, 134, 200, 200);
		teamExplorePanel.add(teamLogo);
		
		JLabel teamRecord = new JLabel("");
		teamRecord.setHorizontalAlignment(SwingConstants.CENTER);
		teamRecord.setForeground(Color.WHITE);
		teamRecord.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		teamRecord.setBounds(69, 345, 200, 48);
		teamExplorePanel.add(teamRecord);
		
		JLabel skatersLabel = new JLabel("Skaters:");
		skatersLabel.setHorizontalAlignment(SwingConstants.LEFT);
		skatersLabel.setForeground(Color.WHITE);
		skatersLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		skatersLabel.setBounds(346, 36, 195, 26);
		teamExplorePanel.add(skatersLabel);
		
		JLabel lblff = new JLabel("SHT");
		lblff.setHorizontalAlignment(SwingConstants.LEFT);
		lblff.setForeground(Color.WHITE);
		lblff.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblff.setBounds(626, 38, 30, 26);
		teamExplorePanel.add(lblff);
		
		JLabel goalieLabel = new JLabel("Goalies:");
		goalieLabel.setHorizontalAlignment(SwingConstants.LEFT);
		goalieLabel.setForeground(Color.WHITE);
		goalieLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		goalieLabel.setBounds(346, 471, 195, 26);
		teamExplorePanel.add(goalieLabel);
		
		List skaterNameList = new List();
		skaterNameList.setForeground(Color.WHITE);
		skaterNameList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		skaterNameList.setBackground(new Color(51, 54, 57));
		skaterNameList.setBounds(346, 67, 221, 398);
		teamExplorePanel.add(skaterNameList);
		
		
		List goalieList = new List();
		goalieList.setForeground(Color.WHITE);
		goalieList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		goalieList.setBackground(new Color(51, 54, 57));
		goalieList.setBounds(346, 503, 221, 39);
		teamExplorePanel.add(goalieList);
		
		List skaterOffList = new List();
		skaterOffList.setForeground(Color.WHITE);
		skaterOffList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		skaterOffList.setBackground(new Color(51, 54, 57));
		skaterOffList.setBounds(626, 67, 30, 398);
		teamExplorePanel.add(skaterOffList);
		
		List goalieOvrList = new List();
		goalieOvrList.setForeground(Color.WHITE);
		goalieOvrList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		goalieOvrList.setBackground(new Color(51, 54, 57));
		goalieOvrList.setBounds(573, 503, 43, 39);
		teamExplorePanel.add(goalieOvrList);
		
		
		Choice teamExploreBar = new Choice();
		teamExploreBar.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		teamExploreBar.setBounds(10, 6, 796, 26);
		teamExplorePanel.add(teamExploreBar);
		for(int i = 0; i < teams.size(); i++) {
			teamExploreBar.add(teams.get(i).getName());
		}
		
		
		
		Button exploreTeam = new Button("Go");
		exploreTeam.setFont(new Font("Arial", Font.BOLD, 15));
		exploreTeam.setBackground(Color.WHITE);
		exploreTeam.setActionCommand("");
		exploreTeam.setBounds(812, 6, 280, 26);
		teamExplorePanel.add(exploreTeam);
		
		List teamGlsList = new List();
		teamGlsList.setForeground(Color.WHITE);
		teamGlsList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		teamGlsList.setBackground(new Color(51, 54, 57));
		teamGlsList.setBounds(838, 67, 44, 398);
		teamExplorePanel.add(teamGlsList);
		
		List teamAssList = new List();
		teamAssList.setForeground(Color.WHITE);
		teamAssList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		teamAssList.setBackground(new Color(51, 54, 57));
		teamAssList.setBounds(888, 67, 44, 398);
		teamExplorePanel.add(teamAssList);
		
		List teamPtsList = new List();
		teamPtsList.setForeground(Color.WHITE);
		teamPtsList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		teamPtsList.setBackground(new Color(51, 54, 57));
		teamPtsList.setBounds(938, 67, 44, 398);
		teamExplorePanel.add(teamPtsList);
		
		JLabel glsLabel = new JLabel("G");
		glsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		glsLabel.setForeground(Color.WHITE);
		glsLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		glsLabel.setBounds(838, 40, 44, 26);
		teamExplorePanel.add(glsLabel);
		
		JLabel assLabel = new JLabel("A");
		assLabel.setHorizontalAlignment(SwingConstants.CENTER);
		assLabel.setForeground(Color.WHITE);
		assLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		assLabel.setBounds(888, 40, 44, 26);
		teamExplorePanel.add(assLabel);
		
		JLabel ptsLabel = new JLabel("P");
		ptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ptsLabel.setForeground(Color.WHITE);
		ptsLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		ptsLabel.setBounds(938, 40, 44, 26);
		teamExplorePanel.add(ptsLabel);
		
		List teamGaaList = new List();
		teamGaaList.setForeground(Color.WHITE);
		teamGaaList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		teamGaaList.setBackground(new Color(51, 54, 57));
		teamGaaList.setBounds(626, 503, 44, 39);
		teamExplorePanel.add(teamGaaList);
		
		List teamSvpList = new List();
		teamSvpList.setForeground(Color.WHITE);
		teamSvpList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		teamSvpList.setBackground(new Color(51, 54, 57));
		teamSvpList.setBounds(676, 503, 44, 39);
		teamExplorePanel.add(teamSvpList);
		
		List teamSoList = new List();
		teamSoList.setForeground(Color.WHITE);
		teamSoList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		teamSoList.setBackground(new Color(51, 54, 57));
		teamSoList.setBounds(726, 503, 44, 39);
		teamExplorePanel.add(teamSoList);
		
		JLabel lblOvr_1 = new JLabel("OVR");
		lblOvr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOvr_1.setForeground(Color.WHITE);
		lblOvr_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblOvr_1.setBounds(573, 473, 43, 26);
		teamExplorePanel.add(lblOvr_1);
		
		JLabel gaaLabel = new JLabel("GAA");
		gaaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gaaLabel.setForeground(Color.WHITE);
		gaaLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		gaaLabel.setBounds(626, 473, 47, 26);
		teamExplorePanel.add(gaaLabel);
		
		JLabel svpLabel = new JLabel("SV%");
		svpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		svpLabel.setForeground(Color.WHITE);
		svpLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		svpLabel.setBounds(676, 473, 44, 26);
		teamExplorePanel.add(svpLabel);
		
		JLabel soLabel = new JLabel("SO");
		soLabel.setHorizontalAlignment(SwingConstants.CENTER);
		soLabel.setForeground(Color.WHITE);
		soLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		soLabel.setBounds(730, 473, 40, 26);
		teamExplorePanel.add(soLabel);
		
		List performanceList = new List();
		performanceList.setForeground(Color.WHITE);
		performanceList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		performanceList.setBackground(new Color(51, 54, 57));
		performanceList.setBounds(988, 67, 88, 398);
		teamExplorePanel.add(performanceList);
		
		JLabel perfLabel = new JLabel("Score");
		perfLabel.setHorizontalAlignment(SwingConstants.CENTER);
		perfLabel.setForeground(Color.WHITE);
		perfLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		perfLabel.setBounds(989, 38, 87, 26);
		teamExplorePanel.add(perfLabel);
		
		List goaliePerformanceList = new List();
		goaliePerformanceList.setForeground(Color.WHITE);
		goaliePerformanceList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		goaliePerformanceList.setBackground(new Color(51, 54, 57));
		goaliePerformanceList.setBounds(776, 503, 88, 39);
		teamExplorePanel.add(goaliePerformanceList);
		
		JLabel perfLabel_1 = new JLabel("Score");
		perfLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		perfLabel_1.setForeground(Color.WHITE);
		perfLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		perfLabel_1.setBounds(776, 473, 87, 26);
		teamExplorePanel.add(perfLabel_1);
		
		List skaterDefList = new List();
		skaterDefList.setForeground(Color.WHITE);
		skaterDefList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		skaterDefList.setBackground(new Color(51, 54, 57));
		skaterDefList.setBounds(702, 67, 30, 398);
		teamExplorePanel.add(skaterDefList);
		
		JLabel lblDef = new JLabel("DEF");
		lblDef.setHorizontalAlignment(SwingConstants.LEFT);
		lblDef.setForeground(Color.WHITE);
		lblDef.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblDef.setBounds(702, 38, 30, 26);
		teamExplorePanel.add(lblDef);
		
		List skaterOvrList = new List();
		skaterOvrList.setForeground(Color.WHITE);
		skaterOvrList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		skaterOvrList.setBackground(new Color(51, 54, 57));
		skaterOvrList.setBounds(573, 67, 40, 398);
		teamExplorePanel.add(skaterOvrList);
		
		JLabel lblOvr = new JLabel("OVR");
		lblOvr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOvr.setForeground(Color.WHITE);
		lblOvr.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblOvr.setBounds(573, 38, 40, 26);
		teamExplorePanel.add(lblOvr);
	
		List skaterPassList = new List();
		skaterPassList.setForeground(Color.WHITE);
		skaterPassList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		skaterPassList.setBackground(new Color(51, 54, 57));
		skaterPassList.setBounds(662, 67, 30, 398);
		teamExplorePanel.add(skaterPassList);
		JLabel lblPly = new JLabel("PSS");
		lblPly.setHorizontalAlignment(SwingConstants.LEFT);
		lblPly.setForeground(Color.WHITE);
		lblPly.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblPly.setBounds(662, 38, 30, 26);
		teamExplorePanel.add(lblPly);
		
		List skaterPhyList = new List();
		skaterPhyList.setForeground(Color.WHITE);
		skaterPhyList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		skaterPhyList.setBackground(new Color(51, 54, 57));
		skaterPhyList.setBounds(738, 67, 30, 398);
		teamExplorePanel.add(skaterPhyList);
		
		JLabel lblPhy = new JLabel("PHY");
		lblPhy.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhy.setForeground(Color.WHITE);
		lblPhy.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblPhy.setBounds(739, 38, 30, 26);
		teamExplorePanel.add(lblPhy);
		
		List skaterGpList = new List();
		skaterGpList.setForeground(Color.WHITE);
		skaterGpList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		skaterGpList.setBackground(new Color(51, 54, 57));
		skaterGpList.setBounds(787, 67, 30, 398);
		teamExplorePanel.add(skaterGpList);
		
		JLabel lblGp = new JLabel("GP");
		lblGp.setHorizontalAlignment(SwingConstants.CENTER);
		lblGp.setForeground(Color.WHITE);
		lblGp.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblGp.setBounds(787, 38, 27, 26);
		teamExplorePanel.add(lblGp);
		
		JPanel leagueInjuryPanel = new JPanel();
		leagueInjuryPanel.setLayout(null);
		leagueInjuryPanel.setBackground(new Color(51, 54, 57));
		tabbedPane.addTab("Injuries", null, leagueInjuryPanel, null);
		
		Choice injuryBar = new Choice();
		injuryBar.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		injuryBar.setBounds(10, 6, 787, 26);
		leagueInjuryPanel.add(injuryBar);
		
		injuryBar.add("All Teams");
		
		for(int i = 0; i < teams.size(); i++) {
			injuryBar.add(teams.get(i).getName());
		}
		
		injuryList = new List();
		injuryList.setForeground(Color.WHITE);
		injuryList.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		injuryList.setBackground(new Color(51, 54, 57));
		injuryList.setBounds(10, 45, 1073, 497);
		leagueInjuryPanel.add(injuryList);
		
		Button loadInjuries = new Button("Go");
		loadInjuries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(injuryBar.getSelectedIndex() == 0) {
					updateInjuries();
				} else {
					updateInjuriesForTeam(injuryBar.getSelectedIndex() - 1);
				}
			}
		});
		loadInjuries.setFont(new Font("Arial", Font.BOLD, 15));
		loadInjuries.setBackground(Color.WHITE);
		loadInjuries.setActionCommand("");
		loadInjuries.setBounds(803, 6, 280, 26);
		leagueInjuryPanel.add(loadInjuries);
		
		JPanel rosterPanel = new JPanel();
		rosterPanel.setLayout(null);
		rosterPanel.setBackground(new Color(51, 54, 57));
		tabbedPane.addTab("Roster Management", null, rosterPanel, null);
		
		Choice rosterBar = new Choice();
		rosterBar.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		rosterBar.setBounds(10, 6, 787, 26);
		rosterPanel.add(rosterBar);
		for(int i = 0; i < teams.size(); i++) {
			rosterBar.add(teams.get(i).getName());
		}
		
		List depthChartList = new List();
		depthChartList.setForeground(Color.WHITE);
		depthChartList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		depthChartList.setBackground(new Color(51, 54, 57));
		depthChartList.setBounds(10, 91, 250, 451);
		rosterPanel.add(depthChartList);
		
		List starterList = new List();
		starterList.setForeground(Color.WHITE);
		starterList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		starterList.setBackground(new Color(51, 54, 57));
		starterList.setBounds(360, 91, 250, 347);
		rosterPanel.add(starterList);
		
		List injuredList = new List();
		injuredList.setForeground(Color.WHITE);
		injuredList.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		injuredList.setBackground(new Color(51, 54, 57));
		injuredList.setBounds(632, 91, 451, 173);
		rosterPanel.add(injuredList);
		
		Button loadRosters = new Button("Go");
		loadRosters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teams.get(rosterBar.getSelectedIndex()).health();
				teams.get(rosterBar.getSelectedIndex()).getStarters();
				depthChartList.clear();
				starterList.clear();
				injuredList.clear();
				int start = 0;
				for(int i = 0; i < teams.get(rosterBar.getSelectedIndex()).players.size(); i++) {
					depthChartList.add(teams.get(rosterBar.getSelectedIndex()).players.get(i).ovrRating() + " " +teams.get(rosterBar.getSelectedIndex()).players.get(i).getName());
					if(teams.get(rosterBar.getSelectedIndex()).players.get(i).injuryLength == 0) {
						start++;
						if (start < 18 ) {
							starterList.add(teams.get(rosterBar.getSelectedIndex()).players.get(i).getName());
						} 
					} else {
						injuredList.add(teams.get(rosterBar.getSelectedIndex()).players.get(i).getName() + " - " + teams.get(rosterBar.getSelectedIndex()).players.get(i).injuryLength + " Games");
					}
					
				}
			
			}
		});
		loadRosters.setFont(new Font("Arial", Font.BOLD, 15));
		loadRosters.setBackground(Color.WHITE);
		loadRosters.setActionCommand("");
		loadRosters.setBounds(803, 6, 280, 26);
		rosterPanel.add(loadRosters);
		
		JButton btnMoveDownRoster = new JButton("/|\\");
		btnMoveDownRoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = depthChartList.getSelectedIndex();
				if (select > 0) {
					Player p = teams.get(rosterBar.getSelectedIndex()).players.get(select);
					Player p2 = teams.get(rosterBar.getSelectedIndex()).players.get(select-1);
					
					depthChartList.add(p.ovrRating() + " " + p.getName(), select);
					teams.get(rosterBar.getSelectedIndex()).players.add(select, p);
					
					depthChartList.add(p2.ovrRating() + " " + p2.getName(), select+1);
					teams.get(rosterBar.getSelectedIndex()).players.add(select+1, p2);
					
					depthChartList.delItem(select-1);
					teams.get(rosterBar.getSelectedIndex()).players.remove(select-1);
					
					depthChartList.delItem(select+1);
					teams.get(rosterBar.getSelectedIndex()).players.remove(select+1);
					
				}
			}
		});
		btnMoveDownRoster.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnMoveDownRoster.setBounds(275, 156, 67, 47);
		rosterPanel.add(btnMoveDownRoster);
		
		JButton btnMoveUpRoster = new JButton("\\|/");
		btnMoveUpRoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = depthChartList.getSelectedIndex();
				if (select < teams.get(rosterBar.getSelectedIndex()).players.size()-1) {
					Player p = teams.get(rosterBar.getSelectedIndex()).players.get(select);
					Player p2 = teams.get(rosterBar.getSelectedIndex()).players.get(select+1);
					
					depthChartList.add(p2.ovrRating() + " " + p2.getName(), select);
					teams.get(rosterBar.getSelectedIndex()).players.add(select, p2);
					
					depthChartList.add(p.ovrRating() + " " + p.getName(), select+1);
					teams.get(rosterBar.getSelectedIndex()).players.add(select+1, p);
					
					depthChartList.delItem(select+2);
					teams.get(rosterBar.getSelectedIndex()).players.remove(select+2);
					
					depthChartList.delItem(select+2);
					teams.get(rosterBar.getSelectedIndex()).players.remove(select+2);
					
				}
			}
		});
		btnMoveUpRoster.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnMoveUpRoster.setBounds(275, 231, 67, 47);
		rosterPanel.add(btnMoveUpRoster);
		
		JLabel StartersLabel = new JLabel("Depth Chart");
		StartersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		StartersLabel.setForeground(Color.WHITE);
		StartersLabel.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		StartersLabel.setBounds(10, 38, 250, 47);
		rosterPanel.add(StartersLabel);
		
		JLabel ReservesLabel = new JLabel("Starters");
		ReservesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ReservesLabel.setForeground(Color.WHITE);
		ReservesLabel.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		ReservesLabel.setBounds(360, 38, 250, 47);
		rosterPanel.add(ReservesLabel);
		
		JLabel IRLabel = new JLabel("Injured");
		IRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		IRLabel.setForeground(Color.WHITE);
		IRLabel.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		IRLabel.setBounds(632, 38, 451, 47);
		rosterPanel.add(IRLabel);
		
		JLabel goaliesLabel = new JLabel("Goalies");
		goaliesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goaliesLabel.setForeground(Color.WHITE);
		goaliesLabel.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		goaliesLabel.setBounds(632, 333, 244, 47);
		rosterPanel.add(goaliesLabel);
		
		List starterList_1_1_1 = new List();
		starterList_1_1_1.setForeground(Color.WHITE);
		starterList_1_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		starterList_1_1_1.setBackground(new Color(51, 54, 57));
		starterList_1_1_1.setBounds(632, 391, 179, 47);
		rosterPanel.add(starterList_1_1_1);
		
		JButton btnSwitchGoalies = new JButton("<>");
		btnSwitchGoalies.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnSwitchGoalies.setBounds(817, 391, 59, 47);
		rosterPanel.add(btnSwitchGoalies);
		
		JButton btnSaveTeam = new JButton("Save Team");
		btnSaveTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teams.get(rosterBar.getSelectedIndex()).saveTeam();
			}
		});
		btnSaveTeam.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		btnSaveTeam.setBounds(911, 391, 172, 47);
		rosterPanel.add(btnSaveTeam);
		
		
		exploreTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamName.setText(teamExploreBar.getSelectedItem());
				teamRecord.setText(teams.get(teamExploreBar.getSelectedIndex()).wins + "-" + teams.get(teamExploreBar.getSelectedIndex()).losses + "-" +teams.get(teamExploreBar.getSelectedIndex()).ot);
				teamLogo.setIcon(new ImageIcon(SimWindow.class.getResource("photos/" + teamExploreBar.getSelectedItem() + ".png")));
				skaterNameList.clear();
				skaterOffList.clear();
				skaterPassList.clear();
				skaterDefList.clear();
				skaterPhyList.clear();
				skaterGpList.clear();
				skaterOvrList.clear();
				goalieOvrList.clear();
				goalieList.clear();
				teamGlsList.clear();
				teamAssList.clear();
				teamPtsList.clear();
				teamGaaList.clear();
				teamSvpList.clear();
				teamSoList.clear();
				performanceList.clear();
				goaliePerformanceList.clear();
				goalieOvrList.add(teams.get(teamExploreBar.getSelectedIndex()).g.offense + "");
				goalieList.add(teams.get(teamExploreBar.getSelectedIndex()).g.getName());
				teamGaaList.add(roundIt(((double)teams.get(teamExploreBar.getSelectedIndex()).g.seasonGoals)/ (teams.get(teamExploreBar.getSelectedIndex()).gamesPlayed()))+ "");
				teamSvpList.add(roundIt(((double)teams.get(teamExploreBar.getSelectedIndex()).g.seasonAssists - (double)teams.get(teamExploreBar.getSelectedIndex()).g.seasonGoals)/(double)teams.get(teamExploreBar.getSelectedIndex()).g.seasonAssists) + "");
				teamSoList.add(teams.get(teamExploreBar.getSelectedIndex()).g.seasonPoints + "");

				
				double perfGScore = (((double)teams.get(teamExploreBar.getSelectedIndex()).g.seasonAssists)/ (teams.get(teamExploreBar.getSelectedIndex()).g.seasonGoals)) / (teams.get(teamExploreBar.getSelectedIndex()).g.offense+5);
				
				String perfGString = "Neutral";
				if(perfGScore > .001) {
				perfGString = "Horrible";
				}
				if(perfGScore > .8) {
				perfGString = "Bad";
				if(perfGScore > 1) {
				perfGString = "Neutral";
				if(perfGScore > 1.2) {
				perfGString = "Good";
				} if (perfGScore > 1.4) {
				perfGString = "Fantastic";
				} if (perfGScore > 1.6) {
				perfGString = "Insane";
				} if (perfGScore > 2) {
				perfGString = "Unreal";
				}
				}
				}
				goaliePerformanceList.add(perfGString);
				
				
				
				for(int i = 0; i < teams.get(teamExploreBar.getSelectedIndex()).players.size(); i++) {
					skaterNameList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).getName());
					skaterOffList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).offense + "");
					skaterPassList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).passing + "");
					skaterDefList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).defense + "");
					skaterPhyList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).physical + "");
					skaterGpList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).gamesPlayed + "");
					skaterOvrList.add(roundItTwo((double)(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).defense * 1.25 + teams.get(teamExploreBar.getSelectedIndex()).players.get(i).offense * 1.5 + teams.get(teamExploreBar.getSelectedIndex()).players.get(i).passing * 1.5 + teams.get(teamExploreBar.getSelectedIndex()).players.get(i).physical * .5)/3) +"");
					teamGlsList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).seasonGoals + "");
					teamAssList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).seasonAssists + "");
					teams.get(teamExploreBar.getSelectedIndex()).players.get(i).pts();
					teamPtsList.add(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).seasonPoints + "");
					double assScore = (double)teams.get(teamExploreBar.getSelectedIndex()).players.get(i).seasonAssists * 1.8 / teams.get(teamExploreBar.getSelectedIndex()).players.get(i).passing;
					double goalScore = (double)teams.get(teamExploreBar.getSelectedIndex()).players.get(i).seasonGoals  / teams.get(teamExploreBar.getSelectedIndex()).players.get(i).offense;
					
					double perfScore = (assScore+goalScore) / (double)(teams.get(teamExploreBar.getSelectedIndex()).players.get(i).gamesPlayed * 1.6);
					String perfString = "Neutral";
					if(perfScore > .01) {
					perfString = "Horrible";
					}
					if(perfScore > .09) {
					perfString = "Bad";
					if(perfScore > .11) {
					perfString = "Neutral";
					if(perfScore > .13) {
					perfString = "Good";
					} if (perfScore > .17) {
					perfString = "Fantastic";
					} if (perfScore > .24) {
					perfString = "Insane";
					} if (perfScore > .32) {
					perfString = "Unreal";
					}
					}
					}
					performanceList.add(perfString);
				}
			}
		});

		
		
		
		
		
		Button resetSeason = new Button("Reset Season");
		resetSeason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < teams.size(); i ++) {
					for(int j = 0; j < teams.get(i).players.size(); j++) {
						teams.get(i).players.get(j).seasonAssists = 0;
						teams.get(i).players.get(j).seasonGoals = 0;
						teams.get(i).players.get(j).seasonPoints = 0;
						teams.get(i).players.get(j).gamesPlayed = 0;
						teams.get(i).players.get(j).injuryLength = 0;		
					}
					teams.get(i).g.seasonAssists = 0;
					teams.get(i).g.seasonGoals = 0;
					teams.get(i).g.seasonPoints = 0;
					teams.get(i).wins = 0;
					teams.get(i).losses = 0;
					teams.get(i).ot = 0;
				}
				updateStandings();
				updatePoints();
				updateGoals();
				updateAssists();
				updateGoalieStats();
			}
		
		});
		resetSeason.setFont(new Font("Arial", Font.BOLD, 15));
		resetSeason.setBackground(Color.WHITE);
		resetSeason.setBounds(602, 895, 179, 35);
		contentPane.add(resetSeason);
		
		JLabel ovrRank = new JLabel("OVR");
		ovrRank.setHorizontalAlignment(SwingConstants.CENTER);
		ovrRank.setForeground(Color.WHITE);
		ovrRank.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		ovrRank.setBounds(1831, 48, 37, 35);
		contentPane.add(ovrRank);
		
		JLabel standingsLabel = new JLabel("Standings");
		standingsLabel.setBounds(1363, 35, 315, 48);
		contentPane.add(standingsLabel);
		standingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		standingsLabel.setForeground(Color.WHITE);
		standingsLabel.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		
		JLabel hockeyRink = new JLabel("");
		hockeyRink.setBounds(783, 66, 572, 275);
		contentPane.add(hockeyRink);
		hockeyRink.setHorizontalAlignment(SwingConstants.CENTER);
		hockeyRink.setBackground(new Color(0,0,0,0));
		hockeyRink.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/Rink.png")));
		
		hockeyPuck = new JLabel("");
		hockeyPuck.setForeground(new Color(0, 153, 0));
		hockeyPuck.setBackground(Color.BLACK);
		hockeyPuck.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		hockeyPuck.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/puck.png")));
		hockeyPuck.setBounds(1057, 192, 280, 25);
		contentPane.add(hockeyPuck);
		
		contentPane.setComponentZOrder(hockeyPuck, 0);
		contentPane.setComponentZOrder(hockeyRink, 1);
		
		
		Button playGame = new Button("Play");
		playGame.setActionCommand("Play");
		playGame.setFont(new Font("Arial", Font.BOLD, 15));
		playGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simGame(teams.get(homeChoice.getSelectedIndex()), teams.get(awayChoice.getSelectedIndex()));
				eventCounter = 0;
				homeForEvents.setText("Home:");
				awayForEvents.setText("Away:");
				homeScoreEvent = 0;
				awayScoreEvent = 0;
			}
		});
		playGame.setBackground(Color.WHITE);
		playGame.setBounds(343, 124, 111, 26);
		contentPane.add(playGame);
		
		JLabel eventsCount = new JLabel("/");
		eventsCount.setHorizontalAlignment(SwingConstants.CENTER);
		eventsCount.setForeground(Color.WHITE);
		eventsCount.setFont(new Font("Bahnschrift", Font.PLAIN, 23));
		eventsCount.setBounds(975, 47, 185, 26);
		contentPane.add(eventsCount);
		
		
		JButton nextEvent = new JButton("Next");
		nextEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(areWeSimming) {
					if(eventCounter+1 == events.size()) {
						
					} else {
						eventCounter++;
						simThisEvent(eventCounter);
						eventsCount.setText(eventCounter + "/" + (events.size()-1));
					}
				}
			}
		});
		nextEvent.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		nextEvent.setBounds(714, 23, 126, 40);
		contentPane.add(nextEvent);
		nextEvent.setVisible(false);
		
		
		JToggleButton simcasterSwitch = new JToggleButton("Sim Cast");
		simcasterSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPane.isVisible()) {
					textPane.setVisible(false);
					areWeSimming = true;
					standings.setVisible(false);
					record.setVisible(false);
					offStrengthIndex.setVisible(false);
					defStrengthIndex.setVisible(false);
					strengthIndex.setVisible(false);
					nextEvent.setVisible(true);
					hockeyPuck.setVisible(true);
				}
				else {
					textPane.setVisible(true);
					areWeSimming = false;
					standings.setVisible(true);
					record.setVisible(true);
					offStrengthIndex.setVisible(true);
					defStrengthIndex.setVisible(true);
					strengthIndex.setVisible(true);
					nextEvent.setVisible(false);
					hockeyPuck.setVisible(false);
				}
			}
		});
		simcasterSwitch.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		simcasterSwitch.setBounds(1023, 0, 89, 17);
		contentPane.add(simcasterSwitch);
		
		homeForEvents = new JLabel("Home:");
		homeForEvents.setForeground(Color.WHITE);
		homeForEvents.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		homeForEvents.setBounds(939, 11, 111, 14);
		contentPane.add(homeForEvents);
		
		awayForEvents = new JLabel("Away:");
		awayForEvents.setForeground(Color.WHITE);
		awayForEvents.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		awayForEvents.setBounds(1135, 11, 103, 14);
		contentPane.add(awayForEvents);
		
		goalLight = new JLabel("");
		goalLight.setHorizontalAlignment(SwingConstants.CENTER);
		goalLight.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/goal_light.gif")));
		goalLight.setBounds(939, 11, 262, 48);
		contentPane.add(goalLight);
		
		JLabel theLogo = new JLabel("");
		theLogo.setHorizontalAlignment(SwingConstants.CENTER);
		theLogo.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/photo.png")));
		theLogo.setBounds(290, 13, 224, 105);
		contentPane.add(theLogo);
		goalLight.setVisible(false);
	}
	
	static void loadTeams() {
		

		Team oilers = new Team("Edmonton Oilers");
		oilers.readFile("src/teamLists/oilers.txt");
		teams.add(oilers);
		
		Team bluejackets = new Team("Columbus Blue Jackets");
		bluejackets.readFile("src/teamLists/bluejackets.txt");
		teams.add(bluejackets);
		
		
		Team blackhawks = new Team("Chicago Blackhawks");
		blackhawks.readFile("src/teamLists/blackhawks.txt");
		teams.add(blackhawks);
		
		Team leafs  = new Team("Toronto Maple Leafs");
		leafs.readFile("src/teamLists/leafs.txt");
		teams.add(leafs);
		 
		Team sens = new Team("Ottawa Senators");
		sens.readFile("src/teamLists/senators.txt");
		teams.add(sens);
		
		Team flames = new Team("Calgary Flames");
		flames.readFile("src/teamLists/flames.txt");
		teams.add(flames);
		
		Team habs = new Team("Montreal Canadiens");
		habs.readFile("src/teamLists/canadiens.TXT");
		teams.add(habs);
		
		Team jets = new Team("Winnipeg Jets");
		jets.readFile("src/teamLists/jets.TXT");
		teams.add(jets);
		
		
		
	}
	
	static String simGame(Team h, Team a) {
		events.clear();
		h.health();
		a.health();
		h.getStarters();
		a.getStarters();
		String s = "";
		h.str();
		a.str();
		h.defStr();
		h.offStr();
		a.defStr();
		a.offStr();
		h.phyStr();
		a.phyStr();
		
		for(int i = 0; i < h.players.size(); i++) {
			h.players.get(i).healing();
		}
		for(int i = 0; i < a.players.size(); i++) {
			a.players.get(i).healing();
		}
		
		for(int i = 0; i < h.starters.size(); i++) {
			h.starters.get(i).play();
		}
		for(int i = 0; i < a.starters.size(); i++) {
			a.starters.get(i).play();
		}
		
		int homeShots = (int)(Math.random() * (1.1 *h.offenseStrength - (a.defenseStrength)*.5) + 12);;
		int awayShots = (int)(Math.random() * (1.1 *a.offenseStrength - (h.defenseStrength)*.5) + 12);
		
		
		int homeHits = (int)(Math.random() * (h.physicalStrength *.1) + 1);
		int awayHits = (int)(Math.random() * (a.physicalStrength *.1) + 1);
		int fights = (int)(Math.random() * ((h.physicalStrength+a.physicalStrength)*.02));
		for(int i = 0; i < homeHits; i++) {
			Player p = randomPlayer(a);
			Player p2 = whoHitOrFought(h);
			
			int random = 1 + (int)(Math.random() * ((100 - 1) + 1));
			if(random == 1) {
				s = s + generateInjury(p,p2);
				events.add("home injury " + p2.getName() + " on " + p.getName());	
			} else {
				events.add("home hit " + p2.getName() + " on " + p.getName());	
			}
		}
		
		for(int i = 0; i < awayHits; i++) {
			Player p = randomPlayer(h);
			Player p2 = whoHitOrFought(a);
			
			int random = 1 + (int)(Math.random() * ((100 - 1) + 1));
			if(random == 1) {
				generateInjury(p,p2);
				events.add("away injury " + p2.getName() + " on " + p.getName());
			} else {
				events.add("away hit " + p2.getName() + " on " + p.getName());
			}
		}
		
		for(int i = 0; i < fights; i++) {
			int random = 1 + (int)(Math.random() * ((2 - 1) + 1));
			if (random == 1) {
				events.add("home fight " + whoHitOrFought(h).getName() + " on " + whoHitOrFought(a).getName());
				homeShots = homeShots + 5;
			} else {
				events.add("away fight " + whoHitOrFought(a).getName() + " on " + whoHitOrFought(h).getName());
				awayShots = awayShots + 5;
			}

		}
		
		int homeGoals = (int) (( homeShots - (a.g.offense * 1.65)+5) * .2 * (Math.random()) + .5);
		int awayGoals = (int) (( awayShots - (h.g.offense * 1.65)+5) * .2 * (Math.random()) + .5);

		
		for(int i = 0; i < homeShots-homeGoals; i++) {
			events.add("home shot " + whoScored(h).getName());
		}
		
		for(int i = 0; i < awayShots-awayGoals; i++) {
			events.add("away shot " +  whoScored(a).getName());
		}

		if(homeGoals < 0) {
			homeGoals = 0;
		}
		if(awayGoals < 0) {
			awayGoals = 0;
		}
		h.g.seasonAssists = h.g.seasonAssists + awayShots;
		h.g.seasonGoals = h.g.seasonGoals + awayGoals;
		a.g.seasonAssists = a.g.seasonAssists + homeShots;
		a.g.seasonGoals = a.g.seasonGoals + homeGoals;
		
		
		s = s + (h.Name + ": " + homeGoals +  "\r\n" + "Shots On Goal:" + homeShots +  "\r\n" );
		s = s + "\r\n";
		
		for(int i = 0; i < homeGoals; i++) {
			s = simGoalHome(h,s);
		}
		
		s = s + "\r\n";
		s = s + (a.Name + ": " + awayGoals +  "\r\n" + "Shots On Goal:" + awayShots +  "\r\n" );
		s = s + "\r\n";

		for(int i = 0; i < awayGoals; i++) {	
			s = simGoalAway(a,s);
		}
		
		s = s + "\r\n";
		
		if(homeGoals > awayGoals) {
			Collections.shuffle(events);
			h.wins++;
			a.losses++;
			s = s + (h.getName() + " Beat " + a.getName());
			if(awayGoals == 0) {
				h.g.seasonPoints = h.g.seasonPoints + 1;
			}
		} else if (awayGoals > homeGoals) {
			Collections.shuffle(events);
			a.wins++;
			h.losses++;
			s = s + (a.getName() + " Beat " + h.getName());
			if(homeGoals == 0) {
				a.g.seasonPoints = a.g.seasonPoints + 1;
			}
		} else {
			int tie = (int)(Math.random() * 2);
			Collections.shuffle(events);
			if(tie == 0) {
				h.wins++;
				a.ot++;
				s = (simGoalHome(h,s) + " in Overtime");
				s = s + "\r\n";
				s = s + (h.getName() + " Beat " + a.getName());
				if(awayGoals == 0) {
					h.g.seasonPoints = h.g.seasonPoints + 1;
				}
			} else {
				a.wins++;
				h.ot++;
				s = (simGoalAway(a,s) + " in Overtime");
				s = s + "\r\n";
				s = s + (a.getName() + " Beat " + h.getName());
				if(homeGoals == 0) {
					a.g.seasonPoints = a.g.seasonPoints + 1;
				}
			}
		}
		
		/**
		for(int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i));
		}
		**/
		
		textPane.setText(s);
		updateStandings();
		return "";
	}
	
	static Player randomPlayer(Team team) {
		ArrayList<Player> chance = new ArrayList<Player>();
		
		for(int i = 0; i < team.starters.size(); i++) {
			chance.add(team.starters.get(i));
		}
		
		int whoScored = (int)(Math.random() * chance.size());
		for(int i = 0; i < chance.size(); i++) {
			if(whoScored == i) {
				return chance.get(i);
			}
		}
		
		
		return null;
	}
	
	static Player whoScored(Team team) {
		ArrayList<Player> chance = new ArrayList<Player>();
		
		for(int i = 0; i < team.starters.size(); i++) {
			for(int j = 0; j < team.starters.get(i).offense; j ++) {
				chance.add(team.starters.get(i));
			}
		}
		
		int whoScored = (int)(Math.random() * chance.size());
		for(int i = 0; i < chance.size(); i++) {
			if(whoScored == i) {
				return chance.get(i);
			}
		}
		
		
		return null;
	}
	
	static Player whoHitOrFought(Team team) {
		ArrayList<Player> chance = new ArrayList<Player>();
		
		for(int i = 0; i < team.starters.size(); i++) {
			for(int j = 0; j < team.starters.get(i).physical; j ++) {
				chance.add(team.starters.get(i));
			}
		}
		
		int whoScored = (int)(Math.random() * chance.size());
		for(int i = 0; i < chance.size(); i++) {
			if(whoScored == i) {
				return chance.get(i);
			}
		}
		
		
		return null;
	}
	
	static Player whoAssisted(Team team) {
		ArrayList<Player> chance = new ArrayList<Player>();
		
		for(int i = 0; i < team.starters.size(); i++) {
			for(int j = 0; j < team.starters.get(i).passing; j ++) {
				chance.add(team.starters.get(i));
			}
		}
		
		int whoScored = (int)(Math.random() * chance.size());
		for(int i = 0; i < chance.size(); i++) {
			if(whoScored == i) {
				return chance.get(i);
			}
		}
		
		
		return null;
	}
	
	static void updateStandings() {
		standings.clear();
		record.clear();
		offStrengthIndex.clear();
		defStrengthIndex.clear();
		strengthIndex.clear();
		int max = 0;
		int tot = 0;
		while(tot != teams.size()) {
			for (int i = 0; i < teams.size(); i++) {
				teams.get(i).pts();
				teams.get(i).str();
				if(teams.get(i).points == max) {
					standings.add("PTS: " + teams.get(i).points + " " + teams.get(i).getName());
					record.add(teams.get(i).wins + "-" + teams.get(i).losses + "-" + teams.get(i).ot);
					offStrengthIndex.add(roundItTwo((double)teams.get(i).offenseStrength/teams.get(i).players.size()) + "");
					defStrengthIndex.add(roundItTwo((double)teams.get(i).defenseStrength/teams.get(i).players.size()) + "");
					strengthIndex.add(roundItTwo((double)(teams.get(i).offenseStrength + teams.get(i).physicalStrength + teams.get(i).defenseStrength)/teams.get(i).players.size()) + "");
					tot++;
				}
			}
			max++;
		}
	}
	
	static void updatePoints() {
		pointsList.clear();
		ArrayList<Player> scorers = new ArrayList<Player>();
		for(int i = 0; i < teams.size(); i++) {
			for(int j = 0; j < teams.get(i).players.size(); j ++) {
				teams.get(i).players.get(j).pts();
				scorers.add(teams.get(i).players.get(j));
			}
		}

		int max = 0;
		int tot = 0;
		while(tot != scorers.size()) {
			for (int i = 0; i < scorers.size(); i++) {
				if(scorers.get(i).seasonPoints == max) {
					pointsList.add("PTS: " + scorers.get(i).seasonPoints+ "  " +  scorers.get(i).getName() );
					tot++;
				}
			}
			max++;
		}
	}
	
	static void updateGoals() {
		goalsList.clear();
		ArrayList<Player> scorers = new ArrayList();
		for(int i = 0; i < teams.size(); i++) {
			for(int j = 0; j < teams.get(i).players.size(); j ++) {
				teams.get(i).players.get(j).pts();
				scorers.add(teams.get(i).players.get(j));
			}
		}
		int max = 0;
		int tot = 0;
		while(tot != scorers.size()) {
			for (int i = 0; i < scorers.size(); i++) {
				if(scorers.get(i).seasonGoals == max) {
					goalsList.add("GLS: " + scorers.get(i).seasonGoals+ "  " +  scorers.get(i).getName() );
					tot++;
				}
			}
			max++;
		}
	}
	
	static void updateAssists() {
		assistList.clear();
		ArrayList<Player> scorers = new ArrayList<Player>();
		for(int i = 0; i < teams.size(); i++) {
			for(int j = 0; j < teams.get(i).players.size(); j ++) {
				teams.get(i).players.get(j).pts();
				scorers.add(teams.get(i).players.get(j));
			}
		}

		int max = 0;
		int tot = 0;
		while(tot != scorers.size()) {
			for (int i = 0; i < scorers.size(); i++) {
				if(scorers.get(i).seasonAssists == max) {
					assistList.add("ASS: " + scorers.get(i).seasonAssists+ "  " +  scorers.get(i).getName() );
					tot++;
				}
			}
			max++;
		}
	}
	
	static void updateGoalieStats() {
		goalsAgainstList.clear();
		shotsFacedList.clear();
		gamesPlayedList.clear();
		
		ArrayList<Goalie> scorers = new ArrayList<Goalie>();
		
			for(int j = 0; j < teams.size(); j ++) {
				scorers.add(teams.get(j).g);
			}
	

		int max = 0;
		int tot = 0;
		while(tot != scorers.size()) {
			for (int j = 0; j < scorers.size(); j++) {
				if((int)(roundIt(((double)scorers.get(j).seasonAssists - (double)scorers.get(j).seasonGoals)/(double)scorers.get(j).seasonAssists)*1000) == max) {
					shotsFacedList.add("SV%: " + roundIt(((double)scorers.get(j).seasonAssists - (double)scorers.get(j).seasonGoals)/(double)scorers.get(j).seasonAssists)+ "  " +  scorers.get(j).getName() );
					tot++;
				}
			}
			max++;
		}
		max = 0;
		tot = 0;
		while(tot != scorers.size()) {
			for (int j = 0; j < scorers.size(); j++) {
				if(scorers.get(j).seasonGoals == max) {
					goalsAgainstList.add("GAA: " + roundIt(((double)scorers.get(j).seasonGoals)/ (teams.get(j).gamesPlayed()))+ "  " +  scorers.get(j).getName() );
					tot++;
				}
			}
			max++;
		}
		max = 0;
		tot = 0;
		while(tot != scorers.size()) {
			for (int j = 0; j < scorers.size(); j++) {
				if(scorers.get(j).seasonPoints == max) {
					gamesPlayedList.add("SO: " + (teams.get(j).g.seasonPoints)+ "  " +  scorers.get(j).getName() );
					tot++;
				}
			}
			max++;
		}
	}
	
	static void updatePointsForTeam(int i) {
		assistList.clear();
		goalsList.clear();
		pointsList.clear();
		ArrayList<Player> scorers = new ArrayList<Player>();
		
			for(int j = 0; j < teams.get(i).players.size(); j ++) {
				teams.get(i).players.get(j).pts();
				scorers.add(teams.get(i).players.get(j));
			}
	

		int max = 0;
		int tot = 0;
		while(tot != scorers.size()) {
			for (int j = 0; j < scorers.size(); j++) {
				if(scorers.get(j).seasonAssists == max) {
					assistList.add("ASS: " + scorers.get(j).seasonAssists+ "  " +  scorers.get(j).getName() );
					tot++;
				}
			}
			max++;
		}
		max = 0;
		tot = 0;
		while(tot != scorers.size()) {
			for (int j = 0; j < scorers.size(); j++) {
				if(scorers.get(j).seasonPoints == max) {
					pointsList.add("PTS: " + scorers.get(j).seasonPoints+ "  " +  scorers.get(j).getName() );
					tot++;
				}
			}
			max++;
		}
		max = 0;
		tot = 0;
		while(tot != scorers.size()) {
			for (int j = 0; j < scorers.size(); j++) {
				if(scorers.get(j).seasonGoals == max) {
					goalsList.add("GLS: " + scorers.get(j).seasonGoals+ "  " +  scorers.get(j).getName() );
					tot++;
				}
			}
			max++;
		}
	}
	
	static void updateInjuries() {
		injuryList.clear();
		ArrayList<Player> scorers = new ArrayList<Player>();
		for(int i = 0; i < teams.size(); i++) {
			for(int j = 0; j < teams.get(i).players.size(); j ++) {
				if (teams.get(i).players.get(j).injuryLength > 0) {
					scorers.add(teams.get(i).players.get(j));
				}
			}
		}

		int max = 0;
		int tot = 0;
		while(tot != scorers.size()) {
			for (int i = 0; i < scorers.size(); i++) {
				if(scorers.get(i).injuryLength == max) {
					injuryList.add(scorers.get(i).getName() + " - " + scorers.get(i).injuryLength + " Games");
					tot++;
				}
			}
			max++;
		}
	}
	
	static void updateInjuriesForTeam(int i) {
		injuryList.clear();
		ArrayList<Player> scorers = new ArrayList<Player>();
		
		for(int j = 0; j < teams.get(i).players.size(); j ++) {
			if(teams.get(i).players.get(j).injuryLength > 0) {
				scorers.add(teams.get(i).players.get(j));	
			}
			
		}
		
		int max = 0;
		int tot = 0;
		while(tot != scorers.size()) {
			for (int j = 0; j < scorers.size(); j++) {
				if(scorers.get(j).injuryLength == max) {
					injuryList.add(scorers.get(j).getName() + " - " + scorers.get(j).injuryLength+ " Games");
					tot++;
				}
			}
			max++;
		}

	}
	
	static void saveTheSeason() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(savedTeams);
		for(int i = 0; i < teams.size(); i++) {
			writer.println(teams.get(i).getName());
			writer.println(teams.get(i).wins);
			writer.println(teams.get(i).losses);
			writer.println(teams.get(i).ot);
			writer.println(teams.get(i).goalsong);
			for(int j = 0; j < teams.get(i).players.size(); j++) {
				writer.println(teams.get(i).players.get(j).firstName);
				writer.println(teams.get(i).players.get(j).lastName);
				writer.println(teams.get(i).players.get(j).offense );
				writer.println(teams.get(i).players.get(j).passing );
				writer.println(teams.get(i).players.get(j).defense );
				writer.println(teams.get(i).players.get(j).physical);
				writer.println(teams.get(i).players.get(j).isGoalie);
				writer.println(teams.get(i).players.get(j).seasonGoals);
				writer.println(teams.get(i).players.get(j).seasonAssists);
				writer.println(teams.get(i).players.get(j).seasonPoints);
				writer.println(teams.get(i).players.get(j).gamesPlayed);
				writer.println(teams.get(i).players.get(j).injuryLength);
			}
			writer.println(teams.get(i).g.firstName);
			writer.println(teams.get(i).g.lastName);
			writer.println(teams.get(i).g.offense);
			writer.println(teams.get(i).g.passing);
			writer.println(teams.get(i).g.defense);
			writer.println(teams.get(i).g.physical);
			writer.println(teams.get(i).g.isGoalie);
			writer.println(teams.get(i).g.seasonGoals);
			writer.println(teams.get(i).g.seasonAssists);
			writer.println(teams.get(i).g.seasonPoints);
			writer.println(teams.get(i).g.gamesPlayed);
			writer.println(teams.get(i).g.injuryLength);
			writer.println("---");
		}
		writer.close();
	}
	
	static void loadTheSeason() throws FileNotFoundException, NullPointerException {
		teams.clear();
		Scanner sc = new Scanner(savedTeams);

		while(sc.hasNextLine()) {
			Team a = new Team(sc.nextLine().toString(),Integer.parseUnsignedInt(sc.nextLine()),Integer.parseUnsignedInt(sc.nextLine()),Integer.parseUnsignedInt(sc.nextLine()),sc.nextLine());
			
			//for(int i = 0; i < 15; i++)
			while(sc.hasNext("---") == false){
				String f = sc.nextLine().toString();
				String l = sc.nextLine().toString();
				int n = Integer.parseUnsignedInt(sc.nextLine());
				int ps = Integer.parseUnsignedInt(sc.nextLine());
				int d = Integer.parseUnsignedInt(sc.nextLine());
				int py = Integer.parseUnsignedInt(sc.nextLine());
				int is = Integer.parseUnsignedInt(sc.nextLine());
				int gl = Integer.parseUnsignedInt(sc.nextLine());
				int as = Integer.parseUnsignedInt(sc.nextLine());
				int pp = Integer.parseUnsignedInt(sc.nextLine());
				int gp = Integer.parseUnsignedInt(sc.nextLine());
				int il = Integer.parseUnsignedInt(sc.nextLine());
				//Player p = new Player(sc.nextLine().toString(),sc.nextLine().toString(), Integer.parseUnsignedInt(sc.nextLine()),Integer.parseUnsignedInt(sc.nextLine()),Integer.parseUnsignedInt(sc.nextLine()), Integer.parseUnsignedInt(sc.nextLine()));
				if (is== 1) {
					Goalie g = new Goalie(f,l,n, ps,d,py,is,gl,as, pp, gp, il);
					a.addGoalie(g);
				} else {
					Player p = new Player(f,l,n, ps, d, py,is,gl,as, pp, gp, il);
					a.addPlayer(p);
				}
			
			}
			
				
			teams.add(a);
			sc.nextLine();
			
		}
		updateStandings();
		updateGoals();
		updateAssists();
		updatePoints();
		

		sc.close();

	}
	
	
	static String simGoalHome(Team h, String s) {
		Player tempPlayer = whoScored(h);
		Player tempAssist = whoAssisted(h);
		Player tempAssist2 = whoAssisted(h);
		int numAssist = (int)(Math.random() * 5);
		tempPlayer.seasonGoals++;
		String goalString = "";
		
		goalString = goalString + (tempPlayer.getName() + " Scored");
		
		if(numAssist == 1) {
			while(tempAssist == tempPlayer) {
				tempAssist = whoAssisted(h);
			}
			tempAssist.seasonAssists++;
			goalString = goalString + ", assisted by " + tempAssist.getName();
		}
		
		if(numAssist >= 2) {
			while(tempAssist == tempPlayer) {
				tempAssist = whoAssisted(h);
			}
			while(tempAssist2 == tempPlayer || tempAssist2 == tempAssist) {
				tempAssist2 = whoAssisted(h);
			}
			tempAssist.seasonAssists++;
			tempAssist2.seasonAssists++;
			goalString = goalString + ", assisted by " + tempAssist.getName() + " and " + tempAssist2.getName() ;
			
		}
		events.add("home goal " + goalString);
		s = s + goalString + "\r\n";
		return s;
	}
	
	static String simGoalAway(Team h, String s) {
		Player tempPlayer = whoScored(h);
		Player tempAssist = whoAssisted(h);
		Player tempAssist2 = whoAssisted(h);
		int numAssist = (int)(Math.random() * 5);
		tempPlayer.seasonGoals++;
		String goalString = "";
		
		goalString = goalString + (tempPlayer.getName() + " Scored");
		
		if(numAssist == 1) {
			while(tempAssist == tempPlayer) {
				tempAssist = whoAssisted(h);
			}
			tempAssist.seasonAssists++;
			goalString = goalString + ", assisted by " + tempAssist.getName();
		}
		
		if(numAssist >= 2) {
			while(tempAssist == tempPlayer) {
				tempAssist = whoAssisted(h);
			}
			while(tempAssist2 == tempPlayer || tempAssist2 == tempAssist) {
				tempAssist2 = whoAssisted(h);
			}
			tempAssist.seasonAssists++;
			tempAssist2.seasonAssists++;
			goalString = goalString + ", assisted by " + tempAssist.getName() + " and " + tempAssist2.getName() ;
			
		}
		events.add("away goal " + goalString);
		s = s + goalString + "\r\n";
		return s;
	}
	
	static String generateInjury(Player p, Player e){
		int severity = (int)Math.floor(Math.random()*(20-1+1)+1);
		p.injuryLength = severity;
		return (p.getName() + " was injured by " + e.getName() + " ,he will be out approximatly "+ severity + " games" +  "\r\n" );
	}
	
	public static double roundIt(double value) {
	    double scale = Math.pow(10, 3);
	    return Math.round(value * scale) / scale;
	}
	
	public static double roundItTwo(double value) {
	    double scale = Math.pow(10, 1);
	    return Math.round(value * scale) / scale;
	}
	
	public static void movePuckHome(JLabel puck) {
		puck.setLocation(824 + (int)(Math.random() * (935-824)) , 89 + (int)(Math.random() * (308-89)));
	}
	
	public static void movePuckAway(JLabel puck) {
		puck.setLocation(1174 + (int)(Math.random() * (1285-1174)) , 89 + (int)(Math.random() * (308-89)));
	}
	
	public static void movePuckRandom(JLabel puck) {
		puck.setLocation(824 + (int)(Math.random() * (1285-824)) , 89 + (int)(Math.random() * (308-89)));
	}
	
	public static void simThisEvent(int e) {
		hockeyPuck.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/puck.png")));
		goalLight.setVisible(false);
		Scanner sc = new Scanner(events.get(e).toString());
		String team = sc.next();
		String event = sc.next();
		String firstName = sc.next();
		String lastName = sc.next();
		String dummy = "";
		String dummy2 = "";
		String dummy3 = "";

		if(sc.hasNext()) {
			dummy = sc.next();
			if(sc.hasNext()) {
			dummy2 = sc.next();
			if(sc.hasNext()) {
			dummy3 = sc.next();
			}
			}
			
		}


		if(team.contains("home") && event.contains("shot")) {
			movePuckHome(hockeyPuck);
			hockeyPuck.setText(lastName);
		}
		
		if(team.contains("away") && event.contains("shot")) {
			movePuckAway(hockeyPuck);
			hockeyPuck.setText(lastName);
		}
		
		if(team.contains("home") && event.contains("goal")) {
			movePuckHome(hockeyPuck);
			hockeyPuck.setText(lastName);
			homeScoreEvent++;
			homeForEvents.setText("Home: " + homeScoreEvent);
			goalLight.setVisible(true);
			SimpleAudioPlayer.main("src/audio/goalHorn.wav");
			SimpleAudioPlayer.main("src/audio/" + teams.get(homeChoice.getSelectedIndex()).goalsong);
		}
		
		if(team.contains("away") && event.contains("goal")) {
			movePuckAway(hockeyPuck);
			hockeyPuck.setText(lastName);
			awayScoreEvent++;
			awayForEvents.setText("Away: " + awayScoreEvent);
			goalLight.setVisible(true);
			SimpleAudioPlayer.main("src/audio/goalHorn.wav");
			SimpleAudioPlayer.main("src/audio/" + teams.get(awayChoice.getSelectedIndex()).goalsong);
		}
		
		if(event.contains("hit")) {
			hockeyPuck.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/bang.png")));
			movePuckRandom(hockeyPuck);
			hockeyPuck.setText(lastName + " on " + dummy3);
		}
		
		if(event.contains("fight")) {
			hockeyPuck.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/fight.png")));
			movePuckRandom(hockeyPuck);
			hockeyPuck.setText(lastName + " on " + dummy3);
		}
		
		if(event.contains("injury")) {
			hockeyPuck.setIcon(new ImageIcon(SimWindow.class.getResource("/photos/injury.png")));
			movePuckRandom(hockeyPuck);
			hockeyPuck.setText(lastName + " on " + dummy3);
		}
		
	}
}
