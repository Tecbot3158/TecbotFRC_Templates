/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.angler;

import javax.swing.text.StyledEditorKit;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.resources.RobotConfigurator;
import frc.robot.resources.TecbotConstants;
import frc.robot.resources.TecbotEncoder;
import frc.robot.math.Math;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;
import frc.robot.Robot;
import frc.robot.RobotConfiguration;
import frc.robot.RobotMap;
import frc.robot.commands.arm.ManualMovement;
import frc.robot.subsystems.watcher.WatchableSubsystem;;

/**
 * Add your docs here.
 */
public class AnglerSubsystem extends Subsystem implements WatchableSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TecbotEncoder encoder;
  private TecbotSpeedController motor;
  private TecbotSpeedController motor2;
  private State state = State.GOOD;
  private int warnTimer;
  private double target = 0;
  private boolean hatch = false;

  public AnglerSubsystem() {

    // Motors

    motor = new TecbotSpeedController(RobotMap.ANGLER_MOTOR_PORT, TypeOfMotor.TALON_SRX);
    motor2 = new TecbotSpeedController(RobotMap.ANGLER_MOTOR_PORT2, TypeOfMotor.PWM_TALON_SRX);

    // Encoders

    encoder = RobotConfigurator.buildEncoder(motor, RobotConfigurator.CONFIG_NOT_SET, RobotConfigurator.CONFIG_NOT_SET);

    encoder.doDefaultSRXConfig();

    this.setName("Angler");

  }

  /**
   * Move the angler to a target position without setting the global target
   * 
   * @param target   Target position of the angler
   * @param maxPower Max power that can be applied by the method
   * @return Boolean that tells you if the angler arrived
   */

  public boolean armAnglerMoveWithoutTargetSet(double t, double maxPower) {
    maxPower = Math.clamp(maxPower, 0, 1);
    double targetLocal = t;
    targetLocal = Math.clamp(targetLocal, TecbotConstants.ARM_ANGLER_RIGHT_LOWER_OFFSET,
        TecbotConstants.ARM_ANGLER_RIGHT_UPPER_OFFSET);

    double distance = targetLocal - getPosition();

    double power = Math.clamp(distance / TecbotConstants.ARM_ANGLER_MAX_DISTANCE, -maxPower, maxPower);

    double absDistance = Math.abs(distance);

    if (absDistance >= TecbotConstants.ARM_ANGLER_ARRIVE_OFFSET) {
      setMotorPower(power);
    }
    if (absDistance < TecbotConstants.ARM_ANGLER_ARRIVE_OFFSET) {
      setMotorPower(0);
      return true;
    }
    return false;
  }

  /**
   * Move the angler to a target position setting the global target
   * 
   * @param target   Target position of the angler
   * @param maxPower Max power that can be applied by the method
   * @return Boolean that tells you if the angler arrived
   */

  public boolean armAnglerMove(double t, double maxPower) {
    target = t;
    maxPower = Math.clamp(maxPower, 0, 1);

    SmartDashboard.putNumber("Arm Target", target);

    target = Math.clamp(target, TecbotConstants.ARM_ANGLER_LEFT_LOWER_OFFSET,
        TecbotConstants.ARM_ANGLER_LEFT_UPPER_OFFSET);

    double distance = target - getPosition();

    SmartDashboard.putNumber("Arm Target Clamped", target);
    SmartDashboard.putNumber("Arm Position", getPosition());

    double power = Math.clamp(distance / TecbotConstants.ARM_ANGLER_MAX_DISTANCE, -maxPower, maxPower);

    double absDistance = Math.abs(distance);

    SmartDashboard.putNumber("Distance", distance);
    SmartDashboard.putNumber("Abs Distance", absDistance);
    SmartDashboard.putNumber("Power", power);

    if (absDistance >= TecbotConstants.ARM_ANGLER_ARRIVE_OFFSET) {
      setMotorPower(power);
    }
    if (absDistance < TecbotConstants.ARM_ANGLER_ARRIVE_OFFSET) {
      setMotorPower(0);
      return true;
    }
    return false;
  }

  /**
   * Try to correct the angler in the nearest position
   */

  // public void anglerCorrectNearPosition(){
  // double leftAnglerPosition = getLeftPosition();
  // double rightAnglerPosition = getRightPosition();

  // double leftDifference = Math.abs(leftAnglerPosition - leftTarget);
  // double rightDifference = Math.abs(rightAnglerPosition - rightTarget);

  // if(leftDifference < rightDifference){
  // armAnglerMoveWithoutTargetSet(leftAnglerPosition,
  // TecbotConstants.ARM_ANGLER_CORRECT_MAX_POWER);
  // } else {
  // armAnglerMoveWithoutTargetSet(rightAnglerPosition,
  // TecbotConstants.ARM_ANGLER_CORRECT_MAX_POWER);
  // }

  // }

  /**
   * Try to correct the angler in the middle position
   */

  // public void anglerCorrectMiddlePosition(){
  // double leftAnglerPosition = getLeftPosition();
  // double rightAnglerPosition = getRightPosition();

  // double middlePosition = (leftAnglerPosition + rightAnglerPosition)/2.0;

  // armAnglerMoveWithoutTargetSet(middlePosition,
  // TecbotConstants.ARM_ANGLER_CORRECT_MAX_POWER);

  // }

  /**
   * Move the arm in teleop mode using pilot triggers
   */

  public void moveAnglerTeleoperated() {
    // double s = Robot.oi.getCopilot().getRawAxis(5) * 10;
    // s = (java.lang.Math.floor(s))/10;
    // motor.set(s);
    // if(Robot.oi.getPilotAIsActive()){
    // motor.set(0.7);
    // } else if(Robot.oi.getPilotYIsActive()){
    // motor.set(-0.7);
    // } else {
    // motor.set(0);
    // }

    // if(Robot.oi.getCopilot().getRawAxis(1) > 0.1 ||
    // Robot.oi.getCopilot().getRawAxis(1) < -0.1){

    double s = Math.deadZone(Robot.oi.getCopilot().getRawAxis(1), 0.2);
    if (Robot.oi.pilot.getRawAxis(4) > .8)
      s = .6;
    if (Robot.oi.pilot.getRawAxis(4) < -.8)
      s = -.6;
    setMotorPower(s);
    SmartDashboard.putNumber("angler power", s);

  }

  /**
   * SmartDashboard print encoders
   */

  public void printEncodersSmartDashboard() {
    SmartDashboard.putNumber("Angler Encoder", encoder.getRaw());
  }

  public void changeHatchBoolean() {
    hatch = !hatch;
  }

  public boolean getHatchBoolean() {
    return hatch;
  }

  /**
   * This method must be used by the watcher to check the state of the mecanism
   */

  @Override
  public void check() {

  }

  public void resetEncoders() {
    encoder.reset();
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
   * Disable the angler
   */

  @Override
  public void setDisabled() {
    this.state = State.DANGER;
  }

  /**
   * Return the state of the angler
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
    setMotorPower(0);
  }

  /**
   * Gives you the raw position of the angler encoder
   * 
   * @return Raw position of the angler encoder
   */

  public double getPosition() {
    return encoder.getRaw();
  }

  /**
   * Return angler motor
   * 
   * @return
   */

  public TecbotSpeedController getLeftMotor() {
    return motor;
  }

  private void setMotorPower(double power) {
    motor.set(power);
    motor2.set(power);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualMovement());

  }

}
