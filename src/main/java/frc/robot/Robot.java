/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import static frc.robot.Constants.*;

/*
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 private final PowerDistributionPanel m_pdp = new PowerDistributionPanel(0);
 private SequentialCommandGroup m_autonomousCommand = null;
 private RobotContainer m_robotContainer;


 /**
  * This function is run when the robot is first started up and should be used for any
  * initialization code.
  */
 @Override
 public void robotInit() {
  // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
  // autonomous chooser on the dashboard.
  m_robotContainer = new RobotContainer();
  m_robotContainer.m_compressor.setClosedLoopControl(true);
  m_robotContainer.m_compressor.enabled();

  // initialize subsystems are done inside subsystem on creation from the container

  // ----------Set up Camera HD3000----------2 options//
  // 
  CameraServer.getInstance().startAutomaticCapture(); //OPT #1
  CameraServer.getInstance().startAutomaticCapture();
  // OPT #2
  //   new Thread(() -> {
  //     UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
  //     camera.setResolution(1280, 960);
  //     CvSink cvSink = CameraServer.getInstance().getVideo();
  //     CvSource outputStream = CameraServer.getInstance().putVideo("Camera Test", 640, 480);

  // Mat source = new Mat();
  // Mat output = new Mat();

  // while (!Thread.interrupted()) {
  //     if (cvSink.grabFrame(source) == 0) {
  //             continue;
  //         }
  //         Imgproc.cvtColor(source, output, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C);
  //         outputStream.putFrame(output);
  //     }
  // }).start();

 }

 /**
  * This function is called every robot packet, no matter the mode. Use this for items like
  * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
  *
  * <p>This runs after the mode specific periodic functions, but before
  * LiveWindow and SmartDashboard integrated updating.
  */
 @Override
 public void robotPeriodic() {
  // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
  // commands, running already-scheduled commands, removing finished or interrupted commands,
  // and running subsystem periodic() methods.  This must be called from the robot's periodic
  // block in order for anything in the Command-based framework to work.
  CommandScheduler.getInstance().run();

  readPDP();
 }

 /**
  * This function is called once each time the robot enters Disabled mode.
  */
 @Override
 public void disabledInit() {}

 @Override
 public void disabledPeriodic() {}

 /**
  * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
  */
 @Override
 public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousSelection();
  // schedule the autonomous command
  if (m_autonomousCommand != null) {
   m_autonomousCommand.schedule();
  }
 }

 /**
  * This function is called periodically during autonomous.
  */
 @Override
 public void autonomousPeriodic() {

 }

 @Override
 public void teleopInit() {
  // This makes sure that the autonomous stops running when
  // teleop starts running. If you want the autonomous to
  // continue until interrupted by another command, remove
  // this line or comment it out.
  if (m_autonomousCommand != null) {
   m_autonomousCommand.cancel();
  }
 }

 /**
  * This function is called periodically during operator control.
  */
 @Override
 public void teleopPeriodic() {

 }

 @Override
 public void testInit() {
  // Cancels all running commands at the start of test mode.
  CommandScheduler.getInstance().cancelAll();
 }

 /**
  * This function is called periodically during test mode.
  */
 @Override
 public void testPeriodic() {}

  public void readPDP() {
  SmartDashboard.putNumber("Hook Current", m_pdp.getCurrent(k_hookMotorPDP));
  SmartDashboard.putNumber("Lift Current", m_pdp.getCurrent(k_liftMotorPDP));
  SmartDashboard.putNumber("Shooter Current", m_pdp.getCurrent(k_ShooterMotorPDP));
  SmartDashboard.putNumber("Feed Current", m_pdp.getCurrent(k_ShooterInPDP));
  SmartDashboard.putNumber("Conv Current", m_pdp.getCurrent(k_BeltConveyorPDP));
  SmartDashboard.putNumber("Intake Current", m_pdp.getCurrent(k_ballIntakeMotorPDP));
  SmartDashboard.putNumber("Color Current", m_pdp.getCurrent(k_ColorWheelMotorPDP));
  SmartDashboard.putNumber("RR Current", m_pdp.getCurrent(k_rightRearDrivePDP));
  SmartDashboard.putNumber("RF Current", m_pdp.getCurrent(k_rightFrontDrivePDP));
  SmartDashboard.putNumber("LR Current", m_pdp.getCurrent(k_leftRearDrivePDP));
  SmartDashboard.putNumber("LF Current", m_pdp.getCurrent(k_leftFrontDrivePDP));
 }

}