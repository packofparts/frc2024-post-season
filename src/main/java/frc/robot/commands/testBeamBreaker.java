// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.beamBreakerSubsystem;
// import frc.robot.subsystems.limeLightSubsystem;

public class testBeamBreaker extends Command {
  beamBreakerSubsystem beamSub;
  /** Creates a new TestLimitSwitch. */
  public testBeamBreaker(beamBreakerSubsystem beamSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.beamSub = beamSub;
    addRequirements(beamSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.print(beamSub.getBeamValue());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
