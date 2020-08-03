/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gogpsproject.producer.parser.ublox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.EndianUtils;

/**
 *
 * @author tophe
 */
public class DecodeNAVPOSPVT {

    InputStream in;

    public DecodeNAVPOSPVT(InputStream in) {
        this.in = in;
    }

    public UBXNavigationStatus decode(OutputStream logos) throws IOException, UBXException {
         int len = EndianUtils.readSwappedShort(in);

         long iTOW = EndianUtils.readSwappedUnsignedInteger(in);    //  0 U4 iTOW    ms GPS time of week of the navigation epoch. See the description of iTOW for details.
        int year = EndianUtils.readSwappedUnsignedShort(in);	//	4	U2	year	Year (UTC)
        int month = in.read();
        int day = in.read();
        int hour = in.read();
        int min = in.read();
        int sec = in.read();
        int valid = in.read();
        long tAcc = EndianUtils.readSwappedUnsignedInteger(in);
        int nano = EndianUtils.readSwappedInteger(in);
        int fixType= in.read();
        int flags = in.read();
        int flags2 = in.read();
        int numSV = in.read();
        int lon = EndianUtils.readSwappedInteger(in);     //  4 I4 lon    deg Longitude
        int lat = EndianUtils.readSwappedInteger(in);     //  8 I4 lat    deg Latitude
        int height = EndianUtils.readSwappedInteger(in);  // 12 I4 height mm Height above ellipsoid
        int hMSL = EndianUtils.readSwappedInteger(in);    // 16 I4 hMSL   mm Height above mean sea level
        int hAcc = EndianUtils.readSwappedInteger(in);
        
        long vAcc = EndianUtils.readSwappedUnsignedInteger(in);
        int velN = EndianUtils.readSwappedInteger(in); 
        int velE = EndianUtils.readSwappedInteger(in); 
        int velD = EndianUtils.readSwappedInteger(in); 
        int gSpeed = EndianUtils.readSwappedInteger(in); 
        int headMot = EndianUtils.readSwappedInteger(in); 
        long sAcc = EndianUtils.readSwappedUnsignedInteger(in);
        long headAcc = EndianUtils.readSwappedUnsignedInteger(in);
        
        int pDOP = EndianUtils.readSwappedUnsignedShort(in);	     
        int reserved1 = in.read();
        int headVeh = EndianUtils.readSwappedInteger(in); 
        int magDec = EndianUtils.readSwappedShort(in);
        int magAcc = EndianUtils.readSwappedUnsignedShort(in);	
        
// GNSSfix Type:
//   0: no fix
//   1: dead reckoning only
//   2: 2D-fix
//   3: 3D-fix
//   4: GNSS + dead reckoning combined
//   5: time only fix



         System.out.println("NAVPOSPVT -[" + year + "/"+month+"/"+day + " "+hour+ " "+min+" "+sec+" ]" + (lon * 1e-7) + "/" + (lat * 1e-7) + "/" + ((1.0 * height) / 1000.0));
        return new UBXNavigationStatus(  iTOW,   fixType,   flags,   0,   flags2,   0,   0);
    }
     
}
