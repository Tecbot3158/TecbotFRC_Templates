/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.CommandHandler;
import frc.robot.commands.CommandHandlerStateModify;
import frc.robot.commands.arm.ChangeHatchBoolean;
import frc.robot.commands.arm.wrist.MoveRoller;
import frc.robot.commands.automatic.DeployBallLowerRocket;
import frc.robot.commands.chassis.MoveStraightForwardOneEncoder;
import frc.robot.commands.chassis.ResetGyro;
import frc.robot.commands.chassis.TurnDegrees;

public class RightToMiddleCargoBackwards extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RightToMiddleCargoBackwards() {
    addSequential(new ResetGyro());
    addSequential(new MoveStraightForwardOneEncoder(1, -7.7f * RobotMap.k_meters_to_encoder));
    addSequential(new TurnDegrees(60, 0.75));
    addSequential(new MoveRoller(-1, .6f));
    addSequential(new TurnDegrees(0, 0.75));
    addSequential(new MoveStraightForwardOneEncoder(-10, 6 * RobotMap.k_meters_to_encoder));
    addSequential(new ChangeHatchBoolean());
    addSequential(new CommandHandlerStateModify(CommandHandler.BOTTOM_CONFIGURATION));
  }
}
