// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	


import edu.wpi.first.wpilibj.command.BBCommand;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ArmPos;

public class ArmSwitchStopPosition extends BBCommand {

	BBCommand commandOne;
	BBCommand commandTwo;
	
    public ArmSwitchStopPosition() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandOne = new SetArmPosition(ArmPos.frontStateRearLimitAngle, 5.0, 2.0);
    	commandTwo = new SetArmPosition(ArmPos.rearStateFrontLimitAngle, 5.0, 2.0);
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
