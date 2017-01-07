/* Smart dashboard Diagnostics
 * 
 */
package org.usfirst.frc.team6013.robot;

//package org.usfirst.frc.team66.robot;

//import org.usfirst.frc.team66.robot.parameters.ControllerParams;
//import org.usfirst.frc.team66.robot.parameters.IOParams;

//import org.usfirst.frc.team66.robot.Autonomous;
//import org.usfirst.frc.team66.robot.Elevator;
//import org.usfirst.frc.team66.robot.Inputs;
//import org.usfirst.frc.team66.robot.SlideDrive;
import org.usfirst.frc.team6013.robot.StringUtils;

//import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Timer;


public class SdDiagnostics {

    private static int m_diagnosticMode = 0;
    private static int kMaximumDiagnosticMenus = 6;
    private static boolean m_diagOff = false;

    private static final String kSpaces40 = "                                        ";
    private static final String kSpaces21 = "                     ";

    public final static double kEpsilon = 0.1;

    //public SpeedController leftMotor1;
    //public SpeedController rightMotor1;
     //public SpeedController Robot.leftMotor1;
    //public SpeedController Robot.rightMotor1;
    
    //public Elevator elevator;				// Access to the public class Elevator in Robot.


	/**
	 *  Display the initial screen.
	 *  Clear these fields at Robot Init time and show the menu.
	 */
	public static void init() {
       	SmartDashboard.putString("Autonomous mode:", "0");
       	SmartDashboard.putString("Diagnostic screen:", "0");

       	//m_diagnosticLast = Timer.getFPGATimestamp();// Set the initial time.

		putMenu();									// Display the menu.

	} // public void


	static void putMenu() {
       	SmartDashboard.putString("0",  "          Team 470 Flexible Smart Dashboard");
       	//SmartDashboard.putString("1",  "                 Diagnostics Menu     Autonomous " + StringUtils.format (Autonomous.getAutonomousMode(), 2));
       	SmartDashboard.putString("1",  "                 Diagonsics Menu     Autonomous " + StringUtils.format (0, 2));
       	SmartDashboard.putString("2",  kSpaces21 + "                 0  Off");
       	SmartDashboard.putString("3",  kSpaces21 + "                 1  Drive");
       	SmartDashboard.putString("4",  kSpaces21 + "                 2  Gripper");
       	SmartDashboard.putString("5",  kSpaces21 + "                 3  Lift");
       	SmartDashboard.putString("6",  kSpaces21 + "                 4  Camera");
       	SmartDashboard.putString("7",  kSpaces21 + "                 5  Autonomous");
       	SmartDashboard.putString("8",  kSpaces21 + "                 6  Misc.");
       	SmartDashboard.putString("9",  kSpaces40);		// Clear the error line.
	}


	public static int readDiagnosticsMode() {
		String s = "";
		int d = 0;

	    try {
	        s = SmartDashboard.getString("Diagnostic screen:");
	    } catch (Exception ex)  {
	    	SmartDashboard.putString("9", "Error in getDiagnosticsMode getting Diagnostics mode.");
	    }

	    if (s.length() == 0) s = "0";
	    d = Integer.valueOf(s);
        if((d < 0) || (d > kMaximumDiagnosticMenus)) {
        	SmartDashboard.putString("Diagnostic screen:", StringUtils.format(m_diagnosticMode, 2));	// Restore the old value in the display.
        	SmartDashboard.putString("9", "Invalid Diagnostic Mode value, must be 0 to 6: " + StringUtils.format(d, 2)); // Show an error.
        }
        else {
        	m_diagnosticMode = d;					// Update the diagnostic mode value.
        	//SmartDashboard.putString("9", "DiagnosticMode mode: " + StringUtil.format(m_diagnosticMode, 2));
        }

        return m_diagnosticMode;
	} // public static void readDiagnosticsMode() {


