/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.wrist;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.resources.RobotConfigurator;
import frc.robot.resources.TecbotConstants;
import frc.robot.resources.TecbotEncoder;
import frc.robot.math.Math;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.arm.wrist.WristManualMovement;
import frc.robot.subsystems.watcher.WatchableSubsystem;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem implements WatchableSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TecbotEncoder encoder;
  private TecbotSpeedController motor, roller;
  private State state = State.GOOD;
  private DoubleSolenoid claw;
  private boolean hatch = false;

  public WristSubsystem() {

    // Motor

    motor = new TecbotSpeedController(RobotMap.WRIST_MOTOR_PORT, TypeOfMotor.TALON_SRX);
    roller = new TecbotSpeedController(RobotMap.ROLLER_MOTOR_PORT, TypeOfMotor.PWM_VICTOR_SPX);
    claw = new DoubleSolenoid(2, 3);
    // Encoder

    encoder = RobotConfigurator.buildEncoder(motor, RobotConfigurator.CONFIG_NOT_SET, RobotConfigurator.CONFIG_NOT_SET);
    encoder.doDefaultSRXConfig();

    this.setName("Wrist");

  }

  public void openClaw() {
    claw.set(Value.kForward);
  }

  public void closeClaw() {
    claw.set(Value.kReverse);
  }

  public boolean armWristMove(double target, double maxPower) {
    maxPower = Math.clamp(maxPower, 0, 1);

    SmartDashboard.putNumber("Wrist Target", target);

    target = Math.clamp(target, TecbotConstants.ARM_WRIST_LOWER_OFFSET, TecbotConstants.ARM_WRIST_UPPER_OFFSET);

    SmartDashboard.putNumber("Wrist Clamped Target", target);

    double distance = getPosition() - target;

    SmartDashboard.putNumber("Wrist Distance", distance);

    double power = Math.clamp(distance / TecbotConstants.ARM_WRIST_MAX_DISTANCE, -maxPower, maxPower);

    SmartDashboard.putNumber("Wrist Power", power);

    double absDistance = Math.abs(distance);

    if (absDistance >= TecbotConstants.ARM_WRIST_ARRIVE_OFFSET) {
      motor.set(power);
    } else {
      motor.set(0);
      return true;
    }

    return false;
  }

  /**
   * This method starts the roller at a given speed
   * 
   * @param speed Roller speed
   */

  public void rollerStart(double speed) {
    double s = java.lang.Math.floor(speed * 10);
    s = s / 10;
    roller.set(s);
  }

  public void changeHatchBoolean() {
    hatch = !hatch;
  }

  public boolean getHatchBoolean() {
    return hatch;
  }

  /**
   * Stops the roller
   */

  public void stopRoller() {
    roller.set(0);
  }

  /**
   * Move Wrist teleoperated, floor included :)
   */

  public void moveWristTeleoperated() {
    if (Robot.oi.getPilot().getPOV() == 0) {
      motor.set(-0.6);
    } else if (Robot.oi.getPilot().getPOV() == 90) {
      motor.set(0.6);
    }

    if (Robot.oi.getCopilotLBIsActive()) {
      motor.set(0.6);
    } else if (Robot.oi.getCopilotRBIsActive()) {
      motor.set(-0.6);
    } else if (!Robot.oi.getCopilotLBIsActive() && !Robot.oi.getCopilotRBIsActive()
        && Robot.oi.getPilot().getPOV() == -1) {
      motor.set(0);
    }

    if (Robot.oi.getCopilot().getPOV() == 0) {
      Robot.extensor.resetEncoders();
    } else if (Robot.oi.getCopilot().getPOV() == 90) {
      Robot.angler.resetEncoders();
    } else if (Robot.oi.getCopilot().getPOV() == 180) {
      Robot.wrist.resetEncoder();
    }
  }

  public void moveWristUp() {
    motor.set(0.6);
  }

  public void moveWristDown() {
    motor.set(-0.6);
  }

  /**
   * SmartDashboard print encoders
   */

  public void printEncodersSmartDashboard() {
    SmartDashboard.putNumber("Wrist Encoder", encoder.getRaw());
  }

  public void resetEncoder() {
    encoder.reset();
  }

  /**
   * This method must be used by the watcher to check the state of the mecanism
   */

  @Override
  public void check() {

  }

  /**
   * This method must be used by the commands that use this method
   */

  @Override
  public void correct() {

  }

  /**
   * Set good state in the subsystem
   */

  @Override
  public void setGood() {
    this.state = State.GOOD;
  }

  /**
   * Set warning state in the subsytem
   */

  @Override
  public void setWarning() {
    this.state = State.WARNING;
  }

  /**
   * Disable the wrist
   */

  @Override
  public void setDisabled() {
    this.state = State.DANGER;
  }

  /**
   * Return the state of the wrist
   */

  @Override
  public State getState() {
    return state;
  }

  /**
   * Return the name of the subsystem
   */

  @Override
  public String getSubsystemName() {
    return this.getName();
  }

  /**
   * Full stop to the subsystem
   */

  public void stop() {
    motor.set(0);
  }

  public TecbotSpeedController getMotor() {
    return motor;
  }

  /**
   * Gives you the raw position of the wrist encoder
   * 
   * @return Raw position of the wrist encoder
   */

  public double getPosition() {
    return encoder.getRaw();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new WristManualMovement());
  }

}
