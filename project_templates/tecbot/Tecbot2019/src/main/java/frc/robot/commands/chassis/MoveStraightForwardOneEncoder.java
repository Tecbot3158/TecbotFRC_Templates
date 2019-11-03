/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class MoveStraightForwardOneEncoder extends Command {
  DistancePIDOneEncoder distancePID;
  double pivotAngle, distanceToMove;
  double diff;

  public MoveStraightForwardOneEncoder(double pivotAngle, double distanceToMove) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
    this.pivotAngle = pivotAngle;
    this.distanceToMove = distanceToMove;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.getLeftEncoder().reset();
    distancePID = new DistancePIDOneEncoder(pivotAngle, Robot.driveTrain.getLeftEncoder());
    distancePID.setAbsoluteTolerance(200);
    distancePID.setOutputRange(-.75, .75);
    distancePID.setInputRange(-Double.MAX_VALUE, Double.MAX_VALUE);

    SmartDashboard.putNumber("Double Min Value", Double.MIN_VALUE);
    SmartDashboard.putNumber("To Distance" + this, distanceToMove);
    distancePID.setSetpoint(distanceToMove);
    distancePID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Navx", Robot.tecbotgyro.getYaw());
    SmartDashboard.putNumber("Left Encoder", Robot.driveTrain.getLeftPosition());
    SmartDashboard.putNumber("Actual PID Position", distancePID.getPosition());
    SmartDashboard.putNumber("Set Point", distancePID.getSetpoint());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("On Target", distancePID.onTarget());
    diff = Math.abs(distanceToMove - distancePID.getPosition());
    return distancePID.onTarget() || diff < 200;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    distancePID.disable();
    Robot.driveTrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    distancePID.disable();
    Robot.driveTrain.drive(0, 0);
  }
}
