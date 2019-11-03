package frc.robot.commands.chassis;

import frc.robot.Robot;
import frc.robot.resources.TecbotConstants;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Implements PID to move the robot a certain distance
 */
public class PIDMoveDistance extends Command {

	// The values which the encoder has to get
	float targetLeftEncoderCount, targetRightEncoderCount, meters, targetAngle;
	DistancePID rightPID, leftPID;
	boolean useGyro;
	float targetPower;

	/**
	 * 
	 * @param distanceInMeters The distance in meters that the robot will move
	 * @param power            The top power that will be given to the motors
	 */
	public PIDMoveDistance(float distanceInMeters, float power) {
		requires(Robot.driveTrain);
		meters = distanceInMeters;
		useGyro = false;
		targetPower = power;
	}

	/**
	 * 
	 * @param distanceInMeters The distance in meters that the robot will move
	 * @param angle            The angle which the robot will point to, use this in
	 *                         order to make the robot move in a straight line
	 * @param power            The top power that will be given to the motors
	 */
	public PIDMoveDistance(float distanceInMeters, float angle, float power) {
		requires(Robot.driveTrain);
		meters = distanceInMeters;
		targetAngle = angle;
		useGyro = true;
		targetPower = power;
	}

	protected void initialize() {
		Robot.leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		Robot.leftEncoder.resetEncoder();

		// Robot.rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		// Robot.rightEncoder.resetEncoder();

		// The difference between the target and the actual encoder count
		float deltaEncoderCount = meters * TecbotConstants.K_METERS_TO_ENCODER;
		// targetRightEncoderCount = Robot.rightEncoder.getRaw() + deltaEncoderCount;
		targetLeftEncoderCount = Robot.leftEncoder.getRaw() + deltaEncoderCount;
		// rightPID = new DistancePID(TecbotConstants.STRAIGHT_P,
		// TecbotConstants.STRAIGHT_I, TecbotConstants.STRAIGHT_D,
		// Robot.rightEncoder);
		leftPID = new DistancePID(TecbotConstants.STRAIGHT_P, TecbotConstants.STRAIGHT_I, TecbotConstants.STRAIGHT_D,
				Robot.leftEncoder);

		/*
		 * rightPID.setOutputRange(-targetPower, targetPower);
		 * rightPID.setAbsoluteTolerance(TecbotConstants.STRAIGHT_TOLERANCE);
		 * rightPID.setSetpoint(targetRightEncoderCount); rightPID.enable();
		 */
		leftPID.setOutputRange(-targetPower, targetPower);
		leftPID.setAbsoluteTolerance(TecbotConstants.STRAIGHT_TOLERANCE);
		leftPID.setSetpoint(targetLeftEncoderCount);
		leftPID.enable();

	}

	protected void execute() {

		if (useGyro) {
			float deltaAngle = (float) (targetAngle - Robot.tecbotgyro.getYaw());
			if (deltaAngle < -180)
				deltaAngle += 360;
			if (deltaAngle > 180)
				deltaAngle -= 360;
			leftPID.setDeltaAngle(deltaAngle);
			// rightPID.setDeltaAngle(deltaAngle);
		}

	}

	protected boolean isFinished() {
		// return (leftPID.onTarget() && rightPID.onTarget());
		return leftPID.onTarget();
	}

	protected void end() {
		// rightPID.disable();
		leftPID.disable();
	}

	protected void interrupted() {
		// leftPID.disable();
		rightPID.disable();
	}
}
