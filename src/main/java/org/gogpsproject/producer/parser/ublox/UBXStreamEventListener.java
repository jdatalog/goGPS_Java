/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gogpsproject.producer.parser.ublox;

import org.gogpsproject.ephemeris.EphGps;
import org.gogpsproject.producer.Observations;
import org.gogpsproject.producer.parser.IonoGps;

/**
 *
 * @author tophe
 */
public interface UBXStreamEventListener {
	public void streamClosed();
	public void addUBXPosition(UBXPosition p);
	public void addUBXDop(UBXDop d);
	public void addUBXNavigationStatus(UBXNavigationStatus s);
}
