package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPiston extends Command {
	DoubleSolenoid solenoid1;
	DoubleSolenoid.Value value1;

    public SetPiston(DoubleSolenoid solenoid, DoubleSolenoid.Value value) {
    	value1 = value;
    	solenoid1= solenoid;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	solenoid1.set(value1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
