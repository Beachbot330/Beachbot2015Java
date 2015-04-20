// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class CanBurglar extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	BBDoubleSolenoid LeftCanBurglarSolenoid = RobotMap.handhandLeft;
	BBDoubleSolenoid RightCanBurglarSolenoid = RobotMap.handhandRight;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void extendCanBurglar() {
    	LeftCanBurglarSolenoid.set(Value.kForward);
    	RightCanBurglarSolenoid.set(Value.kForward);
    }
    
    public void retractCanBurglar() {
    	LeftCanBurglarSolenoid.set(Value.kReverse);
    	RightCanBurglarSolenoid.set(Value.kReverse);
    }
}

