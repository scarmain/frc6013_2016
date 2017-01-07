package org.usfirst.frc.team6013.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;

public class Outputs {
	VictorSP leftDrive;
	VictorSP rightDrive;
	VictorSP leftShooter;
	VictorSP rightShooter;
	Spark shooterHeight;
	Servo shooterShoot;
	
	//Compressor compressor;
	//DoubleSolenoid shooterRaise;
	
	public void Init() {
		leftDrive = new VictorSP(IoConsts.LEFT_DRIVE_PWM);
		rightDrive = new VictorSP(IoConsts.RIGHT_DRIVE_PWM);
		leftShooter = new VictorSP(IoConsts.LEFT_SHOOTER_PWM);
		rightShooter = new VictorSP(IoConsts.RIGHT_SHOOTER_PWM);
		shooterHeight = new Spark(IoConsts.SHOOTER_HEIGHT_PWM);
		shooterShoot = new Servo(IoConsts.SHOOTER_SHOOT_PWM);
		
		/*
    	//initialise and start the compressor
        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);
        shooterRaise = new DoubleSolenoid(0,0,1);
        */
	}
	
	public void Periodic() {
		//drive train
		if(Robot.isAutonomousStatic()) {
			leftDrive.set(Robot.Auto.getLeftSpeed());
			rightDrive.set(Robot.Auto.getRightSpeed());
		} else {
			leftDrive.set(-Robot.DriveTrain.getLeftSpeed());
			rightDrive.set(-Robot.DriveTrain.getRightSpeed());
		}
		
		//shooter
		double shooterSpeed = Robot.Shooter.getShootSpeed();
		//double shooterSpeed = 0;
		leftShooter.set(shooterSpeed);
		rightShooter.set(shooterSpeed);
		shooterHeight.set(Robot.Shooter.getShootHeightSpeed());
		shooterShoot.set(Robot.Shooter.getShooterServo());
		/*
		//shooter pneumatics
		if(Robot.Shooter.isRaised()) {
			shooterRaise.set(Value.kForward);
		} else {
			shooterRaise.set(Value.kReverse);
		}
		*/
	}
}

