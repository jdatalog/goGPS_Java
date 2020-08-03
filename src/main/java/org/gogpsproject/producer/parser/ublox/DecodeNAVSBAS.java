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
public class DecodeNAVSBAS {
// 0 U4
//iTOW - ms 4 U1 geo - - PRN Number of the GEO where correction and
//integrity data is used from
//5 U1 mode - - SBAS Mode
//GPS time of week of the navigation epoch.
//See the description of iTOW for details.
//?
//?
//?
//6
//I1
//sys
//-
//-
//SBAS System (WAAS/EGNOS/...)
//?
//?
//?
//?
//?
//?
//7
//0 Disabled
//1 Enabled integrity
//3 Enabled test mode
//-1 Unknown
//0 WAAS
//1 EGNOS
//2 MSAS
//3 GAGAN
//16 GPS
//X1 service - - SBAS Services available
//bit 0 U :1 Ranging - - GEO may be used as ranging source
//bit 1 U :1 Corrections - - GEO is providing correction data
//bit 2 U :1 Integrity - - GEO is providing integrity
//bit 3 U :1 Testmode - - GEO is in test mode
//bit 4 U :1 Bad - - Problem with signal or broadcast data indicated
//8 U1 cnt - - Number of SV data following
//9 U1[3] reserved0 - - Reserved
//Start of repeated group ( cnt times)
//12 + n·12 U1 svid - - SV ID
//13 + n·12 U1 flags - - Flags for this SV
//14 + n·12 U1 udre - - Monitoring status
//15 + n·12 U1 svSys - - System (WAAS/EGNOS/...)
//same as SYS
//16 + n·12
//U1
//svService
//-
//-
//Services available
//same as SERVICE
//17 + n·12 U1 reserved1 - - Reserved
//18 + n·12 I2 prc - cm Pseudo Range correction in [cm]
//20 + n·12 U1[2] reserved2 - - Reserved
//22 + n·12 I2 ic - cm Ionosphere correction in [cm]
//End of repeated group ( cnt times)   
}
