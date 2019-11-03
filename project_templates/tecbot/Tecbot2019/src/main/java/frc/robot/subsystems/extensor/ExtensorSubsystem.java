/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.extensor;

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
import frc.robot.commands.arm.extensor.ExtensorMovement;
import frc.robot.commands.arm.extensor.ManualMovementExtensor;
import frc.robot.subsystems.watcher.WatchableSubsystem;

/**
 * Add your docs here.
 */
public class ExtensorSubsystem extends Subsystem implements WatchableSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TecbotEncoder encoder;
  private State state = State.GOOD;
  private TecbotSpeedController leftMotor, rightMotor;
  private int warnTimer;
  private boolean hatch = false;
  private double currentTarget = 0;
  private boolean isOnManualMovement;
  private boolean arrivedToTarget;

  public ExtensorSubsystem() {

    // Motors

    leftMotor = new TecbotSpeedController(RobotMap.EXTENSOR_LEFT_MOTOR_PORT, TypeOfMotor.TALON_SRX);
    rightMotor = new TecbotSpeedController(RobotMap.EXTENSOR_RIGHT_MOTOR_PORT, TypeOfMotor.PWM_TALON_SRX);

    // Encoders

    encoder = RobotConfigurator.buildEncoder(leftMotor, RobotConfigurator.CONFIG_NOT_SET,
        RobotConfigurator.CONFIG_NOT_SET);
    encoder.doDefaultSRXConfig();

    this.setName("Extensor");

  }

  /**
   * @return the arrivedToTarget
   */
  public boolean hasArrivedToTarget() {
    return arrivedToTarget;
  }

  /**
   * @param arrivedToTarget the arrivedToTarget to set
   */
  public void setArrivedToTarget(boolean arrivedToTarget) {
    this.arrivedToTarget = arrivedToTarget;
  }

  /**
   * @return the isOnManualMovement
   */
  public boolean isOnManualMovement() {
    return isOnManualMovement;
  }

  /**
   * @param isOnManualMovement the isOnManualMovement to set
   */
  public void setOnManualMovement(boolean isOnManualMovement) {
    this.isOnManualMovement = isOnManualMovement;
  }

  /**
   * Move the extensor to a target position
   * 
   * @param target   Target position of the extensor
   * @param maxPower Max power that can be applied by the method
   * @return Boolean that tells you if the extensor arrived
   * 
   */

  public boolean armExtensorMove(double target, double maxPower) {

    SmartDashboard.putNumber("Extensor Target", target);
    SmartDashboard.putNumber("Current Extensor Target", currentTarget);

    maxPower = Math.clamp(maxPower, 0, 1);
    target = Math.clamp(target, TecbotConstants.ARM_EXTENSOR_LOWER_OFFSET, TecbotConstants.ARM_EXTENSOR_UPPER_OFFSET);

    SmartDashboard.putNumber("Extensor Clamped Target", target);

    double distance = getPosition() - target;

    SmartDashboard.putNumber("Extensor Distance", distance);

    double power = Math.clamp(distance / TecbotConstants.ARM_EXTENSOR_MAX_DISTANCE, -maxPower, maxPower);

    SmartDashboard.putNumber("Extensor Power", power);

    double absDistance = Math.abs(distance);

    if (absDistance >= TecbotConstants.ARM_EXTENSOR_ARRIVE_OFFSET) {
      leftMotor.set(power);
      rightMotor.set(-power);
    }

    if (absDistance < TecbotConstants.ARM_EXTENSOR_ARRIVE_OFFSET) {
      arrivedToTarget = true;
      return true;
    }

    arrivedToTarget = false;
    return false;
  }

  /**
   * Move extensor teleoperated, floor included :)
   */

  public void moveExtensorTeleoperated() {
    // double s = Robot.oi.getCopilotTriggers() * 10;
    SmartDashboard.putNumber("POV", Robot.oi.getPilot().getPOV());
    // s = (java.lang.Math.floor(s))/10;
    // leftMotor.set(s);
    // rightMotor.set(-s);
    // if(Robot.oi.getPilot().getPOV() == 90){
    // leftMotor.set(0.6);
    // rightMotor.set(-0.6);
    // } else if(Robot.oi.getPilot().getPOV() == 270){
    // leftMotor.set(-0.6);
    // rightMotor.set(0.6);
    // } else {
    // leftMotor.set(0);
    // rightMotor.set(0);
    // }
    // double s = Math.deadZone(Robot.oi.getCopilot().getRawAxis(5), 0.2) -
    // Robot.oi.getPilotTriggers();
    double s = Robot.oi.getPilotTriggers();
    leftMotor.set(-s);
    rightMotor.set(s);
    if (encoder.getRaw() > TecbotConstants.RUMBLE_EXTENDER_ENCODER_VALUE) {
    }
    // Robot.oi.getCopilot().setRumble(RumbleType.kRightRumble, 1);
    else {
    }
    // Robot.oi.getCopilot().setRumble(RumbleType.kRightRumble, 0);

  }

  /**
   * SmartDashboard print encoders
   */

  public void printEncodersSmartDashboard() {
    SmartDashboard.putNumber("Extensor Encoder", encoder.getRaw());
  }

  public void changeHatchBoolean() {
    hatch = !hatch;
  }

  public boolean getHatchBoolean() {
    return hatch;
  }

  /**
   * Reset the extensor encoders
   */

  public void resetEncoders() {
    encoder.reset();
  }

  /**
   * Try to correct the extensor
   */

  public void extensorCorrect() {
    // double leftExtensorPosition = getLeftPosition();
    // double rightExtensorPosition = getRightPosition();

    // double middlePosition = (leftExtensorPosition + rightExtensorPosition)/2.0;

    // armExtensorMove(middlePosition,
    // TecbotConstants.ARM_EXTENSOR_CORRECT_MAX_POWER);

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

  public void setGood() {
    this.state = State.GOOD;
  }

  /**
   * Set warning state in the subsytem
   */

  public void setWarning() {
    this.state = State.WARNING;
  }

  /**
   * Set danger state in the subsystem
   */

  @Override
  public void setDisabled() {
    this.state = State.DANGER;
  }

  /**
   * Return the state of the extensor
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
    leftMotor.set(0);
    rightMotor.set(0);
  }

  /**
   * Gives you the raw position of the extensor encoder
   * 
   * @return Raw position of the extensor encoder
   */

  public double getPosition() {
    return encoder.getRaw();
  }

  /**
   * Return the extensor left motor
   * 
   * @return extensor left motor
   */

  public TecbotSpeedController getLeftMotor() {
    return leftMotor;
  }

  /**
   * Return the extensor right motor
   * 
   * @return extensor right motor
   */

  public TecbotSpeedController getRightMotor() {
    return rightMotor;
  }

  public void updateTarget(double target) {

    currentTarget = target;
  }

  public void keepOnTarget() {
    armExtensorMove(currentTarget, TecbotConstants.ARM_EXTENSOR_MAX_POWER);
  }

  public boolean extensorNear() {
    if (encoder.getRaw() < -4000 && encoder.getRaw() > -12000)
      return true;
    return false;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ExtensorMovement());
  }
}
