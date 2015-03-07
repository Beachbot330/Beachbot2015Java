/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.wpilibj;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

 
/**
 *
 * @author joe
 */
public class MultiPIDController extends PIDController{
	
    public class PIDGains {
    	private double P, I, D, F;
    	
    	public PIDGains(double p, double i, double d, double f) {
    		P = p;
    		I = i;
    		D = d;
    		F = f;
    	}
		public double getP() {
			return P;
		}
		public double getI() {
			return I;
		}
		public double getD() {
			return D;
		}
		public double getF() {
			return F;
		}
    	
    	
    }

    public MultiPIDController(PIDGains gains, PIDSource source, PIDOutput output, double period)
    {
        super(gains.getP(),gains.getI(),gains.getD(),gains.getF(),source,output,period);
    }
    
    public MultiPIDController(PIDGains gains, PIDSource source, PIDOutput output)
    {
        super(gains.getP(),gains.getI(),gains.getD(),gains.getF(),source,output);
    }
    
    public MultiPIDController(double p, double i, double d, PIDSource source, PIDOutput output)
    {
        super(p,i,d,source,output);
    }
    public MultiPIDController(double p, double i, double d, double f, PIDSource source, PIDOutput output, double period)
    {
        super(p,i,d,f,source,output,period);
    }
    
    public MultiPIDController(double p, double i, double d, double f, PIDSource source, PIDOutput output)
    {
        super(p,i,d,f, source,output);  
    }

    public void setPID(PIDGains gains) {
        setPID(gains.getP(), gains.getI(), gains.getD(), gains.getF());
    }

}
