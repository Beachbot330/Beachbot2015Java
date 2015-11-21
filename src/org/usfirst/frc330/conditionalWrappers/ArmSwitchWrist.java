// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.SetWristAngle;

import edu.wpi.first.wpilibj.command.BBCommand;

public class ArmSwitchWrist extends BBCommand {

	BBCommand commandOne;
	BBCommand commandTwo;
	
    public ArmSwitchWrist() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandOne = new SetWristAngle(0.0, 3.0, 2.0);
    	commandTwo = new SetWristAngle(180.0, 3.0, 2.0);
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
