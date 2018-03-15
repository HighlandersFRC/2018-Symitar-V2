package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4499.robot.autocommands.motionMagicDriveForward;
import org.usfirst.frc.team4499.robot.commands.OutTakeCrate;
import org.usfirst.frc.team4499.robot.commands.SetPiston;
import org.usfirst.frc.team4499.robot.commands.Wait;

/**
 *
 */
public class BasicAuto extends CommandGroup {

    public BasicAuto() {
    //drive forward 102 inches and drop a crate
	//addSequential(new motionMagicDriveForward(105, RobotMap.navx.getAngle(), 1100, 1500,1,1));
	addSequential(new motionMagicDriveForwardHighGear(107,RobotMap.navx.getAngle(), 3050, 5000, 1,1));
    addSequential(new Wait(0.25));
    addSequential(new OutTakeCrate(0.3,0.3));
    addSequential(new Wait(1));
    addSequential(new SetPiston(RobotMap.leftIntakePiston, RobotMap.openLeftIntake));
    addSequential(new SetPiston(RobotMap.rightIntakePiston, RobotMap.openRightIntake));

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
