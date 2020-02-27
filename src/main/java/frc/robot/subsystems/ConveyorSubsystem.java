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

import static frc.robot.Constants.*;

public class ConveyorSubsystem extends SubsystemBase {
  
  private final WPI_VictorSPX BallConveryor = new WPI_VictorSPX(k_BeltConveyorAddress);
    
  /**
   * Creates a new ConveyorSubsystem.
   */
  public ConveyorSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updatedash();
  }
  public void convayorSpeed(double speed) {
    BallConveryor.set(speed);
  }

  public double getMotorSpeed() {
    return BallConveryor.get();
  }

  private void updatedash(){
    SmartDashboard.putNumber("Conveyor motor set", BallConveryor.get());
  }
}
