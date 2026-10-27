// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.lang.management.MemoryType;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class ManualSystem extends SubsystemBase {
  /** Creates a new ManualSystem. */
  private final SparkMax LFMotor;
  private final SparkMax LBMotor;
  private final SparkMax RBMotor;
  private final SparkMax RFMotor;

    private final SparkMaxConfig LFConfig;
    private final SparkMaxConfig LBConfig;
    private final SparkMaxConfig RFConfig;
    private final SparkMaxConfig RBConfig;

    private final DifferentialDrive drive;

  public ManualSystem() {
    LBMotor = new SparkMax(0, MotorType.kBrushless);
    LFMotor = new SparkMax(0, MotorType.kBrushless);
    RBMotor = new SparkMax(0, MotorType.kBrushless);
    RFMotor = new SparkMax(0, MotorType.kBrushless);

    LFConfig = new SparkMaxConfig();
    LBConfig = new SparkMaxConfig();
    RBConfig = new SparkMaxConfig();
    RFConfig = new SparkMaxConfig();

    drive = new DifferentialDrive(LFMotor,RFMotor);

    LFConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    LBConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60).follow(LFMotor.getDeviceId());
    RFConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    RBConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60).follow(RFMotor.getDeviceId());

    LFMotor.configure(LBConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    LBMotor.configure(LBConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    RFMotor.configure(LBConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    RBMotor.configure(LBConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

  }

  public void manualSystem(double xspeed,double turnspeed){
    drive.arcadeDrive(xspeed, turnspeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
