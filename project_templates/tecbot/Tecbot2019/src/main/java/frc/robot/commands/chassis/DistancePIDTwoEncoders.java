/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotEncoder;
import frc.robot.subsystems.chassis.DriveTrain.Side;

/**
 * Add your docs here.
 */
public class DistancePIDTwoEncoders extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  TecbotEncoder encoder;
  Side side;
  public DistancePIDTwoEncoders(TecbotEncoder encoder, Side side) {
    // Intert a subsystem name and PID values here
    super("Distance PID Two Encoders", RobotMap.straight_P, RobotMap.straight_I, RobotMap.straight_D);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    this.encoder = encoder;
    this.side = side;
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
    return encoder.getRaw();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);

    if(  side == Side.RIGHT ){

      SmartDashboard.putNumber("Right Power", output );

    }else{

      SmartDashboard.putNumber("Left Power", output );
    }
    
    Robot.driveTrain.driveSide(side, java.lang.Math.signum(output)*java.lang.Math.ceil(java.lang.Math.abs(0.75)));
  }
}
