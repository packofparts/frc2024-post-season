// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import POPLib.Controllers.OI.Joysticks;
import POPLib.Controllers.OI.OI;
import POPLib.Controllers.OI.XboxOI;
import POPLib.Swerve.Commands.TeleopSwerveDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Controls;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Wrist;
import frc.robot.util.StateManager.RobotState;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private SendableChooser<Command> pathSelector;

    private Intake intake;
    private Swerve swerve;
    private Shooter shooter;
    private Climb climb;
    private Wrist wrist;
    private Joysticks oi;
    private RobotState currState;

    @Override
    public void robotInit() {
        intake = Intake.getInstance();
        climb = Climb.getInstance();
        oi = new Joysticks();
        swerve = Swerve.getInstance();
        shooter = Shooter.getInstance();
        wrist = Wrist.getInstance();
        currState = RobotState.IDLE;

        NamedCommands.registerCommand("intake_piece", transitionState(RobotState.INTAKE));
        NamedCommands.registerCommand("launch_piece", transitionState(RobotState.FENDER));

        
        // Configure AutoBuilder last
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
                        new ReplanningConfig(true, true)),
                () -> DriverStation.getAlliance().get() == DriverStation.Alliance.Red,
                swerve
        );
        
        // pathSelector.addOption("3 Note Center Start", new PathPlannerAuto("3note_center_start"));
        // pathSelector.addOption("Line", AutoBuilder.followPath(PathPlannerPath.fromPathFile("Line")));
        // pathSelector.addOption("None", new InstantCommand(() -> System.out.println(
        //         "EmPTy"
        // )));

        // PathPlannerPath.fromPathFile("Example Path");
        pathSelector = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto chooser", pathSelector);

        configureBindings();
    }


    private void configureBindings() {
        // Driver
        oi.getDriverButton(Controls.INTAKE).onTrue(transitionState(RobotState.INTAKE));
        oi.getDriverButton(Controls.SUCK_IN).onTrue(transitionState(RobotState.SUCK_IN).andThen(transitionState(RobotState.IDLE)));
        oi.getDriverButton(Controls.IDLE).onTrue(transitionState(RobotState.IDLE));
        oi.getDriverButton(Controls.AMP).onTrue(transitionState(RobotState.AMP));
        oi.getDriverButton(Controls.FENDER).onTrue(transitionState(RobotState.FENDER));


        // Operator
        oi.getOperatorButton(Controls.CLIMB_UP).onTrue(climb.moveUp()).onFalse(climb.stop());
        oi.getOperatorButton(Controls.CLIMB_DOWN).onTrue(climb.moveDown()).onFalse(climb.stop());

        oi.getOperatorButton(Controls.IDLE).onTrue(transitionState(RobotState.IDLE));

        // oi.getDriverController().b().onTrue(new WheelRadiusChar(swerve, Constants.Swerve.MODULE_TYPE, Constants.Swerve.DRIVE_BASE_RADIUS));

        swerve.setDefaultCommand(new TeleopSwerveDrive(swerve, oi));
    }


    public Command transitionState(RobotState newState) {
        return new SequentialCommandGroup (
            new InstantCommand(() -> {
                currState = newState;
                System.out.println("Transitining to state: " + newState.toString());
            }),
            wrist.changeState(newState),
            new ParallelCommandGroup(
                intake.changeState(newState),
                shooter.changeState(newState)
            )
        );
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

        SmartDashboard.putString("Current State", currState.toString());

        // State machine
        if (intake.hasNote() && currState == RobotState.INTAKE) {
            transitionState(RobotState.INDEX).schedule();
        }

        if (currState == RobotState.INDEX && shooter.hasNote()) {
            transitionState(RobotState.SUCK_IN).andThen(transitionState(RobotState.IDLE)).schedule();
        }

        if (!shooter.hasNote() && (currState == RobotState.FENDER || currState == RobotState.AMP)) {
            transitionState(RobotState.IDLE).schedule();
        }

        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }

        System.out.println("TransJoystick:" + (oi.getDriveTrainTranslationX() + oi.getDriveTrainTranslationY()));
    }

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {
        Swerve.getInstance().updateEncoders();
    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = getAutonomousCommand();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        transitionState(RobotState.IDLE).schedule();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    // Auto stuff
    public Command getAutonomousCommand() {
        return pathSelector.getSelected();
    }
}