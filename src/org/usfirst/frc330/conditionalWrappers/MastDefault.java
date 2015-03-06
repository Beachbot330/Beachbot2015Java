// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.command.BBCommand;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ArmPos;
import org.usfirst.frc330.constants.MastPos;

public class MastDefault extends BBCommand {

	BBCommand commandOne;
	BBCommand commandTwo;
	
    public MastDefault() {
    	requires(Robot.mast);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandOne = new HoldMast();
    	commandTwo = new SetMastPositionThrottle();
    	if(Robot.arm.getIsFront()){
    		commandOne.start();
    	}
    	else{
    		commandTwo.start();
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return commandOne.isCompleted() || commandTwo.isCompleted();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
