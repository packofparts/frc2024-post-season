package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;

    private static Climb instance;
    
    public static Climb getInstance() {
        if (instance == null) {
            instance = new Climb();
        }

        return instance;
    }

    private Climb() {
        leftMotor = Constants.Climb.LEFT_MOTOR.createSparkMax();
        rightMotor =  Constants.Climb.RIGHT_MOTOR.createSparkMax();

        rightMotor.follow(leftMotor, true);
    }

    public Command moveUp() {
        return runOnce(() -> {
            leftMotor.set(Constants.Climb.SPEED);
        });
    }

    public Command moveDown() {
        return runOnce(() -> {
            leftMotor.set(-1 * Constants.Climb.SPEED);
        });
    }

    public Command stop() {
        return runOnce(() -> {
            leftMotor.set(0.0);
        });
    }

    public Command autoMoveUp() {
        return moveUp().andThen(run(() -> {}).until(
            () -> leftMotor.getEncoder().getPosition() > Constants.Climb.MAX_POS
        )).andThen(stop());
    }

    public Command autoMoveDown() {
        return moveUp().andThen(run(() -> {}).until(
            () -> leftMotor.getEncoder().getPosition() < 0.0
        )).andThen(stop());
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Climb Encoder", leftMotor.getEncoder().getPosition());
    }
}
