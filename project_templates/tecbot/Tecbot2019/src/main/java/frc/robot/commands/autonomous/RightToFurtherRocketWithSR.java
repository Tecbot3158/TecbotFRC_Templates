/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.chassis.*;
import frc.robot.resources.TecbotConstants;

public class RightToFurtherRocketWithSR extends CommandGroup {
  /**
   * Robot starts from right position, goes to the right rocket using speed
   * reduction
   */
  public RightToFurtherRocketWithSR(boolean startingFromLvl2) {

    if (startingFromLvl2)
      addSequential(new DescendFromRampThenAlign());
    addSequential(new ChassisMoveDistanceWithSpeedReduction(Robot.leftEncoder,
        TecbotConstants.STRAIGHT_START_REDUCING_SPEED_POINT, TecbotConstants.STRAIGHT_TOLERANCE, 4, .7f, 0));
    addSequential(new RotateChassisWithSpeedReduction(90, .7f, TecbotConstants.TURNING_START_REDUCING_SPEED_POINT,
        TecbotConstants.TURNING_TOLERANCE, true));

    /*
     * addSequential(new TurnToAngleWithSpeedReduction(25, .7f, true));
     * addSequential(new MoveDistanceWithSpeedReduction(7.8f, 20, .5f));
     * addSequential(new TurnToAngleWithSpeedReduction(130, .7f, true));
     * addSequential(new MoveDistanceWithSpeedReduction(1f, 130, .5f));
     * addSequential(new Wait(2)); addSequential(new
     * MoveDistanceWithSpeedReduction(-1f, 130, .5f)); addSequential(new
     * TurnToAngleWithSpeedReduction(180, .7f, true)); addSequential(new
     * MoveDistanceWithSpeedReduction(8, 180, .5f));
     */
  }
}
