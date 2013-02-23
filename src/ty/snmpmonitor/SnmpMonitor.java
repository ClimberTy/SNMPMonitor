package ty.snmpmonitor;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import ty.snmpmonitor.ISnmpMonitor;

class openXmlData {
	
	public Document XmlData () {
		Document xmlData = null; 
		try { DocumentBuilderFactory docBuilderFactory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		xmlData = (Document) docBuilder.parse 
				(new File("/root/workspace/snmpmonitor/snmpData.xml"));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return xmlData;
	}
}

public class SnmpMonitor extends UnicastRemoteObject implements ISnmpMonitor {
	
	private static final long serialVersionUID = 1L;
	public int agent;
	public int start;
	public String command;
	
	public void setCommand(String cmd) {
		command = cmd;
		
	}
	public String getCommand() {
		return command;
	}
	
	public SnmpMonitor() throws RemoteException {
		super();
	}
	
	/**
	 * Function: getNumDevices
	 * Args: None
	 * Return: SnmpAgents
	 * Info: This function parses the snmpData.xml to determine how many
	 * SNMP enabled agents there are by parsing the XML for the 
	 * tag name of <SNMPAgent>.
	 */
	public String getNumDevices() throws RemoteException {
		System.out.println("Retrieving Total Devices.");
		String SnmpAgents = null;
		try {
			openXmlData parse = new openXmlData();
			Document doc = parse.XmlData();
		
			NodeList snmpAgents = doc.getElementsByTagName("SNMPAgent");
			int totalAgents = snmpAgents.getLength();
			SnmpAgents = "Total number of agents : " + totalAgents;
		} catch (Exception e){
			e.printStackTrace();
		}
		return SnmpAgents;
	}
	
	/**
	 * Function: getNumDevices
	 * Args: None
	 * Return: SnmpAgents
	 * Info: This function parses the snmpData.xml to determine how many
	 * SNMP enabled agents there are by parsing the XML for the 
	 * tag name of <SNMPAgent>.
	 */
	public String getDeviceNames() throws RemoteException {
		System.out.println("Retrieving Device Names.");
		String agentNames = null;
		
			CharArrayWriter charOut = new CharArrayWriter();
			openXmlData parse = new openXmlData();
			Document doc = parse.XmlData();
			
			NodeList snmpAgents = doc.getElementsByTagName("SNMPAgent");
			
			try{
			for(int s=0; s<snmpAgents.getLength() ; s++) {
				
				Node snmpAgentNode = snmpAgents.item(s);
				if(snmpAgentNode.getNodeType() == Node.ELEMENT_NODE) {
		
				Element snmpAgentElement = (Element)snmpAgentNode; 
				
				//-------
				NodeList operatingSystemList = 
						snmpAgentElement.getElementsByTagName("OperatingSystem");
				Element operatingSystemElement = (Element)operatingSystemList.item(0);
				NodeList textOSList = operatingSystemElement.getChildNodes();
				charOut.write("Agent " + (s+1) + " : " +
						((Node)textOSList.item(0)).getNodeValue().trim() + "\n");
				}//end of if clause

				} 
				
			}catch (Exception e){
				e.printStackTrace();
			}
			agentNames = charOut.toString();
			return agentNames;
	}
	
