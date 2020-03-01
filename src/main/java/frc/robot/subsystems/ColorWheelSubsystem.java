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
import edu.wpi.first.wpilibj.DriverStation;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import static frc.robot.Constants.*;

public class ColorWheelSubsystem extends SubsystemBase {
  private final WPI_VictorSPX m_colorWheelMotor = new WPI_VictorSPX(k_ColorWheelMotorAddress);
  private final Solenoid m_colorWheelDeploy = new Solenoid(k_PCMModule, k_colorWheelDeploy);

  private final ColorSensorV3 colorSensor = new ColorSensorV3(k_colorSensorPort);
  private final ColorMatch colorMatcher = new ColorMatch();

  private final Color blueTargetColor = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color greenTargetColor = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color redTargetColor = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color yellowTargetColor = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private ColorMatchResult matchResult;
  private String m_targetColor = "Green";
  private String m_lastColorFound = "Unknown";
  private String m_ColorFound = "Unknown";
  private String m_ColorStop = "Unknown";
  private boolean m_colorMatched = false;
  private String gameData;
  private int m_colorTransitionCounter = 0;

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
    // get color selected from dashboard
    m_targetColor = m_colorChooser.getSelected(); 
    // over-write that with the target color from the game data
    // if there is no color selection in the game data it will stay
    // with the color chosen from the dashboard
    m_targetColor = getTargetFromDriverStation();
    checkForMatch();
    checkForColorTransition();
    updatedash();
    }
   
  public void SetSpeed(double speed){
    if (m_ColorFound == m_targetColor) {
      m_colorWheelMotor.set(0.0);
    } else {
      m_colorWheelMotor.set(speed);
    }
  }

  public void SetSpeedToTurn(double speed){
    if (m_colorTransitionCounter >= k_countFor3Turns) {
      m_colorWheelMotor.set(0.0);
    } else {
      m_colorWheelMotor.set(speed);
    }
  }

  public void  colorWheelDeploy(boolean out){
    m_colorWheelDeploy.set(out);
  }

  
  private String getTargetFromDriverStation() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    String selectedColor = m_targetColor;
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          //Blue case code
          selectedColor = "Blue";
          break;
        case 'G' :
          //Green case code
          selectedColor = "Green";
          break;
        case 'R' :
          //Red case code
          selectedColor = "Red";
          break;
        case 'Y' :
          //Yellow case code
          selectedColor = "Yellow";
          break;
        default :
          //This is corrupt data
          break;
      }
    } else {
      //Code for no data received yet
    }
    return selectedColor;
  }

  private void checkForMatch() {
    // The color we detect is 2 colors away from the field sensor
    // as spin clockwise the color order is Yellow, Blue, Green, Red, Yellow, Blue, Green, Red
    if (matchResult.color == blueTargetColor) {
      m_ColorFound = "Blue";
      m_ColorStop = "Red";
    } else if (matchResult.color == redTargetColor) {
      m_ColorFound = "Red";
      m_ColorStop = "Blue";
    } else if (matchResult.color == greenTargetColor) {
      m_ColorFound = "Green";
      m_ColorStop = "Yellow";
    } else if (matchResult.color == yellowTargetColor) {
      m_ColorFound = "Yellow";
      m_ColorStop = "Green";
    } else {
      m_ColorFound = "Unknown";
      m_ColorStop = "Unknown";
    }

    if (m_ColorStop == m_targetColor) {
      m_colorMatched = true;
    } else {
      m_colorMatched = false;
    }
  }

  private void updatedash(){    
    SmartDashboard.putString("Color target", m_targetColor);
    SmartDashboard.putString("Color offest", m_ColorStop);
    SmartDashboard.putString("Color detected", m_ColorFound);
    SmartDashboard.putBoolean("Color matched", m_colorMatched);
    SmartDashboard.putNumber("Color motor set", m_colorWheelMotor.get());
    SmartDashboard.putNumber("Color Transition count", m_colorTransitionCounter);
  }
  public int getColorTransitionCount(){//change
    return m_colorTransitionCounter;
  }
  public void resetColorTransitionCount(){ 
    m_colorTransitionCounter = 0;
  }
  private void checkForColorTransition(){
    if(m_lastColorFound != m_ColorFound){
      m_colorTransitionCounter += 1;
      m_lastColorFound = m_ColorFound;
    }
  } 
  
}
