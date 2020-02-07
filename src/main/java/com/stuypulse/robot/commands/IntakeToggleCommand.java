package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.InstantCommand;;

public class IntakeToggleCommand extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake intake;

  public IntakeToggleCommand(Intake intake) {
    this.intake = intake;
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    if(intake.isExtended()) {
      intake.retract();
      System.out.println("IntakeToggleCommand: Retracted Intake.");
    }
    else {
      intake.extend();
      System.out.println("IntakeToggleCommand: Extended Intake.");
    }
  }
}