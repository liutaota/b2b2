package com.zsyc.zt.inca.commom;

/**
 * 出车任务模块错误出参
 */
public enum TaskErrorEnum {
    COMMON_EXCEPTION("5","系统异常"),
    COMMON_EXCEPTION_ALL("1000",""),

    FAIL_INPUT_CUSTOMER_REPLACE("1001","正在录入箱号属于其它客户，是否放弃录入当前客户信息，开始下一个客户及箱号信息录入？"),
    FAIL_INPUT_PACK_NOT_EXISTS("1002","箱号信息不存在！"),
    FAIL_INPUT_TASK_NOT_EXISTS("1003","任务不存在或正式任务不允许录入信息!"),
    FAIL_INPUT_CUSTOMER_EXISTS("1004","客户已被其它出车任务录入，请勿再在此出车任务重复录入!"),
    FAIL_INPUT_PACK_EXISTS("1005","箱号信息已录入过！"),

    FAIL_TO_SAVE_PACK_NOT_INPUT("1006","上传失败，还有未录入箱号!");

    String code;
    String msg;

    TaskErrorEnum(String code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static TaskErrorEnum getByCode(String code){
        if (code == null){
            return COMMON_EXCEPTION;
        }
        for (TaskErrorEnum rsEnum : TaskErrorEnum.values()) {
            if (code.equals(rsEnum.code)){
                return rsEnum;
            }
        }
        return COMMON_EXCEPTION;
    }

}
