/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.arm.wrist.MoveRoller;
import frc.robot.commands.chassis.ChangeTransmission;
import frc.robot.commands.chassis.MoveDistanceWithSpeedReduction;
import frc.robot.commands.chassis.MoveForwardTimed;
import frc.robot.commands.chassis.MoveStraightForwardOneEncoder;
import frc.robot.commands.chassis.ResetGyro;
import frc.robot.commands.chassis.TurnDegrees;

public class LeftToMiddleCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LeftToMiddleCargo() {
    addSequential(new ResetGyro());
    addSequential(new MoveStraightForwardOneEncoder(-10, 7.7f * RobotMap.k_meters_to_encoder));
    addSequential(new TurnDegrees(90, 0.8));
    addSequential(new MoveForwardTimed(3f, -.85f));
    // addSequential(new MoveRoller(-1, .6f));
    // addSequential(new MoveDistanceWithSpeedReduction(-1, .75f));
    // addSequential(new TurnDegrees(-180, 0.6));
    // addSequential(new ResetGyro());
    // addSequential(new MoveStraightForwardOneEncoder(15, 6 *
    // RobotMap.k_meters_to_encoder));

  }
}
