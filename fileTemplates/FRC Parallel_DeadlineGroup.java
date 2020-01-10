/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.fileTemplates;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ${Parallel_Deadline_Group_Name} extends ParallelDeadlineGroup {
  /**
   * Creates a new Parallel_DeadlineGroup.
   */
  public ${Parallel_Deadline_Group_Name}() {
    // Add your commands in the super() call.  Add the deadline first.
    super(
        new InstantCommand()
    );
  }
}
