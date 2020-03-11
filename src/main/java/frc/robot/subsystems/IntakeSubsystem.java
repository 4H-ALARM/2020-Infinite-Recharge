/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {
 private final WPI_VictorSPX ballIntakeMotor = new WPI_VictorSPX(k_ballIntakeMotorAddress);
 private final Solenoid IntakeDeploy = new Solenoid(k_PCMModule, k_intakeDeploy);
 public static boolean m_intakeDeployed = false;
 private ConveyorSubsystem m_conveyorSubsystem = null;

 /**
  * Creates a new IntakeSubsystem.
  */
 public IntakeSubsystem() {

 }

 @Override
 public void periodic() {
  // This method will be called once per scheduler run
  updatedash();
 }

 public void deploy() {
  IntakeDeploy.set(true);
  m_intakeDeployed = true;
 }

 public void retract() {
  IntakeDeploy.set(false);
  m_intakeDeployed = false;
 }

 public void setConveyor(ConveyorSubsystem conveyor) {
  m_conveyorSubsystem = conveyor;
 }

 public void toggle() {
  if (m_intakeDeployed) {
   disable();
  } else {
   enable();
  }
 }

 private void enable() {
  deploy();
  setSpeed(k_intakeSpeed);
  m_intakeDeployed = true;
 }

 private void disable() {
  setSpeed(0.0);
  retract();
  m_intakeDeployed = false;
 }

 public void setSpeed(final double ballSpeed) {
    ballIntakeMotor.set(-ballSpeed);
    if (ballSpeed == 0.0) {
     //m_conveyorSubsystem.convayorSpeed(0.0);
    } else {
     //m_conveyorSubsystem.convayorSpeed(k_conveyorSpeed);
    }
   }

 public void toggleWithConvayor() {
    if (m_intakeDeployed) {
     disableWithConvayor();
    } else {
     enableWithConvayor();
    }
   }

 private void enableWithConvayor() {
    deploy();
    setSpeedWithConveyor(k_intakeSpeed);
    m_intakeDeployed = true;
   }

private void enableWithConvayorBack() {
   deploy();
   setSpeedWithConveyor(-(k_intakeSpeed));
   m_intakeDeployed = true;
   }
 private void disableWithConvayor() {
    setSpeedWithConveyor(0.0);
    retract();
    m_intakeDeployed = false;
   }

public void setSpeedWithConveyor(final double ballSpeed) {
    ballIntakeMotor.set(-ballSpeed);
    if (ballSpeed == 0.0) {
     m_conveyorSubsystem.convayorSpeed(0.0);
    } else {
     m_conveyorSubsystem.convayorSpeed(k_conveyorSpeed);
    }
   }

 private void updatedash() {
  SmartDashboard.putBoolean("Intake Deployed", m_intakeDeployed);
  SmartDashboard.putNumber("Intake motor set", ballIntakeMotor.get());
  SmartDashboard.putNumber("Conveyor motor set", m_conveyorSubsystem.getMotorSpeed());
 }
}