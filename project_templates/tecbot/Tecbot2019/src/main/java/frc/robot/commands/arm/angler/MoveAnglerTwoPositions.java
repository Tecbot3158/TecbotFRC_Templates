/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm.angler;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.watcher.WatchableSubsystem.State;

public class MoveAnglerTwoPositions extends Command {
  double targetBall, targetHatch, maxPower;
  boolean arrived = true;
  public MoveAnglerTwoPositions(double targetBall, double targetHatch, double maxPower) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.angler);
    this.targetBall = targetBall;
    this.targetHatch = targetHatch;
    this.maxPower = maxPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.angler.getState() == State.DANGER){
      this.cancel();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch(Robot.angler.getState()){
      case GOOD:
        if(Robot.angler.getHatchBoolean()){
          arrived = Robot.angler.armAnglerMove(targetHatch, maxPower);
        } else {
          arrived = Robot.angler.armAnglerMove(targetBall, maxPower);
        }
      break;
      case WARNING:

      break;
      case DANGER:

      break;
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("Angler Arrived", arrived);
    return arrived;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.angler.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.angler.stop();
  }
}
