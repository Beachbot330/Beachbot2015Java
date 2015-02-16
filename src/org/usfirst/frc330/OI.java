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
import org.usfirst.frc330.commands.commandgroups.CarryTotes;
import org.usfirst.frc330.commands.commandgroups.LoadSecondTote;
import org.usfirst.frc330.commands.commandgroups.ReceiveTote;
import org.usfirst.frc330.commands.commandgroups.StackThree;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import edu.wpi.first.wpilibj.buttons.TwoJoystickButton;

//import org.usfirst.frc330.commands.autocommands.*;
import org.usfirst.frc330.commands.commandgroups.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    public JoystickButton dropOffAndDrive_hold2;
    public JoystickButton shiftHigh_1;
    public Joystick driverL;
    public JoystickButton dropOffTotes_hold2;
    public JoystickButton carryTotes_rel2;
    public JoystickButton loadSecondTote_rel3;
    public JoystickButton receiveTote_held3;
    public JoystickButton stackThree_5;
    public JoystickButton manualLift_hold4;
    public JoystickButton shiftLow_1;
    public Joystick driverR;
    public JoystickButton adjustHandPositionUp_3;
    public JoystickButton adjustHandPositionDown_2;
    public JoystickButton openAllGrabbers_6;
    public JoystickButton switchArmSide_11;
    public JoystickButton killAll_8;
    public JoystickButton setMastStretch_10;
    public Joystick armJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        armJoystick = new Joystick(2);
        
        setMastStretch_10 = new JoystickButton(armJoystick, 10);
        setMastStretch_10.whenPressed(new SetMastStretch());
        killAll_8 = new JoystickButton(armJoystick, 8);
        killAll_8.whenPressed(new KillAll());
        switchArmSide_11 = new JoystickButton(armJoystick, 11);
        switchArmSide_11.whenPressed(new SwitchArmSide());
        openAllGrabbers_6 = new JoystickButton(armJoystick, 6);
        openAllGrabbers_6.whenPressed(new OpenAllGrabbers());
        adjustHandPositionDown_2 = new JoystickButton(armJoystick, 2);
        adjustHandPositionDown_2.whileHeld(new AdjustHandPositionDown());
        adjustHandPositionUp_3 = new JoystickButton(armJoystick, 3);
        adjustHandPositionUp_3.whileHeld(new AdjustHandPositionUp());
        driverR = new Joystick(1);
        
        shiftLow_1 = new JoystickButton(driverR, 1);
        shiftLow_1.whenPressed(new ShiftLow());
        manualLift_hold4 = new JoystickButton(driverR, 4);
        manualLift_hold4.whileHeld(new ManualLift());
        stackThree_5 = new JoystickButton(driverR, 5);
        stackThree_5.whenPressed(new StackThree());
        receiveTote_held3 = new JoystickButton(driverR, 3);
        receiveTote_held3.whileHeld(new ReceiveTote());
        loadSecondTote_rel3 = new JoystickButton(driverR, 3);
        loadSecondTote_rel3.whenReleased(new LoadSecondTote());
        carryTotes_rel2 = new JoystickButton(driverR, 2);
        carryTotes_rel2.whenReleased(new CarryTotes());
        dropOffTotes_hold2 = new JoystickButton(driverR, 2);
        dropOffTotes_hold2.whileHeld(new DropOffTotes());
        driverL = new Joystick(0);
        
        shiftHigh_1 = new JoystickButton(driverL, 1);
        shiftHigh_1.whileHeld(new ShiftHigh());
        dropOffAndDrive_hold2 = new JoystickButton(driverL, 2);
        dropOffAndDrive_hold2.whileHeld(new DropOffAndDrive());

	    
        // SmartDashboard Buttons
        SmartDashboard.putData("ShiftLow", new ShiftLow());

        SmartDashboard.putData("ShiftHigh", new ShiftHigh());

        SmartDashboard.putData("StackThree", new StackThree());

        SmartDashboard.putData("ManualLift", new ManualLift());

        SmartDashboard.putData("ReceiveTote", new ReceiveTote());

        SmartDashboard.putData("LoadSecondTote", new LoadSecondTote());

        SmartDashboard.putData("CarryTotes", new CarryTotes());

        SmartDashboard.putData("DropOffTotes", new DropOffTotes());

        SmartDashboard.putData("ManualArm", new ManualArm());

        SmartDashboard.putData("LeftGrabberOpen", new LeftGrabberOpen());

        SmartDashboard.putData("LeftGrabberClose", new LeftGrabberClose());

        SmartDashboard.putData("CenterGrabberOpen", new CenterGrabberOpen());

        SmartDashboard.putData("CenterGrabberClose", new CenterGrabberClose());

        SmartDashboard.putData("RightGrabberOpen", new RightGrabberOpen());

        SmartDashboard.putData("RightGrabberClose", new RightGrabberClose());

        SmartDashboard.putData("SetMastStretch", new SetMastStretch());

        SmartDashboard.putData("SwitchArmSide", new SwitchArmSide());

        SmartDashboard.putData("AdjustHandPositionDown", new AdjustHandPositionDown());

        SmartDashboard.putData("OpenAllGrabbers", new OpenAllGrabbers());

        SmartDashboard.putData("KillAll", new KillAll());

        SmartDashboard.putData("BuzzerBeepTimed", new BuzzerBeepTimed());

        SmartDashboard.putData("OpenZachStacker", new OpenZachStacker());

        SmartDashboard.putData("CloseZachStacker", new CloseZachStacker());

        SmartDashboard.putData("CalibrateArmFrontLimit", new CalibrateArmFrontLimit());

        SmartDashboard.putData("CalibrateArmRearLimit", new CalibrateArmRearLimit());

        SmartDashboard.putData("CalibrateMastFrontLimit", new CalibrateMastFrontLimit());

        SmartDashboard.putData("CalibrateMastRearLimit", new CalibrateMastRearLimit());

        SmartDashboard.putData("CalibrateWristFrontLimit", new CalibrateWristFrontLimit());

        SmartDashboard.putData("CalibrateWristRearLimit", new CalibrateWristRearLimit());

        SmartDashboard.putData("ToggleSideSet", new ToggleSideSet());

        SmartDashboard.putData("CalibrateLiftTopLimit", new CalibrateLiftTopLimit());

        SmartDashboard.putData("CalibrateLiftBottomLimit", new CalibrateLiftBottomLimit());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
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


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

