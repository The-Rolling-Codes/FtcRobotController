package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="attachment_TeleOp")
public class attachment_TeleOp extends OpMode {
    mecanumHardware robot = new mecanumHardware();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        //driving code
        //if (Math.abs(gamepad1.left_stick_x) > 0.1 || Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1) {
            double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);

            double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = -gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            robot.frontLeft.setPower(-1 * v1);
            robot.frontRight.setPower(-1 * v2);
            robot.backLeft.setPower(-1 * v3);
            robot.backRight.setPower(-1 * v4);

            telemetry.addData("fLPower", -1 * v1);
            telemetry.addData("fRPower", -1 * v2);
            telemetry.addData("bLPower", -1 * v3);
            telemetry.addData("bRPower", -1 * v4);


        telemetry.addData("Encoder port 1 back left", robot.backLeft.getCurrentPosition());
        telemetry.addData("Encoder port 2 front right", robot.frontRight.getCurrentPosition());
        telemetry.addData("Encoder port 3 back right", robot.backRight.getCurrentPosition());
        telemetry.addData("Encoder port 4 back left", robot.backLeft.getCurrentPosition());

        // attachment code
        telemetry.addLine();
        if (gamepad1.right_bumper) {
            robot.grabberServo.setPosition(mecanumHardware.grabber_max);
            telemetry.addData("Grabber Servo Position", mecanumHardware.grabber_max);
        }
        else if (gamepad1.left_bumper) {

            robot.grabberServo.setPosition(mecanumHardware.grabber_min);
            telemetry.addData("Grabber Servo Position", mecanumHardware.grabber_min);
        }
        else {
            telemetry.addData("Grabber Servo Position", "None");
        }

        if (gamepad1.y) {
            robot.carousel.setPower(-0.5);
        }
        if (gamepad1.b) { 
            robot.carousel.setPower(0);
        }
        if (gamepad1.a) {
            robot.carousel.setPower(0.5);
        }
        if (gamepad1.dpad_left) {
            robot.extenderServo.setPower(1);
            telemetry.addData("Extender Power" , 1);
        }
        else if (gamepad1.dpad_right) {
            robot.extenderServo.setPower(-1);
            telemetry.addData("Extender Power" , -1);
        }
        else {
            robot.extenderServo.setPower(0);
            telemetry.addData("Extender Power" , 0);
        }


        if (gamepad1.dpad_up) {
            robot.pulleyMotor0.setPower(1);
            robot.pulleyMotor1.setPower(1);

        } else if (gamepad1.dpad_down) {
            robot.pulleyMotor0.setPower(-1);
            robot.pulleyMotor1.setPower(-1);
        } else {
            robot.pulleyMotor0.setPower(0);
            robot.pulleyMotor1.setPower(0);
        }
        telemetry.update();
    }
}
