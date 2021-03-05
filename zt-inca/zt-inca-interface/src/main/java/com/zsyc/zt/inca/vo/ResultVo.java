package com.zsyc.zt.inca.vo;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author peiqy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo implements Serializable {


    private static final long serialVersionUID = 8850468085483835669L;
    /**
     * 0表示正常
     */
    Integer errorCode;
    /**
     * 错误消息
     */
    String errorMessage;

    public static ResultVo fail(String errorMessage) {
        return new ResultVo(1, errorMessage);
    }

    public static ResultVo failErrorMessageAppend(ResultVo result, String errorMessage) {
        if (ObjectUtil.isNull(result)) {
            return new ResultVo(1, errorMessage);
        }
        return new ResultVo(result.getErrorCode() + 1, result.getErrorMessage() + "\n" + errorMessage);
    }
    public static ResultVo failAppend(ResultVo result1,ResultVo result2) {
        if (ObjectUtil.isNull(result1) && ObjectUtil.isNull(result2)) {
            return new ResultVo(0,"");
        }

        if (ObjectUtil.isNull(result1)){
           return  result2;
        }
        if (ObjectUtil.isNull(result2)){
            return  result1;
        }
        return new ResultVo(result1.getErrorCode() + result2.getErrorCode(), result1.getErrorMessage() + "\n" + result2.getErrorMessage());
    }

    /**
     * 校验输出
     * @param result
     * @return
     */
    public static ResultVo validReturn(ResultVo result){
        if(ObjectUtil.isNull(result)){
            return new ResultVo(0, "");
        }
        return result;
    }


    public Boolean isSuccess(){
        return this.getErrorCode() == 0;
    }

    public Boolean isError(){
        return this.getErrorCode() != 0;
    }




}
