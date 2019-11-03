/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.automatic.DeployBallHigherRocket;
import frc.robot.commands.automatic.DeployBallLowerRocket;
import frc.robot.commands.automatic.DeployBallMiddleRocket;
import frc.robot.commands.automatic.GoStartingConfiguration;
import frc.robot.commands.automatic.GoTransportConfiguration;
import frc.robot.commands.automatic.GrabFromFeeder;
import frc.robot.commands.automatic.GrabFromFloor;
import frc.robot.resources.TecbotConstants;

/**
 * 
 */
public class CommandHandler {

    public static int STARTING_CONFIGURATION = 0;
    public static int BOTTOM_CONFIGURATION = 1;
    public static int MIDDLE_CONFIGURATION = 2;
    public static int TOP_CONFIGURATION = 3;
    public static int GRAB_CONFIGURATION = 4;
    public static int FEEDER_CONFIGURATION = 5;

    public static int CARGO = 0;
    public static int HATCH = 1;
    public static int UP = 0;
    public static int DOWN = 1;

    static int actualState = 0;
    static int actualPiece = 0;

    static double[][] states = new double[6][2];

    static Command[][] commands = new Command[6][2];

    static Command currentCommand;

    public static Command actualCommand;

    public static void setConfiguration() {

        states[CommandHandler.STARTING_CONFIGURATION][CARGO] = TecbotConstants.ARM_ANGLER_START_CONFIGURATION;
        states[CommandHandler.BOTTOM_CONFIGURATION][CARGO] = TecbotConstants.ARM_ANGLER_DEPLOY_BALL_LOWER_ROCKET;
        states[CommandHandler.MIDDLE_CONFIGURATION][CARGO] = TecbotConstants.ARM_ANGLER_DEPLOY_BALL_MIDDLE_ROCKET;
        states[CommandHandler.TOP_CONFIGURATION][CARGO] = TecbotConstants.ARM_ANGLER_DEPLOY_BALL_UPPER_ROCKET;
        states[CommandHandler.GRAB_CONFIGURATION][CARGO] = TecbotConstants.ARM_ANGLER_GRAB_FROM_FLOOR;
        states[CommandHandler.FEEDER_CONFIGURATION][CARGO] = TecbotConstants.ARM_ANGLER_GRAB_FROM_FEEDER;

        states[CommandHandler.STARTING_CONFIGURATION][HATCH] = TecbotConstants.ARM_ANGLER_START_CONFIGURATION;
        states[CommandHandler.BOTTOM_CONFIGURATION][HATCH] = TecbotConstants.ARM_ANGLER_DEPLOY_HATCH_LOWER_ROCKET;
        states[CommandHandler.MIDDLE_CONFIGURATION][HATCH] = TecbotConstants.ARM_ANGLER_DEPLOY_HATCH_MIDDLE_ROCKET;
        states[CommandHandler.TOP_CONFIGURATION][HATCH] = TecbotConstants.ARM_ANGLER_DEPLOY_HATCH_UPPER_ROCKET;
        states[CommandHandler.GRAB_CONFIGURATION][HATCH] = TecbotConstants.ARM_ANGLER_GRAB_FROM_FLOOR;
        states[CommandHandler.FEEDER_CONFIGURATION][HATCH] = TecbotConstants.ARM_ANGLER_GRAB_FROM_FEEDER;

        commands[CommandHandler.STARTING_CONFIGURATION][UP] = new GoTransportConfiguration(true);
        commands[CommandHandler.BOTTOM_CONFIGURATION][UP] = new DeployBallLowerRocket(true);
        commands[CommandHandler.MIDDLE_CONFIGURATION][UP] = new DeployBallMiddleRocket(true);
        commands[CommandHandler.TOP_CONFIGURATION][UP] = new DeployBallHigherRocket(true);
        commands[CommandHandler.GRAB_CONFIGURATION][UP] = new GrabFromFloor();
        commands[CommandHandler.FEEDER_CONFIGURATION][UP] = new GrabFromFeeder(true);

        commands[CommandHandler.STARTING_CONFIGURATION][DOWN] = new GoTransportConfiguration(false);
        commands[CommandHandler.BOTTOM_CONFIGURATION][DOWN] = new DeployBallLowerRocket(false);
        commands[CommandHandler.MIDDLE_CONFIGURATION][DOWN] = new DeployBallMiddleRocket(false);
        commands[CommandHandler.TOP_CONFIGURATION][DOWN] = new DeployBallHigherRocket(false);
        commands[CommandHandler.GRAB_CONFIGURATION][DOWN] = new GrabFromFloor();
        commands[CommandHandler.FEEDER_CONFIGURATION][DOWN] = new GrabFromFeeder(false);

    }

    public static void goToState(int state) {
        int piece = CARGO;
        if (Robot.angler.getHatchBoolean()) {
            piece = HATCH;
        }

        if (states[state][piece] < states[actualState][actualPiece]) {
            commands[state][UP].start();
            currentCommand = commands[state][UP];
        } else {
            commands[state][DOWN].start();
            currentCommand = commands[state][DOWN];
        }
        actualState = state;
        actualPiece = piece;
    }

    public static void cancelCommand() {
        if (currentCommand != null)
            currentCommand.cancel();
    }

}
