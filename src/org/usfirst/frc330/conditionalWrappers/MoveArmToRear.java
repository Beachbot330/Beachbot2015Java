// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.SwitchArmSide;

public class MoveArmToRear extends BBCommand {

	BBCommandGroup commandOne;
	BBCommand commandTwo;
	
    public MoveArmToRear() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandOne = new SwitchArmSide();
    	commandTwo = new Wait(0.001);
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
