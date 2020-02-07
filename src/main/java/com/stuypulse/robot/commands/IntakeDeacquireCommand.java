package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeDeacquireCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake intake;

  public IntakeDeacquireCommand(Intake intake) {
    this.intake = intake;
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    if(!intake.isExtended()) {
      intake.extend();
      System.out.println("IntakDeacquireCommand: Intake is extended.");
    }
  }

  @Override
  public void execute() {
      intake.deacquire();
      System.out.println("IntakDeacquireCommand: Deacquired game piece.");
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
}