/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm.extensor;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtensorMovement extends Command {
  public ExtensorMovement() {
    requires(Robot.extensor);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.extensor.isOnManualMovement()) {
      Robot.extensor.moveExtensorTeleoperated();
    } else {
      Robot.extensor.keepOnTarget();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.extensor.stop();
  }

  @Override
  protected void interrupted() {
    Robot.extensor.stop();
  }
}
