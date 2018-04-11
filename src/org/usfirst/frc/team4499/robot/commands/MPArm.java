package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotConfig;

import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MPArm extends Command {
	private double endpoint;
	public static double currentAngle;
	private double startingAngle;
	private int run;
	private int angleTolerance;
	private double crateMultiplier;
	private double startTime;
	private double minPower= 0.280;//for comp bot 0.280; for the practice bot0.370
	private double cosMultiplier =0.122; //for comp bot0.122; for the practice bot 0.152;
	private SetLEDColor setLEDColor;
	public MPArm(double angle, int tolerance) {
    	endpoint= angle;
    	angleTolerance= tolerance; 	
    	crateMultiplier= 0;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	startingAngle=-(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180;
        run=0;    	
    	RobotMap.brake.set(RobotMap.releaseBrake);
    	if(RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
	    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    	}
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   
    if(RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
	    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    }

    if(RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
	    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, RobotConfig.timeOut);
    }
    currentAngle=-(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180;
   
    //If the ultrasound is able to detect a nearby crate, set the value to high, else keep it low
    if(RobotMap.analog.getValue()>245) {
    	crateMultiplier = 0.75;
    	
    
    }
    else {
    	crateMultiplier= 1.40;//1.30 on comp?
    

    }
    if(startingAngle<endpoint) {
    	if(currentAngle + angleTolerance<endpoint) {
    		RobotMap.armMaster.set(ControlMode.PercentOutput, -minPower  + crateMultiplier*(-cosMultiplier*Math.cos((-currentAngle*Math.PI)/180))); 	
    	}
    	else {
    		run++;
    		RobotMap.brake.set(RobotMap.setBrake);
    		RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
    	}
    }
    else if(startingAngle>endpoint) {
    	if(currentAngle-angleTolerance>endpoint) {
        	RobotMap.armMaster.set(ControlMode.PercentOutput, +minPower  +crateMultiplier*(-cosMultiplier*Math.cos((-currentAngle*Math.PI)/180)));
        }
        else {
         	run++;
        	RobotMap.brake.set(RobotMap.setBrake);
        	RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
        }
    	
    }
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(RobotMap.armMaster.getMotorOutputPercent()==0 && run!=0) {
    		return true;
    	}
    	if(Math.abs(Timer.getFPGATimestamp()-startTime)>5.00) {
    		return true;
    	}
    	if((OI.armForwardIntake.get()||OI.armReverseIntake.get()||OI.armReverseShoot.get()||OI.armForwardShoot.get()||Math.abs(OI.joyStickTwo.getRawAxis(5))>0.15)&&Math.abs(Timer.getFPGATimestamp()-startTime)>0.25){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	RobotMap.brake.set(RobotMap.setBrake);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
