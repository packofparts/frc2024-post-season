// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Input extends SubsystemBase {
  /** Creates a new Input. */

  /*
   * Hardware:
   * 2x Joysticks
   * 1x XboxController
   */

  public static final XboxController mXbox = new XboxController(1);
  public static final Joystick rotJoystick = new Joystick(0);
  public static final Joystick transJoystick = new Joystick(2);
  public Input() {
    throw new IllegalStateException("Input Class"); // aka dont create an instance, use this statically
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // ignore dis lol.
  }

  // add methods in here as you need.

  /*
   * method structure:
   * public returnTypeHere methodName() {
   *  return controllerVariableName.someGetMethod();
   * }
   */
  public static boolean getA(){
    return mXbox.getAButtonPressed();
  }
  public static boolean getB(){
    return mXbox.getBButtonPressed();
  }
  public static boolean getX(){
    return mXbox.getXButtonPressed();
  }
}
