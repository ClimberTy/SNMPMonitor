package ty.snmpmonitor;

import java.rmi.Naming;

import ty.snmpmonitor.SnmpMonitor;


public class SnmpMonitorServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		      SnmpMonitor SnmpMonitor = new SnmpMonitor();
		      Naming.rebind("SnmpMonitor", SnmpMonitor);
		      System.out.println("Server Started.");
		    }
		    catch(Exception e) {
		      System.out.println("Exception: " + e);
		    }

	}

}
