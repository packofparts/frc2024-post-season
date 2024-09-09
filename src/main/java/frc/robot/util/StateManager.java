package frc.robot.util;

import edu.wpi.first.wpilibj2.command.Command;

public class StateManager {
    public enum RobotState {
        IDLE,
        INTAKE,
        INDEX,
        FENDER,
        AMP;
    }
}