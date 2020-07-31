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
public class UBXPosition {

    private long iTOW;    //  0 U4 iTOW    ms GPS time of week of the navigation epoch. See the description of iTOW for details.
    private int lon;     //  4 I4 lon    deg Longitude
    private int lat;     //  8 I4 lat    deg Latitude
    private int height;  // 12 I4 height mm Height above ellipsoid
    private int hMSL;    // 16 I4 hMSL   mm Height above mean sea level
    private int hAcc;    // 20 u4 hAcc   deg mm Horizontal accuracy estimate
    private UBXNavigationStatus status = null;
    private UBXDop dop = null;
    private boolean valid = false;

    public UBXPosition(long iTOW, int lon, int lat, int height, int hMSL, int hAcc) {
        this.iTOW = iTOW;
        this.lon = lon;
        this.lat = lat;
        this.height = height;
        this.hMSL = hMSL;
        this.hAcc = hAcc;

    }

    public UBXPosition(long iTOW, UBXNavigationStatus status) {
        this.iTOW = iTOW;
        this.lon = -1;
        this.lat = -1;
        this.height = -1;
        this.hMSL = -1;
        this.hAcc = -1;
        this.status = status;
    }

    public UBXPosition(long iTOW, UBXDop dop) {
        this.iTOW = iTOW;
        this.lon = -1;
        this.lat = -1;
        this.height = -1;
        this.hMSL = -1;
        this.hAcc = -1;
        this.dop = dop;
    }

    public long getiTOW() {
        return iTOW;
    }

    public int getLon() {
        return lon;
    }

    public int getLat() {
        return lat;
    }

    public int getHeight() {
        return height;
    }

    public int gethMSL() {
        return hMSL;
    }

    public int gethAcc() {
        return hAcc;
    }

    public UBXNavigationStatus getStatus() {
        return status;
    }

    public UBXDop getDop() {
        return dop;
    }

    public boolean isValid() {
        return valid;
    }

    public void setStatus(UBXNavigationStatus status) {
        this.status = status;
        this.valid = (status.getGpsFix() > 0);
    }

    public void setDop(UBXDop dop) {
        this.dop = dop;
    }

    public void set(UBXPosition p) {
        this.iTOW = p.getiTOW();
        this.lon = p.getLon();
        this.lat = p.getLat();
        this.height = p.getHeight();
        this.hMSL = p.gethMSL();
        this.hAcc = p.gethAcc();
        this.valid = ((status != null) && (status.getGpsFix() > 0));

    }

    public String toString() {
        return "[" + iTOW + "]" + (lon * 1e-7) + "/" + (lat * 1e-7) + "/" + ((1.0 * height) / 1000.0);
    }
}
