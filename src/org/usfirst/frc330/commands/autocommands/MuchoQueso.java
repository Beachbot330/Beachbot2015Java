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
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.ArmPID_on;
import org.usfirst.frc330.commands.BuzzerBeepTimed;
import org.usfirst.frc330.commands.CenterGrabberClose;
import org.usfirst.frc330.commands.CenterGrabberOpen;
import org.usfirst.frc330.commands.CheckDone;
import org.usfirst.frc330.commands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.DriveDistanceAtRelAngle_NoTurn;
import org.usfirst.frc330.commands.DriveWaypoint;
import org.usfirst.frc330.commands.LeftGrabberClose;
import org.usfirst.frc330.commands.OpenAllGrabbers;
import org.usfirst.frc330.commands.RightGrabberClose;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetLiftPosition;
import org.usfirst.frc330.commands.SetMastPosition;
import org.usfirst.frc330.commands.SetWristAngle;
import org.usfirst.frc330.commands.ShiftHigh;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.TurnAngleAggressive;
import org.usfirst.frc330.commands.TurnGyroAbs;
import org.usfirst.frc330.commands.TurnGyroWaypoint;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.WristPID_on;
import org.usfirst.frc330.constants.ArmPos;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.LiftPos;
import org.usfirst.frc330.constants.MastPos;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class MuchoQueso extends BBCommandGroup {
    
    public  MuchoQueso() {
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
    	
    	PIDGains HardScrub = new PIDGains(0.3, 0, 0, 0, 1.0, 0.5, "HardScrub"); //p,  i,  d,  f,  maxOutput, maxOutputStep, name
    	
    	//Hold joint angles, open grabbers, close center grabber
    	addParallel(new ArmPID_on());
    	addParallel(new WristPID_on());
    	addSequential(new ShiftLow());
    	addSequential(new OpenAllGrabbers());
    	addParallel(new CenterGrabberClose());
    	addSequential(new Wait(0.1));
    	
    	//Start raising the arm
    	addParallel(new SetWristAngle(5.0));
    	addParallel(new SetArmPosition(33.0, 2.0));
    	addSequential(new Wait(0.1));
    	
    	//Drive to second station while loading first tote
    	BBCommand driveCommand = new DriveWaypoint(0.0, 92.0, 1.5, 3.0, false);  //X Y Tol Timeout Stop
    	addParallel(driveCommand);
    	addSequential(new SetLiftPosition(LiftPos.dropOff));
    	addSequential(new Wait(0.5));
    	addSequential(new SetLiftPosition(LiftPos.justOverOneTote));
    	addSequential(new Wait(0.25));
//    	addSequential(new Wait(3.30));
    	
    	//Pickup second tote
    	addSequential(new SetLiftPosition(LiftPos.dropOff), 0.5);
    	addSequential(new CheckDone(driveCommand));
    	driveCommand = new DriveDistanceAtAbsAngle_NoTurn(24, 0.0, 1.5, 1.5, false);
    	addParallel(driveCommand);  //Dist Angle Tol
    	addSequential(new Wait(0.5));
    	addSequential(new SetLiftPosition(5.0));
    	addSequential(new CheckDone(driveCommand));
    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(-4.0, 0.0, 1.5, 0.5, false));
    	addParallel(new SetLiftPosition(17.3));
    	addSequential(new Wait(0.1));  //reduced from 0.2
//    	addSequential(new Wait(3.30));
    
    	//Drive back from second tote
    	BBCommand moveArm = new SetArmPosition(-15, 4.0);
    	driveCommand = new DriveDistanceAtAbsAngle_NoTurn(-28 , 0.0, 1.5, 1.5, false);  //double distance, double angle, double tolerance, double timeout
    	addParallel(driveCommand);  //Dist Angl Tol
    	addSequential(new Wait(1.3));
    	addParallel(moveArm);
    	addSequential(new CheckDone(driveCommand));
//    	addSequential(new Wait(3.30));
    	
    	//Drop First Can
    	addSequential(new CheckDone(moveArm));
    	addSequential(new CenterGrabberOpen());
    	addParallel(new SetWristAngle(0.0, 3.0, 0.5));  //angle tolerance timeout
    	addSequential(new SetArmPosition(-35, 2.0));
//    	addSequential(new Wait(3.30));
    	
    	//Drive Backwards to clear dropped can
    	addSequential( new DriveDistanceAtAbsAngle_NoTurn(-12 , 0.0, 1.5, 1.0, false));
//    	addSequential(new Wait(3.30));
    	
    	//Drive to last location
    	PIDGains GyroLow = new PIDGains(0.03, 0.00, 0.02, 0.0, 1.0, 1.0, "GyroLow"); //p,  i,  d,  f,  maxOutput, maxOutputStep
//    	addSequential(new TurnGyroWaypoint(0.0, -155.0, 4.0, 3.0, GyroLow, ChassisConst.GyroDriveHigh));
    	addSequential(new TurnGyroAbs(179.0,5.0, 3.0, false, true, GyroLow, ChassisConst.GyroTurnHigh));
    	addParallel(new SetArmPosition(-12.0, 1.0));
    	addSequential(new Wait(3.30));
    	driveCommand = new DriveWaypoint(0.0, 40.0, 4.0, 3.0, false, ChassisConst.DriveLow, ChassisConst.DriveHigh, GyroLow, ChassisConst.GyroDriveHigh);
    	addParallel(driveCommand);
    	addParallel(new SetLiftPosition(LiftPos.justOverOneTote));
    	addSequential(new Wait(0.2));
    	addSequential(new CheckDone(driveCommand));
    	addSequential(new Wait(3.30));
    	
    	//Grab last can
    	addParallel(new CenterGrabberClose());
    	addSequential(new Wait(0.2));
    	addParallel(new SetArmPosition(ArmPos.verticalAngle, 1.0));
    	addSequential(new Wait(3.30));
    	
    	//Drive to last tote
    	addSequential(new DriveWaypoint(0.0, -10.0, 4.0, 3.0, true));
    	addSequential(new Wait(3.30));
    	
    	//Load third tote
    	addSequential(new SetLiftPosition(LiftPos.dropOff));
    	addSequential(new Wait(3.30));
    	addParallel(new DriveDistanceAtRelAngle_NoTurn(6.0, 0.0, 1.5));  //Dist Angle Tol
    	addSequential(new Wait(0.7));
    	addParallel(new SetLiftPosition(LiftPos.carry));
    	addSequential(new Wait(0.3));  //could possibly reduce one tenth

    	
    	
    	//Turn 90
    	addSequential(new TurnAngleAggressive(90.0, 2.0));  //angle, timeout
    	addSequential(new ShiftHigh());
    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(90.0, 90.0, 4.0));  //Dist Angl Tol
    	addSequential(new SetLiftPosition(LiftPos.dropOff));
    	addSequential(new ShiftLow());
    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(-20.0, 90.0, 4.0));  //Dist Angl Tol
    	addSequential(new BuzzerBeepTimed());
    	addSequential(new BuzzerBeepTimed());
    	addSequential(new BuzzerBeepTimed());
    }
    
    protected void end() {
    	Robot.logger.println("MuchoQueso end time: " + this.timeSinceInitialized());
    }
}