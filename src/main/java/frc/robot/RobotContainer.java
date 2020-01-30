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
import edu.wpi.first.wpilibj.Controller;
import frc.robot.commands.ColorWheelDeploy;
import frc.robot.commands.ConveyorIn;
import frc.robot.commands.ConveyorOut;
import frc.robot.commands.ConveyorStop;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeDeploy;
import frc.robot.commands.ShooterOff;
import frc.robot.commands.ShooterOn;
import frc.robot.commands.winchLockOn;
import frc.robot.commands.LifterDown;
import frc.robot.commands.LifterStop;
import frc.robot.commands.LifterUp;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HookSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterPidSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import com.analog.adis16448.frc.ADIS16448_IMU ;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.IntakeOn;
import frc.robot.commands.IntakeOff;
import edu.wpi.first.wpilibj.Joystick;

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
  private final ShooterPidSubsystem m_shooterpid = new ShooterPidSubsystem();
  // private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final HookSubsystem m_hookSubsystem = new HookSubsystem(); 
  private final LiftSubsystem m_liftSubsystem = new LiftSubsystem();
   private final ColorWheelSubsystem m_colorWheelSubsystem = new ColorWheelSubsystem();
  // and commands are defined here...
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveCommand driveCommand;
  // private final ShooterOn shooterOnCommand;
  // private final ShooterOff shooterOffCommand;
  private final XboxController xboxController = new XboxController(k_xboxController);
  private final Joystick BoxController = new Joystick(k_boxController);
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
    m_shooterpid.register();

    driveCommand = new DriveCommand(m_driveSubsystem, () -> xboxController.getY(Hand.kLeft), () -> xboxController.getY(Hand.kRight));
    m_driveSubsystem.setDefaultCommand(driveCommand);

    // shooterOffCommand = new ShooterOff(m_shooterpid); 
    // shooterOnCommand = new ShooterOn(m_shooterpid);
    // m_shooterpid.setDefaultCommand(shooterOffCommand); 
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // final Button a = new JoystickButton(xboxController, XboxController.Button.kA.value);
    // final Button b = new JoystickButton(xboxController, XboxController.Button.kB.value);
    // final Button x = new JoystickButton(xboxController, XboxController.Button.kX.value);
    // final Button y = new JoystickButton(xboxController, XboxController.Button.kY.value);
    // final Button z = new JoystickButton(BoxController, 1);
    // final Button conveyorOut = new JoystickButton(BoxController, 2);
    // final Button conveyorIn = new JoystickButton(BoxController, 3);
    // final Button conveyorStop = new JoystickButton(BoxController, 4);

    //  x.whenPressed(new IntakeOn(m_intakeSubsystem));
    //  y.whenPressed(new IntakeOff(m_intakeSubsystem));
    //  z.whenPressed(new IntakeDeploy(m_intakeSubsystem));
    //  conveyorOut.whenPressed(new ConveyorIn(m_conveyourSubsystem));
    //  conveyorIn.whenPressed(new ConveyorOut(m_conveyourSubsystem));
    //  conveyorStop.whenPressed(new ConveyorStop(m_conveyourSubsystem));
     
    //     //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Shooter

    // new JoystickButton(xboxController, XboxController.Button.kA.value)
    //     .whenPressed(new InstantCommand(m_shooterpid::enable, m_shooterpid));

    // new JoystickButton(xboxController, XboxController.Button.kB.value)
    //     .whenPressed(new InstantCommand(m_shooterpid::disable, m_shooterpid));   

        //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Shooter

    new JoystickButton(BoxController, 6)
        .whenPressed(new InstantCommand(m_shooterpid::enable, m_shooterpid)); 

    new JoystickButton(BoxController, 6)
        .whenReleased(new InstantCommand(m_shooterpid::disable, m_shooterpid)); 

        //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Lifter

    new JoystickButton(BoxController, 1)
        .whenPressed(new LifterDown(m_liftSubsystem)); 

    new JoystickButton(BoxController, 1)
        .whenReleased(new LifterStop(m_liftSubsystem));

        //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Lifter

    new JoystickButton(BoxController, 2)
        .whenPressed(new LifterUp(m_liftSubsystem)); 

    new JoystickButton(BoxController, 2)
        .whenReleased(new LifterStop(m_liftSubsystem)); 

       //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Intake

    new JoystickButton(BoxController, 3)
        .whenPressed(new IntakeOn(m_intakeSubsystem)); 

    new JoystickButton(BoxController, 3)
        .whenReleased(new IntakeOff(m_intakeSubsystem)); 

      //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ conveyor

    new JoystickButton(BoxController, 4)
        .whenPressed(new ConveyorIn(m_conveyourSubsystem)); 

    new JoystickButton(BoxController, 4)
        .whenReleased(new ConveyorStop(m_conveyourSubsystem)); 

      //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ IntakeDeploy
      
    new JoystickButton(BoxController, 5)
      .whenPressed(new IntakeDeploy(m_intakeSubsystem)); 

      //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ the god color wheel deploy

    new JoystickButton(BoxController, 12)
      .whenPressed(new ColorWheelDeploy(m_colorWheelSubsystem)); 

      //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
      
    new JoystickButton(xboxController, XboxController.Button.kY.value)
      .whenPressed(new winchLockOn(m_liftSubsystem)); 
    
      //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

    // a.whenPressed(new ShooterOn(m_shooterSubsystem));
    // b.whenPressed(new ShooterOff(m_shooterSubsystem));
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
