/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterPidSubsystem;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoCommand extends SequentialCommandGroup {
  private String m_name = "Drive and Shoot";
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  

  /**
   * Creates a new AutoCommand.
   */
  public AutoCommand(DriveSubsystem subsystem, ShooterPidSubsystem shooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new ShooterOn(shooter), new DriveStraight(subsystem).withTimeout(1), new FeedShooter(shooter));
  }

  public String getName() {
    return m_name;
  }
}
