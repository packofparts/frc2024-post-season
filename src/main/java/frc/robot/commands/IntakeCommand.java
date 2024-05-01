// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
  /** Creates a new IntakeCommand. */
  // Required subsystems: Intake

  //TODO: Declare Variables
  public IntakeCommand(IntakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    // TODO: Initizalize Variables
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Not really needed...
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // TODO: Read input from controller using Input class
    // TODO: Check if there is piece already in the intake
    // TODO: Use the methods in the IntakeSubsystem to run the intakes if needed
    // TODO: Make sure to auto stop when needed (eg. you have detected a peice in the Intake)
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
