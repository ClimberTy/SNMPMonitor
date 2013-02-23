package ty.snmpmonitor;

import java.io.*;
import java.rmi.*;

public interface ISnmpMonitor extends Remote {
    
    public String getDeviceStatus () throws RemoteException, IOException;
    
    public String getNumDevices () throws RemoteException;
    
    public String getDeviceNames () throws RemoteException;
    
    public String getDeviceData() throws RemoteException, IOException;
    
    public void setAgentId(int agentID) throws RemoteException;
    
    public int getSpecficAgent() throws RemoteException;
    
    public void setForLoopStart(int startPos) throws RemoteException;
    
    public int getForLoopStart() throws RemoteException;
}
