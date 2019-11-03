/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateChassisWithSpeedReduction extends Command {

  float targetPower;
  float targetAngle;
  float startReducingSpeedPoint;
  float tolerance;

  float deltaAngle;
  float power;

  boolean bestTurn;

  /**
   * 
   * @param m_targetAngle             The angle which the robot will be aiming to
   *                                  turn
   * @param targetPower2              The max power that will be given to the
   *                                  motors
   * @param m_startReducingSpeedPoint The point where it will start reducing speed
   * @param m_tolerance               The maximum distance to the target to be
   *                                  considered finished
   * @param m_bestTurn                Set this true to avoid robot turning more
   *                                  than 180°
   */
  public RotateChassisWithSpeedReduction(float m_targetAngle, float targetPower2, float m_startReducingSpeedPoint,
      float m_tolerance, boolean m_bestTurn) {

    requires(Robot.driveTrain);
    targetAngle = m_targetAngle;
    targetPower = targetPower2;
    startReducingSpeedPoint = m_startReducingSpeedPoint;
    tolerance = m_tolerance;
    bestTurn = m_bestTurn;

  }

  @Override
  protected void initialize() {
    calculateDeltaAngle();
  }

  @Override
  protected void execute() {
    deltaAngle = (float) (targetAngle - Robot.tecbotgyro.getYaw());
    if (Math.abs(deltaAngle) < startReducingSpeedPoint) {

      power = ((deltaAngle / startReducingSpeedPoint) * targetPower);

    } else {

      power = targetPower;

    }

    Robot.driveTrain.driveSideLeft(-power);
    Robot.driveTrain.driveSideRight(-power);
    System.out.println(deltaAngle);
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(deltaAngle) < tolerance);
  }

  @Override
  protected void end() {
    Robot.driveTrain.driveSideLeft(0);
    Robot.driveTrain.driveSideRight(0);
  }

  @Override
  protected void interrupted() {
  }

  private void calculateDeltaAngle() {
    deltaAngle = targetAngle - (float) Robot.tecbotgyro.getYaw();
    if (bestTurn) {
      if (deltaAngle < -180)
        deltaAngle += 360;
      if (deltaAngle > 180)
        deltaAngle -= 360;
    }
  }
}
