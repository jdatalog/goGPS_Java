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
public class DecodeRXMSFRBX {
    
//    0 U1 gnssId - - GNSS identifier (see Satellite Numbering)
//1 U1 svId - - Satellite identifier (see Satellite Numbering)
//2 U1 reserved0 - - Reserved
//3 U1 freqId - - Only used for GLONASS: This is the frequency slot + 7
//(range from 0 to 13)
//4 U1 numWords - - The number of data words contained in this message
//(up to 10, for currently supported signals)
//5 U1 chn - - The tracking channel number the message was
//received on
//6 U1 version - - Message version, (0x02 for this version)
//7 U1 reserved1 - - Reserved
//- - The data words
//Start of repeated group ( numWords times)
//8 + n·4
//U4
//dwrd
//End of repeated group ( numWords times)
    
    
}
