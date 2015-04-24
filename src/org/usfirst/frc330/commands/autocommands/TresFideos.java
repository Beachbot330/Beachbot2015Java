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
import org.usfirst.frc330.commands.DriveDistanceAtRelAngle_NoTurn;
import org.usfirst.frc330.commands.DriveWaypoint;
import org.usfirst.frc330.commands.DriveWaypointBackward;
import org.usfirst.frc330.commands.DriveWaypointBackwardLegacy;
import org.usfirst.frc330.commands.DriveWaypointLegacy;
import org.usfirst.frc330.commands.LeftGrabberClose;
import org.usfirst.frc330.commands.OpenAllGrabbers;
import org.usfirst.frc330.commands.PrintLine;
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
public class TresFideos extends BBCommandGroup {
    
    public  TresFideos() {
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
    	
    	PIDGains driveLow = new PIDGains(0.1,0,0,0,0.8,ChassisConst.defaultMaxOutputStep, "DriveLow");
    	PIDGains gyroDriveLow = new PIDGains(0.03,0,0,0,1,1, "GyroDriveLow");
    	PIDGains gyroTurnLow = new PIDGains(0.03,0,0,0,0.5,1,"GyroTurnLow");
    	
    	//Hold joint angles, open grabbers, close center grabber
    	addParallel(new ArmPID_on());
    	addParallel(new WristPID_on());
    	addSequential(new ShiftLow());
    	addSequential(new OpenAllGrabbers());
    	addSequential(new SetMastPosition(90.0, 1.0, 0.3));
    	addParallel(new SetWristAngle(0.0));
    	addParallel(new SetArmPosition(-35.0, 1.0, 0.1)); //angle tolerance timeout
    	addParallel(new RightGrabberClose());
    	addSequential(new Wait(0.1));	
    	
    	// Raise the arm
    	addParallel(new SetWristAngle(5.0));
    	addSequential(new SetArmPosition(-22.0, 3.0, 0.3));
    	addParallel(new SetLiftPosition(LiftPos.justOverOneTote));
    	
    	//Reverse to give myself room to work
    	//BBCommand driveCommand = new DriveDistanceAtAbsAngle_NoTurn(-14.0 , 0.0, 2.0);  //Dist Angl Tol
    	BBCommand driveCommand = new DriveWaypointBackward(0.0,-10, 2.0, 0.5, false, driveLow, ChassisConst.DriveHigh, gyroDriveLow, ChassisConst.GyroDriveHigh);  //X, Y, Tol, timeout, stop
    	addSequential(driveCommand);
    	//addSequential(new Wait(0.2));
    	
    	//Drive to second can
    	addSequential(new TurnGyroWaypoint(-10.0, 16.0, 1.0, 1.5, gyroTurnLow, ChassisConst.GyroTurnHigh));
    	//addSequential(new Wait(0.2));
    	//double x, double y, double tolerance, double timeout, PIDGains low, PIDGains high
    	addParallel(new DriveWaypointLegacy(-12.0, 16.0, 2.0, 2.0, true, driveLow, ChassisConst.DriveHigh, gyroDriveLow, ChassisConst.GyroDriveHigh));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveLow, PIDGains driveHigh, PIDGains gyroLow, PIDGains gyroHigh
    	
    	//Grab second can
    	addSequential(new SetArmPosition(-31.0, 2.0, 0.1)); //angle tolerance timeout
    	addSequential(new Wait(0.9));
    	addSequential(new LeftGrabberClose());
    	addSequential(new Wait(0.1));
    
    	//Lift two cans
    	addSequential(new SetArmPosition(-20.0, 5.0, 0.3));
    	addSequential(new Wait(0.2));
    	
    	//Backup from wall
    	addSequential(new DriveDistanceAtRelAngle_NoTurn(-10.0, 0.0, 2.0, 2.0)); //double distance, double angle, double tolerance, double timeout
    	
    	//Drive to third can
    	addSequential(new PrintLine("Drive to third can"));
    	addSequential(new TurnGyroWaypoint(-92.0, 37.0, 1.0, 1.5, gyroTurnLow, ChassisConst.GyroTurnHigh));
    	//addSequential(new Wait(2.0));
    	addParallel(new SetArmPosition(-22.0, 3.0, 0.3));
    	addSequential(new DriveWaypointLegacy(-92.0, 37.0, 2.3, 4.5, true, driveLow, ChassisConst.DriveHigh, gyroDriveLow, ChassisConst.GyroDriveHigh));
    	
    	//Grab third can
    	addSequential(new CenterGrabberClose());
    	addSequential(new Wait(0.1));
    	
    	//Drive to Start
    	addParallel(new SetArmPosition(100.0, 3.0, 0.3));
    	addSequential(new ShiftHigh());
    	addSequential(new Wait(0.2));
    	addSequential(new DriveWaypointBackward(20.0, 20.0, 2.0, 2.5, false));  //X, Y, Tol, timeout, stop
    	
    	//Drive to Finish
    	addSequential(new ShiftLow());
    	addSequential(new Wait(0.05));
    	addSequential(new TurnGyroAbs(0.0, 5.0, 2.0, true, true, gyroTurnLow, ChassisConst.GyroTurnHigh)); //angle tolerance timeout stop
    	addParallel(new SetWristAngle(-10.0));
    	addSequential(new DriveWaypointBackwardLegacy(20.0, -90.0, 4.0, 3.0, true, driveLow, ChassisConst.DriveHigh, gyroDriveLow, ChassisConst.GyroDriveHigh));  //X, Y, Tol, timeout, stop  - changed -23 to -19
    	addSequential(new Wait(0.2));
    }
}


