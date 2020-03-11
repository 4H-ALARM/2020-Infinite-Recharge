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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ColorWheelDeploy;
import frc.robot.commands.ColorWheelDeployIn;
import frc.robot.commands.ColorWheelSpinIn;
import frc.robot.commands.ColorWheelStop;
import frc.robot.commands.ColorWheelTurn3To5;
import frc.robot.commands.ConveyorIn;
import frc.robot.commands.ConveyorOut;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.AutoCommandDS;
import frc.robot.commands.ConveyorStop;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeDeploy;
import frc.robot.commands.winchLockOn;
import frc.robot.commands.LifterDown;
import frc.robot.commands.LifterStop;
import frc.robot.commands.LifterUp;
import frc.robot.commands.winchLockOff;
import frc.robot.commands.FeedShooter;
import frc.robot.commands.HookDriveCommand;
import frc.robot.commands.StopFeedingShooter;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HookSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterPidSubsystem;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.IntakeOn;
import frc.robot.commands.IntakeReverse;
import frc.robot.commands.IntakeOff;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.IntakeOffReverse;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Constants.*;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
 public static final AutoCommand AutoCommand = null;
 // The robot's subsystems here..
 private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
 private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
 private final ConveyorSubsystem m_conveyourSubsystem = new ConveyorSubsystem();
 private final ShooterPidSubsystem m_shooterpid = new ShooterPidSubsystem();
 private final HookSubsystem m_hookSubsystem = new HookSubsystem();
 private final LiftSubsystem m_liftSubsystem = new LiftSubsystem();
 private final ColorWheelSubsystem m_colorWheelSubsystem = new ColorWheelSubsystem();

 public  SequentialCommandGroup m_AutoCommand = null;
 private final AutoCommand m_DriveAndShootAuto = new AutoCommand(m_driveSubsystem, m_shooterpid);
 private final AutoCommandDS m_DriveStraightAuto = new AutoCommandDS(m_driveSubsystem);

 public String m_autoSelectionName = "NONE";

 // and commands are defined here...
 // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
 private final DriveCommand driveCommand;
 private final HookDriveCommand hookDriveCommand;
 // controllers
 private final XboxController xboxController = new XboxController(k_xboxController);
 private final Joystick BoxController = new Joystick(k_boxController);

 // make camera, imu  and compressor public parts of the robtot to be run from 
 // the robot class rather than a seperate subsystem
 public final Compressor m_compressor = new Compressor(k_PCMModule);
 // public static final ADIS16448_IMU m_imu = new ADIS16448_IMU();

 private boolean m_useBox = k_useBox; // start by assuming using control box rather than Logitech
 public final SendableChooser < String > m_controllerChooser = new SendableChooser < > ();
 public final SendableChooser < String > m_AutoChooser = new SendableChooser < > ();

 /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
 public RobotContainer() {
  // Configure the button bindings
  configureButtonBindings();

  // set up the Autonomous selector
  m_AutoChooser.setDefaultOption("NONE", "NONE");
  m_AutoChooser.addOption("DRIVE", m_DriveStraightAuto.getName());
  m_AutoChooser.addOption("DRIVE+SHOOT", m_DriveAndShootAuto.getName());
  SmartDashboard.putData("Autonomous Selection", m_AutoChooser);
  // get the initial autonomous selection. 
  // this will be updated again when auto init is called in the robot class
  m_AutoCommand = getAutonomousSelection();

  m_colorWheelSubsystem.register();
  m_shooterpid.register();
  m_hookSubsystem.register();

  // tell the shooter and intake about the coveyor to use
  m_shooterpid.setConveyor(m_conveyourSubsystem);
  m_intakeSubsystem.setConveyor(m_conveyourSubsystem);

  driveCommand = new DriveCommand(m_driveSubsystem, () -> xboxController.getY(Hand.kLeft), () -> xboxController.getY(Hand.kRight));
  m_driveSubsystem.setDefaultCommand(driveCommand);

  hookDriveCommand = new HookDriveCommand(m_hookSubsystem, () -> BoxController.getY());
  m_hookSubsystem.setDefaultCommand(hookDriveCommand);

 }

 /**
  * Use this method to define your button->command mappings.  Buttons can be created by
  * instantiating a {@link GenericHID} or one of its subclasses ({@link
  * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
  * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
  */
 private void configureButtonBindings() {
  setXboxButtons();
  // chooseController();  don't call as it does not do a valid selection  TODO fix if possible
  if (m_useBox) { // our control box
  //  SmartDashboard.putString("Controler Selected", "ALARM BOX");
   setBoxButtons(); // or
  } else { // logitech
  //  SmartDashboard.putString("Controler Selected", "Logitech");
   setJoystickButtons();
  }
 }

 private void setXboxButtons() {
  new JoystickButton(xboxController, XboxController.Button.kY.value)
   .whenPressed(new winchLockOn(m_liftSubsystem));

  new JoystickButton(xboxController, XboxController.Button.kA.value)
   .whenPressed(new winchLockOff(m_liftSubsystem));
 }

 private void setBoxButtons() {
  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Shooter toggle on/off

  new JoystickButton(BoxController, 7)
   .whenPressed(new InstantCommand(m_shooterpid::toggle, m_shooterpid));
  new JoystickButton(BoxController, 8)
   .whenPressed(new InstantCommand(m_shooterpid::disable, m_shooterpid));

  new JoystickButton(BoxController, 6)
   .whenPressed(new FeedShooter(m_shooterpid));
  new JoystickButton(BoxController, 6)
   .whenReleased(new StopFeedingShooter(m_shooterpid));

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Lifter

  new JoystickButton(BoxController, 4)
   .whenPressed(new LifterDown(m_liftSubsystem));
  new JoystickButton(BoxController, 4)
   .whenReleased(new LifterStop(m_liftSubsystem));

  new JoystickButton(BoxController, 3)
   .whenPressed(new LifterUp(m_liftSubsystem));
  new JoystickButton(BoxController, 3)
   .whenReleased(new LifterStop(m_liftSubsystem));

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Intake
  new JoystickButton(BoxController, 9)
   .whenPressed(m_intakeSubsystem::toggle, m_intakeSubsystem);
  new JoystickButton(BoxController, 10)
   .whenPressed(m_intakeSubsystem::toggleWithConvayor, m_intakeSubsystem);
  new JoystickButton(BoxController, 2)
   .whenPressed(new IntakeReverse(m_intakeSubsystem));
   new JoystickButton(BoxController, 2)
   .whenReleased(new IntakeOffReverse(m_intakeSubsystem));

  // new JoystickButton(BoxController, 3)
  //     .whenPressed(new IntakeOff(m_intakeSubsystem));
  // new JoystickButton(BoxController, 3)
  //     .whenReleased(new IntakeOff(m_intakeSubsystem)); 

  // new JoystickButton(BoxController, 5)
  //     .whenPressed(new IntakeDeploy(m_intakeSubsystem));
  // new JoystickButton(BoxController, 5)
  //     .whenReleased(new IntakeOff(m_intakeSubsystem)); 

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ conveyor

  new JoystickButton(BoxController, 5)
   .whenPressed(new ConveyorIn(m_conveyourSubsystem));
  new JoystickButton(BoxController, 5)
   .whenReleased(new ConveyorStop(m_conveyourSubsystem));

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ the god color wheel deploy

  new JoystickButton(BoxController, 12)
   .whenPressed(new ColorWheelSpinIn(m_colorWheelSubsystem));
  new JoystickButton(BoxController, 12)
  .whenReleased(new ColorWheelStop(m_colorWheelSubsystem));

  new JoystickButton(BoxController, 11)
   .whenPressed(new ColorWheelTurn3To5(m_colorWheelSubsystem));
  new JoystickButton(BoxController, 11)
   .whenReleased(new ColorWheelStop(m_colorWheelSubsystem));
 }

 private void setJoystickButtons() {
  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Shooter

  // Shooter is toggled by the trigger button.
  new JoystickButton(BoxController, 1)
   .whenPressed(new InstantCommand(m_shooterpid::toggle, m_shooterpid));

  // Feed is enabled when button 3 is pressed down.
  // The conveyor will always run in while button 3 is pressed down.
  new JoystickButton(BoxController, 3)
   .whenPressed(new FeedShooter(m_shooterpid));
  new JoystickButton(BoxController, 3)
   .whenReleased(new StopFeedingShooter(m_shooterpid));

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Lifter

  new JoystickButton(BoxController, 2) // TODO
   .whenPressed(new LifterDown(m_liftSubsystem));
  new JoystickButton(BoxController, 2) // TODO
   .whenReleased(new LifterStop(m_liftSubsystem));

  new JoystickButton(BoxController, 3) // TODO
   .whenPressed(new LifterUp(m_liftSubsystem));
  new JoystickButton(BoxController, 3) // TODO
   .whenReleased(new LifterStop(m_liftSubsystem));

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ Intake
  new JoystickButton(BoxController, 6)
   .whenPressed(m_intakeSubsystem::toggle, m_intakeSubsystem);

  new JoystickButton(BoxController, 10)
   .whenPressed(m_intakeSubsystem::toggleWithConvayor, m_intakeSubsystem);


  // new JoystickButton(BoxController, 6)
  //     .whenPressed(new IntakeDeploy(m_intakeSubsystem));
  // new JoystickButton(BoxController, 6) // TODO
  //     .whenReleased(new IntakeOff(m_intakeSubsystem)); 

  // new JoystickButton(BoxController, 7)
  //     .whenPressed(new IntakeOn(m_intakeSubsystem)); 
  // new JoystickButton(BoxController, 7)
  //     .whenReleased(new IntakeOff(m_intakeSubsystem)); 

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ the god color wheel deploy

  new JoystickButton(BoxController, 8) // TODO
   .whenPressed(new ColorWheelSpinIn(m_colorWheelSubsystem));
  new JoystickButton(BoxController, 8) // TODO
   .whenReleased(new ColorWheelStop(m_colorWheelSubsystem));


  new JoystickButton(BoxController, 4) // TODO
   .whenPressed(new ColorWheelDeploy(m_colorWheelSubsystem));
  // new JoystickButton(BoxController, 4) // TODO
  //   .whenReleased(new ColorWheelDeployIn(m_colorWheelSubsystem));

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\ conveyor

  // The conveyor can be ran out by pressing button 2.
  new JoystickButton(BoxController, 5)
   .whenPressed(new ConveyorOut(m_conveyourSubsystem));
  new JoystickButton(BoxController, 5)
   .whenReleased(new ConveyorStop(m_conveyourSubsystem));

 }

 private void chooseController() {
  m_controllerChooser.setDefaultOption("ALARM BOX", "ALARM BOX");
  m_controllerChooser.addOption("Logitech", "Logitech");
  SmartDashboard.putData("Controller", m_controllerChooser);

  if (m_controllerChooser.getSelected() == "Logitech") {
   m_useBox = false;
  } else {
   m_useBox = true;
  }
 }

 /**
  * Use this to pass the autonomous command to the main {@link Robot} class.
  *
  * @return the command to run in autonomous
  */
 public SequentialCommandGroup getAutonomousSelection() {
  if (m_AutoChooser.getSelected() == m_DriveStraightAuto.getName()) {
   m_autoSelectionName = m_DriveStraightAuto.getName();
   m_AutoCommand = m_DriveStraightAuto;
  } else if (m_AutoChooser.getSelected() == m_DriveAndShootAuto.getName()) {
    m_autoSelectionName = m_DriveAndShootAuto.getName();
   m_AutoCommand = m_DriveAndShootAuto;
  } else {
    m_autoSelectionName = "NONE";
   m_AutoCommand = null;
  }
  return m_AutoCommand;
 }


}