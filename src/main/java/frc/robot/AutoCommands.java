package frc.robot;

import java.util.function.Supplier;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Swerve;
import frc.robot.util.StateManager.RobotState;

public class AutoCommands {
    private SendableChooser<Command> chooser;

    public AutoCommands(Swerve swerve, Command intakeCommand, Command fenderShot) {
        NamedCommands.registerCommand("Nothing", new InstantCommand());

        NamedCommands.registerCommand("intake_piece", intakeCommand);

        NamedCommands.registerCommand("launch_piece", fenderShot);

        AutoBuilder.configureHolonomic(
            swerve::getOdomPose,
            swerve::setOdomPose,
            swerve::getChassisSpeeds,
            swerve::driveChassis,
            new HolonomicPathFollowerConfig(
                Constants.Swerve.AUTO_TRANSLATION,
                Constants.Swerve.AUTO_ROTATION,
                Constants.Swerve.MODULE_TYPE.maxSpeed,
                Constants.Swerve.DRIVE_BASE_RADIUS,
                new ReplanningConfig(false, false)
            ),
            () -> DriverStation.getAlliance().get() == DriverStation.Alliance.Red,
            swerve
        );

        chooser = new SendableChooser<>();

        chooser.addOption("nothing", new InstantCommand(() -> { }));

        chooser.addOption("3 note", makeAuto("line"));

        SmartDashboard.putData("Auto Selecter", chooser);
    }

    private Command makeAuto(String path) {
        return new PathPlannerAuto(path);
    }

    public Command getAuto() {
        return chooser.getSelected();
    }
}