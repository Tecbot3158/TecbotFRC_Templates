/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.resources.TecbotConstants;

public class TurnToAngleWithSpeedReduction extends CommandGroup {
  /**
   * This makes the robot turn to a certain angle, regardless of where it is
   * 
   * @param targetAngle The angle that the robot will be aiming to be
   * @param targetPower The maximum power given to the motors
   * @param bestTurn    Set this true to avoid the robot turning more than 180°
   */
  public TurnToAngleWithSpeedReduction(float targetAngle, float targetPower, boolean bestTurn) {

    addSequential(new RotateChassisWithSpeedReduction(targetAngle, targetPower,
        TecbotConstants.TURNING_START_REDUCING_SPEED_POINT, TecbotConstants.TURNING_TOLERANCE, bestTurn));

  }
}
