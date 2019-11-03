/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.automatic;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.angler.MoveAnglerTwoPositions;
import frc.robot.commands.arm.extensor.MoveExtensorTwoPositions;
import frc.robot.commands.arm.wrist.MoveWristTwoPositions;
import frc.robot.resources.TecbotConstants;

public class GoTransportConfiguration extends CommandGroup {
    /**
     * Add your docs here.
     */
    public GoTransportConfiguration(boolean movingUpwards) {
        if (movingUpwards) {
            addSequential(new MoveAnglerTwoPositions(TecbotConstants.ARM_ANGLER_START_CONFIGURATION,
                    TecbotConstants.ARM_ANGLER_START_CONFIGURATION, TecbotConstants.ARM_ANGLER_MAX_POWER));
            // addSequential(new
            // MoveExtensorTwoPositions(TecbotConstants.ARM_EXTENSOR_TRANSPORT,
            // TecbotConstants.ARM_EXTENSOR_TRANSPORT,
            // TecbotConstants.ARM_EXTENSOR_MAX_POWER));
            addSequential(new MoveWristTwoPositions(TecbotConstants.ARM_WRIST_TRANSPORT,
                    TecbotConstants.ARM_WRIST_TRANSPORT, TecbotConstants.ARM_WRIST_MAX_POWER));
        } else {
            addSequential(new MoveWristTwoPositions(TecbotConstants.ARM_WRIST_TRANSPORT,
                    TecbotConstants.ARM_WRIST_TRANSPORT, TecbotConstants.ARM_WRIST_MAX_POWER));
            // addSequential(new
            // MoveExtensorTwoPositions(TecbotConstants.ARM_EXTENSOR_TRANSPORT,
            // TecbotConstants.ARM_EXTENSOR_TRANSPORT,
            // TecbotConstants.ARM_EXTENSOR_MAX_POWER));
            addSequential(new MoveAnglerTwoPositions(TecbotConstants.ARM_ANGLER_START_CONFIGURATION,
                    TecbotConstants.ARM_ANGLER_START_CONFIGURATION, TecbotConstants.ARM_ANGLER_MAX_POWER));
        }
    }
}
