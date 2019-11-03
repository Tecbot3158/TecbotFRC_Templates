/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.chassis.*;

public class RocketTestGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RocketTestGroup() {
    addSequential(new ResetGyro());
    addSequential(new MoveStraightForwardOneEncoder(0, 5.8 * RobotMap.k_meters_to_encoder));
    addSequential(new TurnDegrees(49.0, 0.6));
    addSequential(new MoveStraightForwardOneEncoder(49.0, 2.60 * RobotMap.k_meters_to_encoder));
    addSequential(new ResetGyro());
    addSequential(new TurnDegrees(95, 0.6));
  }

  public void initialize() {
    Robot.actualCommand = this;
  }

}
