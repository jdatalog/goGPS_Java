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
public class UBXDop {

    long iTOW;	//	0	U4	-	iTOW	ms
    int gDOP;	//	4	U2		gDOP	Geometric DOP
    int pDOP;	//	6	U2		pDOP	Position DOP
    int tDOP;	//	8	U2		tDOP	Time DOP
    int vDOP;	//	10	U2		vDOP	Vertical DOP
    int hDOP;	//	12	U2		hDOP	Horizontal DOP
    int nDOP;	//	14	U2		nDOP	Northing DOP
    int eDOP;	//	16	U2		eDOP	Easting DOP

    public UBXDop(long iTOW, int gDOP, int pDOP, int tDOP, int vDOP, int hDOP, int nDOP, int eDOP) {
        this.iTOW = iTOW;
        this.gDOP = gDOP;
        this.pDOP = pDOP;
        this.tDOP = tDOP;
        this.vDOP = vDOP;
        this.hDOP = hDOP;
        this.nDOP = nDOP;
        this.eDOP = eDOP;
    }

    public long getiTOW() {
        return iTOW;
    }

    public int getgDOP() {
        return gDOP;
    }

    public int getpDOP() {
        return pDOP;
    }

    public int gettDOP() {
        return tDOP;
    }

    public int getvDOP() {
        return vDOP;
    }

    public int gethDOP() {
        return hDOP;
    }

    public int getnDOP() {
        return nDOP;
    }

    public int geteDOP() {
        return eDOP;
    }
    
    
}
