/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.resources.RobotConfigurator;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public enum MotorConfiguration {
		CAN, TALON
	}

	public enum ChassisConfiguration {
		MOTORS_2, MOTORS_4, MOTORS_6;
	}

	public static MotorConfiguration chassis_typeOfMotor = MotorConfiguration.CAN;
	public static ChassisConfiguration chassis_typeOfConfiguration = ChassisConfiguration.MOTORS_6;

	public static int chassis_frontRightMotor = 2; // 2 Talon SRX PWM 4 Victor SPX
	public static int chassis_frontLeftMotor = 1; // Talon SRX PWM 1 Victor 5
	public static int chassis_rearRightMotor = 4;
	public static int chassis_rearLeftMotor = 5;
	public static int chassis_middleLeftMotor = 3; // CAN
	public static int chassis_middleRightMotor = 20; // CAN
	public static int chassis_leftEncoder[] = { RobotConfigurator.CONFIG_NOT_SET, RobotConfigurator.CONFIG_NOT_SET };
	public static int chassis_rightEncoder[] = { RobotConfigurator.CONFIG_NOT_SET, RobotConfigurator.CONFIG_NOT_SET };

	public static int transmision_port_1 = 0, transmision_port_2 = 1;

	/*
	 * TALON SRX ENCODERS ON CHASSIS If there is no SRX encoder, this HAS to be -1,
	 * BOTH!
	 */
	public static int chassis_leftEncoderSRX = 3;
	public static int chassis_rightEncoderSRX = 20;

	public static float straight_P = 1.3672f;
	public static float straight_I = .3593f;
	public static float straight_D = .0898f;
	public static float straight_Tolerance = 10;

	/*
	 * public static float straight_P = 1.3672f; public static float straight_I = 0;
	 * public static float straight_D = 0 ; public static float straight_Tolerance =
	 * 10;
	 */
	// public static float straight_P = 1.58950784091104f;
	// public static float straight_I = 16.1648949353988f;
	// public static float straight_D = .02348467516511333f;

	public static float turning_P = 1.48950784091104f;
	public static float turning_I = 16.1648949353988f;
	public static float turning_D = .02348467516511333f;
	public static float turning_Tolerance = 7;

	// The equivalence between meters to encoder count
	// Meter * meters_to_encoder = encoder count
	public static float k_meters_to_encoder = (float) (30000 / (.2034 * Math.PI));
	// public static float k_meters_to_encoder = (float)
	// (RobotMap.k_tic_per_revolution / (RobotMap.k_wheel_diameter * Math.PI));
	public static int k_tic_per_revolution = 30000;
	public static float k_wheel_diameter = .2032f;

	// Arm CAN
	public static final int ANGLER_MOTOR_PORT = 12;
	// PWM Motor
	public static final int ANGLER_MOTOR_PORT2 = 3;
	public static final int ANGLER_LEFT_ENCODER_PORTS[] = { 4, 5 };
	public static final int ANGLER_RIGHT_ENCODER_PORTS[] = { 6, 7 };

	// CAN
	public static final int EXTENSOR_LEFT_MOTOR_PORT = 6;
	// PWM
	public static final int EXTENSOR_RIGHT_MOTOR_PORT = 8;

	public static final int EXTENSOR_LEFT_ENCODER_PORTS[] = { 0, 1 };
	public static final int EXTENSOR_RIGHT_ENCODER_PORTS[] = { 2, 3 };

	public static final int WRIST_MOTOR_PORT = 7;
	public static final int WRIST_ENCODER_PORTS[] = { 8, 9 };

	// PWM
	public static int TITLT_MOTOR_1 = 6;
	public static int TITLT_MOTOR_2 = 7;

	// Rodillo Victor SPX 3

	public static final int ROLLER_MOTOR_PORT = 0;

	// Claw
	public static final int CLAWMOTOR = 6;
	public static final int ROLLER = 7;
	public static final int ACTUATOR = 8;
	public static final int CLAWENC_1 = 0;
	public static final int CLAWENC_2 = 1;

	public static final int CLAW_UNITS_PER_LEVEL = 1000; // EXAMPLE

	public static boolean isUsingPS4Controller = true;

}
