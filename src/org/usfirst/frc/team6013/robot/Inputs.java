package org.usfirst.frc.team6013.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
	Joystick driverController;  			//set to ID 1 in DriverStation
	Joystick operatorController;
	final double DEADBAND = 0.2;		//how much percent of a deadband do we need
	
	public Inputs() {
        driverController = new Joystick(IoConsts.JOYSTICK_0);
        operatorController = new Joystick(IoConsts.JOYSTICK_1);
	}
	
	public double getLeftSpeed() {
		return deadband(driverController.getRawAxis(IoConsts.XBOX_LEFT_Y));
	}
	
	public double getRightSpeed() {
		return deadband(driverController.getRawAxis(IoConsts.XBOX_RIGHT_Y));
	}
	
	public double getSpeed() {
		//speed goes from -1 to 1
		double speed = deadband(driverController.getRawAxis(IoConsts.XBOX_LEFT_Y));
		//boost goes from 0-0.3 (trigger is 0-1, no negative)
		double boost = driverController.getRawAxis(IoConsts.XBOX_TRIGGER_RIGHT);
		
		return speed * (0.7 + (0.3 * boost));
	}
	
	public double getTurn() {
		return deadband(driverController.getRawAxis(IoConsts.XBOX_RIGHT_X));
	}
	
	private double deadband(double input) {
    	if((-DEADBAND < input) && (input < DEADBAND)) {
    		return 0;
    	} else {
    		return input;
    	}
	}
	
	public double getShooterHeightSpeed() {
		return deadband(operatorController.getRawAxis(IoConsts.XBOX_RIGHT_Y))*0.35;
	}
	
	public boolean isShooterSlowInPressed() {
		return operatorController.getRawButton(IoConsts.XBOX_A);
	}
	
	public boolean isShooterSlowOutPressed() {
		return operatorController.getRawButton(IoConsts.XBOX_Y);
	}
	
	public boolean isShooterFastOutPressed() {
		return operatorController.getRawButton(IoConsts.XBOX_B);
	}
	
	public boolean isShooterBumpDownPressed() {
		return operatorController.getRawButton(IoConsts.XBOX_LB);
	}
	
	public boolean isShooterBumpUpPressed() {
		return operatorController.getRawButton(IoConsts.XBOX_RB);
	}
	
	public boolean isDriveScrollLeft() {
		return driverController.getRawButton(IoConsts.XBOX_LB);
	}
	
	public boolean isDriveScrollRight() {
		return driverController.getRawButton(IoConsts.XBOX_RB);
	}
}

