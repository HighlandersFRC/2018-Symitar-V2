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
    	
    	if(dir=='R'){
    		addSequential(new motionMagicDriveForward(102, RobotMap.navx.getAngle(), 1100, 1500));
    	}
    	else if(dir=='L'){
    		addSequential(new motionMagicDriveForward(3, RobotMap.navx.getAngle(), 1100, 1500));
    		addSequential(new Wait(0.1));
    		addSequential(new navxTurn(37,0.75f));
    		 addSequential(new Wait(0.1));
    		addSequential(new motionMagicDriveForward(113, RobotMap.navx.getAngle(), 1100, 1500));


    	}
        addSequential(new Wait(0.1));
        addSequential(new OutTakeCrate(0.2,0.5));

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
