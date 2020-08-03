/*
 * Copyright (c) 2010 Eugenio Realini, Mirko Reguzzoni, Cryms sagl - Switzerland. All Rights Reserved.
 *
 * This file is part of goGPS Project (goGPS).
 *
 * goGPS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * goGPS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with goGPS.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.gogpsproject.producer.parser.ublox;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.gogpsproject.ephemeris.EphGps;
import org.gogpsproject.producer.Observations;
import org.gogpsproject.producer.StreamEventListener;
import org.gogpsproject.producer.StreamEventProducer;
import org.gogpsproject.producer.parser.IonoGps;

/**
 * <p>
 * Read and parse UBX messages
 * </p>
 *
 * @author Lorenzo Patocchi cryms.com, Eugenio Realini
 */
public class UBXReader implements StreamEventProducer {

    private InputStream in;
    private Vector<StreamEventListener> streamEventListeners = new Vector<StreamEventListener>();
    private Vector<UBXStreamEventListener> ubxStreamEventListener = new Vector<UBXStreamEventListener>();
    private Boolean debugModeEnabled = true;
    //	private StreamEventListener streamEventListener;

    boolean gpsEnable = true;  // enable GPS data reading
    boolean qzsEnable = true;  // enable QZSS data reading
    boolean gloEnable = true;  // enable GLONASS data reading	
    boolean galEnable = true;  // enable Galileo data reading
    boolean bdsEnable = false;  // enable BeiDou data reading

    private Boolean[] multiConstellation = {gpsEnable, qzsEnable, gloEnable, galEnable, bdsEnable};

    public UBXReader(InputStream is) {
        this(is, null);
    }

    public UBXReader(InputStream is, StreamEventListener eventListener) {
        this.in = is;
        addStreamEventListener(eventListener);
    }

    public UBXReader(InputStream is, StreamEventListener eventListener, UBXStreamEventListener ubxListener) {
        this.in = is;
        addStreamEventListener(eventListener);
    }

    public UBXReader(InputStream is, Boolean[] multiConstellation, StreamEventListener eventListener) {
        this.in = is;
        this.multiConstellation = multiConstellation;
        addStreamEventListener(eventListener);
    }

    public Object readMessageOld() throws IOException, UBXException {

        //	int data = in.read();
        //	if(data == 0xB5){
        int data = in.read();
        if (data == 0x62) {

            data = in.read(); // Class
            if (this.debugModeEnabled) {
                System.out.println("==> 0x" + Integer.toHexString(data));
            }
            boolean parsed = false;
            if (data == 0x02) { // RXM
                data = in.read(); // ID
                if (data == 0x10) { // RAW
                    // RMX-RAW
                    DecodeRXMRAW decodegps = new DecodeRXMRAW(in);
                    parsed = true;

                    Observations o = decodegps.decode(null);
                    if (o != null && this.debugModeEnabled) {
                        System.out.println("Decoded observations");
                    }
                    if (streamEventListeners != null && o != null) {
                        for (StreamEventListener sel : streamEventListeners) {
                            Observations oc = (Observations) o.clone();
                            sel.addObservations(oc);
                        }
                    }
                    return o;

                } else if (data == 0x15) { //RAWX
                    // RMX-RAWX
                    DecodeRXMRAWX decodegnss = new DecodeRXMRAWX(in, multiConstellation);
                    parsed = true;

                    Observations o = decodegnss.decode(null);
                    if (o != null && this.debugModeEnabled) {
                        System.out.println("Decoded observations");
                    }
                    if (streamEventListeners != null && o != null) {
                        for (StreamEventListener sel : streamEventListeners) {
                            Observations oc = (Observations) o.clone();
                            sel.addObservations(oc);
                        }
                    }
                    return o;

                }

            } else if (data == 0x0B) { // AID
                data = in.read(); // ID
                try {
                    if (data == 0x02) { // HUI
                        // AID-HUI (sat. Health / UTC / Ionosphere)
                        DecodeAIDHUI decodegps = new DecodeAIDHUI(in);
                        parsed = true;

                        IonoGps iono = decodegps.decode();
                        if (iono != null && this.debugModeEnabled) {
                            System.out.println("Decoded iono parameters");
                        }
                        if (streamEventListeners != null && iono != null) {
                            for (StreamEventListener sel : streamEventListeners) {
                                sel.addIonospheric(iono);
                            }
                        }
                        return iono;
                    } else if (data == 0x31) { // EPH
                        // AID-EPH (ephemerides)
                        DecodeAIDEPH decodegps = new DecodeAIDEPH(in);
                        parsed = true;

                        EphGps eph = decodegps.decode();
                        if (eph != null && this.debugModeEnabled) {
                            System.out.println("Decoded ephemeris for satellite " + eph.getSatID());
                        }
                        if (streamEventListeners != null && eph != null) {
                            for (StreamEventListener sel : streamEventListeners) {
                                sel.addEphemeris(eph);
                            }
                        }
                        return eph;
                    }
                } catch (UBXException ubxe) {
                    if (this.debugModeEnabled) {
                        System.out.println(ubxe);
                    }
                }
            } else {
                in.read(); // ID
            }
            if (!parsed) {

                // read non parsed message length
                int[] length = new int[2];
                length[1] = in.read();
                length[0] = in.read();

                int len = length[0] * 256 + length[1];
                if (this.debugModeEnabled) {
                    System.out.println("Warning: UBX (0x" + Integer.toHexString(data) + ") message not decoded; skipping " + len + " bytes");
                }
                for (int b = 0; b < len + 2; b++) {
                    in.read();
                }
            }
        } else if (this.debugModeEnabled) {
            System.out.println("Warning: wrong sync char 2 " + data + " 0x" + Integer.toHexString(data) + " [" + ((char) data) + "]");
        }
        //	}else{
        //		//no warning, may be NMEA
        //		//System.out.println("Warning: wrong sync char 1 "+data+" "+Integer.toHexString(data)+" ["+((char)data)+"]");
        //	}
        return null;
    }

