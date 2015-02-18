// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.command.BBCommand;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.HandConst;

public class ArmSwitchWristToLimit extends BBCommand {

	BBCommand commandOne;
	BBCommand commandTwo;
	boolean cmdsInit = false;
	
    public ArmSwitchWristToLimit() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	cmdsInit = false;
    	commandOne = new SetWristAngle(HandConst.rearLimitAngle, HandConst.tolerance, 3.0);
    	commandTwo = new SetWristAngle(HandConst.frontLimitAngle, HandConst.tolerance, 3.0);
    	if(Robot.arm.getIsFront()){
    		commandOne.start();
    	}
    	else{
    		commandTwo.start();
    	}
    }

    protected void execute() {
    	if (commandOne.isInitialized() || commandTwo.isInitialized()){
    		cmdsInit = true;
    	}
    }

    protected boolean isFinished() {
    	if (cmdsInit)
    		return !commandOne.isRunning() && !commandTwo.isRunning();
    	else
    		return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
