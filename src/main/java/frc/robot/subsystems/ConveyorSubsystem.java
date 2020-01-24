/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.*;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ConveyorSubsystem extends SubsystemBase {
  private final WPI_TalonSRX BeltExit = new WPI_TalonSRX(k_BallBeltExitAddress);
  private final WPI_TalonSRX BallConveryor = new WPI_TalonSRX(k_BeltConveyorAddress);
  private final DigitalInput BallTopDetector = new DigitalInput(k_BallTopDetector);
  
  /**
   * Creates a new ConveyorSubsystem.
   */
  public ConveyorSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
