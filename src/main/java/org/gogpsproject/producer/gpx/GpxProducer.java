/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gogpsproject.producer.gpx;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.gogpsproject.positioning.Time;
import org.gogpsproject.producer.parser.ublox.UBXDop;
import org.gogpsproject.producer.parser.ublox.UBXMessageTypeV14;
import org.gogpsproject.producer.parser.ublox.UBXNavigationStatus;
import org.gogpsproject.producer.parser.ublox.UBXPosition;
import org.gogpsproject.producer.parser.ublox.UBXStreamEventListener;

/**
 *
 * @author tophe
 */
public class GpxProducer implements UBXStreamEventListener {

    private static DecimalFormat coordF = new DecimalFormat("0.00000000", new DecimalFormatSymbols(Locale.US));
    private static DecimalFormat altF = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    private static DecimalFormat dopF = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    private static String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<gpx  version=\"1.1\"\n   creator=\"Jdatalog converter\"\n"
            + "   xmlns=\"http://www.topografix.com/GPX/1/1\"\n"
            + "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "   xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\">";
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("", Locale.US);

    private HashMap<Long, UBXPosition> hmap = new HashMap<Long, UBXPosition>();
    private ArrayList<Long> iTOWs = null;

    private String outFilename;
    private String outputDir = "./test";
    private FileOutputStream fos = null;
    private PrintStream ps = null;

    private Long minGpsTime = Long.MAX_VALUE;
    private Long maxGpsTime = Long.MIN_VALUE;
    private long latMin = Long.MAX_VALUE, latMax = Long.MIN_VALUE, lonMin = Long.MAX_VALUE, lonMax = Long.MIN_VALUE;

    public final static String[] GPSFIX_STR = {"nofix",
        "dead reckoning only",
        "2d",
        "3d",
        "GPS + dead reckoning combined",
        "Time only fix"};

    public void setOutputDir(String outDir) {
        this.outputDir = outDir;
    }

