package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.IEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ScanInfoVo implements Serializable {

    /**
     * 扫码人id
     */
    private Long memberId;

    /**
     * 后台生成code
     */
    private String scanCode;

    /**
     * pending
     * scan_success
     * confirm
     */
    private String status;

    /**
     *授权code
     */
    private String loginCode;

    /**
     * @see #status
     */
    public enum EStatus implements IEnum {
        PENDING("未扫码"),
        SCAN_SUCCESS("已扫码"),
        CONFIRM("确认授权"),
        ;
        private String desc;

        EStatus(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }
}
