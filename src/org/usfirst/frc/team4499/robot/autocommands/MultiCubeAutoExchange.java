package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4499.robot.commands.MPArm;
import org.usfirst.frc.team4499.robot.commands.OutTakeCrate;
import org.usfirst.frc.team4499.robot.commands.SetPiston;

/**
 *
 */
public class MultiCubeAutoExchange extends CommandGroup {

    public MultiCubeAutoExchange(char Dir) {
    	addSequential(new CenterChooserAuto(Dir));
    	if(Dir =='R') {
    		//changed so that it should be able to do the exchange instead of multiple in the switch
    		addSequential(new SetPiston(RobotMap.shifters,RobotMap.lowGear));
    		//addSequential(new motionMagicDriveForward(-3,RobotMap.navx.getAngle(),1100,1500));
    		addSequential(new motionMagicDriveForwardHighGear(-20,RobotMap.navx.getAngle(),3050,5000,1,1));
    		//addSequential(new SlipTurn(70,0.75f,-0.6));
    		addSequential(new SetPiston(RobotMap.shifters,RobotMap.lowGear));

    		addSequential(new SlipTurn(50,0.75f,-0.6));
    		addSequential(new SlipTurn(-40,0.75f,-0.6));
    		addSequential(new SetPiston(RobotMap.rightIntakePiston, RobotMap.closeRightIntake));   		
    		addSequential(new SetPiston(RobotMap.leftIntakePiston, RobotMap.closeLeftIntake));
    		addSequential(new MPArm(0,0));
    		addSequential(new SetPiston(RobotMap.rightIntakePiston, RobotMap.openRightIntake));   		
    		addSequential(new SetPiston(RobotMap.leftIntakePiston, RobotMap.openLeftIntake));
    		addSequential(new SwitchAttemptToGrabCrate(2.0));	
    		addSequential(new SetPiston(RobotMap.shifters, RobotMap.lowGear));   		
    		addSequential(new SlipTurn(35,0.75f,-0.1));
    		addSequential(new motionMagicDriveForwardHighGear(-68,RobotMap.navx.getAngle(),3050,5000,1,1));
    		addSequential(new SetPiston(RobotMap.shifters, RobotMap.lowGear));   		
    		addSequential(new SlipTurn(-30,0.75f,-0.1));
    		addSequential(new MPArm(173,0));
    		//addSequential(new OutTakeCrate(1.0,1.0));
    	}
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,;
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
