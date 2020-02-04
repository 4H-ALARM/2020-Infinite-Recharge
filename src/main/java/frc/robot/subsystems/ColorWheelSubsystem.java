/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import static frc.robot.Constants.*;

public class ColorWheelSubsystem extends SubsystemBase {
  private final WPI_VictorSPX m_colorWheelMotor = new WPI_VictorSPX(k_ColorWheelMotorAddress);
  private final Solenoid m_colorWheelDeploy = new Solenoid(k_colorWheelDeploy);

  private final ColorSensorV3 colorSensor = new ColorSensorV3(k_colorSensorPort);
  private final ColorMatch colorMatcher = new ColorMatch();

  private final Color blueTargetColor = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color greenTargetColor = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color redTargetColor = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color yellowTargetColor = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private ColorMatchResult matchResult;
  private String m_targetColor = "Green";
  private String m_ColorFound = "Unkown";
  private boolean m_colorMatched = false;

  public final SendableChooser<String> m_colorChooser = new SendableChooser<>();

  /**
   * Creates a new ColorWheelSubsystem.
   */
  public ColorWheelSubsystem() {
    colorMatcher.addColorMatch(blueTargetColor);
    colorMatcher.addColorMatch(greenTargetColor);
    colorMatcher.addColorMatch(redTargetColor);
    colorMatcher.addColorMatch(yellowTargetColor);

    m_colorChooser.setDefaultOption("Color Green", "Green");
    m_colorChooser.addOption("Color Blue", "Blue");
    m_colorChooser.addOption("Color Red", "Red");
    m_colorChooser.addOption("Color Yellow", "Yellow");
    SmartDashboard.putData("Color", m_colorChooser);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
    matchResult = colorMatcher.matchClosestColor(colorSensor.getColor());
    checkForMatch();
    m_targetColor = m_colorChooser.getSelected();

    updatedash();
    }
   
  public void SetSpeed(double speed){
    if (m_ColorFound == m_targetColor) {
      m_colorWheelMotor.set(0.0);
    } else {
      m_colorWheelMotor.set(speed);
    }
  }

  public void  colorWheelDeploy(boolean out){
    System.out.println("did this activate");
    m_colorWheelDeploy.set(out);
  }

  private void updatedash(){    
    SmartDashboard.putString("Color target", m_targetColor);
    SmartDashboard.putString("Color detected", m_ColorFound);
    SmartDashboard.putBoolean("Color matched", m_colorMatched);
    SmartDashboard.putNumber("Color motor set", m_colorWheelMotor.get());
  }

  private void checkForMatch() {
    if (matchResult.color == blueTargetColor) {
      m_ColorFound = "Blue";
    } else if (matchResult.color == redTargetColor) {
      m_ColorFound = "Red";
    } else if (matchResult.color == greenTargetColor) {
      m_ColorFound = "Green";
    } else if (matchResult.color == yellowTargetColor) {
      m_ColorFound = "Yellow";
    } else {
      m_ColorFound = "Unkown";
    }

    if (m_ColorFound == m_targetColor) {
      m_colorMatched = true;
    } else {
      m_colorMatched = false;
    }

  }
}
