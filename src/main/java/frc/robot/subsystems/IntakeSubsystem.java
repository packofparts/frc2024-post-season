// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  /* 
  Hardware:
  2x Neo Motors (brushless) <- ask Kevin for more info if needed
  1x beambreak sensor

  Hints: https://letmegooglethat.com/?q=wpilib+digital+sensor+api
         https://letmegooglethat.com/?q=rev+robotics+neo+api+documentation+java
  */
  
  // TODO: declare variables

  public IntakeSubsystem() {
    // TODO: Initialize variables 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // LEAVE THIS BLANK
  }

  public boolean pieceInIntake() {
    return false; // TODO
  }
  //outer motor run function
  public void runOuterMotor(double speed) {
    //TODO
  }

  //inner motor run function

  public void runInnerMotor(double speed) {
    //TODO
  }
  //inner motor get speed function
  public double getInnerSpeed() {
    return 0.0; // TODO
  }

  //outer motor get speed function
  public double getOuterSpeed() {
    return 0.0; // TODO
  }
}
