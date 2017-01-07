package org.usfirst.frc.team6013.robot;

import edu.wpi.first.wpilibj.Timer;

public class Auto {
	private double autoStartTime = 0;
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	private String autoMode = Auto_None;
	
	public static final String Auto_None = "None";
	public static final String Auto_LowBar = "LowBar";
	public static final String Auto_Moat = "Moat";
	
	public void Init() {
		autoStartTime = Timer.getFPGATimestamp();
		setAuto(Auto_LowBar);
	}
	
	public void Periodic() {
		double autoTime = Timer.getFPGATimestamp() - autoStartTime;
		if(autoMode.equals(Auto_LowBar)) {
			autoLowBar(autoTime);
		} else if(autoMode.equals(Auto_None)) {
			//do nothing, auto mode none
			leftSpeed = 0;
			rightSpeed = 0;
		}
	}
	
	public void setAuto(String auto) {
		autoMode = auto;
	}
	
	public void autoLowBar(double autoTime) {
		//drive forward slowly for 7 seconds
		if(autoTime < 7) {
			leftSpeed = 0.25;
			rightSpeed = 0.25;
		} else {
			leftSpeed = 0;
			rightSpeed = 0;
		}
	}
	
	public double getLeftSpeed() {
		return leftSpeed;
	}
	
	public double getRightSpeed() {
		return rightSpeed;
	}
}
