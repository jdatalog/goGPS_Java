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
public class DecodeNAVPOSLLH {

    InputStream in;

    public DecodeNAVPOSLLH(InputStream in) {
        this.in = in;
    }

    public UBXPosition decode(OutputStream logos) throws IOException, UBXException {
        // parse little Endian data
        int len = EndianUtils.readSwappedShort(in);
        long iTOW = EndianUtils.readSwappedUnsignedInteger(in);    //  0 U4 iTOW    ms GPS time of week of the navigation epoch. See the description of iTOW for details.
        int lon = EndianUtils.readSwappedInteger(in);     //  4 I4 lon    deg Longitude
        int lat = EndianUtils.readSwappedInteger(in);     //  8 I4 lat    deg Latitude
        int height = EndianUtils.readSwappedInteger(in);  // 12 I4 height mm Height above ellipsoid
        int hMSL = EndianUtils.readSwappedInteger(in);    // 16 I4 hMSL   mm Height above mean sea level
        int hAcc = EndianUtils.readSwappedInteger(in);
        int vAcc = EndianUtils.readSwappedInteger(in);
        System.out.println("[" + iTOW + "]" + (lon * 1e-7) + "/" + (lat * 1e-7) + "/" + ((1.0 * height) / 1000.0)+" - ["+hAcc+ "/"+vAcc+"]");
        return new UBXPosition(iTOW, lon, lat, height, hMSL, hAcc);
    }
}
