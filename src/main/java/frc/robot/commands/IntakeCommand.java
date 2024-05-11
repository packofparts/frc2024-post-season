// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Input;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
  private IntakeSubsystem mIntake;

  /** Creates a new IntakeCommand. */
  // Required subsystems: Intake

  public IntakeCommand(IntakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    mIntake = intake;
  }


  // Called when the command is initially scheduled.in
  @Override
  public void initialize() {
    // Not really needed...
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Input.getIntake()) {
      if (mIntake.pieceInIntake()) {
        mIntake.stopMotors();
      } else {
        mIntake.intakeMotorsAtSpeed(0.5);
        //runs both the motors.
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // No touch.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    // no touch.
  }
}
