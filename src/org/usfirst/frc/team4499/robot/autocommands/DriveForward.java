package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForward extends CommandGroup {

    public DriveForward() {
    	//basic drive forward to make sure our team gets the rp
    	//addSequential(new navxTurn(38,0.75f));
    	//addSequential(new motionMagicDriveForward(101,0, 1100, 1500));
		//negative for the comp bot

    	addSequential(new motionMagicDriveForwardHighGear(105,RobotMap.navx.getAngle(), 3050, 5000, 1,1));
    	//addSequential(new SwitchAttemptToGrabCrate());
    	System.out.println("hi");
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
