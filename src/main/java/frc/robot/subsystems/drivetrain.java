// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  //NO PUBLIC STATIC!!! 
  //NOTHING SHOULD BE STATIC UNLESS ITS SUPPOSED TO BE USED STATICALLY WHICH IS BASICALLY ONLY IN CONSTANTS. 
  //NO VARIABLES SHOULD BE PUBLIC. EVER. 
  private final WPI_TalonSRX leftMotor = new WPI_TalonSRX(Constants.leftDriveMotorPort);
  private final WPI_TalonSRX leftFollow = new WPI_TalonSRX(Constants.leftFollowMotorPort);
  private final WPI_TalonSRX rightMotor = new WPI_TalonSRX(Constants.rightDriveMotorPort);
  private final WPI_TalonSRX rightFollow = new WPI_TalonSRX(Constants.rightFollowMotorPort);
  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    leftFollow.follow(leftMotor);
    rightMotor.follow(rightFollow);

    //depending on gearboxes, motors could end up fighting. if so, change.
    leftFollow.setInverted(false);
    rightMotor.setInverted(false);
  }

  /**
   * Tank Drive - Send raw values to the DriveTrain, no differential mechanics involved, Useful for autonomous
   * @param left - Power to send to the left side of the drive train, -1 - 1
   * @param right - Power to send to the riht side of the drive train, -1 - 1
   */
  public void tankDrive(double left, double right) {
    leftMotor.set(left);
  rightMotor.set(right);
 }

 //!!! subsystems should only interface with the hardware, the logic/math should be left to commands. !!!

//  /**
//   * Arcade Drive - Accepts joystick inputs, Takes the joystick's forward and backward, left and right and converts it into a percentage and passes it to the motors giving beter control to the driver
//   * @param stickfb
//   * @param sticklr
//   */
//  public void arcadedrive(double stickfb, double sticklr) {
  
  //   rightMotor.set(sticklr - stickfb);
  //   rightFollow.set(sticklr - stickfb);
  //   leftMotor.set(stickfb + sticklr);
  //   leftFollow.set(stickfb + sticklr);
  
  //  }
  
  
  /**
   * Sets all motors to 0% output
   */
  public void stopAll() {
    rightMotor.set(0);
    leftMotor.set(0);
    leftFollow.set(0);
    rightFollow.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Speed:", leftMotor.get());
    SmartDashboard.putNumber("Right Speed:", rightMotor.get());
    //Puts the speed being sent to the motors on the dashboard, helpful for diagnostics
  }
}
