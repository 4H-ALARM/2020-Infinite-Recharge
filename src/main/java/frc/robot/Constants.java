/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
 // CAN bus address - PDP = 0, PCM = 1 by default
 public static final int k_leftFrontDriveAddress = 2; // TalonSRX X
 public static final int k_leftRearDriveAddress = 3; // TalonSRX X
 public static final int k_rightFrontDriveAddress = 4; // TalonSRX X
 public static final int k_rightRearDriveAddress = 5; // TalonSRX X
 public static final int k_ColorWheelMotorAddress = 6; // VictorSPX X
 public static final int k_ballIntakeMotorAddress = 7; // VictorSPX X
 public static final int k_BeltConveyorAddress = 8; // VictorSPX X
 public static final int k_ShooterInAddress = 9; // VictorSPX X  
 public static final int k_ShooterMotorAddress = 10; // VictorSPX X
 public static final int k_liftMotorAddress = 11; // VictorSPX X
 public static final int k_hookMotorAddress = 12; // VictorSPX X

 // PDP circuits
 public static final int k_leftFrontDrivePDP = 15;
 public static final int k_leftRearDrivePDP = 14;
 public static final int k_rightFrontDrivePDP = 13;
 public static final int k_rightRearDrivePDP = 12;
 public static final int k_ColorWheelMotorPDP = 7;
 public static final int k_ballIntakeMotorPDP = 6;
 public static final int k_BeltConveyorPDP = 3;
 public static final int k_ShooterInPDP = 4;
 public static final int k_ShooterMotorPDP = 2;
 public static final int k_liftMotorPDP = 1;
 public static final int k_hookMotorPDP = 0;

 // DIO addresses
 public static final int k_BallTopDetector = 0;
 public static final int k_encoder1Ch1DIO = 1;
 public static final int k_encoder1Ch2DIO = 2;
 public static final int k_topDetector = 3;
 public static final int k_bottomDetector = 4;


 // SPIO addresses
 public static final SPI.Port k_gyroPort = SPI.Port.kOnboardCS0;

 // USB addresses
 public static final int k_xboxController = 0;
 public static final int k_boxController = 1;
 public static final boolean k_useBox = true; // set to true to use ALARM Box, false to use logitech

 // other constants
 public static final int k_pulsePerRev = 20;
 public static final int k_minRate = 50;
 public static final boolean k_squareInputs = true;
 public static final int k_countFor3Turns = 30;

 // solenoid addresses on PCM
 public static final int k_PCMModule = 1;
 public static final int k_colorWheelDeploy = 0;
 public static final int k_intakeDeploy = 1;
 public static final int k_winchLock = 2;

 // other PCM addresses planned but not used 
 // public static final int k_colorWheelRetract = 1;    
 // public static final int k_intakeRetract = 3;
 // public static final int k_shiftUp = 4;
 // public static final int k_shiftDown = 5;    
 // public static final int k_winchUnlock = 7;

 public static final I2C.Port k_colorSensorPort = I2C.Port.kOnboard;

 // shooter constants
 public static final double k_shooterFreeRPS = 32000; //39000
 public static final double k_shooterTargetPIT = 8000;
 public static final double k_shooterTargetLow = 17000;
 public static final double k_shooterTargetRPS = 18000; //18000
 public static final double k_shooterTargetHigh = 19000;
 public static final double k_shooterToleranceRPS = 400; // 1000
 public static final double kP = 2 * (1 / k_shooterTargetRPS);
 public static final double kI = 0.01;
 public static final double kD = 0;
 public static final double k_sVolts = 0.05;
 public static final double k_vVoltSecondsPerRotation = 12.0 / k_shooterFreeRPS;
 public static final double k_feederSpeed = -0.5;
 public static final double k_conveyorSpeed = .5;
 public static final double k_intakeSpeed = 0.75;

 public static final int kEncoderCPR = 1024;
 public static final double k_encoderDistancePerPulse =
  // Distance units will be rotations
  1.0 / (double) kEncoderCPR;
 //auto
 public static String k_Automode = "NONE";
 public static final double k_autoDriveTime = 4.3;
 public static final double k_autoShootTime = 3;
 //color wheel
 public static final double k_colorWheelSpeed = .3;

}