// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDrive extends SubsystemBase {
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
}
