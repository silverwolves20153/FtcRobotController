package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class AxonEncoderTesting extends LinearOpMode {
    /*
    * Servo profiling needs to be coded-- might need more variables passed in to support that.
    * */
    public ServoWithProfiling a;
    /*
    * //AnalogInput armAna = hardwareMap.get(AnalogInput.class, "armAna");
        //armCurrentPosition = armAna.getVoltage() / 3.3 * 360;
        //armInitial = armCurrentPosition;
    * */

    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        a = new ServoWithProfiling("axon", "axonAna", -322.2, -0.00310365, 339.1, 1.05245, 500, 0.75);
        a.setPosition(0);
        a.updateCurrentPos();
        telemetry.addData("status", "initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("axonCurrentPosition", a.getCurrentPos());
            telemetry.addData("axonInitial", a.getInitialPos());
            telemetry.update();
            if (gamepad1.a) {
                a.setStartPosEnc(a.getCurrentPos());
                a.setEndPosEnc(a.servoToEncoderPos(0));
                a.setIsMoving(true);
            } else if (gamepad1.b) {
                a.setStartPosEnc(a.getCurrentPos());
                a.setEndPosEnc(a.servoToEncoderPos(1/3));
                a.setIsMoving(true);
            } else if (gamepad1.y) {
                a.setStartPosEnc(a.getCurrentPos());
                a.setEndPosEnc(a.servoToEncoderPos(2/3));
                a.setIsMoving(true);
            } else if (gamepad1.x) {
                a.setStartPosEnc(a.getCurrentPos());
                a.setEndPosEnc(a.servoToEncoderPos(1));
                a.setIsMoving(true);
            }
            if (a.getIsMoving()) {
                a.profile();
            }
        }
    }
    /*
    public static boolean motionProfile(ServoImplEx servo, double msForProfile, double percentStartProfiling, double startPos, double currentPos, double endPos) {
        //start, current, and end are all encoder values
        //return true if still moving, false if done
        if (startPos < endPos) {
            if (currentPos > startPos + (endPos - startPos) * percentStartProfiling) {
                //we want to be profiling

            } else {
                servo.setPosition(aEncoderToServoPos(endPos));
                return true; //we haven't even started profiling yet
            }
        } else { //startPos > endPos
            if (currentPos < startPos - (startPos - endPos) * percentStartProfiling) {
                //we want to be profiling

            } else {
                servo.setPosition(aEncoderToServoPos(endPos));
                return true; //we haven't even started profiling yet
            }
        }
        return false;
    }*/
}
