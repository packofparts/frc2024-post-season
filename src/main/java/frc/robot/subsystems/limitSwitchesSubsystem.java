package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
* Represents a subsystem with limit switches to control movement.
*/

public class limitSwitchesSubsystem  extends SubsystemBase{
    DigitalInput limitSwitch;
    public limitSwitchesSubsystem(int port) {
        limitSwitch = new DigitalInput(port);
    }
    public boolean getLimitValue(){
        return limitSwitch.get();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("LimitSwitch", getLimitValue());
        System.out.println(getLimitValue());
    }
}   
