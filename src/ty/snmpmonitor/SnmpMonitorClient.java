package ty.snmpmonitor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class SnmpMonitorClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		
		try {
		      String snmpMonitorURL = "rmi://localhost/SnmpMonitor";
		      ISnmpMonitor iSnmpMonitor = (ISnmpMonitor) Naming.lookup(snmpMonitorURL);
		      
		      //Client Connection and Main Menu
		      System.out.println("Client Connected... \n");
		      System.out.println("##########################");
		      System.out.println("#  Simple SNMP Monitor   #");
		      System.out.println("# Creator: D. Tyler Long #");
		      System.out.println("# Class: CMIS 445        #");
		      System.out.println("##########################\n");
		      System.out.println("Retrieving SNMP Data...");
		      iSnmpMonitor.getDeviceData();
		      System.out.println(iSnmpMonitor.getNumDevices());
		      System.out.println("\nPlease select which SNMP Device Agent below...");
		      System.out.println(iSnmpMonitor.getDeviceNames());
		      System.out.println("Which Agent (1, 2, or 3) or (4 for All): ");
		      Scanner scan = new Scanner(System.in);
		      int userInput = scan.nextInt();
		      System.out.println("Gathering data from Agent: " + userInput);
		      if (userInput == 1){
		    	  iSnmpMonitor.setAgentId(1);
		    	  iSnmpMonitor.setForLoopStart(0);
		      } else if (userInput == 2) {
		    	  iSnmpMonitor.setAgentId(2);
		    	  iSnmpMonitor.setForLoopStart(1);
		      }else if (userInput == 3) {
		    	  iSnmpMonitor.setAgentId(3);
		    	  iSnmpMonitor.setForLoopStart(2);
		      }else {
		    	  iSnmpMonitor.setAgentId(4);
		    	  iSnmpMonitor.setForLoopStart(0);
		      }
		      
	          System.out.println("\nCURRENT SNMP DATA: \n");
	          System.out.println(iSnmpMonitor.getDeviceStatus());
	          
		}catch (Exception e) {
		      System.out.println("Exception: " + e);
		    }
	}

}
