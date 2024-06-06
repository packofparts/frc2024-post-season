// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.SushiLib.Motor.MotorConfig;
import frc.robot.SushiLib.Sensors.BeamBreak.BeamBreakConfig;

public final class Constants {
    public static class Ports {
        public static final String CANIVORE_NAME = ""; // TODO: Set later
    }

    public static class Intake {
        public static MotorConfig OUTER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static MotorConfig INNER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static BeamBreakConfig BEAM_BREAK = new BeamBreakConfig(-1); // TODO: set later
    }
}
