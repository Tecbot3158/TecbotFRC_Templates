package frc.robot.resources;

public class TecbotConstants {
    /*
     * TODO Remapear hatch abajo extender extensor y bajar actuador Entregar pelota
     * abajo vale madres
     * 
     */

    /*
     * 2018 public static final double FIRST_VELOCITY_POWER = 0.5; public static
     * final double SECOND_VELOCITY_POWER = 0.9; public static final double
     * ROTATION_FORCE_FRONT_LEFT_WHEEL = 0.5; public static final double
     * ROTATION_FORCE_FRONT_RIGHT_WHEEL = 0.5; public static final double
     * ROTATION_FORCE_REAR_LEFT_WHEEL_RIGHT_SPIN = 0.5; public static final double
     * ROTATION_FORCE_REAR_LEFT_WHEEL_LEFT_SPIN = 0.25; public static final double
     * ROTATION_FORCE_REAR_RIGHT_WHEEL_RIGHT_SPIN = 0.25; public static final double
     * ROTATION_FORCE_REAR_RIGHT_WHEEL_LEFT_SPIN = 0.5; public static final double
     * HIGHER_COLLECTOR_ORIENTATION = 100; public static final double
     * MIDDLE_COLLECTOR_ORIENTATION = 1500; public static final double
     * LOWER_COLLECTOR_ORIENTATION = 3000;
     */

    //// Extensor

    // Positions

    public static final double ARM_EXTENSOR_DEPLOY_BALL_CARGO = 0;
    public static final double ARM_EXTENSOR_DEPLOY_HATCH_CARGO = 0;
    public static final double ARM_EXTENSOR_DEPLOY_BALL_LOWER_ROCKET = -2567.0;
    public static final double ARM_EXTENSOR_DEPLOY_BALL_MIDDLE_ROCKET = -5800.0;
    public static final double ARM_EXTENSOR_DEPLOY_BALL_UPPER_ROCKET = -16769.0;
    public static final double ARM_EXTENSOR_DEPLOY_HATCH_LOWER_ROCKET = -211;
    public static final double ARM_EXTENSOR_DEPLOY_HATCH_MIDDLE_ROCKET = -466.0;
    public static final double ARM_EXTENSOR_DEPLOY_HATCH_UPPER_ROCKET = -14854.0;
    public static final double ARM_EXTENSOR_GRAB_FROM_FLOOR = -4135.0;
    public static final double ARM_EXTENSOR_TRANSPORT = 0.0;
    public static final double ARM_EXTENSOR_GRAB_FROM_FEEDER = 0.0;

    // Offsets

    public static final double ARM_EXTENSOR_UPPER_OFFSET = 0;
    public static final double ARM_EXTENSOR_LOWER_OFFSET = -18984;
    public static final double ARM_EXTENSOR_DIFFERENCE_OFFSET = 0;
    public static final double ARM_EXTENSOR_MAX_DISTANCE = 2000;
    public static final double ARM_EXTENSOR_ARRIVE_OFFSET = 200;
    public static final double ARM_EXTENSOR_CORRECT_MAX_POWER = 0;
    public static final double ARM_EXTENSOR_MAX_POWER = 0.8;

    //// Angler

    // Positions

    public static final double ARM_ANGLER_DEPLOY_BALL_CARGO = 0;
    public static final double ARM_ANGLER_DEPLOY_HATCH_CARGO = -1000;
    public static final double ARM_ANGLER_DEPLOY_BALL_LOWER_ROCKET = 125627.0;
    public static final double ARM_ANGLER_DEPLOY_BALL_MIDDLE_ROCKET = -5501.0;
    public static final double ARM_ANGLER_DEPLOY_BALL_UPPER_ROCKET = -30000.0;
    public static final double ARM_ANGLER_DEPLOY_HATCH_LOWER_ROCKET = 163710.0;
    public static final double ARM_ANGLER_DEPLOY_HATCH_MIDDLE_ROCKET = -14406.0;
    public static final double ARM_ANGLER_DEPLOY_HATCH_UPPER_ROCKET = -58667.0;
    public static final double ARM_ANGLER_GRAB_FROM_FLOOR = 190000.0;
    public static final double ARM_ANGLER_START_CONFIGURATION = 0.0;
    public static final double ARM_ANGLER_TRANSPORT = 0.0;
    public static final double ARM_ANGLER_GRAB_FROM_FEEDER = 53452.0;

    // Offsets

    public static final double ARM_ANGLER_LEFT_LOWER_OFFSET = -61100.0;
    public static final double ARM_ANGLER_LEFT_UPPER_OFFSET = 219687.0;
    public static final double ARM_ANGLER_RIGHT_LOWER_OFFSET = -78978.0;
    public static final double ARM_ANGLER_RIGHT_UPPER_OFFSET = 137424.0;
    public static final double ARM_ANGLER_DIFFERENCE_OFFSET = 50;
    public static final double ARM_ANGLER_MAX_DISTANCE = 20000;
    public static final double ARM_ANGLER_ARRIVE_OFFSET = 1000;
    public static final double ARM_ANGLER_CORRECT_MAX_POWER = 0;
    public static final double ARM_ANGLER_MAX_POWER = 0.75;

