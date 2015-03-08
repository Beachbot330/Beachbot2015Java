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
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class  DriveDistanceAtAbsAngle extends BBCommandGroup{
    double angle;
    double maxoutput = 0;
    double stepSize;
    double origDistance = 0;
    
    public DriveDistanceAtAbsAngle(double distance, double angle)
    {
    	addSequential(new TurnGyroAbs(angle, 5));
        addSequential(new DriveDistanceAtAbsAngle_NoTurn(distance, 6, angle, 5, true,  ChassisConst.DriveLow, ChassisConst.DriveHigh, ChassisConst.GyroLow, ChassisConst.GyroHigh));
    }
    
    public DriveDistanceAtAbsAngle(double distance, double distanceTolerance, double angle, double timeout, boolean stopAtEnd)
    {
        addSequential(new TurnGyroAbs(angle,5));
        addSequential(new DriveDistanceAtAbsAngle_NoTurn(distance, distanceTolerance, angle, timeout, stopAtEnd, ChassisConst.DriveLow, ChassisConst.DriveHigh, ChassisConst.GyroLow, ChassisConst.GyroHigh));
        this.angle = angle;
    }
    
    public DriveDistanceAtAbsAngle(double distance, double distanceTolerance, double angle, double timeout, boolean stopAtEnd, PIDGains driveLow, PIDGains driveHigh, PIDGains gyroLow, PIDGains gyroHigh)
    {
        addSequential(new TurnGyroAbs(angle,5));
        addSequential(new DriveDistanceAtAbsAngle_NoTurn(distance, distanceTolerance, angle, timeout, stopAtEnd, driveLow, driveHigh, gyroLow, gyroHigh));
        this.angle = angle;
    }
}
