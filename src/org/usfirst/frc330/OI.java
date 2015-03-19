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

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVAnyButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc330.commands.autocommands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton dropOffAndDrive_hold4;
    public JoystickButton shiftHigh_1;
    public JoystickButton loadSecondTote_2;
    public JoystickButton receiveTote_3;
    public Joystick driverL;
    public JoystickButton dropOffTotes_2;
    public JoystickButton carryTotes_3;
    public JoystickButton stackThree_5;
    public JoystickButton manualLift_hold4;
    public JoystickButton shiftLow_1;
    public Joystick driverR;
    public JoystickButton closeLeftGrabber_10;
    public JoystickButton closeCenterGrabber_1;
    public JoystickButton closeRightGrabber_9;
    public JoystickButton openLeftGrabber_5;
    public JoystickButton openCenterGrabber_2;
    public JoystickButton openRightGrabber_6;
    public JoystickButton openAllGrabbers_12;
    public JoystickButton setMastStretch_16;
    public JoystickButton setMastVertical_11;
    public JoystickButton switchArmSide_14;
    public JoystickButton adjustHandPositionUp_8;
    public JoystickButton adjustHandPositionDown_7;
    public JoystickButton killAll_13;
    public JoystickButton handLevel_3;
    public JoystickButton handDown_4;
    public JoystickButton receiveTote_15;
    public Joystick armJoystick;
    public JoystickButton openLeftGrabber_1;
    public JoystickButton centerGrabberOpen_2;
    public JoystickButton rightGrabberOpen_3;
    public JoystickButton adjustHandPositionUp_9;
    public JoystickButton openAll_5;
    public JoystickButton switchArmSide_7;
    public JoystickButton handLevel_8;
    public JoystickButton adjustHandPositionDown_4;
    public JoystickButton handLevel_10;
    public JoystickButton closeLeftGrabber_11;
    public JoystickButton centerGrabberClose_12;
    public JoystickButton rightGrabberClose_13;
    public JoystickButton handDown_14;
    public JoystickButton closeAllGrabbers_15;
    public JoystickButton killAll_16;
    public JoystickButton moveLiftToToteRX_17;
    public Joystick armGamepad;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public POVAnyButton closeGrabbers;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        armGamepad = new Joystick(3);
        
        moveLiftToToteRX_17 = new JoystickButton(armGamepad, 17);
        moveLiftToToteRX_17.whenPressed(new MoveLiftToToteRX());
        killAll_16 = new JoystickButton(armGamepad, 16);
        killAll_16.whenPressed(new KillAll());
        closeAllGrabbers_15 = new JoystickButton(armGamepad, 15);
        closeAllGrabbers_15.whenPressed(new CloseAllGrabbers());
        handDown_14 = new JoystickButton(armGamepad, 14);
        handDown_14.whenPressed(new HandDown());
        rightGrabberClose_13 = new JoystickButton(armGamepad, 13);
        rightGrabberClose_13.whenPressed(new RightGrabberClose());
        centerGrabberClose_12 = new JoystickButton(armGamepad, 12);
        centerGrabberClose_12.whenPressed(new CenterGrabberClose());
        closeLeftGrabber_11 = new JoystickButton(armGamepad, 11);
        closeLeftGrabber_11.whenPressed(new LeftGrabberClose());
        handLevel_10 = new JoystickButton(armGamepad, 10);
        handLevel_10.whenPressed(new HandLevel());
        adjustHandPositionDown_4 = new JoystickButton(armGamepad, 4);
        adjustHandPositionDown_4.whileHeld(new AdjustHandPositionDown());
        handLevel_8 = new JoystickButton(armGamepad, 8);
        handLevel_8.whenPressed(new HandLevel());
        switchArmSide_7 = new JoystickButton(armGamepad, 7);
        switchArmSide_7.whenPressed(new SwitchArmSide());
        openAll_5 = new JoystickButton(armGamepad, 5);
        openAll_5.whenPressed(new OpenAllGrabbers());
        adjustHandPositionUp_9 = new JoystickButton(armGamepad, 9);
        adjustHandPositionUp_9.whileHeld(new AdjustHandPositionUp());
        rightGrabberOpen_3 = new JoystickButton(armGamepad, 3);
        rightGrabberOpen_3.whenPressed(new RightGrabberOpen());
        centerGrabberOpen_2 = new JoystickButton(armGamepad, 2);
        centerGrabberOpen_2.whenPressed(new CenterGrabberOpen());
        openLeftGrabber_1 = new JoystickButton(armGamepad, 1);
        openLeftGrabber_1.whenPressed(new LeftGrabberOpen());
        armJoystick = new Joystick(2);
        
        receiveTote_15 = new JoystickButton(armJoystick, 15);
        receiveTote_15.whileHeld(new ReceiveTote());
        handDown_4 = new JoystickButton(armJoystick, 4);
        handDown_4.whenPressed(new HandDown());
        handLevel_3 = new JoystickButton(armJoystick, 3);
        handLevel_3.whenPressed(new HandLevel());
        killAll_13 = new JoystickButton(armJoystick, 13);
        killAll_13.whenPressed(new KillAll());
        adjustHandPositionDown_7 = new JoystickButton(armJoystick, 7);
        adjustHandPositionDown_7.whileHeld(new AdjustHandPositionDown());
        adjustHandPositionUp_8 = new JoystickButton(armJoystick, 8);
        adjustHandPositionUp_8.whileHeld(new AdjustHandPositionUp());
        switchArmSide_14 = new JoystickButton(armJoystick, 14);
        switchArmSide_14.whenPressed(new SwitchArmSide());
        setMastVertical_11 = new JoystickButton(armJoystick, 11);
        setMastVertical_11.whenPressed(new SetMastVert());
        setMastStretch_16 = new JoystickButton(armJoystick, 16);
        setMastStretch_16.whenPressed(new SetMastStretch());
        openAllGrabbers_12 = new JoystickButton(armJoystick, 12);
        openAllGrabbers_12.whenPressed(new OpenAllGrabbers());
        openRightGrabber_6 = new JoystickButton(armJoystick, 6);
        openRightGrabber_6.whenPressed(new RightGrabberOpen());
        openCenterGrabber_2 = new JoystickButton(armJoystick, 2);
        openCenterGrabber_2.whenPressed(new CenterGrabberOpen());
        openLeftGrabber_5 = new JoystickButton(armJoystick, 5);
        openLeftGrabber_5.whenPressed(new LeftGrabberOpen());
        closeRightGrabber_9 = new JoystickButton(armJoystick, 9);
        closeRightGrabber_9.whenPressed(new RightGrabberClose());
        closeCenterGrabber_1 = new JoystickButton(armJoystick, 1);
        closeCenterGrabber_1.whenPressed(new CenterGrabberClose());
        closeLeftGrabber_10 = new JoystickButton(armJoystick, 10);
        closeLeftGrabber_10.whenPressed(new LeftGrabberClose());
        driverR = new Joystick(1);
        
        shiftLow_1 = new JoystickButton(driverR, 1);
        shiftLow_1.whenPressed(new ShiftLow());
        manualLift_hold4 = new JoystickButton(driverR, 4);
        manualLift_hold4.whileHeld(new ManualLift());
        stackThree_5 = new JoystickButton(driverR, 5);
        stackThree_5.whenPressed(new StackThree());
        carryTotes_3 = new JoystickButton(driverR, 3);
        carryTotes_3.whenPressed(new CarryTotes());
        dropOffTotes_2 = new JoystickButton(driverR, 2);
        dropOffTotes_2.whenPressed(new DropOffTotes());
        driverL = new Joystick(0);
        
        receiveTote_3 = new JoystickButton(driverL, 3);
        receiveTote_3.whenPressed(new ReceiveTote());
        loadSecondTote_2 = new JoystickButton(driverL, 2);
        loadSecondTote_2.whenPressed(new LoadSecondTote());
        shiftHigh_1 = new JoystickButton(driverL, 1);
        shiftHigh_1.whileHeld(new ShiftHigh());
        dropOffAndDrive_hold4 = new JoystickButton(driverL, 4);
        dropOffAndDrive_hold4.whileHeld(new DropOffAndDrive());

	    
        // SmartDashboard Buttons
        SmartDashboard.putData("ShiftLow", new ShiftLow());

        SmartDashboard.putData("ShiftHigh", new ShiftHigh());

        SmartDashboard.putData("StackThree", new StackThree());

        SmartDashboard.putData("ManualLift", new ManualLift());

        SmartDashboard.putData("ReceiveTote", new ReceiveTote());

        SmartDashboard.putData("LoadSecondTote", new LoadSecondTote());

        SmartDashboard.putData("CarryTotes", new CarryTotes());

        SmartDashboard.putData("DropOffTotes", new DropOffTotes());

        SmartDashboard.putData("LeftGrabberOpen", new LeftGrabberOpen());

        SmartDashboard.putData("LeftGrabberClose", new LeftGrabberClose());

        SmartDashboard.putData("CenterGrabberOpen", new CenterGrabberOpen());

        SmartDashboard.putData("CenterGrabberClose", new CenterGrabberClose());

        SmartDashboard.putData("RightGrabberOpen", new RightGrabberOpen());

        SmartDashboard.putData("RightGrabberClose", new RightGrabberClose());

        SmartDashboard.putData("SwitchArmSide", new SwitchArmSide());

        SmartDashboard.putData("AdjustHandPositionDown", new AdjustHandPositionDown());

        SmartDashboard.putData("KillAll", new KillAll());

        SmartDashboard.putData("BuzzerBeepTimed", new BuzzerBeepTimed());

        SmartDashboard.putData("OpenZachStacker", new OpenZachStacker());

        SmartDashboard.putData("CloseZachStacker", new CloseZachStacker());

        SmartDashboard.putData("CalibrateArmFrontLimit", new CalibrateArmFrontLimit());

        SmartDashboard.putData("CalibrateArmVertical", new CalibrateArmVertical());

        SmartDashboard.putData("CalibrateArmRearLimit", new CalibrateArmRearLimit());

        SmartDashboard.putData("CalibrateMastFrontLimit", new CalibrateMastFrontLimit());

        SmartDashboard.putData("CalibrateMastRearLimit", new CalibrateMastRearLimit());

        SmartDashboard.putData("CalibrateWristFrontLimit", new CalibrateWristFrontLimit());

        SmartDashboard.putData("CalibrateWristRearLimit", new CalibrateWristRearLimit());

        SmartDashboard.putData("ToggleSideSet", new ToggleSideSet());

        SmartDashboard.putData("CalibrateLiftTopLimit", new CalibrateLiftTopLimit());

        SmartDashboard.putData("CalibrateLiftBottomLimit", new CalibrateLiftBottomLimit());

        SmartDashboard.putData("HandDown", new HandDown());

        SmartDashboard.putData("OpenAllGrabbers", new OpenAllGrabbers());

        SmartDashboard.putData("ResetGyro", new ResetGyro());

        SmartDashboard.putData("ManualMast", new ManualMast());

        SmartDashboard.putData("CloseAllGrabbers", new CloseAllGrabbers());

        SmartDashboard.putData("MoveLiftToToteRX", new MoveLiftToToteRX());

        SmartDashboard.putData("CanSnatch", new CanSnatch());

        SmartDashboard.putData("FrontSideManualMast", new FrontSideManualMast());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        closeGrabbers = new POVAnyButton(armJoystick,0);
        closeGrabbers.whenPressed(new CloseAllGrabbers());
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverL() {
        return driverL;
    }

    public Joystick getDriverR() {
        return driverR;
    }

    public Joystick getArmJoystick() {
        return armJoystick;
    }

    public Joystick getArmGamepad() {
        return armGamepad;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

