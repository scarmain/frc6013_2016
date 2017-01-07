package org.usfirst.frc.team6013.robot;

/*
import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class Position implements LiveWindowSendable{
	private BuiltInAccelerometer builtAccel;
	private ADXRS450_Gyro gyro;
	private ADXL362 externAccel;
    private AccelChannel channel;
    
    public void Init() {
    	//initialize inputs
    	builtAccel = new BuiltInAccelerometer();
    	gyro = new ADXRS450_Gyro();
    	externAccel = new ADXL362(Range.k2G);
    	
    	//add local variables
    	channel = new AccelChannel();
    	channel.q = 1;			//process noise
    	channel.r = 120; 		//sensor noise
    	
    	//add inputs to logging table
    	Robot.Logger.addTable(builtAccel, "BuiltInAccelerometer");
    	Robot.Logger.addTable(gyro);
    	Robot.Logger.addTable(externAccel, "ADXL362");
    }
    
    public void Periodic() {
    	channel.addSample(externAccel.getY());
    	AverageFilter(channel);
    	MedianWeighted(channel);
    	KalmanFilter(channel);
    }

    private class AccelChannel {
    	public double[] samples = new double[5];
    	public double averageOutput;
    	public double medianWeightedOutput;
    	public double kalmanOutput;
    	
    	//kalman variables
    	public double q;
    	public double r;
    	public double x;
    	public double p;
    	public double k;
    	
    	public void addSample(double sample) {
    		//move the last samples down one element
    		for(int i=samples.length - 1; i > 0 ; i--) {
    			samples[i] = samples[i-1];
    		}
    		//make the most recent sample #1
    		samples[0] = sample;
    	}
    	
    	public double getNewestSample() {
    		return samples[0];
    	}
    	
    	public double getFilteredData() {
    		return averageOutput;
    	}
    }
    
    private void AverageFilter(AccelChannel ac) {
    	//simple average of the samples
    	double sum = 0;
    	
    	for(double sample : ac.samples) {
    		sum += sample;
    	}
    	
    	ac.averageOutput = sum/ac.samples.length;
    }
    
    private void MedianWeighted(AccelChannel ac) {
    	//weighted average of the samples, throwing out min and max value
    	double minSample = Double.MAX_VALUE;
    	double maxSample = Double.MIN_VALUE;
    	double sum = 0;
    	
    	for(double sample : ac.samples) {
    		sum += sample;
    		minSample = Double.min(minSample, sample);
    		maxSample = Double.max(maxSample, sample);
    	}
    	
    	sum = sum - minSample - maxSample;
    	ac.medianWeightedOutput = sum / (ac.samples.length - 2);
    }
    
    private void KalmanFilter(AccelChannel ac) {
    	//from http://interactive-matter.eu/blog/2009/12/18/filtering-sensor-data-with-a-kalman-filter/
    	ac.p = ac.p + ac.q;
    	ac.k = ac.p/(ac.p + ac.r);
    	ac.x = ac.x + ac.k * (ac.getNewestSample() - ac.x);
    	ac.p = (1-ac.k) * ac.p;
    	
    	ac.kalmanOutput = ac.x;
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
		return "Position";
	}

	@Override
	public void updateTable() {
		if(m_Table != null) {
			m_Table.putNumber("Average", channel.averageOutput);
			m_Table.putNumber("MedianWeighted", channel.medianWeightedOutput);
			m_Table.putNumber("Kalman", channel.kalmanOutput);
		}
	}

	@Override
	public void startLiveWindowMode() {
	}

	@Override
	public void stopLiveWindowMode() {
	}
}
*/
