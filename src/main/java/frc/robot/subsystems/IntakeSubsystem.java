// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  private final CANSparkMax IntakeMotorInner;
  private final CANSparkMax IntakeMotorOuter;
  private final DigitalInput beamBreakInput;
  /* 
  Hardware:
  2x Neo Motors (brushless) <- ask Kevin for more info if needed
  1x beambreak sensor

  Hints: https://letmegooglethat.com/?q=wpilib+digital+sensor+api
         https://letmegooglethat.com/?q=rev+robotics+neo+api+documentation+java
  */
  
  // TODO: declare variables

  public IntakeSubsystem() {
    IntakeMotorInner = new CANSparkMax(IntakeConstants.INNER_INTAKE_SPARK_ID, MotorType.kBrushless);
    IntakeMotorOuter = new CANSparkMax(IntakeConstants.OUTER_INTAKE_SPARK_ID, MotorType.kBrushless);
    beamBreakInput = new DigitalInput(IntakeConstants.INTAKE_BEAMBREAK_ID);
    IntakeMotorInner.restoreFactoryDefaults();
    IntakeMotorOuter.restoreFactoryDefaults();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // LEAVE THIS BLANK
  }

  public boolean pieceInIntake() {
    return beamBreakInput.get(); // CHECK
  }
  
  public void intakeMotorsAtSpeed(double percentOutput){
    IntakeMotorInner.set(percentOutput);
    IntakeMotorOuter.set(percentOutput);
  }
  public void innerMotorAtSpeed(double percentOutput){
    IntakeMotorInner.set(percentOutput);
  }

  public void outerMotorAtSpeed(double percentOutput){
    IntakeMotorOuter.set(percentOutput);
  }

  public void stopMotors() {
    IntakeMotorInner.set(0.0);
    IntakeMotorOuter.set(0.0);
  }
  public void noteToLauncher() {
    IntakeMotorInner.set(IntakeConstants.ACTIVE_INTAKE_SPEED);
  }
}
