package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeAcquireCommand extends CommandBase {
  private final Intake intake;

  public IntakeAcquireCommand(Intake intake) {
    this.intake = intake;
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    System.out.println("Initializing IntakeAcquireCommand");
    intake.extend();
  }

  @Override
  public void execute() {
      System.out.println("Running IntakeAcquireCommand");
      intake.acquire();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Ending IntakeAcqureCommand");
    intake.stop();
  }
}