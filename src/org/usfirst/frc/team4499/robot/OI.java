/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick joyStickOne = new Joystick(0);
	public static Joystick joyStickTwo = new Joystick(1);
	public static JoystickButton shiftDown = new JoystickButton(joyStickOne,5);
	public static JoystickButton shiftUp = new JoystickButton(joyStickOne,6);
	
	public static JoystickButton armForwardIntake = new JoystickButton(joyStickTwo,3);
	public static JoystickButton armReverseIntake = new JoystickButton(joyStickTwo,2);
	public static JoystickButton armForwardShoot = new JoystickButton(joyStickTwo, 4);
	public static JoystickButton armReverseShoot = new JoystickButton(joyStickTwo, 1);
	
	public static JoystickButton hardOuttake = new JoystickButton(joyStickTwo, 6);
	public static JoystickButton softOuttake = new JoystickButton(joyStickTwo, 5);
	
	public static JoystickButton intake = new JoystickButton(joyStickTwo,9);
	
	public static Joystick dial = new Joystick(2);
	public static JoystickButton dialOne = new JoystickButton(dial,1);
	public static JoystickButton dialTwo = new JoystickButton(dial,2);
	public static JoystickButton dialThree = new JoystickButton(dial,3);
	public static JoystickButton dialFour = new JoystickButton(dial,4);
	public static JoystickButton dialFive = new JoystickButton(dial,5);
	public static JoystickButton switchOne = new JoystickButton(dial, 6);
	public static JoystickButton switchTwo = new JoystickButton(dial, 7);
	public static JoystickButton switchThree = new JoystickButton(dial, 8);
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
