/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.watcher;

/**
 * A subsystem whose state needs to be checked
 */

public interface WatchableSubsystem {
    public enum State{
        GOOD, WARNING, DANGER
    }

    /**
     * This method must be used by the watcher to check the state of the mecanism
     */

    public void check();

    public void correct();

    public void setGood();

    public void setWarning();

    public void setDisabled();

    public State getState();

    public String getSubsystemName();

}
