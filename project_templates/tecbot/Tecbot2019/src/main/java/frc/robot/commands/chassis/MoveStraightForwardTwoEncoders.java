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
import frc.robot.subsystems.chassis.DriveTrain.Side;

public class MoveStraightForwardTwoEncoders extends Command {
  public DistancePIDTwoEncoders leftPID;
  public DistancePIDTwoEncoders rightPID;
  double distanceToMove, leftDiff, rightDiff;
  public MoveStraightForwardTwoEncoders(double distanceToMove) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
    this.distanceToMove = distanceToMove;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftPID = new DistancePIDTwoEncoders(Robot.driveTrain.getLeftEncoder(), Side.LEFT);
    rightPID = new DistancePIDTwoEncoders(Robot.driveTrain.getRightEncoder(), Side.RIGHT);

    Robot.driveTrain.getLeftEncoder().reset();
    Robot.driveTrain.getRightEncoder().reset();

    leftPID.setAbsoluteTolerance(200);
    leftPID.setOutputRange(-.75, .75);
    leftPID.setInputRange(-Double.MAX_VALUE, Double.MAX_VALUE);
    leftPID.setSetpoint(distanceToMove);
    leftPID.enable();

    rightPID.setAbsoluteTolerance(200);
    rightPID.setOutputRange(-.75, .75);
    rightPID.setInputRange(-Double.MAX_VALUE, Double.MAX_VALUE);
    rightPID.setSetpoint(-distanceToMove);
    rightPID.enable();
   
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Left Encoder", Robot.driveTrain.getLeftPosition());
    SmartDashboard.putNumber("Right Encoder", Robot.driveTrain.getRightPosition());

    
  }


  boolean leftDone = false;
  boolean rightDone = false;
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    leftDiff = Math.abs(leftPID.getPosition() - leftPID.getSetpoint());
    rightDiff = Math.abs(rightPID.getPosition() - rightPID.getSetpoint());
    SmartDashboard.putNumber("Left Diff", leftDiff); 
    SmartDashboard.putNumber("Right Diff", rightDiff);
    SmartDashboard.putNumber("Right Setpoint", rightPID.getSetpoint());

    SmartDashboard.putBoolean("Right On Target", rightPID.onTarget());


    /**not quite working, joe, 4 feb 2019 */
    if( leftDiff < 200  || leftPID.onTarget() || leftPID.getPosition() < leftPID.getSetpoint() ){
      leftDone = true;
      leftPID.disable();
    }
    if( rightDiff < 200 || rightPID.onTarget() || rightPID.getPosition() >  rightPID.getSetpoint() ){
      rightDone = true;
      rightPID.disable();
    }
    return  (leftDone && rightDone);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    leftPID.disable();
    rightPID.disable();
    Robot.driveTrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    leftPID.disable();
    rightPID.disable();
    Robot.driveTrain.drive(0, 0);
  }
}
