// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import POPLib.Sensors.BeamBreak.BeamBreak;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.StateManager.RobotState;

public class Intake extends SubsystemBase {
    private final CANSparkMax innerMotor;
    private final CANSparkMax outerMotor;
    private final BeamBreak beamBreak;

    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }

    private Intake() {
        innerMotor = Constants.Intake.INNER_MOTOR.createSparkMax();
        outerMotor = Constants.Intake.OUTER_MOTOR.createSparkMax();
        beamBreak = Constants.Intake.BEAM_BREAK.createBeamBreak();
    }

    public Command runIntake() {
        return runOnce(() -> {
            innerMotor.set(Constants.Intake.INTAKE_SPEED);
            outerMotor.set(Constants.Intake.INTAKE_SPEED);
        });
    }

    public Command reverseIntake() {
        return runOnce(() -> {
            innerMotor.set(Constants.Intake.REVERSE_SPEED);
            outerMotor.set(Constants.Intake.REVERSE_SPEED);
        });
    }

    public Command stopIntake() {
        return runOnce(() -> {
            innerMotor.set(Constants.Intake.IDLE_SPEED);
            outerMotor.set(Constants.Intake.IDLE_SPEED);
        });
    }

    public Command indexNote() {
        return runOnce(() -> {
            innerMotor.set(Constants.Intake.INTAKE_SPEED);
            outerMotor.set(Constants.Intake.IDLE_SPEED);
        });
    }

    public Command intakePiece() {
        return runIntake().andThen(run(() -> {})).until(
            beamBreak.getBlockedSupplier()
        ).andThen(indexNote());
    }

    public boolean hasNote() {
        return beamBreak.isBlocked();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Intake Beam Break", beamBreak.isBlocked());
    }

    public Command changeState(RobotState newState) {
        switch (newState) {
            case INTAKE:
                return runIntake();
            case INDEX:
                return indexNote();
            case REVERSE:
                return reverseIntake();
            default:
                return stopIntake();
        }
    }
}
