// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc330.commands;
import org.usfirst.frc330.Robot;
/**
 *
 */

public class DriveDistanceAtRelAngle_NoTurn extends DriveDistanceAtAbsAngle_NoTurn{
    
    public DriveDistanceAtRelAngle_NoTurn(double distance, double angle)
    {
        this(distance, 6, angle, 5, true);
    }
    
    public DriveDistanceAtRelAngle_NoTurn(double distance, double angle, double tolerance)
    {
        this(distance, tolerance, angle, 5, true);
    }
    
    public DriveDistanceAtRelAngle_NoTurn(double distance, double tolerance, double angle, double timeout, boolean stopAtEnd)
    {
        super(distance, tolerance, timeout, 0, stopAtEnd);
        this.angle = angle;
    }
        // Called just before this Command runs the first time
    protected void initialize() {
    	angle = angle + Robot.chassis.getAngle();
        super.initialize();
    }
}
