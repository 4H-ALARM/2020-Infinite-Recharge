/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.*;

import static frc.robot.Constants.*;

public class LiftSubsystem extends SubsystemBase {
  private final DoubleSolenoid m_gearShift = new DoubleSolenoid(k_shiftUp, k_shiftDown);
  private final DoubleSolenoid m_winchLock = new DoubleSolenoid(k_winchLock, k_winchUnlock);
  private final WPI_VictorSPX m_liftMotor = new WPI_VictorSPX(k_liftMotorAddress);
  /**
   * Creates a new LiftSubsystem.
   */
  public LiftSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
