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
import edu.wpi.first.wpilibj.XboxController;

public final class Constants {
    public static final boolean TUNING_MODE = false;
    public static final boolean JOYSTICKS = false;

    public static class Ports {
        public static final String CANIVORE_NAME = "DriveMotors";
    }

    public static class Controls {
        // Shared Util
        public static final int IDLE = XboxController.Button.kStart.value;

        // Driver Controls

        // Orignal
        // public static final int INTAKE = XboxController.Button.kA.value;
        // public static final int FENDER = XboxController.Button.kB.value;
        // public static final int AMP = XboxController.Button.kY.value;
        // public static final int REVERSE = XboxController.Button.kX.value;


        // SIMON
        // public static final int INTAKE = XboxController.Axis.kLeftTrigger.value;
        // public static final int FENDER = XboxController.Axis.kRightTrigger.value;// Right Trigger
        // public static final int AMP = XboxController.Button.kB.value;
        // public static final int REVERSE = XboxController.Button.kA.value;

        // HARSHA
        // public static final int INTAKE = XboxController.Axis.kLeftTrigger.value;
        // public static final int FENDER = XboxController.Axis.kRightTrigger.value;
        // public static final int AMP = XboxController.Button.kB.value;
        // public static final int REVERSE = XboxController.Button.kA.value;

        // ADITYA G
        // public static final int INTAKE = XboxController.Axis.kLeftTrigger.value;
        // public static final int FENDER = XboxController.Axis.kRightTrigger.value;
        // public static final int AMP = XboxController.Button.kA.value;
        // public static final int REVERSE = XboxController.Button.kB.value;


        // ANIKETH
        public static final int INTAKE = XboxController.Axis.kLeftTrigger.value;
        public static final int FENDER = XboxController.Axis.kRightTrigger.value;
        public static final int AMP = XboxController.Button.kRightBumper.value;
        public static final int REVERSE = XboxController.Button.kA.value;
    
        // Operator
        public static final int CLIMB_UP = XboxController.Button.kX.value;
        public static final int CLIMB_DOWN = XboxController.Button.kA.value;

        public static final int ZERO_GYRO = XboxController.Button.kY.value;
        public static final int ZERO_ENCODERS = XboxController.Button.kB.value;
        
    }

    public static class Intake {
        public static MotorConfig OUTER_MOTOR = new MotorConfig(36, 40, true, Mode.COAST); 
        public static MotorConfig INNER_MOTOR = new MotorConfig(30, 40, false, Mode.COAST); 
        public static BeamBreakConfig BEAM_BREAK = new BeamBreakConfig(1, true);

        public static double IDLE_SPEED = 0.0;
        public static double INTAKE_SPEED = 0.8;
        public static double REVERSE_SPEED = -0.2;
    }

    public static class Climb {
        public static MotorConfig LEFT_MOTOR = new MotorConfig(33, 5, false, Mode.COAST); 
        public static MotorConfig RIGHT_MOTOR = new MotorConfig(32, 5, false, Mode.COAST); 

        public static double SPEED = 1.0;

        public static double MAX_POS = 390.0;
    }

    public static class Wrist {
        public final static MotorConfig LEFT_MOTOR = new MotorConfig(
            34,
            40,
            false,
            new PIDConfig(0.01, 0.0, 0.0, 0.0), // 0.1
            Mode.BRAKE
        );

        public final static MotorConfig RIGHT_MOTOR = new MotorConfig(
            35,
            40,
            true,
            new PIDConfig(0.01, 0.0, 0.0, 0.0), // 0.1
            Mode.BRAKE
        );
        
        public static final ArmFFConfig FF = new ArmFFConfig(0.5);
        public static final double GEAR_RATIO = 75.0;
        public static final AbsoluteEncoderConfig ABSOLUTE_CONFIG = new AbsoluteEncoderConfig(0, 127.0 , false);

        public static final double MAX_ERROR = 10.0;

        public static final double IDLE_SETPOINT = 198.0;
        public static final double STAGE_SETPOINT = 198.0;
        public static final double AMP_SETPOINT = 99.69;
    }

    public static class Shooter {
        public final static MotorConfig TOP_MOTOR = new MotorConfig(
            42,
            Constants.Ports.CANIVORE_NAME,
            40,
            true,
            new PIDConfig(0.01, 0.0, 0.0, 0.0095), // 0.02
            Mode.COAST
        );

        public final static MotorConfig BOTTOM_MOTOR = new MotorConfig(
            41,
            Constants.Ports.CANIVORE_NAME,
            40,
            true,
            new PIDConfig(0.01, 0.0, 0.0, 0.0095), // 0.02
            Mode.COAST
        ); 
        

        public final static MotorConfig INDEXER_MOTOR = new MotorConfig(
            31,
            80,
            false,
            Mode.COAST
        );

        public static final double MAX_ERROR = 8.0;

        public static final double INDEXER_SPEED = 0.9;

        public static final double IDLE_SETPOINT = 0.0;
        public static final double FENDOR_SETPOINT = 80.0;
        public static final double STAGE_SETPOINT = 80.0;
        public static final double AMP_SETPOINT = 30.0;

        public static BeamBreakConfig BEAM_BREAK = new BeamBreakConfig(3, true); 
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
            new Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
            new Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0)
        );

        public static final MotorConfig ANGLE_CONFIG = new MotorConfig(
            30,
            false, // Make true if we have a stroke
            PIDConfig.getPid(0.1), // TODO: retune
            MotorConfig.Mode.COAST
        );


        public static final MotorConfig DRIVE_CONFIG = new MotorConfig(
            80,
            false,
            PIDConfig.getPid(0.1, 0.73),
            MotorConfig.Mode.BRAKE
        );

        public static final SDSModules MODULE_TYPE = SDSModules.MK4;

        public static final boolean SWERVE_TUNING_MODE = false;

        public static final PIDConstants AUTO_TRANSLATION = new PIDConstants(3); // Previouse value modified on
                                                                                         // 3/20/24 15 25
        public static final PIDConstants AUTO_ROTATION = new PIDConstants(0.8); // Previouse value modified on
                                                                                        // 3/20/24 1.5 2.0

        public static final SwerveModuleConstants[] SWERVE_MODULE_CONSTANTS = SwerveModuleConstants.generateConstants(
            new Rotation2d[] {
                Rotation2d.fromDegrees(41.044922), // 42.2
                Rotation2d.fromDegrees(315.791016), // 315.4
                Rotation2d.fromDegrees(94.306641), // 95.09
                Rotation2d.fromDegrees(97.646484) // 101.95
            },
            new Integer[] {
                1, 22, 8, //Module 0 (front left on 1294 comp robot) CORRECT
                7, 23, 3, // Module 1 (front right on 1294 comp robot) CORRECT
                9, 20, 4, // Module 2 (back left on 1294 comp robot) CORRECT
                5, 21, 6 // Module 3 (back right on 1294 comp robot)
            },
            MODULE_TYPE, 
            SWERVE_TUNING_MODE, 
            DRIVE_CONFIG, 
            ANGLE_CONFIG
        );

        public static final int PIGEON_ID = 25;
    }
}
