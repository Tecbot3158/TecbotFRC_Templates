/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.resources.TecbotConstants;

public class TurnCertainDegreesWithSpeedReduction extends CommandGroup {
  /**
   * This makes the robot rotate certain degrees relative to the actual position
   */
  public TurnCertainDegreesWithSpeedReduction(float degrees, float targetPower, boolean bestTurn) {

    float targetAngle = (float) (Robot.tecbotgyro.getYaw() + degrees);
    addSequential(new RotateChassisWithSpeedReduction(targetAngle, targetPower,
        TecbotConstants.TURNING_START_REDUCING_SPEED_POINT, TecbotConstants.TURNING_TOLERANCE, bestTurn));

  }
}
