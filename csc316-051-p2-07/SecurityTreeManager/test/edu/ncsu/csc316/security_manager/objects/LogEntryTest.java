/**
 * 
 */
package edu.ncsu.csc316.security_manager.objects;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality for {@link edu.ncsu.csc316.security_manager.objects.LogEntry}.
 * 
 * @author John Choi
 * @version 06292018
 */
public class LogEntryTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.LogEntry#toString()}.
	 */
	@Test
	public void testToString() {
		LogEntry test = new LogEntry("9-13-2015", "2:58:49", "user2", "save patient list");
		assertEquals("LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]", test.toString());
		
		test = new LogEntry("7-10-2015", "0:26:58", "user15", "add patient representative list");
		assertEquals("LogEntry[timestamp=2015/07/10 00:26:58, user=user15, description=add patient representative list]", test.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.LogEntry#getFormattedDate()}.
	 */
	@Test
	public void testGetFormattedDate() {
		LogEntry test = new LogEntry("9-13-2015", "2:58:49", "user2", "save patient list");
		assertEquals("2015/09/13", test.getFormattedDate());
	}
}
