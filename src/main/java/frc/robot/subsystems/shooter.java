// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.shootConstand;

public class shooter extends SubsystemBase {
  /** Creates a new shooter. */



private final TalonFX shootMotorL;
private final TalonFX shootMotorR;

private final TalonFXConfiguration shootMotorConfigL;
private final TalonFXConfiguration shootMotorConfigR;

private double LeftshootSpeed;
private double RightshootSpeed;
private boolean tempsoHigh;

  public shooter() {
    shootMotorL = new TalonFX(shootConstand.shootMotorL);
    shootMotorR = new TalonFX(shootConstand.shootMotorR);

    shootMotorConfigL = new TalonFXConfiguration();
    shootMotorConfigR = new TalonFXConfiguration();

    shootMotorConfigL.CurrentLimits.SupplyCurrentLimit = shootConstand.MaxOutput;
    shootMotorConfigR.CurrentLimits.SupplyCurrentLimit = shootConstand.MaxOutput;
    shootMotorConfigL.CurrentLimits.SupplyCurrentLimitEnable = true;
    shootMotorConfigR.CurrentLimits.SupplyCurrentLimitEnable = true;
    shootMotorConfigL.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    shootMotorConfigR.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    shootMotorConfigL.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    shootMotorConfigR.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    shootMotorL.getConfigurator().apply(shootMotorConfigL);
    shootMotorR.getConfigurator().apply(shootMotorConfigR);

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    tempsoHigh = getLeftShootTemp() > 60 || getRightShootTemp() > 60;
    if(tempsoHigh) shootMotorL.stopMotor();
    else shootMotorL.setVoltage(LeftshootSpeed);

    if(tempsoHigh) shootMotorR.stopMotor();
    else shootMotorR.setVoltage(RightshootSpeed);
  }
  public void strongshoot(){
    LeftshootSpeed = 7;
    RightshootSpeed = 7;
  }
  public void weakshoot(){
    LeftshootSpeed = 6;
    RightshootSpeed = 3;
  }
  public void shootStop(){
    LeftshootSpeed = 0;
    RightshootSpeed = 0;
    shootMotorL.stopMotor();
    shootMotorR.stopMotor();
  }
  public double getRightShootTemp(){
    return shootMotorR.getDeviceTemp().getValueAsDouble();
  }
public double getLeftShootTemp(){
    return shootMotorL.getDeviceTemp().getValueAsDouble();
  }

}

