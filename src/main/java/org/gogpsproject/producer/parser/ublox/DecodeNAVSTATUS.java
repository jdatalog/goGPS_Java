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
import org.gogpsproject.Status;

/**
 *
 * @author tophe
 */
public class DecodeNAVSTATUS {


    InputStream in;

    public DecodeNAVSTATUS(InputStream in) {
        this.in = in;
    }

    public UBXNavigationStatus decode(OutputStream logos) throws IOException, UBXException {
        // parse little Endian data
        int len = EndianUtils.readSwappedShort(in);

        long iTOW = EndianUtils.readSwappedUnsignedInteger(in);
        int gpsFix = in.read();
        int flags = in.read();
        int fixStat = in.read();
        int flags2 = in.read();
        long ttff = EndianUtils.readSwappedUnsignedInteger(in);
        long msss = EndianUtils.readSwappedUnsignedInteger(in);
        // System.out.println( "["+iTOW+"]" +String.format("%8s", Integer.toBinaryString(gpsFix)).replaceAll(" ", "0")
        //                                 +"|"+String.format("%8s",Integer.toBinaryString( flags)).replaceAll(" ", "0")
        //                                 +"|"+String.format("%8s",Integer.toBinaryString(fixStat)).replaceAll(" ", "0")
        //                                 +"|"+String.format("%8s",Integer.toBinaryString(flags2)).replaceAll(" ", "0"));
        return new UBXNavigationStatus(  iTOW,   gpsFix,   flags,   fixStat,   flags2,   ttff,   msss);
    }

}
