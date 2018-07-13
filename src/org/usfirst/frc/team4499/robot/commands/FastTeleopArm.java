package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotConfig;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.tools.PID;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FastTeleopArm extends Command {

	private double setPoint;
	private double startTime;
	private double kP = 0.006;
	private double kI = 0.0001;
	private double kD = 0.005;
	
	private double kPCrate = 0.005;
	private double kICrate = 0.0001;
	private double kDCrate = 0.01;
	
	private PID controller;
	private double startingAngle;

	
    public FastTeleopArm(double angle) {
        setPoint = angle;
        controller  = new PID(kP,kI,kD);
        controller.setSetPoint(setPoint);
        controller.setMaxOutput(0.6);
        controller.setMinOutput(-0.6);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
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
	    /*
	    if(RobotMap.analog.getValue()>240) {
	    	controller.setP(kP);
	    	controller.setI(kI);
	    	controller.setD(kD);
	    	
	    
	    }
	    else {oller.setI(kICrate);
	    	controller.setD(kDCrate);
	    }*/
	    double position = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180;
	    double power = controller.updatePID(position);
	    /*if(setPoint > 90&& position > 90 || setPoint<=90 && position<=90) {
	    	RobotMap.armMaster.set(ControlMode.PercentOutput, -power*Math.cos(position* 2*Math.PI / 180));
	    }
	    else {
	    	
	    }*/
	    RobotMap.armMaster.set(ControlMode.PercentOutput, -power);
	    System.out.println("Setpoint: " + setPoint + "  error:" + controller.getError() + "  Power" + power + "  Position" + position);
	    
	    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(Timer.getFPGATimestamp()-startTime)>3.00) {
    		return true;
    	}
    	if(Math.abs(controller.getError()) < 5) {
    		return true;
    	}
    	
    	if((OI.armForwardIntake.get()||OI.armReverseIntake.get()||OI.armReverseShoot.get()||OI.armForwardShoot.get()||Math.abs(OI.joyStickTwo.getRawAxis(5))>0.15)&&Math.abs(Timer.getFPGATimestamp()-startTime)>0.25){
    		System.out.println("exiting");
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Ended"+ (Timer.getFPGATimestamp()-startTime));
    	RobotMap.brake.set(RobotMap.setBrake);
    	RobotMap.armMaster.set(ControlMode.PercentOutput,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
