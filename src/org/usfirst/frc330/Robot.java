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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.autocommands.*;
import org.usfirst.frc330.constants.ArmPos;
import org.usfirst.frc330.constants.HandConst;
//import org.usfirst.frc330.commands.autocommands.*;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;

import com.ni.vision.VisionException;

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
    public static Frills frills;
    public static Chassis chassis;
    public static Lift lift;
    public static Arm arm;
    public static Hand hand;
    public static Mast mast;
    public static PowerDP powerDP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static Logger logger;
    public static CSVLogger csvLogger;
    private CameraServer server;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        RobotMap.init();
        logger = new Logger();
        csvLogger = new CSVLogger();
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frills = new Frills();
        chassis = new Chassis();
        lift = new Lift();
        arm = new Arm();
        hand = new Hand();
        mast = new Mast();
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
        autoChooser.addObject("TresFideos", new TresFideos());
        autoChooser.addObject("NachoJalepeno", new NachoJalepeno());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        //autoChooser.addObject("GyroFuN", new TurnFourteen());
        //autoChooser.addObject("TucanSam", new TucanSam());
        //autoChooser.addObject("JoeTest", new JoeTest());
        autoChooser.addObject("NachoJalepeno_CCW", new NachoJalepeno_CCW());
        autoChooser.addObject("QuieroUnoJalapeno", new QuieroUnoJalapeno());
        //autoChooser.addObject("SlicedJalapeno", new SlicedJalapeno());
        //autoChooser.addObject("NoMasQueso", new NoMasQueso());
        //autoChooser.addObject("KitchenSinkConQueso", new KitchenSinkConQueso());
        
        
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
        csvLogger.writeHeader();
        logger.println("Robot Init");
        
        if (isPracticeRobot())
        	logger.println("Practice Robot Detected");
        else
        	logger.println("Comeptition Robot Detected");
        
        Command beep = new BuzzerBeepTimed(0.75);
        beep.start();
        
        server = CameraServer.getInstance();
        server.setSize(2);
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        try {
        	server.startAutomaticCapture("cam0");
        }
        catch (VisionException ex) {
            DriverStation.reportError("Error when starting the camera: " + ex.getMessage(), true);
        }
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        logger.println("Disabled Init");
    	logger.updateDate();
    	decideStates();
    }

    double timer;
    public void disabledPeriodic() {

        Scheduler.getInstance().run();

        chassis.calcXY();
//    	timer = Timer.getFPGATimestamp();
        csvLogger.writeData();
//        System.out.println("writeData Time: " + (Timer.getFPGATimestamp() - timer));        
        decideStates();
        
        checkGyroReset();
    }

    boolean prevGyroReset=false;
    private void checkGyroReset() {
    	boolean gyroReset = chassis.getGyroReset();
		if (gyroReset && !prevGyroReset)
		{
			new ResetGyro().start();
		}
		prevGyroReset = gyroReset;
	}

	public void autonomousInit() {
        logger.println("Autonomous Init");
    	logger.updateDate();
    	decideStates();
    	Robot.chassis.resetPosition();
        // schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
        if (autonomousCommand != null) {
        	autonomousCommand.start();
        	logger.println("Running Auto: " + autonomousCommand.getName());
        }
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        chassis.calcXY();
        chassis.pidDriveAuto();
    	csvLogger.writeData();
    }

    public void teleopInit() {
        logger.println("Teleop Init");
    	logger.updateDate();
    	Command beep= new BuzzerBeepTimed(1.25);
    	beep.start();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    boolean invalidState;
    private void decideStates() {
    	invalidState = false;
    	double armAngle = arm.getArmAngle();
    	double handAngle = hand.getWristAngle();
    	if (armAngle < ArmPos.frontStateRearLimitAngle + ArmPos.tolerance*8 && handAngle < 90) {
    		arm.setIsFront(true);
    		frills.buzzerOff();
//    		Robot.logger.println("Arm is in Front",false);
    	}
    	else if (armAngle > ArmPos.rearStateFrontLimitAngle - ArmPos.tolerance*8 && handAngle > 90) {
    		arm.setIsFront(false);
    		frills.buzzerOff();
//    		Robot.logger.println("Arm is in Rear", false);
    	}
    	else {
    		arm.setIsFront(true);
    		//frills.buzzerOn();
    		invalidState = true;
    		Robot.logger.println("Arm in Invalid State, ArmAngle: " + armAngle + " WristAngle: " + handAngle, true);
    	}
    	if (Robot.hand.getHeight() > HandConst.handHeightLimit) {
    		//frills.buzzerOn();
    		invalidState = true;
    		Robot.logger.println("Hand too high. Hand Height: " + Robot.hand.getHeight());
    	}
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
    
    public static boolean isPracticeRobot() {
        return (frills.isPracticeRobot());
    }
}
