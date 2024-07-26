// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import POPLib.Subsytems.Flywheel.SparkFlywheel;
import frc.robot.Constants;

public class Shooter extends SparkFlywheel {
    private static Shooter instance;

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    private Shooter() {
        super(Constants.Shooter.TOP_MOTOR, Constants.Shooter.BOTTOM_MOTOR, "Shooter", Constants.TUNING_MODE, true);
    }

    @Override
    public void periodic() {
        super.periodic();
        super.log();
    }
}
