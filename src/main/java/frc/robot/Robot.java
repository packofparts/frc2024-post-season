// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import POPLib.Controllers.OI;
import POPLib.Swerve.Commands.TeleopSwerveDrive;
import edu.wpi.first.wpilibj.TimedRobot;
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

    private Intake intake;
    private Swerve swerve;
    private Shooter shooter;
    private Climb climb;
    private Wrist wrist;
    private OI oi;

    @Override
    public void robotInit() {
        intake = Intake.getInstance();
        climb = Climb.getInstance();
        oi = OI.getInstance();
        swerve = Swerve.getInstance();
        shooter = Shooter.getInstance();
        wrist = Wrist.getInstance();

        configureBindings();
    }


    private void configureBindings() {
        // Driver
        oi.getDriverButton(Controls.INTAKE).onTrue(transitionState(RobotState.INTAKE));
        oi.getDriverButton(Controls.IDLE).onTrue(transitionState(RobotState.IDLE));
        oi.getDriverButton(Controls.AMP).onTrue(transitionState(RobotState.AMP));
        oi.getDriverButton(Controls.FENDER).onTrue(transitionState(RobotState.FENDER));


        // Operator
        oi.getOperatorButton(Controls.CLIMB_UP).onTrue(climb.autoMoveUp());
        oi.getOperatorButton(Controls.CLIMB_DOWN).onTrue(climb.autoMoveDown());
        oi.getOperatorButton(Controls.IDLE).onTrue(transitionState(RobotState.IDLE));

        // oi.getDriverController().b().onTrue(new WheelRadiusChar(swerve, Constants.Swerve.MODULE_TYPE, Constants.Swerve.DRIVE_BASE_RADIUS));

        swerve.setDefaultCommand(new TeleopSwerveDrive(swerve, oi));
    }


    public Command transitionState(RobotState newState) {
        return new SequentialCommandGroup (
            new InstantCommand(() -> System.out.println("Transitining to state: " + newState.toString())),
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

        // State machine
        if (intake.hasNote()) {
            transitionState(RobotState.INDEX).schedule();
        }

        if (shooter.hasNote()) {
            transitionState(RobotState.IDLE).schedule();;
        }

        if (!shooter.hasNote() && shooter.firingNote()) {
            transitionState(RobotState.IDLE).schedule();
        }
    }

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {
        Swerve.getInstance().updateEncoders();
    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = null;

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }
}
