// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	

import org.usfirst.frc330.Robot;
import edu.wpi.first.wpilibj.command.BBCommand;
import org.usfirst.frc330.commands.*;

public class Template extends BBCommand {

	BBCommand commandOne;
	BBCommand commandTwo;
	boolean cmdsInit = false;
	
    public Template() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	cmdsInit = false;
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
