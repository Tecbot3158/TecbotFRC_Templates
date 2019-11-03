/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.resources.TecbotConstants;
import frc.robot.resources.TecbotEncoder;

/*
This class is intended to make the robot move an exact distance without using PID control,
this is done using a control system that starts reducing the speed after a given point until it arrives to target
*/

public class ChassisMoveDistanceWithSpeedReduction extends Command {

  boolean isUsingGyro;

  float startReducingSpeedPoint;
  float tolerance;
  float deltaEncoderCount;
  float targetPower;
  float targetAngle;
  float targetEncoderCount;
  float deltaDistance;

  TecbotEncoder wheelEncoder;

  /**
   * @param encoder                   The encoder that will have de control system
   * @param m_isLeft                  Set true if the side to control is left side
   * @param m_startReducingSpeedPoint The point where it will start reducing speed
   * @param m_tolerance               The distance to the objective to be
   *                                  considered on target
   * @param d                         The distance that it is aiming to move
   * @param m_targetPower             The max power
   */
  public ChassisMoveDistanceWithSpeedReduction(TecbotEncoder encoder, float m_startReducingSpeedPoint,
      float m_tolerance, double d, float m_targetPower) {

    requires(Robot.driveTrain);
    startReducingSpeedPoint = m_startReducingSpeedPoint;
    tolerance = m_tolerance;
    deltaEncoderCount = (float) (d * TecbotConstants.K_METERS_TO_ENCODER);
    targetPower = m_targetPower;
    wheelEncoder = encoder;
    isUsingGyro = false;

  }

  /**
   * @param encoder                   The encoder that will have de control system
   * @param m_isLeft                  Set true if the side to control is left side
   * @param m_startReducingSpeedPoint The point where it will start reducing speed
   * @param m_tolerance               The distance to the objective to be
   *                                  considered on target
   * @param distance                  The distance that it is aiming to move
   * @param m_targetPower             The max power
   * @param m_targetAngle             The angle that the robot needs to be
   */

  public ChassisMoveDistanceWithSpeedReduction(TecbotEncoder encoder, float m_startReducingSpeedPoint,
      float m_tolerance, float distance, float m_targetPower, float m_targetAngle) {

    requires(Robot.driveTrain);
    startReducingSpeedPoint = m_startReducingSpeedPoint;
    tolerance = m_tolerance;
    deltaEncoderCount = distance * TecbotConstants.K_METERS_TO_ENCODER;
    targetPower = m_targetPower;
    targetAngle = m_targetAngle;
    wheelEncoder = encoder;
    isUsingGyro = true;

  }

  @Override
  protected void initialize() {
    wheelEncoder.reset();
    targetEncoderCount = deltaEncoderCount;
  }

  @Override
  protected void execute() {

    float power;
    deltaDistance = targetEncoderCount + wheelEncoder.getRaw();
    // If it is in the range and it needs to start reducing speed
    if (Math.abs(deltaDistance) < startReducingSpeedPoint) {
      power = ((deltaDistance / startReducingSpeedPoint) * targetPower);
    } else {
      power = (deltaDistance < 0 ? -1 : 1) * targetPower;
    }

    if (isUsingGyro) {
      power = (float) ((deltaDistance < 0 ? -1 : 1) * ((-Robot.tecbotgyro.getYaw() + targetAngle) / 360) * power);
    }

    Robot.driveTrain.driveSideLeft(-power);
    Robot.driveTrain.driveSideRight(power);
    SmartDashboard.putNumber("Alex Encoder", wheelEncoder.getRaw());
    SmartDashboard.putNumber("Alex target ", targetEncoderCount);
    System.out.println("Raw " + wheelEncoder.getRaw());
    System.out.println("delta " + deltaDistance);
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(deltaDistance) < tolerance);
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
