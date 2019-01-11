/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.transportation_manager.city.City;
import edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList;
import edu.ncsu.csc316.transportation_manager.datastructure.Graph;
import edu.ncsu.csc316.transportation_manager.datastructure.Uptree;
import edu.ncsu.csc316.transportation_manager.datastructure.UptreeNode;
import edu.ncsu.csc316.transportation_manager.heap.MinHighwayHeap;
import edu.ncsu.csc316.transportation_manager.highway.Highway;

/**
 * Main manager that runs everything for this software.
 * 
 * @author John Choi
 * @version 07232018
 */
public class TransportationManager {
	
	private Graph<City, Double> graph;

	/**
	 * Constructs a new TransportationManager.
	 * Required method.
	 * 
	 * @param pathToFile the path to the file that contains the set of highways in the graph
	 * @throws IllegalArgumentException thrown if the file cannot be opened
	 */
	public TransportationManager(String pathToFile) throws IllegalArgumentException {
		try {
			readFile(pathToFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns a string representation of the AdjacencyList
	 * in the following format, where (for each city) the Highways are
	 * in sorted order by city1, then city2, then cost, then asphalt:
	 * 
	 * AdjacencyList[
	 *    City 0: Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 *    City 1: Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 *    City 2: Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] Highway[city1=X, city2=X, cost=X.X, asphalt=X.X] Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 *    City 3: Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * ]
	 * Required method.
	 * 
	 * @return the string representation of the AdjacencyLists
	 */
	public String getAdjacencyList() {
		StringBuilder sb = new StringBuilder("AdjacencyList[\n");
		graph.sortCities();
		graph.sortNeighbors();
		sb.append(graph.getAdjacency());
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Returns a string representation of the list of Highways contained in the 
	 * minimum spanning set of Highways. The returned string is in the following format,
	 * where the Highways are in sorted order by city1, city2, then cost, then asphalt:
	 * 
	 * List[
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * ]
	 * Required method.
	 * 
	 * @param type the type ("COST" or "ASPHALT") of field to minimize
	 * @return a string representation of the minimum spanning set of Highways
	 */
	public String getMinimumHighways(String type) {
		CustomArrayList<Highway> highways = mergeSort(kruskalAlgorithm(type));
		StringBuilder sb = new StringBuilder("List[\n");
		for (int i = 0; i < highways.size(); i++) {
			sb.append("   " + highways.get(i).toString());
			if (i != highways.size() - 1) {
				sb.append(",\n");
			}
		}
		sb.append("\n]");
//		System.out.println("\n\n\n" + sb.toString());
		return sb.toString();
	}
	
	/**
	 * Merge sorts the list passed in.
	 * 
	 * @param highways to be sorted
	 * @return sorted list by city name
	 */
	private CustomArrayList<Highway> mergeSort(CustomArrayList<Highway> highways) {
		int total = highways.size();
		if (total < 2) {
			return highways;
		} else {
			int midpoint = total / 2;
			CustomArrayList<Highway> left = new CustomArrayList<>();
			CustomArrayList<Highway> right = new CustomArrayList<>();
			for (int i = 0; i < midpoint; i++) {
				left.add(highways.get(i));
			}
			for (int i = midpoint; i < total; i++) {
				right.add(highways.get(i));
			}
			left = mergeSort(left);
			right = mergeSort(right);
			return merge(left, right, highways);
		}
	}
	
	/**
	 * Private helper method for merge sorting that takes in left half and right half of the list as well as the 
	 * full list.
	 * 
	 * @param left half of the list
	 * @param right half of the list
	 * @param whole list
	 * @return whole sorted list
	 */
	private CustomArrayList<Highway> merge(CustomArrayList<Highway> left, CustomArrayList<Highway> right, CustomArrayList<Highway> whole) {
		int leftIndex = 0, rightIndex = 0, wholeIndex = 0;
		while (leftIndex < left.size() && rightIndex < right.size()) {
			if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
				whole.set(left.get(leftIndex), wholeIndex);
				leftIndex++;
			} else {
				whole.set(right.get(rightIndex), wholeIndex);
				rightIndex++;
			}
			wholeIndex++;
		}
		CustomArrayList<Highway> rest;
		int restIndex;
		if (leftIndex >= left.size()) {
			rest = right;
			restIndex = rightIndex;
		} else {
			rest = left;
			restIndex = leftIndex;
		}
		
		for (int i = restIndex; i < rest.size(); i++) {
			whole.set(rest.get(i), wholeIndex);
			wholeIndex++;
		}
		
		return whole;
	}
	
	/**
	 * Kruskal's algorithm to determine the minimum highway traversal list.
	 * 
	 * @param type of the weight
	 * @return sorted list of highway
	 */
	private CustomArrayList<Highway> kruskalAlgorithm(String type) {
		CustomArrayList<Highway> minHighways = new CustomArrayList<Highway>();
		Uptree<Integer, Integer> uptree = new Uptree<>();
		CustomArrayList<UptreeNode<Integer, Integer>> nodes = new CustomArrayList<>();
		int numCities = graph.getCities().size();
		for (int i = 0; i < numCities; i++) {
			UptreeNode<Integer, Integer> node = new UptreeNode<>(graph.getCities().get(i).getValue(), 0);
			nodes.add(node);
		}
		MinHighwayHeap heap = new MinHighwayHeap(type);
		for (int i = 0; i < graph.getHighway().size(); i++) {
			heap.insert(graph.getHighway().get(i));
//			System.out.println(graph.getHighway().get(i).getC1().getValue() + " " + graph.getHighway().get(i).getC2().getValue() + " " +graph.getHighway().get(i).getCost().getCost() + " " + graph.getHighway().get(i).getAsphalt().getDistance());
		}
		while (numCities > 1) {
			Highway hw = heap.deleteMin();
			int c1 = hw.getC1().getValue();
			int c2 = hw.getC2().getValue();
			int temp1 = uptree.find(nodes.get(c1)).getKey();
			int temp2 = uptree.find(nodes.get(c2)).getKey();
			if (temp1 != temp2) {
				uptree.union(uptree.find(nodes.get(c1)), uptree.find(nodes.get(c2)));
				minHighways.add(hw);
				numCities--;
			}
		}
		return minHighways;
		
	}
	/**
	 * Reads the highway information file.
	 * 
	 * @param fname - name of the file
	 * @throws FileNotFoundException thrown if file is not found
	 */
	public void readFile(String fname) throws FileNotFoundException {
		File myFile = new File(fname);
		Scanner readFile = new Scanner(myFile);
		graph = new Graph<>();
		while (readFile.hasNextLine()) {
			int city1;
			int city2;
			double cost;
			double asphalt;
			try {
				city1 = readFile.nextInt();
				city2 = readFile.nextInt();
				cost = readFile.nextDouble();
				asphalt = readFile.nextDouble();
//				readFile.nextLine();
			} catch (Exception e) {
				break;
			}
			Highway newHighway = new Highway(city1, city2, cost, asphalt);
			graph.insertHighway(newHighway);
		}
		readFile.close();
	}
}
