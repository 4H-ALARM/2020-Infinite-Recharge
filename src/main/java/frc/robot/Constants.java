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
    public static final int k_leftFrontDriveAddress = 2;    // TalonSRX
    public static final int k_leftRearDriveAddress = 3;     // TalonSRX  
    public static final int k_rightFrontDriveAddress = 4;   // TalonSRX
    public static final int k_rightRearDriveAddress = 5;    // TalonSRX
    public static final int k_ShooterMotorAddress = 10;      // TalonSRX
    public static final int k_ballIntakeMotorAddress = 7;   // VictorSPX
    public static final int k_BeltConveyorAddress = 8;      // VictorSPX
    public static final int k_ShooterInAddress = 9;         // VictorSPX
    public static final int k_ColorWheelMotorAddress = 6;  // VictorSPX
    public static final int k_liftMotorAddress = 11;        // VictorSPX
    public static final int k_hookMotorAddress = 12;        // VictorSPX

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

    // other constants
    public static final int k_pulsePerRev = 20;
    public static final int k_minRate = 50;
    public static final boolean k_squareInputs = false;
    public static final int k_colorWheelDeploy = 0;
    public static final int k_colorWheelRetract = 1;
    public static final int k_intakeDeploy = 2;
    public static final int k_intakeRetract = 3;
    public static final int k_shiftUp = 4;
    public static final int k_shiftDown = 5;
    public static final int k_winchLock = 6;
    public static final int k_winchUnlock = 7;

    public static final I2C.Port k_colorSensorPort = I2C.Port.kOnboard;

    // shooter constants
    public static final double k_shooterFreeRPS = 38000;
    public static final double k_shooterTargetRPS = 100000;
    public static final double k_shooterToleranceRPS = 1000;
    public static final double kP = 1;
    public static final double kI = 0.2;
    public static final double kD = 0;   
    public static final double k_sVolts = 0.05;
    public static final double k_vVoltSecondsPerRotation = 12.0 / k_shooterFreeRPS;
    
    public static final double k_feederSpeed = 0.5;
    public static final int kEncoderCPR = 1024;

    public static final double k_encoderDistancePerPulse =
    // Distance units will be rotations
    1.0 / (double) kEncoderCPR;

}