	public void putDiagnostics() {
    	double x, y;
    	String s;
    	//int i = 0;
        //final String sPositions = "                                                                   "; // This must be longer than sSale.
        //final String sScale     = "|L                 2                 3                  4   |   ";
        //final String kSpaces40 = "                                        ";


    	// Display this every 1/2 second to reduce traffic load.
    	//if((m_diagnosticLast + m_diagnosticInterval) < Timer.getFPGATimestamp()) {
    	//	return;
    	//}

        //readDiagnosticsMode();						// Put the diagnostic mode in Robot.diagnosticMode.

        switch (m_diagnosticMode) {
        	
            // No diagnostics to display.
            // Note never run diagnostics during a competition match, it can take excess CPU time.
            case 0:
                if (m_diagOff == false) {           // Do this once per entry into diagnostics Off.
                    m_diagOff = true;
                    putMenu();
                } // if (diagOff == false;
                break;


            // Driver Joystick and diagnostics.
            case 1:
            	SmartDashboard.putString("0", "             Team 470 Flexible Smart Dashboard");
            	SmartDashboard.putString("1", "                                    T470 A-O  ");
             	SmartDashboard.putString("2", "                     Driver Joystick    Diagnostic: " + StringUtils.format (m_diagnosticMode, 2));

             	SmartDashboard.putString("3", "                   Left               Right");
             	
             	x = Robot.Inputs.getLeftSpeed();
             	y = Robot.Inputs.getRightSpeed();
             	s = "Stick     X"  + StringUtils.format(x, 5, 3);
             	s = s + "     Y" + StringUtils.format(y, 5, 3);
             	SmartDashboard.putString("4", s);
             	/*
             	s = "Motor     "  + StringUtils.format(Robot.leftDriveMotor1.get(), 5, 3);
             	s = s + "     " + StringUtils.format(Robot.rightDriveMotor1.get(), 5, 3);
             	SmartDashboard.putString("5", s);
             	*/
             	SmartDashboard.putString("6", "");
             	
             	SmartDashboard.putString("7", "");
             	
             	SmartDashboard.putString("8", "");
             	SmartDashboard.putString("9", "");

//             	s = "Motor     "  + StringUtils.format(Robot.slideDrive.getCommandedLeftSpeed(), 5, 3);
//             	s = s + "     " + StringUtils.format(Robot.slideDrive.getCommandedStrafeSpeed(), 5, 3);
//             	s = s + "     " + StringUtils.format(Robot.slideDrive.getCommandedRightSpeed(), 5, 3);
//            	m_diagnosticMode

//             	s = "Distance "  + StringUtils.format(Robot.slideDrive.getCommandedLeftDistance(), 7, 3);
//             	s = s + "  " + StringUtils.format(Robot.slideDrive.getCommandedStrafeDistance(), 7, 3);
//             	s = s + "  " + StringUtils.format(Robot.slideDrive.getCommandedRightDistance(), 7, 3);
//             	SmartDashboard.putString("5",  s);
/*
             	SmartDashboard.putString("6","Forward Speed:" + StringUtils.format(Robot.inputs.getForwardDriveSpeed(), 5, 3));
             	SmartDashboard.putString("7","Strafe Speed:   " + StringUtils.format(Robot.inputs.getStrafeDriveSpeed(), 5, 3));
             	SmartDashboard.putString("8","Turn Power:     " + StringUtils.format(Robot.inputs.getTurnPower(), 5, 3));
*/
             	//SmartDashboard.putString("9",  kSpaces40);	// Used for error messages.

                m_diagOff = false;
                break;


            // Gripper joystick and diagnostics.
            case 2:
            	SmartDashboard.putString("0", "             Team 470 Flexible Smart Dashboard");
            	SmartDashboard.putString("1", "                                    T470 A-O  ");
            	SmartDashboard.putString("2", "                        Gripper         Diagnostic: " + StringUtils.format (m_diagnosticMode, 2));

            	SmartDashboard.putString("3",  kSpaces40);

            	// Display the gripper position.
/*          	s = "Gripper switch:   " + (Inputs.isGripperButtonPressed() ? "Pressed" : "Released");
            	SmartDashboard.putString("4", s);

               	s = "Gripper is:           " + (Elevator.getGripper() ? "Closed" : "Open");
            	SmartDashboard.putString("5", s);
*/
            	SmartDashboard.putString("6", " ");
            	SmartDashboard.putString("7", " ");
            	SmartDashboard.putString("8", " ");

            	// Display the two roller motors.
            	//s = "Left   Intake Roller      Control: " + StringUtils.format(Robot.getLeftIntakeRollerControl(), 5, 3);
            	//s = s + "      Motor:" + StringUtils.format(payload.getLeftIntakeRollerMotor(), 5, 3);
            	//SmartDashboard.putString("3", s);

            	//s = "Right Intake Roller      Control: " + StringUtils.format(Robot.getRightIntakeRollerControl(), 5, 3);
            	//s = s + "      Motor:" + StringUtils.format(payload.getRightIntakeRollerMotor(), 5, 3);
            	//SmartDashboard.putString("4", s);

              	//SmartDashboard.putString("5",  kSpaces40);

            	// Display the Pivot system joy stick buttons.
              	//s =            "Close Pivot Button:  " + (Robot.getClosePivotControl() == payload.kJoystickButtonPressed ? "Pressed " : "Released");
              	//s = s + "      Open  Pivot Button:  " + (Robot.getOpenPivotControl()  == payload.kJoystickButtonPressed ? "Pressed  " : "Released");
              	//SmartDashboard.putString("6", s);

              	// Display the left pivot limit switches and motor.
            	//s = "Left   Pivot:          " + (payload.getLeftPivotOpenLimitSwitch() == payload.kLimitSwitchPressed ? "Open " : "Close");
            	//s = s + "       " + StringUtils.format(payload.getLeftPivotMotor(), 5, 3);
            	//s = s + "           " + (payload.getLeftPivotCloseLimitSwitch() == payload.kLimitSwitchPressed ? "Close" : "Open");
            	//SmartDashboard.putString("7", s);

              	// Display the right pivot limit switches and motor.
            	//s = "Right Pivot:          " + (payload.getRightPivotCloseLimitSwitch() == payload.kLimitSwitchPressed ? "Close" : "Open");
            	//s = s + "       " + StringUtils.format(payload.getRightPivotMotor(), 5, 3);
            	//s = s + "           " + (payload.getLeftPivotOpenLimitSwitch() == payload.kLimitSwitchPressed ? "Open " : "Close");
            	//SmartDashboard.putString("8", s);

              	//SmartDashboard.putString("9",  kSpaces40);

            	m_diagOff = false;
              	break;


            // Lift diagnostics.
            case 3:
            	SmartDashboard.putString("0", "             Team 470 Flexible Smart Dashboard");
            	SmartDashboard.putString("1", "                                    T470 A-O  ");
            	SmartDashboard.putString("2", "                         Lift         Diagnostic: " + StringUtils.format (m_diagnosticMode, 2));

            	//SmartDashboard.putString("3", "Lift Position:     " + sScale);
/*
            	x = (kLiftMax - kLiftMin) / (sScale.length() - 1);	// Computer the position for the carrot. (- 1 for the last "|".
            	i = (int) (Elevator.getLiftPosition() / x);
            	//i = (int) (Elevator.getLiftPosition() / x);  // for testing
            	if (i > sScale.length()) i = sScale.length();
            	if (i < 0) i = 0;
             	s = sPositions.substring(0, i);		// Make the string the right length.

             	x = Elevator.getLiftPosition();
            	if(x <= (Elevator.getElevatorLoadPosition() + kEpsilon)) 
            		s = s + "^";
            	else if((x >= (Elevator.getElevatorCarryPosition() - kEpsilon)) && (x <= (Elevator.getElevatorCarryPosition() + kEpsilon)))
            		s = s + "^";
                else if((x >= (Elevator.getElevatorStact1Position() - kEpsilon)) && (x <= (Elevator.getElevatorStact1Position() + kEpsilon)))
                	s = s + "^";
                else if((x >= (Elevator.getElevatorStact2Position() - kEpsilon)) && (x <= (Elevator.getElevatorStact2Position() + kEpsilon)))
                	s = s + "^";
                else s = s + "!";

            	SmartDashboard.putString("4", "                        " + s);

            	s = "Lift Buttons:            " + (Inputs.isLoadButtonPressed() == kJoystickButtonPressed ? "P" : "_") +
            		"              " + (Inputs.isCarryButtonPressed() == kJoystickButtonPressed ? "P" : "_") +
            		"              " + (Inputs.isStack1ButtonPressed() == kJoystickButtonPressed ? "P" : "_") +
            		"              " + (Inputs.isStack2ButtonPressed() == kJoystickButtonPressed ? "P" : "_");
            	SmartDashboard.putString("5", s);

            	SmartDashboard.putString("6", "Manual Lift:             " + (Inputs.isLiftUpButtonPressed() == kJoystickButtonPressed ? "Up" : "__") + 
            	                              "             " + (Inputs.isLiftDownButtonPressed() == kJoystickButtonPressed ? "Down" : "_____"));

            	// Show the lift motor speed and distance.
            	//s = "Lift Encoder: " + StringUtil.format(Robot.liftMotor.get(), 5, 3);   TESTING
            	//s = s + "      Motor:" + StringUtil.format(Robot.liftEncoder.getDistance(), 5, 3);
            	s =     "Lift Encoder Inches: " + StringUtils.format(Elevator.getLiftPosition(), 7, 2);
            	s = s + "    Lift Encoder Raw    :" + StringUtils.format(Elevator.getLiftPosRaw(), 5, 3);
            	SmartDashboard.putString("7", s);

                SmartDashboard.putString("8", "Lift Encoder Rate:" + StringUtils.format(Elevator.getLiftSpeed(), 5, 3));
            	//SmartDashboard.putString("9",  kSpaces40);
*/
            	m_diagOff = false;
              	break;


            // Camera diagnostics.
            case 4:
            	SmartDashboard.putString("0", "             Team 470 Flexible Smart Dashboard");
            	SmartDashboard.putString("1", "                                    T470 A-O  ");
            	SmartDashboard.putString("2", "                         Camera       Diagnostic: " + StringUtils.format (m_diagnosticMode, 2));

/*            	
            	SmartDashboard.putString("3", "Tote found:     " + (Vision.getIsTote() ? "True" : "False"));
            	SmartDashboard.putString("4", "Centered:       " + StringUtils.format(Vision.getCenter(), 5 , 2));
            	SmartDashboard.putString("5", "Distance:       " + StringUtils.format(Vision.getDistance(), 5 , 2));
            	SmartDashboard.putString("6", "Particles:       " + StringUtils.format(Vision.getNupParticles(), 5 , 2));
            	SmartDashboard.putString("7", "Particles Flitered:" + StringUtils.format(Vision.getFilteredParticles(), 5 , 2));
*/
            	SmartDashboard.putString("8", kSpaces40);
            	m_diagOff = false;
              	break;


            // Autonomous diagnostics.
            case 5:
            	SmartDashboard.putString("0", "             Team 470 Flexible Smart Dashboard");
            	SmartDashboard.putString("1", "                                    T470 A-O  ");
            	SmartDashboard.putString("2", "                         Autonomous    Diagnostic: " + StringUtils.format (m_diagnosticMode, 2));
/*
            	SmartDashboard.putString("3", "Autonomous Mode:      " + StringUtils.format (Autonomous.getAutonomousMode(), 2));

            	SmartDashboard.putString("4", "Past State    "   + StringUtils.format (Robot.autonomous.getPastStateInt(), 4) + "   " + Robot.autonomous.getPastState());
            	SmartDashboard.putString("5", "State           " + StringUtils.format (Robot.autonomous.getStateInt(), 4)     + "   " + Robot.autonomous.getState());
            	SmartDashboard.putString("6", "Next State   "    + StringUtils.format (Robot.autonomous.getNextStateInt(), 4) + "   " + Robot.autonomous.getNextState());

            	SmartDashboard.putString("7", kSpaces40);
            	SmartDashboard.putString("8", kSpaces40);
            	//SmartDashboard.putString("9", kSpaces40);
*/
            	m_diagOff = false;
              	break;


                // Misc. diagnostics.
            case 6:
            	SmartDashboard.putString("0", "             Team 470 Flexible Smart Dashboard");
            	SmartDashboard.putString("1", "                                    T470 A-O  ");
            	SmartDashboard.putString("2", "                       Miscellaneous   Diagnostic: " + StringUtils.format (m_diagnosticMode, 2));

            	SmartDashboard.putString("3", kSpaces40);
            	SmartDashboard.putString("4", kSpaces40);
            	SmartDashboard.putString("5", kSpaces40);
            	SmartDashboard.putString("6", kSpaces40);
            	SmartDashboard.putString("7", kSpaces40);
            	SmartDashboard.putString("8", kSpaces40);
            	//SmartDashboard.putString("9", kSpaces40);

            	m_diagOff = false;
              	break;

        } // Switch

    } // public void putDiagnostics () {

} // public class SdDiagnostics {
