package org.usfirst.frc.team6013.logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class JoystickWindowWrapper implements LiveWindowSendable {
	private ITable m_Table = null;
	private Joystick m_Joystick = null;
	private int m_Port = 0;
	
	public JoystickWindowWrapper(Joystick joystick, int port) {
		m_Joystick = joystick;
		m_Port = port;
	}
	
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
		return "Joystick";
	}

	@Override
	public void updateTable() {
		//write axis
		int count = m_Joystick.getAxisCount();
		String temp = "Joystick" + m_Port + " - Axis";
		for(int i=0; i < count; i++) {
			m_Table.putNumber(temp + i, m_Joystick.getRawAxis(i));
		}
		
		//write buttons
		count = m_Joystick.getButtonCount();
		temp = "Joystick" + m_Port + " - Button";
		for(int i=1; i <= count; i++) {
			m_Table.putBoolean(temp + i, m_Joystick.getRawButton(i));
		}
	}

	@Override
	public void startLiveWindowMode() {
	}

	@Override
	public void stopLiveWindowMode() {
	}

}
