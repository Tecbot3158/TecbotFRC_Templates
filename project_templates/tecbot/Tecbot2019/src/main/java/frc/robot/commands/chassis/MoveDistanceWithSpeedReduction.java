/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.resources.TecbotConstants;

public class MoveDistanceWithSpeedReduction extends CommandGroup {
    /**
     * Moves a certain distance using speed reduction
     */
    public MoveDistanceWithSpeedReduction(double d, float targetPower) {

        addSequential(new ChassisMoveDistanceWithSpeedReduction(Robot.leftEncoder,
                TecbotConstants.STRAIGHT_START_REDUCING_SPEED_POINT, TecbotConstants.STRAIGHT_TOLERANCE, d,
                targetPower));
        /*
         * addParallel(new ChassisMoveDistanceWithSpeedReduction(Robot.rightEncoder,
         * false, TecbotConstants.STRAIGHT_START_REDUCING_SPEED_POINT,
         * TecbotConstants.STRAIGHT_TOLERANCE, distance, targetPower));
         */
    }

    public MoveDistanceWithSpeedReduction(double d, float targetPower, float angle) {

        addSequential(new ChassisMoveDistanceWithSpeedReduction(Robot.leftEncoder,
                TecbotConstants.STRAIGHT_START_REDUCING_SPEED_POINT, TecbotConstants.STRAIGHT_TOLERANCE, targetPower,
                angle));
        /*
         * addParallel(new ChassisMoveDistanceWithSpeedReduction(Robot.rightEncoder,
         * false, TecbotConstants.STRAIGHT_START_REDUCING_SPEED_POINT,
         * TecbotConstants.STRAIGHT_TOLERANCE, distance, targetPower, angle));
         */
    }
}
