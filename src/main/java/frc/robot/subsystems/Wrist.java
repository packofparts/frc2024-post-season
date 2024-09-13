// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import POPLib.Subsytems.Pivot.SparkPivot;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.util.StateManager.RobotState;

public class Wrist extends SparkPivot {
    private static Wrist instance;

    public static Wrist getInstance() {
        if (instance == null) {
            instance = new Wrist();
        }
        return instance;
    }

    private Wrist() {
        super(
            Constants.Wrist.RIGHT_MOTOR, 
            Constants.Wrist.LEFT_MOTOR,
            Constants.Wrist.GEAR_RATIO,
            true,
            Constants.Wrist.FF,
            Constants.Wrist.ABSOLUTE_CONFIG,
            Constants.TUNING_MODE,
            "Wrist"
        );

        setpoint.setDefault(Constants.Wrist.IDLE_SETPOINT);
    }

    public Command changeState(RobotState newState) {
        switch (newState) {
            case AMP:
                return moveWrist(Constants.Wrist.AMP_SETPOINT, Constants.Wrist.MAX_ERROR);
            default:
                return moveWrist(Constants.Wrist.IDLE_SETPOINT, Constants.Wrist.MAX_ERROR);
        }
    }

    @Override
    public void periodic() {
        super.periodic();
        super.log();
    }
}
