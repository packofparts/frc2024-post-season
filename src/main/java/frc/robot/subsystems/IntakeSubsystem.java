// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  private final CANSparkMax mInnerMotor;
  private final CANSparkMax mOuterMoter;
  public final DigitalInput mBeamBreak;
  /* 
  Hardware:
  2x Neo Motors (brushless) <- ask Kevin for more info if needed
  1x beambreak sensor

  Hints: https://letmegooglethat.com/?q=wpilib+digital+sensor+api
         https://letmegooglethat.com/?q=rev+robotics+neo+api+documentation+java
  */
  
  // TODO: declare variables

  public IntakeSubsystem() {
    mInnerMotor = new CANSparkMax(IntakeConstants.INNER_INTAKE_SPARK_ID, MotorType.kBrushless);
    mOuterMoter = new CANSparkMax(IntakeConstants.OUTER_INTAKE_SPARK_ID, MotorType.kBrushless);
    mBeamBreak = new DigitalInput(IntakeConstants.INTAKE_BEAMBREAK_ID);
    mInnerMotor.restoreFactoryDefaults();
    mOuterMoter.restoreFactoryDefaults();
  }

  @Override
  public void periodic() {
    // This method will be called once per sche duler run
    // LEAVE THIS BLANK
  }

  public boolean pieceInIntake() {
    return mBeamBreak.get(); // CHECK
  }
  
  public void intakeMotorsAtSpeed(double percentOutput){
    mInnerMotor.set(percentOutput);
    mOuterMoter.set(percentOutput);
  }
  public void innerMotorAtSpeed(double percentOutput){
    mInnerMotor.set(percentOutput);
  }

  public void outerMotorAtSpeed(double percentOutput){
    mOuterMoter.set(percentOutput);
  }

  public void noteToLauncher() {
    mInnerMotor.set(IntakeConstants.ACTIVE_INTAKE_SPEED);
  }
}
