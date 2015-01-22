package edu.wpi.first.wpilibj.command;

import org.usfirst.frc330.Robot;

public class BBCommandGroup extends CommandGroup {

    synchronized void validate(String message) {
        //do nothing. 
    }

    protected void initialize() {
        this.removeAllCommands();
        super.initialize(); //To change body of generated methods, choose Tools | Templates.
    }

	protected void removeAllCommands() {
		this.m_children.removeAllElements();
		this.m_commands.removeAllElements();
	}
    
	void _initialize(){
		super._initialize();
		Robot.logger.println(this.getClass().getName() + " initialized");
	}

	void _end(){
		super._end();
		Robot.logger.println(this.getClass().getName() + " ended");
	}
	
	void _interrupted(){
		super._interrupted();
		Robot.logger.println(this.getClass().getName() + " interrupted");
	}
    
}