    public void setFilename(String outFilename) {
        this.outFilename = outFilename;
        try {
            fos = new FileOutputStream(this.outputDir + "/" + outFilename, false);
            ps = new PrintStream(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void streamClosed() {
        UBXPosition p = null;
        UBXNavigationStatus s = null;
        UBXDop d = null;

        int gpsFix = 0;

        iTOWs = new ArrayList(hmap.keySet());

        Collections.sort(iTOWs);

        this.writeHeaderSection(ps);
        this.writeTrack(ps);
        ps.println("</gpx>");

        try {
            ps.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUBXPosition(UBXPosition p) {
        Long iTOW = p.getiTOW();
        if (iTOW>0) {
            if (minGpsTime > iTOW) {
                minGpsTime = iTOW;
            }
            if (maxGpsTime < iTOW) {
                maxGpsTime = iTOW;
            }

            if (latMin > p.getLat()) {
                latMin = p.getLat();
            }
            if (latMax < p.getLat()) {
                latMax = p.getLat();
            }
            if (lonMin > p.getLon()) {
                lonMin = p.getLon();
            }
            if (lonMax < p.getLon()) {
                lonMax = p.getLon();
            }

            if (!hmap.containsKey(iTOW)) {
                // Check status before adding
                hmap.put(iTOW, p);
            } else {
                ((UBXPosition) hmap.get(iTOW)).set(p);
            }
        }
    }

    @Override
    public void addUBXDop(UBXDop d) {
        Long iTOW = d.getiTOW();

        if (hmap.containsKey(iTOW)) {
            ((UBXPosition) hmap.get(iTOW)).setDop(d);
        } else {
            hmap.put(iTOW, new UBXPosition(iTOW, d));
        }
    }

    @Override
    public void addUBXNavigationStatus(UBXNavigationStatus s) {
        Long iTOW = s.getiTOW();
        int gpsFix = s.getGpsFix();
        int flags = s.getFlags();

        if (gpsFix <= 0x05) {
            System.out.println("[" + iTOW + "] " + UBXMessageTypeV14.GPSFIX_STR[gpsFix] + " " + (flags & 0x01) + "|" + (flags & 0x02) + " (" + (flags & 0xF) + ")");
        } else {
            System.out.println("[" + iTOW + "] Unknown gpsFix status (" + gpsFix + ")");
        }
        if (hmap.containsKey(iTOW)) {
            ((UBXPosition) hmap.get(iTOW)).setStatus(s);
        } else {
            hmap.put(iTOW, new UBXPosition(iTOW, s));
        }
    }

    private void writeHeaderSection(PrintStream pw) {
        double lon = 0.0d, alt = 0.0d;

        pw.println(GpxProducer.header);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat shf = new SimpleDateFormat("HH:mm:ss", Locale.US);
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(Time.gpsToUnixTime(this.minGpsTime / 1000d, 2024));

        pw.println("<metadata>\n");
        pw.println("<time>" + sdf.format(date.getTime()) + "T" + shf.format(date.getTime()) + "Z</time>");

        lon = ((lon = ((double) this.lonMin * 1e-7d)) < 180) ? lon : lon - 360;
        pw.print("<bounds minlat=\"" + coordF.format(((double) this.latMin * 1e-7d)) + "\" minlon=\"" + coordF.format(lon));
        //  p = log.getMax();
        lon = ((lon = ((double) this.lonMax * 1e-7d)) < 180) ? lon : lon - 360;
        pw.println("\" maxlat=\"" + coordF.format(((double) this.latMax * 1e-7d)) + "\" maxlon=\"" + coordF.format(lon) + "\"/>");
        pw.println("</metadata>\n");

    }

    private void writeTrack(PrintStream pw) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat shf = new SimpleDateFormat("HH:mm:ss", Locale.US);
        GregorianCalendar date = new GregorianCalendar();
        double lon = 0.0d;
        float alt = 0.0f;
        UBXPosition p = null;
        UBXNavigationStatus s = null;
        UBXDop d = null;

        pw.println("<trk>\n<name>" + "MyTrack" + "</name>\n<trkseg>");

        for (Long iTOW : iTOWs) {
            p = hmap.get(iTOW);
            if (p.isValid()) {
                s = p.getStatus();
                d = p.getDop();
                date.setTimeInMillis(Time.gpsToUnixTime((double) iTOW / 1000d, 2024));
                lon = ((lon = ((double) p.getLon() * 1e-7d)) < 180) ? lon : lon - 360;
                pw.print("<trkpt lat=\"" + coordF.format(((double) p.getLat() * 1e-7d)));
                pw.println("\" lon=\"" + coordF.format(lon) + "\">");
                alt = (float) p.gethMSL() / 1000f;
                System.out.println((float) p.gethMSL() / 1000f + " / " + (float) p.getHeight() / 1000f + " [" + (((float) p.getHeight() / 1000f) - ((float) p.gethMSL() / 1000f)) + "]");
                pw.println("<ele>" + altF.format(alt) + "</ele>");
                pw.println("<time>" + sdf.format(date.getTime()) + "T" + shf.format(date.getTime()) + "Z</time>");
                // pw.println("<geoidheight>" + altF.format(pt.getGeoidCor()) + "</geoidheight>");
                if (null != d) {
                    pw.println("<hdop>" + dopF.format((float) d.gethDOP() / 100f) + "</hdop>");
                    pw.println("<pdop>" + dopF.format((float) d.getpDOP() / 100f) + "</pdop>");
                    pw.println("<vdop>" + dopF.format((float) d.getvDOP() / 100f) + "</vdop>");
                }
                if (null != s) {
                    int gpsFix = s.getGpsFix();
                    int flags = s.getFlags();
                    if (gpsFix == UBXMessageTypeV14.GPSFIX_NONE) {
                        pw.println("<fix>none</fix>");
                    } else if ((flags & 0x02) == 0x02) {
                        pw.println("<fix>dgps</fix>");
                    } else {
                        switch (gpsFix) {
                            case UBXMessageTypeV14.GPSFIX_2D:
                                pw.println("<fix>2d</fix>");
                                break;
                            case UBXMessageTypeV14.GPSFIX_3D:
                                pw.println("<fix>3d</fix>");
                                break;
                        }
                    }

                }
                pw.println("</trkpt>");

            }
        }

        pw.println("</trkseg>\n</trk>");

    }

}
