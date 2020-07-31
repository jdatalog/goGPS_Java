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
public class UBXMessageTypeV14 {

    public final static int CLASS_ACK = 0x05; // ACK
    public final static int CLASS_AID = 0x0B; // AID
    public final static int CLASS_CFG = 0x06; // CFG
    public final static int CLASS_INF = 0x04; // INF
    public final static int CLASS_LOG = 0x21; // LOG
    public final static int CLASS_MON = 0x0A; // MON
    public final static int CLASS_NAV = 0x01; // NAV
    public final static int CLASS_RXM = 0x02; // RXM
    public final static int CLASS_TIM = 0x0D; // TIM

    public final static int ID_ACK_ACK = 0x01; // 2 Output Message Acknowledged
    public final static int ID_ACK_NAK = 0x00; // 2 Output Message Not-Acknowledged
    public final static int ID_AID_ALM = 0x30; // 0 Poll Request Poll GPS Aiding Almanac Data
    // 1 Poll Request Poll GPS Aiding Almanac Data for a SV
    // (8) or (40) Input/Output GPS Aiding Almanac Input/Output Message
    public final static int ID_AID_ALP = 0x50; // 0 + 2*N Input ALP file data transfer to the receiver
    // 1 Input Mark end of data transfer
    // 1 Output Acknowledges a data transfer
    // 1 Output Indicate problems with a data transfer
    // 24 Periodic/Polled Poll the AlmanacPlus status
    public final static int ID_AID_ALPSRV = 0x32; // 16 + 1*dataSize Input ALP server sends AlmanacPlus data to client
    // 16 Output ALP client requests AlmanacPlus data from server
    // 8 + 2*size Output ALP client sends AlmanacPlus data to server.
    public final static int ID_AID_AOP = 0x33; // 0 Poll request Poll AssistNow Autonomous data
    // 1 Poll request Poll AssistNow Autonomous data for one satellite
    // (60) or (204) Input/Output AssistNow Autonomous data
    public final static int ID_AID_DATA = 0x10; // 0 Poll Request Polls all GPS Initial Aiding Data
    public final static int ID_AID_EPH = 0x31; // 0 Poll Request Poll GPS Aiding Ephemeris Data
    // 1 Poll Request Poll GPS Aiding Ephemeris Data for a SV
    // (8) or (104) Input/Output GPS Aiding Ephemeris Input/Output Message
    public final static int ID_AID_HUI = 0x02; // 0 Poll Request Poll GPS Health, UTC and ionosphere parameters
    // 72 Input/Output GPS Health, UTC and ionosphere parameters
    public final static int ID_AID_INI = 0x01; // 0 Poll Request Poll GPS Initial Aiding Data
    // 48 Input/Output Aiding position, time, frequency, clock drift
    public final static int ID_AID_REQ = 0x00; // 0 Virtual Sends a poll (AID-DATA) for all GPS Aiding Data

