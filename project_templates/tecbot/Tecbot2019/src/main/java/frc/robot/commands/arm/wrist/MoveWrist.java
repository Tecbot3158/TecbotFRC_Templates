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
import frc.robot.subsystems.watcher.WatchableSubsystem.State;

public class MoveWrist extends Command {
  double target, maxPower;
  boolean arrived = false;
  public MoveWrist(double target, double maxPower) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wrist);
    this.target = target;
    this.maxPower = maxPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SmartDashboard.putNumber("Wrist Setpoint", target);
    if(Robot.wrist.getState() == State.DANGER){
      this.cancel();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch(Robot.wrist.getState()){
      case GOOD:
      arrived = Robot.wrist.armWristMove(target, maxPower);
      break;
      case WARNING:
      Robot.wrist.correct();
      Robot.wrist.stop();
      arrived = false;
      break;
      case DANGER:
      this.cancel();
      break;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("Wrist Arrived", arrived);
    return arrived;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.wrist.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.wrist.stop();
  }
}
