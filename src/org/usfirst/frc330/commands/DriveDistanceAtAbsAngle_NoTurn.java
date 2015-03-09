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
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;
/**
 *
 */
public class  DriveDistanceAtAbsAngle_NoTurn extends DriveDistance{
    double angle;
    PIDGains gains, gyroLow, gyroHigh;
    
    public DriveDistanceAtAbsAngle_NoTurn(double distance, double angle)
    {
        this(distance, 6, angle, -1.0, true, ChassisConst.DriveLow, ChassisConst.DriveHigh, ChassisConst.GyroDriveLow, ChassisConst.GyroDriveHigh); //-1 means no timeout
    }
    
    public DriveDistanceAtAbsAngle_NoTurn(double distance, double angle, double tolerance)
    {
        this(distance, tolerance, angle, -1.0, true, ChassisConst.DriveLow, ChassisConst.DriveHigh, ChassisConst.GyroDriveLow, ChassisConst.GyroDriveHigh); //-1 means no timeout
    }
    
    public DriveDistanceAtAbsAngle_NoTurn(double distance, double tolerance, double angle, double timeout, boolean stopAtEnd, PIDGains driveLow, PIDGains driveHigh, PIDGains gyroLow, PIDGains gyroHigh)
    {
        super(distance, tolerance, timeout, stopAtEnd, driveLow, driveHigh);
        this.angle = angle;
        this.gyroHigh = gyroHigh;
        this.gyroLow = gyroLow;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
//    	leftDistance = leftDistance + Robot.chassis.getLeftDistance();
//        rightDistance = rightDistance + Robot.chassis.getRightDistance();
        super.initialize();
        if (Robot.chassis.isHighGear())         {
            gains = gyroHigh;
        }
        else {
            gains = gyroLow;
        }
        Robot.chassis.gyroPID.setPID(gains);
        Robot.chassis.gyroPID.setSetpoint(angle);
        Robot.chassis.gyroPID.enable();  
    }
}
