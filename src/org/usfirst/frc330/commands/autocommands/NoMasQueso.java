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
import org.usfirst.frc330.commands.SetWristAngle;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.TurnGyroAbs;
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
public class NoMasQueso extends BBCommandGroup {
    
    public  NoMasQueso() {
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
    	
    	PIDGains GyroLow = new PIDGains(0.3, 0, 0, 0, 1.0, 0.5, "HardScrub"); //p,  i,  d,  f,  maxOutput, maxOutputStep, name
    	
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
    	
    	//Pickup second tote
    	addSequential(new SetLiftPosition(LiftPos.dropOff), 0.5);
    	addSequential(new CheckDone(driveCommand));
    	addParallel(new DriveDistanceAtRelAngle_NoTurn(2.0, 0.0, 0.5));  //Dist Angle Tol
    	addSequential(new Wait(0.7));
    	addParallel(new SetLiftPosition(3.0));
    	addSequential(new Wait(0.1));  //reduced from 0.2
    
    	//Drive back from second tote and turn
    	BBCommand moveArm = new SetArmPosition(-15, 4.0);
    	driveCommand = new DriveDistanceAtRelAngle_NoTurn(-42 , 0.0, 1.5);  //double distance, double angle, double tolerance, double timeout
    	addParallel(driveCommand);  //Dist Angl Tol
    	addSequential(new Wait(1.3));
    	addParallel(new SetLiftPosition(17.3));
    	addParallel(moveArm);
    	addSequential(new CheckDone(driveCommand));
    	addSequential(new TurnGyroAbs(14.0, 1.0, 3.0, true, true));  //Angle Tol Timeout
    	addSequential(new Wait(0.2));
    	
    	//Drive Forward and Grab Can1 and Can2 at angle
    	addSequential(new CheckDone(moveArm));
    	addSequential(new CenterGrabberOpen());
    	addParallel(new SetWristAngle(0.0, 3.0, 0.5));  //angle tolerance timeout
    	addSequential(new SetArmPosition(-35, 2.0));
    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(44.0 , 14.0, 2.0));  //Dist Angl Tol
    	addSequential(new LeftGrabberClose());
    	addSequential(new CenterGrabberClose());
    	addSequential(new Wait(0.1));
    	
    	//Raise the Arm and Turn towards the third station
    	addSequential(new SetArmPosition(-20, 5.0));
    	addSequential(new TurnGyroAbs(165.9, 2.0, 4.0, true, true, GyroLow, ChassisConst.GyroTurnHigh));  //Angle Tolerance Timeout
    	addSequential(new Wait(0.1));
    	
    	//Drive to the third station
    	addParallel(new SetArmPosition(-15, 2.0));
    	addSequential(new DriveWaypoint(14.0, 10.0, 2.0, 3.0, false));
    	addSequential(new Wait(3.30));
    	
    	//Grab the third can, lift arm
    	addSequential(new RightGrabberClose());
    	addSequential(new SetArmPosition(90, 2.0));
    	addSequential(new Wait(3.30));
    	
    	//Drive to third tote
    	addSequential(new DriveWaypoint(2.0, -50.0, 4.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.DriveHigh, GyroLow, ChassisConst.GyroDriveHigh));
    	addSequential(new Wait(3.30));
    	
    	//Pickup Third Tote
    	addSequential(new SetLiftPosition(LiftPos.dropOff), 0.5);
    	addSequential(new SetLiftPosition(3.0));
    	addSequential(new Wait(3.30));
    	
    	//Turn towards finish (away from)
    	addSequential(new TurnGyroAbs(360, 2.0, 4.0, true, true, GyroLow, ChassisConst.GyroTurnHigh));  //Angle Tolerance Timeout
    	addSequential(new Wait(3.30));

    }
    
    protected void end() {
    	Robot.logger.println("NoMasQueso end time: " + this.timeSinceInitialized());
    }
}
