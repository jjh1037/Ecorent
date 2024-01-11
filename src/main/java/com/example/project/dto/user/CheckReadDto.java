package com.example.project.dto.user;

public class CheckReadDto {
    private int memberId;
    private int formId;
    private boolean checked;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "CheckReadDto{" +
                "memberId=" + memberId +
                ", formId=" + formId +
                ", checked=" + checked +
                '}';
    }
}
