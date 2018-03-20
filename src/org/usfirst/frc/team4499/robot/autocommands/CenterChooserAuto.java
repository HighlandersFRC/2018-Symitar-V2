package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.Robot;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.OutTakeCrate;
import org.usfirst.frc.team4499.robot.commands.SetPiston;
import org.usfirst.frc.team4499.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterChooserAuto extends CommandGroup {

    public CenterChooserAuto(char dir) {
    	//if the switch is on the right, drives forward and drops, if it is on the left, drives forward slightly turns to line up, and then 
    	//drives forward to meet the switch
    	if(dir=='R'){
    	//	addSequential(new motionMagicDriveForward(101, RobotMap.navx.getAngle(), 1100, 1500,1,1));
    		addSequential(new motionMagicDriveForwardHighGear(105,RobotMap.navx.getAngle(), 3050, 5000, 1,1));
    	    addSequential(new Wait(0.35));
    	    addSequential(new OutTakeCrate(0.5,0.5));
    	   
    	    addSequential(new SetPiston(RobotMap.leftIntakePiston, RobotMap.openLeftIntake));
    	    addSequential(new SetPiston(RobotMap.rightIntakePiston, RobotMap.openRightIntake));
    	   
    	}
    	else if(dir=='L'){
    		
    		
    		addSequential(new SetPiston(RobotMap.shifters, RobotMap.lowGear));
    		addSequential(new SlipTurn(-41,0.75f,0.4));//negative for the comp bot
    		addSequential(new Wait(0.1));
    		addSequential(new motionMagicDriveForwardHighGear(100,RobotMap.navx.getAngle(), 3050, 5000, 1,1));
    		addSequential(new Wait(0.1));
    		
    		addSequential(new SlipTurn(43,0.75f, 0.2));//positive for the comp bot
    		addSequential(new Wait(0.1));
    		//addSequential(new motionMagicDriveForwardHighGear(35, RobotMap.navx.getAngle(), 1100, 1500,1,1));
            addSequential(new OutTakeCrate(0.4,0.4)); 
            
    	}
       

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
