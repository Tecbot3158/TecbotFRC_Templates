/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotConstants;
import frc.robot.resources.TecbotEncoder;

/**
 * Add your docs here.
 */
public class DistancePIDOneEncoder extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  private double pivotAngle = 0;
  private double diff;
  private TecbotEncoder encoder;

  public DistancePIDOneEncoder(double pivotAngle, TecbotEncoder encoder) {
    // Intert a subsystem name and PID values here
    super("Distance PID One Encoder", RobotMap.straight_P, RobotMap.straight_I, RobotMap.straight_D);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    this.pivotAngle = pivotAngle;
    this.encoder = encoder;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return -encoder.getRaw();
  }

  public double getAngleDifference() {
    return diff = pivotAngle - Robot.tecbotgyro.getYaw();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);

    double correction = getAngleDifference() * TecbotConstants.CORRECTION_COEFFICIENT;
    SmartDashboard.putNumber("Correction", correction);
    SmartDashboard.putNumber("Angle Difference", getAngleDifference());
    Robot.driveTrain.drive(-output, -correction);
  }
}
