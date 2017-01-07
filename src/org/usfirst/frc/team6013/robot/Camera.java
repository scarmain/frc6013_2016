package org.usfirst.frc.team6013.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Camera {
	int usbSession;
	Image usbFrame;
	
	public void Init() {
		try{
			usbFrame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
			// the camera name (ex "cam0") can be found through the roborio web interface
			usbSession = NIVision.IMAQdxOpenCamera("cam1",
					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(usbSession);
		} catch (Exception e) {
			//bad programming with default catch, but dont want robot to crash on camera error
			System.out.print("");
		}
	}
	
	public void Periodic() {
		try {
			//usb camera
			NIVision.IMAQdxGrab(usbSession, usbFrame, 1);
			CameraServer.getInstance().setImage(usbFrame);
		} catch (Exception e) {
			//bad programming with default catch, but dont want robot to crash on camera error
			System.out.print("");
		}
	}
	
	@SuppressWarnings("unused")
	private void AxisCameraDemo() {
		Image frame;
	    AxisCamera camera;
	    
	    //init
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // open the camera at the IP address assigned. This is the IP address that the camera
        // can be accessed through the web interface.
        camera = new AxisCamera("10.1.91.100");
        
        //cyclic
        /* grab an image from the camera, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
        camera.getImage(frame);
        
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);

        CameraServer.getInstance().setImage(frame);

	}
	
	@SuppressWarnings("unused")
	private void UsbCameraDemo() {
	    int session;
	    Image frame;
	    
	    //init
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
        //cyclic
        NIVision.IMAQdxStartAcquisition(session);
        /*
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
        //while (isOperatorControl() && isEnabled()) {
            NIVision.IMAQdxGrab(session, frame, 1);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            Timer.delay(0.005);		// wait for a motor update time
        //}
        NIVision.IMAQdxStopAcquisition(session);
	}
}
