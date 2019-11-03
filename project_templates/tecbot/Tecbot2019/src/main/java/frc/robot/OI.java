package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.arm.ChangeHatchBoolean;

import frc.robot.commands.arm.angler.MoveAngler;

import frc.robot.commands.arm.angler.ResetAnglerEncoder;

import frc.robot.commands.arm.extensor.MoveExtensor;

import frc.robot.commands.arm.extensor.ResetExtensorEncoders;
import frc.robot.commands.arm.extensor.ToggleManualMovement;
import frc.robot.commands.arm.wrist.CloseClaw;

import frc.robot.commands.arm.wrist.MoveWrist;

import frc.robot.commands.arm.wrist.OpenClaw;

import frc.robot.commands.arm.wrist.ResetWristEncoder;
import frc.robot.commands.automatic.CancelCommands;
import frc.robot.commands.automatic.DeployBallHigherRocket;

import frc.robot.commands.automatic.DeployBallLowerRocket;

import frc.robot.commands.automatic.DeployBallMiddleRocket;

import frc.robot.commands.automatic.GoStartingConfiguration;

import frc.robot.commands.automatic.GoTransportConfiguration;

import frc.robot.commands.autonomous.*;

import frc.robot.commands.chassis.ResetGyro;

import frc.robot.commands.chassis.SetTransmissionOff;

import frc.robot.commands.chassis.SetTransmissionOn;

import frc.robot.commands.chassis.ToggleTransmission;

import frc.robot.commands.CancelArm;
import frc.robot.commands.CommandHandler;
import frc.robot.commands.CommandHandlerStateModify;
import frc.robot.resources.TecbotConstants;

public class OI {

	/**
	 * TODO extensor a los triggers ^/ Rollers a eje x de derecha Solo cuando sea
	 * mayor a .8 ^/ Angler a eje y derecha Solo cuando sea mayor a .8 y solo se
	 * prende en .6 ^/ Quitar extensor de los automaticos
	 */

	public boolean ps4 = RobotMap.isUsingPS4Controller;

	public Joystick pilot, copilot, tester, third;

	public JoystickButton start, back, a, b, x, y, rt, lt, rb, lb, select, ls, rs, middleButton;

	public JoystickButton a_tester, b_tester, x_tester, y_tester, rb_tester, lb_tester;

	public JoystickButton a_copilot, y_copilot, b_copilot, rb_copilot, lb_copilot;

	public boolean isShift;

