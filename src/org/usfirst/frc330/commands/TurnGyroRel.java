// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc330.commands;
import org.usfirst.frc330.Robot;
 
/**
 *
 */
public class  TurnGyroRel extends TurnGyroAbs{
    double origAngle = 0;
    public TurnGyroRel(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this(angle, 0, 15, false);
    }
    
    public TurnGyroRel(double angle, double tolerance)
    {
        this(angle, tolerance, 15, false);
    }
    
    public TurnGyroRel(double angle, double tolerance, double timeout, boolean stopAtEnd) {
        super(angle,tolerance,timeout,stopAtEnd,true);
        origAngle = angle;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        angle = angle+Robot.chassis.getAngle();
        super.initialize();
    }

    protected void end() {
        super.end(); //To change body of generated methods, choose Tools | Templates.
        angle = origAngle;
    }
}
