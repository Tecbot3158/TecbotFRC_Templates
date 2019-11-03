/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.wrist.MoveRoller;
import frc.robot.commands.chassis.MoveDistanceWithSpeedReduction;
import frc.robot.commands.chassis.MoveForwardTimed;
import frc.robot.commands.chassis.TurnDegrees;

public class RightToMiddleCargoShip extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RightToMiddleCargoShip(boolean startingFromLvl2) {
    if (startingFromLvl2)
      addSequential(new DescendFromRampThenAlign());

    addSequential(new MoveDistanceWithSpeedReduction(7.5f, .8f, 0));
    addSequential(new TurnDegrees(-90, .7f, false));
    addSequential(new MoveForwardTimed(1.5f, .5f));
    addSequential(new MoveRoller(-1, .6f));
    addSequential(new MoveDistanceWithSpeedReduction(-1, .7f));
    addSequential(new TurnDegrees(-180, .7f, true));
    // addSequential(new TurnDegrees(-10, .7f, true));
    addSequential(new MoveDistanceWithSpeedReduction(6, .7f, -10));
  }

}
