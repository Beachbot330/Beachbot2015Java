package org.usfirst.frc330.wpilibj;

public class PIDGains {
	private double P, I, D, F, maxOutput, maxOutputStep;
	
	public PIDGains(double p, double i, double d, double f, double maxOutput, double maxOutputStep) {
		P = p;
		I = i;
		D = d;
		F = f;
		this.maxOutput = maxOutput;
		this.maxOutputStep = maxOutputStep;
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
	public double getMaxOutput() {
		return maxOutput;
	}
	public double getMaxOutputStep() {
		return maxOutputStep;
	}
}