    public Object readMessage() throws IOException, UBXException {

        UBXNavigationStatus s = null;
        //	int data = in.read();
        //	if(data == 0xB5){
        int classID = 0;
        int[] length = {0, 0};
        int len = 0;

        int msgClass = in.read();
        if (msgClass == 0x62) {

            msgClass = in.read(); // Class
            classID = in.read();  // ID

            switch (msgClass) {
                case UBXMessageTypeV2301.CLASS_NAV:
                    switch (classID) {
                        case UBXMessageTypeV2301.ID_NAV_POSLLH:
                            UBXPosition p = null;
                            DecodeNAVPOSLLH decodePositionLLH = new DecodeNAVPOSLLH(in);
                            p = decodePositionLLH.decode(null);

                            if (ubxStreamEventListener != null && p != null) {
                                for (UBXStreamEventListener sel : ubxStreamEventListener) {
                                    sel.addUBXPosition(p);
                                }
                            }
                            return p;
                        case UBXMessageTypeV2301.ID_NAV_STATUS:
                            DecodeNAVSTATUS decodeNavigationStatus = new DecodeNAVSTATUS(in);
                            s = decodeNavigationStatus.decode(null);
                            if (ubxStreamEventListener != null && s != null) {
                                for (UBXStreamEventListener sel : ubxStreamEventListener) {
                                    sel.addUBXNavigationStatus(s);
                                }
                            }
                            return s;
                        case UBXMessageTypeV2301.ID_NAV_DOP:
                            UBXDop d = null;
                            DecodeNAVPOSDOP decodeNavDop = new DecodeNAVPOSDOP(in);
                            d = decodeNavDop.decode(null);
                            if (ubxStreamEventListener != null && d != null) {
                                for (UBXStreamEventListener sel : ubxStreamEventListener) {
                                    sel.addUBXDop(d);
                                }
                            }
                            return d;
                        case UBXMessageTypeV2301.ID_NAV_PVT:
                            DecodeNAVPOSPVT decodeNavPVT = new DecodeNAVPOSPVT(in);
                            s = decodeNavPVT.decode(null);
                            if (ubxStreamEventListener != null && s != null) {
                                for (UBXStreamEventListener sel : ubxStreamEventListener) {
                                    sel.addUBXNavigationStatus(s);
                                }
                            }
                            return null;
                        default:
                            length[1] = in.read();
                            length[0] = in.read();
                            len = length[0] * 256 + length[1];
                            for (int b = 0; b < len + 2; b++) {
                                in.read();
                            }
                            if (this.debugModeEnabled) {
                                System.out.println("CLASS_NAV Not yeat parsed : " + Integer.toHexString(classID));
                            }
                            return null;
                    }
                case UBXMessageTypeV2301.CLASS_RXM:
                    Observations o = null;
                    switch (classID) {
                        case UBXMessageTypeV2301.ID_RXM_RAWX:
                            DecodeRXMRAWX decodegnss = new DecodeRXMRAWX(in, multiConstellation);

                            o = decodegnss.decode(null);
                            if (o != null && this.debugModeEnabled) {
                                System.out.println("Decoded observations");
                            }
                            if (streamEventListeners != null && o != null) {
                                for (StreamEventListener sel : streamEventListeners) {
                                    Observations oc = (Observations) o.clone();
                                    sel.addObservations(oc);
                                }
                            }
                            return o;
                        case UBXMessageTypeV2301.ID_RXM_RAW:
                            // RMX-RAWX
                            DecodeRXMRAW decodegps = new DecodeRXMRAW(in);

                            o = decodegps.decode(null);
                            if (o != null && this.debugModeEnabled) {
                                System.out.println("Decoded observations");
                            }
                            if (streamEventListeners != null && o != null) {
                                for (StreamEventListener sel : streamEventListeners) {
                                    Observations oc = (Observations) o.clone();
                                    sel.addObservations(oc);
                                }
                            }
                            return o;
                        case UBXMessageTypeV2301.ID_RXM_SFRBX:
                            dummyDecode(msgClass, classID);
                            if (this.debugModeEnabled) {
                                System.out.println("CLASS_RXM ID_RXM_SFRBX Not yeat parsed");
                            }
                            return null;
                        default:
                            dummyDecode(msgClass, classID);
                            if (this.debugModeEnabled) {
                                System.out.println("CLASS_RXM : " + Integer.toHexString(classID));
                            }
                            return null;
                    }
                case UBXMessageTypeV2301.CLASS_AID:
                    switch (classID) {
                        case UBXMessageTypeV2301.ID_AID_HUI:
                            // AID-HUI (sat. Health / UTC / Ionosphere)
                            DecodeAIDHUI decodeAidHui = new DecodeAIDHUI(in);

                            IonoGps iono = decodeAidHui.decode();
                            if (iono != null && this.debugModeEnabled) {
                                System.out.println("Decoded iono parameters");
                            }
                            if (streamEventListeners != null && iono != null) {
                                for (StreamEventListener sel : streamEventListeners) {
                                    sel.addIonospheric(iono);
                                }
                            }
                            return iono;
                        case UBXMessageTypeV2301.ID_AID_EPH:
                            // AID-EPH (ephemerides)
                            DecodeAIDEPH decodeAidEph = new DecodeAIDEPH(in);

                            EphGps eph = decodeAidEph.decode();
                            if (eph != null && this.debugModeEnabled) {
                                System.out.println("Decoded ephemeris for satellite " + eph.getSatID());
                            }
                            if (streamEventListeners != null && eph != null) {
                                for (StreamEventListener sel : streamEventListeners) {
                                    sel.addEphemeris(eph);
                                }
                            }
                            return eph;
                    }
                case UBXMessageTypeV2301.CLASS_ACK:

                    dummyDecode(msgClass, classID);
                    if (this.debugModeEnabled) {
//                        System.out.println("CLASS_ACK Not yeat parsed : " + Integer.toHexString(classID));
                    }
                    return null;
                default:
                    dummyDecode(msgClass, classID);
                    if (this.debugModeEnabled) {
                        System.out.println("Not yeat parsed : " + Integer.toHexString(msgClass) + " " + Integer.toHexString(classID));
                    }
                    return null;

            }

        } else if (this.debugModeEnabled) {
            System.out.println("Warning: wrong sync char 2 " + classID + " 0x" + Integer.toHexString(classID) + " [" + ((char) classID) + "]");
        }

        return null;
    }