	public OI() {

		pilot = new Joystick(0);

		third = new Joystick(3);

		// Xbox

		// a = new JoystickButton(pilot, 1);

		// b = new JoystickButton(pilot, 2);

		// x = new JoystickButton(pilot, 3);

		// y = new JoystickButton(pilot, 4);

		// lb = new JoystickButton(pilot, 5);

		// rb = new JoystickButton(pilot, 6);

		// select = new JoystickButton(pilot, 7);

		// start = new JoystickButton(pilot, 8);

		// Playstation

		a = new JoystickButton(pilot, 2);

		b = new JoystickButton(pilot, 3);

		y = new JoystickButton(pilot, 4);

		x = new JoystickButton(pilot, 1);

		lb = new JoystickButton(pilot, 5);

		rb = new JoystickButton(pilot, 6);

		ls = new JoystickButton(pilot, 11);

		rs = new JoystickButton(pilot, 12);

		select = new JoystickButton(pilot, 9);

		start = new JoystickButton(pilot, 10);

		middleButton = new JoystickButton(pilot, 13);

		// start.whenPressed(new GoStartingConfiguration(true));
		/*
		 * // start.whenPressed(new GoTransportConfiguration()); start.whenPressed(new
		 * CommandHandlerStateModify(CommandHandler.STARTING_CONFIGURATION)); //
		 * a.whenPressed(new DeployBallLowerRocket()); a.whenPressed(new
		 * CommandHandlerStateModify(CommandHandler.BOTTOM_CONFIGURATION)); //
		 * b.whenPressed(new DeployBallMiddleRocket()); b.whenPressed(new
		 * CommandHandlerStateModify(CommandHandler.MIDDLE_CONFIGURATION)); //
		 * y.whenPressed(new DeployBallHigherRocket()); y.whenPressed(new
		 * CommandHandlerStateModify(CommandHandler.TOP_CONFIGURATION));
		 * 
		 * x.whenPressed(new ChangeHatchBoolean());
		 */
		// TODO quitar el comentario para que jalen los comandos automaticos

		lb.whenPressed(new OpenClaw());

		rb.whenPressed(new CloseClaw());

		middleButton.whenPressed(new ToggleManualMovement());

		ls.whenPressed(new ToggleTransmission());

		rs.whenPressed(new CancelCommands());
		// a.whenPressed(new ResetGyro());

		// b.whenPressed(new MoveAngler(TecbotConstants.ARM_ANGLER_DEPLOY_HATCH_CARGO,
		// TecbotConstants.ARM_ANGLER_MAX_POWER));

		// x.whenPressed(new MoveAngler(TecbotConstants.ARM_ANGLER_TRANSPORT,
		// TecbotConstants.ARM_ANGLER_MAX_POWER));

		// y.whenPressed(new CancelArm());

		// lb.whenPressed(new
		// MoveAngler(TecbotConstants.ARM_ANGLER_DEPLOY_BALL_LOWER_ROCKET,
		// TecbotConstants.ARM_ANGLER_MAX_POWER));

		// rb.whenPressed(new
		// MoveAngler(TecbotConstants.ARM_ANGLER_DEPLOY_BALL_MIDDLE_ROCKET,
		// TecbotConstants.ARM_ANGLER_MAX_POWER));

		copilot = new Joystick(1);

		// a_copilot = new JoystickButton(copilot, 1);

		// b_copilot = new JoystickButton(copilot, 2);

		a_copilot = new JoystickButton(copilot, 1);

		y_copilot = new JoystickButton(copilot, 4);

		b_copilot = new JoystickButton(copilot, 2);

		lb_copilot = new JoystickButton(copilot, 5);

		rb_copilot = new JoystickButton(copilot, 6);

		a_copilot.whenPressed(new OpenClaw());

		b_copilot.whenPressed(new CloseClaw());

		// a_copilot.whenPressed(new ResetExtensorEncoders());

		// b_copilot.whenPressed(new
		// MoveExtensor(TecbotConstants.ARM_EXTENSOR_UP_POSITION,
		// TecbotConstants.ARM_EXTENSOR_MAX_POWER));

		// tester = new Joystick(2);

		// a_tester = new JoystickButton(tester, 1);

		// b_tester = new JoystickButton(tester, 2);

		// x_tester = new JoystickButton(tester, 3);

		// y_tester = new JoystickButton(tester, 4);

		// lb_tester = new JoystickButton(tester, 5);

		// rb_tester = new JoystickButton(tester, 6);

		// a_tester.whenPressed(new ResetWristEncoder());

		// lb_tester.whenPressed(new OpenClaw());

		// rb_tester.whenPressed(new CloseClaw());

		/*
		 * 
		 * if (ps4) { x = new JoystickButton(pilot, 1); a = new JoystickButton(pilot,
		 * 
		 * 2); b = new JoystickButton(pilot, 3); y = new JoystickButton(pilot, 4); lb =
		 * 
		 * new JoystickButton(pilot, 5); rb = new JoystickButton(pilot, 6); back = new
		 * 
		 * JoystickButton(pilot, 9); start = new JoystickButton(pilot, 10);
		 * 
		 * 
		 * 
		 * lb.whenPressed(new ToggleShift()); lb.whenReleased(new ToggleShift());
		 * 
		 * 
		 * 
		 * a.whenPressed(new CargoHatch()); b.whenPressed(new CargoHatchLv2());
		 * 
		 * y.whenPressed(new CargoHatchLv3());
		 * 
		 * 
		 * 
		 * rb.whenPressed(new PickHatch()); // R1.whenPressed(new ClawTeleop()); //
		 * 
		 * R2.whenPressed(new ExtenderTeleop()); } else {
		 * 
		 * 
		 * 
		 * a = new JoystickButton(pilot, 1); b = new JoystickButton(pilot, 2); x = new
		 * 
		 * JoystickButton(pilot, 3); y = new JoystickButton(pilot, 4); lb = new
		 * 
		 * JoystickButton(pilot, 5); rb = new JoystickButton(pilot, 6); back = new
		 * 
		 * JoystickButton(pilot, 7); start = new JoystickButton(pilot, 8);
		 * 
		 * 
		 * 
		 * lb.whenPressed(new ToggleShift()); lb.whenReleased(new ToggleShift());
		 * 
		 * 
		 * 
		 * a.whenPressed(new CargoHatch()); b.whenPressed(new CargoHatchLv2());
		 * 
		 * x.whenPressed(new CargoHatchLv3());
		 * 
		 * 
		 * 
		 * // L2.whenPressed(new PickHatch()); rb.whenPressed(new ClawTeleop()); //
		 * 
		 * R2.whenPressed(new ExtenderTeleop());
		 * 
		 * 
		 * 
		 * }
		 * 
		 */

	}

	public Joystick getPilot() {

		return pilot;

	}

	public Joystick getCopilot() {

		return copilot;

	}

	public Joystick getTester() {

		return tester;

	}

	public Joystick getThird() {
		return third;
	}

	public double getPilotRightStickX() {

		if (ps4) {

			return pilot.getRawAxis(2);

		} else {

			return pilot.getRawAxis(4);

		}

	}

	public boolean getCopilotAIsActive() {

		return a_copilot.get();

	}

	public boolean getCopilotRBIsActive() {

		return rb_copilot.get();

	}

	public boolean getCopilotLBIsActive() {

		return lb_copilot.get();

	}

	public boolean getCopilotYIsActive() {

		return y_copilot.get();

	}

	public boolean getPilotAIsActive() {

		return a.get();

	}

	public boolean getPilotYIsActive() {

		return y.get();

	}

	public double getPilotRightStickY() {

		return pilot.getRawAxis(5);

	}

	public double getPilotTriggers() {
		// Xbox Controller
		return -(pilot.getRawAxis(3) + 1) / 2 + (pilot.getRawAxis(2) + 1) / 2;

	}

	public double getCopilotTriggers() {

		return -copilot.getRawAxis(2) + copilot.getRawAxis(3);

	}

	public double getTesterTriggers() {

		return -tester.getRawAxis(2) + tester.getRawAxis(3);

	}

}