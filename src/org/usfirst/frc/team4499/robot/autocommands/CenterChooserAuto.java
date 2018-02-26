package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.Robot;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.OutTakeCrate;
import org.usfirst.frc.team4499.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterChooserAuto extends CommandGroup {

    public CenterChooserAuto(char dir) {
    	addSequential(new motionMagicDriveForward(5, RobotMap.navx.getAngle(), 1100, 1500));
    	if(dir=='R'){
    		addSequential(new navxTurn(-20.0 - Robot.angleDif,0.75f));
    	}
    	else if(dir=='L'){
            addSequential(new navxTurn(20.0 - Robot.angleDif,0.75f));
    	}
        addSequential(new Wait(0.1));
    	addSequential(new motionMagicDriveForward(103, RobotMap.navx.getAngle(), 1100, 1500));
        addSequential(new OutTakeCrate());

    	//addSequential(new motionMagicDriveForward(40, RobotMap.navx.getAngle(), 1100, 1500));
      //  addSequential(new OutTakeCrate());
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
