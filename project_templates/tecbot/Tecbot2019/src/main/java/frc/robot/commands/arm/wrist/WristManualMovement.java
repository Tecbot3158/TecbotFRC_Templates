/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm.wrist;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class WristManualMovement extends Command {
  public WristManualMovement() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Left Trigger", (Robot.oi.getCopilot().getRawAxis(3) + 1) / 2);
    SmartDashboard.putNumber("Right Trigger", (Robot.oi.getCopilot().getRawAxis(4) + 1) / 2);

    if (Math.abs(Robot.oi.pilot.getRawAxis(5)) > .8f)
      Robot.wrist.rollerStart(Robot.oi.pilot.getRawAxis(5));
    else
      Robot.wrist.stopRoller();

    Robot.wrist.moveWristTeleoperated();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.wrist.stopRoller();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.wrist.stopRoller();
  }
}
