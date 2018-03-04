package org.usfirst.frc.team4499.robot.autocommands;

import java.text.FieldPosition;

import javax.lang.model.element.Element;
import javax.naming.InsufficientResourcesException;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotConfig;
import org.usfirst.frc.team4499.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPicker extends Command {
	private int autoAttempts = 0;
	DoNothing doNothing= new DoNothing();
	BasicAuto basicAuto = new BasicAuto();
    DriveForward driveForward = new DriveForward();
    Wait wait = new Wait(0.1);
    NinteyDegreeTurnAuto ninteyDegreeTurnAuto;
    CenterChooserAuto centerChooser;

    public AutoPicker() {
    	//This class is used to take values from the fms, and from the Digikey switch box, and to  chose autos based of that
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotConfig.fieldPositions==null) {
    		System.out.println("NUll");
    	}
    	
    	for(int i = 0; i<RobotConfig.fieldPositions.length()-1;i++) {
    		if(!(RobotConfig.fieldPositions.charAt(i)=='C'||RobotConfig.fieldPositions.charAt(i)=='L'||RobotConfig.fieldPositions.charAt(i)=='R')) {
    			driveForward.start();
    			return;
    		}
    	}
    
    	if(OI.dialOne.get()) {
    		 driveForward.start();
    		 return;
    	}
    	else if(OI.dialTwo.get()) {
    		if(RobotConfig.fieldPositions.charAt(0)== RobotConfig.robotStartPosition) {
    			basicAuto.start();
        		return;  		
    		}
    		else {
    			driveForward.start();
        		return;  		
    		}
    	}
    	else if(OI.dialThree.get()){
    		if(RobotConfig.fieldPositions.charAt(0)=='L'&&RobotConfig.robotStartPosition=='L'){
    			ninteyDegreeTurnAuto = new NinteyDegreeTurnAuto('L');
    			ninteyDegreeTurnAuto.start();
    			return;
    		}
    		else if(RobotConfig.fieldPositions.charAt(0)=='R'&&RobotConfig.robotStartPosition=='R'){
    			ninteyDegreeTurnAuto = new NinteyDegreeTurnAuto('R');
    			ninteyDegreeTurnAuto.start();
    			return;
    		}
    		else{    			
    			driveForward.start();
    			return;
    		}
    	}
    	else if(OI.dialFour.get()){
    		
    			if(RobotConfig.fieldPositions.charAt(0)=='L'){
    				centerChooser = new CenterChooserAuto('L');
                 	centerChooser.start();
            		return;  		
    			}
    			else if(RobotConfig.fieldPositions.charAt(0)=='R'){
    				centerChooser = new CenterChooserAuto('R');
    				centerChooser.start();
    	    		return;  		
    			}
    			else{
    				driveForward.start();
    			}
    		
    	}
    	else {
    		driveForward.start();
    		return;  		
    	}
    /*	else if(fieldPos.isEmpty()) {
    		doNothing.start();	
    	return;
    	}
    	for(int i = 0; i<fieldPos.length()-1;i++) {
    		if(fieldPos.charAt(i)!='R'||fieldPos.charAt(i)!='L') {
    			doNothing.start();
    			return;
    		}
    	}
    	if(fieldPos.charAt(0)==RobotConfig.robotStartPosition) {
    		basicAuto.start();
    		return;
    	}
    	else {
    		driveForward.start();
    	}
    	
    	fieldPos = RobotConfig.fieldPositions;
    	*/
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
