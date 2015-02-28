// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	

import org.usfirst.frc330.Robot;
import edu.wpi.first.wpilibj.command.BBCommand;
import org.usfirst.frc330.commands.*;

public class Template extends BBCommand {

	BBCommand commandOne;
	BBCommand commandTwo;
	
    public Template() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandOne = new DebugOne();
    	commandTwo = new DebugTwo();
    	if(true){
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
