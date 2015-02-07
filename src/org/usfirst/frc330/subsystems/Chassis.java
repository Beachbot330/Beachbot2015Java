// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.TankDrive;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.wpilibj.DualSpeedController;
import org.usfirst.frc330.wpilibj.DummyPIDOutput;
import org.usfirst.frc330.wpilibj.MultiPrefPIDController;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Chassis extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Solenoid shift = RobotMap.chassisshift;
    Compressor compressor = RobotMap.chassiscompressor;
    AnalogInput pressureSensor = RobotMap.chassispressureSensor;
    Encoder driveTrainEncoderL = RobotMap.chassisdriveTrainEncoderL;
    Encoder driveTrainEncoderR = RobotMap.chassisdriveTrainEncoderR;
    SpeedController chassisLeftDrive1 = RobotMap.chassischassisLeftDrive1;
    SpeedController chassisLeftDrive2 = RobotMap.chassischassisLeftDrive2;
    DualSpeedController chassisLeftDrive = RobotMap.chassischassisLeftDrive;
    SpeedController chassisRightDrive1 = RobotMap.chassischassisRightDrive1;
    SpeedController chassisRightDrive2 = RobotMap.chassischassisRightDrive2;
    DualSpeedController chassisRightDrive = RobotMap.chassischassisRightDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    IMU imu;
    SerialPort sp;
    
    public MultiPrefPIDController gyroPID, leftDrivePID, rightDrivePID;
    private DummyPIDOutput gyroOutput, leftDriveOutput, rightDriveOutput;

    public Chassis() {
    	super();
    	
    	try {
	    	sp = new SerialPort(57600,SerialPort.Port.kMXP);
			imu = new IMU(sp,(byte) 50);
	    	} catch( Exception ex ) {
	    		ex.printStackTrace();
	    	}
        if ( imu != null ) {
            LiveWindow.addSensor("Chassis", "Gyro", imu);
        }
        
        gyroOutput = new DummyPIDOutput();
        leftDriveOutput = new DummyPIDOutput();
        rightDriveOutput = new DummyPIDOutput();
        
        gyroPID = new MultiPrefPIDController(0.11,0,0,imu,gyroOutput,"gyro");
        leftDrivePID = new MultiPrefPIDController(0.2,0,0,driveTrainEncoderL,leftDriveOutput,"leftDrive");
        rightDrivePID = new MultiPrefPIDController(0.2,0,0,driveTrainEncoderR,rightDriveOutput,"rightDrive");
        
        leftDrivePID.setOutputRange(-0.8, 0.8);
        rightDrivePID.setOutputRange(-0.8, 0.8);
        
        SmartDashboard.putData("gyroPID", gyroPID);
        SmartDashboard.putData("leftDrivePID", leftDrivePID);
        SmartDashboard.putData("rightDrivePID", rightDrivePID);
        
        final double diameter = 6;
        final double PulseperRevolution = 250;
        final double encoderGearRatio = 3;
        final double gearRatio = 54.0/30.0;
        final double Fudgefactor = 1.0;
        final double distanceperpulse = Math.PI*diameter/PulseperRevolution/encoderGearRatio/gearRatio * Fudgefactor;

        driveTrainEncoderL.setDistancePerPulse(distanceperpulse);
        driveTrainEncoderR.setDistancePerPulse(distanceperpulse);
        
    	CSVLoggable temp = new CSVLoggable() {
			public double get() { return driveTrainEncoderL.getDistance(); }
    	};
    	Robot.csvLogger.add("DriveTrainEncoderDistanceL", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return driveTrainEncoderR.getDistance(); }
    	};
    	Robot.csvLogger.add("DriveTrainEncoderDistanceR", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return driveTrainEncoderL.getRate(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainEncoderRateL", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return driveTrainEncoderR.getRate(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainEncoderRateR", temp);    	

    	temp = new CSVLoggable() {
			public double get() { return chassisLeftDrive.get(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainLeft", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return chassisRightDrive.get(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainRight", temp); 
    	
    	temp = new CSVLoggable() {
			public double get() { return imu.getYaw(); }  		
    	};    	
    	Robot.csvLogger.add("ChassisAngle", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getX(); }  		
    	};     	
    	Robot.csvLogger.add("ChassisX", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getY(); }  		
    	};      	
    	Robot.csvLogger.add("ChassisY", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getPressure(); }  		
    	};  
    	Robot.csvLogger.add("Pressure", temp);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void shiftHigh()
    {
    	shift.set(true);
    }
    
    public void shiftLow()
    {
    	shift.set(false);
    }
    
    public boolean isHighGear() {
    	return shift.get();
    }
    
    public double getPressure() {
    	return 37.5*(pressureSensor.getAverageVoltage()- 0.5);
    }
    
    double left, right;
    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick)
    {
       left = -leftJoystick.getY();
       right = -rightJoystick.getY();
    }
   
    public void tankDrive(double left, double right)
    {
        this.left = left;
        this.right = right;
    }
    
    private void drive(double left, double right) {
        chassisLeftDrive.set(left);
        chassisRightDrive.set(right);
    }
    
    public void pidDrive()
    {
        double left, right;
        if (DriverStation.getInstance().isDisabled())
        {
            stopDrive();
        }
        else
        {
            left = this.left+leftDriveOutput.getOutput() - gyroOutput.getOutput();
            right = this.right+rightDriveOutput.getOutput() + gyroOutput.getOutput();
            drive(left, right);
            this.left = 0;
            this.right = 0;
        }
    }
    
    public void stopDrive()
    {
        if (gyroPID.isEnable())
            gyroPID.reset();
        if (leftDrivePID.isEnable())
            leftDrivePID.reset();
        if (rightDrivePID.isEnable())
            rightDrivePID.reset();        
        tankDrive(0, 0);  
    }
    
    double gain = .5;
    public void cheesyDrive(Joystick leftJoystick, Joystick rightJoystick)     {
        double turn = rightJoystick.getAxis(Joystick.AxisType.kX);
        double throttle = -leftJoystick.getAxis(Joystick.AxisType.kY);
        double left, right;
        
        if (Math.abs(throttle) > 0.2)
            turn = Math.abs(throttle) * turn * gain;
        
        left = throttle  + turn;
        right = throttle - turn;
        
        tankDrive(left + skim(right),right + skim(left));
//        right = right + skim(left);
    }
    
    private double skim(double v) {
        // gain determines how much to skim off the top
        if (v > 1.0)
          return -((v - 1.0) * gain);
        else if (v < -1.0)
          return -((v + 1.0) * gain);
        return 0;
    }
    
    private double x=0, y=0;
    private double prevLeftEncoderDistance=0, prevRightEncoderDistance=0;
    
    public void setXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    int counter = 0;
    public void calcXY()
    {
        double distance, leftEncoderDistance, rightEncoderDistance, gyroAngle;
        
        leftEncoderDistance = driveTrainEncoderL.getDistance();
        rightEncoderDistance = driveTrainEncoderR.getDistance();
        gyroAngle = getAngle();
        distance =  ((leftEncoderDistance - prevLeftEncoderDistance) + (rightEncoderDistance - prevRightEncoderDistance))/2;
        x = x + distance * Math.sin(Math.toRadians(gyroAngle));
        y = y + distance * Math.cos(Math.toRadians(gyroAngle));
        prevLeftEncoderDistance = leftEncoderDistance;
        prevRightEncoderDistance = rightEncoderDistance;
    }
    
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    
    public void resetPosition()
    {
    	driveTrainEncoderL.reset();
    	driveTrainEncoderR.reset();
        imu.zeroYaw();
        setXY(0,0);
        this.prevLeftEncoderDistance = 0;
        this.prevRightEncoderDistance = 0;
    }
    
    public double getAngle() {
    	return imu.getYaw();
    }
    
    public double getLeftDistance() {
    	return driveTrainEncoderL.getDistance();
    }
    
    public double getRightDistance() {
    	return driveTrainEncoderR.getDistance();
    }
}

