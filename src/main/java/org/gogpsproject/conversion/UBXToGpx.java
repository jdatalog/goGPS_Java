/*
 * Copyright (c) 2011 Eugenio Realini, Mirko Reguzzoni, Cryms sagl - Switzerland. All Rights Reserved.
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
package org.gogpsproject.conversion;

import java.io.File;
import java.util.Locale;
import org.gogpsproject.producer.gpx.GpxProducer;

import org.gogpsproject.producer.parser.ublox.UBXFileReader;

/**
 * @author Lorenzo Patocchi, cryms.com; Eugenio Realini, GReD srl
 *
 * Converts UBX binary file to RINEX
 *
 */
public class UBXToGpx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//force dot as decimal separator
		Locale.setDefault(new Locale("en", "US"));
		
		if(args.length<2){
			System.out.println("UBXToGpx <ubx file> <output directory>");
			return;
		}

		int p=0;
		String inFile = args[p++];
		String outDir = args[p++];

			System.out.println("in :"+inFile);
		
		GpxProducer rp = new GpxProducer();
		rp.setOutputDir(outDir);
		//rp.setFilename("rover_20190728074446.gpx");
		//rp.setFilename("rover_20190728093730.gpx");
		//rp.setFilename("rover_20190728123142.gpx");
		rp.setFilename("rover_20200311103620.gpx");

		UBXFileReader roverIn = new UBXFileReader(new File(inFile));
		try {
			roverIn.init();
			roverIn.addUBXStreamEventListener(rp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(roverIn.hasMoreObservations()){

                    roverIn.getNextObservations();
		}
		
		rp.streamClosed();
		System.out.println("END");
	}
}
