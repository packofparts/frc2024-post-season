// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import POPLib.Subsytems.Pivot.SparkPivot;
import frc.robot.Constants;

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
    }

    public void periodic() {
        super.periodic();
    }
}
