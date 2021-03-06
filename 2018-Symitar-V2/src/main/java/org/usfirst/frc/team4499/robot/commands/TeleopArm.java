package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4499.robot.commands.MPArm;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;

/**
 *
 */
public class TeleopArm extends Command {
    private MPArm mpArm;
    public TeleopArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	mpArm =new MPArm(60, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("position", mpArm.currentAngle);
    	if(!mpArm.isRunning()) {
    		// if(OI.armReverseIntake.get()) {
    		if(OI.armForwardIntake.get()) {
    			mpArm= new MPArm(0, 0);
    			mpArm.start();
    		} 		
    	  //if(OI.armForwardIntake.get()) {
    		if(OI.armReverseIntake.get()) {
    			mpArm= new MPArm(180, 0);
    			mpArm.start();
    		}
    	
    		 //if(OI.armReverseShoot.get()) {
    		if(OI.armForwardShoot.get()) {
    			mpArm= new MPArm(60, 10);
    			mpArm.start();
    		}
    	 //if(OI.armForwardShoot.get()) {
    		if(OI.armReverseShoot.get()) {
    			mpArm= new MPArm(120, 10);
    			mpArm.start();
    		}
    	
    		if(Math.abs(OI.joyStickTwo.getRawAxis(5))>0.15) {
        		RobotMap.brake.set(RobotMap.releaseBrake);
        		//on comp RobotMap.armMaster.set(ControlMode.PercentOutput, 0.30*OI.joyStickTwo.getRawAxis(5));
        		RobotMap.armMaster.set(ControlMode.PercentOutput, -0.35*OI.joyStickTwo.getRawAxis(5));

        	    mpArm.currentAngle=-(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180;
        	}
    		else{
    			RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
        		RobotMap.brake.set(RobotMap.setBrake);

    		}
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
