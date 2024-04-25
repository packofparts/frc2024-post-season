package frc.robot.subsystems;
import java.security.Key;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class beamBreakerSubsystem extends SubsystemBase{
    DigitalInput beamBreaker;
    public beamBreakerSubsystem(int port){
        beamBreaker = new DigitalInput(port);

    }
    public boolean getBeamValue(){
        return beamBreaker.get();
    }
    @Override
    public void periodic(){
        SmartDashboard.putBoolean("BeamBreaker", getBeamValue());
    }
}