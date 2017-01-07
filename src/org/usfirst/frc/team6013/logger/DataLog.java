package org.usfirst.frc.team6013.logger;

import java.io.*;
import java.util.LinkedList;
import java.util.Set;

import org.usfirst.frc.team6013.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;


public class DataLog {
	private boolean isLogging;
	private FileWriter fw;
	private double startTime;
	private LinkedList<TableInfo> tables;
	private double lastFlush;
	
	public DataLog() {
		tables = new LinkedList<TableInfo>();
		startTime = 0;
		fw = null;
		isLogging = false;
	}
	
	public void setLogging(boolean value) {
		isLogging = value;
	}
	
	public void addTable(LiveWindowSendable table, String name) {
		TableInfo ti = new TableInfo();
		
		//update data
		table.initTable(new MemoryTable());
		table.updateTable();
		
		//fill table structure
		ti.table = table;
		Set<String> test = table.getTable().getKeys();
		String[] keys = new String[test.size()];
		int i=0;
		for(String key : test) {
			keys[i] = key;
			i++;
		}
		ti.keys = keys;	
		ti.name = name;
		tables.add(ti);
	}
	
	public void addTable(LiveWindowSendable table) {
		addTable(table, null);
	}
	
	public void Periodic() {
		//check if logging is enabled
		if(isLogging == true) {
			//check if the log file has been started
			if(fw == null) {
				//start logging
				try {
					fw = new FileWriter(Robot.getLogPath() + "log.csv");
					writeHeader();
					startTime = Timer.getFPGATimestamp();
					lastFlush = startTime;
				} catch (IOException e) {
					isLogging = false;
				}
			}
			
			//normal logging loop
			writeLogLine();
		} else {
			//logging is off
			if(fw != null) {
				//shut down logging
				try {
					fw.close();
				} catch (IOException e) {
					//don't care if closing the logger causes exception
				}
				fw = null;
			}
		}
	}
	
	private void writeHeader() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("Time|");
		
		//for each table
		for(int i=0; i<tables.size(); i++) {
			TableInfo ti = tables.get(i);
			String tableName;
			if(ti.name != null) {
				tableName = ti.name;
			} else {
				tableName = ti.table.getSmartDashboardType();
			}
			
			//for each key in the table
			for(int j = 0; j<ti.keys.length; j++) {
				sb.append(tableName);
				sb.append(" - ");
				sb.append(ti.keys[j]);
				sb.append('|');
			}
		}
		
		//write new line at end of line
		sb.append('\n');
		
		//write to file
		if(fw != null) {
			try {
				fw.write(sb.toString());
			} catch (IOException e) {
				isLogging = false;
			}
		}
	}
	
	private void writeLogLine() {
		StringBuilder sb = new StringBuilder(); 
		
		//write time stamp
		double logTime = Timer.getFPGATimestamp() - startTime;
		sb.append(String.format("%.3f", logTime));
		sb.append('|');
		
		//write all the tables
		for(int i=0; i<tables.size(); i++) {
			TableInfo ti = tables.get(i);
			
			//update the table
			ti.table.updateTable();
			ITable table = ti.table.getTable();
			
			//for each key in the table
			for(int j = 0; j<ti.keys.length; j++) {
				Object value = table.getValue(ti.keys[j], 0);
				if(value instanceof Double) {
					//if double, reduce to 3 decimal points
					sb.append(String.format("%.3f", value));
				} else {
					sb.append(value.toString());
				}
				sb.append('|');
			}
		}
		
		//write new line at end of line
		sb.append('\n');
		
		//write to file
		if(fw != null) {
			try {
				fw.write(sb.toString());
				if ((Timer.getFPGATimestamp() - lastFlush) > 1) {
					fw.flush();
					lastFlush = Timer.getFPGATimestamp();
				}
			} catch (IOException e) {
				isLogging = false;
			}
		}
	}
	
	private class TableInfo {
		String[] keys;
		LiveWindowSendable table;
		String name = null;
	}
}
