package frc.robot;

import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;

public class AutoContainer implements Loggable {
    
    private static AutoContainer SINGLE_INSTANCE = new AutoContainer();

    public static AutoContainer getInstance() {
        return SINGLE_INSTANCE;
    }

    public void autoStateChooser() {
        if (fieldPosition1(true)){
            setAuto(1);
        }else if (fieldPosition2(true)){
            setAuto(2);
        }else if (fieldPosition3(true)){
            setAuto(3);
        }else if (fieldPosition4(true)){
            setAuto(4);
        }else{
            setAuto(0);
        }
    }

    private static void setAuto(int autoPosition) {
        
        switch (autoPosition) { // These are fake values, put in real stuff
            case 1:
                SwerveMap.GYRO.setAngleAdjustment(45);
                break;
            case 2:
                SwerveMap.GYRO.setAngleAdjustment(90);
                break;
            case 3:
                SwerveMap.GYRO.setAngleAdjustment(180);
                break;
            default:
                SwerveMap.GYRO.setAngleAdjustment(0);
                break;
        }
    }

    @Config.ToggleButton(name="Field Position 1", defaultValue = false, rowIndex = 2, columnIndex = 1, height = 1, width = 1)
    private static boolean fieldPosition1(boolean pos1) {
        if (pos1) {return true;}
        else {return false;}
    }

    @Config.ToggleButton(name="Field Position 2", defaultValue = false, rowIndex = 3, columnIndex = 1, height = 1, width = 1)
    private static boolean fieldPosition2(boolean pos2) {
        if (pos2) {return true;}
        else {return false;}
    }

    @Config.ToggleButton(name="Field Position 1", defaultValue = false, rowIndex = 4, columnIndex = 1, height = 1, width = 1)
    private static boolean fieldPosition3(boolean pos3) {
        if (pos3) {return true;}
        else {return false;}
    }

    @Config.ToggleButton(name="Field Position 1", defaultValue = false, rowIndex = 5, columnIndex = 1, height = 1, width = 1)
    private static boolean fieldPosition4(boolean pos4) {
        if (pos4) {return true;}
        else {return false;}
    }
}
