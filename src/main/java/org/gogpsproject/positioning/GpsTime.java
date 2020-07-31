/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gogpsproject.positioning;

/**
 *
 * @author tophe
 */
public class GpsTime {

    private static Long[] leaps = {46828800l, 78364801l, 109900802l, 173059203l, 252028804l, 315187205l,
        346723206l, 393984007l, 425520008l, 457056009l, 504489610l, 551750411l, 599184012l,
        820108813l, 914803214l, 1025136015l, 1119744016l, 1167264017l};

    public static boolean isLeap(Long gpsTime) {
        for (Long l : GpsTime.leaps) {
            if (l == gpsTime) {
                return true;
            }
        }
        return false;
    }
    
    public static int countLeaps (Long gpsTime) {
        int nLeaps = 0;
        for (Long l : GpsTime.leaps) {
            if (gpsTime >= l) {
               nLeaps++;
            }
        } 
        return nLeaps;
    }
// Convert GPS Time to Unix Time
   public static Long gps2unix(Long gpsTime){
     // Add offset in seconds
     Long unixTime = gpsTime + 315964800;

     unixTime += countLeaps(gpsTime);
     
    // if (isLeap(gpsTime)) {
    //    unixTime += 0.5;
    // }
     return unixTime;
   }
}

