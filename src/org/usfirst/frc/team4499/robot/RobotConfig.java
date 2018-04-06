package org.usfirst.frc.team4499.robot;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotConfig {
	//This is a place to put all sorts of constants that will be used in other places of the robot configuration
	public static double gearRatio = 7.5;
    public static double encoderTicsPerShaftRotation = 4096;
    public static double encoderTicsPerWheelRotation = gearRatio * encoderTicsPerShaftRotation ;
    public static double wheelDiam = 6.0;
    public static double wheelCircum = Math.PI * wheelDiam;
    
	public static double voltageControlMax = 11.0;
	
	
	public static int driveMotorContinuousCurrent = 40;//25;     //Amps
	public static int driveMotorPeakCurrent = 60;//50;			//Amps
	public static int driveMotorPeakCurrentDuration = 100;
	//temporarily taken out until research can be done
	public static int armMotorContinuousCurrent = 16;     //Amps
	public static int armMotorPeakCurrent = 23;			//Amps
	public static int armMotorPeakCurrentDuration = 100;//Milliseconds
//	public static int grabberMotorContinuousCurrent = 6;     //Amps
//	public static int grabberMotorPeakCurrent = 10;			//Amps
//	public static int grabberMotorPeakCurrentDuration = 100;
	public static boolean enableDriveCurrentLimit = true;
	//public static boolean enableArmCurrentLimit = true;
	//public static boolean enableGrabberCurrentLimit = true;
	public static int armMaxEncoderTicks = -2100;
	public static int armStartEncoderTicks = -722;
	public static char robotStartPosition; //U = unnasigned
	public static String fieldPositions="";
	public static double driverDeadZone = 0.15;
	
	public static int ultraSoundFailValue = 245;
	
	public static int timeOut = 4;//Milliseconds
	
	//This will run when this object is created, and will handle configuring all sensors 
	public RobotConfig() {
		setStartingConfig();
		
	}
	public void setStartingConfig() {
		//Defines robot start position based on digikey inputs
	   if(OI.switchOne.get()) {
	        RobotConfig.robotStartPosition = 'L';
	   }
	   else if(OI.switchTwo.get()) {
	        RobotConfig.robotStartPosition = 'C';
	   }
	   else if(OI.switchThree.get()) {
	        RobotConfig.robotStartPosition = 'R';
	   }
	   else {
	        RobotConfig.robotStartPosition = 'U';
	   }
		//Setup follower can talons
    	RobotMap.rightDriveFollowerOne.set(ControlMode.Follower, RobotMap.rightDriveLeadID);
    	RobotMap.rightDriveFollowerTwo.set(ControlMode.Follower, RobotMap.rightDriveLeadID);
    	RobotMap.leftDriveFollowerOne.set(ControlMode.Follower, RobotMap.leftDriveLeadID);
    	RobotMap.leftDriveFollowerTwo.set(ControlMode.Follower, RobotMap.leftDriveLeadID);
    	
    	//Invert the right hand side of the drive train
    	RobotMap.rightDriveLead.setInverted(true);
    	RobotMap.rightDriveFollowerOne.setInverted(true);
    	RobotMap.rightDriveFollowerTwo.setInverted(true);
    	
    	//TODO This particular motor runs backwards. If hardware changes this will need to be changed also.
    	RobotMap.leftDriveLead.setInverted(false);//runs backwards for comp, change to false for practice
    	RobotMap.leftDriveFollowerTwo.setInverted(false);//Runs backwards for Practice bot, change to false for comp
    	RobotMap.leftDriveFollowerOne.setInverted(true);//true on comp
    	
    	RobotMap.intakeLeft.setInverted(true);//true on Comp
    	RobotMap.intakeRight.setInverted(true);//true on Comp 
    	
    	//Setup and Enable current limiting for all drive motors
    	for(TalonSRX talon:RobotMap.driveMotors) {
    		talon.configContinuousCurrentLimit(RobotConfig.driveMotorContinuousCurrent, RobotConfig.timeOut);
    		talon.configPeakCurrentLimit(RobotConfig.driveMotorPeakCurrent, RobotConfig.timeOut);
    		talon.configPeakCurrentDuration(RobotConfig.driveMotorPeakCurrentDuration, RobotConfig.timeOut);
    		talon.enableCurrentLimit(RobotConfig.enableDriveCurrentLimit);
    	}
    	//temporarliy taken out until further research can be done
//      for(TalonSRX talon:RobotMap.armMotors) {
//    		talon.configContinuousCurrentLimit(RobotConfig.armMotorContinuousCurrent, RobotConfig.timeOut);
//    		talon.configPeakCurrentLimit(RobotConfig.armMotorPeakCurrent, RobotConfig.timeOut);
//    		talon.configPeakCurrentDuration(RobotConfig.armMotorPeakCurrentDuration, RobotConfig.timeOut);
//    		talon.enableCurrentLimit(RobotConfig.enableArmCurrentLimit);
//    	}
//    	for(TalonSRX talon:RobotMap.armMotors) {
//    		talon.configContinuousCurrentLimit(RobotConfig.armMotorContinuousCurrent, RobotConfig.timeOut);
//    		talon.configPeakCurrentLimit(RobotConfig.armMotorPeakCurrent, RobotConfig.timeOut);
//    		talon.configPeakCurrentDuration(RobotConfig.armMotorPeakCurrentDuration, RobotConfig.timeOut);
//    		talon.enableCurrentLimit(RobotConfig.enableArmCurrentLimit);
//    	}
    	RobotMap.leftDriveLead.configVoltageCompSaturation(RobotConfig.voltageControlMax, 10);
    	RobotMap.leftDriveLead.enableVoltageCompensation(false); 
    	RobotMap.leftDriveLead.configVoltageMeasurementFilter(32, 10);
    	RobotMap.rightDriveLead.configVoltageCompSaturation(RobotConfig.voltageControlMax, 10);
    	RobotMap.rightDriveLead.enableVoltageCompensation(false); 
    	RobotMap.rightDriveLead.configVoltageMeasurementFilter(32, 10);
    	
    	RobotMap.armMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		RobotMap.armMaster.setSelectedSensorPosition(0, 0, 0);
		RobotMap.armMaster.getSensorCollection().setAnalogPosition(0, 0);
		RobotMap.armMaster.setSensorPhase(true);
		
	    RobotMap.armMaster.getSensorCollection().setQuadraturePosition(this.armStartEncoderTicks, 0);
		//Setup follower can Talon
		RobotMap.armFollower.set(ControlMode.Follower, RobotMap.armMasterID);
		RobotMap.armMaster.setInverted(false);
		RobotMap.armFollower.setInverted(false);
		
		//Setup armMaster Nominal Outputs
		RobotMap.armMaster.configNominalOutputForward(0, 10);
		RobotMap.armMaster.configNominalOutputReverse(0, 10);
		RobotMap.armMaster.configPeakOutputForward(1, 10);
		RobotMap.armMaster.configPeakOutputReverse(-1, 10);
  
		
		RobotMap.intakeLeft.set(ControlMode.Follower, RobotMap.intakeRightID);
		RobotMap.intakeRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);

	}
	public void autoConfig() {
		RobotMap.leftDriveLead.enableVoltageCompensation(true);
		RobotMap.rightDriveLead.enableVoltageCompensation(true);
		RobotMap.rightDriveLead.configOpenloopRamp(0, 0);
    	RobotMap.leftDriveLead.configOpenloopRamp(0, 0);
	}
	public void teleopConfig() {
		RobotMap.leftDriveLead.enableVoltageCompensation(false);
		RobotMap.rightDriveLead.enableVoltageCompensation(false);
		RobotMap.rightDriveLead.configOpenloopRamp(0.5, 0);
    	RobotMap.leftDriveLead.configOpenloopRamp(0.5, 0);
		
	}
}
