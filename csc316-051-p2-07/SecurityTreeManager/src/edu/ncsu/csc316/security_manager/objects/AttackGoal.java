/**
 * 
 */
package edu.ncsu.csc316.security_manager.objects;

/**
 * Defines one attack goal.
 * 
 * @author John Choi
 * @version 07032018
 */
public class AttackGoal {

	private String type;
	private double probability;
	private double score;
	private double cost;
	private String description;
	
	/**
	 * Constructs this attack goal.
	 * 
	 * @param type of this attack goal
	 * @param probability of this attack goal
	 * @param score of this attack goal
	 * @param cost of this attack goal
	 * @param description of this attack goal
	 */
	public AttackGoal(String type, double probability, double score, double cost, String description) {
		this.type = type;
		this.probability = probability;
		this.score = score;
		this.cost = cost;
		this.description = description;
	}
	
	/**
	 * Constructs one attack node with type and description and sets everything else to 0.
	 * 
	 * @param type of the attack goal
	 * @param description of the attack goal
	 */
	public AttackGoal(String type, String description) {
		this.type = type;
		this.description = description;
		probability = 0;
		score = 0;
		cost = 0;
	}

	/**
	 * Getter for type.
	 * 
	 * @return the type
	 */
	public String getType() {
		if (type.equals("O")) {
			return "OR";
		} else if (type.equals("A")) {
			return "AND";
		} else {
			return type;
		}
	}

	/**
	 * Getter for probability.
	 * 
	 * @return the probability
	 */
	public double getProbability() {
		return probability;
	}

	/**
	 * Getter for the score.
	 * 
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Getter for the cost.
	 * 
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Setter for the probability.
	 * 
	 * @param probability the probability to set
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}

	/**
	 * Setter for the score.
	 * 
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * Setter for the cost.
	 * 
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Getter for the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns attack goal information in format.
	 * 
	 * @return attack goal information in format
	 */
	public String toString() {
		return String.format("%s Step[%s, C=%.2f, P=%.3f, I=%.2f]", getType(), description, cost, probability, score);
	}

	/**
	 * Returns true if the two objects are equal.
	 * 
	 * @param obj - object to compare
	 * @return true if the two objects are equal
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttackGoal other = (AttackGoal) obj;
		if (cost != other.cost)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(probability) != Double.doubleToLongBits(other.probability))
			return false;
		if (score != other.score)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
