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
import org.usfirst.frc330.commands.ArmPID_on;
import org.usfirst.frc330.commands.CenterGrabberClose;
import org.usfirst.frc330.commands.CheckDone;
import org.usfirst.frc330.commands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.DriveDistanceAtRelAngle_NoTurn;
import org.usfirst.frc330.commands.DriveWaypoint;
import org.usfirst.frc330.commands.DriveWaypointBackward;
import org.usfirst.frc330.commands.LeftGrabberClose;
import org.usfirst.frc330.commands.OpenAllGrabbers;
import org.usfirst.frc330.commands.RightGrabberClose;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetLiftPosition;
import org.usfirst.frc330.commands.SetMastPosition;
import org.usfirst.frc330.commands.SetWristAngle;
import org.usfirst.frc330.commands.ShiftHigh;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.TurnGyroAbs;
import org.usfirst.frc330.commands.TurnGyroWaypoint;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.WristPID_on;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.LiftPos;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class IRISingleGripper2CansStacked extends BBCommandGroup {
    
    public  IRISingleGripper2CansStacked() {
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
    	
        final PIDGains DriveLow = new PIDGains(0.1,0,0,0,0.4,0.05, "DriveLow");
    	
    	//Hold joint angles, open grabbers, close center grabber
    	addParallel(new ArmPID_on());
    	addParallel(new WristPID_on());
    	addSequential(new ShiftLow());
    	addSequential(new OpenAllGrabbers());
    	addSequential(new SetMastPosition(90.0, 1.0, 0.3));
    	addSequential(new SetWristAngle(0.0));
    	addSequential(new SetArmPosition(5.0, 0.5, 1.0)); //angle tolerance timeout
    	addSequential(new Wait(0.1));
    	BBCommand DriveCmd = new DriveDistanceAtAbsAngle_NoTurn(102, 2, 0, 7, false,DriveLow,ChassisConst.DriveHigh,ChassisConst.GyroDriveLow, ChassisConst.GyroDriveHigh); //distance, distanceTolerance, angle, timeout, stopAtEnd
	
    	
    	//Drive into Auto Zone
    	addParallel(DriveCmd);
    	addSequential(new Wait(0.1));

    	addSequential(new CenterGrabberClose());
    	
    	addSequential(new Wait(1.0));
    	
    	// Raise the arm
    	addSequential(new SetArmPosition(80.0, 3.0, 0.3));
    	


    }
}
