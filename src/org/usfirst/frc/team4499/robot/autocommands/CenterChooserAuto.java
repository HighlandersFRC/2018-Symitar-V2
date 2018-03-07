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
    	//if the switch is on the right, drives forward and drops, if it is on the left, drives forward slightly turns to line up, and then 
    	//drives forward to meet the switch
    	if(dir=='R'){
    		addSequential(new motionMagicDriveForward(101, RobotMap.navx.getAngle(), 1100, 1500,1,1));
    	    addSequential(new OutTakeCrate(0.4,0.4));
    	}
    	else if(dir=='L'){
    		addSequential(new motionMagicDriveForward(3, RobotMap.navx.getAngle(), 1100, 1500,1,1));
    		if(RobotMap.navx.getAngle()==0) {
    			
    		}
    		else {
    		addSequential(new navxTurn(38,0.75f));
    		}
    		addSequential(new Wait(0.1));
    		addSequential(new motionMagicDriveForward(119, RobotMap.navx.getAngle(), 1100, 1500,1,1));
    		if(RobotMap.navx.getAngle()==0) {
    			
    		}
    		else {
    		addSequential(new navxTurn(-38,0.75f));
    		}
    		addSequential(new Wait(0.1));
    		addSequential(new motionMagicDriveForward(9, RobotMap.navx.getAngle(), 1100, 1500,1,1));
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
