package edu.ncsu.csc316.security_manager.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ncsu.csc316.security_manager.datastructure.BinarySearchTree;
import edu.ncsu.csc316.security_manager.datastructure.CustomArrayList;
import edu.ncsu.csc316.security_manager.datastructure.GeneralTree;
import edu.ncsu.csc316.security_manager.objects.AttackGoal;
import edu.ncsu.csc316.security_manager.objects.LogEntry;

/**
 * Main controller/manager for this software.
 * 
 * @author John Choi
 * @version 07032018
 */
public class SecurityTreeManager {
	
	private GeneralTree<AttackGoal> attackGoals;
	private BinarySearchTree<String, LogEntry> logEntries;

	/**
	 * Constructs a new SecurityTreeManager object with the given paths 
	 * to the preOrder and postOrder traversal files.
	 * Required method.
	 * 
	 * @param preOrder - the path to the preOrder traversal file
	 * @param postOrder - the path to the postOrder traversal file
	 */
	public SecurityTreeManager(String preOrder, String postOrder) {
		try {
			attackGoals = new GeneralTree<>();
			readAttackGoal(preOrder, postOrder);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Constructs a new SecurityTreeManager object with the given path
	 * to the file that contains the log entries.
	 * Required method
	 * 
	 * @param filePath - the path to the log entry file
	 */
	public SecurityTreeManager(String filePath) {
		try {
			logEntries = new BinarySearchTree<>();
			readLogFile(filePath);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns the level order traversal of the Attack Tree
	 * as a string in the format (where each "node" is indented 3 spaces):
	 * 
	 * LevelOrder[
	 *    GOAL Step[Use DDoS Attack to Disrupt All Users, C=0.00, P=0.000, I=0.00]
	 *    OR Step[Attack Servers, C=0.00, P=0.000, I=0.00]
	 *    OR Step[Attack Comm Infrastructure, C=0.00, P=0.000, I=0.00]
	 * ]
	 *
	 * THE LEVEL ORDER TRAVERSAL MUST NOT RETURN ANY OF THE PROPAGATED VALUES!
	 * Why? So you can earn credit for having a correct traversal,
	 * even if you have incorrect functions for propagating values.
	 * Required method.
	 * 
	 * @return the level order traversal (as a string) of the attack tree
	 */
	public String getAttackTreeLevelOrder() {
		return String.format("LevelOrder[\n%s\n]", attackGoals.printLevelOrder());
	}
	
	/**
	 * Return the level order traversal of the Log Tree
	 * as a string in the format (where each "node" is indented 3 spaces):
	 * 
	 * LevelOrder[
	 *    LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]
	 *    LogEntry[timestamp=2012/12/18 16:25:58, user=user18, description=view diagnoses]
	 *    LogEntry[timestamp=2016/12/12 06:28:13, user=user6, description=edit patient representative list]
	 * ]
	 * Required method.
	 * 
	 * @return the level order traversal (as a string) of the log tree
	 */
	public String getLogTreeLevelOrder() {
		return String.format("LevelOrder[\n%s\n]", logEntries.printLevelOrder());
	}
	
	/**
	 * Returns the values (as a string) propagated to the root node
	 * using the formulas from the project writeup.
	 * For example:
	 * GOAL Step[Use DDoS Attack to Disrupt All Users, C=21557.12, P=0.878, I=8.00]
	 * Required method.
	 * 
	 * @return the metric values (as a string) that are propagated to the root node
	 */
	public String propagateValues() {
		calculate();
		AttackGoal root = attackGoals.getRoot().getData();
		return String.format("GOAL Step[%s, C=%.2f, P=%.3f, I=%.2f]", root.getDescription(), root.getCost(), root.getProbability(), root.getScore());
	}
	
	/**
	 * Recursively goes through the tree in post order and calculates the cost, probability, and impact
	 * when the information is empty.
	 */
	private void calculate() {
		calculate(attackGoals.getRoot());
	}
	
	/**
	 * Helper method that does the recursive traversal.
	 * 
	 * @param node current node
	 */
	private void calculate(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		if (node == null) {
			return;
		}
		for (int i = 0; i < node.getChildren().size(); i++) {
			calculate(node.getChildren().get(i));
		}
		if (node.getData().getCost() == 0 && node.getData().getProbability() == 0 && node.getData().getScore() == 0) {
			fillInData(node);
		}
	}
	
	/**
	 * Fills in the data when the node is missing it.
	 * 
	 * @param node current node
	 */
	private void fillInData(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		if (node.getChildren().get(0).getData().getType().equals("OR")) {
			node.getData().setCost(calculateCostOR(node));
			node.getData().setProbability(calculateProbOR(node));
			node.getData().setScore(calculateImpactOR(node));
		} else /*(node.getChildren().get(0).getData().getType().equals("AND")*/ {
			node.getData().setCost(calculateCostAND(node));
			node.getData().setProbability(calculateProbAND(node));
			node.getData().setScore(calculateImpactAND(node));
		}
	}

	/**
	 * Calculates probability of AND nodes.
	 * 
	 * @param node current node
	 * @return node probability
	 */
	private double calculateProbAND(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		double multiplication = 1;
		for (int i = 0; i < node.getChildren().size(); i++) {
			multiplication *= node.getChildren().get(i).getData().getProbability();
		}
		return multiplication;
	}
	
	/**
	 * Calculates probability of OR nodes.
	 * 
	 * @param node current node
	 * @return node probability
	 */
	private double calculateProbOR(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		double multiplication = 1;
		for (int i = 0; i < node.getChildren().size(); i++) {
			multiplication *= 1 - node.getChildren().get(i).getData().getProbability();
		}
		return 1 - multiplication;
	}
	
	/**
	 * Calculates cost of OR nodes.
	 * 
	 * @param node current node
	 * @return node cost
	 */
	private double calculateCostOR(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		double topSummation = 0;
		double bottomSummation = 0;
		for (int i = 0; i < node.getChildren().size(); i++) {
			AttackGoal root = node.getChildren().get(i).getData();
			topSummation += (root.getProbability() * root.getCost());
			bottomSummation += root.getProbability();
		}
		return topSummation / bottomSummation;
	}
	
	/**
	 * Calculates impact of OR nodes.
	 * 
	 * @param node current node
	 * @return node impact
	 */
	private double calculateImpactOR(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		double max = 0;
		for (int i = 0; i < node.getChildren().size(); i++) {
			AttackGoal root = node.getChildren().get(i).getData();
			if (root.getScore() > max) {
				max = root.getScore();
			}
		}
		return max;
	}
	
	/**
	 * Calculates cost of AND nodes.
	 * 
	 * @param node current node
	 * @return node cost
	 */
	private double calculateCostAND(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		double result = 0;
		for (int i = 0; i < node.getChildren().size(); i++) {
			result += node.getChildren().get(i).getData().getCost();
		}
		return result;
	}
	
	/**
	 * Calculates impact of AND nodes.
	 * 
	 * @param node current node
	 * @return node impact
	 */
	private double calculateImpactAND(GeneralTree<AttackGoal>.GeneralTreeNode<AttackGoal> node) {
		double result = 0;
		double top = 1;
		double bottom = Math.pow(10, node.getChildren().size() - 1);
		for (int i = 0; i < node.getChildren().size(); i++) {
			top *= (10 - node.getChildren().get(i).getData().getScore());
		}
		top = Math.pow(10, node.getChildren().size()) - top;
		result = top / bottom;
		return result;
	}
	
	/**
	 * Returns (as a string, sorted in increasing order) the log entries 
	 * for the given date in the format:
	 * 
	 * LogEntry[timestamp=2015/07/17 15:49:38, user=user4, description=print calendar]
	 * LogEntry[timestamp=2015/07/17 15:55:25, user=user8, description=save immunizations]
	 * Required method.
	 * 
	 * @param date - the date, in the format MM-DD-YYYY
	 * @return the string representation of the log entries for the specified date
	 * @throws IllegalArgumentException thrown if the input date format is invalid
	 */
	public String getLogEntriesForDate(String date) throws IllegalArgumentException {
//		String regex = "^(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$";
		String regex = "[0-9]{1,2}(-)[0-9]{1,2}(-)[0-9]{4}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);
		if (!matcher.matches()) {
			throw new IllegalArgumentException();
		}
		if (date.length() != 10) {
			date = convertDateFormat(date);
		}
		StringBuilder sb = new StringBuilder();
//		CustomArrayList<LogEntry> removedLogs = new CustomArrayList<>();
//		String lookupResult = logEntries.lookup(date).getData().toString();
//		while (true) {
//			try {
//				String lookupResult = logEntries.lookup(date).getData().toString();
//				sb.append(lookupResult);
//				removedLogs.add(logEntries.remove(date));
//			} catch (NullPointerException e) {
//				//restore bst
//				for (int i = 0; i < removedLogs.size(); i++) {
//					logEntries.insert(removedLogs.get(i).getDate(), removedLogs.get(i));
//				}
//				break;
//			}
//			sb.append("\n");
//		}
		String lookupResult = ""; //removing duplicate check feature for now
		try {
			lookupResult = logEntries.lookup(date).getData().toString();
			sb.append(lookupResult);
			sb.append("\n");
			if (logEntries.lookup(date).getDuplicates() != 0) {
				for (int i = 1; i <= logEntries.lookup(date).getDuplicates(); i++) {
					lookupResult = logEntries.lookup(date).getDuplicateNodes().get(i).toString();
					sb.append(lookupResult);
					sb.append("\n");
				}
			}
		} catch (NullPointerException e) {
			//do nothing
		}
		
		return sb.toString();
	}
	
	/**
	 * Converts the input date into MM-DD-YYYY format.
	 * 
	 * @param date to convert
	 * @return converted date
	 */
	private String convertDateFormat(String date) {
		String[] temp = date.split("-");
		int month = Integer.parseInt(temp[0]);
		int day = Integer.parseInt(temp[1]);
		int year = Integer.parseInt(temp[2]);
		return String.format("%02d-%02d-%04d", month, day, year);
	}

	/**
	 * Reads the attack goal file.
	 * 
	 * @param preOrder file
	 * @param postOrder file
	 * @throws FileNotFoundException thrown if file(s) cannot be found
	 */
	public void readAttackGoal(String preOrder, String postOrder) throws FileNotFoundException {
		File preO = new File(preOrder);
		File postO = new File(postOrder);
		Scanner read = new Scanner(preO);
		AttackGoal oneGoal;
		//reading preOrder file
		CustomArrayList<AttackGoal> pre = new CustomArrayList<>();
		while (read.hasNext()) {
			String type = read.next();
			try {
				oneGoal = new AttackGoal(type, read.nextDouble(), read.nextInt(), read.nextInt(), read.nextLine().trim());
			} catch (Exception e) {
				oneGoal = new AttackGoal(type, read.nextLine().trim());
			}
			pre.add(oneGoal);
		}
		read.close();
		
		//reading postOrder file
		CustomArrayList<AttackGoal> post = new CustomArrayList<>();
		read = new Scanner(postO);
		while (read.hasNextLine()) {
			String type = read.next();
			try {
				oneGoal = new AttackGoal(type, read.nextDouble(), read.nextInt(), read.nextInt(), read.nextLine().trim());
			} catch (Exception e) {
				oneGoal = new AttackGoal(type, read.nextLine().trim());
			}
			post.add(oneGoal);
		}
		read.close();
//		attackGoals.buildAttackTree(pre, post, 0, post.size() - 1, 0);
		attackGoals.buildAttackTree(pre, post);
	}

	/**
	 * Reads the log file.
	 * 
	 * @param filename to read
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public void readLogFile(String filename) throws FileNotFoundException {
		File readFile = new File(filename);
		Scanner read = new Scanner(readFile);
		LogEntry log;
		while (read.hasNextLine()) {
			String date = read.next();
			String[] rest = read.nextLine().split(",");
			log = new LogEntry(date, rest[0], rest[1], rest[2]);
			logEntries.insert(log.getDate(), log);
		}
		read.close();
	}
}
