package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.CANifier;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLEDColor extends Command {
	private double red;
	private double green;
	private double blue;

    public SetLEDColor(double R,double G, double B) {
    	red=R;
    	green = G;
    	blue = B;
    	RobotMap.canifier.setLEDOutput(green,CANifier.LEDChannel.LEDChannelA);
		RobotMap.canifier.setLEDOutput(blue,CANifier.LEDChannel.LEDChannelB);
		RobotMap.canifier.setLEDOutput(red,CANifier.LEDChannel.LEDChannelC);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public void changeLedColor(double R,double G,double B) {
    	red=R;
    	green = G;
    	blue = B;
    	RobotMap.canifier.setLEDOutput(green,CANifier.LEDChannel.LEDChannelA);
		RobotMap.canifier.setLEDOutput(blue,CANifier.LEDChannel.LEDChannelB);
		RobotMap.canifier.setLEDOutput(red,CANifier.LEDChannel.LEDChannelC);
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
    }
}
