/**
 * 
 */
package edu.ncsu.csc316.security_manager.objects;

/**
 * Defines one log entry.
 * 
 * @author John Choi
 * @version 06292018
 */
public class LogEntry {
	
	private String date; //will hold MM-DD-YYYY
	private String time;
	private String user;
	private String description;

	/**
	 * Constructs this log entry.
	 * 
	 * @param date of this log entry
	 * @param time of this log entry
	 * @param user of this log entry
	 * @param description of this log entry
	 */
	public LogEntry(String date, String time, String user, String description) {
		this.date = date;
		this.time = time;
		this.user = user;
		this.description = description;
	}

	/**
	 * Getter for the date.
	 * Returns in a guaranteed format MM-DD-YYYY
	 * 
	 * @return the date
	 */
	public String getDate() {
		String[] temp = date.split("-");
		int month = Integer.parseInt(temp[0]);
		int day = Integer.parseInt(temp[1]);
		int year = Integer.parseInt(temp[2]);
		return String.format("%02d-%02d-%d", month, day, year);
	}
	
	/**
	 * Returns the formatted date.
	 * 
	 * @return the formatted date
	 */
	public String getFormattedDate() {
		return dateConverter();
	}

	/**
	 * Returns the information about this log entry in format.
	 * 
	 * @return information about this log entry
	 */
	public String toString() {
		return String.format("LogEntry[timestamp=%s %s, user=%s, description=%s]", dateConverter(), timeConverter(), user, description);
	}
	
	/**
	 * Helper method that ensures YYYY/MM/DD format
	 * 
	 * @return formatted date
	 */
	private String dateConverter() {
		String[] temp = date.split("-");
		int month = Integer.parseInt(temp[0]);
		int day = Integer.parseInt(temp[1]);
		int year = Integer.parseInt(temp[2]);
		return String.format("%04d/%02d/%02d", year, month, day);
	}
	
	
	
	/**
	 * Helper method that ensures HH:MM:SS format of time.
	 * 
	 * @return formatted time
	 */
	private String timeConverter() {
		String[] temp = time.split(":");
		int hour = Integer.parseInt(temp[0].trim());
		int minute = Integer.parseInt(temp[1].trim());
		int second = Integer.parseInt(temp[2].trim());
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
}
