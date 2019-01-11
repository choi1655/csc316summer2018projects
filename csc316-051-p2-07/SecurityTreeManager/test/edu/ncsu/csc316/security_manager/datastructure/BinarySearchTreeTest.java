/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.security_manager.objects.LogEntry;

/**
 * Tests functionality for {@link edu.ncsu.csc316.security_manager.datastructure.BinarySearchTree}.
 * 
 * @author John Choi
 * @version 07022018
 */
public class BinarySearchTreeTest {

	private LogEntry sample = new LogEntry("9-13-2015", "2:58:49", "user2", "save patient list");
	private LogEntry sample1 = new LogEntry("12-18-2012", "16:25:58", "user18", "view diagnoses");
	private LogEntry sample2 = new LogEntry("7-19-2013", "22:49:10", "user1", "edit email reminders");
	private LogEntry sample3 = new LogEntry("7-10-2015", "0:26:58", "user15", "add patient representative list");
	private LogEntry sample4 = new LogEntry("8-1-2015", "3:54:17", "user10", "update appointment requests");
	private LogEntry sample5 = new LogEntry("7-10-2015", "6:28:13", "user6", "edit patient representative list");
	
	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.BinarySearchTree#insert(java.lang.Comparable, java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		BinarySearchTree<String, LogEntry> bst = new BinarySearchTree<>();
		bst.insert(sample.getDate(), sample);
		bst.insert(sample1.getDate(), sample1);
		bst.insert(sample2.getDate(), sample2);
		bst.insert(sample3.getDate(), sample3); //duplicate
		bst.insert(sample4.getDate(), sample4);
		bst.insert(sample5.getDate(), sample5); //duplicate
		assertEquals("LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]", bst.lookup("07-19-2013").getData().toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.BinarySearchTree#lookup(java.lang.Comparable)}.
	 */
	@Test
	public void testLookup() {
		BinarySearchTree<String, LogEntry> bst = new BinarySearchTree<>();
		bst.insert(sample.getDate(), sample);
		bst.insert(sample1.getDate(), sample1);
		bst.insert(sample2.getDate(), sample2);
		bst.insert(sample3.getDate(), sample3);
		bst.insert(sample4.getDate(), sample4);
		bst.insert(sample5.getDate(), sample5);
		
		//lookup
		assertEquals("LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]", bst.lookup("07-19-2013").getData().toString());
		
		//bst itself wont handle duplicates but the manager will
		assertEquals("LogEntry[timestamp=2015/07/10 06:28:13, user=user6, description=edit patient representative list]", bst.lookup("07-10-2015").getData().toString());
		
		
		StringBuilder sb = new StringBuilder();
//		CustomArrayList<LogEntry> removedLogs = new CustomArrayList<>();
		String lookupResult = bst.lookup("07-10-2015").getData().toString();
		sb.append(lookupResult);
		sb.append("\n");
		if (bst.lookup("07-10-2015").getDuplicates() != 0) {
			for (int i = 1; i <= bst.lookup("07-10-2015").getDuplicates(); i++) {
				lookupResult = bst.lookup("07-10-2015").getDuplicateNodes().get(i).toString();
				sb.append(lookupResult);
				sb.append("\n");
			}
		}
//		System.out.println(sb.toString());
		assertEquals("LogEntry[timestamp=2015/07/10 06:28:13, user=user6, description=edit patient representative list]\n"
				+ "LogEntry[timestamp=2015/07/10 00:26:58, user=user15, description=add patient representative list]\n", sb.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.BinarySearchTree#remove(Comparable)}.
	 */
//	@Test
//	public void testRemove() {
//		BinarySearchTree<String, LogEntry> bst = new BinarySearchTree<>();
//		bst.insert(sample.getDate(), sample);
//		bst.insert(sample1.getDate(), sample1);
//		bst.insert(sample2.getDate(), sample2);
//		bst.insert(sample3.getDate(), sample3);
//		bst.insert(sample4.getDate(), sample4);
//		bst.insert(sample5.getDate(), sample5);
//		
//		//lookup
//		assertEquals("LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]", bst.lookup("07-19-2013").getData().toString());
//		
//		//try to remove
//		assertEquals("2013/07/19", bst.remove("07-19-2013").getFormattedDate());
//		assertEquals("2012/12/18", bst.remove("12-18-2012").getFormattedDate());
//		
//		bst = new BinarySearchTree<>();
//		bst.insert(sample.getDate(), sample);
//		bst.insert(sample1.getDate(), sample1);
//		bst.insert(sample2.getDate(), sample2);
//		bst.insert(sample3.getDate(), sample3);
//		bst.insert(sample4.getDate(), sample4);
//		bst.insert(sample5.getDate(), sample5);
//		assertEquals("2013/07/19", bst.remove("07-19-2013").getFormattedDate());
//		
//		bst = new BinarySearchTree<>();
//		bst.insert(sample.getDate(), sample);
//		bst.insert(sample1.getDate(), sample1);
//		bst.insert(sample2.getDate(), sample2);
//		bst.insert(sample3.getDate(), sample3);
//		bst.insert(sample4.getDate(), sample4);
//		bst.insert(sample5.getDate(), sample5);
//		assertEquals("LogEntry[timestamp=2015/07/10 00:26:58, user=user15, description=add patient representative list]", bst.remove("07-10-2015").toString());
//		assertEquals("LogEntry[timestamp=2015/07/10 06:28:13, user=user6, description=edit patient representative list]", bst.remove("07-10-2015").toString());
//	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.BinarySearchTree#printLevelOrder()}.
	 */
	@Test
	public void testPrintLevelOrder() {
		BinarySearchTree<String, LogEntry> bst = new BinarySearchTree<>();
		bst.insert(sample.getDate(), sample);
		bst.insert(sample1.getDate(), sample1);
		bst.insert(sample2.getDate(), sample2);
		bst.insert(sample3.getDate(), sample3);
		bst.insert(sample4.getDate(), sample4);
		bst.insert(sample5.getDate(), sample5);
		
		System.out.println(bst.printLevelOrder());
		assertEquals("   LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]\n" + 
				"   LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]\n" + 
				"   LogEntry[timestamp=2012/12/18 16:25:58, user=user18, description=view diagnoses]\n" + 
				"   LogEntry[timestamp=2015/07/10 06:28:13, user=user6, description=edit patient representative list]\n" + 
				"   LogEntry[timestamp=2015/08/01 03:54:17, user=user10, description=update appointment requests]", bst.printLevelOrder());
	}
}
