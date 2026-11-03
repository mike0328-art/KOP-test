// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ManualSystem;
import frc.robot.subsystems.shooter;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ShootCommd extends Command {
  private final shooter m_Shooter;
  private final BooleanSupplier A;
  private final BooleanSupplier Y;

  private final BooleanSupplier lefttriggerFunc;

  private boolean apush;
  private boolean ypush;
  private boolean leftpush;

  /** Creates a new ShootCommd. */
  public ShootCommd(shooter m_Shooter, BooleanSupplier A, BooleanSupplier Y, BooleanSupplier lefttriggerFunc) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_Shooter = m_Shooter;
    this.A = A;
    this.Y = Y;
    this.lefttriggerFunc = lefttriggerFunc;
    addRequirements(m_Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    apush = A.getAsBoolean();
    ypush = Y.getAsBoolean();
    leftpush = lefttriggerFunc.getAsBoolean();
    if (apush) {
      m_Shooter.AAshoot();
    } else if (ypush) {
      m_Shooter.BBshoo();
    } else {
      m_Shooter.strongshoot();
    }
    if(leftpush){
      m_Shooter.intake();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Shooter.shootStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
