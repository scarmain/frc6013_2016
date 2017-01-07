/*
IO list
-------
PWM1 left  motor 1
PWM2 right motor 1
PWM3 (left  motor 2)
PWM4 (right motor 2)
PWM5 steering motor (two of them)
PWM6 (arm elbow motor)
PWM7 
PWM8 
PWM9 
PWM10 

R1 Air compressor spike relay (Diagnostic)
R2 Pole alignment (and red light)
R3 (White light soleniod)
R4 (Blue light soleniod)
R5
R6
R7
R8

S1 Gripper open solenoid
S2 Minibot launch solenoid
S3 
S4 
S5 
S6 
S7 
S8

DIO1 Diagnostic robot
DIO2 Diagnostic operator interface
DIO3 (Left light sensor)
DIO4 (Center light sensor)
DIO5 (Right light sensor)
DIO6 (Autonomous mode bit 0)
DIO7 (Autonomous mode bit 1)
DIO8 (Autonomous mode bit 2)
DIO9 
DIO10 Nasson pressure switch
DIO11 (Encoder input A)
DIO12 (Encoder input B)
DIO13 
DIO14 

AIO1 gyro
AIO2 (Left   distance sensor)
AIO3 (Right  distance sensor)
AIO4 Center distance sensor
AIO5 Side   distance sensor to look for the pole
AIO6 (elbow pot)
AIO7 
AIO8 Battery voltage

Operator interface
USB port1 driver joystick
USB port2 arm joystick
(USB port3 Cypris module)
USB port4 Stop button

Joystick1 Driver
X-axis turns left or right
Y-axis go foreward of backward
Button 1 or Trigger reverse logical front of the robot
Button 2 -
Button 3 - (Load White logo light)
Button 4 - Load Red   logo light
Button 5 - (Load Blue  logo light)
Button 6 - Pipe Seek Off
Button 7 - Pipe Seek On
Button 8 -
Button 9 -
Button 10  Emergency Stop Clear
Button 11  Emergency Stop Set

Joystick2 Arm
X-axis Nothing
Y-axis Elbow up/down
Button 1 (Manual arm movement, cancel AutoArm)
Button 2 (Boost)
Button 3 (Arm position Low)
Button 4 (Arm position Mid)
Button 5 (Arm position High)
Button 6 Gripper close
Button 7 Gripper close
Button 8 
Button 9 
Button 10 Minibot launch
Button 11 Minibot unlaunch
Button 10 After 110 seconds and both buttons pressed Launch Mini-Bot
Buttons 10 & 11 Immediately Launch Mini-Bot
*/

package org.usfirst.frc.team6013.robot;

import java.io.File;

import org.usfirst.frc.team6013.logger.DataLog;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/* SdDiagnostics
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team6013.robot.StringUtils;
import org.usfirst.frc.team6013.robot.SdDiagnostics;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.Gyro;
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.CounterBase;
//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.parsing.IInputOutput;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Jaguar;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.Relay;
//import com.sun.squawk.util.MathUtils;
import java.lang.Math;
*/

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends IterativeRobot {
	/* SdDiagnostics
    SendableChooser chooser;
	public static SdDiagnostics sdDiagnostics = null;
	public static StringUtils stringUtils;
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    */
	static Inputs Inputs;
	static DriveTrain DriveTrain;
	static Shooter Shooter;
	static Outputs Outputs;
	static DataLog Logger;
	static Camera Camera;
	static Auto Auto;
	static Robot RobotClass;
	static SendableChooser AutoChooser;
	
    private static String logPath;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	/* SdDiagnostics
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        stringUtils = new StringUtils();
        LiveWindow.setEnabled(false);				// Prevents another dashboard from coming up in Test mode.
 
        sdDiagnostics = new SdDiagnostics();
        sdDiagnostics.init();
    	*/

    	RobotClass = this;
    	
    	findLogPath();
        Logger = new DataLog();
        
    	Inputs = new Inputs();
    	Shooter = new Shooter();
    	DriveTrain = new DriveTrain();
    	Outputs = new Outputs();
    	Camera = new Camera();
    	Auto = new Auto();
    	
    	Shooter.Init();
    	DriveTrain.Init();
        Outputs.Init();
        Camera.Init();
        
        Logger.addTable(DriveTrain);
        Logger.addTable(Shooter);
        
    	AutoChooser = new SendableChooser();
    	AutoChooser.addDefault("Run Low Bar", org.usfirst.frc.team6013.robot.Auto.Auto_LowBar);
    	AutoChooser.addObject("Run Moat", org.usfirst.frc.team6013.robot.Auto.Auto_Moat);
    	AutoChooser.addObject("Do Nothing", org.usfirst.frc.team6013.robot.Auto.Auto_None);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	/* SdDiagnostics
    	autoSelected = (String) chooser.getSelected();
		//autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		*/   	
    	//Auto.setAuto((String)AutoChooser.getSelected());
    	Auto.Init();
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	/* SdDiagnostics
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    	*/
    	
    	Auto.Periodic();
    	Outputs.Periodic();
    	
    	Logger.setLogging(true);
    	Logger.Periodic();
    }

    public void teleopInit() {
    	//things to do on first teleop phase
    	
    }
	
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        /* SdDiagnostics
        sdDiagnostics.readDiagnosticsMode();
        sd/Diagnostics.putDiagnostics();
        */
    	DriveTrain.Periodic();  	
    	Shooter.Periodic();
    	Outputs.Periodic();
    	Camera.Periodic();
    	
    	Logger.setLogging(true);
    	Logger.Periodic();
    }
    
    public void testInit() {
    	
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	DriveTrain.Periodic();  	
    	Shooter.Periodic();
    	Outputs.Periodic();
    	Camera.Periodic();
    	
    	Logger.setLogging(true);
    	Logger.Periodic();
    }
    
    public void disabledInit() {
    	
    }
    
    public void disabledPeriodic() {
    	Camera.Periodic();
    	//SmartDashboard.putString("AutoMode", (String)AutoChooser.getSelected());
    	Logger.setLogging(false);
    	Logger.Periodic();
    }
    
    public static boolean isAutonomousStatic() {
    	return RobotClass.isAutonomous();
    }
    
    final double rampValue = 0.06;
    
    public double rampDrive(double desired, double last) {
    	if((desired - last) > rampValue) {
    		return last + rampValue;
    	} else if((last - desired ) > rampValue) {
    		return last - rampValue;
    	} else {
    		return desired;
    	}
    }
    
    public static String getLogPath() {
    	return logPath;
    }
    
    private void findLogPath() {
    	String path = "";
    	int folderNum = 0;
    	
    	if(isSimulation()) {
    		path = "log/";
    	} else {
    		path = "/U/log/";
    	}
    	String drivePath = path + "drive";
    	drivePath=drivePath.replace('/', File.separatorChar);
    	
    	File[] directories = new File(path).listFiles();
    	if(directories != null) {
	    	for(File file : directories) {
	    		if(file.isDirectory()) {
	    			String filePath = file.getAbsolutePath();
	    			int index = filePath.indexOf(drivePath);

	    			if(index >= 0) {
	    				String number = filePath.substring(index + drivePath.length(), filePath.length());
	    				int newNum = Integer.parseInt(number);
	    				if(newNum >= folderNum) {
	    					folderNum = newNum + 1;
	    				}
	    			}
	    		}
	    	}
    	}
    	
    	logPath = drivePath + folderNum + File.separatorChar;
    	File folder = new File(logPath);
    	folder.mkdirs();
    }
}

