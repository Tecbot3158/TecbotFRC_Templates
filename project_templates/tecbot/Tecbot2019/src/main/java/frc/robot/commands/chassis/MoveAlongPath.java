package frc.robot.commands.chassis;

import frc.robot.Robot;
import frc.robot.resources.TecbotConstants;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class MoveAlongPath extends Command {

    EncoderFollower m_left_follower, m_right_follower;

    String k_path_name;

    Notifier follower_notifier;

    public MoveAlongPath(String name) {
        requires(Robot.driveTrain);
        k_path_name = name;
    }

    protected void initialize() {

        Trajectory leftTrajectory, rightTrajectory;
        leftTrajectory = PathfinderFRC.getTrajectory(k_path_name + ".left");
        rightTrajectory = PathfinderFRC.getTrajectory(k_path_name + ".right");
        System.out.print(leftTrajectory.segments.length);

        m_left_follower = new EncoderFollower(leftTrajectory);
        m_right_follower = new EncoderFollower(rightTrajectory);

        m_left_follower.configureEncoder(0, TecbotConstants.K_CHASSIS_TIC_PER_REVOLUTION,
                TecbotConstants.K_WHEEL_DIAMETER);
        m_right_follower.configureEncoder(0, TecbotConstants.K_CHASSIS_TIC_PER_REVOLUTION,
                TecbotConstants.K_WHEEL_DIAMETER);

        /*
         * m_left_follower.configurePIDVA(TecbotConstants.STRAIGHT_P,
         * TecbotConstants.STRAIGHT_I, TecbotConstants.STRAIGHT_D, .5, 1);
         * m_right_follower.configurePIDVA(TecbotConstants.STRAIGHT_P,
         * TecbotConstants.STRAIGHT_I, TecbotConstants.STRAIGHT_D, .5, 1);
         */
        // m_left_follower.configurePIDVA(1, 0, 0, 1 / 0.6, 0);
        // m_right_follower.configurePIDVA(1, 0, 0, 1 / 0.6, 0);

        m_left_follower.configurePIDVA(1, 0, 0, 0, 0);
        m_right_follower.configurePIDVA(1, 0, 0, 0, 0);

        follower_notifier = new Notifier(this::followPath);
        follower_notifier.startPeriodic(leftTrajectory.get(0).dt);
    }

    protected void followPath() {
        double deltaAngle = Pathfinder.r2d(m_left_follower.getHeading()) - Robot.tecbotgyro.getYaw();

        double leftSpeed = m_left_follower.calculate(-Robot.leftEncoder.getRaw());
        double rightSpeed = m_right_follower.calculate(Robot.rightEncoder.getRaw());

        double turn = 0.8 * (-1.0 / 80.0) * deltaAngle;
        Robot.driveTrain.driveSideLeft(-leftSpeed + turn);
        Robot.driveTrain.driveSideRight(rightSpeed + turn);
        System.out.println("right" + rightSpeed + " pos " + m_right_follower.getSegment().position);
        System.out.println("left " + leftSpeed + " pos " + m_left_follower.getSegment().position);
        System.out.println(
                " left pos " + (Robot.leftEncoder.getRaw() / 30000.0 * .6) + " enc " + Robot.leftEncoder.getRaw());
        System.out.println(" right enc " + Robot.rightEncoder.getRaw());
    }

    protected void execute() {
        /*
         * double deltaAngle = Pathfinder.r2d(m_left_follower.getHeading()) -
         * Robot.tecbotgyro.getYaw();
         * 
         * double leftSpeed = m_left_follower.calculate(Robot.leftEncoder.getRaw());
         * double rightSpeed = m_right_follower.calculate(Robot.rightEncoder.getRaw());
         * 
         * double turn = 0.8 * (-1.0 / 80.0) * deltaAngle;
         * Robot.driveTrain.driveSideLeft(-leftSpeed + turn);
         * Robot.driveTrain.driveSideRight(rightSpeed + turn);
         * System.out.println(rightSpeed);
         */

    }

    protected boolean isFinished() {
        return (m_left_follower.isFinished() || m_right_follower.isFinished());
    }

    protected void end() {
        follower_notifier.stop();
    }

    protected void interrupted() {
        follower_notifier.stop();
    }

}