    public final static int ID_CFG_ANT = 0x13; // 0 Poll Request Poll Antenna Control Settings
    // 4 Input/Output Antenna Control Settings
    public final static int ID_CFG_CFG = 0x09; // (12) or (13) Command Clear, Save and Load configurations
    public final static int ID_CFG_DAT = 0x06; // 0 Poll Request Poll Datum Setting
    // 44 Input Set User-defined Datum
    // 52 Output The currently defined Datum
    public final static int ID_CFG_GNSS = 0x3E; // 0 Poll Request Polls the configuration of the GNSS system configuration
    // 4 + 8*numConfigBlocks Input/Output GNSS system configuration
    public final static int ID_CFG_INF = 0x02; // 0 + 10*N Input/Output Information message configuration
    // 1 Poll INF message configuration for one protocol
    public final static int ID_CFG_ITFM = 0x39; // 0 Poll Request Polls the Jamming/Interference Monitor configuration.
    // 8 Command Jamming/Interference Monitor configuration.
    public final static int ID_CFG_LOGFILTER = 0x47; // 0 Poll Request Poll Data Logger filter Configuration
    // 12 Input/Output Data Logger Configuration
    public final static int ID_CFG_MSG = 0x01; // 2 Poll Request Poll a message configuration
    // 3 Input/Output Set Message Rate
    // 8 Input/Output Set Message Rate(s)
    public final static int ID_CFG_NAV5 = 0x24; // 0 Poll Request Poll Navigation Engine Settings
    // 36 Input/Output Navigation Engine Settings
    public final static int ID_CFG_NAVX5 = 0x23; // 0 Poll Request Poll Navigation Engine Expert Settings
    // 40 Input/Output Navigation Engine Expert Settings
    public final static int ID_CFG_NMEA = 0x17; // 0 Poll Request Poll the NMEA protocol configuration
    // 12 Input/Output NMEA protocol configuration
    // 4 Input/Output NMEA protocol configuration (deprecated)
    public final static int ID_CFG_PM2 = 0x3B; // 0 Poll Request Poll extended Power Management configuration
    // 44 Input/Output Extended Power Management configuration
    public final static int ID_CFG_PRT = 0x00; // 0 Poll Request Polls the configuration of the used I/O Port
    // 1 Poll Request Polls the configuration for one I/O Port
    // 20 Input/Output Port Configuration for DDC Port
    // 20 Input/Output Port Configuration for SPI Port
    // 20 Input/Output Port Configuration for UART
    // 20 Input/Output Port Configuration for USB Port
    public final static int ID_CFG_RATE = 0x08; // 0 Poll Request Poll Navigation/Measurement Rate Settings
    // 6 Input/Output Navigation/Measurement Rate Settings
    public final static int ID_CFG_RINV = 0x34; // 0 Poll Request Poll contents of Remote Inventory
    // 1 + 1*N Input/Output Contents of Remote Inventory
    public final static int ID_CFG_RST = 0x04; // 4 Command Reset Receiver / Clear Backup Data Structures
    public final static int ID_CFG_RXM = 0x11; // 0 Poll Request Poll RXM configuration
    // 2 Input/Output RXM configuration
    public final static int ID_CFG_SBAS = 0x16; // 0 Poll Request Poll contents of SBAS Configuration
    // 8 Input/Output SBAS Configuration
    public final static int ID_CFG_TP5 = 0x31; // 0 Poll Request Poll Time Pulse Parameters
    // 1 Poll Request Poll Time Pulse Parameters
    // 32 Input/Output Time Pulse Parameters
    public final static int ID_CFG_USB = 0x1B; // 0 Poll Request Poll a USB configuration
    // 108 Input/Output USB Configuration

    public final static int ID_INF_DEBUG = 0x04; // 0 + 1*N Output ASCII String output, indicating debug output
    public final static int ID_INF_ERROR = 0x00; // 0 + 1*N Output ASCII String output, indicating an error
    public final static int ID_INF_NOTICE = 0x02; // 0 + 1*N Output ASCII String output, with informational contents
    public final static int ID_INF_TEST = 0x03; // 0 + 1*N Output ASCII String output, indicating test output
    public final static int ID_INF_WARNING = 0x01; // 0 + 1*N Output ASCII String output, indicating a warning

    public final static int ID_LOG_CREATE = 0x07; // 8 Command Create Log File
    public final static int ID_LOG_ERASE = 0x03; // 0 Command Erase Logged Data
    public final static int ID_LOG_FINDTIME = 0x0E; // 12 Input Finds the index of the first log entry <= given time
    // 8 Output This message is the response to FINDTIME request.
    public final static int ID_LOG_INFO = 0x08; // 0 Poll Request Poll for log information
    // 48 Output Log information
    public final static int ID_LOG_RETRIEVE = 0x09; // 12 Command Request log data
    public final static int ID_LOG_RETRIEVEPOS = 0x0b; // 40 Output Position fix log entry
    public final static int ID_LOG_RETRIEVESTRING = 0x0d; // 16 + 1*byteCountOutput Byte string log entry
    public final static int ID_LOG_STRING = 0x04; // 0 + 1*N Command Store arbitrary string in on-board Flash memory

    public final static int ID_MON_HW = 0x09; // 60 Periodic/Polled Hardware Status
    public final static int ID_MON_HW2 = 0x0B; // 28 Periodic/Polled Extended Hardware Status
    public final static int ID_MON_IO = 0x02; // 0 + 20*N Periodic/Polled I/O Subsystem Status
    public final static int ID_MON_MSGPP = 0x06; // 120 Periodic/Polled Message Parse and Process Status
    public final static int ID_MON_RXBUF = 0x07; // 24 Periodic/Polled Receiver Buffer Status
    public final static int ID_MON_RXR = 0x21; // 1 Output Receiver Status Information
    public final static int ID_MON_TXBUF = 0x08; // 28 Periodic/Polled Transmitter Buffer Status
    public final static int ID_MON_VER = 0x04; // 0 Poll Request Poll Receiver/Software Version
    // 40 + 30*N Answer to Poll Receiver/Software Version

