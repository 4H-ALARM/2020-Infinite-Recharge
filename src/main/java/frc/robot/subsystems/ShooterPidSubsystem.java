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
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

public class ShooterPidSubsystem extends PIDSubsystem {

 private final WPI_VictorSPX m_shooterMotor = new WPI_VictorSPX(k_ShooterMotorAddress);
 private final Encoder m_encoder = new Encoder(k_encoder1Ch1DIO, k_encoder1Ch2DIO, true);
 private final WPI_VictorSPX m_shooterGate = new WPI_VictorSPX(k_ShooterInAddress);
 private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(k_sVolts, k_vVoltSecondsPerRotation);
 private ConveyorSubsystem m_conveyorSubsystem = null;

 public final SendableChooser<String> m_setPointChooser = new SendableChooser<>();
 private double m_setPoint = k_shooterTargetRPS;
 
 private boolean m_ballDetected = false;

 /**
  * Creates a new ShooterPidSubsystem.
  */

 public ShooterPidSubsystem() {
  super(new PIDController(kP, kI, kD));
  getController().setTolerance(k_shooterToleranceRPS);
  m_encoder.setDistancePerPulse(k_encoderDistancePerPulse);
  m_setPointChooser.setDefaultOption("Set Point", "SP");
  m_setPointChooser.addOption("Low Set Point", "LSP");
  m_setPointChooser.addOption("High Set Point", "HSP");
  m_setPointChooser.addOption("Pit Set Point", "PSP");
  SmartDashboard.putData("Set Point", m_setPointChooser);
  updateSetPoint();
  // The PIDController used by the subsystem
  this.init();
 }

 @Override
 public void useOutput(double output, double setpoint) {
  updateSetPoint();
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
  updatedash();
 }

 public void stopFeeder() {
  m_shooterGate.set(0);
  m_conveyorSubsystem.convayorSpeed(0);
  updatedash();
 }

 public void setConveyor(ConveyorSubsystem conveyor) {
  m_conveyorSubsystem = conveyor;
 }

 public void toggle() {
    if (this.isEnabled()) {
     disable();
    } else {
     enable();
    }
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
 }

 private void updateSetPoint() {
     String setPointSelection = m_setPointChooser.getSelected();

     if (setPointSelection == "PSP") {
        m_setPoint = k_shooterTargetPIT;
     } else if (setPointSelection == "LSP") {
        m_setPoint = k_shooterTargetLow;
     } else if (setPointSelection == "HSP") {
        m_setPoint = k_shooterTargetHigh;
     } else {
        m_setPoint = k_shooterTargetRPS;
     }
    setSetpoint(m_setPoint);
 }
}