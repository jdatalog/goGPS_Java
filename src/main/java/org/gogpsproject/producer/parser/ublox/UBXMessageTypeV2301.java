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
public class UBXMessageTypeV2301 extends UBXMessageTypeV14 {

    public final static int CLASS_ESF = 0x10;
    public final static int CLASS_MGA = 0x13;
    public final static int CLASS_SEC = 0x27;
    public final static int CLASS_UPD = 0x09;
    
    public final static int ID_CFG_BATCH = 0x93;
    public final static int ID_CFG_DGNSS = 0x70;
    public final static int ID_CFG_DOSC = 0x61;
    public final static int ID_CFG_DYNSEED = 0x85;
    public final static int ID_CFG_ESRC = 0x60;
    public final static int ID_CFG_FIXSEED = 0x84;
    public final static int ID_CFG_GEOFENCE = 0x69;
    public final static int ID_CFG_HNR = 0x5C;
    public final static int ID_CFG_ODO = 0x1E;
    public final static int ID_CFG_PMS = 0x86;
    public final static int ID_CFG_PWR = 0x57;
    public final static int ID_CFG_SMGR = 0x62;
    public final static int ID_CFG_TMODE2 = 0x3D;
    public final static int ID_CFG_TMODE3 = 0x71;
    public final static int ID_CFG_TXSLOT = 0x53;
    public final static int ID_ESF_INS = 0x15;
    public final static int ID_ESF_RAW = 0x03;
    public final static int ID_ESF_STATUS = 0x10;
    public final static int ID_LOG_BATCH = 0x11;
    public final static int ID_LOG_RETRIEVEBATCH = 0x10;
    public final static int ID_MGA_ACK_DATA0  = 0x60;
    public final static int ID_MGA_ANO = 0x20;
    public final static int ID_MGA_BDS_ALM  = 0x03;
    public final static int ID_MGA_BDS_EPH  = 0x03;
    public final static int ID_MGA_BDS_HEALTH  = 0x03;
    public final static int ID_MGA_BDS_IONO  = 0x03;
    public final static int ID_MGA_BDS_UTC  = 0x03;
    public final static int ID_MGA_DBD = 0x80;
    public final static int ID_MGA_FLASH_ACK  = 0x21;
    public final static int ID_MGA_FLASH_DATA  = 0x21;
    public final static int ID_MGA_FLASH_STOP  = 0x21;
    public final static int ID_MGA_GAL_ALM  = 0x02;
    public final static int ID_MGA_GAL_EPH  = 0x02;
    public final static int ID_MGA_GAL_UTC  = 0x02;
    public final static int ID_MGA_GLO_ALM  = 0x06;
    public final static int ID_MGA_GLO_EPH  = 0x06;
    public final static int ID_MGA_GPS_ALM  = 0x00;
    public final static int ID_MGA_GPS_EPH  = 0x00;
    public final static int ID_MGA_GPS_HEALTH  = 0x00;
    public final static int ID_MGA_GPS_IONO  = 0x00;
    public final static int ID_MGA_GPS_UTC  = 0x00;
    public final static int ID_MGA_INI_CLKD  = 0x40;
    public final static int ID_MGA_INI_EOP  = 0x40;
    public final static int ID_MGA_INI_FREQ  = 0x40;
    public final static int ID_MGA_INI_POS_LLH  = 0x40;
    public final static int ID_MGA_INI_POS_XYZ  = 0x40;
    public final static int ID_MGA_INI_TIME_GNSS  = 0x40;
    public final static int ID_MGA_INI_TIME_UTC  = 0x40;
    public final static int ID_MGA_QZSS_ALM  = 0x05;
    public final static int ID_MGA_QZSS_EPH  = 0x05;
    public final static int ID_MGA_QZSS_HEALTH  = 0x05;
    public final static int ID_MON_BATCH = 0x32;
    public final static int ID_MON_GNSS = 0x28;
    public final static int ID_MON_PATCH = 0x27;
    public final static int ID_MON_SMGR = 0x2E;
    public final static int ID_NAV_ATT = 0x05;
    public final static int ID_NAV_EOE = 0x61;
    public final static int ID_NAV_GEOFENCE = 0x39;
    public final static int ID_NAV_HPPOSECEF = 0x13;
    public final static int ID_NAV_HPPOSLLH = 0x14;
    public final static int ID_NAV_ODO = 0x09;
    public final static int ID_NAV_ORB = 0x34;
    public final static int ID_NAV_RELPOSNED = 0x3C;
    public final static int ID_NAV_RESETODO = 0x10;
    public final static int ID_NAV_SAT = 0x35;
    public final static int ID_NAV_SVIN = 0x3B;
    public final static int ID_NAV_TIMEBDS = 0x24;
    public final static int ID_NAV_TIMEGAL = 0x25;
    public final static int ID_NAV_TIMEGLO = 0x23;
    public final static int ID_NAV_TIMELS = 0x26;
    public final static int ID_RXM_IMES = 0x61;
    public final static int ID_RXM_RAWX = 0x15;
    public final static int ID_RXM_RLM = 0x59;
    public final static int ID_RXM_RTCM = 0x32;
    public final static int ID_RXM_SFRBX = 0x13;
    public final static int ID_SEC_SIGN = 0x01;
    public final static int ID_SEC_UNIQID = 0x03;
    public final static int ID_TIM_DOSC = 0x11;
    public final static int ID_TIM_FCHG = 0x16;
    public final static int ID_TIM_HOC = 0x17;
    public final static int ID_TIM_SMEAS = 0x13;
    public final static int ID_TIM_SVIN = 0x04;
    public final static int ID_TIM_TOS = 0x12;
    public final static int ID_TIM_VCOCAL = 0x15;
    public final static int ID_UPD_SOS = 0x14;

}
