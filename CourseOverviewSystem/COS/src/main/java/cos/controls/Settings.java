package cos.controls;

import java.io.Serializable;

public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;

    //Study settings default defined here
    private int studyWorkH = 0;

    private int studyWorkM = 45;

    private int studyBreakH = 0;

    private int studyBreakM = 15;

    private int studyCycle = 7;


    private boolean studyShowT = false;

    private boolean studyShowWT = true;

    private boolean studyShowCycles = false;


    public Settings() {


    }

    public int getStudyCycle() {
        return studyCycle;
    }

    public void setStudyCycle(int studyCycle) {
        this.studyCycle = studyCycle;
    }

    public int getStudyWorkH() {
        return studyWorkH;
    }

    public void setStudyWorkH(int studyWorkH) {
        this.studyWorkH = studyWorkH;
    }

    public int getStudyWorkM() {
        return studyWorkM;
    }

    public void setStudyWorkM(int studyWorkM) {
        this.studyWorkM = studyWorkM;
    }

    public int getStudyBreakH() {
        return studyBreakH;
    }

    public void setStudyBreakH(int studyBreakH) {
        this.studyBreakH = studyBreakH;
    }

    public int getStudyBreakM() {
        return studyBreakM;
    }

    public void setStudyBreakM(int studyBreakM) {
        this.studyBreakM = studyBreakM;
    }

    public boolean isStudyShowT() {
        return studyShowT;
    }

    public void setStudyShowT(boolean studyShowT) {
        this.studyShowT = studyShowT;
    }

    public boolean isStudyShowWT() {
        return studyShowWT;
    }

    public void setStudyShowWT(boolean studyShowWT) {
        this.studyShowWT = studyShowWT;
    }

    public boolean isStudyShowCycles() {
        return studyShowCycles;
    }

    public void setStudyShowCycles(boolean studyShowCycles) {
        this.studyShowCycles = studyShowCycles;
    }

    public void saveSettings() {


    }
}
