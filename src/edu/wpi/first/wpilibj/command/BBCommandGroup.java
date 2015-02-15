package edu.wpi.first.wpilibj.command;

import org.usfirst.frc330.Robot;

public class BBCommandGroup extends CommandGroup {
    
	void _initialize(){
		super._initialize();
		Robot.logger.println(this.getClass().getName() + " initialized", false);
	}

	void _end(){
		super._end();
		Robot.logger.println(this.getClass().getName() + " ended", false);
	}
	
	void _interrupted(){
		super._interrupted();
		Robot.logger.println(this.getClass().getName() + " interrupted", false);
	}
    
}
