// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import POPLib.Controllers.OI;
import POPLib.Swerve.Commands.TeleopSwerveDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final Intake intake;
    private final Swerve swerve;
    private final Shooter shooter;
    private final Climb climb;
    private final OI oi;

    public RobotContainer() {
        intake = Intake.getInstance();
        climb = Climb.getInstance();
        oi = OI.getInstance();
        swerve = Swerve.getInstance();
        shooter = Shooter.getInstance();

        configureBindings();
    }

    private void configureBindings() {
        oi.getDriverController().a().onTrue(intake.intakePiece().andThen(shooter.feedInNote()).andThen(intake.stopIntake()));

        oi.getDriverController().x().onTrue(climb.autoMoveUp());
        oi.getDriverController().y().onTrue(climb.autoMoveDown());

        swerve.setDefaultCommand(new TeleopSwerveDrive(swerve, oi));
    }

    public Command getAutonomousCommand() { return null; }
}
