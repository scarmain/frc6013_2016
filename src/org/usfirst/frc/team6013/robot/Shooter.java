package org.usfirst.frc.team6013.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class Shooter implements LiveWindowSendable {
	private double lastButtonPress;
	private double shootSpeed;
	private double shootHeight;
	private double shootServo;
	private Potentiometer shootPosition;
	private boolean lastBumpUp;
	private boolean lastBumpDown;
	private ShootDir heightDir;
	private double targetHeight;
	private double positionRead;
	
	private final double SHOOTER_HOLD_TIME = 0.5;
	private final double SHOOTER_HEIGHT_MIN = 1050;
	private final double SHOOTER_HEIGHT_MID = 665;
	private final double SHOOTER_HEIGHT_MAX = 190;
	
	private enum ShootDir {
		None,
		Up,
		Down
	}
	
	public void Init() {
		lastButtonPress = 0;
		shootSpeed = 0;
		shootHeight = 0;
		shootServo = 0;
		lastBumpUp = false;
		lastBumpDown = false;
		heightDir = ShootDir.None;
		targetHeight = 0;
		
		//shooter position (channel 0 with range 0-4096, no offset)
		shootPosition = new AnalogPotentiometer(0, 4096, 0);
	}
	
	public void Periodic() {
		double currentTime = Timer.getFPGATimestamp();
		//check if shoot speed button is pressed
		if(Robot.Inputs.isShooterFastOutPressed()) {
			lastButtonPress = currentTime;
			shootSpeed = 1.0;
			shootServo = 0.5;
		} else if(Robot.Inputs.isShooterSlowOutPressed()) {
			lastButtonPress = currentTime;
			shootSpeed = 0.4;
			shootServo = 0.5;
		} else if(Robot.Inputs.isShooterSlowInPressed()) {
			lastButtonPress = currentTime;
			shootSpeed = -0.25;
			shootServo = 0.0;
		}
		
		//check if button was released and we need to stop the motor
		if((currentTime - lastButtonPress) > SHOOTER_HOLD_TIME) {
			shootSpeed = 0;
			shootServo = 0;
		}
		
		//read position from pot
		positionRead = shootPosition.get();
		
		//set motor speed
		ShooterBump();
		if(heightDir == ShootDir.None) {
			shootHeight = Robot.Inputs.getShooterHeightSpeed();
		} else if(heightDir == ShootDir.Up) {
			shootHeight = -0.4;
			if(positionRead < targetHeight) {
				heightDir = ShootDir.None;
				shootHeight = 0;
			}
		} else if(heightDir == ShootDir.Down) {
			shootHeight = 0.4;
			if(positionRead > targetHeight) {
				heightDir = ShootDir.None;
				shootHeight = 0;
			}
		} else {
			//dummy if statement, should not be reached
			shootHeight = 0;
		}
		
		//dashboard debug
		SmartDashboard.putNumber("ShootPos", positionRead);
		SmartDashboard.putNumber("ShootHeight", shootHeight);
		double lowMid = (SHOOTER_HEIGHT_MIN + SHOOTER_HEIGHT_MID)/2;
		double highMid = (SHOOTER_HEIGHT_MID + SHOOTER_HEIGHT_MAX)/2;
		if(positionRead > lowMid) {
			SmartDashboard.putBoolean("ShootLow", true);
			SmartDashboard.putBoolean("ShootMid", false);
			SmartDashboard.putBoolean("ShootMax", false);
		} else if(positionRead < highMid) {
			SmartDashboard.putBoolean("ShootLow", false);
			SmartDashboard.putBoolean("ShootMid", false);
			SmartDashboard.putBoolean("ShootMax", true);
		} else {
			SmartDashboard.putBoolean("ShootLow", false);
			SmartDashboard.putBoolean("ShootMid", true);
			SmartDashboard.putBoolean("ShootMax", false);
		}
		
		//stop control if at ends
		if((positionRead > SHOOTER_HEIGHT_MIN) && (shootHeight > 0)) {
			shootHeight = 0;
			heightDir = ShootDir.None;
		}
		if((positionRead < SHOOTER_HEIGHT_MAX) && (shootHeight < 0)) {
			shootHeight = 0;
			heightDir = ShootDir.None;
		}
		

	}
	
	public double getShootSpeed() {
		return shootSpeed;
	}
	
	public double getShootHeightSpeed() {
		return shootHeight;
	}
	
	public double getShooterServo() {
		return shootServo;
	}
	
	/*
	public boolean isRaised() {
		return raised;
	}
	*/
	
	private void ShooterBump() {
		//shooter bump
		boolean bumpUp = Robot.Inputs.isShooterBumpUpPressed();
		if((lastBumpUp == false) && (bumpUp == true)) {
			if(positionRead > SHOOTER_HEIGHT_MID) {
				heightDir = ShootDir.Up;
				targetHeight = SHOOTER_HEIGHT_MID;
			} else if(positionRead > SHOOTER_HEIGHT_MAX) {
				heightDir = ShootDir.Up;
				targetHeight = SHOOTER_HEIGHT_MAX;
			} else {
				//at end, stop
				heightDir = ShootDir.None;
				targetHeight = 0;
			}
		}
		lastBumpUp = bumpUp;
		
		boolean bumpDown = Robot.Inputs.isShooterBumpDownPressed();
		if((lastBumpDown == false) && (bumpDown == true)) {
			if(positionRead < SHOOTER_HEIGHT_MID) {
				heightDir = ShootDir.Down;
				targetHeight = SHOOTER_HEIGHT_MID;
			} else if(positionRead < SHOOTER_HEIGHT_MIN) {
				heightDir = ShootDir.Down;
				targetHeight = SHOOTER_HEIGHT_MIN;
			} else {
				//at end, stop
				heightDir = ShootDir.None;
				targetHeight = 0;
			}
		}
		lastBumpDown = bumpDown;
	}
	
	private ITable m_Table;
	
	@Override
	public void initTable(ITable subtable) {
		m_Table = subtable;
	}

	@Override
	public ITable getTable() {
		return m_Table;
	}

	@Override
	public String getSmartDashboardType() {
		return "Shooter";
	}

	@Override
	public void updateTable() {
	    if (m_Table != null) {
	    	m_Table.putNumber("ShootSpeed", shootSpeed);
	    	m_Table.putNumber("ShootHeight", shootHeight);
	    	m_Table.putNumber("ShootServo", shootServo);
	    	m_Table.putNumber("PotRead", positionRead);
	    	m_Table.putNumber("TargetHeight", targetHeight);
	    }
	}

	@Override
	public void startLiveWindowMode() {
	}

	@Override
	public void stopLiveWindowMode() {
	}
}
