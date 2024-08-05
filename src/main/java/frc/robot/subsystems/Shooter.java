// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import POPLib.Sensors.BeamBreak.BeamBreak;
import POPLib.Subsytems.Flywheel.TalonFlywheel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

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

        indexer = Constants.Shooter.INDEXER_MOTOR.createSparkMax();
        beamBreak = Constants.Shooter.BEAM_BREAK.createBeamBreak();
    }

    public void turnOnIndexer() {
        indexer.set(Constants.Shooter.INDEXER_SPEED);
    }

    public void turnOffIndexer() {
        indexer.set(0.0);
    }

    // public Command fireNote(double setpoint) {
    //     return updateSetpointCommand(setpoint, Constants.Shooter.MAX_ERROR).
    //         andThen(this::turnOnIndexer).
    //         until(beamBreak.getUnBlockedSupplier())
    //         .andThen(this::turnOffIndexer);
    // }

    public Command feedInNote() {
        return runOnce(this::turnOnIndexer).andThen(run(() -> {}).
            until(beamBreak.getBlockedSupplier())
            .andThen(this::turnOffIndexer));
    }

    @Override
    public void periodic() {
        super.periodic();
        SmartDashboard.putBoolean("Shooter Blocked", beamBreak.isBlocked());
        super.log();
    }
}
