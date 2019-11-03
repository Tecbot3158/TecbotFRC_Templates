/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.tilt;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.tilt.TiltTeleop;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;

/**
 * The subsystem that will make the robot tilt and climb
 */
public class TiltSubsystem extends Subsystem {

  TecbotSpeedController motor1, motor2;

  public TiltSubsystem() {

    motor1 = new TecbotSpeedController(RobotMap.TITLT_MOTOR_1, TypeOfMotor.PWM_TALON_SRX);
    motor2 = new TecbotSpeedController(RobotMap.TITLT_MOTOR_2, TypeOfMotor.PWM_TALON_SRX);

  }

  public void tiltTeleop() {
    // motor1.set(Robot.oi.getCopilotTriggers());
    // motor2.set(Robot.oi.getCopilotTriggers());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TiltTeleop());
  }
}
