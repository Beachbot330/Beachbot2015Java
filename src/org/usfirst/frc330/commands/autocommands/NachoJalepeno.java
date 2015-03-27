// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands.autocommands;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.CanSnatchFinish;
import org.usfirst.frc330.commands.commandgroups.CanSnatchStart;
import org.usfirst.frc330.conditionalWrappers.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NachoJalepeno extends BBCommandGroup {
    
    public  NachoJalepeno() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	// Put boolean option on Dashboard
    	String ccw = new String("CCW Rotation");
    	SmartDashboard.putBoolean(ccw, false);
    	
    	addSequential(new ShiftLow());
    	//BBCommand driveCommand = new DriveDistanceAtAbsAngle_NoTurn(12, 2, 0, 1, false); //  distance, tolerance, angle, timeout, stopAtEnd
    	//addParallel(driveCommand); //distance, distanceTolerance, angle, timeout, stopAtEnd    	
    	BBCommandGroup snatch = new CanSnatchStart();
    	addParallel(snatch);
    	//addSequential(new CheckDone(driveCommand));
    	
    	addSequential(new Wait(1.3));
    	
    	PIDGains DriveLow_forty = new PIDGains(0.1, 0, 0, 0, 0.4, 0.05, "DriveLow_forty");
    	//double p, double i, double d, double f, double maxOutput, double maxOutputStep, String name
    	
    	addSequential(new DriveDistanceAtAbsAngle(-18, 2, 0, 1, false, DriveLow_forty, ChassisConst.DriveHigh, ChassisConst.GyroDriveLow, ChassisConst.GyroDriveHigh));
    	//distance, distanceTolerance, angle, timeout, stopAtEnd, driveLow, driveHigh, gyroLow, gyroHigh
    	addSequential(new CheckDoneGroup(snatch));
    	
    	BBCommandGroup snatchFinish = new CanSnatchFinish();
    	addParallel(snatchFinish);
    	addSequential(new Wait(0.2));
    	addSequential(new SetWristAngle(140.0, 5.0, 1.0)); //Angle tolerance timeout
    	addSequential(new SetWristAngle(180.0, 5.0, 1.0)); //Angle tolerance timeout
    	addSequential(new CheckDoneGroup(snatchFinish));
    	
    	addSequential(new DriveDistanceAtAbsAngle(72, 2, 0, 2, false)); //distance, distanceTolerance, angle, timeout, stopAtEnd
    	// 72 was 60
    	//addSequential(new NachoJalapenoTurn(ccw));
    	addSequential(new TurnGyroAbs(90,5));
    	addSequential(new SetArmPosition(200.0, 1.0, 2.0));
    	addSequential(new CenterGrabberOpen());
    	addSequential(new SetArmPosition(217.0, 1.0, 1.5));
    	//addSequential(new NachoJalapenoDrive(ccw));
    	addSequential(new DriveDistanceAtRelAngle_NoTurn(-9.0, 0.0, 2.0, 1.0));
    	addSequential(new Wait(0.4));
    	addSequential(new CenterGrabberClose());
    }
}
