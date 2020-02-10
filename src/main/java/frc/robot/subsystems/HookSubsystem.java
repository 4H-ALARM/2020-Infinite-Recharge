/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.*;

import static frc.robot.Constants.*;

public class HookSubsystem extends SubsystemBase {
  private final WPI_VictorSPX m_hookMotor = new WPI_VictorSPX(k_hookMotorAddress);
  private final  ADXRS450_Gyro  m_gyro = new ADXRS450_Gyro(k_gyroPort);
  /**
   * Creates a new HookSubsystem.
   */
  public HookSubsystem() {
    // init gyro
    m_gyro.calibrate();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    updatedash();
  }

  public void drive(final double speed){
    m_hookMotor.set(speed);
  }

  private void updatedash(){
    SmartDashboard.putNumber("gyro Measurement", m_gyro.getAngle());
    SmartDashboard.putNumber("hook motor set", m_hookMotor.get());
  }
    
}
