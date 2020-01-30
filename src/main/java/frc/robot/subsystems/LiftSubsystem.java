/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.*;

import static frc.robot.Constants.*;

public class LiftSubsystem extends SubsystemBase {
  private boolean m_topDetected = false;
  private boolean m_bottomDetected = false;
  private final DigitalInput m_topDetector = new DigitalInput(k_topDetector);
  private final DigitalInput m_bottomDetector = new DigitalInput(k_bottomDetector);
  private final Solenoid m_gearShift = new Solenoid(k_shiftUp);
  private final Solenoid m_winchLock = new Solenoid(k_winchLock);
  private final WPI_VictorSPX m_liftMotor = new WPI_VictorSPX(k_liftMotorAddress);
  /**
   * Creates a new LiftSubsystem.
   */
  public LiftSubsystem() {

  }

  @Override
  public void periodic() {
    m_topDetected = m_topDetector.get();
    m_bottomDetected = m_bottomDetector.get();
    // This method will be called once per scheduler run
  }
  public void SetSpeed(double Speed){
    m_liftMotor.set(Speed);

  }
  public void gearshift (boolean high){
      m_gearShift.set(high);
  }
  public void winchLock (boolean high){
    m_winchLock.set(high);
}
}
