package frc.robot;

import java.text.RuleBasedCollator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Intake implements Loggable {
  
  private static Intake SINGLE_INSTANCE = new Intake();
  
  private static WPI_TalonFX intakeDrive;
  private static WPI_TalonFX indexBottom;
  private static WPI_TalonFX indexTop;
  private static WPI_TalonFX indexShooter;
  private static DoubleSolenoid intakeSolenoid;
  // SWITCHES: GREEN = NOT PRESSED, RED = PRESSED
  private static DigitalInput bottomLimitSwitch;
  private static DigitalInput topLimitSwitch;

    public static Intake getInstance() {
        return SINGLE_INSTANCE;
    }

    public void init(){
      intakeDrive  = new WPI_TalonFX(13);
      indexBottom = new WPI_TalonFX(16);
      indexTop = new WPI_TalonFX(12);
      indexShooter = new WPI_TalonFX(0);

      intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 5, 7);

      bottomLimitSwitch = new DigitalInput(1);
      topLimitSwitch = new DigitalInput(0);
    }

    public void intakePneumatics() {
      if (Robot.xbox.getXButton() == true){
        intakeSolenoid.set(Value.kReverse);
      } else {
        intakeSolenoid.set(Value.kForward);
      }
    }

    public void spinIntake() { 
        // Actually make the motors spin on button press
      if (Robot.xbox.getRightTriggerAxis() > 0){
        intakeDrive.set(ControlMode.PercentOutput, .5);
      } else if(Robot.xbox.getYButton()) {
        intakeDrive.set(ControlMode.PercentOutput, -.5);  
      } else {
        intakeDrive.set(ControlMode.PercentOutput, 0);
      }
    }

    public void crapIndex() {
      if (Robot.xbox.getLeftTriggerAxis() > 0) {
        if (topLimitSwitch.get()) {
          indexBottom.set(ControlMode.PercentOutput, 0);
        } else {
          indexBottom.set(ControlMode.PercentOutput, 0.5);
          indexTop.set(ControlMode.PercentOutput, 0.5);
        }
        
      } else if (Robot.xbox.getRightTriggerAxis() > 0) {
        indexBottom.set(ControlMode.PercentOutput, 0.5);
        indexTop.set(ControlMode.PercentOutput, 0.5);
        indexShooter.set(ControlMode.PercentOutput, 0.6);
      } else {
        indexTop.set(ControlMode.PercentOutput, 0);
        indexBottom.set(ControlMode.PercentOutput, 0);
        indexShooter.set(ControlMode.PercentOutput, 0);
      }
    }

    // NOT ACTIVE CODE RIGHT NOW
    public void indexerDrive() {
      // put method in as key
        switch (indexManager()) {
          case 1:
            indexBottom.set(ControlMode.PercentOutput, 0.5);
            indexTop.set(ControlMode.PercentOutput, 0);
            
            break;

           case 2:
            indexBottom.set(ControlMode.PercentOutput, 0); 
            indexTop.set(ControlMode.PercentOutput, 0);
            break;

          case 3:
            indexTop.set(ControlMode.PercentOutput, 0.5);
          default:
            indexBottom.set(ControlMode.PercentOutput, 0.5);
            indexTop.set(ControlMode.PercentOutput, 0);
            break;
        }
      } 

    public int indexManager() {
      if (topLimitSwitch.get()){
        return 1;
      }
      else if (bottomLimitSwitch.get() && topLimitSwitch.get()){
        return 2;
      } else if (bottomLimitSwitch.get()){
        return 3;
      } else {
        return 0;
      }
    }

    @Log.BooleanBox(rowIndex = 1, columnIndex = 2)
    public boolean getBottomLimitSwitch() {
      return bottomLimitSwitch.get();
    }

    @Log.BooleanBox(rowIndex = 3, columnIndex = 4)
    public boolean getTopLimitSwitch() {
      return topLimitSwitch.get();
    }
    
    
}
