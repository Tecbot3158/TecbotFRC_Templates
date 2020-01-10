/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.fileTemplates;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;

public class ${Profiled_PID_Subsystem_Name} extends ProfiledPIDSubsystem {
  /**
   * Creates a new Profiled_PID_Subsystem.
   */
  public ${Profiled_PID_Subsystem_Name}() {
    super(
        // The ProfiledPIDController used by the subsystem
        new ProfiledPIDController(0, 0, 0,
                                  // The motion profile constraints
                                  new TrapezoidProfile.Constraints(0, 0)));
  }

  @Override
  public void useOutput(double output, TrapezoidProfile.State setpoint) {
    // Use the output (and optionally the setpoint) here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
