package com.github;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.gogpsproject.GoGPS;
import org.gogpsproject.NavigationProducer;
import org.gogpsproject.Observations;
import org.gogpsproject.ObservationsProducer;
import org.gogpsproject.parser.rinex.RinexNavigation;
import org.gogpsproject.parser.rinex.RinexNavigationParser;
import org.gogpsproject.parser.rinex.RinexObservationParser;
import org.gogpsproject.parser.ublox.DecodeRXMRAWX;
import org.gogpsproject.parser.ublox.UBXException;
import org.gogpsproject.parser.ublox.UBXFileReader;
import org.gogpsproject.producer.KmlProducer;

import java.io.*;

public class Issues {

  /**
   * Code by @MartaBanach
   * See https://github.com/goGPS-Project/goGPS_Java/issues/27#issuecomment-290527675
   */
  @Test
  public void i27(){
    ObservationsProducer roverIn = new UBXFileReader(new File("./src/test/resources/ublox.ubx"));
    ObservationsProducer masterIn = new RinexObservationParser(new File("./src/test/resources/vrs.17o"));
//    NavigationProducer navigationIn = new RinexNavigationParser(new File("./src/test/resources/vrs.17n"));
    NavigationProducer navigationIn = new RinexNavigation( RinexNavigation.NASA_NAVIGATION_DAILY ); 

    double goodDopThreshold = 3.0; // Threshold - pr�g graniczny
    int TimeSampleDelaySec = 30;
    String outPath = "./out.kml";
    // should be tuned according to the dataset;
    // use '0' to disable timestamps in the KML String outPath = "./test/out.kml";
    try {
        KmlProducer kml = new KmlProducer(outPath, goodDopThreshold, TimeSampleDelaySec);

        navigationIn.init();
        roverIn.init();
        masterIn.init();

        int dynamicModel = GoGPS.DYN_MODEL_STATIC; //may be also set to constant acceleration or static
        GoGPS goGPS = new GoGPS(navigationIn, roverIn, masterIn)
                         .addPositionConsumerListener(kml)
                         .setDynamicModel(dynamicModel)
                         .setCutoff(0)
        
//        .runCodeStandalone();
//        .runKalmanFilterCodePhaseStandalone();
                         .runKalmanFilterCodePhaseDoubleDifferences(); // -> Missing M or R obs
        
        roverIn.release(true, 10000);
        masterIn.release(true, 10000);
        navigationIn.release(true, 10000);
        
        goGPS.runUntilFinished();
    } catch (Exception e) {
        e.printStackTrace();
    }

  }
}