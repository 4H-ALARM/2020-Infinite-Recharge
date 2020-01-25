/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ShooterOff;
import frc.robot.commands.ShooterOn;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HookSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import com.analog.adis16448.frc.ADIS16448_IMU ;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import static frc.robot.Constants.*;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems here..
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ConveyorSubsystem m_conveyourSubsystem = new ConveyorSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();

  private final HookSubsystem m_hookSubsystem = new HookSubsystem(); 
  private final LiftSubsystem m_liftSubsystem = new LiftSubsystem();
 
  private final ColorWheelSubsystem m_colorWheelSubsystem = new ColorWheelSubsystem();


  // and commands are defined here...
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final DriveCommand driveCommand;

  private final ShooterOn shooterOnCommand;

  private final ShooterOff shooterOffCommand;
  
  private final XboxController xboxController = new XboxController(k_xboxController);


  // make camera and compressor public parts of the robtot to be run from 
  // the robot class rather than a seperate subsystem
  public final Compressor m_compressor = new Compressor();
  // public static ADIS16448_IMU m_imu = new ADIS16448_IMU();



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_colorWheelSubsystem.register();
    m_shooterSubsystem.register();

    driveCommand = new DriveCommand(m_driveSubsystem, () -> xboxController.getY(Hand.kLeft), () -> xboxController.getY(Hand.kRight));
    m_driveSubsystem.setDefaultCommand(driveCommand);

    shooterOffCommand = new ShooterOff(m_shooterSubsystem); 
    shooterOnCommand = new ShooterOn(m_shooterSubsystem);
    m_shooterSubsystem.setDefaultCommand(shooterOffCommand); 
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final Button a = new JoystickButton(xboxController, XboxController.Button.kA.value);
    final Button b = new JoystickButton(xboxController, XboxController.Button.kB.value);

    a.whenPressed(new ShooterOn(m_shooterSubsystem));
    b.whenPressed(new ShooterOff(m_shooterSubsystem));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
