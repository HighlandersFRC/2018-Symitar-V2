package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.MPArm;
import org.usfirst.frc.team4499.robot.commands.OutTakeCrate;
import org.usfirst.frc.team4499.robot.commands.SetPiston;
import org.usfirst.frc.team4499.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MultiCubeAutoSwitch extends CommandGroup {

    public MultiCubeAutoSwitch(char Dir) {
    	addSequential(new CenterChooserAuto(Dir));
    	if(Dir =='R') {
    		//changed so that it should be able to do the exchange instead of multiple in the switch
    		addSequential(new SetPiston(RobotMap.shifters,RobotMap.lowGear));
    		//addSequential(new motionMagicDriveForward(-3,RobotMap.navx.getAngle(),1100,1500));
    		addSequential(new motionMagicDriveForwardHighGear(-20,RobotMap.navx.getAngle(),3050,5000,1,1));
    		//addSequential(new SlipTurn(70,0.75f,-0.6));
    		addSequential(new SetPiston(RobotMap.shifters,RobotMap.lowGear));
    		addSequential(new SlipTurn(65,0.75f,-0.7));
    		addSequential(new SlipTurn(-60,0.75f,-0.7));
    		//addSequential(new SlipTurn(65,0.75f,-0.8));
    		//addSequential(new SlipTurn(-60,0.75f,-0.8));
    		addSequential(new MPArm(0,0));
    		//on comp addSequential(new MPArm(175,0));
    		addSequential(new SwitchAttemptToGrabCrate(0.7));	
    		addSequential(new SlipTurn(50,0.75f,-0.6));
    		addParallel(new MPArm(60,10));
    		//addParallel(new MPArm(120,10));
    		addSequential(new motionMagicDriveForwardHighGear(37,RobotMap.navx.getAngle(),3050,5000,1,1));
    		addSequential(new SlipTurn(-40,0.75f,0.6));
    		addSequential(new motionMagicDriveForwardHighGear(10,RobotMap.navx.getAngle(),3050,5000,1,1));
            addSequential(new OutTakeCrate(0.4,0.4));
            addSequential(new SlipTurn(50,0.75f,-0.5));
            addSequential(new Wait(0.1));
            //addSequential(new SetPiston(RobotMap.shifters,RobotMap.lowGear));
            addSequential(new SlipTurn(-40,0.25f,-0.2));
    	
    	}
    	else if(Dir =='L') {
    		addParallel(new SetPiston(RobotMap.shifters,RobotMap.lowGear));
    		addSequential(new motionMagicDriveForwardHighGear(-44,RobotMap.navx.getAngle(),3050,5000,1,1));
    		addParallel(new SetPiston(RobotMap.shifters,RobotMap.lowGear));
    		addSequential(new SlipTurn(-64,0.75f,-0.4));
    		//on comp addSequential(new motionMagicDriveForwardHighGear(-44,RobotMap.navx.getAngle(),3050,5000,1,1));
    		addSequential(new motionMagicDriveForwardHighGear(-49,RobotMap.navx.getAngle(),3050,5000,1,1));

    		addParallel(new SetPiston(RobotMap.shifters,RobotMap.lowGear));
    		addSequential(new SlipTurn(66,0.75f,-0.4));
    		
    		//ON competition addSequential(new MPArm(175,0));
    		addSequential(new MPArm(0,0));
    		addSequential(new SwitchAttemptToGrabCrate(0.6));
    		addParallel(new SetPiston(RobotMap.shifters,RobotMap.highGear));
    		addParallel(new MPArm(50,0));
    		//On Comp addParallel(new MPArm(130,0));
    		addSequential(new SlipTurn(-42,0.5f,-0.3));
    		addSequential(new Wait(0.1));
    		addSequential(new SlipTurn(40.0,0.75f,0.9));
    		addSequential(new Wait(0.1));
            addSequential(new OutTakeCrate(0.45,0.45));
    	}
    	
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
