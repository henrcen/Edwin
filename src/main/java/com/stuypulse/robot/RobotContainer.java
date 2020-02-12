/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.stuypulse.robot;

import com.stuypulse.robot.commands.ChimneyDownCommand;
import com.stuypulse.robot.commands.ControlPanelManualControlCommand;
import com.stuypulse.robot.commands.ControlPanelSpinToColorCommand;
import com.stuypulse.robot.commands.ControlPanelTurnRevolutionsCommand;
import com.stuypulse.robot.commands.IntakeAcquireCommand;
import com.stuypulse.robot.commands.IntakeDeacquireCommand;
import com.stuypulse.robot.commands.IntakeRetractCommand;
import com.stuypulse.robot.commands.ShooterControlCommand;
import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Climber;
import com.stuypulse.robot.subsystems.ControlPanel;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;
import com.stuypulse.robot.subsystems.Shooter;
import com.stuypulse.robot.util.LEDController;
import com.stuypulse.stuylib.input.WPIGamepad;
import com.stuypulse.stuylib.input.gamepads.Logitech;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled isn the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
public class RobotContainer {

  private final Funnel funnel = new Funnel();
  private final Climber climber = new Climber();
  private final Intake intake = new Intake();
  private final ControlPanel controlPanel = new ControlPanel();
  private final Shooter shooter = new Shooter();
  private final Chimney chimney = new Chimney();

  private final LEDController controller = new LEDController(0, shooter);

  private final WPIGamepad operatorGamepad = new Logitech.XMode(Constants.OPERATOR_GAMEPAD_PORT);

  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    controlPanel.setDefaultCommand(new ControlPanelManualControlCommand(controlPanel, operatorGamepad));
    shooter.setDefaultCommand(new ShooterControlCommand(shooter, operatorGamepad));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    operatorGamepad.getLeftTrigger().whileHeld(new IntakeDeacquireCommand(intake));
    operatorGamepad.getRightTrigger().whileHeld(new IntakeAcquireCommand(intake));
    operatorGamepad.getLeftBumper().whenPressed(new ControlPanelSpinToColorCommand(controlPanel));
    operatorGamepad.getRightTrigger().whenPressed(new ControlPanelTurnRevolutionsCommand(controlPanel));
    operatorGamepad.getTopButton().whileHeld(new ChimneyDownCommand(chimney));
    operatorGamepad.getLeftButton().whileHeld(new ChimneyDownCommand(chimney));
    operatorGamepad.getRightButton().whenPressed(new IntakeRetractCommand(intake));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public LEDController getLEDController() {
    return controller;
  }

}
