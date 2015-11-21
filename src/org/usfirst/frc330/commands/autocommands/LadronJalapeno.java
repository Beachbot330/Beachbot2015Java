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
import org.usfirst.frc330.commands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.ExtendCanBurglar;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.Wait;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class LadronJalapeno extends BBCommandGroup {
    
    public  LadronJalapeno() {
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
    	addSequential(new ExtendCanBurglar());
    	addSequential(new ShiftLow());
    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(0, 2, 0, 2, false)); //distance, distanceTolerance, angle, timeout, stopAtEnd
    	addSequential(new Wait(3.30));
//    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(6, 2, 0, 2, false)); //distance, distanceTolerance, angle, timeout, stopAtEnd
//    	addSequential(new Wait(3.30));
//    	addSequential(new DriveDistanceAtAbsAngle_NoTurn(72, 2, 0, 2, false)); //distance, distanceTolerance, angle, timeout, stopAtEnd
    }
}