	/**
	 * Function: getDeviceStatus
	 * Args: None
	 * Return: dataOut
	 * Info: This function parses the snmpData.xml and breaks it down to 
	 * its element tag names and then appends those results to
	 * a CharArrayWriter. The files then are converted to a String variable
	 * and then returned.
	 */
	public String getDeviceStatus() throws RemoteException, IOException {
		String dataOut = null;
		
		System.out.println("Parsing XML Data.");

		CharArrayWriter charOut = new CharArrayWriter();
		openXmlData parse = new openXmlData();
		Document doc = parse.XmlData();

		// normalize text representation
		doc.getDocumentElement().normalize ();
		/*System.out.println ("Root element of the XML is " 
			+ doc.getDocumentElement().getNodeName());*/

		NodeList snmpAgents = doc.getElementsByTagName("SNMPAgent");
		if (agent == 4)
			agent = snmpAgents.getLength();
		try {		
		for(int s=start; s<agent ; s++) {
			
			Node snmpAgentNode = snmpAgents.item(s);
			if(snmpAgentNode.getNodeType() == Node.ELEMENT_NODE) {
	
			Element snmpAgentElement = (Element)snmpAgentNode; 
			
			//-------
			NodeList operatingSystemList = 
					snmpAgentElement.getElementsByTagName("OperatingSystem");
			Element operatingSystemElement = (Element)operatingSystemList.item(0);
			NodeList textOSList = operatingSystemElement.getChildNodes();
			charOut.write("Operating System : " + 
					((Node)textOSList.item(0)).getNodeValue().trim() + "\n");
	
			//------- 
			NodeList systemNameList = 
					snmpAgentElement.getElementsByTagName("SystemName");
			Element systemNameElement = (Element)systemNameList.item(0);
	
			NodeList textSystemNameList = systemNameElement.getChildNodes();
			charOut.write("System Name : " + 
					((Node)textSystemNameList.item(0)).getNodeValue().trim() + "\n");
	
			//----
			NodeList systemContactList = 
					snmpAgentElement.getElementsByTagName("SystemContact");
			Element systemContactElement = (Element)systemContactList.item(0);
	
			NodeList textSystemContactList = systemContactElement.getChildNodes();
			charOut.write("System Contact : " + 
					((Node)textSystemContactList.item(0)).getNodeValue().trim() + "\n");
	
			//------
			NodeList systemTypeList = 
					snmpAgentElement.getElementsByTagName("SystemType");
			Element systemTypeElement = (Element)systemTypeList.item(0);
	
			NodeList textSystemType = systemTypeElement.getChildNodes();
			charOut.write("System Type : " + 
					((Node)textSystemType.item(0)).getNodeValue().trim() + "\n");
	
			//------
			NodeList systemDescList = 
					snmpAgentElement.getElementsByTagName("SystemDesc");
			Element systemDescElement = (Element)systemDescList.item(0);
	
			NodeList textsystemDesc = systemDescElement.getChildNodes();
			charOut.write("System Desc : " + 
					((Node)textsystemDesc.item(0)).getNodeValue().trim() + "\n");
	
			//------
			NodeList systemProcessorList = 
					snmpAgentElement.getElementsByTagName("SystemProcessor");
			Element systemProcessorElement = (Element)systemProcessorList.item(0);
	
			NodeList textSystemProcessor = systemProcessorElement.getChildNodes();
			charOut.write("System Processor : " + 
					((Node)textSystemProcessor.item(0)).getNodeValue().trim() + "\n");
	
			//------
			NodeList systemDriveList = 
					snmpAgentElement.getElementsByTagName("SystemDrive");
			Element systemDriveElement = (Element)systemDriveList.item(0);
	
			NodeList textSystemDrive = systemDriveElement.getChildNodes();
			charOut.write("System Drive : " + 
					((Node)textSystemDrive.item(0)).getNodeValue().trim() + "\n");
	
			//------
			NodeList systemDriveSizeList = 
					snmpAgentElement.getElementsByTagName("SystemDriveSize");
			Element systemDriveSizeElement = (Element)systemDriveSizeList.item(0);
	
			NodeList textSystemDriveSize = systemDriveSizeElement.getChildNodes();
			charOut.write("System Drive Size : " + 
					((Node)textSystemDriveSize.item(0)).getNodeValue().trim() + "\n");
	
			//------
			NodeList systemMemoryList = 
					snmpAgentElement.getElementsByTagName("SystemMemory");
			Element systemMemoryElement = (Element)systemMemoryList.item(0);
	
			NodeList textSystemMemory = 
					systemMemoryElement.getChildNodes();
			charOut.write("System Memory : " + 
					((Node)textSystemMemory.item(0)).getNodeValue().trim() + "\n");
	
			//------
			NodeList systemUptimeList = 
					snmpAgentElement.getElementsByTagName("SystemUptime");
			Element systemUptimeElement = (Element)systemUptimeList.item(0);
	
			NodeList textSystemUptime = systemUptimeElement.getChildNodes();
			charOut.write("System Uptime : " + 
					((Node)textSystemUptime.item(0)).getNodeValue().trim() + "\n");
	
			//------
			
			NodeList systemProcessesList = 
					snmpAgentElement.getElementsByTagName("SystemProcesses");
			Element systemProcessesElement = (Element)systemProcessesList.item(0);
	
			NodeList textSystemProcesses = systemProcessesElement.getChildNodes();
			charOut.write("System Processes : " + 
					((Node)textSystemProcesses.item(0)).getNodeValue().trim()+ "\n");
	
			//------
			charOut.write("\n");
			}//end of if clause

			} 
			
		}catch (Exception e){
			e.printStackTrace();
		}
		dataOut = charOut.toString();
		return dataOut;
	}
	
	/**
	 * Function: getDeviceData
	 * Args: None
	 * Return: null
	 * Info: This function is a executable command for the snmpWalk.py Python
	 * script. This is intended for the Python script to gather new SNMP data
	 * directly from the SNMP agents. From there the Python script saves the 
	 * data in an XML formatted document for the rest of the Java application
	 * to parse.
	 */
	public String getDeviceData() throws RemoteException, IOException {
		String pySnmpWalkPath = "/root/workspace/snmpmonitor/xmlparser/snmpWalk.py";
		
		System.out.println("Gathering New SNMP Data.");
		SnmpMonitor getData = new SnmpMonitor();
		
		getData.setCommand(pySnmpWalkPath);
		Runtime snmpRt = Runtime.getRuntime();
		@SuppressWarnings("unused")
		Process snmpPr = snmpRt.exec(getData.getCommand());
		
		//Read the output from the SNMP Python Script - Debug Only
		/*BufferedReader snmpBfr = new BufferedReader(
				new InputStreamReader(snmpPr.getInputStream()));
		String line = "";
			while((line = snmpBfr.readLine()) != null){
				System.out.println(line);
		}*/
		return null;
	}
    
    public int getSpecficAgent() throws RemoteException {
    	return agent;
    }
    
	public void setAgentId(int agentID) throws RemoteException {
		agent = agentID;
		
	}

	public int getForLoopStart() throws RemoteException {

		return start;
	}
	
	public void setForLoopStart(int startPos) throws RemoteException {
		start = startPos;
	}

}

