/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
/*
 * This will drive the robot forwards to a waypoint on the field based on its 
 * original starting position.
 */
import org.usfirst.frc330.wpilibj.PIDGains;

public class DriveWaypointBackwardLegacy extends DriveWaypointBackward {
//	double leftDistance, rightDistance;
	
	public DriveWaypointBackwardLegacy(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
		this(x,y,tolerance,timeout,stopAtEnd, ChassisConst.DriveLow, ChassisConst.DriveHigh, ChassisConst.GyroDriveLow, ChassisConst.GyroDriveHigh);
	}
	
    public DriveWaypointBackwardLegacy(double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveLow, PIDGains driveHigh, PIDGains gyroLow, PIDGains gyroHigh) {
        super(x, y, tolerance, timeout, stopAtEnd, driveLow, driveHigh, gyroLow, gyroHigh);
    }

    protected boolean isFinished() {
        if ((Robot.chassis.leftDrivePID.onTarget() || Robot.chassis.rightDrivePID.onTarget()) || isTimedOut())
        {
                return true;            
        }
        return false;
    }
}
