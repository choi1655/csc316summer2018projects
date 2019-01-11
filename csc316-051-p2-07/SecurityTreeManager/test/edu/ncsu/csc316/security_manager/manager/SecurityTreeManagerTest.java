/**
 * 
 */
package edu.ncsu.csc316.security_manager.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests {@link edu.ncsu.csc316.security_manager.manager.SecurityTreeManager}.
 * 
 * @author John Choi
 * @version 07032018
 */
public class SecurityTreeManagerTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.manager.SecurityTreeManager#getAttackTreeLevelOrder()}.
	 */
	@Test
	public void testGetAttackTreeLevelOrder() {
		SecurityTreeManager stm = new SecurityTreeManager("input/ddos-preorder.txt", "input/ddos-postorder.txt");
		System.out.println(stm.getAttackTreeLevelOrder());
		assertEquals("LevelOrder[\n" + 
				"   GOAL Step[Use DDoS Attack to Disrupt All Users, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack Servers, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack Comm Infrastructure, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack All Clients, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Direct BOTNET against key Servers, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Infect Servers with Worm/Virus, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack Switches, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack Routers, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack DNS, C=50000.00, P=0.100, I=8.00]\n" + 
				"   OR Step[Infect Clients with Worm/Virus, C=10000.00, P=0.100, I=5.00]\n" + 
				"   OR Step[\"Rent\" Existing BOTNET, C=5000.00, P=0.500, I=6.00]\n" + 
				"   OR Step[Build a BOTNET, C=0.00, P=0.000, I=0.00]\n" + 
				"   AND Step[Find Vulnerable Computers, C=2000.00, P=0.500, I=3.00]\n" + 
				"   AND Step[Infect Computer with BOT, C=5000.00, P=0.400, I=4.00]\n" + 
				"   AND Step[Remain Undetected, C=1000.00, P=0.600, I=4.00]\n" + 
				"]", stm.getAttackTreeLevelOrder());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.manager.SecurityTreeManager#getLogTreeLevelOrder()}.
	 */
	@Test
	public void testGetLogTreeLevelOrder() {
		SecurityTreeManager stm = new SecurityTreeManager("input/sample-log.txt");
//		System.out.println(stm.getLogTreeLevelOrder());
		assertEquals("LevelOrder[\n" + 
				"   LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]\n" + 
				"   LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]\n" + 
				"   LogEntry[timestamp=2012/12/18 16:25:58, user=user18, description=view diagnoses]\n" + 
				"   LogEntry[timestamp=2015/07/10 00:26:58, user=user15, description=add patient representative list]\n" + 
				"   LogEntry[timestamp=2015/08/01 03:54:17, user=user10, description=update appointment requests]\n" + 
				"   LogEntry[timestamp=2016/12/12 06:28:13, user=user6, description=edit patient representative list]\n" + 
				"   LogEntry[timestamp=2015/02/10 16:12:23, user=user15, description=modify office visit information]\n" + 
				"   LogEntry[timestamp=2015/07/17 15:55:25, user=user8, description=save immunizations]\n" + 
				"   LogEntry[timestamp=2011/08/12 20:13:47, user=user1, description=create basic health information]\n" + 
				"   LogEntry[timestamp=2015/10/07 05:34:10, user=user7, description=edit email reminders]\n" + 
				"   LogEntry[timestamp=2009/01/03 10:35:47, user=user11, description=view email reminders]\n" + 
				"   LogEntry[timestamp=2010/06/27 03:45:37, user=user10, description=delete calendar]\n" + 
				"   LogEntry[timestamp=2010/07/14 12:53:15, user=user7, description=remove diagnoses]\n" + 
				"   LogEntry[timestamp=2014/10/06 13:11:31, user=user5, description=edit office visit information]\n" + 
				"   LogEntry[timestamp=2007/11/24 23:10:24, user=user19, description=update appointment requests]\n" + 
				"   LogEntry[timestamp=2015/03/29 13:38:31, user=user7, description=view demographics]\n" + 
				"   LogEntry[timestamp=2012/09/17 11:45:24, user=user16, description=create upcoming appointments]\n" + 
				"   LogEntry[timestamp=2013/05/14 21:16:36, user=user3, description=add upcoming appointments]\n" + 
				"   LogEntry[timestamp=2007/06/01 07:37:31, user=user3, description=print immunizations]\n" + 
				"   LogEntry[timestamp=2013/06/16 23:28:33, user=user3, description=add appointment requests]\n" + 
				"]", stm.getLogTreeLevelOrder());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.manager.SecurityTreeManager#propagateValues()}.
	 */
	@Test
	public void testPropagateValues() {
		SecurityTreeManager stm = new SecurityTreeManager("input/ddos-preorder.txt", "input/ddos-postorder.txt");
		System.out.println(stm.propagateValues());
		assertEquals("GOAL Step[Use DDoS Attack to Disrupt All Users, C=21557.12, P=0.878, I=8.00]", stm.propagateValues());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.manager.SecurityTreeManager#getLogEntriesForDate(java.lang.String)}.
	 * 
	 * @throws IllegalArgumentException if the date format is invalid
	 */
	@Test
	public void testGetLogEntriesForDate() throws IllegalArgumentException {
		SecurityTreeManager stm = new SecurityTreeManager("input/sample-log.txt");
		try {
			stm.getLogEntriesForDate("hello this doesnt match");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
		assertEquals("", stm.getLogEntriesForDate("02-14-2015"));
		assertEquals("LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]\n", stm.getLogEntriesForDate("7-19-2013"));
		
		assertEquals("LevelOrder[\n" + 
				"   LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]\n" + 
				"   LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]\n" + 
				"   LogEntry[timestamp=2012/12/18 16:25:58, user=user18, description=view diagnoses]\n" + 
				"   LogEntry[timestamp=2015/07/10 00:26:58, user=user15, description=add patient representative list]\n" + 
				"   LogEntry[timestamp=2015/08/01 03:54:17, user=user10, description=update appointment requests]\n" + 
				"   LogEntry[timestamp=2016/12/12 06:28:13, user=user6, description=edit patient representative list]\n" + 
				"   LogEntry[timestamp=2015/02/10 16:12:23, user=user15, description=modify office visit information]\n" + 
				"   LogEntry[timestamp=2015/07/17 15:55:25, user=user8, description=save immunizations]\n" + 
				"   LogEntry[timestamp=2011/08/12 20:13:47, user=user1, description=create basic health information]\n" + 
				"   LogEntry[timestamp=2015/10/07 05:34:10, user=user7, description=edit email reminders]\n" + 
				"   LogEntry[timestamp=2009/01/03 10:35:47, user=user11, description=view email reminders]\n" + 
				"   LogEntry[timestamp=2010/06/27 03:45:37, user=user10, description=delete calendar]\n" + 
				"   LogEntry[timestamp=2010/07/14 12:53:15, user=user7, description=remove diagnoses]\n" + 
				"   LogEntry[timestamp=2014/10/06 13:11:31, user=user5, description=edit office visit information]\n" + 
				"   LogEntry[timestamp=2007/11/24 23:10:24, user=user19, description=update appointment requests]\n" + 
				"   LogEntry[timestamp=2015/03/29 13:38:31, user=user7, description=view demographics]\n" + 
				"   LogEntry[timestamp=2012/09/17 11:45:24, user=user16, description=create upcoming appointments]\n" + 
				"   LogEntry[timestamp=2013/05/14 21:16:36, user=user3, description=add upcoming appointments]\n" + 
				"   LogEntry[timestamp=2007/06/01 07:37:31, user=user3, description=print immunizations]\n" + 
				"   LogEntry[timestamp=2013/06/16 23:28:33, user=user3, description=add appointment requests]\n" + 
				"]", stm.getLogTreeLevelOrder());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.manager.SecurityTreeManager#readAttackGoal(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testReadAttackGoal() {
		@SuppressWarnings("unused")
		SecurityTreeManager stm = new SecurityTreeManager("input/ddos-preorder.txt", "input/ddos-postorder.txt");
		try {
			stm = new SecurityTreeManager("input/thisdoesntexist.txt", "input/ddos-postorder.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.manager.SecurityTreeManager#readLogFile(java.lang.String)}.
	 */
	@Test
	public void testReadLogFile() {
		@SuppressWarnings("unused")
		SecurityTreeManager stm = new SecurityTreeManager("input/sample-log.txt");
		try {
			stm = new SecurityTreeManager("input/thisdoesntexist.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
	}
}
