// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.PIDConstants;
import POPLib.Control.ArmFFConfig;
import POPLib.Control.PIDConfig;
import POPLib.Motor.MotorConfig;
import POPLib.Motor.MotorConfig.Mode;
import POPLib.Sensors.AbsoluteEncoder.AbsoluteEncoderConfig;
import POPLib.Sensors.BeamBreak.BeamBreakConfig;
import POPLib.Swerve.SwerveConstants.SDSModules;
import POPLib.Swerve.SwerveConstants.SwerveModuleConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

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
        
        public static final ArmFFConfig FF = new ArmFFConfig(0.0);
        public static final double GEAR_RATIO = 1.0;
        public static final AbsoluteEncoderConfig ABSOLUTE_CONFIG = new AbsoluteEncoderConfig(0, 0.0, false);
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

    public static final class Swerve {
        public static final boolean GYRO_INVERSION = false; // Always ensure Gyro is CCW+ CW-

        public static final double TRACK_WIDTH = Units.inchesToMeters(23);
        public static final double WHEEL_BASE = Units.inchesToMeters(23);

        public static final double DRIVE_BASE_RADIUS = Math
            .sqrt(TRACK_WIDTH * TRACK_WIDTH + WHEEL_BASE * WHEEL_BASE) / 2;

        public static final SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics(
            new Translation2d(WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
            new Translation2d(WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
            new Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
            new Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0)
        );

        public static final MotorConfig ANGLE_CONFIG = new MotorConfig(
            20,
            false, // Make true if we have a stroke
            PIDConfig.getPid(0.0), // TODO: retune
            MotorConfig.Mode.COAST
        );


        public static final MotorConfig DRIVE_CONFIG = new MotorConfig(
            40,
            true,
            PIDConfig.getPid(0.0),
            MotorConfig.Mode.BRAKE
        );

        public static final SDSModules MODULE_TYPE = SDSModules.MK4i;

        public static final boolean SWERVE_TUNING_MODE = false;

        public static final SwerveModuleConstants[] SWERVE_MODULE_CONSTANTS = SwerveModuleConstants.generateConstants(
            new Rotation2d[] {
                Rotation2d.fromDegrees(0.0),
                Rotation2d.fromDegrees(0.0),
                Rotation2d.fromDegrees(0.0),
                Rotation2d.fromDegrees(0.0)
            },
            new Integer[] {
                -1, -1, -1, //Module 0
                -1, -1, -1 // Module 1
                -1, -1, -1 // Module 2
                -1, -1, -1 // Module 3
            },
            MODULE_TYPE, 
            SWERVE_TUNING_MODE, 
            DRIVE_CONFIG, 
            ANGLE_CONFIG
        );

        public static final int PIGEON_ID = -1;
    }
}
