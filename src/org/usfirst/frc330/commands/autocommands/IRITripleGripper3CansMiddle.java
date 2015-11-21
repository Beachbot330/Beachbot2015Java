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
import org.usfirst.frc330.commands.CloseAllGrabbers;
import org.usfirst.frc330.commands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.OpenAllGrabbers;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetMastPosition;
import org.usfirst.frc330.commands.SetWristAngle;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.WristPID_on;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class IRITripleGripper3CansMiddle extends BBCommandGroup {
    
    public  IRITripleGripper3CansMiddle() {
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
    	addParallel(new SetArmPosition(-20.0, 1.0, 0.1)); //angle tolerance timeout
    	addParallel(new CloseAllGrabbers());
    	addSequential(new Wait(0.1));	
    	
    	// Raise the arm
    	addSequential(new SetArmPosition(-15, 3.0, 0.3));
    	
    	//Drive into Auto Zone
    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(-72, 2, 0, 2, false)); //distance, distanceTolerance, angle, timeout, stopAtEnd
    	
    	//Set Down Cans
    	addSequential(new SetArmPosition(-20.0, 3.0, 1.0));
    	addSequential(new Wait(0.3));
    	addSequential(new OpenAllGrabbers());
    	addSequential(new Wait(0.3));
    	addSequential(new SetArmPosition(-31.0, 3.0, 0.3));
    	addSequential(new Wait(0.4));
    	
    	//Regrip Cans
    	addSequential(new CloseAllGrabbers());
    	
    	// Raise the arm
    	addSequential(new SetArmPosition(100.0, 3.0, 0.3));
    	addParallel(new SetWristAngle(-10.0));

    }
}
