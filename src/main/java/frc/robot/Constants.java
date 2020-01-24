/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int k_leftFrontDriveAddress = 3;
    public static final int k_leftRearDriveAddress = 4;
    public static final int k_rightFrontDriveAddress = 5;
    public static final int k_rightRearDriveAddress = 6;
    public static final boolean k_squareInputs = false;

    public static final int k_ballThrowMotorAddress = 7;
    public static final int k_ballIntakeMotorAddress = 8;

    public static final int k_BeltConveyorAddress = 9;
    public static final int k_BallBeltExitAddress = 10;

    public static final int k_BallTopDetector = 0;

    public static final int k_ColorWheelSPXAddress = 2;
    public static final int k_rightSPXAddress = 3;

    public static final int k_motorStopSwitchDIO = 9;

    public static final int k_ShooterMotorAddress = 11;

    public static final int k_encoder1Ch1DIO = 1;
    public static final int k_encoder1Ch2DIO = 2;
    public static final int k_pulsePerRev = 20;
    public static final int k_minRate = 50;

    public static final int k_wheelDeploy = 0;
    public static final int k_wheelRetract = 1;

    public static final I2C.Port k_colorSensorPort = I2C.Port.kOnboard;

    public static final int k_xboxController = 0;

}
