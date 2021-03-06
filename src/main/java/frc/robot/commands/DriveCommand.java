/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
  private final DriveSubsystem driveSubsystem;

  private final DoubleSupplier leftSpeed;
  private final DoubleSupplier rightSpeed;

  /**
   * Creates a new DriveCommand.
   */
  public DriveCommand(DriveSubsystem driveSubsystem, final DoubleSupplier leftSpeed, final DoubleSupplier rightSpeed) {
    this.driveSubsystem = driveSubsystem;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.drive(leftSpeed.getAsDouble(), rightSpeed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
