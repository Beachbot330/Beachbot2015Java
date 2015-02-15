// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.autocommands.*;
//import org.usfirst.frc330.commands.autocommands.*;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser autoChooser;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static Lift lift;
    public static Frills frills;
    public static Arm arm;
    public static Hand hand;
    public static PowerDP powerDP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static Logger logger;
    public static CSVLogger csvLogger;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        RobotMap.init();
        logger = new Logger();
        csvLogger = new CSVLogger();
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        lift = new Lift();
        frills = new Frills();
        arm = new Arm();
        hand = new Hand();
        powerDP = new PowerDP();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period

        autoChooser = new SendableChooser();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autoChooser.addObject("DoNothing", new DoNothing());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
        csvLogger.writeHeader();
        logger.println("Robot Init");
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        logger.println("Disabled Init");
    	logger.updateDate();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        chassis.calcXY();
    	csvLogger.writeData();
    }

    public void autonomousInit() {
        logger.println("Autonomous Init");
    	logger.updateDate();
    	decideStates();
        // schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        chassis.calcXY();
        chassis.pidDrive();
    	csvLogger.writeData();
    }

    public void teleopInit() {
        logger.println("Teleop Init");
    	logger.updateDate();
    	decideStates();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    private void decideStates() {
    	double armAngle = arm.getArmAngle();
    	//double handAngle = hand.getAngle();
    	if (armAngle > 90) arm.setIsFront(true);
	}

	/**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        chassis.calcXY();
        chassis.pidDrive();
    	csvLogger.writeData();
    }
    
    public void testInit() {
        logger.println("Test Init");
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        chassis.calcXY();
        csvLogger.writeData();
    }
    
    public static boolean isPracticerobot() {
        return (frills.isPracticeRobot());
    }
}
