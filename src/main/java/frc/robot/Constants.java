// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import POPLib.Control.PIDConfig;
import POPLib.Motor.MotorConfig;
import POPLib.Motor.MotorConfig.Mode;
import POPLib.Sensors.BeamBreak.BeamBreakConfig;

public final class Constants {
    public static final boolean TUNING_MODE = false;

    public static class Ports {
        public static final String CANIVORE_NAME = ""; // TODO: Set later
    }

    public static class Intake {
        public static MotorConfig OUTER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static MotorConfig INNER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static BeamBreakConfig BEAM_BREAK = new BeamBreakConfig(-1); // TODO: set later
    }

    public static class Wrist {
        public final static MotorConfig LEFT_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public final static MotorConfig RIGHT_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public static final double G = 0.0;
        public static final double S = 0.0;
        public static final double V = 0.0;

        public static final double GEAR_RATIO = 1.0;
    }

    public static class Shooter {
        public final static MotorConfig TOP_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public final static MotorConfig BOTTOM_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        ); 

        public final static MotorConfig INDEXER_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public static final double MAX_ERROR = 5.0;

        public static final double INDEXER_SPEED = 0.8;

        public static BeamBreakConfig BEAM_BREAK = new BeamBreakConfig(-1); // TODO: set later
    }
}
