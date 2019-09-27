package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotConfig;
import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopDriving extends Command {
	public boolean highGear = false;
	private double leftPower = 0;
	private double rightPower= 0;
    public TeleopDriving() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftJoystick = OI.joyStickOne.getRawAxis(1);
    	double rightJoystick = OI.joyStickOne.getRawAxis(5);
    	

		
    	if(highGear) {
    		if(Math.abs(OI.joyStickOne.getRawAxis(1))>0.15) {
       			leftPower = Math.pow(Math.abs(leftJoystick),2)*Math.abs(leftJoystick)/leftJoystick;
       		}
       		else {
    			RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    			leftPower = 0;
    		}
       		if(Math.abs(OI.joyStickOne.getRawAxis(5))>0.15) {
    			rightPower = Math.pow(Math.abs(rightJoystick),2)*Math.abs(rightJoystick)/rightJoystick;
    		}
    		else {
    			rightPower = 0;
    			RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0); 
    		}
       		if(Math.abs(leftPower)>Math.abs(rightPower)&&Math.abs(leftPower-rightPower)<=1) {
       			double dif = leftPower - rightPower;
       			rightPower = leftPower-((dif)*Math.abs(dif));
       		}
       		else if(Math.abs(rightPower)>Math.abs(leftPower)&&Math.abs(rightPower-leftPower)<=1) {
       			double dif = rightPower - leftPower;
       			leftPower = rightPower-((dif)*Math.abs(dif));
       		}
    		RobotMap.rightDriveLead.set(ControlMode.PercentOutput, rightPower);
    		RobotMap.leftDriveLead.set(ControlMode.PercentOutput, leftPower);
    		
    	}
    	else {
    		if(Math.abs(OI.joyStickOne.getRawAxis(1))>0.15){
    			RobotMap.leftDriveLead.set(ControlMode.PercentOutput, OI.joyStickOne.getRawAxis(1));
    		}
    		else {
    			RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);

    		}
    		if(Math.abs(OI.joyStickOne.getRawAxis(5))>0.15){
    			RobotMap.rightDriveLead.set(ControlMode.PercentOutput, OI.joyStickOne.getRawAxis(5));
    		}
    		else {
    			RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);

    		}	
    	}
    	

    
	


    		
    	if(OI.shiftUp.get()) {
    		highGear = true;
    		RobotMap.shifters.set(RobotMap.highGear);
    	}
    	else if(OI.shiftDown.get()) {
    		highGear = false;
    		RobotMap.shifters.set(RobotMap.lowGear);
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
		RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0); 


    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
