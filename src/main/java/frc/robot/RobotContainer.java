// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ManualDrive;
import frc.robot.commands.ShootCommd;
import frc.robot.subsystems.ManualSystem;
import frc.robot.subsystems.shooter;

import java.util.function.BooleanSupplier;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final CommandXboxController driveController = new CommandXboxController(0);
  private final ManualSystem m_ManualSystem = new ManualSystem();
  private final shooter m_Shooter = new shooter();
  
  
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_ManualSystem.setDefaultCommand(new ManualDrive(
      m_ManualSystem,
      ()-> -driveController.getLeftY(),
      ()-> driveController.getRightX()));
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasse%s for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    BooleanSupplier ypush = () -> driveController.y().getAsBoolean();
    BooleanSupplier apush = () -> driveController.a().getAsBoolean();
    BooleanSupplier leftpush = () -> driveController.leftTrigger(0.5).getAsBoolean();
    driveController.leftTrigger(0.5).whileTrue(new ShootCommd(m_Shooter, apush, ypush, leftpush));
    driveController.rightTrigger(0.5).whileTrue(new ShootCommd(m_Shooter, apush, ypush, leftpush));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Commands.runEnd(()->m_ManualSystem.manualSystem(0.5, 0), ()->m_ManualSystem.manualSystem(0.0, 0), m_ManualSystem).withTimeout(2.5);
  }
}
