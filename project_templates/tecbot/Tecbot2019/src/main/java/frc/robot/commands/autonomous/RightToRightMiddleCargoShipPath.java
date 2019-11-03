/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.chassis.MoveAlongPath;
import frc.robot.commands.chassis.TurnToAngleWithSpeedReduction;

public class RightToRightMiddleCargoShipPath extends CommandGroup {
  /**
   * 
   */
  public RightToRightMiddleCargoShipPath(boolean startingFromLvl2) {

    if (startingFromLvl2)
      addSequential(new DescendFromRampThenAlign());
    addSequential(new MoveAlongPath("RightToMiddleCargoRight"));
    addSequential(new TurnToAngleWithSpeedReduction(90, .7f, true));
    addSequential(new MoveAlongPath("RightMiddleCargoToDepot"));
  }
}
