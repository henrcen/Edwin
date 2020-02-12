package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeDeacquireCommand extends CommandBase {
  private final Intake intake;

  public IntakeDeacquireCommand(Intake intake) {
    this.intake = intake;
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    System.out.println("Initializing IntakeDeacquireCommand");
    intake.extend();
  }

  @Override
  public void execute() {
    System.out.println("Running IntakeDeacquireCommand");
    intake.deacquire();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Ending IntakeDeacquireCommand");
    intake.stop();
  }
}