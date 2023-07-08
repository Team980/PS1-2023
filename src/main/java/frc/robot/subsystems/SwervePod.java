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
    //uncomment when switching to real units instead of rotations and rpm
    //velEnc.setPositionConversionFactor(((4/12) * Math.PI) / (8.14)); //circumference for 4" wheel divided by 12" to a foot / gear ratio * -> feet
    //velEnc.setVelocityConversionFactor(((4/12) * Math.PI) / (8.14 * 60));//circumference for 4" wheel divided by 12" to a foot / gear ratio * convert to seconds -> feet per second

    podID = driveID;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Speed" + podID, getSpeed());
    SmartDashboard.putNumber("Distance" + podID, getDistance());
    // This method will be called once per scheduler run
  }

  public double getSpeed(){
    return velEnc.getVelocity();
  }

  public double getDistance(){
    return velEnc.getPosition();
  }

  
}
