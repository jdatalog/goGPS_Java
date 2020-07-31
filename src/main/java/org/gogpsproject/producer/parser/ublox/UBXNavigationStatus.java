/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gogpsproject.producer.parser.ublox;

/**
 *
 * @author tophe
 */
public class UBXNavigationStatus {

    private long iTOW;	 // 0    U4      -       iTOW    ms      GPS time of week of the navigation epoch.
                         //			      See the description of iTOW for details.
    private int gpsFix;  //4    U1	      gpsFix	      0x00 = no fix
                         //			              0x01 = dead reckoning only
                         //			              0x02 = 2D-fix
                         //			              0x03 = 3D-fix
                         //			              0x04 = GPS + dead reckoning combined
                         //			              0x05 = Time only fix
                         //			              0x06..0xff = reserved    
    private int flags;	 // 5    X1	      flags	      Navigation Status Flags
    private int fixStat; // 6    X1	      fixStat	      Fix Status Information
    private int flags2;  // 7    X1	      flags2	      further information about navigation output 
    private long ttff;	 //  8    U4	      ttff    ms      Time to first fix (millisecond time tag)
    private long msss;	 //  12   U4	      msss    ms      Milliseconds since Startup / Reset  

    public UBXNavigationStatus(long iTOW, int gpsFix, int flags, int fixStat, int flags2, long ttff, long msss) {
        this.iTOW = iTOW;
        this.gpsFix = gpsFix;
        this.flags = flags;
        this.fixStat = fixStat;
        this.flags2 = flags2;
        this.ttff = ttff;
        this.msss = msss;
    }

    public long getiTOW() {
        return iTOW;
    }

    public int getGpsFix() {
        return gpsFix;
    }

    public int getFlags() {
        return flags;
    }

    public int getFixStat() {
        return fixStat;
    }

    public int getFlags2() {
        return flags2;
    }

    public long getTtff() {
        return ttff;
    }

    public long getMsss() {
        return msss;
    }

}
