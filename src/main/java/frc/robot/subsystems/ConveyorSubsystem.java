/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import static frc.robot.Constants.*;

public class ConveyorSubsystem extends SubsystemBase {
  private final SendableChooser<String> m_reverseChooser = new SendableChooser<>();
  private final WPI_VictorSPX BallConveryor = new WPI_VictorSPX(k_BeltConveyorAddress);

  private int isFWD = 1;  // becomes -1 if reverse is selected from the dashboard
    
  /**
   * Creates a new ConveyorSubsystem.
   */
  public ConveyorSubsystem() {
    m_reverseChooser.setDefaultOption("Conveyor Fwd", "FWD");
    m_reverseChooser.addOption("Conveyor Rev", "RVR");
    SmartDashboard.putData("ConveyorDirection", m_reverseChooser);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    getFWDorRevSelection();
    updatedash();
  }
  public void convayorSpeed(double speed) {
    BallConveryor.set(isFWD*speed);
  }

  public double getMotorSpeed() {
    return BallConveryor.get();
  }

  private void updatedash(){
    SmartDashboard.putNumber("Conveyor motor set", BallConveryor.get());
  }

  private void getFWDorRevSelection() {
    if (m_reverseChooser.getSelected() == "RVR") {
      isFWD = -1;
    } else {
      isFWD = 1;
    }


  }
}
