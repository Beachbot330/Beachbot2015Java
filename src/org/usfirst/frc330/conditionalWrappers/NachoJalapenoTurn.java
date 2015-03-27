// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.conditionalWrappers;	

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc330.commands.*;

public class NachoJalapenoTurn extends BBCommand {

	BBCommand commandOne;
	BBCommand commandTwo;
	String dashboardKey;
	
    public NachoJalapenoTurn(String str) {
    	this.dashboardKey = str;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandOne = new TurnGyroAbs(90,5);
    	commandTwo = new TurnGyroAbs(-90,5);
    	if(SmartDashboard.getBoolean(dashboardKey, false)){
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
