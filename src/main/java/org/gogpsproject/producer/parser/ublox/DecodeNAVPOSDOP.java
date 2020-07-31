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
public class DecodeNAVPOSDOP {

    private InputStream in;

    public DecodeNAVPOSDOP(InputStream in) {
        this.in = in;
    }

    public UBXDop decode(OutputStream logos) throws IOException, UBXException {
        // parse little Endian data
        int len = EndianUtils.readSwappedShort(in);
        long iTOW = EndianUtils.readSwappedUnsignedInteger(in);	//	0	U4	-	iTOW	ms
        int gDOP = EndianUtils.readSwappedUnsignedShort(in);	//	4	U2		gDOP	Geometric DOP
        int pDOP = EndianUtils.readSwappedUnsignedShort(in);	//	6	U2		pDOP	Position DOP
        int tDOP = EndianUtils.readSwappedUnsignedShort(in);	//	8	U2		tDOP	Time DOP
        int vDOP = EndianUtils.readSwappedUnsignedShort(in);	//	10	U2		vDOP	Vertical DOP
        int hDOP = EndianUtils.readSwappedUnsignedShort(in);	//	12	U2		hDOP	Horizontal DOP
        int nDOP = EndianUtils.readSwappedUnsignedShort(in);	//	14	U2		nDOP	Northing DOP
        int eDOP = EndianUtils.readSwappedUnsignedShort(in);	//	16	U2		eDOP	Easting DOP
        // System.out.println("[" + iTOW + "]" + (gDOP/100.0) + "/" + (pDOP/100.0) + "/" + (tDOP/100.0)
        //                                    + (vDOP/100.0) + "/" + (hDOP/100.0) + "/" + (nDOP/100.0) 
        //                                    + "/" + (eDOP/100.0) );
        return new UBXDop( iTOW,  gDOP,  pDOP,  tDOP,  vDOP,  hDOP,  nDOP,  eDOP);
    }
}