    /**
     * @return the streamEventListener
     */
    @SuppressWarnings("unchecked")
    @Override
    public Vector<StreamEventListener> getStreamEventListeners() {
        return (Vector<StreamEventListener>) streamEventListeners.clone();
    }

    /**
     * @param streamEventListener the streamEventListener to set
     */
    @Override
    public void addStreamEventListener(StreamEventListener streamEventListener) {
        if (streamEventListener == null) {
            return;
        }
        if (!streamEventListeners.contains(streamEventListener)) {
            this.streamEventListeners.add(streamEventListener);
        }
    }

    /* (non-Javadoc)
	 * @see org.gogpsproject.StreamEventProducer#removeStreamEventListener(org.gogpsproject.StreamEventListener)
     */
    @Override
    public void removeStreamEventListener(
            StreamEventListener streamEventListener) {
        if (streamEventListener == null) {
            return;
        }
        if (streamEventListeners.contains(streamEventListener)) {
            this.streamEventListeners.remove(streamEventListener);
        }
    }

    public Vector<UBXStreamEventListener> getUbxStreamListener() {
        return ubxStreamEventListener;
    }

    public void addUbxStreamEventListener(UBXStreamEventListener streamEventListener) {

        if (ubxStreamEventListener == null) {
            return;
        }
        if (!ubxStreamEventListener.contains(streamEventListener)) {
            this.ubxStreamEventListener.add(streamEventListener);
        }
    }

    public void removeUbxStreamEventListener(
            UBXStreamEventListener streamEventListener) {
        if (ubxStreamEventListener == null) {
            return;
        }
        if (ubxStreamEventListener.contains(streamEventListener)) {
            this.ubxStreamEventListener.remove(streamEventListener);
        }
    }

    public void enableDebugMode(Boolean enableDebug) {
        this.debugModeEnabled = enableDebug;
    }

    private void dummyDecode(int msgClass, int classID) throws IOException {
        // read non parsed message length
        int[] length = new int[2];

        length[1] = in.read();
        length[0] = in.read();

        int len = length[0] * 256 + length[1];
        if (this.debugModeEnabled) {
//            System.out.println("Warning: " + Integer.toHexString(msgClass) + " " + Integer.toHexString(classID) + ") message not decoded; skipping " + len + " bytes");
        }
        for (int b = 0; b < len + 2; b++) {
            in.read();
        }
    }
}
