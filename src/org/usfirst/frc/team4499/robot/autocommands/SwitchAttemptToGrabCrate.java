package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchAttemptToGrabCrate extends Command {
	public double findTime;
	public boolean found = false;

    public SwitchAttemptToGrabCrate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shifters.set(RobotMap.lowGear);
    	RobotMap.leftDriveLead.set(ControlMode.PercentOutput, -0.34);
    	RobotMap.rightDriveLead.set(ControlMode.PercentOutput, -0.3);//TODO shouldn't be different on comp
    	RobotMap.intakeLeft.set(ControlMode.PercentOutput, -0.3);
    	RobotMap.intakeRight.set(ControlMode.PercentOutput, -0.3);


    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.analog.getValue()<240&&!found) {
    		findTime = Timer.getFPGATimestamp();
    		found = true;
    		RobotMap.leftIntakePiston.set(RobotMap.closeLeftIntake);
        	RobotMap.rightIntakePiston.set(RobotMap.closeLeftIntake);
    	}
    	 
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(found&&Math.abs(Timer.getFPGATimestamp()- findTime)>0.5) {
    		return true;
    	}
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    	RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
    	RobotMap.intakeLeft.set(ControlMode.PercentOutput, 0);
    	RobotMap.intakeRight.set(ControlMode.PercentOutput, 0);
    	RobotMap.leftIntakePiston.set(RobotMap.closeLeftIntake);
    	RobotMap.rightIntakePiston.set(RobotMap.closeLeftIntake);

    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}