/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.*;

public class DriveSubsystem extends SubsystemBase {

  // private final WPI_TalonSRX leftFrontDriveTalonSRX = new WPI_TalonSRX(k_leftFrontDriveAddress);
  // private final WPI_TalonSRX leftRearDriveTalonSRX = new WPI_TalonSRX(k_leftRearDriveAddress);
  // private final SpeedControllerGroup m_left = new SpeedControllerGroup(leftFrontDriveTalonSRX, leftRearDriveTalonSRX);
  // private final WPI_TalonSRX rightFrontDriveTalonSRX = new WPI_TalonSRX(k_rightFrontDriveAddress);
  // private final WPI_TalonSRX rightRearDriveTalonSRX = new WPI_TalonSRX(k_rightRearDriveAddress);
  // private final SpeedControllerGroup m_right = new SpeedControllerGroup(rightFrontDriveTalonSRX, rightRearDriveTalonSRX);
  // private final DifferentialDrive driveSubsystem = new DifferentialDrive(m_left, m_right);
  
  private final Spark leftDrive = new Spark(1);
  private final Spark rightDrive = new Spark(2);
  //private final RobotDrive drive = new RobotDrive(leftDrive, rightDrive);
  public DifferentialDrive driveSubsystem = new DifferentialDrive(leftDrive, rightDrive);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(final double lSpeed, final double rSpeed) {

   driveSubsystem.tankDrive(lSpeed, rSpeed, k_squareInputs);
    
  }
  
}
