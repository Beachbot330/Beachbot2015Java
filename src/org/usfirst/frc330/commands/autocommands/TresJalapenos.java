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
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.LiftPos;
import org.usfirst.frc330.constants.MastPos;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class TresJalapenos extends BBCommandGroup {
    
    public  TresJalapenos() {
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
    	
    	//Reverse to give myself room to work
    	//BBCommand driveCommand = new DriveDistanceAtAbsAngle_NoTurn(-14.0 , 0.0, 2.0);  //Dist Angl Tol
    	BBCommand driveCommand = new DriveWaypointBackward(0.0,-10, 2.0, 1.0, false);  //X, Y, Tol, timeout, stop
    	addSequential(driveCommand);
    	addSequential(new Wait(0.2));
    	
    	//Drive to second can
    	addSequential(new TurnGyroWaypoint(-15.0, 15.0, 1.0, 10.0));
    	addSequential(new Wait(0.2));
    	//double x, double y, double tolerance, double timeout, PIDGains low, PIDGains high
    	addParallel(new DriveWaypoint(-15.0, 15.0, 2.0, 10.0, true));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveLow, PIDGains driveHigh, PIDGains gyroLow, PIDGains gyroHigh
    	
    	//Grab second can
    	addSequential(new SetArmPosition(-31.0, 2.0, 0.1)); //angle tolerance timeout
    	addSequential(new Wait(0.5));
    	addSequential(new LeftGrabberClose());
    	addSequential(new Wait(0.2));
    	
    	//Lift two cans
    	addSequential(new SetArmPosition(-20.0, 4.0, 0.3));
    	addSequential(new Wait(0.2));
    	
    	//Drive to third can
    	addSequential(new TurnGyroWaypoint(-77.0, 34.0, 1.0, 10.0));
    	addSequential(new DriveWaypoint(-77.0, 34.0, 2.0, 10.0, true));
    	addSequential(new Wait(1.0));
    	
    	//Grab third can
    	addSequential(new SetArmPosition(-22.0, 3.0, 0.3));
    	addSequential(new Wait(1.0));
    	addSequential(new CenterGrabberClose());
    	addSequential(new Wait(1.0));
    	
    	//Drive to Start
    	addParallel(new SetArmPosition(90.0, 3.0, 0.3));
    	addSequential(new Wait(1.0));
    	addSequential(new DriveWaypointBackward(15.0, 34.0, 2.0, 2.0, false));  //X, Y, Tol, timeout, stop
    	addSequential(new Wait(3.0));
    	
    	//Drive to Finish
    	addSequential(new TurnGyroWaypoint(29.8, -57, 1.0, 10.0));
    	addSequential(new DriveWaypoint(29.8, -57, 2.0, 10.0, true));
    	
   
//    	//Drive to second station while loading first tote
//    	BBCommand driveCommand = new DriveDistanceAtAbsAngle_NoTurn(80.0 , 0.0, 2.0);  //Dist Angl Tol
//    	addParallel(driveCommand);
//    	addSequential(new SetLiftPosition(LiftPos.dropOff));
//    	addSequential(new SetLiftPosition(LiftPos.justOverOneTote));
//    	addSequential(new Wait(0.25));
//    	
//    	//Pickup second tote
//    	addSequential(new SetLiftPosition(LiftPos.dropOff), 0.5);
//    	addSequential(new CheckDone(driveCommand));
//    	addParallel(new DriveDistanceAtRelAngle_NoTurn(4.5, 0.0, 0.5));  //Dist Angle Tol
//    	addSequential(new Wait(0.7));
//    	addParallel(new SetLiftPosition(17.3));
//    	addSequential(new Wait(0.2));  //could possibly reduce one tenth
//    
//    	//Drive back from second tote and turn
//    	BBCommand moveArm = new SetArmPosition(-15, 4.0);
//    	driveCommand = new DriveDistanceAtRelAngle_NoTurn(-42 , 0.0, 2.0);
//    	addParallel(driveCommand);  //Dist Angl Tol
//    	addSequential(new Wait(1.3));
//    	addParallel(moveArm);
//    	addSequential(new CheckDone(driveCommand));
//    	addSequential(new TurnGyroAbs(14.0, 0.5));  //Angle Tol
//    	addSequential(new Wait(0.3));
//    	
//    	//Drive Forward and Grab Can1 and Can2 at angle
//    	addSequential(new CheckDone(moveArm));
//    	addSequential(new CenterGrabberOpen());
//    	addParallel(new SetWristAngle(0));
//    	addSequential(new SetArmPosition(-35, 2.0));
//    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(34.0 , 14.0, 2.0));  //Dist Angl Tol  //Matt swears this will work 3/1
//    	addSequential(new LeftGrabberClose());
//    	addSequential(new CenterGrabberClose());
//    	addSequential(new Wait(0.1));
//    	
//    	//Backup and leave can1
//    	addSequential(new SetArmPosition(-28, 5.0));
//    	addSequential(new DriveDistanceAtRelAngle_NoTurn(-8.0 , 0.0, 2.0));  //Matt swears this will work 3/1
//    	addParallel(new CenterGrabberOpen());
//    	addSequential(new DriveDistanceAtRelAngle_NoTurn(-26.0 , 0.0, 2.0));  //Dist Angl Tol
//    	
//    	//Straighten Out and drive to left behind can1
//    	addSequential(new TurnGyroAbs(0.0, 0.5));  //Angle Tol
//    	addSequential(new Wait(0.3));
//    	driveCommand = new DriveDistanceAtAbsAngle_NoTurn(42.0 , 0.0, 2.0);
//    	addParallel(driveCommand);  //Dist Angl Tol
//    	addSequential(new CheckDone(driveCommand));
//    	addSequential(new RightGrabberClose());
//    	addSequential(new Wait(0.2));
//    	
//    	//Lift Cans
//    	addParallel(new SetArmPosition(-10, 5.0));
//    	
//    	//Drive to last location
//    	driveCommand = new DriveDistanceAtAbsAngle_NoTurn(75.0 , 0.0, 2.0);  //Dist Angl Tol
//    	addParallel(driveCommand);
//    	addParallel(new SetLiftPosition(LiftPos.justOverOneTote));
//    	addSequential(new Wait(0.6));
//    	addParallel(new SetArmPosition(-8.0, 1.0));
//    	addSequential(new CheckDone(driveCommand));
//    	
//    	//Grab last can
//    	addParallel(new CenterGrabberClose());
//    	addSequential(new Wait(0.2));
//    	addParallel(new SetArmPosition(90.0, 1.0));
//    	
//    	//Drive to last tote
//    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(13.0 , 0.0, 2.0));
//    	
//    	//Load third tote
//    	addSequential(new SetLiftPosition(LiftPos.dropOff));
//    	addParallel(new DriveDistanceAtRelAngle_NoTurn(6.0, 0.0, 1.5));  //Dist Angle Tol
//    	addSequential(new Wait(0.7));
//    	addParallel(new SetLiftPosition(LiftPos.carry));
//    	addSequential(new Wait(0.3));  //could possibly reduce one tenth
//    	
//    	//Grab third Can
//    	addParallel(new CenterGrabberClose());
//    	addSequential(new Wait(0.1));
//    	
//    	
//    	//Turn 90
//    	addSequential(new TurnAngleAggressive(90.0, 2.0));  //angle, timeout
//    	addSequential(new ShiftHigh());
//    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(70.0, 90.0, 4.0));  //Dist Angl Tol
//    	addSequential(new SetLiftPosition(LiftPos.dropOff));
//    	addSequential(new ShiftLow());
//    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(-10.0, 90.0, 4.0));  //Dist Angl Tol
//    	addSequential(new BuzzerBeepTimed());
//    	addSequential(new BuzzerBeepTimed());
//    	addSequential(new BuzzerBeepTimed());
    	
    }
}
