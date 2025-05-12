package cloud.opencode.base.basecode.enums;

import lombok.Getter;

/**
 * Response status enumeration
 * 响应状态枚举
 *
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@Getter
public enum ResultStatusEnum {
    /**
     * Success status / 成功状态
     */
    SUCCESS(0),

    /**
     * Failure status / 失败状态
     */
    FAIL(1);

    /**
     * Status code / 状态码
     */
    private final int code;

    /**
     * Constructor
     * 构造方法
     *
     * @param code Status code / 状态码
     */
    ResultStatusEnum(int code) {
        this.code = code;
    }

    /**
     * Get status name
     * 获取状态名称
     *
     * @return Status name / 状态名称
     */
    public String getName() {
        return this.name();
    }
}
