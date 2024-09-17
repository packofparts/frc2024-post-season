// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;
import com.revrobotics.CANSparkMax;
import POPLib.Sensors.BeamBreak.BeamBreak;
import POPLib.Subsytems.Flywheel.TalonFlywheel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.util.StateManager.RobotState;

public class Shooter extends TalonFlywheel {
    private final CANSparkMax indexer;
    private final BeamBreak beamBreak;

    private static Shooter instance;

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    private Shooter() {
        super(Constants.Shooter.TOP_MOTOR, Constants.Shooter.BOTTOM_MOTOR, "Shooter", Constants.TUNING_MODE, false);

        setpoint.setDefault(Constants.Shooter.IDLE_SETPOINT);

        indexer = Constants.Shooter.INDEXER_MOTOR.createSparkMax();
        beamBreak = Constants.Shooter.BEAM_BREAK.createBeamBreak();
    }

    public void turnOnIndexer() {
        indexer.set(Constants.Shooter.INDEXER_SPEED);
    }

    public void turnOffIndexer() {
        indexer.set(0.0);
    }

    public Command fireNote(double setpoint) {
        return updateSetpointCommand(setpoint, Constants.Shooter.MAX_ERROR).
            andThen(this::turnOnIndexer);
    }

    public Command fireNoteNoIndexer(double setpoint) {
        return updateSetpointCommand(setpoint, Constants.Shooter.MAX_ERROR);
    }

    public Command feedInNote() {
        // return updateSetpointCommand(Constants.Shooter.IDLE_SETPOINT, Constants.Shooter.MAX_ERROR)
        //     .andThen(this::turnOnIndexer);

        return run(() -> turnOnIndexer());

        // return updateSetpointCommand(Constants.Shooter.IDLE_SETPOINT, Constants.Shooter.MAX_ERROR)
        //     .andThen(this::turnOnIndexer).andThen(run(() -> {}).
        //     until(beamBreak.getBlockedSupplier())
        //     .andThen(this::turnOffIndexer));
    }

    public boolean firingNote() {
        return getVelocity() > 1.0;
    }

    public boolean hasNote() {
        return beamBreak.isBlocked();
    }

    public BooleanSupplier hasNoteSupplier(){
        return beamBreak.getBlockedSupplier();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Shooter Blocked", beamBreak.isBlocked());
        super.periodic();
        super.log();
    }

    public Command changeState(RobotState newState) {
        switch (newState) {
            case INDEX:
                return feedInNote();
            case FENDER:
                return fireNote(Constants.Shooter.FENDOR_SETPOINT);
            case AMP:
                return fireNote(Constants.Shooter.AMP_SETPOINT);
            default:
                return fireNoteNoIndexer(Constants.Shooter.IDLE_SETPOINT);
        }
    }
}
