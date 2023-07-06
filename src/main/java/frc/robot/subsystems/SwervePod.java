// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwervePod extends SubsystemBase {
  private CANSparkMax driveMotor;
  private CANSparkMax swerveMotor;
  private CANCoder dirEnc;
  private RelativeEncoder velEnc;

  private int podID;
  /** Creates a new SwervePod. */
  public SwervePod(int driveID , int swerveID, int dirID) {
    driveMotor = new CANSparkMax(driveID, MotorType.kBrushless);
    swerveMotor = new CANSparkMax(swerveID, MotorType.kBrushless);
    dirEnc = new CANCoder(dirID);
    velEnc = driveMotor.getEncoder();

    podID = driveID;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Speed" + podID, velEnc.getVelocity());
    SmartDashboard.putNumber("Distance" + podID, velEnc.getPosition());
    // This method will be called once per scheduler run
  }

  
}
