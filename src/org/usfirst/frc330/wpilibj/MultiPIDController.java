/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.wpilibj;

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

 
/**
 *
 * @author joe
 */
public class MultiPIDController extends PIDController{
	
    public MultiPIDController(PIDGains gains, PIDSource source, PIDOutput output, double period)
    {
        super(gains.getP(),gains.getI(),gains.getD(),gains.getF(),source,output,period);
    }
    
    public MultiPIDController(PIDGains gains, PIDSource source, PIDOutput output)
    {
        super(gains.getP(),gains.getI(),gains.getD(),gains.getF(),source,output);
    }

    public void setPID(PIDGains gains) {
        setPID(gains.getP(), gains.getI(), gains.getD(), gains.getF());
        Robot.logger.println("Changing " + this.toString() + " Gains to " + gains.toString() + ". P=" + gains.getP());
        
    }
    
    public void setMaxOutput(double maxOutput) {
    	setOutputRange(-maxOutput,maxOutput);
    }

}
