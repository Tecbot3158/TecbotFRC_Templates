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

public class MoveExtensor extends Command {
  double target, maxPower;
  boolean arrived;

  /**
   * 
   * @param target   Target
   * @param maxPower Currently not used
   */
  public MoveExtensor(double target, double maxPower) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.extensor);
    this.target = target;
    this.maxPower = maxPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.extensor.updateTarget(target);
    if (Robot.extensor.getState() == State.DANGER) {
      this.cancel();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*
     * switch (Robot.angler.getState()) { case GOOD: arrived =
     * Robot.extensor.armExtensorMove(target, maxPower); break; case WARNING:
     * Robot.extensor.correct(); arrived = false; break; case DANGER: this.cancel();
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
