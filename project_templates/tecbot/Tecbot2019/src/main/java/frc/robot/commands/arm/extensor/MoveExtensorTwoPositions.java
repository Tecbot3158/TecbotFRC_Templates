/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm.extensor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.watcher.WatchableSubsystem.State;

public class MoveExtensorTwoPositions extends Command {
  double targetBall, targetHatch, maxPower;
  boolean arrived = true;

  public MoveExtensorTwoPositions(double targetBall, double targetHatch, double maxPower) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.extensor);
    this.targetBall = targetBall;
    this.targetHatch = targetHatch;
    this.maxPower = maxPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (Robot.extensor.getState() == State.DANGER) {
      this.cancel();
    }
    if (Robot.extensor.getHatchBoolean()) {
      Robot.extensor.updateTarget(targetHatch);
    } else {
      Robot.extensor.updateTarget(targetBall);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*
     * switch (Robot.extensor.getState()) { case GOOD: if
     * (Robot.extensor.getHatchBoolean()) { arrived =
     * Robot.extensor.armExtensorMove(targetHatch, maxPower); } else { arrived =
     * Robot.extensor.armExtensorMove(targetBall, maxPower); } break; case WARNING:
     * 
     * break; case DANGER:
     * 
     * break; }
     */
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("Extensor Arrived", Robot.extensor.hasArrivedToTarget());
    return Robot.extensor.hasArrivedToTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.extensor.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.extensor.stop();
  }
}
