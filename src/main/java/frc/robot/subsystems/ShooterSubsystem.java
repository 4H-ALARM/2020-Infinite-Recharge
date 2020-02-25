/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

import static frc.robot.Constants.*;

public class ShooterSubsystem extends SubsystemBase {
 private final WPI_VictorSPX m_shooterMotor = new WPI_VictorSPX(k_ShooterMotorAddress);
 private final Encoder m_encoder = new Encoder(k_encoder1Ch1DIO, k_encoder1Ch2DIO);
 private final WPI_VictorSPX m_shooterGate = new WPI_VictorSPX(k_ShooterInAddress);
 private final DigitalInput m_ballTopDetector = new DigitalInput(k_BallTopDetector);

 private boolean m_ballDetected = false;

 /**
  * Creates a new ShooterSubsystem.
  */
 public ShooterSubsystem() {
  this.init();

 }

 @Override
 public void periodic() {
  // This method will be called once per scheduler run
  m_ballDetected = m_ballTopDetector.get();
  System.out.print("encoder ");
  System.out.println(m_encoder.get());
  System.out.print("ballTopDetector ");
  System.out.println(m_ballDetected);
 }

 public void init() {
  m_encoder.setDistancePerPulse(k_pulsePerRev);
  m_encoder.setMinRate(k_minRate);
  m_encoder.reset();
 }

 public void setShooterSpeed(final double Speed) {
  m_shooterMotor.set(Speed);
 }

}