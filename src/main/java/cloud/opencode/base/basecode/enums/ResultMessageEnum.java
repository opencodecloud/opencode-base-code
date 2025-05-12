package cloud.opencode.base.basecode.enums;

import lombok.Getter;

/**
 * Response message enumeration
 * 响应消息枚举
 *
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@Getter
public enum ResultMessageEnum {
    /**
     * System error message / 系统错误消息
     */
    SYSTEM_ERROR("ERROR!"),

    /**
     * System success message / 系统成功消息
     */
    SYSTEM_SUCCESS("SUCCESS!"),

    /**
     * System OK message / 系统正常消息
     */
    SYSTEM_OK("OK!");

    /**
     * Message value / 消息值
     */
    private final String value;

    /**
     * Constructor
     * 构造方法
     *
     * @param value Message value / 消息值
     */
    ResultMessageEnum(String value) {
        this.value = value;
    }
}