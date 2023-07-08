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

  private final int SPARE_POD_ENCODER_ID = 25;

  private double[] swerveOffsets = {-17,0,0,0,0};

  private int podID;
  private boolean usingSpare;
  /** Creates a new SwervePod. */
  public SwervePod(int driveID , boolean usingSpare) {
    driveMotor = new CANSparkMax(driveID, MotorType.kBrushless);
    swerveMotor = new CANSparkMax(driveID + 10, MotorType.kBrushless);
    if(usingSpare){
      dirEnc = new CANCoder(SPARE_POD_ENCODER_ID);
    }
    else{
      dirEnc = new CANCoder(driveID + 20);
    }

    velEnc = driveMotor.getEncoder();
    //uncomment when switching to real units instead of rotations and rpm
    //velEnc.setPositionConversionFactor(((4/12) * Math.PI) / (8.14)); //circumference for 4" wheel divided by 12" to a foot / gear ratio * -> feet
    //velEnc.setVelocityConversionFactor(((4/12) * Math.PI) / (8.14 * 60));//circumference for 4" wheel divided by 12" to a foot / gear ratio * convert to seconds -> feet per second

    podID = driveID;
    this.usingSpare = usingSpare;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Speed" + podID, getSpeed());
    SmartDashboard.putNumber("Distance" + podID, getDistance());
    SmartDashboard.putNumber("Angle" + podID, getAngle());
    // This method will be called once per scheduler run
  }

  public double getSpeed(){
    return velEnc.getVelocity() / 8.14;
  }

  public double getDistance(){
    return velEnc.getPosition() / 8.14;
  }

  public double getAngle(){
    double angle = dirEnc.getAbsolutePosition();
    if(angle > 180){
      angle -= 360;
    }
    double offset = swerveOffsets[podID - 1];//compensate for starting at 0
    if(usingSpare){
      offset = swerveOffsets[4];//counts from 0
    }
    return angle + offset;
  }

  public void turnPod(double turn){
    if(Math.abs(turn) > .1){
      swerveMotor.set(turn);
    }
    else{
      swerveMotor.set(0);
    }
    
  }

  public void drivePod(double drive){
    if(Math.abs(drive) > .1){
      driveMotor.set(drive);
    }
    else{
      driveMotor.set(0);
    }
  }

  
}