    public static final int RUMBLE_ANGLER_ENCODER_VALUE = 200000;

    //// Wrist

    // Positions

    public static final double ARM_WRIST_TRANSPORT = 0.0;
    public static final double ARM_WRIST_DEPLOY_HATCH_LOWER_ROCKET = 4040.0;
    public static final double ARM_WRIST_DEPLOY_BALL_LOWER_ROCKET = 1307.0;

    public static final double ARM_WRIST_DEPLOY_BALL_MIDDLE_ROCKET = 629.0;
    public static final double ARM_WRIST_DEPLOY_HATCH_MIDDLE_ROCKET = 2822.0;

    public static final double ARM_WRIST_DEPLOY_BALL_UPPER_ROCKET = 842.0;
    public static final double ARM_WRIST_DEPLOY_HATCH_UPPER_ROCKET = 2523.0;

    public static final double ARM_WRIST_BOTTOM_BALL_POSITION = 910.0;
    public static final double ARM_WRIST_BOTTOM_HATCH_POSITION = 2575.0;
    public static final double ARM_WRIST_GRAB_BALL_FLOOR = 64.0;
    public static final double ARM_WRIST_GRAB_FROM_FEEDER = 1604.0;

    // Offsets

    public static final double ARM_WRIST_UPPER_OFFSET = 3649.0;
    public static final double ARM_WRIST_LOWER_OFFSET = 0.0;
    public static final double ARM_WRIST_MAX_DISTANCE = 500;
    public static final double ARM_WRIST_ARRIVE_OFFSET = 100;
    public static final double ARM_WRIST_MAX_POWER = 0.75;

    public static final double DEFAULT_MOTOR_MAX_POWER = 1;

    public static final double CHASSIS_TURN_MAX_DISTANCE = 12;
    public static final double CHASSIS_TURN_ARRIVE_OFFSET = 6;

    public static final double ARM_ANGLER_HATCH1_PERCENTAGE = 0.15;
    public static final double ARM_ANGLER_HATCH2_PERCENTAGE = 0.35;
    public static final double ARM_ANGLER_HATCH3_PERCENTAGE = 0.55;
    public static final double ARM_ANGLER_CARGO1_PERCENTAGE = 0.55;
    public static final double ARM_ANGLER_CARGO2_PERCENTAGE = 0.75;
    public static final double ARM_ANGLER_CARGO3_PERCENTAGE = 0.95;
    public static final double ARM_ZERO_PERCENTAGE = 0.05;

    public static final int RUMBLE_EXTENDER_ENCODER_VALUE = -2500;

    public static final double CLAW_0 = 0.05;
    public static final double CLAW_90 = 0.05;
    public static final double CLAW_180 = 0.05;

    public static final double ARM_EXTENDER_ZERO = 0.10;
    public static final double ARM_EXTENDER_HATCH3 = 0.7;
    public static final double ARM_EXTENDER_CARGO2 = 0.5;
    public static final double ARM_EXTENDER_CARGO3 = 0.9;
    public static final double CORRECTION_COEFFICIENT = 0.055;

    public enum ROLLER_STATES {
        OFF, CARGO_IN, CARGO_OUT, HATCH_IN, HATCH_OUT
    }

    // public static float STRAIGHT_P = 1.3672f;
    // public static float STRAIGHT_I = .3593f;
    // public static float STRAIGHT_D = .0898f;

    public static float STRAIGHT_P = 1.08881027038789f / 12;
    public static float STRAIGHT_I = 14.042820266636f / 12;
    public static float STRAIGHT_D = .0209510441814581f / 12;
    public static float STRAIGHT_TOLERANCE = 1000f;
    public static float STRAIGHT_START_REDUCING_SPEED_POINT = 30000;

    // public static float straight_P = 1.58950784091104f;
    // public static float straight_I = 16.1648949353988f;
    // public static float straight_D = .02348467516511333f;

    public static float TURNING_P = 1.58950784091104f;
    public static float TURNING_I = 16.1648949353988f;
    public static float TURNING_D = .02348467516511333f;
    public static float TURNING_TOLERANCE = 8;
    public static float TURNING_START_REDUCING_SPEED_POINT = 16;

    // The equivalence between meters to encoder count
    // Meter * meters_to_encoder = encoder count
    public static float K_METERS_TO_ENCODER = (float) (30000 / (.2034 * Math.PI));
    // public static float k_meters_to_encoder = (float)
    // (RobotMap.k_tic_per_revolution / (RobotMap.k_wheel_diameter * Math.PI));
    public static int K_CHASSIS_TIC_PER_REVOLUTION = 30000;
    public static float K_WHEEL_DIAMETER = .2032f;
}