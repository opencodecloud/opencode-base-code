package cloud.opencode.base.basecode.enums;

/**
 * @author Jon So,
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/opencodecloud">project github</a>
 */
public enum ResultStatusEnum {
    SUCCESS(0),
    FAIL(1);
    private int code;

    ResultStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name();
    }
}
