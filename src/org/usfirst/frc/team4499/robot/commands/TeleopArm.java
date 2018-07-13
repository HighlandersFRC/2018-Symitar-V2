package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4499.robot.commands.MPArm;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotConfig;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.FastTeleopArm;

/**
 *
 */
public class TeleopArm extends Command {
    private MPArm mpArm;
    private FastTeleopArm fastArm;
    public TeleopArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	mpArm =new MPArm(60, 0);
    	fastArm = new FastTeleopArm(60);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(-(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180);
    	if(RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
	    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    	}

    	if(RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
	    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, RobotConfig.timeOut);
    	}
    	if(!fastArm.isRunning()) {
    		 if(OI.armReverseIntake.get()) {
    		//if(OI.armForwardIntake.get()) {
    			 fastArm = new FastTeleopArm(7);
    			 fastArm.start();
    			//mpArm= new MPArm(0, 0);
    			//mpArm.start();
    		} 		
    		if(OI.armForwardIntake.get()) {
    		//if(OI.armReverseIntake.get()) {
    			fastArm = new FastTeleopArm(180);
   			 fastArm.start();
    			//mpArm= new MPArm(180, 0);
    			//mpArm.start();
    		}
    	
    		 if(OI.armReverseShoot.get()) {
    		//if(OI.armForwardShoot.get()) {
    			 fastArm = new FastTeleopArm(65);
    			 fastArm.start();
    			//mpArm= new MPArm(60, 15);
    			//mpArm.start();
    		}
    	 if(OI.armForwardShoot.get()) {
    		//if(OI.armReverseShoot.get()) {
    			fastArm=new FastTeleopArm(125);
    			fastArm.start();
    		 	//mpArm= new MPArm(120, 15);
    			//mpArm.start();
    		}
    	
    		if(Math.abs(OI.joyStickTwo.getRawAxis(5))>0.15) {
        		RobotMap.brake.set(RobotMap.releaseBrake);
        		RobotMap.armMaster.set(ControlMode.PercentOutput, 0.65*OI.joyStickTwo.getRawAxis(5));
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
