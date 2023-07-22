// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDrive extends SubsystemBase {
  private final double L = 22 + (3 / 16);//in inches must be measured axel to axel
  private final double W = 21 + (13 / 16);
  private double r;


  private SwervePod pod1;
  private SwervePod pod2;
  private SwervePod pod3;
  private SwervePod pod4;

  private int whichPod;

  /** Creates a new SwerveDrive. */
  public SwerveDrive() {
    pod1 = new SwervePod(1 , false);
    pod2 = new SwervePod(2 , false);
    pod3 = new SwervePod(3 , false);
    pod4 = new SwervePod(4 , false);

    whichPod = 1;

    r = Math.sqrt((L * L) + (W * W));
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public SwervePod getPod(int pod){
    switch(pod){
      case 1:
        return pod1;
      case 2:
        return pod2;
      case 3:
        return pod3;
       case 4:
        return pod4;
      default:
        return null;
      }
  }

  public void podDriver(double drive , double turn){
    switch(whichPod){
      case 1:
        pod1.drivePod(drive);
        pod1.turnPod(turn);
        break;
      case 2:
        pod2.drivePod(drive);
        pod2.turnPod(turn);
        break;
      case 3:
        pod3.drivePod(drive);
        pod3.turnPod(turn);
        break;
      case 4:
        pod4.drivePod(drive);
        pod4.turnPod(turn);
        break;
    }//end switch
  }

  public void switcher(int whichPod){
    this.whichPod = whichPod;
  }

  public void controllerTest(double x1 , double y1 , double x2){
    if(Math.abs(x1) <= .1){
      x1 = 0;
    }
    if(Math.abs(y1) <= .1){
      y1 = 0;
    }
    if(Math.abs(x2) <= .1){
      x2 = 0;
    }

    double a = x1 - x2 * (L / r);
    double b = x1 + x2 * (L / r);
    double c = y1 - x2 * (W / r);
    double d = y1 + x2 * (W / r);

    double backRightSpeed = Math.sqrt ((a * a) + (d * d));
    double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
    double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
    double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));

    double backRightAngle = (Math.atan2 (a, d) / Math.PI) * 180;
    double backLeftAngle = (Math.atan2 (a, c) / Math.PI) * 180;
    double frontRightAngle = (Math.atan2 (b, d) / Math.PI) * 180;
    double frontLeftAngle = (Math.atan2 (b, c) / Math.PI) * 180;

    SmartDashboard.putNumber("BR Speed", backRightSpeed);
    SmartDashboard.putNumber("BR Angle", backRightAngle);
    SmartDashboard.putNumber("BL Speed", backLeftSpeed);
    SmartDashboard.putNumber("BL Angle", backLeftAngle);
    SmartDashboard.putNumber("FR Speed", frontRightSpeed);
    SmartDashboard.putNumber("FR Angle", frontRightAngle);
    SmartDashboard.putNumber("FL Speed", frontLeftSpeed);
    SmartDashboard.putNumber("FL Angle", frontLeftAngle);
    SmartDashboard.putNumber("x1", x1);
    SmartDashboard.putNumber("y1", y1);
    SmartDashboard.putNumber("x2", x2);

    /*backRight.drive (backRightSpeed, backRightAngle);
    backLeft.drive (backLeftSpeed, backLeftAngle);
    frontRight.drive (frontRightSpeed, frontRightAngle);
    frontLeft.drive (frontLeftSpeed, frontLeftAngle);*/

  }

  public void stop(){
    pod1.drivePod(0);
    pod1.turnPod(0);

  }
}
