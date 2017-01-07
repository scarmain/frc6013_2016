package org.usfirst.frc.team6013.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class DriveTrain implements LiveWindowSendable {
	private double leftOutput = 0;
	private double rightOutput = 0;
	private final boolean squaredInputs = true;
	
	private double inputLeft = 0;
	private double inputRight = 0;
	private double inputSpeed = 0;
	private double inputTurn = 0;
	
	public void Init() {
		leftOutput = 0;
		rightOutput = 0;
	}
	
	public void Periodic() {
		inputLeft = Robot.Inputs.getLeftSpeed();
		inputRight = Robot.Inputs.getRightSpeed();
		//TankDrive(leftSpeed, rightSpeed);
		
		inputSpeed = Robot.Inputs.getSpeed();
		if(Robot.Inputs.isDriveScrollLeft()) {
			inputTurn = -0.3;
		} else if(Robot.Inputs.isDriveScrollRight()) {
			inputTurn = 0.3;
		} else {
			inputTurn = Robot.Inputs.getTurn();
		}
		ArcadeDrive(inputSpeed, inputTurn);
	}
	
	@SuppressWarnings("unused")
	private void TankDrive(double leftSpeed, double rightSpeed) {
		//from WPILib
		
	    // square the inputs (while preserving the sign) to increase fine control
	    // while permitting full power
		leftSpeed = limit(leftSpeed);
		rightSpeed = limit(rightSpeed);
	    if (squaredInputs) {
	      if (leftSpeed >= 0.0) {
	    	  leftSpeed = (leftSpeed * leftSpeed);
	      } else {
	    	  leftSpeed = -(leftSpeed * leftSpeed);
	      }
	      if (rightSpeed >= 0.0) {
	    	  rightSpeed = (rightSpeed * rightSpeed);
	      } else {
	    	  rightSpeed = -(rightSpeed * rightSpeed);
	      }
	    }
	    
	    leftOutput = leftSpeed;
		rightOutput = rightSpeed;
	}
	
	private void ArcadeDrive(double moveValue, double rotateValue) {
	    moveValue = limit(moveValue);
	    rotateValue = limit(rotateValue);

	    if (squaredInputs) {
	      // square the inputs (while preserving the sign) to increase fine control
	      // while permitting full power
	      if (moveValue >= 0.0) {
	        moveValue = (moveValue * moveValue);
	      } else {
	        moveValue = -(moveValue * moveValue);
	      }
	      if (rotateValue >= 0.0) {
	        rotateValue = (rotateValue * rotateValue);
	      } else {
	        rotateValue = -(rotateValue * rotateValue);
	      }
	    }

	    if (moveValue > 0.0) {
	      if (rotateValue > 0.0) {
	    	  leftOutput = moveValue - rotateValue;
	    	  rightOutput = Math.max(moveValue, rotateValue);
	      } else {
	    	  leftOutput = Math.max(moveValue, -rotateValue);
	    	  rightOutput = moveValue + rotateValue;
	      }
	    } else {
	      if (rotateValue > 0.0) {
	    	  leftOutput = -Math.max(-moveValue, rotateValue);
	    	  rightOutput = moveValue + rotateValue;
	      } else {
	    	  leftOutput = moveValue - rotateValue;
	    	  rightOutput = -Math.max(-moveValue, -rotateValue);
	      }
	    }
	}
	
	private double limit(double value) {
		if(value > 1) {
			return 1;
		} else if (value < -1) {
			return -1;
		} else {
			return value;
		}
	}
	
	public double getLeftSpeed() {
		return leftOutput;
	}
	
	public double getRightSpeed() {
		return rightOutput;
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
		return "DriveTrain";
	}

	@Override
	public void updateTable() {
	    if (m_Table != null) {
	    	m_Table.putNumber("InputLeft", inputLeft);
	    	m_Table.putNumber("InputRight", inputRight);
	    	m_Table.putNumber("InputTurn", inputTurn);
	    	m_Table.putNumber("InputSpeed", inputSpeed);
	    	m_Table.putNumber("OutputLeft", leftOutput);
	    	m_Table.putNumber("OutputRight", rightOutput);
	    }
	}

	@Override
	public void startLiveWindowMode() {
	}

	@Override
	public void stopLiveWindowMode() {
	}

}
