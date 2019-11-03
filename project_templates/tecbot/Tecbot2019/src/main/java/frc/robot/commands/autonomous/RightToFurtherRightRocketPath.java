/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.chassis.*;

public class RightToFurtherRightRocketPath extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RightToFurtherRightRocketPath() {

    addSequential(new DescendFromRampThenAlign());
    addSequential(new MoveAlongPath("RightToFurtherRightRocket"));
  }
}
