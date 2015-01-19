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
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Solenoid chassisshift;
    public static DoubleSolenoid chassismast;
    public static Compressor chassiscompressor;
    public static AnalogInput chassispressureSensor;
    public static Encoder chassisdriveTrainEncoderL;
    public static Encoder chassisdriveTrainEncoderR;
    public static SpeedController chassischassisLeftDrive1;
    public static SpeedController chassischassisLeftDrive2;
    public static SpeedController chassischassisRightDrive1;
    public static SpeedController chassischassisRightDrive2;
    public static Encoder pickuppickupEncoder;
    public static SpeedController pickuppickupLiftLeft;
    public static SpeedController pickuppickupLiftRight;
    public static Relay frillsbuzzer;
    public static DigitalOutput frillslights;
    public static AnalogInput armarmPot;
    public static SpeedController armarmLeft;
    public static SpeedController armarmRight;
    public static Solenoid handhandLeft;
    public static Solenoid handhandCenter;
    public static Solenoid handhandRight;
    public static SpeedController handwristLeft;
    public static SpeedController handwristRight;
    public static AnalogPotentiometer handwristPot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static DigitalInput practiceRobot;
    

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassisshift = new Solenoid(0, 0);
        LiveWindow.addActuator("Chassis", "shift", chassisshift);
        
        chassismast = new DoubleSolenoid(0, 1, 2);      
        LiveWindow.addActuator("Chassis", "mast", chassismast);
        
        chassiscompressor = new Compressor(0);
        
        
        chassispressureSensor = new AnalogInput(2);
        LiveWindow.addSensor("Chassis", "pressureSensor", chassispressureSensor);
        
        chassisdriveTrainEncoderL = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "driveTrainEncoderL", chassisdriveTrainEncoderL);
        chassisdriveTrainEncoderL.setDistancePerPulse(1.0);
        chassisdriveTrainEncoderL.setPIDSourceParameter(PIDSourceParameter.kRate);
        chassisdriveTrainEncoderR = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "driveTrainEncoderR", chassisdriveTrainEncoderR);
        chassisdriveTrainEncoderR.setDistancePerPulse(1.0);
        chassisdriveTrainEncoderR.setPIDSourceParameter(PIDSourceParameter.kRate);
        chassischassisLeftDrive1 = new VictorSP(0);
        LiveWindow.addActuator("Chassis", "chassisLeftDrive1", (VictorSP) chassischassisLeftDrive1);
        
        chassischassisLeftDrive2 = new VictorSP(1);
        LiveWindow.addActuator("Chassis", "chassisLeftDrive2", (VictorSP) chassischassisLeftDrive2);
        
        chassischassisRightDrive1 = new VictorSP(2);
        LiveWindow.addActuator("Chassis", "chassisRightDrive1", (VictorSP) chassischassisRightDrive1);
        
        chassischassisRightDrive2 = new VictorSP(3);
        LiveWindow.addActuator("Chassis", "chassisRightDrive2", (VictorSP) chassischassisRightDrive2);
        
        pickuppickupEncoder = new Encoder(4, 5, false, EncodingType.k4X);
        LiveWindow.addSensor("Pickup", "pickupEncoder", pickuppickupEncoder);
        pickuppickupEncoder.setDistancePerPulse(1.0);
        pickuppickupEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        pickuppickupLiftLeft = new Victor(4);
        LiveWindow.addActuator("Pickup", "pickupLiftLeft", (Victor) pickuppickupLiftLeft);
        
        pickuppickupLiftRight = new Victor(5);
        LiveWindow.addActuator("Pickup", "pickupLiftRight", (Victor) pickuppickupLiftRight);
        
        frillsbuzzer = new Relay(0);
        LiveWindow.addActuator("Frills", "buzzer", frillsbuzzer);
        
        frillslights = new DigitalOutput(6);
        LiveWindow.addActuator("Frills", "lights", frillslights);
        
        armarmPot = new AnalogInput(1);
        LiveWindow.addSensor("Arm", "armPot", armarmPot);
        
        armarmLeft = new Talon(6);
        LiveWindow.addActuator("Arm", "armLeft", (Talon) armarmLeft);
        
        armarmRight = new Talon(7);
        LiveWindow.addActuator("Arm", "armRight", (Talon) armarmRight);
        
        handhandLeft = new Solenoid(0, 3);
        LiveWindow.addActuator("Hand", "handLeft", handhandLeft);
        
        handhandCenter = new Solenoid(0, 4);
        LiveWindow.addActuator("Hand", "handCenter", handhandCenter);
        
        handhandRight = new Solenoid(0, 5);
        LiveWindow.addActuator("Hand", "handRight", handhandRight);
        
        handwristLeft = new Jaguar(8);
        LiveWindow.addActuator("Hand", "wristLeft", (Jaguar) handwristLeft);
        
        handwristRight = new Jaguar(9);
        LiveWindow.addActuator("Hand", "wristRight", (Jaguar) handwristRight);
        
        handwristPot = new AnalogPotentiometer(0, 1.0, 0.0);
        LiveWindow.addSensor("Hand", "wristPot", handwristPot);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        practiceRobot = new DigitalInput(7);
    }
}
