/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author The Cowboys
 */
public class Checker
{
    private String IpAddress;
    
    
    // This method checks the ip of the user, and checks if it matches the schools
    
    public boolean checker() throws UnknownHostException
    {
        IpAddress = InetAddress.getLocalHost().getHostAddress();

        String[] adr = IpAddress.split("\\.");
        for (int i = 0; i < adr.length - 1; i++)
        {
            if (adr[0].equals("172") && adr[1].equals("2") && adr[2].equals("161"))
            {
                return true;
            }
        }
        return false;
    }
}
