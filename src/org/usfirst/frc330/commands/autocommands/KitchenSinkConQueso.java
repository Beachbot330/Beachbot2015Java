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
import org.usfirst.frc330.constants.LiftPos;
import org.usfirst.frc330.constants.MastPos;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class KitchenSinkConQueso extends BBCommandGroup {
    
    public  KitchenSinkConQueso() {
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
    	
    	//Hold joint angles, drive forward slightly, close center grabber
    	addSequential(new ShiftLow());
    	//addParallel(new MastPID_on());
    	addParallel(new ArmPID_on());
    	addParallel(new WristPID_on());
    	addSequential(new SetMastPosition(120.0, 1.0, 0.3));
    	//addSequential(new DriveDistance(2,0.5,0.5, true));
    	addParallel(new CenterGrabberClose());
    	addSequential(new Wait(0.1));
    	
    	//Adjust the mast to vertical and start raising the arm
    	addParallel(new SetWristAngle(8.0));
    	addSequential(new SetArmPosition(-2.0, 1.0, 0.7));
    	addSequential(new SetMastPosition(MastPos.vertical, 3.0));
    	addParallel(new SetArmPosition(33.0, 2.0));
    	addParallel(new SetWristAngle(5.0));
//    	
//    	//Drive to second station while loading first tote
//    	addSequential(new SetLiftPosition(LiftPos.dropOff)); //TODO: Try to make it so that we can drive the lift while driving
//    	addSequential(new SetLiftPosition(LiftPos.justOverOneTote));
//    	addSequential(new DriveDistance(82.0 , 1.0));  //TODO: Make this parallel
//    	addSequential(new SetLiftPosition(LiftPos.dropOff));
//    	addParallel(new SetLiftPosition(LiftPos.carry));
//    	addSequential(new Wait(0.1));
//    	
//    	//move back 30 degrees
//    	//rotate +16 degrees
//    	
//    	//Drop off can
//    	addSequential(new SetArmPosition(-14, 1.0));
//    	addSequential(new CenterGrabberOpen());
//    	
//    	//move back 32 inches
    	
    	
    	
    	
    	
    	
    	
    }
}