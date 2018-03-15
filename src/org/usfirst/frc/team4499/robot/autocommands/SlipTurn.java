package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.OI;

import org.usfirst.frc.team4499.robot.RobotMap;
//import org.usfirt.frc.team4499.robot.tools.PID;


import org.usfirst.frc.team4499.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4499.robot.tools.PID;


/**
 *
*/ 
public class SlipTurn extends Command {
	private double speed = 0;
	private double time;
	private double desiredAngle;
	private double kp = 0.00035;
	private double ki = 0.0002400;
	private double kd = 0;
	private PID orientation; 
	private double startAngle;
	private int zeroed;
    private float turnPower;
    private double forwardSpeed;
	private boolean across = false;
	private int run;
	

    public SlipTurn( double angle, float Power,double fSpeed) {
        desiredAngle = angle;
        forwardSpeed = fSpeed;
        orientation = new PID(kp,ki,kd);
    	orientation.setContinuous(true);
    	orientation.setMaxOutput(Power);
    	orientation.setMinOutput(-Power);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle= RobotMap.navx.getAngle();
    	run =0;
       orientation.setSetPoint(startAngle + desiredAngle);
      
    }

    // Csalled repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	orientation.updatePID(RobotMap.navx.getAngle());
    	RobotMap.leftDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,-forwardSpeed+orientation.getResult());//Reversed For Comp Bot
    	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,-forwardSpeed-orientation.getResult());
   }


    

    // Make this return true whcccen this Command no longer needs to run execute()
    protected boolean isFinished() {
    
    	if(Math.abs(RobotMap.navx.getAngle() -(startAngle + this.desiredAngle)) <=2) {
   
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    
    
    	RobotMap.leftDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	orientation.setSetPoint(RobotMap.navx.getAngle());
    	

  	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

