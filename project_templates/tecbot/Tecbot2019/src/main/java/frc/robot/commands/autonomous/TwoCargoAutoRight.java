/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.arm.angler.MoveAnglerTwoPositions;
import frc.robot.commands.arm.extensor.MoveExtensorTwoPositions;
import frc.robot.commands.arm.wrist.CloseClaw;
import frc.robot.commands.arm.wrist.MoveRoller;
import frc.robot.commands.arm.wrist.MoveWristTwoPositions;
import frc.robot.commands.chassis.MoveStraightForwardOneEncoder;
import frc.robot.commands.chassis.ResetGyro;
import frc.robot.commands.chassis.TurnDegrees;
import frc.robot.resources.TecbotConstants;

public class TwoCargoAutoRight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public TwoCargoAutoRight() {

    addSequential(new ResetGyro());
    addSequential(new MoveStraightForwardOneEncoder(1, -7.7f * RobotMap.k_meters_to_encoder));
    addSequential(new TurnDegrees(60, 0.75));
    addSequential(new MoveRoller(-1, .6f));
    addSequential(new TurnDegrees(0, 0.75));
    addSequential(new MoveStraightForwardOneEncoder(-5, 6 * RobotMap.k_meters_to_encoder));

    addSequential(new CloseClaw());
    addSequential(new MoveWristTwoPositions(TecbotConstants.ARM_WRIST_GRAB_BALL_FLOOR,
        TecbotConstants.ARM_WRIST_GRAB_BALL_FLOOR, TecbotConstants.ARM_WRIST_MAX_POWER));
    addSequential(new MoveExtensorTwoPositions(TecbotConstants.ARM_EXTENSOR_GRAB_FROM_FLOOR,
        TecbotConstants.ARM_EXTENSOR_GRAB_FROM_FLOOR, TecbotConstants.ARM_EXTENSOR_MAX_POWER));
    addSequential(new MoveAnglerTwoPositions(TecbotConstants.ARM_ANGLER_GRAB_FROM_FLOOR,
        TecbotConstants.ARM_ANGLER_GRAB_FROM_FLOOR, TecbotConstants.ARM_ANGLER_MAX_POWER));

    addSequential(new MoveRoller(-.5, .5f));

    addSequential(new MoveAnglerTwoPositions(TecbotConstants.ARM_ANGLER_START_CONFIGURATION,
        TecbotConstants.ARM_ANGLER_START_CONFIGURATION, TecbotConstants.ARM_ANGLER_MAX_POWER));
    addSequential(new MoveExtensorTwoPositions(TecbotConstants.ARM_EXTENSOR_TRANSPORT,
        TecbotConstants.ARM_EXTENSOR_TRANSPORT, TecbotConstants.ARM_EXTENSOR_MAX_POWER));
    addSequential(new MoveWristTwoPositions(TecbotConstants.ARM_WRIST_TRANSPORT, TecbotConstants.ARM_WRIST_TRANSPORT,
        TecbotConstants.ARM_WRIST_MAX_POWER));

    addSequential(new MoveStraightForwardOneEncoder(-25, -2 * RobotMap.k_meters_to_encoder));
    addSequential(new TurnDegrees(-180, .75f));

    addSequential(new MoveRoller(1, 1));

  }
}
