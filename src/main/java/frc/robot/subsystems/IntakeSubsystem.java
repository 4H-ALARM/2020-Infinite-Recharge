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

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {
  private final WPI_VictorSPX ballIntakeMotor = new WPI_VictorSPX(k_ballIntakeMotorAddress);
  private final Solenoid IntakeDeploy = new Solenoid(k_intakeDeploy);
  private double motorSpeed;

  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(final double ballSpeed) {
    ballIntakeMotor.set(ballSpeed);
  }
  public void deploy(){
    IntakeDeploy.set(true);
  }
}
