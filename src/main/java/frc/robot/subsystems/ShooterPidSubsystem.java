/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

public class ShooterPidSubsystem extends PIDSubsystem {

 private final WPI_VictorSPX m_shooterMotor = new WPI_VictorSPX(k_ShooterMotorAddress);
 private final Encoder m_encoder = new Encoder(k_encoder1Ch1DIO, k_encoder1Ch2DIO, true);
 private final WPI_VictorSPX m_shooterGate = new WPI_VictorSPX(k_ShooterInAddress);
 private final DigitalInput m_ballTopDetector = new DigitalInput(k_BallTopDetector);
 private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(k_sVolts, k_vVoltSecondsPerRotation);
 private ConveyorSubsystem m_conveyorSubsystem = null;

 private double m_setPoint = k_shooterTargetRPS;


 private boolean m_ballDetected = false;

 /**
  * Creates a new ShooterPidSubsystem.
  */

 public ShooterPidSubsystem() {
  super(new PIDController(kP, kI, kD));
  getController().setTolerance(k_shooterToleranceRPS);
  m_encoder.setDistancePerPulse(k_encoderDistancePerPulse);
  setSetpoint(m_setPoint);
  // The PIDController used by the subsystem
  this.init();
 }

 @Override
 public void useOutput(double output, double setpoint) {
  // Multiply by -1 to get the motor turning the right way
  m_shooterMotor.setVoltage(-1 * (output + m_shooterFeedforward.calculate(setpoint)));
  m_ballDetected = true; // until detector attached alway assume ball in place  later use this: m_ballTopDetector.get();
  updatedash();
 }

 @Override
 public double getMeasurement() {
  // Return the process variable measurement here
  return m_encoder.getRate();
 }

 public boolean atSetpoint() {
  return m_controller.atSetpoint();
 }

 public void runFeeder() {
  if (m_ballDetected && (this.atSetpoint()) && (this.isEnabled())) {
   m_shooterGate.set(k_feederSpeed);
   m_conveyorSubsystem.convayorSpeed(k_conveyorSpeed);
  } else {
   m_shooterGate.set(0.0);
  }
 }

 public void stopFeeder() {
  m_shooterGate.set(0);
  m_conveyorSubsystem.convayorSpeed(0);
 }

 public void setConveyor(ConveyorSubsystem conveyor) {
  m_conveyorSubsystem = conveyor;
 }

 private void init() {
  m_encoder.setDistancePerPulse(k_pulsePerRev);
  m_encoder.setMinRate(k_minRate);
  m_encoder.reset();
  this.disable();
 }
 private void updatedash() {
  SmartDashboard.putNumber("encoder Measurement", getMeasurement());
  SmartDashboard.putNumber("Shooter set point", m_setPoint);
  SmartDashboard.putNumber("shooter set", m_shooterMotor.get());
  SmartDashboard.putNumber("feeder set", m_shooterGate.get());
  SmartDashboard.putBoolean("shooter ready", (this.atSetpoint() && this.isEnabled()));
  SmartDashboard.putBoolean("Ball Detected", m_ballDetected);
 }

 public void toggle() {
  if (this.isEnabled()) {
   disable();
  } else {
   enable();
  }
 }
}