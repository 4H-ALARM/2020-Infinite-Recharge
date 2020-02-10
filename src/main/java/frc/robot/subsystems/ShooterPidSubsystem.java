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
  private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(k_sVolts,k_vVoltSecondsPerRotation);


  private boolean m_ballDetected = false;

  /**
   * Creates a new ShooterPidSubsystem.
   */

  public ShooterPidSubsystem() {
    super(new PIDController(kP, kI, kD));
    getController().setTolerance(k_shooterToleranceRPS);
    m_encoder.setDistancePerPulse(k_encoderDistancePerPulse);//im a reference to a pointer
    setSetpoint(k_shooterTargetRPS);
        // The PIDController used by the subsystem
        this.init();
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // setSetpoint(k_shooterTargetRPS);
    m_shooterMotor.setVoltage(output + m_shooterFeedforward.calculate(setpoint));
    m_ballDetected = true;  // until detector attached alway assume ball in place  later use this: m_ballTopDetector.get();
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
    } else {
      m_shooterGate.set(0.0);
    }
  }

  public void stopFeeder() {
    m_shooterGate.set(0);
  }

  private void init() {
    m_encoder.setDistancePerPulse(k_pulsePerRev);
    m_encoder.setMinRate(k_minRate);
    m_encoder.reset();
    this.disable();
  }
  private void updatedash(){
    SmartDashboard.putNumber("encoder Measurement", getMeasurement());
    SmartDashboard.putNumber("Shooter set point",k_shooterTargetRPS);
    SmartDashboard.putNumber("shooter set", m_shooterMotor.get());
    SmartDashboard.putNumber("feeder set", m_shooterGate.get());
    SmartDashboard.putBoolean("shooter ready", (this.atSetpoint() && this.isEnabled()));
    SmartDashboard.putBoolean("Ball Detected", m_ballDetected);
  }
}