    public final static int ID_NAV_AOPSTATUS = 0x60; // 20 Periodic/Polled AssistNow Autonomous Status
    public final static int ID_NAV_CLOCK = 0x22; // 20 Periodic/Polled Clock Solution
    public final static int ID_NAV_DGPS = 0x31; // 16 + 12*numCh Periodic/Polled DGPS Data Used for NAV
    public final static int ID_NAV_DOP = 0x04; // 18 Periodic/Polled Dilution of precision
    public final static int ID_NAV_POSECEF = 0x01; // 20 Periodic/Polled Position Solution in ECEF
    public final static int ID_NAV_POSLLH = 0x02; // 28 Periodic/Polled Geodetic Position Solution
    public final static int ID_NAV_PVT = 0x07; // 84 Periodic/Polled Navigation Position Velocity Time Solution
    public final static int ID_NAV_SBAS = 0x32; // 12 + 12*cnt Periodic/Polled SBAS Status Data
    public final static int ID_NAV_SOL = 0x06; // 52 Periodic/Polled Navigation Solution Information
    public final static int ID_NAV_STATUS = 0x03; // 16 Periodic/Polled Receiver Navigation Status
    public final static int ID_NAV_SVINFO = 0x30; // 8 + 12*numCh Periodic/Polled Space Vehicle Information
    public final static int ID_NAV_TIMEGPS = 0x20; // 16 Periodic/Polled GPS Time Solution
    public final static int ID_NAV_TIMEUTC = 0x21; // 20 Periodic/Polled UTC Time Solution
    public final static int ID_NAV_VELECEF = 0x11; // 20 Periodic/Polled Velocity Solution in ECEF
    public final static int ID_NAV_VELNED = 0x12; // 36 Periodic/Polled Velocity Solution in NED

    public final static int ID_RXM_ALM = 0x30; // 0 Poll Request Poll GPS Constellation Almanac Data
    // 1 Poll Request Poll GPS Constellation Almanac Data for a SV
    // (8) or (40) Poll Answer / Periodic GPS Aiding Almanac Input/Output Message
    public final static int ID_RXM_EPH = 0x31; // 0 Poll Request Poll GPS Constellation Ephemeris Data
    // 1 Poll Request Poll GPS Constellation Ephemeris Data for a SV
    // (8) or (104) Poll Answer / Periodic GPS Aiding Ephemeris Input/Output Message
    public final static int ID_RXM_PMREQ = 0x41; // 8 Command Requests a Power Management task
    public final static int ID_RXM_RAW = 0x10; // 8 + 24*numSV Periodic/Polled Raw Measurement Data
    public final static int ID_RXM_SFRB = 0x11; // 42 Periodic Subframe Buffer
    public final static int ID_RXM_SVSI = 0x20; // 8 + 6*numSV Periodic/Polled SV Status Info

    public final static int ID_TIM_TM2 = 0x03; // 28 Periodic/Polled Time mark data
    public final static int ID_TIM_TP = 0x01; // 16 Periodic/Polled Time Pulse Timedata
    public final static int ID_TIM_VRFY = 0x06; // 20 Polled/Once Sourced Time Verification

    public final static int GPSFIX_NONE = 0x00;  // no fix
    public final static int GPSFIX_DRCKO = 0x01; // dead reckoning only
    public final static int GPSFIX_2D = 0x02;    // 2D-fix
    public final static int GPSFIX_3D = 0x03;    // 3D-fix
    public final static int GPSFIX_DRCKC = 0x04;    // GPS + dead reckoning combined
    public final static int GPSFIX_TIME = 0x05;    // Time only fix

    public final static String[] GPSFIX_STR = { "no fix",
                                                "dead reckoning only",
                                                "2D-fix",
                                                "3D-fix",
                                                "GPS + dead reckoning combined",
                                                "Time only fix"};
}
